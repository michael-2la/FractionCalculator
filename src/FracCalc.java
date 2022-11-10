import java.util.Scanner;
public class FracCalc {

    public static void main(String[] args) 
    {
        // TODO: Read the input from the user and call produceAnswer with an equation
        Scanner scan = new Scanner(System.in);



    }
    
    // ** IMPORTANT ** DO NOT DELETE THIS FUNCTION.  This function will be used to test your code
    // This function takes a String 'input' and produces the result
    //
    // input is a fraction string that needs to be evaluated.  For your program, this will be the user input.
    //      e.g. input ==> "1/2 + 3/4"
    //        
    // The function should return the result of the fraction after it has been calculated
    //      e.g. return ==> "1_1/4"
    public static String produceAnswer(String input)
    { 
        // TODO: Implement this function to produce the solution to the input
        Scanner scan = new Scanner(input);
        
        scan.useDelimiter(" ");

        String first = scan.next();
        String operat = scan.next();
        String second = scan.next();
        findFrac(first);
        findFrac(second);
        



        return second;
    }

    // TODO: Fill in the space below with any helper methods that you think you will need
    public static boolean findFrac(String frac) {
        Scanner scan = new Scanner(frac);
        // splits according to mixed vs proper fractions
        String[] mixedFrac = frac.split("_");
        int wholeNum = Integer.valueOf(mixedFrac[0]);
        
        // splits fraction into numerator and denominator
        String[] properFrac = mixedFrac[1].split("/");
        int numerator = Integer.valueOf(properFrac[0]);
        int denominator = Integer.valueOf(properFrac[1]);
        return true;
    }
    
}
