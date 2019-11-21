package ca.retrylife.ics4u.riotool.network;

public class Netconn {

    private static Netconn instance;

    public static Netconn getInstance() {
        if (instance == null) {
            instance = new Netconn();
        }

        return instance;
    }

    public void connect() {
        System.out.println("Connecting to robot");
    }

    public void disconnect() {
        System.out.println("Disconnecting from robot");
    }
}