
package gameofgreed;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GameOfGreed {
    final static int RUN_COUNT = 10000;

    public static void main(String[] args) {
        List<Integer>[] trials = new List[RUN_COUNT];
        //run the games
        for (int i = 0; i < RUN_COUNT; i++) {
            trials[i] = runGame();
        }

        //determine maximum game length
        int maxSize = 0;
        for (List<Integer> trial : trials) {
            if (trial.size() > maxSize) {
                maxSize = trial.size();
            }
        }

        //pad zero values (people who got 0 points)
        for (List<Integer> trial : trials) {
            while (trial.size() < maxSize)
                trial.add(0);
        }

        //TEST
//        for (List<Integer> trial : trials) {
//            System.out.println(trial);
//        }
        System.out.println("TRIALS RUN: " + RUN_COUNT);
        System.out.println("MAX GREED: " + (maxSize - 1));

        for (int i = 0; i < maxSize; i++) {
            System.out.print("Greed: " + i);
            String toPrint = "";
            int total = 0;
            for (List<Integer> trial : trials) {
                if (trial.get(i) != 0)
                    toPrint += trial.get(i) + ", ";
                total += trial.get(i);
            }
            System.out.println("\tAverage Score: " + (double) total / RUN_COUNT);
            toPrint = toPrint.substring(0, toPrint.length() - 2);
            System.out.println(toPrint);
            System.out.println();
//            copy(toPrint);
//            scan.nextLine();
        }
    }
    private static final Random RANDOM = new Random();

    static List<Integer> runGame() {
        List<Integer> scores = new ArrayList<>();
        int points = RANDOM.nextInt(6) + 1 + RANDOM.nextInt(6) + 1; //two dice thrown for initial score
        int roll = 0;
        while (roll != 2) { //once 2 is rolled, game over, no value (0 points) for rest of list
            scores.add(points); //person who sits down collects their points
            roll = RANDOM.nextInt(6) + 1; //roll die
            points += roll;
        }
        return scores;
    }
    private static final Clipboard CLIPBOARD = Toolkit.getDefaultToolkit().getSystemClipboard();

    private static void copy(String myString) {
        StringSelection stringSelection = new StringSelection(myString);
        CLIPBOARD.setContents(stringSelection, null);
    }
}
