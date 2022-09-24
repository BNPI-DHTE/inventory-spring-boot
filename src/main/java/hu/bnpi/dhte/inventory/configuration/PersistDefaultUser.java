package hu.bnpi.dhte.inventory.configuration;

import org.flywaydb.core.api.callback.Callback;
import org.flywaydb.core.api.callback.Context;
import org.flywaydb.core.api.callback.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.sql.*;

@Component
public class PersistDefaultUser implements Callback {

    @Value("${spring.security.user.name}")
    private String defaultUsername;

    @Value("${spring.security.user.password}")
    private String defaultUserPassword;

    @Value("${spring.security.user.roles}")
    private String defaultUserRole;

    @Value("${inventory.default.user.permission.name}")
    private String defaultPermissionName;

    @Value("${inventory.default.user.permission.description}")
    private String defaultPermissionDescription;

    @Override
    public boolean supports(Event event, Context context) {
        return event == Event.AFTER_MIGRATE;
    }

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public PersistDefaultUser(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public boolean canHandleInTransaction(Event event, Context context) {
        return false;
    }

    @Override
    public void handle(Event event, Context context) {
        if (supports(event, context)) {
            Connection connection = context.getConnection();
            try {
                if (isDefaultUserPersisted(connection)) {
                    persistDefaultUser(connection);
                }
            } catch (SQLException sqle) {
                throw new IllegalStateException("Cannot insert default user", sqle);
            }
        }
    }

    @Override
    public String getCallbackName() {
        return PersistDefaultUser.class.getSimpleName();
    }

    private boolean isDefaultUserPersisted(Connection connection) throws SQLException {
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(
                    "select value from settings where name = 'default_user_is_set'");
            int isDefaultUserPersisted = 0;
            while (resultSet.next()) {
                isDefaultUserPersisted = resultSet.getInt(1);
            }
            return isDefaultUserPersisted != 1;
        }
    }

    private void persistDefaultUser(Connection connection) throws SQLException {
        long permissionId = insertDefaultPermission(connection);
        long roleId = insertDefaultRole(connection);
        long userId = insertDefaultUser(connection);
        assignPermissionToRole(connection, permissionId, roleId);
        assignRoleToUser(connection, roleId, userId);
        updateState(connection);
    }

    private long insertDefaultPermission(Connection connection) throws SQLException {
        try (PreparedStatement insertDefaultPermission = connection.prepareStatement(
                "insert into permissions (name, description) values (?, ?)",
                Statement.RETURN_GENERATED_KEYS)) {
            insertDefaultPermission.setString(1, defaultPermissionName);
            insertDefaultPermission.setString(2, defaultPermissionDescription);
            insertDefaultPermission.executeUpdate();
            return parseGeneratedKey(insertDefaultPermission);
        }
    }

    private long insertDefaultRole(Connection connection) throws SQLException {
        try (PreparedStatement insertDefaultRole = connection.prepareStatement(
                "insert into roles (name) values (?)",
                Statement.RETURN_GENERATED_KEYS)) {
            insertDefaultRole.setString(1, defaultUserRole);
            insertDefaultRole.executeUpdate();
            return parseGeneratedKey(insertDefaultRole);
        }
    }

    private long insertDefaultUser(Connection connection) throws SQLException {
        try (PreparedStatement insertDefaultUser = connection.prepareStatement(
                "insert into users (" +
                        "username," +
                        "password," +
                        "is_account_non_expired," +
                        "is_account_non_locked," +
                        "is_credentials_non_expired," +
                        "is_account_enabled)" +
                        " values (?, ?, ?, ?, ?, ?)",
                Statement.RETURN_GENERATED_KEYS)) {
            insertDefaultUser.setString(1, defaultUsername);
            insertDefaultUser.setString(2, encryptDefaultPassword());
            insertDefaultUser.setBoolean(3, true);
            insertDefaultUser.setBoolean(4, true);
            insertDefaultUser.setBoolean(5, true);
            insertDefaultUser.setBoolean(6, true);
            insertDefaultUser.executeUpdate();
            return parseGeneratedKey(insertDefaultUser);
        }
    }

    private long parseGeneratedKey(PreparedStatement insertDefaultRole) throws SQLException {
        try (ResultSet key = insertDefaultRole.getGeneratedKeys()) {
            long id = 0;
            while (key.next()) {
                id = key.getLong(1);
            }
            return id;
        }
    }

    private String encryptDefaultPassword() {
        return passwordEncoder.encode(defaultUserPassword);
    }

    private void assignPermissionToRole(Connection connection, long permissionId, long roleId) throws SQLException {
        try (PreparedStatement assignPermissionToRole = connection.prepareStatement(
                "insert into roles_permissions (permission_id, role_id) values (?, ?)")) {
            assignPermissionToRole.setLong(1, permissionId);
            assignPermissionToRole.setLong(2, roleId);
            assignPermissionToRole.executeUpdate();
        }
    }

    private void assignRoleToUser(Connection connection, long roleId, long userId) throws SQLException {
        try (PreparedStatement assignRoleToUser = connection.prepareStatement(
                "insert into user_roles (role_id, user_id) values (?, ?)")) {
            assignRoleToUser.setLong(1, roleId);
            assignRoleToUser.setLong(2, userId);
            assignRoleToUser.executeUpdate();
        }
    }

    private void updateState(Connection connection) throws SQLException {
        try (PreparedStatement updateState = connection.prepareStatement(
                "update settings set value = 1 where name = 'default_user_is_set'")) {
            updateState.executeUpdate();
        }
    }
}
