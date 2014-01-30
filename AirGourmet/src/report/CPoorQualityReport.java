package report;

import java.text.SimpleDateFormat;

import flightRecord.CFlightRecord;
import passenger.CPassenger;

public class CPoorQualityReport extends CReport {
	protected boolean qualifiesForReport (CFlightRecord aFlightRecord)
	  //
	  // qualifiesForReport qualifies a record for this report if it has a special meal loaded within the date
	  // range of the report with a perceived quality less than 5
	  //
	  {
	    return (( (aFlightRecord.getFlightDate ().after (fromDate))
	          || (aFlightRecord.getFlightDate ().equals (fromDate)))
	            && ((aFlightRecord.getFlightDate ().before (toDate))
	          || (aFlightRecord.getFlightDate ().equals (toDate)))
	            && (aFlightRecord.getMealLoaded () )
	            && (aFlightRecord.getPerceivedQuality () < 5)
	            && (aFlightRecord.getPerceivedQuality () > -1));
	  } // qualifiesForReport

	  protected void printRecord (CFlightRecord aFlightRecord)
	  //
	  // printRecord outputs the passenger, flight date, and meal type
	  //
	  {
	    CPassenger      tempPassenger = new CPassenger ();
	                      // represents the passenger assigned to
	                      // this reservation
	    SimpleDateFormat    flightDateFormat = new SimpleDateFormat ("MMM/dd/yyyy");
	                      // used to format dates

	    if (tempPassenger.getPassenger (aFlightRecord.getPassengerID () ))
	    {
	      System.out.println ("-----------------------------------------------------------------------------\n");
	      System.out.println ("PASSENGER: " + tempPassenger.toString ());
	      System.out.println ("FLIGHT DATE:   "
	                  + flightDateFormat.format (aFlightRecord.getFlightDate ())
	                   + "        MEAL TYPE: "
	                  + CFlightRecord.mealTypeValues[aFlightRecord.getMealType ()
	                  -  'A']);
	    }
	  } // printRecord

	  //
	  // default constructor
	  //

	  public CPoorQualityReport ()
	  { recsPerScreen = 2;
	    printHeader = true;
	    theHeader = "         Poor Quality Report\n";
	  }

	} // class CPoorQualityReport



