package ca.retrylife.ics4u.riotool;

import ca.retrylife.ics4u.riotool.graphics.MainWindow;
import ca.retrylife.ics4u.riotool.network.Netconn;
import ca.retrylife.libics.frameworks.Assignment;

public class App extends Assignment {

    MainWindow mw;

    public static void main(String[] args) {
        (new App()).run();
    }

    public App() {
        register("RoboRIO Tool");

        // Create the main window
        mw = new MainWindow();

        // Set up callbacks
        mw.setConnectionCallbacks((ae) -> {
            Netconn.getInstance().connect();
        }, (ae) -> {
            Netconn.getInstance().disconnect();
        });

    }

    @Override
    public void run() {

    }
}