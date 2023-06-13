import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

//Jacob Culp
//Creates a shiftTable from a provided pattern and test to see if it in the provided text.
//Then prints the shiftTable and the result.
public class Horspool_Algorithm {

	public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);

        System.out.print("Please enter the text: ");
        String text = scanner.nextLine();
        System.out.print("Please enter your pattern: ");
        String pattern = scanner.nextLine();

        HashMap<Character, Integer> shiftTable = createShiftTable(pattern);

        //Print the shift table and answer
        System.out.println("Shift Table:");
        for (Map.Entry<Character, Integer> pair : shiftTable.entrySet()) {
            System.out.println(pair.getKey() + " = " + pair.getValue());
        }
        // '~' indicates any other character that didn't appear in the pattern
        System.out.println("~ = " + pattern.length());
        if(isPatternInText(text, pattern, shiftTable)) System.out.println("The pattern '"+pattern+"' IS in the text '"+text+"'.");
        else System.out.println("The pattern '"+pattern+"' IS NOT in the text '"+text+"'.");
    }
	
	//Uses hashmap to create a table with each character having its own shift value
    public static HashMap<Character, Integer> createShiftTable(String pattern){
        HashMap<Character, Integer> shiftTable = new HashMap<>();
        int length = pattern.length();

        //Go from the first letter to the second last letter
        for(int i = 0; i < length - 1; i++){
            char letter = pattern.charAt(i);
            int shift = length - i - 1;
            if(shiftTable.containsKey(letter)) shiftTable.replace(letter, shift);
            else shiftTable.put(letter, shift);
        }

        //The last character is length assuming you did not find it already in the pattern
        char c = pattern.charAt(length - 1);
        if(!shiftTable.containsKey(c)) shiftTable.put(c, length);

        return shiftTable;
    }
    
	//Uses the horspool algorithm to derive whether or not the pattern is in the text
    public static boolean isPatternInText(String text, String pattern, HashMap<Character, Integer> shiftTable){
        int patternLength = pattern.length();
        char rightChar = pattern.charAt((patternLength - 1));

        int i = patternLength - 1;
        while(i < text.length()){
            char c = text.charAt(i);
            if(rightChar == c){
                int k = i - 1;
                int j = patternLength - 2;
                while(j >= 0){
                    char patChar = pattern.charAt(j);
                    char textChar = text.charAt(k);
                    if(patChar != textChar) break;
                    else{
                        k--;
                        j--;
                    }
                }
                //in case you get through the entire pattern
                if(j == -1) return true;
            }
            if(shiftTable.containsKey(c)) i += shiftTable.get(c);
            else i += patternLength;
        }
        return false;
    }
    
}
