import java.io.IOException;
import java.util.Scanner;

static final int[] succeedTests = {0, 3, 4, 7};

public static void main(String[] args) throws IOException {
    Scanner sc = new Scanner(System.in);

    System.out.println(succeeds(sc.nextInt()) ? "Yes" : "No");
}

static boolean succeeds(int test) {
    for (int succeedTest: succeedTests)
        if (test == succeedTest)
            return true;
    return false;
}