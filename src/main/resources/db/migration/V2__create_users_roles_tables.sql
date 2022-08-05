CREATE TABLE roles
(
    id   BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    name VARCHAR(100)                            NOT NULL,
    CONSTRAINT pk_roles PRIMARY KEY (id)
);

CREATE TABLE users
(
    id       BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    name     VARCHAR(100)                            NOT NULL,
    username VARCHAR(100)                            NOT NULL,
    email    VARCHAR(254)                            NOT NULL,
    password VARCHAR(100)                            NOT NULL,
    CONSTRAINT pk_users PRIMARY KEY (id)
);

CREATE TABLE users_roles
(
    inventory_app_user_id BIGINT NOT NULL,
    roles_id              BIGINT NOT NULL
);

ALTER TABLE roles
    ADD CONSTRAINT uc_roles_name UNIQUE (name);

ALTER TABLE users
    ADD CONSTRAINT uc_users_email UNIQUE (email);

ALTER TABLE users
    ADD CONSTRAINT uc_users_username UNIQUE (username);

ALTER TABLE users_roles
    ADD CONSTRAINT fk_userol_on_inventory_app_role FOREIGN KEY (roles_id) REFERENCES roles (id);

ALTER TABLE users_roles
    ADD CONSTRAINT fk_userol_on_inventory_app_user FOREIGN KEY (inventory_app_user_id) REFERENCES users (id);