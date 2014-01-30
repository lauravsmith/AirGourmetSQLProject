package Air;

import java.io.InputStreamReader;
import java.io.Reader;

import passenger.CPassenger;
import flightRecord.CFlightRecord;
import report.*;


public class AirGourmetUtilities {
	
	//
	  // public static methods
	  //

	  public static char getChar ()
	  //
	  // getChar returns the first character entered from the keyboard
	  //
	  {
	    char           ch = '\n';  // represents character read from keyboard

	    try
	    {
	      Reader in = new InputStreamReader (System.in);
	      ch = (char)in.read ();
	    }
	    catch (Exception e)
	    {
	      System.out.println ("Error: " + e.toString ());
	    }

	    return ch;

	  } // getChar

	  public static String readString ()
	  //
	  // readString returns a string entered from the keyboard
	  //
	  {
	    StringBuffer       tempBuffer = new StringBuffer ();
		char           c;

	    try
		{
		  while ((c = (char)System.in.read ( )) != '\n')
		  {
		    tempBuffer.append (c);
		  }
		}
		catch (Exception e)
		{
		  System.out.println ("Exception: " + e.getMessage ( ) + "has occurred");
		}
	    
		return tempBuffer.toString ().trim();
	  }
	  
	  

	  public static void clearScreen ()
	  //
	  // clearScreen clears the screen
	  //
	  {
	    int       i;          // loop counter representing number of
	                      // blank lines to be printed

	    for (i = 0; i < 26; i++)
	      System.out.println ("");

	  } // clearScreen

	  public static void pressEnter ()
	  //
	  // pressEnter waits until the user presses the <ENTER> key
	  //
	  {
	    char          ch = '\n';  // dummy variable used to induce keyboard input

	    Reader in = new InputStreamReader (System.in);
	    try
	    {
	      while ((ch = (char)in.read ()) != '\n');
	    }
	    catch (Exception e)
	    {
	      System.out.println ("Error: " + e.toString ());
	    }

	  } // pressEnter

	   private static void displayReportMenu ()
	   //
	   // displayReportMenu displays the menu containing all the reporting options
	   // available to the user
	   //
	   {
	    boolean        done;    // terminates do-loop
	    char          choice;  // user's choice
	    CCatererReport    catererReport = new CCatererReport ();
	    COnBoardReport    onBoardReport = new COnBoardReport ();
	    CPercentageReport  percentageReport = new CPercentageReport ();
	    CPoorQualityReport  poorQualityReport = new CPoorQualityReport ();
	    CLowSodiumReport  lowSodiumReport = new CLowSodiumReport ();

	    done = false;
	    while (!done)
	    {
	      clearScreen ();
	      System.out.println ("          Air Gourmet - REPORT MENU\n\n");
	      System.out.println ("        1. 24 Hour Caterer List\n");
	      System.out.println ("        2. On Board Meals List\n");
	      System.out.println ("        3. Report on Percentages\n");
	      System.out.println ("        4. Report on Poor Quality\n");
	      System.out.println ("        5. Report on Low Sodium\n");
	      System.out.println ("        6. Return to Main Menu\n\n");
	      System.out.print ("      Enter your choice and press <ENTER>: ");

	      choice = getChar ();
	      switch (choice)
	      {
	        case '1':
	          catererReport.print ();
	          break;

	        case '2':
	          onBoardReport.print ();
	          break;

	        case '3':
	          percentageReport.print ();
	          break;

	        case '4':
	          poorQualityReport.print ();
	          break;

	        case '5':
	          lowSodiumReport.print ();
	          break;

	        case '6':
	          done = true;
	          break;

	        default:
	          System.out.println ("\n\nChoice is out of range\n\n");
	          System.out.println ("       Press <ENTER> to return to menu...");
	          pressEnter ();
	          break;

	      } // switch (choice)
	    } // while (!done)

	   }  // displayReportMenu

	   public static void displayMainMenu ()
	   //
	   // displayMainMenu displays the main menu containing all the options available to the user
	   //
	  {
	    boolean      done;      // terminates do-loop
	    char        choice;    // user's choice
	    CFlightRecord    flightRecord = new CFlightRecord ();  
	                      // temporary flight record used to
	                      // invoke flight record operations

	    CPassenger passenger = new CPassenger ();

	    done = false;
	    while (!done)
	    {
	      clearScreen ();

	      System.out.println ("          Air Gourmet - MAIN MENU\n\n");
	      System.out.println ("        1. Enter a Reservation\n");
	      System.out.println ("        2. Check-in a Passenger\n");
	      System.out.println ("        3. Scan the Special Meals List\n");
	      System.out.println ("        4. Scan a Returned Postcard\n");
	      System.out.println ("        5. Produce a Report\n");
	      System.out.println ("        6. Quit\n\n");
	      System.out.print ("      Enter your choice and press <ENTER>: ");

	      choice = getChar ();
	      switch (choice)
	      {
	        case '1':
	          flightRecord.getReservation ();
	          break;

	        case '2':
	          flightRecord.checkInPassenger ();
	          break;

	        case '3':
	          flightRecord.scanSpecialMeals ();
	          break;

	        case '4':
	          flightRecord.scanPostcard ();
	          break;

	        case '5':
	          displayReportMenu ();
	          break;

	        case '6':
	          System.out.println ("\n\nThank you for using Air Gourmet!!\n\n");
	          System.out.println ("       Press <ENTER> to exit...");
	          pressEnter ();
	          done = true;
	          break;

	        default:
	          System.out.println ("\n\nChoice is out of range\n\n");
	          System.out.println ("       Press <ENTER> to return to menu...");
	          pressEnter ();
	          break;

	        } // switch (choice)
	    } // while (!done)

	  }  // displayMainMenu

}
