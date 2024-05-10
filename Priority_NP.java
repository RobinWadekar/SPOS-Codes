import java.util.*;
class Priority_NP {
public static void Priority_Algo(int Scheduling[][]) { // Process Name, Arrival Time, Burst Time, Priority, Waiting Time, Finish Time/Turn_Around Time
        for (int j = 0; j < Scheduling.length; j++) {  //     0       ,   1         ,     2     ,    3    ,       4     ,            5
            int min_time = Scheduling[j][1];
            int min_time_index = j;
            for (int i = j; i < Scheduling.length; i++) {
                if (Scheduling[i][1] < min_time)
                    { min_time = Scheduling[i][1]; min_time_index = i; }
            }
            sort_array(Scheduling, min_time_index, j);
        }
        for (int j = 0; j < Scheduling.length; j++) {
            int max_prio = Scheduling[j][3];
            int max_prio_index = j;
            for (int i = j; i < Scheduling.length; i++) {
                if (Scheduling[i][1] == Scheduling[j][1] && Scheduling[i][3] < max_prio)
                    { max_prio = Scheduling[i][3]; max_prio_index = i; }
            }
            sort_array(Scheduling, max_prio_index, j);
        }
        int time = 0;
        boolean process = true;
        int BT[] = new int[Scheduling.length];
        Arrays.fill(BT, -1);
        int Prio[] = new int[Scheduling.length];
        Arrays.fill(Prio, -1);
        for (int i = 0; i < Scheduling.length; i++) {
            Scheduling[i][4] = Scheduling[i][5] = 0;
            BT[i] = Scheduling[i][2]; Prio[i] = Scheduling[i][3];
        }
        int execution = -1;
        int min_prio_index = 0;
        System.out.print("\nGantt Chart :\n0");
        while(process)
        {
            if(execution == -1)
                { execution = 0; }
            else
                { execution = min_prio_index; }
            time += BT[execution];
            BT[execution] = 0;
            // min_prio_index = Prio[execution];
            Prio[execution] = Integer.MAX_VALUE;
            for (int i = 0; i < Prio.length; i++) {
                if(Scheduling[i][1] <= time && BT[i] != 0 && Prio[i] != Integer.MAX_VALUE && Prio[i] < Prio[min_prio_index])
                    { min_prio_index = i; }
            }
            System.out.print(" P"+Scheduling[execution][0]+" "+time);
            Scheduling[execution][5] = time;
            Scheduling[execution][4] = Scheduling[execution][5] - Scheduling[execution][2];
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
        System.out.println("\n\nProcess Name\tArrival Time\tBurst Time\tPriority\tWaiting Time\tTurn Around Time");
        for (int i = 0; i < Scheduling.length; i++) {
            for (int j = 0; j < Scheduling[i].length; j++)
                { System.out.print(Scheduling[i][j] + "\t\t"); }
            System.out.println();
        }
        System.out.println();
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter No. of Processes : ");
        int n = sc.nextInt();
        int[] Scheduling[] = new int[n][6]; // Process Name, Arrival Time, Burst Time, Priority, Waiting Time, Finish Time/Turn_Around Time
        for (int i = 0; i < n; i++) {       //        0           1            2          3           4                   5
            Arrays.fill(Scheduling[i], -1);
            Scheduling[i][0] = i;
            System.out.print("\nEnter Arrival Time of Process " + i + " : ");
            Scheduling[i][1] = sc.nextInt();
            System.out.print("Enter Burst Time of Process " + i + " : ");
            Scheduling[i][2] = sc.nextInt();
            System.out.print("Enter Priority of the Process " + i + " : ");
            Scheduling[i][3] = sc.nextInt();
        }
        Priority_Algo(Scheduling);
        display(Scheduling);
    }
    
}