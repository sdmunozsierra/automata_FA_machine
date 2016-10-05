package automata_FA_machine;
//imports 	
import java.util.Scanner;

public class main {
	public static void main(String args[]) {
		
		start(); //call in start method
	
	}// end main

	/** Methods */
	/* Start method*/
	public static void start() {
		//Declare scanner and variables
		Scanner scan = new Scanner(System.in);
		int n = -1; // number of states
		int i = 0; // index will cycle trough the program
		String answer; // used to answer y/n questions
		boolean done = false; // has it finished?
		
		//Ask user for number of states, has to be positive
		while (n < 0) {
			System.out.println("Enter number of states"); // ask the user for n
			n = scan.nextInt();
		} // end while
		
		//Create an array of accepted final states
		boolean accepted[] = new boolean[n]; // array of final states
		
		//Ask for which are final states
		while (n > i) {
			System.out.println("Is state q" + (i+1) + " a final state?\ny/n");
			answer = scan.next(); //scan for answer
			char anschar = answer.charAt(0);
			if (anschar == 'y') 
				accepted[i] = true;
			else if (anschar == 'n')
				accepted[i] = false;
			else {
				System.out.println("Please input y or n");
			}
			i++; // Increment i
		} // end while
		
		//Number of inputs e.g. 2 for binary (0 or 1)
		System.out.println("Please input number of symbols");
		int s = scan.nextInt();
		//Create an array containing the directions of 
		int delta[][] = new int[s][n]; // rows are number of symbols, columns number of states
		int dest = -1; // using a new variable to make everything simpler
		// ask for every possibility
		for (int x = 0; x < delta.length; x++) {  //Two rows, either 0 or 1
			for (int y = 0; y < delta[x].length; y++) {  //N number of columns
				System.out.println("In state q" + (y+1) + " if input is a " + x + " it goes to state..");
				dest = scan.nextInt(); // must be positive
				delta[x][y] = (dest-1); //decrement one because user sees states starting from 1
			}
		} // end double loop
		//Check information mainly for debugging
		infoFinal(accepted); //final states	
		System.out.println(); 
		infoMachine(delta); //information
		
		//Ask the user for expressions
		while (done == false) {  //while !done
			//declare variables
			int state = 0; //start with state 0 user will see 1
			System.out.println("Enter the expression to check");
			String exp = scan.next(); //expression to check
			
			// cycle through array
			for (int j = 0; j < exp.length(); j++) {
				//System.out.println("CurrChar: "+exp.charAt(j)); //debugging
				//System.out.println("CurrState: "+state);		  //debugging
				state = delta[exp.charAt(j)-48][state];
			}//end for
			
			//results
			if (accepted[state]) //is the current state final?
				System.out.println("The string " + exp + " is accepted");
			else { 
				System.out.println("The string " + exp + " is not accepted");
			} // end else
			
			//continue?
			System.out.println("\nWant to quit? y/n");
			//simplify plz
			answer = scan.next(); //scan for answer
			char anschar = answer.charAt(0);
			if (anschar == 'y') {
				done = true;
				System.out.println("exiting..");
			}
			else if (anschar == 'n')
				done = false;
			else { 
				System.out.println("Please input y or n");
			}
			
		} // end while done
	}// end start
	
	/* Prints information about w */
	public static void infoMachine(int[][] array){
		for (int i = 0; i < array.length; i++) {
			for (int j = 0; j < array[i].length; j++) {
				System.out.println("State q"+(i+1)+" --"+j+"--> q"+(array[i][j]+1));
			}//end for
			System.out.println();
		}//end for
	}//end printInfo
	/* Prints final states */
	public static void infoFinal(boolean[] array){
		System.out.println("Final states in this machine:\n ");
		for (int i = 0; i < array.length; i++) {
			if(array[i]==true) 
				System.out.println("State q"+(i+1)+" is final");
		}//end for
	}//end finalStates

}// end class
