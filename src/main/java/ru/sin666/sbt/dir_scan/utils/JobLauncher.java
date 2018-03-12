package ru.sin666.sbt.dir_scan.utils;

import java.util.Timer;
import java.util.TimerTask;

public class JobLauncher {
    private Timer timer;

    public JobLauncher(){
        timer = new Timer(true);
    }

    public void launchJob(long delay, final Runnable job){
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                job.run();
            }
        }, delay, delay);
    }

    public void stopAll(){
        timer.cancel();
    }
}
