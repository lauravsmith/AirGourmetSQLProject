package report;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import Air.AirGourmetUtilities;
import flightRecord.CFlightRecord;

public class COnBoardReport extends CReport {
	
	//
	  // data member
	  //

	  private String        flightNum;  // the report is for a specific flight number

	  protected void getQualifications ()
	  //
	  // getQualifications retrieves and validates a flight date and a flight number for the caterer
	  //
	  {
	    boolean        dateOK = false;  // indicates if flight date properly entered
	    String          flightStrDate;  // used to get a string representing a date
	    SimpleDateFormat     flightDateFormat = new SimpleDateFormat ("MMM/dd/yyyy");
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
	  // printRecord outputs the passenger, seat number, meal type, and checkbox for this record
	  //
	  {
	    System.out.println ("-----------------------------------------------------------------------------");
	    System.out.println ("PASSENGER: " + aFlightRecord.getPassengerID ()
	              + "  SEAT: " + aFlightRecord.getSeatNum ()
	              + "  MEAL TYPE:"
	              + CFlightRecord.mealTypeValues[aFlightRecord.getMealType () - 'A']
	              + "    |__|");
	  } // printRecord

	  //
	  // default constructor
	  //

	  public COnBoardReport ()
	  {
	    recsPerScreen = 10;
	    printHeader = true;
	    theHeader = "        On Board Meals List\n";
	  }

	} // class COnBoardReport


