import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

static final int[] primeIndices = {94573, 94583, 94597, 94603, 94613, 94621, 94649, 94651, 94687, 94693, 94709, 94723,
        94727, 94747, 66809, 66821, 66841, 66851, 66853, 66863, 66877, 66883, 66889, 66919, 66923, 66931};
static final char[] chars = "AaBbCcDdEeFfGgHhIiJjKkLlMmNnOoPpQqRrSsTtUuVvWwXxYyZz04689".toCharArray();
static final char[][] primes = {"73".toCharArray(), "647".toCharArray(), "613".toCharArray(), "471".toCharArray()};
static final int[] check2 = {80, 83};
static final int[] succeedTests = {3, 4, 7};

public static void main(String[] args) throws IOException {
    Random random = new Random();
    BufferedWriter bw;

    for (int test = 1; test <= 10; test++) {
        System.out.println("test " + test);

        bw = new BufferedWriter(new FileWriter("systembreach/input/input" + test + ".txt"));

        bw.write(test + "\n");
        boolean correct = false;
        for (int succeedTest: succeedTests)
            if (test == succeedTest)
                correct = true;

        int strLen = random.nextInt(100000, 200000) + 1;
        char[] password = new char[strLen];
        for (int i = 0; i < strLen; i++) {
            password[i] = chars[random.nextInt(chars.length)];
            if (i % 2 == 0 && random.nextInt(100) == 0)
                password[i] = '@';
        }

        System.out.println("p0");

        for (int i = 0; i < strLen; i++)
            for (int j = i + 1; j < strLen; j++)
                while ((int) Math.sqrt(password[i] * password[j]) == 81) {
                    password[i]--;
                    password[j]--;
                    j--;
                }

        System.out.println("p1");

//        for (int i = 0; i < strLen; i++)
//            for (int j = i + 1; j < strLen; j++)
//                for (int k = j + 1; k < strLen; k++)
//                    if ((int) (password[i] * password[j-1] * Math.pow(password[k], 2)) == 84540729) {
//                        password[j]--;
//                        password[k]--;
//                        j--;
//                        break;
//                    }
//
//        System.out.println("p2");

        placePrimes(password, random);

        placeAt(password, random);

        if (!correct)
            switch (random.nextInt(3)) {
                case 0 -> placePrimes(password, random);
                case 1 -> placeAt(password, random);
                case 2 -> placeCheck2(password, random);
            }

        System.out.println(correct);
        bw.write(password);
        bw.newLine();
        bw.flush();
        System.out.println("finished");
    }
    System.out.println("all done");
}

static void placePrimes(char[] pwd, Random random) {
    char[] chosenPrime = primes[random.nextInt(primes.length)];
    int idx;
    do {
        idx = random.nextInt(0, pwd.length - chosenPrime.length);
    } while (pwd[idx] == '4' || pwd[idx] == '6' || pwd[idx] == '7');

    System.arraycopy(chosenPrime, 0, pwd, idx, chosenPrime.length);
}

static void placeAt(char[] pwd, Random random) {
    int idx;
    do {
        idx = primeIndices[random.nextInt(primeIndices.length)];
    } while (pwd[idx] == '@');

    pwd[idx] = '@';
}

static void placeCheck2(char[] pwd, Random random) {
    int i1, i2;
    do {
        i1 = random.nextInt(pwd.length);
    } while (pwd[i1] == '@');
    do {
        i2 = random.nextInt(pwd.length);
    } while (i1 == i2 || pwd[i2] == '@');
    pwd[i1] = (char) check2[0];
    pwd[i2] = (char) check2[1];
}