package hu.bnpi.dhte.inventory.openapi.constant;

public class SwaggerConstant {

    private SwaggerConstant() {
        throw new IllegalStateException("Utility class");
    }

    public static final String SECURITY_REFERENCE = "Token Access";

    public static final String AUTHORIZATION_DESCRIPTION = "Full API Permission";

    public static final String AUTHORIZATION_SCOPE = "Unlimited";

    public static final String API_TITLE = "Inventory Management Application OpenAPI documentation";

    public static final String API_DESCRIPTION = "In this interactive documentation you can understand the Inventory Management Application's " +
            "API. You can find what JSON structures accepted by the API, what kind of data you can get from the application. You can also try, " +
            "how the application works.";

    public static final String API_VERSION = "0.0.2-SNAPSHOT";

    public static final String SECURE_PATH = "/*/.*";

    public static final String API_TAG = "Invoice Service";
}
