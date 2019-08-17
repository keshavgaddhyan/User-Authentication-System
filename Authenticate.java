import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;
import org.json.simple.parser.ParseException;


/**
 * @author keshavgaddhyan
 * This program contains the main function and controls the basic flow of the code
 * it is the subclass of User class which contains all the implementation of the functions
 */

public class Authenticate extends User {

	
		/**
		 * @param args
		 * @throws NoSuchAlgorithmException
		 * @throws IOException
		 * @throws ParseException
		 * 
		 * handles the flow of program
		 */
		
		public static void main(String[] args) throws NoSuchAlgorithmException, IOException, ParseException {
        @SuppressWarnings("resource")
		Scanner scan=new Scanner(System.in);
		int choice=1;
		System.out.println("Welcome to the COMP2396 Authentication system!");
		System.out.println("1. Authenticate user");
		System.out.println("2. Add user record");
		System.out.println("3. Edit user record");
		System.out.println("4. Reset user password");
		System.out.println("What would you like to perform?");
		read();
		while(choice!=0)
		{
			System.out.print("Please enter your command (1-4, or 0 to terminate the system): ");
			choice=scan.nextInt();
			
			switch (choice)
			{
			
			case 0: write();
				
				break;
			case 1: authen();
			break;
				
			case 2: addusr();
			break;
			
			case 3: 
				
				User x= authen();
				if(x!=null)
				{
					
					modifyusr(x);
				}
				
			break;
			
			case 4: 
			
				admin();
				break;
				
				default: 
					System.out.println("Invalid choice");
					break;
					
			}
		}
		 
	}

	

}
