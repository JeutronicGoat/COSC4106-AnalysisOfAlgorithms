import java.util.Arrays;

//Jacob Culp
public class LongestSubsequence {

	public static void main(String[] args) {
	
//		String string1 = "CBDA";
//		String string2 = "ACADB";
		
		String string1 = "ABCBDAB";
		String string2 = "BDCABA";
		int m = string1.length();
		int n = string2.length();
		int[][] c = new int[m+1][n+1];
		String result = "";
		
		//Set all values to 0 in the first column and row
		for(int i = 0; i <= m; i++) {
			c[i][0] = 0;
			c[0][i] = 0;
		}
		
		for(int i = 0; i <= m; i++) {
			for(int j = 0; j <= n; j++) {
				//i and j at 0 always = zero
				if(i != 0 && j !=0) { 
					if(string1.charAt(i-1) == string2.charAt(j-1)) {
						c[i][j] = c[i-1][j-1] + 1;
					}
					else {
						c[i][j] = Math.max(c[i-1][j], c[i][j-1]);
					}
					//Prints the letters of each string and the current value in the table at each step
					//System.out.println("("+string1.charAt(i-1)+", "+string2.charAt(j-1)+", "+c[i][j]+")");
				}
			}
		}
		
		for(int i=m, j=n; c[i][j] != 0;){
				if(c[i][j] > c[i-1][j] && c[i][j] > c[i][j-1]) {
					result = string1.charAt(i-1) + result;
					i--;
					j--;
				}
				else if(c[i][j] == c[i-1][j]) {
					i--;
				}
				else if(c[i][j] == c[i][j-1]) {
					j--;
				}
		}
		
		//Print table and result
		for(int k = 0; k <= m; k++) {
			System.out.println(Arrays.toString(c[k]));
		}
		System.out.println("The longest subsequence between X = "+string1+" and Y = "+string2+" is "+result+".");
		
		
	}
}
