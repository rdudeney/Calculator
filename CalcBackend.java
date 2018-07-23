

import java.math.*;

// Implements calculator math logic in this class
public class CalcBackend {
    
    private static double currentValue = 0;     //currentValue of double
    private static String stringCurr = "";      //stores digits as added
    private static char operator = '0';         //stores current operator
    private static boolean decimal = false;     //confirms if decimal pressed
    private static double value1 = 0;           //first value
    private static double value2 = 0;           //value after operator
    private static boolean equals = false;      //confirms operator as equals
    

    public CalcBackend() {}

    // Tells the backend that a button has been pushed
    public void feedChar(char c)
    {
        //processes digit depending on current state of backend
        if (c >= '0' && c <= '9') {
        
            if (operator == '0' && !decimal) {
                stringCurr += c;
                value1 = Double.parseDouble(stringCurr);
                currentValue = value1;
            }
            
            else if (operator == '0' && decimal) {
                stringCurr += c;
                value1 = Double.parseDouble(stringCurr);
                currentValue = value1;
            }
            
            else if (operator != '0' && !decimal) {
                stringCurr += c;
                value2 = Double.parseDouble(stringCurr);
                currentValue = value2;
            }
            
            else if (operator != '0' && decimal) {
                stringCurr += c;
                value2 = Double.parseDouble(stringCurr);
                currentValue = value2;
            }
        }
        
        //process operator char
        if (c == '+' || c == '-' || c == '*' || c == '/') {
            
            //sets equals to true if user attempts to utilize operator as equals
            if (value1 != 0 && value2 != 0) {
                equals = true;
            }
            
            //switch operator depending on key clicked
            if (value1 != 0 && value2 == 0) {
                operator = c;
                stringCurr = "";
                decimal = false;
            }
            
            //if operator as equals, define state of backend
            if (equals) {
            
                if (operator == '+') {
                    currentValue = value1 + value2;
                    stringCurr = "";
                    operator = c;
                    decimal = false;
                    value1 = currentValue;
                    value2 = 0;
                }
                
                else if (operator == '-') {
                    currentValue = value1 - value2;
                    stringCurr = "";
                    decimal = false;
                    operator = c;
                    value1 = currentValue;
                    value2 = 0;
                }
                
                else if (operator == '*') {
                    currentValue = value1 * value2;
                    stringCurr = "";
                    decimal = false;
                    operator = c;
                    value1 = currentValue;
                    value2 = 0;
                }
                
                else if (operator == '/') {
                    currentValue = value1 / value2;
                    stringCurr = "";
                    decimal = false;
                    operator = c;
                    value1 = currentValue;
                    value2 = 0;
                }
            }
        }
        
        //process square root char
        if (c == '√') {
            
            //throw excpetion if either digits are negative
            if (value1 != 0 && value2 == 0) {
                
                if (value1 < 0) {
                    throw new IllegalArgumentException();   
                }
                
                else {
                    value1 = Math.sqrt(value1);
                    currentValue = value1;
                }
            }
            
            else if (value2 != 0) {
                
                if (value2 < 0) {
                    throw new IllegalArgumentException();
                }
                else {
                    value2 = Math.sqrt(value2);
                    currentValue = value2;
                }
            }
        }
        
        //clear screen, backend returned to initial state
        if (c== 'c') {
            currentValue = 0;
            stringCurr = "";
            operator = '0';
            decimal = false;
            value1 = 0;
            value2 = 0;
        }
        
        //if decimal char received, confirm no further decimals added
        if (c == '.') {
            
            if (operator == '0' && !decimal) {
                stringCurr += '.';
                decimal = true;
            }
            
            else if (!decimal) {
                stringCurr += '.';
                decimal = true;
            }
        }
        
        //update backed if equals char received
        if (c == '=') {
            
            if (operator == '+') {
                //BigDecimal addition = new BigDecimal(value1 + value2);
                currentValue = value1 + value2;
                operator = '0';
                stringCurr = "";
                decimal = false;
                equals = false;
                value1 = currentValue;
                value2 = 0;
            }
                
            else if (operator == '-') {
                //BigDecimal subtraction = new BigDecimal(value1 - value2);
                currentValue = value1 - value2;
                operator = '0';
                stringCurr = "";
                decimal = false;
                equals = false;
                value1 = currentValue;
                value2 = 0;
            }
                
            else if (operator == '*') {
                //BigDecimal multiplication = new BigDecimal(value1 * value2);
                currentValue = value1 * value2;
                operator = '0';
                stringCurr = "";
                decimal = false;
                equals = false;
                value1 = currentValue;
                value2 = 0;
            }
                
            else if (operator == '/') {
                //BigDecimal division = new BigDecimal(value1 / value2);
                currentValue = value1 / value2;
                operator = '0';
                stringCurr = "";
                decimal = false;
                equals = false;
                value1 = currentValue;
                value2 = 0;
            }
            
            else if (operator == '0') {
                stringCurr = "";
                decimal = false;
                equals = false;
                value1 = 0;
                value2 = 0;
            }
        }
        
        //update backend if user presses negation button
        if (c == 'n') {
            
            if (operator == '0' && value1 != 0) {
                value1 *= -1;
                currentValue = value1;
            }
            
            else if (value2 != 0) {
                value2 *= -1;
                currentValue = value2;
            }
        }
    }

    // Asks the backend what number should be displayed
    public double getDisplayVal()
    {
        return currentValue;
    }

}

