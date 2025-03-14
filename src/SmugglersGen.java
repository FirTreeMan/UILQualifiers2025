import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public static void main(String[] args) throws IOException {
    Random random = new Random();
    BufferedWriter bw;

    for (int test = 1; test <= 10; test++) {
        System.out.println("test " + test);

        bw = new BufferedWriter(new FileWriter("smugglers/input/input" + test + ".txt"));

        int hideoutCount, queries;
        if (test <= 5) {
            hideoutCount = random.nextInt(8000, 10001);
            queries = random.nextInt(800, 1001);
        } else {
            hideoutCount = random.nextInt(80000, 100001);
            queries = random.nextInt(80000, 100001);
        }

        bw.write(hideoutCount + " " + queries + "\n");

        long total = 0;
        int[] hideouts = new int[hideoutCount];
        for (int i = 0; i < hideoutCount; i++) {
            if (random.nextInt(4) == 0)
                hideouts[i] = random.nextInt(1, 10000000);
            else hideouts[i] = random.nextInt(1, 1000000001);
            total += hideouts[i];
        }

        for (int pizzaCount: hideouts)
            bw.write(pizzaCount + "\n");
        for (int i = 0; i < queries; i++)
            bw.write(random.nextLong(total / 2, total / 10 * 9) + "\n");

        bw.flush();
        System.out.println("finished");
    }

    System.out.println("all done");
}