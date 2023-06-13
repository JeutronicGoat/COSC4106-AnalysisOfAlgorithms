//Jacob Culp
import java.util.Arrays;
public class TransformAndConquer {

	public static void main(String[] args) {
		
		int[] array1 = {8,3,4,7};
		int[] array2 = {5,6,12,1};
		int sum1 = 10;
		
		int[] array3 = {1, 5, 4, 2};
        int[] array4 = {6, 3, 2, 1};
        int sum2 = 9;
        
        mergeSort(array1);
        mergeSort(array2);
        mergeSort(array3);
        mergeSort(array4);
        
//        System.out.println(Arrays.toString(array1));
//        System.out.println(Arrays.toString(array2));
//        System.out.println(Arrays.toString(array3));
//        System.out.println(Arrays.toString(array4));
        
        findSumElements(array1, array2, sum1);
        findSumElements(array3, array4, sum2);
        
	}
	
	public static void findSumElements(int[] array1, int[] array2, int sum) {
		//we go through array1 n times but then use binary search on array2 
		//to find the desired element if it exists
		
		boolean nonTrue = false;
		for(int i = 0; i < array1.length; i++) {
			int num1 = array1[i];
			int num2 = sum - num1;
			
			int result = Arrays.binarySearch(array2, num2);
//			System.out.println("num1 "+num1+", sum "+sum+", num2 "+num2+", result "+result);	//for testing purposes
			if(result >= 0) {
				System.out.println("The two numbers that add to "+sum+" are "+array1[i]+" from array1 and "+array2[result]+" from array2.");
				nonTrue = true;
			}
		}
		//if there are no 2 number that add up to the sum print a message
		if(nonTrue == false) System.out.println("No two numbers add up to "+sum+".");
	}
	
	public static void mergeSort(int[] array){
        if(array.length > 1){
            int mid = array.length / 2;

            int[] left = new int[mid];
            int[] right = new int[array.length - mid];
            for(int i = 0; i < mid; i++){
                left[i] = array[i];
            }

            for(int i = 0; i < array.length - mid; i++){
                right[i] = array[mid + i];
            }

            mergeSort(left);
            mergeSort(right);
            merge(left, right, array);
        }

        return ;
    }
	
	public static void merge(int[] left, int[] right, int[] array){
        int i = 0, j = 0, k = 0;
        while(i < left.length && j < right.length){
            if(left[i] <= right[j]){
                array[k] = left[i];
                i++;
            }
            else{
                array[k] = right[j];
                j++;
            }
            k++;
        }

        if(i == left.length){
            while(j < right.length){
                array[k] = right[j];
                k++;
                j++;
            }
        }
        else{
            while(i < left.length){
                array[k] = left[i];
                k++;
                i++;
            }
        }

    }
}
