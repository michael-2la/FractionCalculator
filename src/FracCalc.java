import java.util.Objects;
import java.util.Scanner;
/**
 * Class for a Fraction Calculator given two fractions and an operator
 * @author Michelle La
 */
public class FracCalc {
    // static variables for taking the two fractions and the eventual result
    public static int whole1;
    public static int numerator1;
    public static int denominator1;
    public static int whole2;
    public static int numerator2;
    public static int denominator2;
    public static String answer;
    /**
     * prompts an input from user/takes input from user, initializes static variables, returns answer to fraction expression
     * @return fraction expression answer
     */
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
        String userInput = "";
        while (!userInput.equals("END")) {
            System.out.println("Enter a fraction expression: ");
            userInput = scan.nextLine();

            produceAnswer(userInput);
            System.out.println(answer);
        }




    }

    // ** IMPORTANT ** DO NOT DELETE THIS FUNCTION.  This function will be used to test your code
    // This function takes a String 'input' and produces the result
    //
    // input is a fraction string that needs to be evaluated.  For your program, this will be the user input.
    //      e.g. input ==> "1/2 + 3/4"
    //
    // The function should return the result of the fraction after it has been calculated
    //      e.g. return ==> "1_1/4"
    /**
     * takes input, splits each fraction + operator with a delimiter, checks for the validity of the expression
     * returns answer depending on operator type (addition, subtraction, etc.) after calling calculation methods
     * @param input from the line userInput in main method
     * @return fraction expression answer
     */
    public static String produceAnswer(String input) {
        // TODO: Implement this function to produce the solution to the input
        Scanner scan = new Scanner(input);
        // splits expression according to different parts (fraction1, operator, fraction2)
        scan.useDelimiter(" ");

        String first = scan.next();
        String operat = scan.next();
        String second = scan.next();

        // validate if the fractions are valid, if the operator is valid - EXTRA CREDIT
        for (int i=0; i<first.length(); i++) {
            if (first.charAt(i) != '/' && first.charAt(i) != '_') {
                if (!Character.isDigit(first.charAt(i))) {
                    // prints error message
                    System.out.println("ERROR: Fraction is in an invalid format.");
                    // prevents program from crashing
                    return "";
                }


            }
        }
        for (int i=0; i<second.length(); i++) {
            if (second.charAt(i) != '/' && second.charAt(i) != '_') {
                if (!Character.isDigit(second.charAt(i))) {
                    // prints error message
                    System.out.println("ERROR: Fraction is in an invalid format.");
                    // prevents program from crashing
                    return "";
                }

            }
        }

        // checks if the operator is valid - EXTRA CREDIT
        if (operat.equals("+") == false && operat.equals("-") == false && operat.equals("*") == false && operat.equals("/") == false) {
            System.out.println("ERROR: Invalid operator!");
            return "";
        }

        // splits respective fractions into whole, numerator, denominator
        findFrac1(first);
        findFrac2(second);

        // validate if numerators/denominators/wholes of expression are valid - EXTRA CREDIT
        if (denominator2 == 0 || denominator1 == 0) {
            System.out.println("ERROR: Cannot divide by 0.");
            return "";
        }


        // checks for operator type and then calls respective calculation method accordingly, and returns answer
        if (operat.equals("+")) {
            addition();
            return answer;
        }
        else if (operat.equals("-")) {
            subtraction();
            return answer;
        }
        else if (operat.equals("*")) {
            multiplication();
            return answer;
        }
        else if (operat.equals("/")) {
            division();
            return answer;
        }



        return "";


    }

    // TODO: Fill in the space below with any helper methods that you think you will need
    /**
     * program to find the greatest common factor of the numerator and denominator
     * @param integers a and b for numerator, denominator
     * @return greatest common factor of numerator/denominator
     */
    public static int GCF(int a, int b) {
        if (b == 0) {
            return a;
        }
        else {
            return (GCF(b, a % b));
        }

    }
    /**
     * reduces answer to mixed fractions if numerator/denominator is greater than 1
     * @param integers nums and denoms for the mixedAns numerator and newDenom denominator
     * @return void
     */
    public static void mixedFraction(int nums, int denoms) {
        // mixed fraction check:
        int wholes;
        // takes absolute value of numerator/denominator for deciding on whether or not it is a mixed fraction
        if (Math.abs(nums/denoms) > 0) {
            wholes = nums/denoms;

            if (nums < 0 ) {
                // makes only the whole number negative for formatting
                nums = -1 * nums;
                // update new numerator
                nums = nums - (wholes * denoms);
                answer = wholes + "_" + nums + "/" + denoms;
            }
            else if (denoms < 0) {
                // makes only the whole number negative for formatting
                denoms = -1 * denoms;
                // update new numerator
                nums = nums - (Math.abs(wholes * denoms));
                answer = wholes + "_" + nums + "/" + denoms;
            }
            else {
                // update new numerator
                nums = nums - (wholes * denoms);
                answer = wholes + "_" + nums + "/" + denoms;
            }
        }
        else {
            // if the fraction is proper
            answer = nums + "/" + denoms;
        }
    }



    /**
     * program to split the first fraction into a whole number, numerator, and denominator
     * @param string frac for the first fraction in the expression
     * @return true once program has run
     */
    public static boolean findFrac1(String frac) {
        Scanner scan = new Scanner(frac);
        // if mixed fraction, split whole and fractional then split numerator and denominator
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
        // if not a fraction (whole number)
        else {
            numerator1 = Integer.parseInt(frac);
            denominator1 = 1;
        }

        return true;

    }
    /**
     * program to split the second fraction into a whole number, numerator, and denominator
     * @param string frac for the second fraction in the expression
     * @return true once program has run
     */
    public static boolean findFrac2(String frac) {
        Scanner scan = new Scanner(frac);
        // if mixed fraction, split whole and fractional then split numerator and denominator
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
        // if not a fraction (whole number)
        else {
            numerator2 = Integer.parseInt(frac);
            denominator2 = 1;
        }

        return true;

    }
    /**
     * program to add two fractions together with multiple cases, then send sum to answer variable
     * @return true once program has run
     */
    public static boolean addition() {
        int mixedAns;
        int newDenom;
        // turns into mixed fraction
        if (whole1 != 0) {
            if (whole1 < 0) {
                numerator1 = -(numerator1 - (whole1 * (denominator1)));
            }
            else {
                numerator1 = numerator1 + (whole1 * denominator1);
            }

        }
        if (whole2 != 0) {
            if (whole2 < 0) {
                numerator2 = -(numerator2 - (whole2 * (denominator2)));
            }
            else {
                numerator2 = numerator2 + (whole2 * denominator2);
            }
        }

        // if denominators are equal:
        if (denominator1 == denominator2) {
            mixedAns = numerator1 + numerator2;
            newDenom = denominator2;
            int gcf = GCF(mixedAns, newDenom);
            // reduce numerator and denominator
            mixedAns = mixedAns/gcf;
            newDenom = newDenom/gcf;
            // return reduced fraction, also checking for special cases
            if (mixedAns == 0) {
                // for numerator = 0 cases
                answer = "0";
            }
            else if (newDenom == 1) {
                answer = String.valueOf(mixedAns);
            }
            else {
                // mixed fraction check:
                mixedFraction(mixedAns, newDenom);
            }



        }
        // if denominators not equal:
        else {
            // find common denominator
            newDenom = denominator1 * denominator2;
            // now convert fractions into having same denominator
            // if it is a whole number:
            if (denominator1 == 1) {
                numerator1 = newDenom * numerator1;
            }
            else {
                numerator1 = numerator1 * (newDenom / denominator1);
            }
            // if it is a whole number:
            if (denominator2 == 1) {
                numerator2 = newDenom * numerator2;
            }
            else {
                numerator2 = numerator2 * (newDenom / denominator2);
            }

            // now add values:
            mixedAns = numerator1 + numerator2;

            // simplify answer:
            int gcf = GCF(mixedAns, newDenom);
            // reduce numerator and denominator
            mixedAns = mixedAns/gcf;
            newDenom = newDenom/gcf;
            // return reduced fraction
            if (mixedAns == 0) {
                // for numerator = 0 cases
                answer = "0";
            }
            else if (newDenom == 1) {
                answer = String.valueOf(mixedAns);
            }
            else {
                // mixed fraction check:
                mixedFraction(mixedAns, newDenom);
            }




        }
        return true;

    }
    /**
     * program to subtract two fractions together with multiple cases, then send difference to answer variable
     * @return true once program has run
     */
    public static boolean subtraction() {
        int mixedAns;
        int newDenom;
        // turns into mixed fraction
        if (whole1 != 0) {
            if (whole1 < 0) {
                numerator1 = -(numerator1 - (whole1 * (denominator1)));
            }
            else {
                numerator1 = numerator1 + (whole1 * denominator1);
            }

        }
        if (whole2 != 0) {
            if (whole2 < 0) {
                numerator2 = -(numerator2 - (whole2 * (denominator2)));
            }
            else {
                numerator2 = numerator2 + (whole2 * denominator2);
            }

        }

        // if denominators are equal:
        if (denominator1 == denominator2) {
            mixedAns = numerator1 - numerator2;
            newDenom = denominator2;
            int gcf = GCF(mixedAns, newDenom);
            // reduce numerator and denominator
            mixedAns = mixedAns/gcf;
            newDenom = newDenom/gcf;
            // return reduced fraction
            if (mixedAns == 0) {
                // for numerator = 0 cases
                answer = "0";
            }
            else if (newDenom == 1) {
                answer = String.valueOf(mixedAns);
            }
            else {
                // mixed fraction check:
                mixedFraction(mixedAns, newDenom);
            }
        }
        // if denominators not equal:
        else {

            // find common denominator
            newDenom = denominator1 * denominator2;
            // now convert fractions into having same denominator
            // if it is a whole number:
            if (denominator1 == 1) {
                numerator1 = newDenom * numerator1;
            } else {
                numerator1 = numerator1 * (newDenom / denominator1);
            }
            // if it is a whole number:
            if (denominator2 == 1) {
                numerator2 = newDenom * numerator2;
            } else {
                numerator2 = numerator2 * (newDenom / denominator2);
            }

            // now add values:
            mixedAns = numerator1 - numerator2;

            // simplify answer:
            int gcf = GCF(mixedAns, newDenom);
            // reduce numerator and denominator
            mixedAns = mixedAns/gcf;
            newDenom = newDenom/gcf;
            // return reduced fraction
            if (mixedAns == 0) {
                // for numerator = 0 cases
                answer = "0";
            }
            else if (newDenom == 1) {
                answer = String.valueOf(mixedAns);
            }
            else {
                // mixed fraction check:
                mixedFraction(mixedAns, newDenom);
            }
        }
        return true;
    }

    /**
     * program to multiply two fractions together with multiple cases, then send product to answer variable
     * @return true once program has run
     */
    public static boolean multiplication() {
        int mixedAns;
        int newDenom;
        // turns into mixed fraction
        if (whole1 != 0) {
            if (whole1 < 0) {
                numerator1 = -(numerator1 - (whole1 * (denominator1)));
            }
            else {
                numerator1 = numerator1 + (whole1 * denominator1);
            }

        }
        if (whole2 != 0) {
            if (whole2 < 0) {
                numerator2 = -(numerator2 - (whole2 * (denominator2)));
            }
            else {
                numerator2 = numerator2 + (whole2 * denominator2);
            }
        }

        // for multiplication: simply multiply across
        mixedAns = numerator1 * numerator2;
        newDenom = denominator1 * denominator2;

        // simplify answer
        int gcf = GCF(mixedAns, newDenom);
        // reduce numerator and denominator
        mixedAns = mixedAns/gcf;
        newDenom = newDenom/gcf;
        // return reduced fraction
        if (mixedAns == 0) {
            // for numerator = 0 cases
            answer = "0";
        }
        else if (newDenom == 1) {
            answer = String.valueOf(mixedAns);
        }
        else {
            // mixed fraction check:
            mixedFraction(mixedAns, newDenom);
        }


        return true;

    }
    /**
     * program to divide two fractions together with multiple cases, then send dividend to answer variable
     * @return true once program has run
     */
    public static boolean division() {
        int mixedAns;
        int newDenom;
        // turns into mixed fraction
        if (whole1 != 0) {
            if (whole1 < 0) {
                numerator1 = -(numerator1 - (whole1 * (denominator1)));
            }
            else {
                numerator1 = numerator1 + (whole1 * denominator1);
            }

        }
        if (whole2 != 0) {
            if (whole2 < 0) {
                numerator2 = -(numerator2 - (whole2 * (denominator2)));
            }
            else {
                numerator2 = numerator2 + (whole2 * denominator2);
            }

        }
        // for division, switch numerators and denominators:
        newDenom = denominator1 * numerator2;
        mixedAns = numerator1 * denominator2;

        // simplify answer:
        int gcf = GCF(mixedAns, newDenom);
        // reduce numerator and denominator
        mixedAns = mixedAns/gcf;
        newDenom = newDenom/gcf;
        // return reduced fraction
        if (mixedAns == 0) {
            // for numerator = 0 cases
            answer = "0";
        }
        else if (newDenom == 1) {
            answer = String.valueOf(mixedAns);
        }
        else {
            // mixed fraction check:
            mixedFraction(mixedAns, newDenom);
        }


        return true;

    }

}