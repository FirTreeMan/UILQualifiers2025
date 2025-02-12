import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public static void main(String[] args) throws IOException {
    Random random = new Random();
    BufferedWriter bw;

    for (int test = 1; test <= 10; test++) {
        System.out.println("test " + test);

        bw = new BufferedWriter(new FileWriter("highendpizza/" + test + ".in"));

        int calculatedDays = random.nextInt(100000, 1000001);
        int openingDays;
        if (test <= 5)
            openingDays = random.nextInt(3, 50);
        else openingDays = random.nextInt(100000, 1000000);
        if (openingDays % 2 == 0) openingDays++;

        bw.write(calculatedDays + " " + openingDays + "\n");

        while (calculatedDays-->0)
            bw.write(random.nextInt(1, 101) + "\n");

        bw.flush();
        System.out.println("finished");
    }
    System.out.println("all done");
}