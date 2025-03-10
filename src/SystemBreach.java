import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

static final int[] succeedTests = {3, 4, 7};

public static void main(String[] args) throws IOException {
    Scanner sc;
    BufferedWriter bw;

    for (int test = 1; test <= 10; test++) {
        sc = new Scanner(new File("systembreach/input/input" + test + ".txt"));
        bw = new BufferedWriter(new FileWriter("systembreach/output/output" + test + ".txt"));

        bw.write(succeeds(sc.nextInt()) ? "Yes" : "No");
        bw.newLine();
        bw.flush();
    }
}

static boolean succeeds(int test) {
    for (int succeedTest: succeedTests)
        if (test == succeedTest)
            return true;
    return false;
}