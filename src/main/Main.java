package main;

import game.Game;
import tests.Test;

public class Main {
    private static boolean restart;

    public static void main(String[] args) {
        restart = true;
        while (restart) {
            Game game = new Game();
            restart = false;
            game.startGame();
            while (true) {
                if (game.getRestart()) {
                    restart = true;
                    break;
                }
            }
            //Test test = new Test();
            //test.runAllTests();
        }
    }
}

