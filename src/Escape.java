import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;
import java.util.Scanner;

static int n;
static int[] step;
static boolean[] globalSeen;

public static void main(String[] args) throws IOException {
    Scanner sc;
    BufferedWriter bw;

    for (int test = 1; test <= 10; test++) {
        sc = new Scanner(new File("escape/input" + test + ".txt"));
        bw = new BufferedWriter(new FileWriter("escape/output" + test + ".txt"));

        n = sc.nextInt();
        int pursuers = sc.nextInt();

        step = new int[n];
        globalSeen = new boolean[n];
        for (int i = 0; i < n; i++) {
            int start = sc.nextInt() - 1;
            step[start] = sc.nextInt() - 1;
        }

        int cycleTotal = 0;
        for (int i = 0; i < n; i++)
            if (!globalSeen[i])
                cycleTotal += findCycle(i);

        if (cycleTotal >= pursuers)
            bw.write("Freedom!\n");
        else bw.write((pursuers - cycleTotal) + "\n");
        bw.flush();
    }
}

static int findCycle(int pos) {
    HashSet<Integer> localSeen = new HashSet<>();

    while (!localSeen.contains(pos)) {
        localSeen.add(pos);
        pos = step[pos];
        if (globalSeen[pos]) return 0;
    }

    int entryNode = pos;
    pos = step[pos];
    int length = 1;

    while (pos != entryNode) {
        pos = step[pos];
        length++;
    }

    for (int i: localSeen)
        globalSeen[i] = true;

    return length;
}