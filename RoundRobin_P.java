import java.util.*;
public class RoundRobin_P {
    public static void RR_Algo(int Scheduling[][], final int time_slice) {
        for (int j = 0; j < Scheduling.length; j++) {
            int min_time = Scheduling[j][1];
            int min_time_index = j;
            Scheduling[j][3] = Scheduling[j][4] = 0;
            for (int i = j; i < Scheduling.length; i++) {
                if (Scheduling[i][1] < min_time)
                    { min_time = Scheduling[i][1]; min_time_index = i; }
            }
            sort_array(Scheduling, min_time_index, j);
        }
        int time = 0;
        boolean process = true;
        int BT[] = new int[Scheduling.length];
        Arrays.fill(BT, -1);
        for(int i=0; i<BT.length; i++) { BT[i] = Scheduling[i][2]; }
        System.out.print("\nGnatt Chart :\n0 ");
        while(process){
            for (int i = 0; i < Scheduling.length; i++) {
                if(BT[i] != 0){
                    if(BT[i] >= time_slice){
                        time += time_slice;
                        BT[i] -= time_slice;
                    }
                    else{
                        time += BT[i];
                        BT[i] = 0;
                    }
                    if(BT[i] == 0) {
                        Scheduling[i][4] = time;
                        Scheduling[i][3] = time - Scheduling[i][1] - Scheduling[i][2];
                        System.out.print(" P" + Scheduling[i][0] + " " + time + " ");
                    }
                }

            }
            process = false;
            for (int i = 0; i < BT.length; i++) {
                if(BT[i] > 0) { process = true; break; }
            }
        }
    }
    public static void sort_array(int[][] scheduling, int i, int j) {
        int temp[] = scheduling[j];
        scheduling[j] = scheduling[i];
        scheduling[i] = temp;
    }
    public static void display(int Scheduling[][]) {
        System.out.println("\n\nProcess Name\tArrival Time\tBurst Time\tWaiting Time\tTurn Around Time");
        for (int i = 0; i < Scheduling.length; i++) {
            for (int j = 0; j < Scheduling[i].length; j++) {
                System.out.print(Scheduling[i][j] + "\t\t");
            }
            System.out.println();
        }
        System.out.println();
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter No. of Processes : ");
        int n = sc.nextInt();
        int[] Scheduling[] = new int[n][5]; // Process Name, Arrival Time, Burst Time, Waiting Time, Finish Time/Turn_Around Time
        for (int i = 0; i < n; i++) {
            Arrays.fill(Scheduling[i], -1);
            Scheduling[i][0] = i;
            System.out.print("\nEnter Arrival Time of Process " + i + " : ");
            Scheduling[i][1] = sc.nextInt();
            System.out.print("Enter Burst Time of Process " + i + " : ");
            Scheduling[i][2] = sc.nextInt();
        }
        System.out.print("\nEnter Time-Slice : ");
        int time_slice = sc.nextInt();
        RR_Algo(Scheduling, time_slice);
        display(Scheduling);
    }
}