import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;

static final char[] chars = "AaBbCcDdEeFfGgHhIiJjKkLlMmNnOoPpQqRrSsTtUuVvWwXxYyZz0123456789".toCharArray();
static final char[] numbers = "1234567890".toCharArray();
static final HashSet<String> seenStrings = new HashSet<>();
static final HashSet<String> seenNumbers = new HashSet<>();

public static void main(String[] args) throws IOException {
    BufferedWriter bw;

    for (int test = 1; test <= 10; test++) {
        System.out.println("test " + test);

        bw = new BufferedWriter(new FileWriter("pizza/" + test + ".in"));
        int numbers = (int) (Math.random() * 991) + 10;
        int realPos = (int) (Math.random() * numbers);

        bw.write(String.valueOf(numbers));
        bw.newLine();
        System.out.println(numbers + " numbers");

        while (numbers-- > 0) {
            if (numbers == realPos)
                bw.write("PizzaCo");
            else bw.write(makeRandomString());

            bw.write(' ');

            bw.write(makeRandomNumber());
            bw.newLine();
        }

        bw.flush();
        System.out.println("finished\n");
    }

    System.out.println("all done");
}

static String makeRandomString() {
    char[] stringChars;
    String out;
    do {
        int stringSize = (int) (Math.random() * 20) + 1;
        stringChars = new char[stringSize];
        for (int i = 0; i < stringSize; i++)
            stringChars[i] = chars[(int) (Math.random() * chars.length)];

        out = String.copyValueOf(stringChars);
    } while (seenStrings.contains(out));

    seenStrings.add(out);
    return out;
}

static String makeRandomNumber() {
    StringBuilder out;
    do {
        out = new StringBuilder();
        for (int i = 0; i < 3; i++)
            out.append(numbers[(int) (Math.random() * numbers.length)]);
        out.append('-');
        for (int i = 0; i < 3; i++)
            out.append(numbers[(int) (Math.random() * numbers.length)]);
    } while (seenNumbers.contains(out.toString()));

    seenNumbers.add(out.toString());
    return out.toString();
}
