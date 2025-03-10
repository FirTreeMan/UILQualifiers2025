import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public static void main(String[] args) throws IOException {
    BufferedWriter bw;

    for (int test = 1; test <= 10; test++) {
        System.out.println("test " + test);

        bw = new BufferedWriter(new FileWriter("accidentalpurchase/input" + test + ".txt"));

        int toppings;
        if (test <= 5)
            toppings = (int) (Math.random() * 40) + 1;
        else toppings = (int) (Math.random() * 20) + 40 + 1;

        bw.write(toppings + "\n");
        bw.flush();
        System.out.println("finished");
    }

    System.out.println("all done");
}
