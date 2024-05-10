import java.util.Arrays;
import java.util.Scanner;
public class WorstFit {
    public static void worstFit(int[] blockSize, int m, int[] processSize, int n) {
        int[] allocation = new int[n];
        Arrays.fill(allocation, -1);
        for (int i = 0; i < n; i++) {
            int wstIdx = -1;
            for (int j = 0; j < m; j++) {
                if (blockSize[j] >= processSize[i]) {
                    if (wstIdx == -1 || blockSize[wstIdx] < blockSize[j]) {
                        wstIdx = j;
                    }
                }
            }
            if (wstIdx != -1) {
                allocation[i] = wstIdx;
                blockSize[wstIdx] -= processSize[i];
            }
        }
        System.out.println("\nProcess No.\tProcess Size\tBlock no.");
        for (int i = 0; i < n; i++) {
            System.out.print(" " + (i + 1) + "\t\t" + processSize[i] + "\t\t");
            if (allocation[i] != -1)
                System.out.print(allocation[i] + 1);
            else
                System.out.print("Not Allocated");
            System.out.println();
        }
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the number of blocks: ");
        int m = sc.nextInt();
        int[] blockSize = new int[m];
        System.out.println("Enter the block sizes:");
        for (int i = 0; i < m; i++) {
            blockSize[i] = sc.nextInt();
        }
        System.out.print("Enter the number of processes: ");
        int n = sc.nextInt();
        int[] processSize = new int[n];
        System.out.println("Enter the process sizes:");
        for (int i = 0; i < n; i++) {
            processSize[i] = sc.nextInt();
        }
        worstFit(blockSize, m, processSize, n);
        sc.close();
    }
}