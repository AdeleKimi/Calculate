package Calculate;

import javax.swing.plaf.basic.BasicInternalFrameTitlePane;
import java.util.Locale;


public class LogicClass {


    public static void run() {
        boolean isArabic = false;
        String firstNum = "null";
        String secondNum;
        char sign = '\u0000';
        StringBuffer tmp = new StringBuffer();
        String getLine = ReadData.getLine();
        char[] symbols = parsExpression(getLine);
        for (int i = 0; i < symbols.length; i++) {
            if ((symbols[i] == '+') | (symbols[i] == '-') | (symbols[i] == '*') | (symbols[i] == '/')) {
                sign = symbols[i];
                firstNum = String.valueOf(tmp);
                tmp.setLength(0);
            } else {
                tmp.append(symbols[i]);
            }
        }
        secondNum = String.valueOf(tmp);
        if (sign == '\u0000') {
            try {
                throw new MyException();
            } catch (MyException e) {
            }
        }

        int n = 0;
        int m = 0;
        int result = 0;


        if (checkForRim(firstNum, secondNum)) {
            n = Integer.parseInt(firstNum);
            m = Integer.parseInt(secondNum);
        } else {
            if (checkForArabic(firstNum, secondNum)) {
                n = ArabicNumerals.fromArabic(firstNum);
                m = ArabicNumerals.fromArabic(secondNum);
                isArabic = true;
            } else {
                try {
                    throw new Exception();
                } catch (Exception e) {}
            }
        }
        if ((n >= 1 & n <= 10) & (m >= 1 & m <= 10)) {
            if (sign == '+') {
                result = MathExpression.sum(n, m);
            } else {
                if (sign == '-') {
                    result = MathExpression.diff(n, m);
                } else {
                    if (sign == '*') {
                        result = MathExpression.multi(n, m);
                    } else {
                        if (sign == '/') {
                            result = MathExpression.div(n, m);
                        } else {
                            return;

                        }

                    }
                }
            }
            if (isArabic) {
                if (result < 0) {
                    result = Math.abs(result);
                    System.out.println("-" + ArabicNumerals.toArabic(result));
                } else {
                    System.out.println(ArabicNumerals.toArabic(result));
                }
            } else {
                System.out.println(result);
            }


        } else {
            try {
                throw new Exception();
            } catch (Exception e) {
            }
        }


    }

    private static char[] parsExpression(String expression) {
        String text = expression.toUpperCase();
        String tmp = text.replaceAll("\\s+", "");
        char[] symbols = new char[tmp.length()];
        for (int i = 0; i < symbols.length; i++) {
            symbols[i] = tmp.charAt(i);

        }

        return symbols;
    }

    private static boolean checkForRim(String first, String second) {
        boolean firstCheck = false;
        boolean secondCheck = false;
        try {
            Integer.parseInt(first);
            firstCheck = true;
        } catch (NumberFormatException e) {
            // firstCheck = false;
        } catch (NullPointerException e) {}

        try {
            Integer.parseInt(second);
            secondCheck = true;
        } catch (NumberFormatException e) {
            // secondCheck = false;
        } catch (NullPointerException e) {}

        if (firstCheck & secondCheck)
            return true;
        else
            return false;
    }

    private static boolean checkForArabic(String first, String second) {
        boolean firstCheck = false;
        boolean secondCheck = false;

        try {
            Integer.parseInt(first);
            firstCheck = false;
        } catch (NumberFormatException e) {
            firstCheck = true;
        } catch (NullPointerException e) {
            System.out.println("что-то, пошло не так, попробуйте снова с корректными данными");
        }

        try {
            Integer.parseInt(second);
            secondCheck = false;
        } catch (NumberFormatException e) {
            secondCheck = true;
        } catch (NullPointerException e) {
            System.out.println("что-то, пошло не так, попробуйте снова с корректными данными");
        }

        if (firstCheck & secondCheck)
            return true;
        else
            return false;
    }
}
