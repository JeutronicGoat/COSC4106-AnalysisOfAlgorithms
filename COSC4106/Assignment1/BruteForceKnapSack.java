import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;

//Jacob Culp
//Asks for each of the variable needed for the knapsack problem
//then uses getPowerSet to get the powerset which then allows us to 
//use each subset as idexes to find the total weights and values.

//Finally we compare the result to find the highest value that doesn't 
//exceed the weight limit and display the results.
public class BruteForceKnapSack {

	public static void main(String[] args) {
		
		Scanner in = new Scanner(System.in);
		
		System.out.print("Please enter the amount of items: ");
		int numItems = in.nextInt()+1;
		int items[] = new int[numItems];
		items[0] = 0;
		for(int i=1; i<numItems; i++) items[i] = i;
		
		System.out.println("Please enter the weight of each item: ");
		int itemWeights[] = new int[numItems];
		itemWeights[0] = 0;
		for(int i=1; i<numItems; i++) {
			System.out.print(i+") ");
			itemWeights[i] = in.nextInt();
		}
		
		System.out.println("Please enter the value of each item: ");
		int itemValues[] = new int[numItems];
		itemValues[0] = 0;
		for(int i=1; i<numItems; i++) {
			System.out.print(i+") ");
			itemValues[i] = in.nextInt();
		}
		
		System.out.print("What is the capacity of your knapsack?: ");
		int capacity = in.nextInt();
		
		int[][] powerSet = getPowerSet(items);
		
		int[] weight = new int[powerSet.length];
		int[] value = new int[powerSet.length];
		for(int i=0; i<=powerSet.length-1; i++) {
			for(int j=0; j<=powerSet[i].length-1; j++) {
				weight[i] += itemWeights[powerSet[i][j]];
				value[i] += itemValues[powerSet[i][j]];
			}
		}
		
//		int[] value = new int[powerSet.length];
//		for(int i=0; i<=powerSet.length-1; i++) {
//			for(int j=0; j<=powerSet[i].length-1; j++) {
//				value[i] += itemValues[powerSet[i][j]];
//			}
//		}
		
		System.out.println("The total weight of each subset is: ");
		for(int i=0; i<=weight.length-1; i++) System.out.print(weight[i]+" ");
		System.out.println("\nThe total value of each subset is: ");
		for(int i=0; i<=value.length-1; i++) System.out.print(value[i]+" ");
		
		int largestVal = 0;
		for(int i=0; i<=weight.length-1; i++) {
			if(value[i]>largestVal && weight[i]<=capacity) largestVal = value[i];
		}
		System.out.println("\nThe largest value that fits into the knapsack is "+largestVal+".");
		
//		int[][] test = getPowerSet(items);			//print powerset
//		for(int i=0; i<=test.length-1; i++) {
//			System.out.println(Arrays.toString(test[i]));
//		}
		
	}
	
	public static int[][] getPowerSet(int[] a){
		
		if(a.length == 1) {
			int[][] temp = {a};
			return temp;
		}
		else {
			int elem = a[a.length-1];
			int[] temp = new int[a.length-1];
			for(int i=0; i<=temp.length-1; i++) temp[i]=a[i];
			
			
			int[][] b = getPowerSet(temp);
			
			
			int[][] b2 = new int[b.length][];
			for(int i=0; i<=b.length-1; i++) {
				int[]temp1 = new int[b[i].length+1];
				for(int j=0; j<=b[i].length-1; j++) {
						temp1[j] = b[i][j];
				}
				temp1[temp1.length-1] = elem;
				b2[i] = temp1;
			}
			
			
			int[][] c = new int[b.length*2][];
			for(int i=0; i<=b.length-1; i++) {
				c[i]=b[i];
			}
			for(int i=b2.length, j=0; i<=c.length-1; i++, j++) {
				c[i]=b2[j];
			}
			return c;
		}
		
	}
	
}
