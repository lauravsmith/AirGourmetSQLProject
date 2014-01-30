package report;

public class CNotLoadedReport extends CReport {
	
//	protected boolean qualifiesForReport (CFlightRecord aFlightRecord)
//  //
//  // getQualifications qualifies a record for this report if it is within the date range
//  // of the report and having more than one meal not loaded within the date range
//  //
//  {
//    return (( (aFlightRecord.getFlightDate ().after (fromDate))
//          || (aFlightRecord.getFlightDate ().equals (fromDate)))
//            && ((aFlightRecord.getFlightDate ().before (toDate))
//          || (aFlightRecord.getFlightDate ().equals (toDate)))
//            && (aFlightRecord.getCheckedIn ())
//            && (!alreadyEncountered (aFlightRecord.getPassengerID () ))
//            && (notLoadedMoreThanOnce (aFlightRecord)));
//  } // qualifiesForReport
//
//  protected void printRecord (CFlightRecord aFlightRecord)
//  //
//  // printRecord outputs the passenger name/address and the dates when a meal was not loaded
//  //
//  {
//    CFlightRecord      tempFltRec;  // temporary record used for reading in
//                      // all reservations
//    CPassenger      tempPassenger = new CPassenger ();
//                      // represents the passenger assigned to
//                      // this reservation
//    boolean         EOF = false;
//    File          fileExists = new File ("fltRec.dat");
//    SimpleDateFormat flightDateFormat = new SimpleDateFormat ("MMM/dd/yyyy");
//
//    if (tempPassenger.getPassenger (aFlightRecord.getPassengerID ()))
//    {
//      markEncountered (tempPassenger.getPassengerID () );
//      System.out.println ("");
//      System.out.println ("-----------------------------------------------------------------------------");
//      System.out.println ("PASSENGER: " + tempPassenger);
//      System.out.print ("DATES: ");
//    }
//
//    //
//    // loop through all flight records
//    //
//    try
//    {
//      ObjectInputStream in = new ObjectInputStream (new FileInputStream ("fltRec.dat"));
//
//      while (!EOF)
//      {
//        try
//        {
//          tempFltRec = (CFlightRecord)in.readObject ();
//
//          //
//          // check if there is a match with the current flight record object
//          // must have the same passengerID and be within report date range
//          //
//          if ((!tempFltRec.getMealLoaded () )
//              && (tempFltRec.getPassengerID ().
//                compareTo (aFlightRecord.getPassengerID () ) == 0)
//              && ((tempFltRec.getFlightDate ().after (fromDate)
//            || (tempFltRec.getFlightDate ().equals (fromDate))
//              && ((tempFltRec.getFlightDate ().before (toDate))
//            || (tempFltRec.getFlightDate ().equals (toDate))))))
//
//            System.out.print (flightDateFormat.format (tempFltRec.getFlightDate ())
//                + "  ");
//
//        } // try
//
//        catch (EOFException e)
//        {
//          EOF = true;
//        }
//
//      } // while
//
//      in.close ();
//    } // try
//
//    catch (Exception e)
//    {
//      e.printStackTrace (System.out);
//    }
//
//  } // printRecord
//
//  private boolean  alreadyEncountered (String searchID)
//  //
//  // alreadyEncountered determines if a passengerID has already been encountered by this report
//  //
//  {
//    boolean        found = false;  // indicates if pass, already encountered
//    String          tempID;  // temporary passengerID read from file
//    File          fileExists = new File ("notLoaded.dat");
//                      // used to test if file exists
//
//    if (fileExists.exists ())
//      try
//      {
//        RandomAccessFile randomReader = new RandomAccessFile
//            ("notLoaded.dat", "r");
//
//        //
//        // loop through all IDs in the file
//        //
//        while ((tempID = randomReader.readLine ()) != null)
//        {
//          if (tempID.toUpperCase ().indexOf (searchID.toUpperCase ()) >= 0)
//          {
//            found = true;
//            break;
//          }
//        }
//
//        randomReader.close ();
//
//      }
//      catch (Exception e)
//      {
//        System.out.println (e);
//      }
//
//    return found;
//
//  } // alreadyEncountered
//
//  private boolean  notLoadedMoreThanOnce (CFlightRecord aFlightRecord)
//  //
//  // notLoadedMoreThanOnce determines if a passenger has had more than one meal not loaded
//  //
//  {
//    boolean        EOF = false;
//    short          notLoadedCount = 0;  // count of meals not loaded
//    CFlightRecord      tempFltRec;  // temporary record used for reading in
//                      // and comparing all reservations
//    File                 fileExists = new File ("fltRec.dat");
//                      // used to test if file exists
//
//    try
//    {
//      if (fileExists.exists ())
//      {
//        ObjectInputStream in = new ObjectInputStream (new FileInputStream
//            ("fltRec.dat"));
//
//        while (!EOF)
//        {
//          try
//          {
//            tempFltRec = (CFlightRecord)in.readObject ();
//
//            //
//            // check if there is a match with the current flight record object
//            // must have the same passengerID and be within report date range
//            //
//            if ((!tempFltRec.getMealLoaded () )
//                && (tempFltRec.getCheckedIn ())
//                && (tempFltRec.getPassengerID ().toUpperCase ().compareTo
//                    (aFlightRecord.getPassengerID ().toUpperCase ()) == 0)
//                && ((tempFltRec.getFlightDate ().after (fromDate))
//              || (tempFltRec.getFlightDate ().equals (fromDate)))
//                && ((toDate.after (tempFltRec.getFlightDate () ))
//              || (toDate.equals (tempFltRec.getFlightDate () ))))
//            {
//              notLoadedCount++;
//              if (notLoadedCount > 1)
//                break;
//             }
//          }
//          catch (EOFException e)
//          {
//            EOF = true;
//          }
//
//        } // while (!EOF)
//
//        in.close ();
//      } // if (fileExists.exists ())
//
//    } // try
//
//    catch (Exception e)
//    {
//      e.printStackTrace (System.out);
//    }
//
//    return (notLoadedCount > 1);
//
//  } // notLoadedMoreThanOnce
//
//
//  private void markEncountered (String encounteredID)
//  //
//  // markEncountered adds the current passengerID to the notLoaded file
//  //
//  {
//    try
//    {
//      RandomAccessFile randomWriter = new RandomAccessFile ("notLoaded.dat", "rw");
//
//      //
//      // write the new ID to the end of the file
//      //
//      randomWriter.seek (randomWriter.length ());
//      randomWriter.writeBytes (encounteredID + "\n");
//      randomWriter.close ();
//    }
//    catch (Exception e)
//    {
//      System.out.println ("Error: " + e.toString ());
//    }
//
//  } // markEncountered
//
//  //
//  // default constructor
//  //
//
//  public CNotLoadedReport ()
//  {
//    recsPerScreen = 2;
//    printHeader = true;
//    theHeader = "      Meals Not Loaded Report\n";
//  }
//
//  public void print ()
//  //
//  // print deletes a needed file before calling the base class print method
//  //
//  {
//    File          fileNotLoaded = new File ("notLoaded.dat");
//                      // used to test if file exists
//
//    //
//    // delete the notLoaded file before printing the report
//    //
//    if (fileNotLoaded.exists ())
//      fileNotLoaded.delete ();
//
//    super.print ();
//
//  } // print ()

}
