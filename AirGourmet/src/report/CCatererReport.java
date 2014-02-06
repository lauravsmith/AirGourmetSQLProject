package report;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import Air.AirGourmetUtilities;
import flightRecord.CFlightRecord;

public class CCatererReport extends CReport {
	
	//
	  // data members
	  //

	  private String        flightNum;  // the report is for a specific flight number
	  private int[]        specialMealTally = new int[13];
	                      // represents a tally of the different types of
	                      // meals needed from the caterer

	  //
	  // protected methods
	  //

	  protected void getQualifications ()
	  //
	  // getQualifications retrieves and validates a flight date and a flight number for the caterer
	  //
	  {
	    boolean        dateOK = false;  // indicates if flight date properly entered
	    String          flightStrDate;   // used to get a string representing a date
	    SimpleDateFormat flightDateFormat = new SimpleDateFormat ("MMM/dd/yyyy");
	                      // used to parse a date

	    AirGourmetUtilities.clearScreen ();

	    //
	    // retrieve and validate a value for flightDate
	    //
	    while (!dateOK)
	    {
	      System.out.print ("Enter the DATE of the flight: ");

	      flightStrDate = AirGourmetUtilities.readString ();
	      dateOK = true;

	      try
	      {
	        fromDate = flightDateFormat.parse (flightStrDate);
	      }
	      catch (ParseException pe)
	      {
	        System.out.println ("\n\tYou entered the date incorrectly.\n");
	        System.out.println ("\tPlease use the format mmm/dd/yyyy.\n\n");
	        dateOK = false;
	      }

	    }

	    //
	    // retrieves a value for flightNum
	    //

	    System.out.print ("Enter the FLIGHT NUMBER: ");

	    flightNum = AirGourmetUtilities.readString ();

	  } // getQualifications

	  protected boolean qualifiesForReport (CFlightRecord aFlightRecord)
	  //
	  // qualifiesForReport qualifies a record for this report if it has the same flight date
	  // and flight number as that specified by the user
	  //
	  {
	    return ((aFlightRecord.getFlightDate ().equals (fromDate))
	        && (aFlightRecord.getFlightNum ().compareTo (flightNum) == 0));
	  } // qualifiesForReport

	  protected void printRecord (CFlightRecord aFlightRecord)
	  //
	  // printRecord does not print any information.  It updates an array that keeps track of the different
	  // kinds of meals that need to be delivered by the caterer
	  //
	  {
	    ++specialMealTally[aFlightRecord.getMealType () - 'A'];
	  } // printRecord

	  //
	  // default constructor
	  //

	  public CCatererReport ()
	  {
	    recsPerScreen = 1;
	    printHeader = false;
	    theHeader = "     Caterer Special Meals List";
	  }

	  public void print ()
	  //
	  // print overrides the print method in the base class.  It initializes an array that keeps track of meals,
	  // calls the base class print method, and then prints out the array
	  //
	  {
	    char          ch;

	    for (ch = 'A'; ch < 'N'; ch++)
	      specialMealTally[ch - 'A'] = 0;

	    super.print ();

	    System.out.println ("\t\t                     Air Gourmet");
	    System.out.println ("\t\t\t" + theHeader + "\n");

	    System.out.println ("MEAL TYPE\t\t NUMBER OF MEALS NEEDED\n");
	    System.out.println ("-----------------------------------------------------------------------------");

	    for (ch = 'A'; ch < 'N'; ch++)
	      System.out.println (CFlightRecord.mealTypeValues[ch - 'A'] + "\t\t\t   "
	          + specialMealTally[ch - 'A']);

	    System.out.println ("\n\nPress <ENTER> to return to main menu...");
	    AirGourmetUtilities.pressEnter ();

	  } // print

}
