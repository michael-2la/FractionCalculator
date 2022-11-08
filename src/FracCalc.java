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
        Scanner scan = new Scanner(System.in);
        if (input.contains('+') == true) {
            scan.useDelimiter("+");
        }
        else if (input.contains('-') == true) {
            scan.useDelimiter("-");
        }
        else if (input.contains('*') == true) {
            scan.useDelimiter("*");
        }
        else if (input.contains('/') == true) {
            scan.useDelimiter("/");
        }
        String first;
        String second;
        if (scan.hasNext()) {
            first = String.valueOf(scan.hasNext());
        }
        if (scan.hasNext()) {
            second = String.valueOf(scan.hasNext());
        }
        // check if improper fraction:
        String full1;
        String fract1;
        String full2;
        String fract2;

        if (first.contains('_') == true) {
            for (int i=0;i<first.indexOf('_'); i++) {
                full1 = full1 + first.substring(i, i);
            }
            for (int i=first.indexOf('/'); i<first.length(); i++) {
                fract1 = fract1 + first.substring(i, i);
            }

        }
        if (second.contains('_') == true) {
            for (int i=0;i<second.indexOf('_'); i++) {
                full2 = full2 + second.substring(i, i);
            }
            for (int i=second.indexOf('/'); i<second.length(); i++) {
                fract2 = fract2 + second.substring(i, i);
            }

        }



        return "";
    }

    // TODO: Fill in the space below with any helper methods that you think you will need
    
}
