import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;
public class HanoiSteps{
	static BufferedWriter writer; //declare static BufferedReader
	static BufferedReader reader; //declare static BufferedWriter
	static String stepOfHanoi;
	
	static void hanoiStepByStep(int n, char left, char right, char middle) throws IOException{
		if (n == 0) { // base case: returns to previous recursion call if there are no disks in function call
			return;
		}
		else {
			/* shift all the disks before the largest disk from the leftmost rod 
			 * to the middle rod to relocate the largest disk to the rightmost rod */
			hanoiStepByStep(n-1, left, middle, right); 
			writer.write("Disk " + n + " moved from " + left + " to " + right); //write the step by step process of the puzzle into a file
			writer.newLine();
			/* shift all relocated disks from the middle rod to the rightmost rod
			 * to complete the problem */
			hanoiStepByStep(n-1, middle, right, left);
		}
	}
	
	public static void main(String[] args) {
		
		int disks = 0;
		Scanner input = new Scanner(System.in);
		do {
			try {
				writer = new BufferedWriter(new FileWriter("Hanoi_Steps.txt")); //instantiated BufferedWriter object
				reader = new BufferedReader(new FileReader("Hanoi_Steps.txt")); //instantiated BufferedReader object
				System.out.print("Enter number of disks (1-9): "); //asks user for input
				disks = input.nextInt(); 
				if(disks < 1 || disks > 9) //limits input from one to nine disks
					System.out.println("Input out of expected range of values. Try Again!");
				else {
					hanoiStepByStep(disks, 'A', 'C', 'B'); //recursive function call by passing the number of disks and names of rods as arguments
					writer.close();
					System.out.println("\nSteps: ");
					while((stepOfHanoi = reader.readLine()) != null) { // print out the contents of the file
						System.out.println(stepOfHanoi);
					}
					reader.close();
					input.close();
				}
			} catch (IOException e) { 
				e.printStackTrace();
			} catch (InputMismatchException e) { // catches any non-integer inputs, prints out error message, and reprompt for user input
				System.out.println("Invalid Input. This program only accepts integer inputs. Try Again!");
				input.next();
			}
		}while(disks < 1 || disks > 9);
	}
}
