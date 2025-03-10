import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public static void main(String[] args) throws IOException {
    Scanner sc;
    BufferedWriter bw;

    for (int test = 1; test <= 10; test++) {
        sc = new Scanner(new File("highendpizza/input" + test + ".txt"));
        bw = new BufferedWriter(new FileWriter("highendpizza/output" + test + ".txt"));

        int n = sc.nextInt();
        int m = sc.nextInt();
        int[] prefixSums = new int[n+1];

        for (int i = 1; i <= n; i++)
            prefixSums[i] = prefixSums[i-1] + sc.nextInt();

        int optimalDay = 0;
        int optimalSold = 0;

        for (int i = 1; i <= n - m; i++) {
            int sold = prefixSums[i+m/2-1] - prefixSums[i-1] + prefixSums[i+m-1] - prefixSums[i+m/2];
            if (sold > optimalSold) {
                optimalSold = sold;
                optimalDay = i;
            }
        }

        bw.write(optimalDay + "\n");
        bw.flush();
    }
}