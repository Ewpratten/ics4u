package binaryhex;

import javax.swing.JOptionPane;

/**************************************************************************************************************
 * This program is a launcher for various small sub programs to do various
 * binary and hexadecimal conversions It also demonstrates how to use
 * JOptionPane
 ***************************************************************************************************************/

public class App {
    public static void main(String[] args) {

        String name = JOptionPane.showInputDialog(null, "What's your name?", "Welcome to My OptionPane",
                JOptionPane.ERROR_MESSAGE);

        // handle CANCEL option
        if (name == null) {
            System.exit(0);
        }

        Object[] options = { "B to D", "D to B", "B to H", "H to B", "H to D", "D to H", "Exit" };

        boolean keepLooping = true;
        while (keepLooping) {
            int m = JOptionPane.showOptionDialog(null, "Please choose a program to run", "Binary and Hex conversions",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);

            switch (m) { // problem: the numbers and functions called MUST match the order of the options
                         // in the array above.
            case 0:
                BtoD();
                break;
            case 1:
                DtoB();
                break;
            case 2:
                BtoH();
                break;
            case 3:
                HtoB();
                break;
            case 4:
                HtoD();
                break;
            case 5:
                DtoH();
                break;
            default:
                keepLooping = false;
                break;
            }
        }
    } // end of main

    static void BtoD() {
        String binary = JOptionPane.showInputDialog(null, "Please enter a binary number", "Binary to Decimal",
                JOptionPane.INFORMATION_MESSAGE);
        int decimal = 0;
        int len = binary.length();
        // handle CANCEL option
        if (binary == null)
            return;

        // do the conversion
        for (int i = len - 1; i >= 0; i--) {
            if (binary.charAt(i) == '1')
                decimal += Math.pow(2, len - 1 - i);
            else if (binary.charAt(i) != '0') {
                JOptionPane.showMessageDialog(null, "Error. You can only enter 1s and 0s", "Binary conversion error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }
            // System.out.printf("B=%s,c=%c i=%d, total=%d\n",binary, binary.charAt(i), i,
            // decimal);
        }
        JOptionPane.showMessageDialog(null, binary + " converted to decimal is " + decimal,
                "Binary conversion complete", JOptionPane.INFORMATION_MESSAGE);

    }

    /**
     * Convert a u_int8 to a binary string
     * 
     * Bigger numbers are not allowed in order to reduce processing times (and this
     * is only for demonstration)
     * 
     * @param x 8-bit int
     * @return Binary string
     */
    static String toBin(int x) {
        // Handle the number 0
        if (x == 0) {
            return "0";
        }

        // Create holder for binary. 8 bit max
        char binary[] = new char[8];

        int index = 0;
        while (x > 0) {
            binary[index++] = (char) ('0' + (x % 2));
            x = x / 2;
        }

        return new String(binary);

    }

    static void DtoB() {
        String input = JOptionPane.showInputDialog(null, "Please enter a decimal number (max 255)", "Decimal to Binary",
                JOptionPane.INFORMATION_MESSAGE);

        // Handle null inputs
        if (input == null) {
            return;
        }

        // Parse to int
        int decimal = Integer.parseInt(input);

        // Handle incorrect bounds
        if (decimal < 0 || decimal > 255) {
            JOptionPane.showMessageDialog(null,
                    "Your number is not between 0 and 255. This program only accepts 8-bit ints in order to keep the processing time low.",
                    "Binary conversion failed", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        // Convert to bin
        String binary = toBin(decimal);

        JOptionPane.showMessageDialog(null, decimal + " converted to binary is " + binary, "Binary conversion complete",
                JOptionPane.INFORMATION_MESSAGE);
    }

    static void BtoH() {
    }

    static void HtoB() {
    }

    static void DtoH() {
    }

    static void HtoD() {
    }
} // end of class