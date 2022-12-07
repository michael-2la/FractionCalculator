import java.util.Scanner;
public class FracCalc {
    public static void main(String[] args)
    {
        // TODO: Read the input from the user and call produceAnswer with an equation
        Scanner input = new Scanner(System.in);
        while (!input.equals("quit")) {
            System.out.println("Enter two fractions: ");
            String fraction = input.nextLine();
            if (fraction.equals("quit")) {
                break;
            }
            System.out.println(produceAnswer(fraction));
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
    public static String produceAnswer(String input) {
        // TODO: Implement this function to produce the solution to the input
        Scanner fraction = new Scanner(input);
        fraction.useDelimiter(" ");
        String fraction1 = fraction.next();
        String operator = fraction.next();
        String fraction2 = fraction.next();

        // scanner to parse fraction 1
        Scanner parser1 = new Scanner(fraction1);
        int wholeNum1 = 0;
        if (fraction1.indexOf("_") >= 0) { //test if mixed fraction
            parser1.useDelimiter("_");
            wholeNum1 = Integer.parseInt(parser1.next());
            //update fraction1 to remove #_
            fraction1 = fraction1.substring(fraction1.indexOf("_") + 1);
        }
        // scanner to update fraction 1 with just fraction part so without whole number
        Scanner parserOne = new Scanner(fraction1);
        int numerator1 = 0;
        int denominator1 = 1;
        if (fraction1.indexOf("/") >= 0) {
            parserOne.useDelimiter("/");
            numerator1 = Integer.parseInt(parserOne.next());
            denominator1 = Integer.parseInt(parserOne.next());
        } else {
            wholeNum1 = Integer.parseInt(fraction1);
        }
        int improperNumerator1 = (wholeNum1 * denominator1) + numerator1;
        int improperDenominator1 = denominator1;

        // scanner to parse fraction 2
        Scanner parser2 = new Scanner(fraction2);
        int wholeNum2 = 0;
        if (fraction2.indexOf("_") >= 0) {
            parser2.useDelimiter("_");
            wholeNum2 = parser2.nextInt();
            fraction2 = fraction2.substring(fraction2.indexOf("_") + 1);
        }
        // scanner to update fraction 2 with just fraction part so without whole number
        Scanner parserTwo = new Scanner(fraction2);
        int numerator2 = 0;
        int denominator2 = 1;
        if (fraction2.indexOf("/") >= 0) {
            parserTwo.useDelimiter("/");
            numerator2 = parserTwo.nextInt();
            denominator2 = parserTwo.nextInt();
        } else {
            wholeNum2 = Integer.parseInt(fraction2);
        }
        int improperNumerator2 = (wholeNum2 * denominator2) + numerator2;
        int improperDenominator2 = denominator2;


        // add function
        String solution = null;
        if (operator.equals("+")) {
            if (wholeNum1 < 0) {
                improperNumerator1 = (wholeNum1 * denominator1) - numerator1;
            }
            if (wholeNum2 < 0) {
                improperNumerator2 = (wholeNum2 * denominator2) - numerator2;
            }
            int sumDenominator = denominator1 * denominator2;
            int sumNumerator1 = improperNumerator1 * denominator2;
            int sumNumerator2 = improperNumerator2 * denominator1;
            int sumNumerator = sumNumerator1 + sumNumerator2;
            solution = sumNumerator + "/" + sumDenominator;

            // divide by 1
            if (sumDenominator == 1) {
                solution = Integer.toString(sumNumerator);
            }

            // numerator is 0
            if (sumNumerator == 0) {
                solution = Integer.toString(sumNumerator);
            }

            int highestMultiple = 1;
            if (Math.abs(sumNumerator) < Math.abs(sumDenominator)) {
                for (int multiple = Math.max(sumNumerator, sumDenominator); multiple > 1; multiple--) {
                    if (sumNumerator % multiple == 0 && sumDenominator % multiple ==0) {
                        highestMultiple = multiple;
                        break;
                    }
                }
            }
            sumNumerator = sumNumerator / highestMultiple;
            sumDenominator = sumDenominator / highestMultiple;

            // simplify
            if (Math.abs(sumNumerator) >= sumDenominator) {
                int sumMod = sumNumerator % sumDenominator;
                int sumSimplify = sumNumerator / sumDenominator;
                if (sumMod == 0) {
                    solution = Integer.toString(sumSimplify);
                }
                else {
                    sumNumerator = sumMod;
                    int sumWhole = sumSimplify;
                    if ((sumDenominator % sumNumerator) == 0) {
                        sumDenominator = sumDenominator / sumNumerator;
                        sumNumerator = sumDenominator / sumNumerator;
                    }
                    for (int multiple = 2; multiple < Math.abs(sumDenominator);multiple++) {
                        while (sumNumerator % multiple == 0 && sumDenominator % multiple ==0) {
                            sumNumerator /= multiple;
                            sumDenominator /= multiple;
                        }
                    }
                    // num and den both neg
                    if (sumNumerator < 0 && sumDenominator < 0) {
                        sumNumerator = Math.abs(sumNumerator);
                        sumDenominator = Math.abs(sumDenominator);
                    }
                    if (sumWhole < 0) {
                        sumNumerator = Math.abs(sumNumerator);
                        sumDenominator = Math.abs(sumDenominator);
                    }
                    solution = sumWhole + "_" + sumNumerator + "/" + sumDenominator;
                }
            }
            else{ //if no whole part
                solution = sumNumerator + "/" + sumDenominator;
            }
        }

        // subtract function
        if (operator.equals("-")) {
            if (wholeNum1 < 0) {
                improperNumerator1 = (wholeNum1 * denominator1) - numerator1;
            }
            if (wholeNum2 < 0) {
                improperNumerator2 = (wholeNum2 * denominator2) - numerator2;
            }
            int diffDenominator = denominator1 * denominator2;
            int diffNumerator1 = improperNumerator1 * denominator2;
            int diffNumerator2 = improperNumerator2 * denominator1;
            int diffNumerator = diffNumerator1 - diffNumerator2;
            solution = diffNumerator + "/" + diffDenominator;

            // divide by 1
            if (diffDenominator == 1) {
                solution = Integer.toString(diffNumerator);
            }

            int highestMultiple = 1;
            if (Math.abs(diffNumerator) < Math.abs(diffDenominator)) {
                for (int multiple = Math.max(diffNumerator, diffDenominator); multiple > 1; multiple--) {
                    if (diffNumerator % multiple == 0 && diffDenominator % multiple ==0) {
                        highestMultiple = multiple;
                        break;
                    }
                }
            }
            diffNumerator = diffNumerator / highestMultiple;
            diffDenominator = diffDenominator / highestMultiple;

            // simplify
            if (Math.abs(diffNumerator) >= diffDenominator) {
                int diffMod = diffNumerator % diffDenominator;
                int diffSimplify = diffNumerator / diffDenominator;
                if (diffMod == 0) {
                    solution = Integer.toString(diffSimplify);
                }
                else {
                    diffNumerator = diffMod;
                    int diffWhole = diffSimplify;
                    if ((diffDenominator % diffNumerator) == 0) {
                        diffDenominator = diffDenominator / diffNumerator;
                        diffNumerator = diffDenominator / diffNumerator;
                    }
                    for (int multiple = 2; multiple < Math.abs(diffDenominator); multiple++) {
                        while (diffNumerator % multiple == 0 && diffDenominator % multiple ==0) {
                            diffNumerator /= multiple;
                            diffDenominator /= multiple;
                        }
                    }

                    // num and den both neg
                    if (diffNumerator < 0 && diffDenominator < 0) {
                        diffNumerator = Math.abs(diffNumerator);
                        diffDenominator = Math.abs(diffDenominator);
                    }
                    if (diffWhole < 0) {
                        diffNumerator = Math.abs(diffNumerator);
                        diffDenominator = Math.abs(diffDenominator);
                    }
                    solution = diffWhole + "_" + diffNumerator + "/" + diffDenominator;
                }
            }
            else{ //if no whole part
                solution = diffNumerator + "/" + diffDenominator;
            }

            // numerator is 0
            if (diffNumerator == 0) {
                solution = Integer.toString(diffNumerator);
            }
        }

        // multiply function
        if (operator.equals("*")) {
            if (wholeNum1 < 0) {
                improperNumerator1 = (wholeNum1 * denominator1) - numerator1;
            }
            if (wholeNum2 < 0) {
                improperNumerator2 = (wholeNum2 * denominator2) - numerator2;
            }
            int productDenominator = denominator1 * denominator2;
            int productNumerator = improperNumerator1 * improperNumerator2;
            solution = productNumerator + "/" + productDenominator;

            // divide by 1
            if (productDenominator == 1) {
                solution = Integer.toString(productNumerator);
            }

            // numerator is 0
            if (productNumerator == 0) {
                solution = Integer.toString(productNumerator);
            }

            int highestMultiple = 1;
            if (Math.abs(productNumerator) < Math.abs(productDenominator)) {
                for (int multiple = Math.max(productNumerator, productDenominator); multiple > 1; multiple--) {
                    if (productNumerator % multiple == 0 && productDenominator % multiple ==0) {
                        highestMultiple = multiple;
                        break;
                    }
                }
            }
            productNumerator = productNumerator / highestMultiple;
            productDenominator = productDenominator / highestMultiple;

            // simplify
            if (Math.abs(productNumerator) >= productDenominator) {
                int productMod = productNumerator % productDenominator;
                int productSimplify = productNumerator / productDenominator;
                if (productMod == 0) {
                    solution = Integer.toString(productSimplify);
                }
                else {
                    productNumerator = productMod;
                    int productWhole = productSimplify;
                    if ((productDenominator % productNumerator) == 0) {
                        productDenominator = productDenominator / productNumerator;
                        productNumerator = productDenominator / productNumerator;
                    }
                    for (int multiple = 2; multiple < Math.abs(productDenominator); multiple++) {
                        while (productNumerator % multiple == 0 && productDenominator % multiple ==0) {
                            productNumerator /= multiple;
                            productDenominator /= multiple;
                        }
                    }
                    // num and den both neg
                    if (productNumerator < 0 && productDenominator < 0) {
                        productNumerator = Math.abs(productNumerator);
                        productDenominator = Math.abs(productDenominator);
                    }
                    if (productWhole < 0) {
                        productNumerator = Math.abs(productNumerator);
                        productDenominator = Math.abs(productDenominator);
                    }
                    solution = productWhole + "_" + productNumerator + "/" + productDenominator;
                }
            }

        }

        // divide function
        if (operator.equals("/")) {
            if (wholeNum1 < 0) {
                improperNumerator1 = (wholeNum1 * denominator1) - numerator1;
            }
            if (wholeNum2 < 0) {
                improperNumerator2 = (wholeNum2 * denominator2) - numerator2;
            }
            int quotientNumerator2 = denominator2;
            int quotientDenominator2 = improperNumerator2;
            int quotientDenominator = denominator1 * quotientDenominator2;
            int quotientNumerator = improperNumerator1 * quotientNumerator2;
            solution = quotientNumerator + "/" + quotientDenominator;

            // divide by 1
            if (quotientDenominator == 1) {
                solution = Integer.toString(quotientNumerator);
            }

            // numerator is 0
            if (quotientNumerator == 0) {
                solution = Integer.toString(quotientNumerator);
            }

            int highestMultiple = 1;
            if (Math.abs(quotientNumerator) < Math.abs(quotientDenominator)) {
                for (int multiple = Math.max(quotientNumerator, quotientDenominator); multiple > 1; multiple--) {
                    if (quotientNumerator % multiple == 0 && quotientDenominator % multiple ==0) {
                        highestMultiple = multiple;
                        break;
                    }
                }
            }
            quotientNumerator = quotientNumerator / highestMultiple;
            quotientDenominator = quotientDenominator / highestMultiple;

            // simplify
            if (Math.abs(quotientNumerator) >= Math.abs(quotientDenominator)) {
                int quotientMod = quotientNumerator % quotientDenominator;
                int quotientSimplify = quotientNumerator / quotientDenominator;
                if (quotientMod == 0) {
                    solution = Integer.toString(quotientSimplify);
                }
                else {
                    quotientNumerator = quotientMod;
                    int quotientWhole = quotientSimplify;
                    if ((quotientDenominator % quotientNumerator) == 0) {
                        quotientDenominator = quotientDenominator / quotientNumerator;
                        quotientNumerator = quotientNumerator / quotientNumerator;
                    }
                    for (int multiple = 2; multiple < Math.abs(quotientDenominator); multiple++) {
                        while (quotientNumerator % multiple == 0 && quotientDenominator % multiple ==0) {
                            quotientNumerator /= multiple;
                            quotientDenominator /= multiple;
                        }
                    }
                    if (quotientNumerator < 0 && quotientDenominator < 0) {
                        quotientNumerator = Math.abs(quotientNumerator);
                        quotientDenominator = Math.abs(quotientDenominator);
                    }
                    if (quotientWhole < 0) {
                        quotientNumerator = Math.abs(quotientNumerator);
                        quotientDenominator = Math.abs(quotientDenominator);
                    }
                    if (quotientDenominator < 0) {
                        quotientNumerator = quotientNumerator * -1;
                    }
                    solution = quotientWhole + "_" + quotientNumerator + "/" + quotientDenominator;
                }
            }
        }
        return solution;
    }
    // TODO: Fill in the space below with any helper methods that you think you will need
}