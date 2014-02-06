package report;

import java.text.SimpleDateFormat;

import flightRecord.CFlightRecord;

public class CLowSodiumReport extends CReport {

	protected boolean qualifiesForReport (CFlightRecord aFlightRecord)
	  //
	  // qualifiesForReport qualifies a record for this report if it has a loaded low-sodium meal within
	  // the date range of the report
	  //
	  {
	    return (( (aFlightRecord.getFlightDate ().after (fromDate))
	          || (aFlightRecord.getFlightDate ().equals (fromDate)))
	            && ((aFlightRecord.getFlightDate ().before (toDate))
	          || (aFlightRecord.getFlightDate ().equals (toDate)))
	            && (aFlightRecord.getMealType () == 'J')
	            && (aFlightRecord.getMealLoaded () == true));
	  } // qualifiesForReport

	  protected void printRecord (CFlightRecord aFlightRecord)
	  //
	  // printRecord outputs the flight number, flight date, and perceived quality
	  //
	  {
	    SimpleDateFormat flightDateFormat = new SimpleDateFormat ("mm/dd/yyyy");
	                      // used to format dates

	    System.out.println ("-----------------------------------------------------------------------------");
	    System.out.println ("FLIGHT NUMBER: " + aFlightRecord.getFlightNum ());
	    System.out.println ("FLIGHT DATE:   "
	        + flightDateFormat.format (aFlightRecord.getFlightDate ()));
	    System.out.println ("PERCEIVED QUALITY: " + aFlightRecord.getPerceivedQuality ());
	  } // printRecord

	  //
	  // default constructor
	  //
	  public CLowSodiumReport ()
	  {
	    recsPerScreen = 3;
	    printHeader = true;
	    theHeader = "          Low Sodium Report\n";
	  }

	} // class CLowSodiumReport

