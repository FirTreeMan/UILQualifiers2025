import java.io.*;
import java.util.Scanner;

public static void main(String[] args) throws IOException {
    Scanner sc;
    BufferedWriter bw;

    for (int test = 1; test <= 10; test++) {
        sc = new Scanner(new File("accidentalpurchase/input" + test + ".txt"));
        bw = new BufferedWriter(new FileWriter("accidentalpurchase/output" + test + ".txt"));

        int n = sc.nextInt();
        long permutations = 1L << n;

        bw.write(permutations + "000000");
        bw.flush();
    }
}
