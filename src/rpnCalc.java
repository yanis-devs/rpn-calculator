import java.util.Scanner;

//---------------------------------------//
//               CLASS-WORK              //
//↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓//

class Node {
    double data;
    Node next;
    Node(double data){
        this.data = data;
        this.next = null;
    }
}

class LinkedList {
    Node head;
    void insert(double data){
        Node newNode = new Node(data);
        if(head == null){
            head = newNode;
            return;
        }
        Node current = head;
        while(current.next != null){
            current = current.next;

        }

        current.next = newNode;
    }

    void insertB(double data){
        Node newNode = new Node(data);
        newNode.next = head;
        head = newNode;
        
    }

    void display(){
        Node current = head;
        while(current != null){
            System.out.print(current.data + " // ");
            current = current.next;
        }
        System.out.println("..............");
    }

    boolean search(double data){
        Node current = head;
        
        while(current != null){
            if(current.data == data){
                System.out.println();
                System.out.println("Found!");
                return true;
                
            } else {
                System.out.println();
                System.out.println("..");
            }
            current = current.next;
        }
        System.out.println("Not found");
        return false;
    }

    void removeFirstNode(){
        if(head != null){
        head = head.next;
        }
    }

    void remove(double data){
        Node current = head;
        while(current.next != null){
            if(current.next.data == data){
                current.next = current.next.next;
            } else {
            current = current.next;
            }
        }
    }
    
    void removeLastNode(){
        Node current = head;

        if (current == null){
        System.out.println("list only has the head // empty list!");
        return;
        }

        if (current.next == null){
            head = null;

            System.out.println("the only node got removed");
            return;
            
        }

        while(current != null){
            if(current.next.next == null){
                current.next = null;
            }
            current = current.next;
        }
        
    }
}



class StackLL{
    Node top;
    StackLL(){
        top = null;
    }

    void push(double value){
        Node newNode = new Node(value);
        newNode.next = top;
        top = newNode;
    }

    double pop(){
        if(top == null){
            return -1;
        }
        double popped = top.data;
        top = top.next;
        return popped;
    }

    double peek(){
        if(top == null){
            System.out.println(C.bR + C.BG + "ERROR! stack is empty" + C.X);
            return Double.NaN;

        }
        double peeked = top.data;
        return peeked;
    }

    boolean isEmpty(){
        return top == null;
    }
}

//------------------------------------------------------------------------------------------------------------------------//
//--------------------------------------------------------END-------------------------------------------------------------//
//------------------------------------------------------------------------------------------------------------------------//



//---------------------------------------//
//            PERSONAL-WORK              //
//            FOR ASSIGNMENT             //
//↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓//




/**
 * Main class: RPN Calculator
 */
public class rpnCalc {
    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in); // scanner object creation
        boolean runs = true; // controls whether the program runs (true) or not (false)


        // UI element: a Welcome message (uses custom colors from class C)
        System.out.println(C.B + C.bBWG + "=======================================" + C.X);
        System.out.println(C.B + C.bBWG + "      Welcome to the RPN Calculator    " + C.X);
        System.out.println(C.B + C.bBWG + "=======================================" + C.X);


        while(runs){

        // UI element: display the options Menu (uses custom colors from class C)
        menu();

        int choice = 0;

        while(true){

            try{
                choice = Integer.parseInt(scan.nextLine().trim()); // attempt parsing the user's input into an integer
                if(choice >= 1 && choice <= 3) break; // valid, leave the loop
                else System.out.print("Please enter a number from 1 to 3: ");
            }
            // if parsing failed
            catch(NumberFormatException e){
                System.out.print("Invalid input! Enter a number from 1 to 3: ");
            }

        }
 // store the user's input in choice, assume user input is an integer 1-3)

        // switch-cases block to process choice

        switch(choice){

            case 1: viewInstructions(); // UI element: display a list of ordered instructions
                break;

            case 2: /** Evaluating the RPN expression:
                     * - prompt user for input using promptForExpression(scan)
                     * - pass it to evaluateRPN()
                     * - store output in result
                     */
                    double result = evaluateRPN(promptForExpression(scan));

                    System.out.println(C.Cn + C.BG + result + C.X); // print the result
                break;

            case 3: scan.close(); // close scan
                    runs = false; //stop the while loop
                break;

            default: System.out.println(C.R + "Invalid! please choose an option from 1-3." + C.X);
                    continue; // jump to the next iteration
                
            }
        }
    }

    //--   Method: evaluate the RPN expression   --//

    static double evaluateRPN(String exp){

        // DECLARING VARIABLES //
        //-----------------------------------------------------------------------------------------------------------------
        String delimeter = ","; // custom delimeter

        StackLL stack = new StackLL(); // create a new stack

        String[] tokens = exp.split(delimeter); // split the user's RPN expression into individual operators and operands

        String[] validOperators = {"+", "-", "/", "*"}; // an array that stores valid operators

        int numCounter = 0; // count how many numbers are in the stack so far

        boolean exceptionOccurred = false; // check if an exception occured

        boolean operatorIsValid = false; // check if the user's provided operator is valid

        boolean divisionIsPossible = true; // check if divison is possible between two numbers
        //-----------------------------------------------------------------------------------------------------------------



        // WRITING THE LOGIC //
        //-----------------------------------------------------------------------------------------------------------------
            // Enhanced for-loop to process each token
            for(String token : tokens){
                
                operatorIsValid = false; //reset flag to default (false)

                try {

                    double number = Double.parseDouble(token); // attempt to parse the token into a double

                    stack.push(number); // push the number to the stack, assume the token got parsed successfully

                    numCounter++; // record the pushed number

                }
                
                // detect if parsing the token failed using NumberFormatException
                catch(NumberFormatException e) {

                    // check if the unparsed token is a valid operator
                    for(String operator : validOperators){

                        if(token.equals(operator)){
                            operatorIsValid = true;
                        }

                    }

                    
                    if(operatorIsValid){

                        exceptionOccurred = true;

                        // check that there are at least two numbers in the stack
                        if(numCounter < 2){

                            System.out.println(C.bR + C.BG + "ERROR! not enough numbers to operate with or invalid order. current token: " + token + C.X);
                            return Double.NaN; // print NaN, the standard undefined double

                        }

                        double a = stack.pop(); // store the top number in a
                        if(a == 0) divisionIsPossible = false;

                        double b = stack.pop(); //store the second top number in b

                        // switch-case block to perform an operation corresponding to user's input
                        switch(token){

                            case "+": stack.push(a + b);
                                    numCounter--;
                                break;

                            case "-": stack.push(b - a);
                                    numCounter--;
                                break;

                            case "/": if(!divisionIsPossible) {
                                        System.out.println(C.bR + C.BG + "ERROR! division by zero is undefined. current token: " + token  + C.X);
                                        return Double.NaN;
                                    }
                                    stack.push(b / a);
                                    numCounter--;
                                break;

                            case "*": stack.push(a * b);
                                    numCounter--;
                                break;

                            }
                        
                    } else {

                    // if the operator is invalid
                    System.out.println(C.bR + C.BG + "ERROR! illegal character or invalid operator in the expression. current token: " + token + C.X);
                    return Double.NaN;

                    }
                }
            }

        

        // check if an operator exists after pushing 2 or more numbers
        if(numCounter >= 2 && exceptionOccurred == false){

            System.out.println(C.bR + C.BG + "ERROR! missing an operator." + C.X);
            return Double.NaN;

        }

        //check if there are leftover numbers in stack, indicating that the expression lacks enough operators
        if(numCounter != 1){

            System.out.println(C.bR + C.BG + "ERROR! insufficient number of operators or empty tokens." + C.X);
            return Double.NaN;
        }
        
        exceptionOccurred = false; // reset flag to default (false)
        
        //return the final result
        return stack.peek();

        //-----------------------------------------------------------------------------------------------------------------
    }

    //--   Method: printing a custom design menu   --//

    static void menu() {
        System.out.println(C.P + "\nPlease choose an option:");
        System.out.println("1. View instructions");
        System.out.println("2. Run calculator");
        System.out.println("3. Exit");
        System.out.print("Enter your choice (1-3): " + C.X);
    }

    //--   Method: printing a custom design instructions panel   --//

    static void viewInstructions() {

        System.out.println(C.B + C.BWG + "=======================================" + C.X);
        System.out.println(C.B + C.BWG + "       RPN Calculator Instructions     " + C.X);
        System.out.println(C.B + C.BWG + "=======================================" + C.X);

        System.out.println(C.G +"1. Enter a mathematical expression in Reverse Polish Notation (RPN).");
        System.out.println("   - Use commas to separate numbers and operators.");
        System.out.println("   - Example: 3,4,+,2,*  (which is equivalent to (3 + 4) * 2)");
        System.out.println("2. Available operators: +, -, *, /");
        System.out.println("3. You can use negative numbers and decimals.");
        System.out.println("4. After entering the expression, the calculator will evaluate it and show the result.");
        System.out.println("5. You can then enter another expression or exit the program.");
        System.out.println("=======================================\n" + C.X);
    }

    //--   Method: prompting user for an RPN expression   --//

    static String promptForExpression(Scanner s) {
        System.out.println("\n=======================================");
        System.out.println("Enter an expression in Reverse Polish Notation (RPN).");
        System.out.print("Your expression: ");
        
        String expression = s.nextLine().trim(); // storing user's input in expression
        return expression;
    }
}


/**
 * Complementary class: Text & Background Colors
 * Imported from an external source
 */
class C {
    // Reset
    public static final String X = "\u001B[0m";

    // Regular colors
    public static final String B = "\u001B[30m"; // black
    public static final String R = "\u001B[31m"; // red
    public static final String G = "\u001B[32m"; // green
    public static final String Y = "\u001B[33m"; // yellow
    public static final String BL = "\u001B[34m"; // blue
    public static final String P = "\u001B[35m"; // purple
    public static final String Cn = "\u001B[36m"; // cyan
    public static final String W = "\u001B[37m"; // white

    // Bright variants
    public static final String bB = "\u001B[90m";
    public static final String bR = "\u001B[91m";
    public static final String bG = "\u001B[92m";
    public static final String bY = "\u001B[93m";
    public static final String bBL = "\u001B[94m";
    public static final String bP = "\u001B[95m";
    public static final String bCn = "\u001B[96m";
    public static final String bW = "\u001B[97m";

    // Background colors
    public static final String BG = "\u001B[40m"; // black bg
    public static final String BRG = "\u001B[41m"; // red bg
    public static final String BGG = "\u001B[42m"; // green bg
    public static final String BYG = "\u001B[43m"; // yellow bg
    public static final String BBLG = "\u001B[44m"; // blue bg
    public static final String BPG = "\u001B[45m"; // purple bg
    public static final String BCnG = "\u001B[46m"; // cyan bg
    public static final String BWG = "\u001B[47m"; // white bg

    // Bright background variants
    public static final String bBG = "\u001B[100m";
    public static final String bBRG = "\u001B[101m";
    public static final String bBGG = "\u001B[102m";
    public static final String bBYG = "\u001B[103m";
    public static final String bBBLG = "\u001B[104m";
    public static final String bBPG = "\u001B[105m";
    public static final String bBCnG = "\u001B[106m";
    public static final String bBWG = "\u001B[107m";
}



//------------------------------------------------------------------------------------------------------------------------//
//--------------------------------------------------------END-------------------------------------------------------------//
//------------------------------------------------------------------------------------------------------------------------//
