import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

static final char[] chars = "AaBbCcDdEeFfGgHhIiJjKkLlMmNnOoPpQqRrSsTtUuVvWwXxYyZz0123456789".toCharArray();

public static void main(String[] args) throws IOException {
    Random random = new Random();
    BufferedWriter bw;

    for (int test = 1; test <= 10; test++) {
        System.out.println("test " + test);

        bw = new BufferedWriter(new FileWriter("pizzaglyphs/" + test + ".in"));

        int wordTranslate = random.nextInt(100, 1000);
        int phraseTranslate = random.nextInt(100, 1000);

        bw.write(wordTranslate + " " + phraseTranslate + "\n");

        char[] toTranslate = new char[100];
        for (int i = 0; i < 100; i++)
            toTranslate[i] = chars[random.nextInt(chars.length)];
        String glyphs = String.valueOf(toTranslate);
        bw.write(glyphs);
        bw.newLine();

        for (int i = 0; i < wordTranslate; i++) {
            boolean accurate = random.nextBoolean();
            if (accurate) {
                int startIdx = random.nextInt(toTranslate.length - 1);
                int endIdx = random.nextInt(startIdx, toTranslate.length) + 1;
                bw.write(glyphs.substring(startIdx, endIdx));
            } else {
                int randLength = random.nextInt(1, toTranslate.length);
                for (int x = 0; x < randLength; x++)
                    bw.write(chars[random.nextInt(chars.length)]);
            }

            bw.write(" ");
            int randLength = random.nextInt(1, toTranslate.length);
            for (int x = 0; x < randLength; x++)
                bw.write(chars[random.nextInt(chars.length)]);
            bw.newLine();
        }

        for (int i = 0; i < phraseTranslate; i++) {
            boolean accurate = random.nextBoolean();
            if (accurate) {
                int startIdx = random.nextInt(toTranslate.length - 2);
                int endIdx = random.nextInt(startIdx, toTranslate.length - 2) + 1;
                int startIdx2 = random.nextInt(endIdx, toTranslate.length - 1) + 1;
                int endIdx2 = random.nextInt(startIdx2, toTranslate.length) + 1;
                bw.write(glyphs.substring(startIdx, endIdx) + " " + glyphs.substring(startIdx2, endIdx2) + " " + (startIdx2 - endIdx));
            } else {
                int randLength = random.nextInt(1, toTranslate.length);
                for (int x = 0; x < randLength; x++)
                    bw.write(chars[random.nextInt(chars.length)]);
                bw.write(" ");
                randLength = random.nextInt(1, toTranslate.length);
                for (int x = 0; x < randLength; x++)
                    bw.write(chars[random.nextInt(chars.length)]);
                bw.write(" ");
                bw.write(String.valueOf(random.nextInt(5000)));
            }

            bw.write(" ");
            int randLength = random.nextInt(1, toTranslate.length);
            for (int x = 0; x < randLength; x++)
                bw.write(chars[random.nextInt(chars.length)]);
            bw.newLine();
        }

        bw.flush();
        System.out.println("finished");
    }
    System.out.println("all done");
}