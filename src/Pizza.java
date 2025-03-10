import java.io.*;
import java.util.Scanner;

public static void main(String[] args) throws IOException {
    Scanner sc;
    BufferedWriter bw;

    for (int test = 1; test <= 10; test++) {
        sc = new Scanner(new File("pizza/input" + test + ".txt"));
        bw = new BufferedWriter(new FileWriter("pizza/output" + test + ".txt"));

        int n = sc.nextInt();
        boolean found = false;

        while (n-->0) {
            String person = sc.next();
            String number = sc.next();

            if (person.equals("PizzaCo")) {
                found = true;
                bw.write(number);
                bw.newLine();
                bw.flush();
                break;
            }
        }

        assert found;
    }
}
