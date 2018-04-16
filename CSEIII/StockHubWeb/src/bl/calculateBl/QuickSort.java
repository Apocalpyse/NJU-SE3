package bl.calculateBl;

/**
 * Created by A on 2017/5/21.
 * 这个类实现对数据排序的功能，当输入一组数据时，返回这组数据排序从小到大排序后的位置的结果
 * 如将数据{1.1, 6.38, 7.92, 1.00, 669.32, 3.68, 78.5, -96.568, 2.56}排序后，
 * 得到｛7， 3， 0， 8， 5， 1， 2， 6， 4｝
 */
public class QuickSort {
    public int[] getSort(double n[]) {
//        double n[]={1.1, 6.38, 7.92, 1.00, 669.32, 3.68, 78.5, -96.568, 2.56};
        int location[]=new int[n.length];
        for(int i=0;i<n.length; i++){
            location[i]=i;
        }
        quicksort(n, 0, n.length-1, location);
        return location;
    }

    public static void quicksort(double n[], int left, int right, int location[]) {
        int dp;
        if (left < right) {
            dp = partition(n, left, right, location);
            quicksort(n, left, dp - 1, location);
            quicksort(n, dp + 1, right, location);
        }
    }

    public static int partition(double n[], int left, int right, int location[]) {
        double pivot = n[left];
        int placa = location[left];
        while (left < right) {
            while ( (left < right) && (n[right] >= pivot))
                right--;
            if (left < right){
                n[left++] = n[right];
                location[left-1]=location[right];
            }

            while ( (left < right) && (n[left] <= pivot) )
                left++;
            if (left < right){
                n[right--] = n[left];
                location[right+1]=location[left];
            }
        }
        n[left] = pivot;
        location[left]=placa;
        return left;
    }

    /*
    public static void main(String [] args){
        QuickSort quickSort=new QuickSort();
        double n[]={1.1, 6.38, 7.92, 1.00, 669.32, 3.68, 78.5, -96.568, 2.56};
        int location1[]=quickSort.getSort(n);
        for (int i = 0; i < location1.length; i++) {
            System.out.print(location1[i]+" : ");
            int loc=location1[i];
            System.out.println(n[loc]);
        }
    }
    */
}
