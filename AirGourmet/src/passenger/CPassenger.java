package passenger;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import Air.AirGourmetUtilities;
import java.sql.*;

public class CPassenger implements Serializable {
	
	//
	  // data members
	  //
	  private String        passengerID;  // ID of passenger (9 digits)
	  private String        firstName;  // first name of passenger (15 chars)
	  private char        middleInit;  // middle initial of passenger (1 char)
	  private String        lastName;  // last name of passenger (15 chars)
	  private String        suffix;    // suffix of passenger (5 chars)
	  private String        address1;  // first line of passenger address (25 chars)
	  private String        address2;  // second line of pass. address (25 chars)
	  private String        city;    // city in which passenger lives (14 chars)
	  private String        state;    // state in which passenger lives (14 chars)
	  private String        postalCode;  // postal code of passenger (10 chars)
	  private String        country;  // country in which pass. lives (20 chars)

	  //
	  // accessor methods
	  //
	  public String  getPassengerID ()      { return passengerID; }
	  public String  getFirstName ()      { return firstName; }
	  public char  getMiddleInit ()        { return middleInit; }
	  public String  getLastName ()      { return lastName; }
	  public String  getSuffix ()          { return suffix; }
	  public String  getAddress1 ()        { return address1; }
	  public String  getAddress2 ()        { return address2; }
	  public String  getCity ()          { return city; }
	  public String  getState ()          { return state; }
	  public String  getPostalCode ()      { return postalCode; }
	  public String  getCountry ()        { return country; }

	  //
	  // mutator methods
	  //
	  public void  setPassengerID (String s)    { passengerID = s.toUpperCase (); }
	  public void  setFirstName (String f)    { firstName = f.toUpperCase (); }
	  public void  setMiddleInit (char m)    { middleInit = m; }
	  public void  setLastName (String l)    { lastName = l.toUpperCase (); }
	  public void  setSuffix (String s)      { suffix = s.toUpperCase (); }
	  public void  setAddress1 (String a1)    { address1 = a1.toUpperCase (); }
	  public void  setAddress2 (String a2)    { address2 = a2.toUpperCase (); }
	  public void  setCity (String c)      { city = c.toUpperCase (); }
	  public void  setState (String s)      { state = s.toUpperCase (); }
	  public void  setPostalCode (String p)    { postalCode = p.toUpperCase (); }
	  public void  setCountry (String c)    { country = c.toUpperCase (); }

	  //
	  // public methods
	  //
	   static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	   static final String DB_URL = "jdbc:mysql://localhost/se4453";

	   //  Database credentials
	   static final String USER = "root";
	   static final String PASS = "123456";
	  
	  public void savePassenger() 
	  {
		  if (!this.alreadyExists()) {
		   Connection conn = null;
		   Statement stmt = null;
		   
		String insertTableSQL = "INSERT INTO CPASSENGER"
					+ "(PASSENGERID, FIRSTNAME, MIDDLEINIT, LASTNAME, SUFFIX, ADDRESS1, ADDRESS2, CITY, STATE, POSTALCODE, COUNTRY) " + "VALUES"
					+ "(" + "'"+ this.passengerID +"','"+ this.firstName + "','" + this.middleInit + "','" + this.lastName + "','"+ this.suffix + "','" + this.address1 + "','" + this.address2 + "','" + this.city +"','"+ this.state +"','"+ this.postalCode + "','" + this.country + "')";
				
				try{
			   
			      //STEP 2: Register JDBC driver
			      DriverManager.registerDriver(new com.mysql.jdbc.Driver());

			      //STEP 3: Open a connection
			      conn = DriverManager.getConnection(DB_URL, USER, PASS);

			      //STEP 4: Execute a query
			      stmt = conn.createStatement();
			   // execute insert SQL stetement
					stmt.executeUpdate(insertTableSQL);
					
			   }catch(SQLException se){
			      //Handle errors for JDBC
			      se.printStackTrace();
			   } finally{
				      //finally block used to close resources
				      try{
				         if(conn!=null)
				            conn.close();
				      }catch(SQLException se){
				         se.printStackTrace();
				      }//end finally try
				   }//end try
		  }
	  }

	  public synchronized String toString ()
	  //
	  // toString composes a string representation of the passenger object
	  //
	  {
	    return passengerID + "\n"+ firstName + " " + middleInit + " " + lastName + " " + suffix + "\n"
	        +  address1 + "\n" + address2 + "\n" +  city + " " + state + "  " + postalCode + "  "
	        + country;
	  }

	  public void Copy (ResultSet tempPassenger) throws SQLException
	  //
	  // Copy  makes a copy of tempPassenger into the current object
	  //
	  {
//		  String insertTableSQL = "INSERT INTO CPASSENGER"
//					+ "(PASSENGERID, FIRSTNAME, MIDDLEINIT, LASTNAME, SUFFIX, ADDRESS1, ADDRESS2, CITY, STATE, POSTALCODE, COUNTRY) " + "VALUES"
//					+ "(" + "'"+ this.passengerID +"','"
	    this.passengerID = tempPassenger.getString("PASSENGERID");
	    this.firstName = tempPassenger.getString("FIRSTNAME");
	    this.middleInit = tempPassenger.getString("MIDDLEINIT").charAt(0);
	    this.lastName = tempPassenger.getString("LASTNAME");
	    this.suffix = tempPassenger.getString("SUFFIX");
	    this.address1 = tempPassenger.getString("ADDRESS1");
	    this.address2 = tempPassenger.getString("ADDRESS2");
	    this.city = tempPassenger.getString("CITY");
	    this.state = tempPassenger.getString("STATE");
	    this.postalCode = tempPassenger.getString("POSTALCODE");
	    this.country = tempPassenger.getString("COUNTRY");
	    System.out.println("saving passenger");
	  }

	  public boolean getPassenger (String searchID)
	  //
	  // getPassenger loads the passenger from the file that has passengerID equal to searchID
	  // Returns true if the passenger was found and loaded
	  //
	  {

		   Connection conn = null;
		   Statement stmt = null;
			String selectTableSQL = "SELECT * FROM CPASSENGER WHERE PASSENGERID='" + this.passengerID + "'";

				try{
			   
			      //STEP 2: Register JDBC driver
			      DriverManager.registerDriver(new com.mysql.jdbc.Driver());

			      //STEP 3: Open a connection
			      conn = DriverManager.getConnection(DB_URL, USER, PASS);

			      //STEP 4: Execute a query
			      stmt = conn.createStatement();
			   // execute insert SQL stetement
			      ResultSet rs = stmt.executeQuery(selectTableSQL);
					
			      boolean exists = rs.next();
			      
			      if (exists) {
			    	  
			    	  this.Copy(rs);
			    	  return true;
			      } else {
			    	  return false;
			      }
				}
				    catch (Exception e)
				    {
				      e.printStackTrace (System.out);
				    } finally{
				        //finally block used to close resources
				        try{
				           if(conn!=null)
				              conn.close();
				        }catch(SQLException se){
				           se.printStackTrace();
				        }//end finally try
				     }//end try
				
				return false;
	  } // getPassenger


	  public void getDescription ()
	  //
	  // getDescription retrieves passenger information
	  //
	  {
	    AirGourmetUtilities.clearScreen ();

	    System.out.println ("Please enter the following information about the passenger.\n\n");

	    System.out.println ("Enter the PASSENGER ID assigned to this passenger");
	    System.out.print (" (9 numbers only - no spaces or dashes): ");
	    passengerID = AirGourmetUtilities.readString ();

	    if (!alreadyExists () )
	    {
	      System.out.print ("Enter the FIRST name of the passenger: ");
	      firstName = AirGourmetUtilities.readString ();

	      System.out.print ("Enter the MIDDLE INITIAL of the passenger: ");
	      middleInit = AirGourmetUtilities.getChar ();

	      System.out.print ("Enter the LAST name of the passenger: ");
	      lastName = AirGourmetUtilities.readString ();

	      System.out.print ("Enter the SUFFIX used by the passenger: ");
	      suffix = AirGourmetUtilities.readString ();

	      System.out.print ("Enter the ADDRESS (first line) of the passenger: ");
	      address1 = AirGourmetUtilities.readString ();

	      System.out.print ("Enter the ADDRESS (second line) of the passenger: ");
	      address2 = AirGourmetUtilities.readString ();

	      System.out.print ("Enter the CITY where the passenger lives: ");
	      city = AirGourmetUtilities.readString ();

	      System.out.print ("Enter the STATE where the passenger lives: ");
	      state = AirGourmetUtilities.readString ();

	      System.out.print ("Enter the POSTAL CODE where the passenger lives: ");
	      postalCode = AirGourmetUtilities.readString ();

	      System.out.print ("Enter the COUNTRY where the passenger lives: ");
	      country = AirGourmetUtilities.readString ();
	    }
	  } // getDescription

	  //
	  // private method
	  //

	  private boolean alreadyExists ()
	  //
	  // alreadyExists determines if the passengerID of the current object already exists in the file
	  // if the ID exists, then the user is asked if the values stored in the file
	  // are to be used
	  //
	  {
		   Connection conn = null;
		   Statement stmt = null;
			String selectTableSQL = "SELECT * FROM CPASSENGER WHERE PASSENGERID='" + this.passengerID + "'";

				try{
			   
			      //STEP 2: Register JDBC driver
			      DriverManager.registerDriver(new com.mysql.jdbc.Driver());

			      //STEP 3: Open a connection
			      conn = DriverManager.getConnection(DB_URL, USER, PASS);

			      //STEP 4: Execute a query
			      stmt = conn.createStatement();
			   // execute insert SQL stetement
			      ResultSet rs = stmt.executeQuery(selectTableSQL);
					
			      boolean exists = rs.next();
			      
			      if (exists) {
			    	  
			    	  this.Copy(rs);
			  	    
				      System.out.println ("\n\n");
				      System.out.println ("The following passenger exists: \n\n");
				      System.out.println (this.toString () + "\n");

				      System.out.println ("Do you want to use this name and address to make a ");
				      System.out.print ("reservation for this passenger (Y/N)? ");

				      char ch = AirGourmetUtilities.getChar ();
				      System.out.println ("\n");

				      if (Character.toUpperCase (ch) == 'Y')
				    	  return true;
			      } else {
			    	  return false;
			      }
			      return false;
			   }catch(SQLException se){
			      //Handle errors for JDBC
			      se.printStackTrace();
			   } finally{
				      //finally block used to close resources
				      try{
				         if(conn!=null)
				            conn.close();
				      }catch(SQLException se){
				         se.printStackTrace();
				      }//end finally try
				   }//end try
				return false; 
	  } // alreadyExists

	} // class CPassenger




