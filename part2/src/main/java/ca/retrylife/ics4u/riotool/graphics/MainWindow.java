package ca.retrylife.ics4u.riotool.graphics;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;

import ca.retrylife.libics.graphics.ActionConsumer;
import ca.retrylife.libics.graphics.Window;
import net.miginfocom.swing.MigLayout;
import java.awt.event.ActionEvent;
import java.util.function.Consumer;

public class MainWindow {
    private Window window;

    /* Set Components */
    private JLabel teamLabel = new JLabel("Team number:");
    private JTextField teamField = new JTextField("5024");
    private JButton connectButton = new JButton("Connect");
    private JButton disconnectButton = new JButton("Disconnect");
    private JLabel robotNameLabel = new JLabel("Robot Name:");
    private JTextField robotName = new JTextField("  Unknown Robot  ");
    private JLabel versionsTitle = new JLabel("Versions:");
    private JTextArea versionInfo = new JTextArea(
            "Lib: Could not read library version information\nPDP: Unknown\nPCM: Unknown\nTalon 1: Not found\n Talon 2: Not found\n\n\n\n");
    private JScrollPane versionContainer;
    private JLabel toolTitle = new JLabel("Tools:");

    public MainWindow() {
        // Create the window
        window = new Window("RoboRIO Tool");
        window.setLayout(new MigLayout());

        /* Add components */

        // Team select
        window.add(teamLabel, "wrap");
        window.add(teamField, "wrap");

        // Connection controls
        window.add(connectButton, "wrap");
        window.add(disconnectButton, "wrap");

        // Robot name
        window.add(robotNameLabel, "wrap");
        window.add(robotName, "wrap");

        // Robot versions
        window.add(versionsTitle, "wrap");

        // Handle adaptible version display
        versionInfo.setEditable(false);
        versionContainer = new JScrollPane(versionInfo);
        versionContainer.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        window.add(versionContainer, "wrap");

        // Robot tools
        window.add(toolTitle, "wrap");

        // Make the window visible
        window.pack();
        window.onClose(() -> {
            System.exit(0);
        });
        window.setResizable(false);
        window.setLocationRelativeTo(null);
        window.setVisible(true);

    }

    /* Expose button listeners */

    public void setConnectionCallbacks(Consumer<ActionEvent> onConnection, Consumer<ActionEvent> onDisconnection) {
        connectButton.addActionListener(new ActionConsumer(onConnection));
        disconnectButton.addActionListener(new ActionConsumer(onDisconnection));
    }
}