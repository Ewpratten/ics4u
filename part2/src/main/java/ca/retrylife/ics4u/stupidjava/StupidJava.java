package ca.retrylife.ics4u.stupidjava;

import java.util.ArrayList;

public class StupidJava implements Runnable {

    ArrayList<Integer> wid = new ArrayList<>();
    
    public static void main(String[] args) {
        new StupidJava().run();
    }

    public StupidJava() {

        // Stupid FOR loop
        wid.add(500);
        for (; wid.size() - 1 < wid.get(0); wid.add(0)) {
            
        }
        
    }

    @Override
    public void run() {
        
    }
}