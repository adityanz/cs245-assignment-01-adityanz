public class MergeSort implements SortingAlgorithm {
    @Override
    public void sort(int [] arr)
    {
        mergesort(arr,0,arr.length-1);
    }
    public void mergesort(int [] arr, int low, int high)
    {
         if (low>=high) {
             return;
         }
         int mid = (low+high)/2;
         int n = arr.length;

         int[] arra = new int[(n + 1)/2];
         int[] arrb = new int[n - arra.length];

        for (int i = 0; i < n; i++)
        {
            if (i < arra.length)
                arra[i] = arr[i];
            else
                arrb[i - arra.length] = arr[i];
        }
         mergesort(arra,low,mid);
         mergesort(arrb,mid+1,high);
         merge(arra,arrb,arr);
    }
    public void merge(int [] left_arr, int [] right_arr, int[] target)
    {
        int left=0;
        int right=0;
        int target_index = 0;

        while (left<left_arr.length && right < right_arr.length)
        {
            if (left_arr[left] < right_arr[right])
            {
                target[target_index] =left_arr[left];
                ++left;
            }
            else
            {
                target[target_index] = right_arr[right];
                ++right;
            }
            ++target_index;
        }
        while (left <left_arr.length)
        {
            target[target_index] = left_arr[left];
            target_index++;
            left++;
        }
        while (right <right_arr.length)
        {
            target[target_index] = right_arr[right];
            target_index++;
            right++;
        }
    }


}
