package com.company;

import java.util.*;
import java.lang.*;


public class Main {

    public static void main(String[] args) {

        System.out.println("Input an arithmetic expression:");
        Scanner in = new Scanner(System.in);
        String expression = in.nextLine();

        System.out.println(calc(expression));

    }

    public static String calc(String input){
        int a = 0, b = 0;
        double a_d, b_d;
        String operation;
        String[] arithmetic_op = {"+", "-", "*", "/"};
        String[] unitates = {"I", "II", "III", "IV", "V",
                "VI", "VII", "VIII", "IX", "X"};
        String[] dozens = {"X", "XX", "XXX", "XL", "L",
                "LX", "LXX", "LXXX", "XC", "C"};

        String[] strs;
        try{
            strs = input.split("\\s");
            if (strs.length != 3) {
                throw new Exception("\nINCORRECT INPUT:" +
                        "\nstring is not a mathematical operation" +
                        "\nor" +
                        "\nthe format of the mathematical operation " +
                        "does not satisfy the task - " +
                        "\ntwo operands and one operator (+, -, /, *)");
            }
        } catch(Exception ex){
            return ex.getMessage();
        }
        try{
            operation = strs[1];
            boolean temp = false;
            for (String s : arithmetic_op) {
                if (operation.equals(s)) {
                    temp = true;
                    break;
                }
            }
            if(!temp) {
                throw new Exception("\nINCORRECT INPUT:" +
                        "\nThe calculator can perform " +
                        "addition, subtraction, multiplication and division operations");
            }
        }catch(Exception ex) {
            return ex.getMessage();
        }

        boolean isFilled = false;
        boolean get_mes = false;
        boolean isRoman1 = false, isRoman2 = false;
        try{
            a_d = Double.parseDouble(strs[0]);
            b_d = Double.parseDouble(strs[2]);
            if((a_d % 1 == 0)&&(b_d % 1 == 0)){
                a = (int)a_d;
                b = (int)b_d;
                isFilled = true;
            }else {
                get_mes = true;
                throw new Exception("\nThe calculator can only work with integers.");
            }
        } catch(Exception ex){
            if(get_mes) return ex.getMessage();
        }

        if(!isFilled) {
            try {
                for (int i = 0; i < unitates.length; ++i) {
                    if (strs[0].equals(unitates[i])) {
                        a = i + 1;
                        isRoman1 = true;
                    }
                    if (strs[2].equals(unitates[i])) {
                        b = i + 1;
                        isRoman2 = true;
                    }
                }
                if ((isRoman1 && !isRoman2) || (!isRoman1 && isRoman2)) {
                    throw new Exception("\nINCORRECT INPUT:" +
                            "\ndifferent number systems are used at the same time");
                }
            } catch (Exception ex) {
                return ex.getMessage();
            }
        }

        if ((a > 0 && a < 11) && (b > 0 && b < 11)) {
            int result = 0;
            switch (operation) {
                case "+":
                    result = a + b;
                    break;
                case "-":
                    result = a - b;
                    break;
                case "*":
                    result = a * b;
                    break;
                case "/":
                    result = a / b;
                    break;
            }
            if (!isRoman1 && !isRoman2) return String.valueOf(result);
            else{
                if(result < 1) {
                    return "\nthe result is a negative number (or zero)" +
                            "\nthere are no negative numbers (and zero) in the roman system";
                }
                int ones = result % 10;
                int tens = result / 10;
                String onestr = "";
                String tenstr = "";
                if(ones > 0) onestr = unitates[ones-1];
                if(tens > 0) tenstr = dozens[tens-1];
                return (tenstr + onestr);
            }
        } else return "\nThe calculator must accept input numbers" +
                " from 1 to 10 inclusive, no more.";
    }
}
