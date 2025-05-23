import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;

static char[][] pizza;
static int rows;
static int cols;
static int searchRows;
static int searchCols;
static int toppingTotal;
static HashMap<Character, Integer> frequencyMap;
static int sliceUnevenness;
static int sliceToppings;

public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);

    rows = sc.nextInt();
    cols = sc.nextInt();
    searchRows = sc.nextInt();
    searchCols = sc.nextInt();
    sc.nextLine();

    pizza = new char[rows][cols];
    for (int i = 0; i < rows; i++)
        pizza[i] = sc.nextLine().toCharArray();

    HashSet<Character> seenToppings = new HashSet<>();
    for (int r = 0; r < rows; r++)
        for (int c = 0; c < cols; c++)
            if (pizza[r][c] != ' ')
                seenToppings.add(pizza[r][c]);

    toppingTotal = seenToppings.size();
    frequencyMap = new HashMap<>();

    int bestR = rows - searchRows - 1, bestC = cols - searchCols - 1;
    int bestUnevenness = Integer.MAX_VALUE;
    int bestToppings = 0;
    for (int r = rows - searchRows; r >= 0; r--)
        for (int c = cols - searchCols; c >= 0; c--) {
            calcSlice(r, c);
            if (sliceUnevenness < bestUnevenness ||
                    sliceUnevenness == bestUnevenness && sliceToppings > bestToppings) {
                bestR = r;
                bestC = c;
                bestUnevenness = sliceUnevenness;
                bestToppings = sliceToppings;
            }
        }

    System.out.println(bestC + " " + bestR + "\n");
}

static void calcSlice(int startRow, int startCol) {
    frequencyMap.clear();
    sliceToppings = 0;

    int min = Integer.MAX_VALUE, max = 0;
    for (int r = startRow; r < startRow + searchRows; r++)
        for (int c = startCol; c < startCol + searchCols; c++) {
            if (pizza[r][c] == ' ') continue;

            frequencyMap.merge(pizza[r][c], 1, Integer::sum);
            sliceToppings++;
        }

    if (frequencyMap.size() < toppingTotal)
        min = 0;

    for (int frequency: frequencyMap.values()) {
        min = Math.min(min, frequency);
        max = Math.max(max, frequency);
    }

    sliceUnevenness = max - min;
}
