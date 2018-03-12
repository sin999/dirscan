package ru.sin666.sbt.dir_scan.utils;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.*;
import java.util.stream.Stream;

import static java.lang.Math.abs;
import static java.util.stream.Collectors.joining;
import static org.junit.Assert.assertEquals;

public class JobLauncherTest {

    private final double CONFIDENCE_INTERVAL = 0.9;
    private final int EXPECTED_CALLS = 3;
    private final long INTERVAL = 100;
    private long delayInterval;
    private final String EXPECTED_STRING = ".\n.\n.\n";


    private JobLauncher jobLauncher;
    private ByteArrayOutputStream byteArrayOutputStream;

    @Before
    public void beforeJobLuncherTest() {
        jobLauncher = new JobLauncher();
        byteArrayOutputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(byteArrayOutputStream));
        delayInterval = calculateDelayInterval(INTERVAL, EXPECTED_CALLS, CONFIDENCE_INTERVAL);

    }

    @After
    public void afterJobLuncherTest() {
        System.setOut(System.out);
    }

    @Test
    public void dotPrintTest() throws InterruptedException {
        jobLauncher.launchJob(INTERVAL, () -> System.out.println("."));
        Thread.sleep(delayInterval);
        jobLauncher.stopAll();

        assertEquals("", EXPECTED_STRING, byteArrayOutputStream.toString());
    }


    private long calculateDelayInterval(long intervalLength, int expectedCallCount, double confidenceInterval) {
        return intervalLength * expectedCallCount + (long) (intervalLength * confidenceInterval);
    }
}
