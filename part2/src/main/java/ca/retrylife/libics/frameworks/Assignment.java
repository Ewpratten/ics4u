package ca.retrylife.libics.frameworks;

import java.util.ArrayList;

public abstract class Assignment implements Runnable {

    // ArrayList to store all assignments
    public static ArrayList<Assignment> _assignments = new ArrayList<Assignment>();
    public String _name = "unnamed";

    public void register(String name) {
        this._name = name;
        _assignments.add(this);
    }
}
