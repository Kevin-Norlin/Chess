package game;

import assets.Piece;

import java.sql.Time;
import java.util.ArrayList;

public class Player {
    // TODO: Add timer capabilities for player (startTimer() etc..)
    private ArrayList<Piece> pieces;
    private int num;
    private boolean isTimerRunning;
    private long startTime;
    private long elapsedTime;
    private static final int TIME = 60 * 10 * 1000; // Total time in ms

    public Player(int num) {
        this.num = num;
    }

    public int getNum() {
        return this.num;
    }

    public void startTimer() {
        if (!isTimerRunning) {
            this.isTimerRunning = true;
            this.startTime = System.currentTimeMillis();
            // Start a separate thread to update the time continuously
            new Thread(() -> {
                while (isTimerRunning) {
                    long currentTime = System.currentTimeMillis();
                    this.elapsedTime += (currentTime - this.startTime);
                    this.startTime = currentTime;
                    try {
                        Thread.sleep(100); // Update the time every 100 milliseconds
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }
    }

    public void stopTimer() {
        if (isTimerRunning) {
            this.isTimerRunning = false;
        }
    }

    public void resetTimer() {
        this.isTimerRunning = false;
        this.startTime = 0;
        this.elapsedTime = 0;
    }

    public long getRemainingTime() {
        if (isTimerRunning) {
            long remainingTime = TIME - this.elapsedTime;
            return Math.max(remainingTime, 0); // Ensure the remaining time is non-negative
        } else {
            return TIME;
        }
    }

    public String getRemainingTimeAsString() {
        long remainingTime = TIME - this.elapsedTime;
        long seconds = remainingTime / 1000;
        int minutes = (int) (Math.floor(seconds / 60));
        seconds %= 60;
        String retStr = String.valueOf(minutes) + " : " + String.valueOf(seconds);
        return retStr;
    }
}
