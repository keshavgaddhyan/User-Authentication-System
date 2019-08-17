import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


/**
 * @author keshavgaddhyan
 *This class has the definition of all the function and deals with objects of user
 */
/**
 * @author keshavgaddhyan
 *
 */
public class User implements Hash{
	
	private String username="", hashed_password="", name="",email="";
	private String login_date= new SimpleDateFormat("yyyy-MM-dd").format(new Date());
	private long number=0;
	private long login_count=0;
	private Boolean acc_locked=false;
	/**
	 * stores the list of all the users in the array
	 */
	static ArrayList<User> records=new ArrayList<>();
	 static Scanner scan1=new Scanner (System.in);

	/* (non-Javadoc)
	 * @see Hash#hashing(java.lang.String)
	 */
	public String hashing(String password) throws NoSuchAlgorithmException
	{
		MessageDigest md = MessageDigest.getInstance("SHA-1");
        md.update(password.getBytes());
        byte[] b = md.digest();
        StringBuffer temp = new StringBuffer();
        for(byte b1 : b){
            temp.append(Integer.toHexString(b1 & 0xff).toString());
        }
        return temp.toString();
	}


	
	/**
	 * @throws NoSuchAlgorithmException
	 * 
	 * this function adds a new user into the list of users
	 */
	public static void addusr() throws NoSuchAlgorithmException
	{
	@SuppressWarnings("resource")
	Scanner xyz=new Scanner(System.in);
	User x=new User();
	System.out.print("Please enter your username: ");
	x.username=xyz.nextLine();
	System.out.print("Please enter your password: ");
	String passcode=xyz.nextLine();
	Boolean check=checkpass(passcode);
	if(check!=true)
	System.out.println("Your password has to fulfil: at least 1 small letter, 1 capital letter, 1 digit!");
	while(check!=true)
	{
		System.out.print("Please enter your password: ");
		passcode=xyz.nextLine();
		check=checkpass(passcode);
	}
	System.out.print("Please re-enter your password: ");
	String npass=xyz.nextLine();
	checkpass1(passcode,npass);
	x.hashed_password=x.hashing(passcode);
	System.out.print("Please enter your full name: ");
	x.name=xyz.nextLine();
	System.out.print("Please enter your email address: ");
	x.email=xyz.nextLine();
	System.out.print("Please enter your Phone number: ");
	x.number=xyz.nextLong();
	records.add(x);
	}
	
	
	
	/**
	 * @param a first password
	 * @param b re-entered password
	 * @return password when both 1st and second password are same
	 * 
	 * this function takes the user input until the password and re-entered password are the same.
	 */
	static String checkpass1(String a,String b)
	{
		while(!a.equals(b))
		{
		System.out.print("Password not match! Please enter the new password: ");
		 a=scan1.nextLine();
		Boolean x=checkpass(a);
		if(x!=true)
		System.out.println("Your password has to fulfil: at least 1 small letter, 1 capital letter, 1 digit!");
		while(x!=true)
		{
			System.out.print("Please enter the new password: ");
			a=scan1.nextLine();
			x=checkpass(a);
		}
		System.out.print("Please re-enter the new password: ");
		 b=scan1.nextLine();
		 
	}
		return b;
	}
	
	
	
	/**
	 * @param a password to be checked
	 * @return true if password satisfies all the requirements(one digit,one capital alphabet and one lower case alphabet)
	 */
	static Boolean checkpass(String a)
	{
	int length;
	length=a.length();
	int d=0,C_alpha=0,alpha=0;
	for(int i=0;i<length;i++)
	{
		char x=a.charAt(i);
		if(Character.isDigit(x)==true)
			d++;
		if(Character.isLowerCase(x)==true)
			alpha++;
		if(Character.isUpperCase(x)==true)
			C_alpha++;
	}
	
	if(d>0 && alpha>0 && C_alpha>0 && length>=6)
		return true;
	else
		return false;
	}

	
	
	
	/**
	 * @param x is the username of the user to be compared
	 * @return the user if it exists
	 * 
	 * this function checks if a user exists in the records or not.
	 */
	static User user_exist(String x)
	{
		int i;
		for(i=0;i<records.size();i++)
		{
			if(records.get(i).username.equals(x))
				return records.get(i);
		}
		return null;
	}
	
	
	
	
	
	
	/**
	 * @param x is the user to be modified
	 * @throws NoSuchAlgorithmException
	 * 
	 * This function modifies the information of the user
	 */
	@SuppressWarnings("resource")
	static void modifyusr(User x) throws NoSuchAlgorithmException
	{
		if(records.isEmpty())
		{
			System.out.println("no records present to modify");
			return;
		}
			
System.out.print("Please enter your new password:");
Scanner ob3=new Scanner(System.in);
String pass=ob3.nextLine();
Boolean a=checkpass(pass);
if(a!=true)
System.out.println("Your password has to fulfil: at least 1 small letter, 1 capital letter, 1 digit!");
while(a!=true)
{
	System.out.print("Please enter your password: ");
	pass=ob3.nextLine();
	a=checkpass(pass);
}

System.out.print("Please re-enter your new password: ");
String npass=ob3.nextLine();
npass=checkpass1(pass,npass);

x.hashed_password=x.hashing(npass);
System.out.print("Please enter your new full name: ");
x.name=ob3.nextLine();
System.out.print("Please enter your new email address: ");
x.email=ob3.nextLine();
System.out.println("Record update successfully!");
		}
	
	
	

	
	/**
	 * @return the user if authentication is successful
	 * @throws NoSuchAlgorithmException
	 * 
	 * This function authenticates if username and password matching. returns null otherwise.
	 */
	static User authen() throws NoSuchAlgorithmException
	{
		
		@SuppressWarnings("resource")
		Scanner ob=new Scanner(System.in);
	
			System.out.print("Please enter your username: ");
			String usname=ob.nextLine();
			User x=user_exist(usname);
			if(x==null)
			{
				System.out.println("User does not exist");
				return null;
			}
			
				if(x.login_count<3)
				{
			System.out.print("Please enter your password: ");
			String pas=ob.nextLine();
		
			if(x.username.equals(usname) && x.hashed_password.equals(x.hashing(pas)))
			{
				System.out.println("Login success! Hello "+x.username+"!");
				x.login_count=0;
		       x.login_date= new SimpleDateFormat("yyyy-MM-dd").format(new Date());
				
			    return x;

			}
		
			else
			{
			x.login_count++;
			if(x.login_count<3)
			{
			System.out.println("Login Failed!");
			  return authen();
			  
			
			}
			else
			{
				System.out.println("Login failed! Your account has been locked!");
				x.acc_locked=true;
				return null;
			}
				}
				}
				else
				{
					System.out.println("Login failed! Your account has been locked!");
					return null;
				}
		
	}
	
	
	
	
	/**
	 * @throws NoSuchAlgorithmException
	 * this function creates administrator account if it does not already exists
	 * otherwise is resets the password of other users
	 * 
	 */
	@SuppressWarnings("resource")
	static void admin() throws NoSuchAlgorithmException
	{
		Scanner ob2=new Scanner(System.in);
		User m=new User();
		if(user_exist("administrator")==null)	
		{   
			
			System.out.println("Administrator account not exist, please create the administrator account by setting up a password for it.");
			
			m.username="administrator";
			System.out.print("Please enter the password: ");
			String pass=ob2.nextLine();
			Boolean a=checkpass(pass);
			if(a!=true)
			System.out.println("Your password has to fulfil: at least 1 small letter, 1 capital letter, 1 digit!");
			while(a!=true)
			{
				System.out.print("Please enter the password: ");
				pass=ob2.nextLine();
				a=checkpass(pass);
			}
			System.out.print("Please re-enter the new password: ");
			String npass=ob2.nextLine();
			pass=checkpass1(pass,npass);
			System.out.println("Administrator account created successfully!");
			m.hashed_password=m.hashing(pass);
			records.add(m);
			
		}
		else
		{
			System.out.print("Please enter the password of administrator: ");
			String temp=ob2.nextLine();
			m=user_exist("administrator");
			while(!(m.hashing(temp).equals(m.hashed_password)))	
			{
				System.out.print("Admin password incorrect. Please enter the password again: ");
				temp=ob2.nextLine();
			}
			
			System.out.print("Please enter the user account need to reset: ");
			String u=ob2.nextLine();
			User x=user_exist(u);
			if(x==null)
			{
				System.out.println("User does not exist");
				return;
			}
			System.out.print("Please enter the new password: ");
			temp=ob2.nextLine();
			Boolean a=checkpass(temp);
			if(a!=true)
			System.out.println("Your password has to fulfil: at least 1 small letter, 1 capital letter, 1 digit!");
			while(a!=true)
			{
				System.out.print("Please enter the new password: ");
				temp=ob2.nextLine();
				a=checkpass(temp);
			}

			System.out.print("Please re-enter your new password: ");
			String npass=ob2.nextLine();
			 npass=checkpass1(temp,npass);
			 x.hashed_password=x.hashing(npass);
			 System.out.println("Password update successfully!");
			 x.acc_locked=false;
			 x.login_count=0;
			 
				}
			
	}
	
	
	
	/**
	 * @throws IOException
	 * 
	 * this function writes the records from array of users to the file user.txt
	 */
	@SuppressWarnings("unchecked")
	static void write() throws IOException
	{
		
		File file=new File("User.txt");
		PrintStream pr=new PrintStream(file);
		JSONArray list= new JSONArray();
		for(int i=0;i<records.size();i++)
		{
		JSONObject obj=new JSONObject();
		obj.put("username", records.get(i).username);
		obj.put("hash_password",records.get(i).hashed_password);
		obj.put("Full Name",records.get(i).name);
		obj.put("Email", records.get(i).email);
		obj.put("Phone number",records.get(i).number);
		obj.put("Fail count", records.get(i).login_count);
		obj.put("Last Login Date", records.get(i).login_date);
		obj.put("Account locked", records.get(i).acc_locked);
		list.add(obj);
	}
		JSONObject x=new JSONObject();
		x.put("user_array", list);
		
		pr.println(x);
		pr.close();
	
	}
	
	/**
	 * @throws FileNotFoundException
	 * @throws IOException
	 * @throws ParseException
	 * 
	 * this function reads the information from user.txt and stores it in the user array(records)
	 */
	static void read() throws FileNotFoundException, IOException, ParseException
	{
	
		File f=new File("User.txt");
		if(f.exists())
		{
		JSONParser parser=new JSONParser();
		JSONObject obj=(JSONObject) parser.parse(new FileReader("User.txt"));
		JSONArray xyz= (JSONArray) obj.get("user_array");
		
		for(int i=0;i<xyz.size();i++)
		{
			User temp=new User();
			JSONObject n= (JSONObject) xyz.get(i);
			temp.username= n.get("username").toString();
			temp.hashed_password=n.get("hash_password").toString();
			temp.name=n.get("Full Name").toString();
			temp.email=n.get("Email").toString();
			temp.number=(long) n.get("Phone number");
			temp.login_count=(long) n.get("Fail count");
			temp.login_date=n.get("Last Login Date").toString();
			temp.acc_locked=(Boolean) n.get("Account locked");
			records.add(temp);
		}
		
	}
	}
}
	