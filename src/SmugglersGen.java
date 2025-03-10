import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public static void main(String[] args) throws IOException {
    Random random = new Random();
    BufferedWriter bw;

    for (int test = 1; test <= 10; test++) {
        System.out.println("test " + test);

        bw = new BufferedWriter(new FileWriter("smugglers/input" + test + ".txt"));

        int hideoutCount;
        if (test <= 5)
            hideoutCount = random.nextInt(1000, 10001);
        else hideoutCount = random.nextInt(100001, 1000001);

        int pizzaNeeded = random.nextInt(hideoutCount * 2, 1000000001);

        bw.write(hideoutCount + " " + pizzaNeeded + "\n");

        int[] hideouts = new int[hideoutCount];

        int baseAmount = random.nextInt(pizzaNeeded / 2, pizzaNeeded * 2 / 3);
        int hideoutsNecessary = hideoutCount / 2 + random.nextInt(hideoutCount / 4, hideoutCount / 2 + 1);
        pizzaNeeded -= baseAmount;

        for (int i = hideoutCount - hideoutsNecessary; i < hideoutCount; i++) {
            int added = random.nextInt(hideouts[i-1], pizzaNeeded / (hideoutCount - i) * 3 / 4);
            hideouts[i] += added;
            pizzaNeeded -= added;
        }
        hideouts[hideoutCount-1] += pizzaNeeded;
        for (int i = hideoutCount - hideoutsNecessary; i < hideoutCount; i++)
            hideouts[i] += baseAmount / hideoutsNecessary + (baseAmount % hideoutsNecessary) / 2;

        for (int i = hideoutCount - hideoutsNecessary - 1; i >= 0; i--) {
            hideouts[i] = random.nextInt((int) (hideouts[i+1] * (hideoutsNecessary - 1D) / hideoutsNecessary), hideouts[i+1]) + 1;
        }

        for (int pizzaCount: hideouts)
            bw.write(pizzaCount + "\n");

        bw.flush();
        System.out.println("finished");
    }

    System.out.println("all done");
}