import java.util.Scanner;
public class FracCalc {
    public static int whole1;
    public static int numerator1;
    public static int denominator1;
    public static int whole2;
    public static int numerator2;
    public static int denominator2;
    public static int operator;

    public static String answer;
    public static void main(String[] args) 
    {
        // TODO: Read the input from the user and call produceAnswer with an equation
        whole1 = 0;
        numerator1 = 0;
        denominator1 = 1;
        whole2 = 0;
        numerator2 = 0;
        denominator2 = 1;
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter a fraction expression: ");
        String userInput = scan.next();

        produceAnswer(userInput);



    }
    
    // ** IMPORTANT ** DO NOT DELETE THIS FUNCTION.  This function will be used to test your code
    // This function takes a String 'input' and produces the result
    //
    // input is a fraction string that needs to be evaluated.  For your program, this will be the user input.
    //      e.g. input ==> "1/2 + 3/4"
    //        
    // The function should return the result of the fraction after it has been calculated
    //      e.g. return ==> "1_1/4"
    public static String produceAnswer(String input) {
        // TODO: Implement this function to produce the solution to the input
        Scanner scan = new Scanner(input);
        
        scan.useDelimiter(" ");

        String first = scan.next();
        String operat = scan.next();
        String second = scan.next();

        String ans = "";

        findFrac1(first);
        findFrac1(second);

        if (operat == "+") {
            addition();
            System.out.println(answer);
        }
        else if (operat == "-") {
            subtraction();
            System.out.println(answer);
        }
        else if (operat == "*") {
            multiplication();
            System.out.println(answer);
        }
        else if (operat == "/") {
            division();
            System.out.println(answer);
        }


    }

    // TODO: Fill in the space below with any helper methods that you think you will need
    public static boolean findFrac1(String frac) {
        Scanner scan = new Scanner(frac);
        // if mixed fraction, split whole and fractional then split numerator and denominator
        int wholeNum = 0;
        int numer = 0;
        int denom = 0;
        if (frac.contains("_")) {
            String[] mixedFrac = frac.split("_");
            whole1 = Integer.parseInt(mixedFrac[0]);
            // splits fraction into numerator and denominator
            String[] properFrac = mixedFrac[1].split("/");
            numerator1 = Integer.parseInt(properFrac[0]);
            denominator1 = Integer.parseInt(properFrac[1]);
        }
        // if proper fraction, split according to numerator/denominator
        else if (frac.contains("/")){
                String[] properFrac = frac.split("/");
                numerator1 = Integer.parseInt(properFrac[0]);
                denominator1 = Integer.parseInt(properFrac[1]);
        }

        return true;

    }

    public static boolean findFrac2(String frac) {
        Scanner scan = new Scanner(frac);
        // if mixed fraction, split whole and fractional then split numerator and denominator
        int wholeNum = 0;
        int numer = 0;
        int denom = 0;
        if (frac.contains("_")) {
            String[] mixedFrac = frac.split("_");
            whole2 = Integer.parseInt(mixedFrac[0]);
            // splits fraction into numerator and denominator
            String[] properFrac = mixedFrac[1].split("/");
            numerator2 = Integer.parseInt(properFrac[0]);
            denominator2 = Integer.parseInt(properFrac[1]);
        }
        // if proper fraction, split according to numerator/denominator
        else if (frac.contains("/")){
                String[] properFrac = frac.split("/");
                numerator2 = Integer.parseInt(properFrac[0]);
                denominator2 = Integer.parseInt(properFrac[1]);
        }

        return true;

    }

    public static boolean addition() {
        // turns into mixed fraction
        if (whole1 != 0) {
            numerator1 = numerator1 + (whole1 * denominator1);
        }
        if (whole2 != 0) {
            numerator2 = numerator2 + (whole2 * denominator2);
        }

        // if denominators are equal:
        if (denominator1 == denominator2) {
            int finalNumer = numerator1 + numerator2;
            answer = finalNumer + "/" + denominator1;
        }
        // if denominators not equal:
        else {
            int lcm = 0;
            int oldDenom1 = denominator1;
            int oldDenom2 = denominator2;

            // find lcm of denominators
            for (int i=1; i<= denominator1 && i <= denominator2; i++) {
                if (denominator1 & i == 0 && denominator2 & i == 0) {
                    lcm = (denominator1 * denominator2) / i;
                }
            }
            // now convert fractions into having same denominator
            numerator1 = numerator1 * (lcm / oldDenom1);
            numerator2 = numerator2 * (lcm / oldDenom2);

            // now add values:
            int mixedAns = numerator1 + numerator2;
            answer = mixedAns + "/" denominator2;
        }

    }

    public static boolean subtraction() {
        // turns into mixed fraction
        if (whole1 != 0) {
            numerator1 = numerator1 + (whole1 * denominator1);
        }
        if (whole2 != 0) {
            numerator2 = numerator2 + (whole2 * denominator2);
        }

        // if denominators are equal:
        if (denominator1 == denominator2) {
            int finalNumer = numerator1 - numerator2;
            answer = finalNumer + "/" + denominator1;
        }
        // if denominators not equal:
        else {
            int lcm = 0;
            int oldDenom1 = denominator1;
            int oldDenom2 = denominator2;

            // find lcm of denominators
            for (int i=1; i<= denominator1 && i <= denominator2; i++) {
                if (denominator1 & i == 0 && denominator2 & i == 0) {
                    lcm = (denominator1 * denominator2) / i;
                }
            }
            // now convert fractions into having same denominator
            numerator1 = numerator1 * (lcm / oldDenom1);
            numerator2 = numerator2 * (lcm / oldDenom2);

            // now add values:
            int mixedAns = numerator1 - numerator2;
            answer = mixedAns + "/" denominator2;
        }

    }

    public static boolean multiplication() {
        // turns into mixed fraction
        if (whole1 != 0) {
            numerator1 = numerator1 + (whole1 * denominator1);
        }
        if (whole2 != 0) {
            numerator2 = numerator2 + (whole2 * denominator2);
        }

        // if denominators are equal:
        if (denominator1 == denominator2) {
            int finalNumer = numerator1 * numerator2;
            answer = finalNumer + "/" + denominator1;
        }
        // if denominators not equal:
        else {
            int lcm = 0;
            int oldDenom1 = denominator1;
            int oldDenom2 = denominator2;

            // find lcm of denominators
            for (int i=1; i<= denominator1 && i <= denominator2; i++) {
                if (denominator1 & i == 0 && denominator2 & i == 0) {
                    lcm = (denominator1 * denominator2) / i;
                }
            }
            // now convert fractions into having same denominator
            numerator1 = numerator1 * (lcm / oldDenom1);
            numerator2 = numerator2 * (lcm / oldDenom2);

            // now add values:
            int mixedAns = numerator1 * numerator2;
            answer = mixedAns + "/" denominator2;
        }

    }

    public static boolean multiplacation() {
        // turns into mixed fraction
        if (whole1 != 0) {
            numerator1 = numerator1 + (whole1 * denominator1);
        }
        if (whole2 != 0) {
            numerator2 = numerator2 + (whole2 * denominator2);
        }

        // if denominators are equal:
        if (denominator1 == denominator2) {
            double finalNumer = (double) numerator1 / numerator2;
            answer = finalNumer + "/" + denominator1;
        }
        // if denominators not equal:
        else {
            int lcm = 0;
            int oldDenom1 = denominator1;
            int oldDenom2 = denominator2;

            // find lcm of denominators
            for (int i=1; i<= denominator1 && i <= denominator2; i++) {
                if (denominator1 & i == 0 && denominator2 & i == 0) {
                    lcm = (denominator1 * denominator2) / i;
                }
            }
            // now convert fractions into having same denominator
            numerator1 = numerator1 * (lcm / oldDenom1);
            numerator2 = numerator2 * (lcm / oldDenom2);

            // now add values:
            double mixedAns = (double) numerator1 / numerator2;
            answer = mixedAns + "/" denominator2;
        }

    }
    
}
