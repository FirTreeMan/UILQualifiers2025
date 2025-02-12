import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

static final char[] chars = "AaBbCcDdEeFfGgHhIiJjKkLlMmNnOoPpQqRrSsTtUuVvWwXxYyZz0123456789".toCharArray();

public static void main(String[] args) throws IOException {
    BufferedWriter bw;

    for (int test = 1; test <= 10; test++) {
        System.out.println("test " + test);

        bw = new BufferedWriter(new FileWriter("saveaslice/" + test + ".in"));
        int rows = (int) (Math.random() * 50) + 1;
        int cols = (int) (Math.random() * 50) + 1;
        if (test >= 6) {
            rows += 50;
            cols += 50;
        }

        int sliceRows = (int) (Math.random() * rows) + 1;
        int sliceCols = (int) (Math.random() * cols) + 1;

        bw.write(rows + " " + cols + "\n");
        bw.write(sliceRows + " " + sliceCols + "\n");

        int toppingCount = (int) (Math.random() * (rows + cols) / 2) + 1;
        char[] toppings = new char[toppingCount + 1];

        toppings[0] = ' ';
        for (int i = 1; i <= toppingCount; i++)
            toppings[i] = chars[(int) (Math.random() * chars.length)];

        for (int r = 0; r < rows; r++) {
            bw.write(toppings[(int) (Math.random() * toppings.length)]);
            for (int c = 1; c < cols; c++)
                bw.write(" " + toppings[(int) (Math.random() * toppings.length)]);
            bw.newLine();
        }

        bw.flush();
        System.out.println("finished");
    }
    System.out.println("all done");
}
