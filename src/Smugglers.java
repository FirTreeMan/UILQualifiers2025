import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

static int n;
static int desired;
static long[] prefixSums;

public static void main(String[] args) throws IOException {
    Scanner sc;
    BufferedWriter bw;

    for (int test = 1; test <= 10; test++) {
        sc = new Scanner(new File("smugglers/input" + test + ".txt"));
        bw = new BufferedWriter(new FileWriter("smugglers/output" + test + ".txt"));

        n = sc.nextInt();
        desired = sc.nextInt();
        prefixSums = new long[n+1];

        for (int i = 1; i <= n; i++)
            prefixSums[i] = prefixSums[i-1] + sc.nextLong();

        bw.write(binarySearch() + "\n");
        bw.flush();
    }
}

static int binarySearch() {
    int ans = 0;
    int l = 1;
    int r = n;
    while (l <= r) {
        int mid = l + (r - l) / 2;
        if (check(mid)) {
            ans = n - mid + 1;
            l = mid + 1;
        } else r = mid - 1;
    }

    return ans;
}

static boolean check(int mid) {
    return prefixSums[n] - prefixSums[mid-1] >= desired;
}