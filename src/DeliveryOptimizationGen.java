import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.Random;
import java.util.Stack;

public static void main(String[] args) throws IOException {
    BufferedWriter bw;

    for (int test = 1; test <= 10; test++) {
        System.out.println("test " + test);

        bw = new BufferedWriter(new FileWriter("deliveryoptimization/input" + test + ".txt"));

        int nodeCount = (int) (Math.random() * 9999) + 2;
        bw.write(nodeCount + "\n");

        int[] nodes = new int[nodeCount];
        for (int i = 0; i < nodeCount; i++)
            nodes[i] = i + 1;
        shuffleArray(nodes);

        Stack<Integer> availableNodes = new Stack<>();
        for (int shuffledNode: nodes)
            availableNodes.push(shuffledNode);

        int x = availableNodes.pop();
        ArrayDeque<Integer> q = new ArrayDeque<>();
        q.add(x);

        int maxChildren = 4;
        while (!q.isEmpty()) {
            int p = q.pop();
            int childCount = Math.min((int) (Math.random() * maxChildren) + 1, availableNodes.size());
            if (Math.random() < 0.05)
                maxChildren = Math.max(maxChildren - 1, 1);

            while (childCount-->0) {
                int v = availableNodes.pop();
                q.add(v);
                bw.write(p + " " + v + "\n");
            }
        }

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