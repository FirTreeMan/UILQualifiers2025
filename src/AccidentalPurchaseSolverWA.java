import java.util.Scanner;

public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);

    int n = sc.nextInt();
    long permutations = 1L << n;

    System.out.println(permutations * 1000000);
}