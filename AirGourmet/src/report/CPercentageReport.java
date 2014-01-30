package report;

import java.text.NumberFormat;

import Air.AirGourmetUtilities;
import flightRecord.CFlightRecord;

public class CPercentageReport extends CReport {
	
	// the following are used to keep track of the different kinds of totals
	  // needed to display the various percentages
	  //
	  private int[]       loadedAsSpecifiedFound = new int[13];
	  private int[]       onBoardFound = new int[13];
	  private int[]       onBoardNotLoaded = new int[13];
	  private int[]       totalEncountered = new int[13];

	  protected boolean qualifiesForReport (CFlightRecord aFlightRecord)
	  //
	  // qualifiesForReport qualifies a record for this report if it has a special meal within the date
	  // range of the report
	  //
	  {
	    return (( (aFlightRecord.getFlightDate ().after (fromDate))
	          || (aFlightRecord.getFlightDate ().equals (fromDate)))
	            && ((aFlightRecord.getFlightDate ().before (toDate))
	          || (aFlightRecord.getFlightDate ().equals (toDate))));
	  } // qualifiesForReport

	  protected void printRecord (CFlightRecord aFlightRecord)
	  //
	  // printRecord does not print any information.  Instead,
	  // it updates several arrays that keep track of the different kinds of percentages
	  //
	  {
	    ++totalEncountered[aFlightRecord.getMealType () -  'A'];
	    ++totalEncountered[12];

	    if (aFlightRecord.getMealLoaded () == true)
	    {
	      ++loadedAsSpecifiedFound[aFlightRecord.getMealType () -  'A'];
	      ++loadedAsSpecifiedFound[12];
	    }
	    if (aFlightRecord.getCheckedIn () == true)
	    {
	      ++onBoardFound[aFlightRecord.getMealType () -  'A'];
	      ++onBoardFound[12];
	    }
	    if ((aFlightRecord.getCheckedIn () == true)
	        && (aFlightRecord.getMealLoaded () == false))
	    {
	      ++onBoardNotLoaded[aFlightRecord.getMealType () - 'A'];
	      ++onBoardNotLoaded[12];
	    }
	  } // printRecord

	  //
	  // default constructor
	  //

	  public CPercentageReport ()
	  {
	    recsPerScreen = 2;
	    printHeader = false;
	    theHeader = "         Percentages Report\n";
	  }

	  public void print ()
	  //
	  // print overrides the print method in the base class.
	  // It initializes an array that keeps track of meals, calls the base
	  // class print method, and then prints out the array
	  //
	  {
	    int          i;
	    NumberFormat     df = NumberFormat.getPercentInstance ();
	                      // used to format percentages

	    //
	    // initialize the totals
	    //
	    for (i = 0; i < 13; i++)
	    {
	      loadedAsSpecifiedFound[i] = 0;
	      onBoardFound[i] = 0;
	      onBoardNotLoaded[i] = 0;
	      totalEncountered[i] = 0;
	    }

	    super.print ();

	    //
	    // print header
	    //
	    System.out.println ("\t\t                     Air Gourmet");
	    System.out.println ("\t\t\t" + theHeader + "\n");

	    System.out.println ("MEAL TYPE\t     % LOADED\t      % ON BOARD" +
	                      "     % ON BOARD, NOT LOADED");
	    System.out.println ("-----------------------------------------------------------------------------");

	    //
	    // print the percentages for each meal type
	    //
	    for (i = 0; i < 12; i++)
	    {
	      System.out.print (CFlightRecord.mealTypeValues[i] + "\t     ");

	      if (totalEncountered[i] == 0)
	        System.out.print ("   NA\t\t");
	      else
	        System.out.print ("   "+ df.format ((float) loadedAsSpecifiedFound[i] /
	              (float) totalEncountered[i]) + " \t\t");

	      if (totalEncountered[i] == 0)
	        System.out.print ("   NA\t\t   ");
	      else
	        System.out.print ("   "+ df.format ((float) onBoardFound[i] /
	                (float) totalEncountered[i]) + "\t\t   ");

	      if (totalEncountered[i] == 0)
	        System.out.print ("   NA");
	      else
	        System.out.print ("   " + df.format ((float) onBoardNotLoaded[i] /
	                (float) totalEncountered[i]));

	      System.out.println ("");
	    }

	    //
	    // print the totals
	    //
	    System.out.print ("\nTOTALS:        \t     ");

	    if (totalEncountered[12] == 0)
	      System.out.print ("  NA\t\t");
	    else
	      System.out.print ("   "+ df.format ((float) loadedAsSpecifiedFound[12] /
	                    (float) totalEncountered[12]) + " \t\t");

	    if (totalEncountered[12] == 0)
	      System.out.print ("   NA\t\t   ");
	    else
	      System.out.print ("   "+ df.format ((float) onBoardFound[12] /
	                  (float) totalEncountered[12]) + "\t\t   ");

	    if (totalEncountered[12] == 0)
	      System.out.print ("   NA");
	    else
	      System.out.print ("   " + df.format ((float) onBoardNotLoaded[12] /
	                (float) totalEncountered[12]));

	    System.out.println ("\n\nPress <ENTER> to return to main menu...");
	    AirGourmetUtilities.pressEnter ();

	  } // print

	} // class CPercentageReport




