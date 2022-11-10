import java.util.Scanner;
public class FracCalc {
    public static int whole;
    public static int numerator;
    public static int denominator;
    public static void main(String[] args) 
    {
        // TODO: Read the input from the user and call produceAnswer with an equation
        whole = 0;
        numerator = 0;
        denominator = 1;
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

        findFrac(second);
        

        if (second.contains("/")) {
            return "whole:" + whole + " numerator:" + numerator + " denominator:" + denominator;
        }
        else {
            return "whole:" + second + " numerator:" + "0" + " denominator:" + "1";
        }


    }

    // TODO: Fill in the space below with any helper methods that you think you will need
    public static boolean findFrac(String frac) {
        Scanner scan = new Scanner(frac);
        // if mixed fraction, split whole and fractional then split numerator and denominator
        int wholeNum = 0;
        int numer = 0;
        int denom = 0;
        if (frac.contains("_")) {
            String[] mixedFrac = frac.split("_");
            whole = Integer.parseInt(mixedFrac[0]);
            // splits fraction into numerator and denominator
            String[] properFrac = mixedFrac[1].split("/");
            numerator = Integer.parseInt(properFrac[0]);
            denominator = Integer.parseInt(properFrac[1]);
        }
        // if proper fraction, split according to numerator/denominator
        else if (frac.contains("/")){
                String[] properFrac = frac.split("/");
                numerator = Integer.parseInt(properFrac[0]);
                denominator = Integer.parseInt(properFrac[1]);
        }

        return true;

    }
    
}
