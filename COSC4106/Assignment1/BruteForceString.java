import java.util.Scanner;


//Jacob Culp
//Reads the beginning letter and the left most letter to see if it as a string that begins with a and ends with b
//then it reads the beggining letter and the second left most etc.
//Add 1 to count for every possible substring and print the results.
public class BruteForceString {

	public static void main(String[] args) {
		
		Scanner in = new Scanner(System.in);
		System.out.print("Please enter a String: ");
		String text = in.nextLine();
		text = text.toUpperCase();
		
		int count = 0;
		for(int i=text.length()-1; i>=0; i--) {
			for (int j=0; j<i; j++) {
				if (text.charAt(j) == 'A' && text.charAt(i) == 'B') count++;
			}
		}
		
		System.out.print("String: "+text+"\nThe number of substrings that start with A and end with B is "+count+".");
		
		in.close();
	}
}
