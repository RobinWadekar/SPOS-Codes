import java.util.*;
// 1 2 3 4 2 5 3 4 2 6 7 8 7 9 7 8 2 5 4 9
// 3 = 13
// 4 = 11
class OPR {
    public static int OPR_Algo(int[] Pages, int page_size) {
        int page_fault = 0;
        int []used[] = new int[page_size][2];
        for (int i = 0; i < used.length; i++)
            { Arrays.fill(used[i], -1); }
        for (int i = 0; i < Pages.length; i++) {
            if (!check_used(used, Pages[i])) {
                page_fault++;
                if (i < page_size) {
                    used[i][0] = Pages[i];
                    used[i][1] = 0;
                } else {
                    int max = Integer.MIN_VALUE;
                    int max_index = -1;
                    for (int j = 0; j < used.length; j++) {
                        used[j][1] = calculate_distance(Pages, used[j][0], i);
                        if(used[j][1] > max)
                            { max = used[j][1]; max_index = j; }
                    }
                    used[max_index][0] = Pages[i];
                    used[max_index][1] = 0;
                }
            }
            display(used, Pages[i]);
        }
        return page_fault;
    }
    public static void display(int []output[], int page) {
        System.out.print("Output Queue at Arrival of Page " + page + " : ");
        for (int i = 0; i < output.length; i++) {
            System.out.print(output[i][0] + "   ");
        }
        System.out.println();
    }
    public static boolean check_used(int []Queue[], int page) {
        for (int i=0; i<Queue.length; i++) {
            if (Queue[i][0] == page) {
                return true;
            }
        }
        return false;
    }
    public static int calculate_distance(int Pages[], int page, int index) {
        for (int i=index; i<Pages.length; i++) {
            if(Pages[i] == page)
            { return (i-index); }
        }
        return Integer.MAX_VALUE;
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("\nEnter the no. of Pages : ");
        int n = sc.nextInt();
        int Pages[] = new int[n];
        System.out.println("Enter Page Numbers : ");
        for (int i = 0; i < Pages.length; i++)
            { Pages[i] = sc.nextInt(); }
        System.out.print("Enter Page Frame Size : ");
        int page_Frame_Size = sc.nextInt();
        System.out.println();
        int page_fault = OPR_Algo(Pages, page_Frame_Size);
        System.out.println("\nPage Fault : "+page_fault);
        System.out.println();
    }
}