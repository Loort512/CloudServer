package cloudServer.ports;


public class Constants {

    private static int PORT = 8105;

    private static String HOST = "http://localhost:" + PORT;

    private static String API_HOST = HOST + "/api";

    public static String FILE_HOST = API_HOST + "/file";

    public static String USER_HOST = API_HOST + "/user";
    public static String STORAGE_PATH = System.getProperty("user.dir") + "/Storage/";
}
