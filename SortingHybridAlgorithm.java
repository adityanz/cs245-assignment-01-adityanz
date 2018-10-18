import java.util.*;
import java.awt.*;
import java.lang.*;

public class SortingHybridAlgorithm implements SortingAlgorithm {
    @Override
    public void sort(int [] arr)
    {
        cool(arr);
    }

    public static void bubblesort (int lb, int ub, int arr[])
    {
        boolean swaps = true;
        int k = 0;
        while (swaps){
            swaps = false;
            k++;
            for (int j = lb; j < ub; j++) {
                if (arr[j] > arr[j + 1]) {
                    swap(j, j + 1, arr);
                    swaps = true;
                }
            }
        }
    }

    public static void swap (int a, int b, int arr[]) {
        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }
    static int therunsize = 5;
    static ArrayList<Point> runslist = new ArrayList<Point>();
    static ArrayList<Point> runslist2 = new ArrayList<Point>();
    static ArrayList<Point> notrunslist = new ArrayList<Point>();
    static ArrayList<Point> notrunslist2 = new ArrayList<Point>();
    static ArrayList<Point> superlist = new ArrayList<Point>();
    static int runs;


    public static void cool(int [] arr) {
        findruns(arr);
        badrunfinder(arr);
        sortbadruns(arr);
        superlist.addAll(notrunslist);
        superlist.addAll(runslist);
        superlist.sort(Comparator.comparing(Point::getX));
        merge(arr);
    }

    public static void findruns(int arr[]) {
        int endpoint;
        Point p = new Point(0, 0);
        for (int i = 0; i < arr.length - 1; i += endpoint) {
            endpoint = recursive(i, arr) + 1;
            if (endpoint >= therunsize) {
                p = new Point(i, (i + endpoint - 1));
                runslist.add(p);
            }
        }
    }

    private static void badrunfinder(int arr[]) {
        Point pointone, pointtwo;
        int unsortedx = 0;
        int unsortedy = 0;

        for (int i = 0; i < runslist.size() - 1; i++) {
            pointone = runslist.get(i);
            pointtwo = runslist.get(i + 1);

            if ((int) pointone.getX() - 1 >= 0) {
                unsortedx = (int) pointone.getY() + 1;
                unsortedy = (int) pointtwo.getX() - 1;
                if (unsortedx <= unsortedy)
                    notrunslist.add(new Point(unsortedx, unsortedy));
            }
        }

        pointone = runslist.get(0);
        pointtwo = runslist.get(1);
        if ((int) pointone.getY() - 1 == -1) {
            unsortedx = (int) pointone.getY() + 1;
            unsortedy = (int) pointtwo.getX() - 1;
            notrunslist.add(0, new Point(unsortedx, unsortedy));
        } else if ((int) pointone.getX() - 1 >= 0) {
            unsortedy = (int) pointone.getX() - 1;
            notrunslist.add(0, new Point(0, unsortedy));
        }

        pointtwo = runslist.get(runslist.size() - 1);
        if ((int) pointtwo.getY() + 1 != arr.length) {
            unsortedx = (int) pointtwo.getY() + 1;
            unsortedy = arr.length - 1;
            notrunslist.add(new Point(unsortedx, unsortedy));
        }
    }

    public static void sortbadruns(int arr[]) {
        for (int i = 0; i < notrunslist.size(); i++)
        {
            Point point = notrunslist.get(i);
            bubblesort((int) point.getX(), (int) point.getY(), arr);
        }
    }
    private static void merge(int arr[]) {
        while(superlist.size() > 1){
            for (int i = 0; i < superlist.size()-1;i++){
                mergeArr(superlist.get(i),superlist.get(i+1), arr);
                superlist.get(i).setLocation((int)superlist.get(i).getX(),(int)superlist.get(i+1).getY());
                superlist.remove(superlist.get(i+1));
            }
        }
    }

    private static void mergeArr(Point a1, Point a2, int arr[]) {
        int sL = (int) a1.getX();
        int eL = (int) a1.getY();
        int sR = (int) a2.getX();
        int eR = (int) a2.getY();
        int size = eR - sL + 1;

        int l = 0;
        int r = 0;
        int target_index = sL;

        int temp[] = new int [size];
        int lSize = eL-sL+1;
        int rSize = eR-sR+1;
        int L[] = new int[lSize];
        int R[] = new int[rSize];
        for (int i = 0;i < lSize;i++){
            L[i] = arr[sL+i];
        }
        for (int i = 0;i < rSize; i++){
            R[i] = arr[sR+i];
        }

        while(l < lSize && r < rSize){
            if (L[l] < R[r]){
                arr[target_index] = L[l];
                l++;
            }
            else{
                arr[target_index] = R[r];
                r++;
            }
            target_index++;
        }
        while(l < lSize){
            arr[target_index] = L[l];
            target_index++;
            l++;
        }
        while(r < rSize){
            arr[target_index] = R[r];
            target_index++;
            r++;
        }
    }

    public static int recursive(int i, int arr[])
    {
        if ((i+1) == arr.length || arr [i+1] < arr[i])
        {
            return 0;
        }
        return recursive(i + 1, arr) + 1;
    }
}