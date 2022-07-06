package cloudServer.ports;

public class Constants {

    private static String WAREHOUSE_URL = "http://localhost:8095";

    public static String GET_COMPONENTS_URL = WAREHOUSE_URL + "/api/components";

    public static String URL_GET_COMPONENT_BY_PRODUCTID = GET_COMPONENTS_URL + "?productID=%s";
}
