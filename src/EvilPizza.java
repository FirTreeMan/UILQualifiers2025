import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

static int n;
static int h;
static int[] hideouts;

public static void main(String[] args) throws IOException {
    Scanner sc;
    BufferedWriter bw;

    for (int test = 1; test <= 10; test++) {
        sc = new Scanner(new File("evilpizza/input/input" + test + ".txt"));
        bw = new BufferedWriter(new FileWriter("evilpizza/output/output" + test + ".txt"));

        n = sc.nextInt();
        h = sc.nextInt();

        hideouts = new int[h];
        for (int i = 0; i < h; i++)
            hideouts[i] = sc.nextInt();

        int most = mostWarehouses();

        bw.write(most == -1 ? "It's so over..." : Integer.toString(most));

        bw.flush();
    }
}

static int mostWarehouses() {
    int most = 0;

    long totalPizzas = 0;
    int[] frequencies = new int[31];
    for (int pizzas: hideouts) {
        totalPizzas += pizzas;
        for (int i = 0; i < 31; i++)
            if ((pizzas & (1 << i)) != 0) {
                frequencies[i]++;
                break;
            }
    }

    if (totalPizzas % n != 0) return -1;
    int perWorker = (int) (totalPizzas / n);

    int[] needed = new int[31];
    for (int i = 0; i < 31; i++)
        needed[i] = (perWorker & (1 << i)) > 0 ? 1 : 0;

    while (!empty(frequencies)) {
        for (int i = 30; i >= 0; i--) {
            if (needed[i] > frequencies[i]) {
                int increase = breakdown(needed, frequencies, i);
                if (increase == -1) return -1;
                most += increase;
            }
        }

        subMax(frequencies, needed);
    }

    return most;
}

static boolean empty(int[] arr) {
    for (int i: arr)
        if (i != 0) return false;
    return true;
}

static void subMax(int[] a, int[] b) {
    int minDiff = Integer.MAX_VALUE;
    for (int i = 0; i < 31; i++) {
        if (b[i] == 0) continue;
        if (a[i] < b[i]) return;
        minDiff = Math.min(minDiff, a[i] / b[i]);
    }

    if (minDiff == Integer.MAX_VALUE) return;

    for (int i = 0; i < 31; i++) {
        a[i] -= minDiff * b[i];
    }
}

static int breakdown(int[] needed, int[] frequencies, int neededIdx) {
    if (neededIdx == 0) return -1;

    int diff = needed[neededIdx] - frequencies[neededIdx];
    needed[neededIdx] -= diff;
    needed[neededIdx-1] += diff * 2;

    return diff;
}
