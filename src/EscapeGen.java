import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public static void main(String[] args) throws IOException {
    Random random = new Random();
    BufferedWriter bw;

    for (int test = 1; test <= 10; test++) {
        System.out.println("test " + test);

        bw = new BufferedWriter(new FileWriter("escape/input" + test + ".txt"));

        int locationCount = random.nextInt(90000, 100000);
        int cycleTotal = 0;
        int[] locations = new int[locationCount];
        for (int i = 0; i < locationCount; i++)
            locations[i] = i + 1;
        shuffleArray(locations);

        ArrayList<int[]> edges = new ArrayList<>();
        int idx = 0;
        while (idx < locationCount) {
            int componentLength = Math.min(random.nextInt(500, 1000), locationCount - idx - 1);
            if (idx + componentLength == locationCount - 1) componentLength++;
            int cycleLength = random.nextInt(1, componentLength) + 1;

            int entryNode = -1;
            for (int i = componentLength; i > 0; i--) {
                if (i == cycleLength) entryNode = locations[idx];
                edges.add(new int[]{locations[idx],
                        i != 1 ? locations[idx+1] : entryNode});

                idx++;
            }

            cycleTotal += cycleLength;
        }

        int pursuers = random.nextInt(cycleTotal / 2, cycleTotal * 3);

        bw.write(locationCount + " " + pursuers + "\n");
        int[][] edgeArray = edges.toArray(new int[0][0]);
        shuffleArray(edgeArray);
        for (int[] edge: edgeArray)
            bw.write(edge[0] + " " + edge[1] + "\n");

        bw.flush();
        System.out.println("finished");
    }
    System.out.println("all done");
}

static void shuffleArray(int[] arr) {
    int idx, temp;
    Random random = new Random();
    for (int i = arr.length - 1; i > 0; i--)
    {
        idx = random.nextInt(i + 1);
        temp = arr[idx];
        arr[idx] = arr[i];
        arr[i] = temp;
    }
}

static void shuffleArray(int[][] arr) {
    int idx;
    int[] temp;
    Random random = new Random();
    for (int i = arr.length - 1; i > 0; i--)
    {
        idx = random.nextInt(i + 1);
        temp = arr[idx];
        arr[idx] = arr[i];
        arr[i] = temp;
    }
}