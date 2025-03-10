import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public static void main(String[] args) throws IOException {
    Random random = new Random();
    BufferedWriter bw;

    for (int test = 1; test <= 10; test++) {
        System.out.println("test " + test);

        bw = new BufferedWriter(new FileWriter("theyevolve/input" + test + ".txt"));

        int pesticides = random.nextInt(1, 101);
        int breeds = random.nextInt(1, 10001);

        bw.write(pesticides + " " + breeds + "\n");

        for (int i = 0; i < breeds; i++) {
            boolean evolves = random.nextBoolean();
            bw.write(evolves ? "Abnormal" : "Normal");

            for (int p = 0; p < pesticides; p++) {
                boolean fails = random.nextInt(breeds * 2) == 0;
                int effectiveness = random.nextInt(50);
                if (fails) {
                    if (!evolves) effectiveness += 51;
                } else if (evolves) effectiveness += 51;

                bw.write(" " + effectiveness);
            }

            bw.newLine();
        }

        bw.flush();
        System.out.println("finished");
    }

    System.out.println("all done");
}