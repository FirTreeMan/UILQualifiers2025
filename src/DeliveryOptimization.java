import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.Stack;

public static void main(String[] args) throws IOException {
    Scanner sc;
    BufferedWriter bw;

    for (int test = 1; test <= 10; test++) {
        sc = new Scanner(new File("deliveryoptimization/" + test + ".in"));
        bw = new BufferedWriter(new FileWriter("deliveryoptimization/" + test + ".out"));

        int n = sc.nextInt();
        ArrayList<Integer>[] adj = new ArrayList[n];
        for (int i = 0; i < n; i++)
            adj[i] = new ArrayList<>();

        for (int i = 0; i < n - 1; i++) {
            int a = sc.nextInt() - 1, b = sc.nextInt() - 1;
            adj[a].add(b);
            adj[b].add(a);
        }

        int[] distance = new int[n];
        distance[0] = 1;

        Stack<Integer> stack = new Stack<>();
        stack.add(0);

        int farthestHouse = 0;
        int maxHouses = 1;

        while (!stack.isEmpty()) {
            int p = stack.pop();

            for (int v: adj[p]) {
                if (distance[v] != 0) continue;

                distance[v] = distance[p] + 1;
                if (distance[v] > maxHouses) {
                    maxHouses = distance[v];
                    farthestHouse = v;
                }

                stack.push(v);
            }
        }

        bw.write(maxHouses + "\n");

        Arrays.fill(distance, 0);
        distance[farthestHouse] = 1;
        stack.add(farthestHouse);
        maxHouses = 0;

        while (!stack.isEmpty()) {
            int p = stack.pop();

            for (int v: adj[p]) {
                if (distance[v] != 0) continue;

                distance[v] = distance[p] + 1;
                maxHouses = Math.max(maxHouses, distance[v]);

                stack.push(v);
            }
        }

        bw.write(maxHouses + "\n");
        bw.flush();
    }
}