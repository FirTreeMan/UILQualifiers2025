import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public static void main(String[] args) throws IOException {
    Random random = new Random();
    BufferedWriter bw;

    for (int test = 1; test <= 10; test++) {
        System.out.println("test " + test);

        bw = new BufferedWriter(new FileWriter("evilpizza/input" + test + ".txt"));

        int staff;
        int kitchenStaff;
        int tiers;
        if (test <= 4) {
            staff = random.nextInt(80, 100);
            kitchenStaff = random.nextInt(10, 12);
            tiers = random.nextInt(5, 10);
        } else {
            staff = random.nextInt(180, 200);
            kitchenStaff = random.nextInt(40, 50);
            tiers = random.nextInt(10, 15);
        }

        int[] staffInTier = new int[tiers];
        staffInTier[0] = kitchenStaff;
        int staffToAssign = staff - kitchenStaff;
        int avgDistributed = (staff - kitchenStaff) / (tiers - 1);
        for (int i = 1; i < tiers - 1; i++) {
            staffInTier[i] = Math.min(staffToAssign, random.nextInt(avgDistributed, avgDistributed * 3) / 2);
            staffToAssign -= staffInTier[i];
        }
        staffInTier[tiers-1] = staffToAssign;



        bw.flush();
        System.out.println("finished");
    }
    System.out.println("all done");
}