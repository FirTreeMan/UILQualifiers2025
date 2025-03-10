import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public static void main(String[] args) throws IOException {
    Random random = new Random();
    BufferedWriter bw;

    for (int test = 1; test <= 10; test++) {
        System.out.println("test " + test);

        bw = new BufferedWriter(new FileWriter("evilpizza/input/input" + test + ".txt"));

        int workers, hideoutCnt;
        if (test <= 5) {
            workers = random.nextInt(8000, 9000);
            hideoutCnt = random.nextInt(workers * 10, 100001 - workers);
        } else {
            workers = random.nextInt(80000, 90000);
            hideoutCnt = random.nextInt(workers * 10, 1000001 - workers);
        }

        int[] target = new int[31];
        int totalHideouts = 0;
        while (totalHideouts < workers * 8) {
            for (int i = 30; i >= 0; i--) {
                if (totalHideouts >= workers * 8) break;
                if (target[i] == 0 && random.nextInt(10) == 0) {
                    target[i] += workers;
                    totalHideouts += workers;
                }
            }
        }

        while (totalHideouts < hideoutCnt) {
            for (int i = 30; i > 0; i--) {
                if (totalHideouts >= hideoutCnt) break;
                if (target[i] == 0 || random.nextInt(4) != 0) continue;

                int decomposedAmt = Math.min(hideoutCnt - totalHideouts, random.nextInt(1, target[i] + 1));
                target[i] -= decomposedAmt;
                target[i-1] += decomposedAmt * 2;
                totalHideouts += decomposedAmt;
            }
        }

        bw.write(workers + " " + hideoutCnt + "\n");

        if (random.nextInt(4) == 0) {
            target[random.nextInt(31)] += workers;
            hideoutCnt += workers;
            if (random.nextBoolean()) {
                target[random.nextInt(31)]++;
                hideoutCnt++;
            }
        }

        int[] hideouts = new int[hideoutCnt];
        int idx = 0;

        for (int i = 0; i < 31; i++)
            for (int q = 0; q < target[i]; q++)
                hideouts[idx++] = 1 << i;
        shuffleArray(hideouts, random);

        for (int i: hideouts)
            bw.write(i + "\n");

        bw.flush();
        System.out.println("finished");
    }
    System.out.println("all done");
}

static void shuffleArray(int[] arr, Random random) {
    int idx;
    int temp;
    for (int i = arr.length - 1; i > 0; i--)
    {
        idx = random.nextInt(i + 1);
        temp = arr[idx];
        arr[idx] = arr[i];
        arr[i] = temp;
    }
}
