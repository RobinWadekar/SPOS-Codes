import java.util.Arrays;
import java.util.Scanner;
class NextFit {
    public static void nextFit(int[] blockSize, int m, int[] processSize, int n) {
        int[] allocation = new int[n];
        Arrays.fill(allocation, -1);
        int nxtIdx = 0;
        for (int i = 0; i < n; i++) {
            int count = 0, j = nxtIdx;
            while (j < m) {
                if (blockSize[j] >= processSize[i]) {
                    allocation[i] = j;
                    blockSize[j] -= processSize[i];
                    nxtIdx = j;
                    break;
                }
                count++; j = ++j % m;
                if (count == m) { allocation[i] = -1; break; }
            }
        }
        System.out.println("\nProcess No.\t\tProcess Size\t\t\tBlock no.");
        for (int i = 0; i < n; i++) {
            System.out.print(" " + (i + 1) + "\t\t\t\t" + processSize[i] + "\t\t\t");
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
        System.out.print("Enter the block sizes:");
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
        nextFit(blockSize, m, processSize, n);
        sc.close();
    }
}