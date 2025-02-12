import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public static void main(String[] args) throws IOException {
    Scanner sc;
    BufferedWriter bw;

    for (int test = 1; test <= 10; test++) {
        sc = new Scanner(new File("pizzaglyphs/" + test + ".in"));
        bw = new BufferedWriter(new FileWriter("pizzaglyphs/" + test + ".out"));

        int n = sc.nextInt();
        int m = sc.nextInt();
        String glyph = sc.next();
        StringBuilder translated = new StringBuilder(glyph);
        Word[] words = new Word[n];
        Phrase[] phrases = new Phrase[m];

        for (int i = 0; i < n; i++)
            words[i] = new Word(sc.next(), sc.next());
        for (int i = 0; i < m; i++)
            phrases[i] = new Phrase(sc.next(), sc.next(), sc.nextInt(), sc.next());

        Arrays.sort(words);
        Arrays.sort(phrases);

        String[] replacements = new String[glyph.length()];

        for (Phrase phrase: phrases) {
            for (int i = 0; i < glyph.length() - (phrase.find.length() + phrase.distance + phrase.find2.length()); i++) {
                if (!phrase.find.equals(glyph.substring(i, i + phrase.find.length()))) continue;
                int find2Idx = i + phrase.find.length() + phrase.distance;
                if (!phrase.find2.equals(glyph.substring(find2Idx, find2Idx + phrase.find2.length()))) continue;


            }
        }
    }
}

static class Word implements Comparable<Word> {
    public final String find;
    public final String replace;

    Word(String find, String replace) {
        this.find = find;
        this.replace = replace;
    }

    @Override
    public int compareTo(Word o) {
        if (!find.equals(o.find)) return find.compareTo(o.find);
        return replace.compareTo(o.replace);
    }
}

static class Phrase implements Comparable<Phrase> {
    public final String find;
    public final String find2;
    public final int distance;
    public final String replace;

    Phrase(String find, String find2, int distance, String replace) {
        this.find = find;
        this.find2 = find2;
        this.distance = distance;
        this.replace = replace;
    }

    @Override
    public int compareTo(Phrase o) {
        if (!find.equals(o.find)) return find.compareTo(o.find);
        if (!find2.equals(o.find2)) return find2.compareTo(o.find2);
        if (distance != o.distance) return distance - o.distance;
        return replace.compareTo(o.replace);
    }
}