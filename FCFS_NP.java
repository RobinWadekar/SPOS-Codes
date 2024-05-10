import java.util.*;
class FCFS_NP {
public static void FCFS_Algo(int Scheduling[][]) { // Process Name, Arrival Time, Burst Time, Waiting Time, Finish Time/Turn_Around Time
        for (int j = 0; j < Scheduling.length; j++) { //     0           1            2             3                  4
            int min_time = Scheduling[j][1];
            int min_time_index = j;
            for (int i = j; i < Scheduling.length; i++) {
                if (Scheduling[i][1] < min_time)
                { min_time = Scheduling[i][1]; min_time_index = i; }
            }
            sort_array(Scheduling, min_time_index, j);
        }
        Scheduling[0][3] = 0;
        Scheduling[0][4] = Scheduling[0][2];
        for (int i = 1; i < Scheduling.length; i++) {
            Scheduling[i][4] = Scheduling[i-1][4] + Scheduling[i][2];
            Scheduling[i][3] = Scheduling[i-1][4] - Scheduling[i][1];
        }
    }
    public static void sort_array(int[][] scheduling, int i, int j) {
        int temp[] = scheduling[j];
        scheduling[j] = scheduling[i];
        scheduling[i] = temp;
    }
    public static void display(int Scheduling[][]) {
        System.out.print("\nGantt Chart :\n0");
        for (int i = 0; i < Scheduling.length; i++) {
            System.out.print(" P" + Scheduling[i][0] + " " + Scheduling[i][4]);
        }
        System.out.println("\n\nProcess Name\t\tArrival Time\t\tBurst Time\t\tWaiting Time\t\tTurn_Around Time");
        for (int i = 0; i < Scheduling.length; i++) {
            System.out.println(Scheduling[i][0] + "\t\t\t" + Scheduling[i][1] + "\t\t\t" + Scheduling[i][2] + "\t\t\t" + Scheduling[i][3] + "\t\t\t" + Scheduling[i][4]);
        }
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter No. of Processes : ");
        int n = sc.nextInt();
        int[] Scheduling[] = new int[n][5]; // Process Name, Arrival Time, Burst Time, Waiting Time, Finish Time/Turn_Around Time
        for (int i = 0; i < n; i++) {
            Scheduling[i][0] = i;
            System.out.print("\nEnter Arrival Time of Process " + i + " : ");
            Scheduling[i][1] = sc.nextInt();
            System.out.print("Enter Burst Time of Process " + i + " : ");
            Scheduling[i][2] = sc.nextInt();
        }
        FCFS_Algo(Scheduling);
        display(Scheduling);
    }
}