//Jacob Culp
import java.util.Arrays;
public class DivideAndConquer {

	public static void main(String[] args) {
		int[] array = {9,8,4,5};
		System.out.print("The # of inversions in "+Arrays.toString(array)+" is ");
		int answer = inversions(array);
		System.out.print(answer+".");
	}
	
	//divides the array until they are all 1 element
	//counts all inversions on the back up
	public static int inversions(int[] array) {
		int inver = 0;
		if(array.length > 1) {
			int mid =  array.length/2;
			
			int[] left = new int[mid];
			int[] right = new int[array.length - mid];
			for(int i = 0; i < mid; i++) {
				left[i] = array[i];
			}
			
			for(int i = 0; i < array.length - mid; i++) {
				right[i] = array[mid+i];
			}
			
			inver += inversions(left);
			inver += inversions(right);
			inver += merge(left, right, array);
					
		}
		
		return inver;
	}
	
	//merges all the elements back together while keeping track of how many inversion there are
	public static int merge(int[] left, int[] right, int[] array) {
		int inver = 0;
		int i = 0, j = 0, k = 0;
		while(i < left.length && j < right.length) {
			if(left[i] <= right[j]) {
				array[k] = left[i];
				i++;
			}
			else {
				array[k] = right[j];
				inver += left.length;
				j++;
			}
			k++;
		}
		
		if(i == left.length) {
			while(j < right.length) {
				array[k] = left[j];
				k++;
				j++;
			}
		}
		else {
			while(i < left.length) {
				array[k] = left[i];
				k++;
				i++;
			}
		}
		
		return inver;
	}
	
}
