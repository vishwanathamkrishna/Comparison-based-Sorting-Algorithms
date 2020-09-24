import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;
import java.util.stream.IntStream;

public class SortedNumbersSorting {
	private static final Random random = new Random();

	public static void main(String[] args) {
	
			// Using Scanner for Getting Input from User 
	        Scanner in = new Scanner(System.in); 
	        System.out.println("Enter the number of elements you want to sort:");
	        int n = Integer.parseInt(in.nextLine());
	        in.close();
	        
	        int sortedArray[] = IntStream.range(1, n+1).toArray();
			int sortedArray1[] = Arrays.copyOf(sortedArray, sortedArray.length);
			int sortedArray2[] = Arrays.copyOf(sortedArray, sortedArray.length);
			int sortedArray3[] = Arrays.copyOf(sortedArray, sortedArray.length);
			int sortedArray4[] = Arrays.copyOf(sortedArray, sortedArray.length);
			
			
	        //INSERTION SORT
	        System.out.println("<<<Already Sorted Array>>>: "+Arrays.toString(sortedArray));
	        long insertionstarttime = System.nanoTime();
	        InsertionSort(sortedArray);
	        long insertionendtime = System.nanoTime();
	        System.out.println("Insertion Sort Execution time in nanoseconds:"+(insertionendtime-insertionstarttime));
	        System.out.println("<<<Sorted Array>>>: "+Arrays.toString(sortedArray));
	        
	        System.out.println("\n");
	        
	        //MODIFIED QUICK SORT
	        System.out.println("<<<Already Sorted Array>>>: "+Arrays.toString(sortedArray1));
	        long mquickstarttime = System.nanoTime();
	        medianQuickSort(sortedArray1, 0, sortedArray1.length-1);
	        long mquickendtime = System.nanoTime();
	        System.out.println("Modified Quick Sort Execution time in nanoseconds:"+(mquickendtime-mquickstarttime));
	        System.out.println("<<<Sorted Array>>>: "+Arrays.toString(sortedArray1));
	        
	        System.out.println("\n");
	        
	        //INPLACE QUICK SORT
	        System.out.println("<<<Already Sorted Array>>>*: "+Arrays.toString(sortedArray2));
	        long iquickstarttime = System.nanoTime();
	        inPlaceQuickSort(sortedArray2, 0, sortedArray2.length-1);
	        long iquickendtime = System.nanoTime();
	        System.out.println("Inplace Quick Sort Execution time in nanoseconds:"+(iquickendtime-iquickstarttime));
	        System.out.println("<<<Sorted Array>>>: "+Arrays.toString(sortedArray2));
	        
	        System.out.println("\n");
	        
	        //MERGE SORT
	        System.out.println("<<<Already Sorted Array>>>: "+Arrays.toString(sortedArray3));
	        long mergestarttime = System.nanoTime();     
	        mergesort(sortedArray3, 0, sortedArray3.length-1); 
	        long mergeendtime = System.nanoTime();
	        System.out.println("Merge Sort Execution time in nanoseconds:"+(mergeendtime-mergestarttime));
	        System.out.println("<<<Sorted Array>>>: "+Arrays.toString(sortedArray3));
	        
	        System.out.println("\n");
	        
	        //HEAP SORT
	        System.out.println("<<<Already Sorted Array>>>*: "+Arrays.toString(sortedArray4));
	        long heapstarttime = System.nanoTime();     
	        heapsort(sortedArray4);
	        long heapendtime = System.nanoTime();
	        System.out.println("Heap Sort Execution time in nanoseconds:"+(heapendtime-heapstarttime)); 
	        System.out.println("<<<Sorted Array>>>: "+Arrays.toString(sortedArray4));
	        }
	
	//Function to sort array using insertion sort
    static void InsertionSort(int arrayToSort[]) 
    { 
        int n = arrayToSort.length; 
        for (int i=1; i<n; ++i) 
        { 
            int key = arrayToSort[i]; 
            int j = i-1; 
  
            /* Move elements of arr[0..i-1], that are 
               greater than key, to one position ahead 
               of their current position */
            while (j>=0 && arrayToSort[j] > key) 
            { 
                arrayToSort[j+1] = arrayToSort[j]; 
                j = j-1; 
            } 
            arrayToSort[j+1] = key; 
        } 
    } 
    
    
  
    //Function to sort using modified quick sort
	private static void medianQuickSort(int[] arrayToSort1, int left, int right) {		
		if(left + 10 <= right) {
			int pivot = getMedian(arrayToSort1, left, right);
			int partition = partition(arrayToSort1, left, right, pivot);
			medianQuickSort(arrayToSort1, left, partition-1);
			medianQuickSort(arrayToSort1, partition+1, right);		
		}else {
			//call insertion sort for smaller arrays
            InsertionSort(arrayToSort1);
		}
	}
	
	private static int partition(int[] arrayToSort1, int left, int right, int pivot) {
		int i = left, j = right - 1;
		while(true) {
			while(arrayToSort1[++i] < pivot)
				;
			while(pivot < arrayToSort1[--j])
				;
			if(i >= j)
				break;
			else
				swap(arrayToSort1, i, j);
		}
		swap(arrayToSort1, i, right-1);
		return i;
	}
	
	private static int getMedian(int[] arrayToSort1, int left, int right) {
		int center = (left+right)/2;
		
		if(arrayToSort1[center] < arrayToSort1[left])
			swap(arrayToSort1, center, left);
		if(arrayToSort1[right] < arrayToSort1[left])
			swap(arrayToSort1, right, left);
		if(arrayToSort1[right] < arrayToSort1[center])
			swap(arrayToSort1, right, center);
		
		swap(arrayToSort1, center, right-1);
		
		return arrayToSort1[right-1];
	}
	
	private static void swap(int[] arrayToSort1, int p, int r) {
		int temp = arrayToSort1[p];
		arrayToSort1[p] = arrayToSort1[r];
		arrayToSort1[r] = temp;
	}
    
	
	//Function for in-place quick sort
	
	private static void inPlaceQuickSort(int[] arrayToSort2, int left, int right) {
		if(left >= right)
			return;
		final int randomInt = right - left + 1;
		int pivot = random.nextInt(randomInt) + left;
		int newPivot = inPlacePartition(arrayToSort2, left, right, pivot);
		
		inPlaceQuickSort(arrayToSort2, left, newPivot-1);
		inPlaceQuickSort(arrayToSort2, newPivot+1, right);
	}
	
	private static int inPlacePartition(int[] arrayToSort2, int left, int right, int pivot) {
		int pivotValue = arrayToSort2[pivot];
		
		swapArray(arrayToSort2, pivot, right);
		
		int tempIndex = left;
		for(int i = left; i <= (right - 1); i++) {
            if(arrayToSort2[i] < pivotValue) {
                swapArray(arrayToSort2, i, tempIndex);
                tempIndex++;
            }
        }

        swapArray(arrayToSort2, tempIndex, right);

        return tempIndex;
	}
	
	private static void swapArray(int[] arrayToSort2, int p, int r) {
		int pValue = arrayToSort2[p];
		int rValue = arrayToSort2[r];
		arrayToSort2[p] = rValue;
		arrayToSort2[r] = pValue;
	}
	
	
	// Function for merge sort
	
	// Main function that sorts arr[l..r] using 
    // merge() 
    private static void mergesort(int arr[], int l, int r) 
    { 
        if (l < r) 
        { 
            // Find the middle point 
            int m = (l+r)/2; 
  
            // Sort first and second halves 
            mergesort(arr, l, m); 
            mergesort(arr , m+1, r); 
  
            // Merge the sorted halves 
            merge(arr, l, m, r); 
        } 
    }
    
 // Merges two subarrays of arr[]. 
    // First subarray is arr[l..m] 
    // Second subarray is arr[m+1..r] 
    private static void merge(int arr[], int l, int m, int r) 
    { 
        // Find sizes of two subarrays to be merged 
        int n1 = m - l + 1; 
        int n2 = r - m; 
  
        /* Create temp arrays */
        int L[] = new int [n1]; 
        int R[] = new int [n2]; 
  
        /*Copy data to temp arrays*/
        for (int i=0; i<n1; ++i) 
            L[i] = arr[l + i]; 
        for (int j=0; j<n2; ++j) 
            R[j] = arr[m + 1+ j]; 
  
  
        /* Merge the temp arrays */
  
        // Initial indexes of first and second subarrays 
        int i = 0, j = 0; 
  
        // Initial index of merged subarry array 
        int k = l; 
        while (i < n1 && j < n2) 
        { 
            if (L[i] <= R[j]) 
            { 
                arr[k] = L[i]; 
                i++; 
            } 
            else
            { 
                arr[k] = R[j]; 
                j++; 
            } 
            k++; 
        } 
  
        /* Copy remaining elements of L[] if any */
        while (i < n1) 
        { 
            arr[k] = L[i]; 
            i++; 
            k++; 
        } 
  
        /* Copy remaining elements of R[] if any */
        while (j < n2) 
        { 
            arr[k] = R[j]; 
            j++; 
            k++; 
        } 
    } 
	
    //Function for heap sort
    

public static void heapsort(int arr[])
    {
        int n = arr.length;
 
      // Build max heap
        for (int i = n / 2 - 1; i >= 0; i--) {
          heapify(arr, n, i);
        }
           
 
// Heap sort
        for (int i=n-1; i>=0; i--)
        {
            int temp = arr[0];
            arr[0] = arr[i];
            arr[i] = temp;
 
          // Heapify root element
            heapify(arr, i, 0);
        }
    }
 
    public static void heapify(int arr[], int n, int i)
    {
      // Find largest among root, left child and right child
        int largest = i;
        int l = 2*i + 1;
        int r = 2*i + 2;  
 
        if (l < n && arr[l] > arr[largest])
            largest = l;
 
        if (r < n && arr[r] > arr[largest])
            largest = r;
 
      // Swap and continue heapifying if root is not largest
        if (largest != i)
        {
            int swap = arr[i];
            arr[i] = arr[largest];
            arr[largest] = swap;
 
            heapify(arr, n, largest);
        }
    }
}
