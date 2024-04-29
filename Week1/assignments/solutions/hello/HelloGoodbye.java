/******************************************************************************
 *  Compilation:  javac HelloGoodbye.java
 *  Execution:    java HelloGoodbye Kevin Bob
 *
 *  Takes two names as command-line arguments and prints hello and goodbye
 *  messages as shown below (with the names for the hello message in the
 *  same order as the command-line arguments and with the names for the
 *  goodbye message in reverse order)
 *
 *  % java HelloGoodbye Kevin Bob
 *  Hello Kevin and Bob.
 *  Goodbye Bob and Kevin.
 *
 *
 ******************************************************************************/

public class HelloGoodbye {

    public static void main(String[] args) {

        // Prints "Hello Kevin and Bob." and "Goodbye Bob and Kevin." in the terminal window.
        System.out.println("Hello " + args[0] + " and " + args[1] + ".");
        System.out.println("Goodbye " + args[1] + " and " + args[0] + ".");
    }

}