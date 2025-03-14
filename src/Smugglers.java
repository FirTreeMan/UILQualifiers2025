import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

static int n;
static int m;
static int[] arr;
static long[] prefixSums;

public static void main(String[] args) throws IOException {
    Scanner sc;
    BufferedWriter bw;

    for (int test = 1; test <= 10; test++) {
        sc = new Scanner(new File("smugglers/input/input" + test + ".txt"));
        bw = new BufferedWriter(new FileWriter("smugglers/output/output" + test + ".txt"));

        n = sc.nextInt();
        m = sc.nextInt();
        arr = new int[n+1];
        prefixSums = new long[n+1];

        for (int i = n; i > 0; i--)
            arr[i] = sc.nextInt();
        for (int i = 1; i <= n; i++)
            prefixSums[i] = prefixSums[i-1] + arr[i];

        while (m-->0)
            bw.write(binarySearch(sc.nextLong()) + "\n");
        bw.flush();
    }
}

static int binarySearch(long desired) {
    int ans = 0;
    int l = 1;
    int r = n;
    while (l <= r) {
        int mid = l + (r - l) / 2;
        if (check(mid, desired)) {
            ans = mid;
            r = mid - 1;
        } else l = mid + 1;
    }

    return ans;
}

static boolean check(int mid, long desired) {
    return prefixSums[mid] >= desired;
}