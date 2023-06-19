package org.example;

import java.time.Duration;
import java.util.Timer;
import java.util.TimerTask;

public class CheckStringFound {
    public CheckStringFound() {
        Timer timer = new Timer();
        TimerTask timerTask = new TimerTask() {
            int i = 0;
            int dots = 0;

            @Override
            public void run() {

                if (Task.isCollisionFound()) {

                    System.out.println("\n\ntime Elapsed is " + Duration.ofMillis( System.currentTimeMillis() - Main.beginTimeInMillis).toMinutes() + " Minutes.");

                    Task.collisionStrings
                            .forEach((s, s2) ->
                            System.out.println( (++i) +"." + "\t(String , Hash value) -> (" + s + " , " + s2 + ")."));

                    Task.setCollisionFound(false);

                }

                else {System.out.print("\rSearching" + ".".repeat(dots++));dots %= 4;}

            }
        };

        timer.scheduleAtFixedRate(timerTask, 0, 1000);
    }

    public static void start() {
        new CheckStringFound();
    }
}
