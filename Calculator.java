

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

//Calculator.java

/*
* This class creates a GUI that acts as a calculator. Number and functions buttons
* appear in JFrame, allowing user to interact with numbers and results.
*
* @author: Stephen Dudeney, stephen.dudeney@gmail.com
* @version: Last modified 04/10/2017
*/

public class Calculator {
    
    final static int NUM_B = 20;                        //number of buttons
    static JFrame jfCalc = new JFrame("Calculator");    //JFrame for Calculator
    static JPanel jpButtons = new JPanel ();            //JPanel of buttons
    static JTextField jpText = new JTextField("0.0");   //JTextField for answers
    static JButton[] calculator = new JButton[NUM_B];   //array of buttons
    
    public static void main(String[] args) {
        
        //initialize GUI
        jfCalc.setSize(500, 600);
        jfCalc.setLayout(new BorderLayout());
        jfCalc.setVisible(true);
        jfCalc.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jpButtons.setLayout(new GridLayout(5, 5, 2, 2));
        jpText.setFont(new Font("Times", Font.BOLD, 45));
        jpText.setHorizontalAlignment(SwingConstants.RIGHT);
        
        //initialize buttons
        init();
        
        //add buttons to GUI
        jfCalc.add(jpButtons, BorderLayout.CENTER);
        jfCalc.add(jpText, BorderLayout.NORTH);
    }
    
    private static void init() {
        
        //define buttons
        String[] calcText = {
            "c", "√", "/", "*",
            "7", "8", "9","-",
            "4", "5", "6", "+",
            "1", "2", "3", "=",
            "0", ".", "+/-", ""
        };
        
        
        for (int i = 0; i < NUM_B - 1; i++) {
               
            calculator[i] = new JButton(calcText[i]);  
            calculator[i].setFont(new Font("Times", Font.BOLD, 24));
            calculator[i].addActionListener(new calClick());
            jpButtons.add(calculator[i]);
            
        }
    }
    
    /*
    * method implements actionlistener, sends button text to CalcBackend
    * and receives double back from backend which is printed to the GUI
    */
    
    static class calClick implements ActionListener {
     
        public void actionPerformed (ActionEvent e) {
            
            String symbol = e.getActionCommand();
            
            //create CalcBackend Object
            CalcBackend operation = new CalcBackend();
            
            //if calculator square root function called, catch exception
            if (symbol.charAt(0) == '√') {
                try {
                    operation.feedChar(symbol.charAt(0));
                } catch (IllegalArgumentException exc) {
                    jpText.setText("ERROR");
                }
                
                if (!jpText.getText().equals("ERROR")) {
                    String value = String.valueOf(operation.getDisplayVal());
                    jpText.setText(value);
                }
            }
            
            //minus function call
            else if (symbol.length() > 1 && symbol.charAt(0) == '+' && symbol.charAt(1) == '/') {
                operation.feedChar('n');
                jpText.setText(String.valueOf(operation.getDisplayVal()));
            }
            
            //send button text to Backend and print response in JTextField
            else {
                operation.feedChar(symbol.charAt(0));
                String value = String.valueOf(operation.getDisplayVal());
                jpText.setText(value);
            }
        }
    }
}
