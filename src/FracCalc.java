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
        String userInput = scan.nextLine();

        produceAnswer(userInput);
        System.out.println(answer);



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

        // validate if the fractions are valid, if the operator is valid (Extra Credit)
        validateNums(first,second);
        // validateOper(operat);


        findFrac1(first);
        findFrac2(second);

        // validate if numerators/denominators/wholes of expression are valid (Extra Credit)
        validateParts();



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

    public static int GCF(int a, int b) {
        if (b == 0) {
            return a;
        }
        else {
            return (GCF(b, a % b));
        }

    }

    public static void validateOper(String operators) {
        if (operators.equals("+") == false || operators.equals("-") == false || operators.equals("*") == false || operators.equals("/") == false) {
            System.out.println("ERROR: Invalid operator!");
        }
    }

    public static String validateNums(String firsts, String seconds) {
        for (int i=0; i<firsts.length(); i++) {
            // if the character is not a number:
            if (firsts.charAt(i) < '0' || firsts.charAt(i) > '9') {
                if (firsts.charAt(i) != '/' && firsts.charAt(i) != '_') {
                    return "ERROR: Non-digit fraction!";
                }
            }

        }
        for (int i=0; i<seconds.length(); i++) {
            // if the character is not a number:
            if (seconds.charAt(i) < '0' || seconds.charAt(i) > '9') {
                if (seconds.charAt(i) != '/' && seconds.charAt(i) != '_') {
                    return "ERROR: Non-digit fraction!";
                }
            }
        }
        return "";

    }

    public static String validateParts() {
        if (denominator1 == 0 || denominator2 == 0) {
            return "ERROR: Cannot divide by 0";
        }
        return "";
    }


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
        else {
            numerator1 = Integer.parseInt(frac);
            denominator1 = 1;
        }

        return true;

    }

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
        else {
            numerator2 = Integer.parseInt(frac);
            denominator2 = 1;
        }

        return true;

    }

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
                answer = mixedAns + "/" + newDenom;
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
                answer = mixedAns + "/" + newDenom;
            }




        }
        return true;

    }

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
            mixedAns = numerator1 + numerator2;
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
                answer = mixedAns + "/" + newDenom;
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
                answer = mixedAns + "/" + newDenom;
            }
        }
        return true;
    }

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
            answer = mixedAns + "/" + newDenom;
        }


        return true;

    }

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
            answer = mixedAns + "/" + newDenom;
        }


        return true;

    }

}