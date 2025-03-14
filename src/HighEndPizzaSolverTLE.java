import java.util.Scanner;

public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);

    int n = sc.nextInt();
    int m = sc.nextInt();
    int[] days = new int[n+1];

    for (int i = 1; i <= n; i++)
        days[i] = sc.nextInt();

    int optimalDay = 0;
    int optimalSold = 0;

    for (int i = 1; i <= n - m; i++) {
        int sold = 0;
        for (int j = 0; j < m; j++)
            if (j != m / 2)
                sold += days[i+j];
        if (sold > optimalSold) {
            optimalSold = sold;
            optimalDay = i;
        }
    }

    System.out.println(optimalDay);
}