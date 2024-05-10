import java.util.Scanner;

class SJF_P {
    public static void SJF_Algo(int Scheduling[][]) { // Process Name, Arrival Time, Burst Time, Waiting Time, Finish Time/Turn_Around Time
        int time = 0;                                 //        0           1            2             3                  4
        int execution = -1;
        boolean process = true;
        int BT[] = new int[Scheduling.length];
        for (int i = 0; i < Scheduling.length; i++) {
            BT[i] = Scheduling[i][2];
            Scheduling[i][3] = 0;
        }
        System.out.println("\nGnatt Chart :");
        while (process) {
            // Checking if any process has arrived
            int[] arrived = new int[Scheduling.length];
            array_null(arrived);
            int arriv_point = 0;
            for (int i = 0; i < Scheduling.length; i++) {
                if (Scheduling[i][1] <= time) {
                    arrived[arriv_point] = i;
                    arriv_point++;
                }
            }
            // Checking if any process arrived has min Burst Time
            int minBurstIndex = -1;
            int minBurstTime = Integer.MAX_VALUE;
            for (int i = 0; i < Scheduling.length; i++) {
                if (arrived[i] != -1 && BT[arrived[i]] != 0 && BT[arrived[i]] <= minBurstTime) {
                    minBurstIndex = arrived[i];
                    minBurstTime = BT[arrived[i]];
                }
            }
            // Executing min Burst Time process
            if (minBurstIndex != -1) {
                if (execution != -1 && BT[execution] != 0 && BT[execution] > minBurstTime) {
                    BT[minBurstIndex]--;
                    execution = minBurstIndex;
                    time++;
                } else {
                    BT[minBurstIndex]--;
                    execution = minBurstIndex;
                    time++;
                }
            } else {
                time++;
            }
            // Gnatt Chart
            System.out.print(time - 1 + " P" + execution + " ");
            // Waiting Time Calculation
            for (int i = 0; i < Scheduling.length; i++) {
                if (arrived[i] != -1 && execution != arrived[i] && BT[arrived[i]] != 0) {
                    Scheduling[arrived[i]][3] += 1;
                }
            }
            // Turn-around Time Calculation
            if (BT[execution] == 0) {
                Scheduling[execution][4] = time;
            }
            // Checking if any process is remaining to be executed
            process = false;
            for (int i = 0; i < Scheduling.length; i++) {
                if (BT[i] > 0) {
                    process = true;
                    break;
                }
            }
            if (arriv_point == 0 && minBurstIndex == -1) {
                process = false;
            }
        }
        System.out.print(time);
    }
    public static void array_null(int arr[]) {
        for (int i = 0; i < arr.length; i++) {
            arr[i] = -1;
        }
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
            array_null(Scheduling[i]);
            Scheduling[i][0] = i;
            System.out.print("\nEnter Arrival Time of Process " + i + " : ");
            Scheduling[i][1] = sc.nextInt();
            System.out.print("Enter Burst Time of Process " + i + " : ");
            Scheduling[i][2] = sc.nextInt();
        }
        SJF_Algo(Scheduling);
        display(Scheduling);
    }
}