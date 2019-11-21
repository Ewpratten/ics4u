package ca.retrylife.libics.graphics;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.function.Consumer;

/**
 * Adapts a consumer to an Action
 */
public class ActionConsumer implements ActionListener {

    Consumer<ActionEvent> action;

    public ActionConsumer(Consumer<ActionEvent> action) {
        this.action = action;
    }

    @Override
    public void actionPerformed(ActionEvent arg0) {
        action.accept(arg0);
    }

}