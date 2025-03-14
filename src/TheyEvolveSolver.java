import java.io.IOException;
import java.util.Scanner;
import java.util.TreeMap;

public static void main(String[] args) throws IOException {
    Scanner sc = new Scanner(System.in);

    int n = sc.nextInt();
    int p = sc.nextInt();

    int[] maxNormalEffectiveness = new int[n];
    TreeMap<Integer, Integer>[] evolvingEffectivenessFrequency = new TreeMap[n];
    for (int i = 0; i < n; i++) evolvingEffectivenessFrequency[i] = new TreeMap<>();
    boolean[] invalid = new boolean[n];

    for (int breed = 0; breed < p; breed++) {
        boolean evolving = sc.next().equals("Abnormal");
        for (int i = 0; i < n; i++) {
            int effectiveness = sc.nextInt();
            if (invalid[i]) continue;

            if (evolving) {
                if (effectiveness <= 50) {
                    invalid[i] = true;
                    continue;
                }
            } else if (effectiveness >= 50) {
                invalid[i] = true;
                continue;
            }

            if (evolving)
                evolvingEffectivenessFrequency[i].merge(effectiveness, 1, Integer::sum);
            else maxNormalEffectiveness[i] = Math.max(maxNormalEffectiveness[i], effectiveness);
        }
    }

    int pesticide = -1;
    int bestSuitability = Integer.MIN_VALUE;
    for (int i = 0; i < n; i++) {
        if (invalid[i]) continue;

        int mode = evolvingEffectivenessFrequency[i].lastKey();
        for (int effectiveness: evolvingEffectivenessFrequency[i].descendingKeySet())
            if (evolvingEffectivenessFrequency[i].get(effectiveness) > evolvingEffectivenessFrequency[i].get(mode)) {
                mode = effectiveness;
                if (mode <= 50) break;
            }
        if (mode <= 50) continue;

        int suitability = mode - maxNormalEffectiveness[i];
        if (suitability > bestSuitability) {
            bestSuitability = suitability;
            pesticide = i;
        }
    }

    if (pesticide == -1)
        System.out.println("Need more development");
    else System.out.println(pesticide + 1);
}