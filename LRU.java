import java.util.Arrays;
import java.util.Scanner;
// 1 2 3 4 2 5 3 4 2 6 7 8 7 9 7 8 2 5 4 9
// 3 = 16
// 4 = 13
class LRU {
    public static int LRU_Algo(int[] Pages, int page_size) {
        int page_fault = 0;
        int used[] = new int[page_size];
        Arrays.fill(used, -1);
        for(int i=0; i<Pages.length; i++){
            if (!check_used(used, Pages[i])) {
                page_fault++;
                if(i < page_size){
                    used[page_size-1-i] = Pages[i];
                }
                else{
                    for(int j=0; j<used.length-1; j++){
                        used[j] = used[j+1];
                    }
                    used[page_size-1] = Pages[i];
                }
            }
            else{
                int pointer = -1;
                for(int j=0; j<used.length; j++){
                    if(used[j] == Pages[i]){
                        pointer = j;
                        break;
                    }
                }
                for(int j=pointer; j<used.length-1; j++){
                    used[j] = used[j+1];
                }
                used[page_size-1] = Pages[i];
            }
            display(used, Pages[i]);
        }
        return page_fault;
    }
    public static boolean check_used(int Queue[], int page) {
        for (int i=0; i<Queue.length; i++) {
            if (Queue[i] == page) {
                return true;
            }
        }
        return false;
    }
    public static void display(int output[], int page) {
        System.out.print("Output Queue at Arrival of Page " + page + " : ");
        for (int i = 0; i < output.length; i++) {
            System.out.print(output[i] + "   ");
        }
        System.out.println();
    }
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        System.out.print("\nEnter the no. of Pages : ");
        int n = sc.nextInt();
        int Pages[] = new int[n];
        System.out.println("Enter Page Numbers : ");
        for (int i = 0; i < Pages.length; i++) {
            Pages[i] = sc.nextInt();
        }
        System.out.print("Enter Page Frame Size : ");
        int page_Frame_Size = sc.nextInt();
        System.out.println();
        int page_fault = LRU_Algo(Pages, page_Frame_Size);
        System.out.println("\nPage Fault : "+page_fault);
        System.out.println();
    }
}