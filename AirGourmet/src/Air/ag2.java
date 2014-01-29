//
// This is an implementation of the Air Gourmet product written in Java
//
// This application was developed using Borland JBuilder 2.0 (JDK 1.1)
//
package Air;

import java.io.*;
import java.util.*;
import java.text.*;

//---------------------------------------------------------------------------------------------------------------------------------------------------

class CPassenger implements java.io.Serializable
{
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

  public synchronized String toString ()
  //
  // toString composes a string representation of the passenger object
  //
  {
    return passengerID + "\n"+ firstName + " " + middleInit + " " + lastName + " " + suffix + "\n"
        +  address1 + "\n" + address2 + "\n" +  city + " " + state + "  " + postalCode + "  "
        + country;
  }

  public void Copy (CPassenger tempPassenger)
  //
  // Copy  makes a copy of tempPassenger into the current object
  //
  {
    this.passengerID = tempPassenger.getPassengerID ();
    this.firstName = tempPassenger.getFirstName ();
    this.middleInit = tempPassenger.getMiddleInit ();
    this.lastName = tempPassenger.getLastName ();
    this.suffix = tempPassenger.getSuffix ();
    this.address1 = tempPassenger.getAddress1 ();
    this.address2 = tempPassenger.getAddress2 ();
    this.city = tempPassenger.getCity ();
    this.state = tempPassenger.getState ();
    this.postalCode = tempPassenger.getPostalCode ();
    this.country = tempPassenger.getCountry ();
  }

  public boolean getPassenger (String searchID)
  //
  // getPassenger loads the passenger from the file that has passengerID equal to searchID
  // Returns true if the passenger was found and loaded
  //
  {
    boolean        found = false;  // indicates if passenger already exists
    File          fileExists = new File ("passenger.dat");
                      // used to test if file exists
    CPassenger      tempPassenger;  // temporary object used to determine if
                      // object already exists
    boolean        EOF = false;

    if (!fileExists.exists ())
      return false;

    try
    {
      ObjectInputStream in = new ObjectInputStream (new FileInputStream 
          ("passenger.dat"));

      while (!EOF)
      {
        try
        {
          //
          // determine if the passenger object already exists
          //
          tempPassenger = (CPassenger)in.readObject ();

          //
      // check if there is a match with searchID
          //
          if (tempPassenger.getPassengerID ().toLowerCase ().compareTo
              (searchID.toLowerCase ()) == 0)
            {
              found = true;
              this.Copy (tempPassenger);
              break;
            }
        } // try
        catch (EOFException e)
        {
          EOF = true;
        }

      } // while (!EOF)

      in.close ();
    } // try
    catch (Exception e)
    {
      e.printStackTrace (System.out);
    }

    return found;

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


  public void insert ()
  //
  // insert inserts a passenger object in the proper place
  //
  {
    boolean        found = false;  // indicates if object insertion point found
    File          fileExists = new File ("passenger.dat");
                      // used to test if file exists
    CPassenger      tempPassenger;  // temporary object used for file copying
    boolean        EOF = false;

    try
    {
      ObjectOutputStream out = new ObjectOutputStream (new FileOutputStream
          ("tempP.dat"));

      if (fileExists.exists ())
      {
        ObjectInputStream in = new ObjectInputStream (new FileInputStream
            ("passenger.dat"));

        while (!EOF)
        {
          try
          {
            // read/write temporary object from the passenger file
            tempPassenger = (CPassenger)in.readObject ();
            out.writeObject (tempPassenger);
          }
          catch (EOFException e)
          {
            EOF = true;
          }

        } // while (!EOF)

        in.close ();
      } // if (fileExists.exists ())
      else
        out.writeObject (this);

      out.close ();
    } // try

    catch (Exception e)
    {
      e.printStackTrace (System.out);
    }

    EOF = false;

    try
    {
      ObjectInputStream in = new ObjectInputStream (new FileInputStream ("tempP.dat"));
      ObjectOutputStream out = new ObjectOutputStream (new FileOutputStream
          ("passenger.dat"));

      while (!EOF)
      {
        try
        {
          tempPassenger = (CPassenger)in.readObject ();

          //
          // check if there is already a passenger with the current ID.
          // If one exists, it means a change of address so write the new
          // passenger object into the file
          //
          if (passengerID.compareTo (tempPassenger.getPassengerID ().
              toLowerCase ()) == 0)
          {
            out.writeObject (this);
            found = true;
          }
          else
            out.writeObject (tempPassenger);

        } // try

        catch (EOFException e)
        {
          if (!found)
            out.writeObject (this);

          EOF = true;
        }

      } // while (!EOF)

      in.close ();
      out.close ();
    } // try

    catch (Exception e)
    {
      e.printStackTrace (System.out);
    }

  } // insert

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
    char          ch;    // holds user response to Y/N question
    boolean        found = false;  // indicates if passenger already exists
    String          searchID;  // the passengerID for which to search
    File          fileExists = new File ("passenger.dat");
                      // used to test if file exists
    CPassenger      tempPassenger;  // temporary object used to determine if
                      // object already exists
    boolean        EOF = false;

    if (!fileExists.exists ())
      return false;

    searchID = passengerID;

    try
    {
      ObjectInputStream in = new ObjectInputStream (new FileInputStream
          ("passenger.dat"));

      while (!EOF)
      {
        try
        {
          //
          // determine if the passenger object already exists
          //
          tempPassenger = (CPassenger)in.readObject ();

          if (tempPassenger.getPassengerID ().toLowerCase ().compareTo
              (searchID.toLowerCase ()) == 0)
          {
            found = true;
            this.Copy (tempPassenger);
            break;
          }
        } // try

        catch (EOFException e)
        {
          EOF = true;
        }

      } // while

      in.close ();
    } // try

    catch (Exception e)
    {
      e.printStackTrace (System.out);
    }

    //
    // A record was found that has the same passengerID.
    // Ask the users if they want to use this record in the current
    // reservation (if the user answers N, then a new name/address may be
    // given to this passengerID)
    //
    if (found)
    {
      System.out.println ("\n\n");
      System.out.println ("The following passenger exists: \n\n");
      System.out.println (this.toString () + "\n");

      System.out.println ("Do you want to use this name and address to make a ");
      System.out.print ("reservation for this passenger (Y/N)? ");

      ch = AirGourmetUtilities.getChar ();
      System.out.println ("\n");

      found = false;

      if (Character.toUpperCase (ch) == 'Y')
        found = true;
    }
    passengerID = searchID;
    return found;

  } // alreadyExists

} // class CPassenger


//---------------------------------------------------------------------------------------------------------------------------------------------------

class CFlightRecord implements java.io.Serializable
{
  //
  // mealTypeValues represents an array containing the different kind of meal types
  //
  public static String mealTypeValues[] =  {"Child          ", "Diabetic       ",
      "Halaal         ", "Kosher         ", "Lactose Free   ", "Low Calorie    ",
      "Low Cholesterol", "Low Fat        ", "Low Protein    ", "Low Sodium     ",
      "Sea Food       ", "Vegan          ", "Vegetarian     "};

  //
  // data members
  //

  private String        passengerID;  // ID of passenger (9 digits)
  private String        reservationID;  // reservationID of flight (6 uppercase)
  private String        flightNum;  // flight number (3 digits, right justified)
  private Date        flightDate;  // date of flight
  private String        seatNum;  // seat number (3 digits + char, right justified)
  private char        mealType;  // special meal type
  private short        perceivedQuality;  // perceived meal quality (1 through 5)
  private boolean      checkedIn;  // indicates if passenger has checked in
  private boolean      mealLoaded;  // indicates if a passengers meal was loaded

  //
  // accessor methods
  //

  public String  getPassengerID ()      { return passengerID; }
  public String  getReservationID ()    { return reservationID; }
  public String  getFlightNum ()      { return flightNum; }
  public Date  getFlightDate ()      { return flightDate; }
  public String  getSeatNum ()        { return seatNum; }
  public char  getMealType ()        { return mealType; }
  public short  getPerceivedQuality ()    { return perceivedQuality; }
  public boolean  getCheckedIn ()    { return checkedIn; }
  public boolean  getMealLoaded ()    { return mealLoaded; }

  //
  // mutator methods
  //
  public void  setPassengerID (String s)    { passengerID = s.toUpperCase (); }
  public void  setReservationID (String r)    { reservationID = r.toUpperCase (); }
  public void  setFlightNum (String f)    { flightNum = f.toUpperCase (); }
  public void  setFlightDate (Date d)    { flightDate = d; }
  public void  setSeatNum (String s)    { seatNum = s.toUpperCase (); }
  public void  setMealType (char m)    { mealType = m; }
  public void  setPerceivedQuality (short p)    { perceivedQuality = p; }
  public void  setCheckedIn (boolean c)    { checkedIn = c; }
  public void  setMealLoaded (boolean m)    { mealLoaded = m; }

  //
  // public methods
  //
  public synchronized String toString ()
  //
  // toString will compose a string representation of the flight record object
  //
  {
    return passengerID + "  " + reservationID + "\n" +  flightNum + "  " + flightDate + "\n"
        + seatNum + "  " + mealType + "\n" + perceivedQuality + "  " + checkedIn + "  "
        + mealLoaded + "\n";
  }

  public void Copy (CFlightRecord tempFltRec)
  //
  // this will make a copy of tempFltRec into the current object
  //
  {
    this.passengerID = tempFltRec.getPassengerID ();
    this.reservationID = tempFltRec.getReservationID ();
    this.flightNum = tempFltRec.getFlightNum ();
    this.flightDate = tempFltRec.getFlightDate ();
    this.seatNum = tempFltRec.getSeatNum ();
    this.mealType = tempFltRec.getMealType ();
    this.perceivedQuality = tempFltRec.getPerceivedQuality ();
    this.checkedIn = tempFltRec.getCheckedIn ();
    this.mealLoaded = tempFltRec.getMealLoaded ();
  }

  public void  getReservation ()
  //
  // getReservation retrieves flight reservation information
  //
  {
    CPassenger      aPassenger = new CPassenger ();
                      // passenger assigned to this reservation
    boolean        mealOK = false;  // indicates if meal type properly entered
    boolean        dateOK = false;  // indicates if flight date properly entered
    boolean        reservationOK = false;  // indicates if reservationID is valid
    boolean        seatOK = false;  // indicates if seat number is valid
    boolean        flightNumOK = false;  // indicates if flight number ID is valid
    String          flightStrDate;  // used to get a string representing a date
    SimpleDateFormat    flightDateFormat = new SimpleDateFormat ("MMM/dd/yyyy");
                      // used to parse a date

    AirGourmetUtilities.clearScreen ();

    System.out.println ("Please enter the following information about the reservation.\n\n");

    //
    // retrieve and validate a value for reservationID
    //
    while (!reservationOK)
    {
      System.out.print ("Enter the RESERVATION ID: ");
      reservationID = AirGourmetUtilities.readString ();

      if (checkReservationID () )
        if (!alreadyExists () )
          reservationOK = true;
        else
        {
          System.out.println ("\n\tThis RESERVATION ID already exists.\n");
          System.out.println ("\tPlease try again...\n\n");
        }
      else
      {
        System.out.println ("\n\tA RESERVATION ID must be 6 letters.\n");
        System.out.println ("\tPlease try again...\n\n");
      }
    }

    //
    // retrieve and validate a value for flightNum
    //
    while (!flightNumOK)
    {
      System.out.print ("Enter the FLIGHT NUMBER: ");
      flightNum = AirGourmetUtilities.readString ();

      if (checkFlightNum () )
        flightNumOK = true;
      else
      {
        System.out.println ("\n\tA FLIGHT NUMBER must be 3 digits.\n");
        System.out.println ("\tPlease try again...\n\n");
      }
    }

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
        flightDate = flightDateFormat.parse (flightStrDate);
      }
      catch (ParseException pe)
      {
        System.out.println ("\n\tYou entered the date incorrectly.\n");
        System.out.println ("\tPlease use the format mmm/dd/yyyy.\n\n");
        dateOK = false;
      }

    }

    //
    // retrieve and validate a value for seatNum
    //
    while (!seatOK)
    {
      System.out.print ("Enter the SEAT NUMBER assigned to this passenger: ");
      seatNum = AirGourmetUtilities.readString ();

      if (checkSeatNum () )
        if (!seatReserved () )
          seatOK = true;
        else
        {
          System.out.println ("\n\tThis seat is already reserved.\n");
          System.out.println ("\tPlease choose another seat.\n\n");
        }
      else
      {
        System.out.println ("\n\tA SEAT NUMBER must be 3 digits followed by" +
            " an uppercase letter.\n");
        System.out.println ("\tPlease try again...\n\n");
      }
    }

    //
    // retrieve and validate a value for mealType
    //
    while (!mealOK)
    {
      System.out.println ("\n\nList of available special meals:\n\n");
      System.out.println ("  A - Child             B - Diabetic          C - Halaal\n");
      System.out.println ("  D - Kosher            E - Lactose Free      F - Low Cal\n");
      System.out.println ("  G - Low Cholesterol   H - Low Fat           I - Low Protein\n");
      System.out.println ("  J - Low Sodium        K - Sea Food          L - Vegan\n");
      System.out.println ("  M - Vegetarian\n\n");

      System.out.print ("Enter the SPECIAL MEAL for this reservation: ");

      mealType = Character.toUpperCase (AirGourmetUtilities.getChar ());

      if ((mealType >= 'A') && (mealType <= 'M'))
        mealOK = true;
      else
      {
        System.out.println ("Invalid response!\n");
        System.out.println ("Please enter a value from the following list:\n");
      }
    }

    //
    // get passenger information and insert the passenger in the passenger file
    //
    aPassenger.getDescription ();
    aPassenger.insert ();

    //
    // copy the passengerID from the passenger object to the flight record object
    // initialize the values for perceivedQuality, checkedIn, mealLoaded
    //
    passengerID = aPassenger.getPassengerID ();
    perceivedQuality = -1;
    checkedIn = false;
    mealLoaded = false;

    insert ();
    // insert the reservation into the reservation file

  } // getReservation


  public void checkInPassenger ()
  //
  // checkInPassenger sets checkedIn to true for a specific reservation
  //
  {
    boolean        reservationOK = false;  // indicates if reservationID is valid

    AirGourmetUtilities.clearScreen ();
    //
    // retrieve and validate a value for reservationID
    //
    while (!reservationOK)
    {
      System.out.print ("Enter the RESERVATION ID: ");
      reservationID = AirGourmetUtilities.readString ();

      if (checkReservationID () )
        reservationOK = true;
      else
      {
        System.out.println ("\n\tA RESERVATION ID must be 6 letters.\n");
        System.out.println ("\tPlease try again...\n\n");
      }
    }

    //
    // search for the reservation, set checkedIn to true, and then insert the change
    //
    if (alreadyExists () )
    {
      checkedIn = true;
      insert ();

      System.out.println ("\n\n\tThe passenger has been checked in.\n");
      System.out.println ("\tPlease check their identification.\n");

      System.out.println ("\n\nPress <ENTER> to return to main menu...");
      AirGourmetUtilities.pressEnter ();
    }
    else
    {
      System.out.println ("\n\n\tThere is no reservation with this ID...");

      System.out.println ("\n\nPress <ENTER> to return to main menu...");
      AirGourmetUtilities.pressEnter ();
    }

  } // checkInPassenger

  public boolean scanSpecialMeals ()
  //
  // scanSpecialMeals queries the user whether the meal was loaded and then updates the file.
  // It does this for all of the reservations on a specific flight (flight number + flight date)
  //
  {
    boolean        dateOK = false;  // indicates if flight date is properly entered
    boolean        flightNumOK = false;  // indicates if flight number is valid
    boolean        EOF = false;
    char          ch;    // holds user response to Y/N question
    File          fileExists = new File ("fltRec.dat");
                      // used to test if file exists
    CFlightRecord tempFltRec;    // temporary object used for file copying
    String flightStrDate;        // used to get a string representing a date
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
        flightDate = flightDateFormat.parse (flightStrDate);
      }
      catch (ParseException pe)
      {
        System.out.println ("\n\tYou entered the date incorrectly.\n");
        System.out.println ("\tPlease use the format mmm/dd/yyyy.\n\n");
        dateOK = false;
      }

    }

    //
    // retrieve and validate a value for flightNum
    //
    while (!flightNumOK)
    {
      System.out.print ("Enter the FLIGHT NUMBER: ");
      flightNum = AirGourmetUtilities.readString ();

      if (checkFlightNum () )
        flightNumOK = true;
      else
      {
        System.out.println ("\n\tA FLIGHT NUMBER must be 3 digits.\n");
        System.out.println ("\tPlease try again...\n\n");
      }
    }

    AirGourmetUtilities.clearScreen ();

    if (!fileExists.exists ())
      return false;

    try
    {
      ObjectInputStream in = new ObjectInputStream (new FileInputStream ("fltRec.dat"));
      ObjectOutputStream out = new ObjectOutputStream (new FileOutputStream
          ("tempF.dat"));

      while (!EOF)
      {
        try
        {
          //
          // copy the current flight record file to a temporary file
          //
          tempFltRec = (CFlightRecord)in.readObject ();
          out.writeObject (tempFltRec);
        } // try

        catch (EOFException e)
        {
          EOF = true;
        }

      } // while

      in.close ();
      out.close ();
    } // try

    catch (Exception e)
    {
      e.printStackTrace (System.out);
    }

    EOF = false;

    //
    // copy the temporary file to new flight record file
    // while inserting the passenger object in the proper location after
    // updating mealLoaded
    //

    try
    {
      ObjectInputStream in = new ObjectInputStream (new FileInputStream ("tempF.dat"));
      ObjectOutputStream out = new ObjectOutputStream (new FileOutputStream
          ("fltRec.dat"));

      while (!EOF)
      {
        try
        {
          tempFltRec = (CFlightRecord)in.readObject ();

          if ((flightDate.equals (tempFltRec.getFlightDate ()))
              && (flightNum.toUpperCase ().compareTo (tempFltRec.
                getFlightNum ().toUpperCase () ) == 0))
          {
            System.out.println ("\n\nPASSENGER: " + tempFltRec.getPassengerID ());
            System.out.println ("  SEAT: " + tempFltRec.getSeatNum ());
            System.out.println ("  MEAL TYPE: "
                + mealTypeValues[tempFltRec.getMealType () - 'A']);
            System.out.print ("\n\nWas the meal for this passenger loaded (Y/N) ? ");

            ch = AirGourmetUtilities.getChar ();
            if (Character.toUpperCase (ch) == 'Y')
              tempFltRec.setMealLoaded (true);
            else
              tempFltRec.setMealLoaded (false);
          }
          out.writeObject (tempFltRec);

        } // try

        catch (EOFException e)
        {
          EOF = true;
        }

      } // while

      in.close ();
      out.close ();
    } // try

    catch (Exception e)
    {
      e.printStackTrace (System.out);
    }

    return true;
  } // scanSpecialMeals

  public void scanPostcard ()
  //
  // scanPostcard sets perceivedQuality for a specific reservation to a value entered
  // by the user and inserts the change
  //
  {
    boolean        reservationOK = false;   // indicates if reservationID is valid

    AirGourmetUtilities.clearScreen ();

    //
    // retrieve and validate a value for reservationID
    //
    while (!reservationOK)
    {
      System.out.print ("Enter the RESERVATION ID: ");
      reservationID = AirGourmetUtilities.readString ();

      if (checkReservationID () )
        reservationOK = true;
      else
      {
        System.out.println ("\n\tA RESERVATION ID must be 6 letters.\n");
        System.out.println ("\tPlease try again...\n\n");
      }
    }

    //
    // if the reservation exists, get the perceivedQuality and then
    // write the change back to the file
    //
    if (alreadyExists () )
    {
      String tempString;
      System.out.print ("Enter perceived meal quality (1 thru 5):");

      tempString = AirGourmetUtilities.readString ();
      perceivedQuality = (short)Integer.parseInt (tempString);

      insert ();

      System.out.println ("\n\n\tThe passenger record has been updated.\n");

      System.out.println ("\n\nPress <ENTER> to return to main menu...");
      AirGourmetUtilities.pressEnter ();
    }
    else
    {
      System.out.println ("\n\n\tThere is no reservation with this ID...");

      System.out.println ("\n\nPress <ENTER> to return to main menu...");
      AirGourmetUtilities.pressEnter ();
    }

  } // scanPostcard

  public void insert ()
  //
  // insert inserts a flight record object in the proper place
  //
  {
    boolean        found = false;  // indicates if object insertion point found
    File          fileExists = new File ("fltRec.dat");
                      // used to test if file exists
    CFlightRecord      tempFltRec;  // temporary object used for file copying
    boolean        EOF = false;

    try
    {
      ObjectOutputStream out = new ObjectOutputStream (new FileOutputStream
          ("tempF.dat"));

      if (fileExists.exists ())
      {
        ObjectInputStream in = new ObjectInputStream (new FileInputStream
            ("fltRec.dat"));

        while (!EOF)
        {
          try
          {
            // read/write temporary object from the passenger file
            tempFltRec = (CFlightRecord)in.readObject ();
            out.writeObject (tempFltRec);
          }
          catch (EOFException e)
          {
            EOF = true;
          }

        } // while (!EOF)

        in.close ();
      } // if (fileExists.exists ())
      else
        out.writeObject (this);

      out.close ();
    } // try

    catch (Exception e)
    {
      e.printStackTrace (System.out);
    }

    EOF = false;

    try
    {
      ObjectInputStream in = new ObjectInputStream (new FileInputStream ("tempF.dat"));
      ObjectOutputStream out = new ObjectOutputStream (new FileOutputStream
          ("fltRec.dat"));

      while (!EOF)
      {
        try
        {
          tempFltRec = (CFlightRecord)in.readObject ();

          //
          // copy the temporary file to new flight record file
          // while inserting the flight record object in the proper location
          //
          if (reservationID.toLowerCase ().compareTo (tempFltRec.getReservationID
              ().toLowerCase ()) == 0)
          {
            out.writeObject (this);
            found = true;
          }
          else
            out.writeObject (tempFltRec);

        } // try

        catch (EOFException e)
        {
          if (!found)
            out.writeObject (this);

          EOF = true;
        }

      } // while (!EOF)

      in.close ();
      out.close ();
    } // try

    catch (Exception e)
    {
      e.printStackTrace (System.out);
    }

  } // insert

  //
  // private methods
  //

  private boolean  checkReservationID ()
  //
  // checkReservationID determines if the reservationID is valid
  //
  {
    boolean        valid = true;  // indicates if the reservationID is valid
    short          i;      // used to iterate through chars in the
                      // reservationID
    char          ch;

    reservationID = reservationID.toUpperCase ();

    //
    // make sure that the reservationID is composed of exactly 6 chars
    //
    if (reservationID.length () == 6)
    {
      for (i = 0; i<6; i++)
        {
          ch = reservationID.charAt (i);
          if ((!Character.isLetter (ch)) || (ch == ' '))
            valid = false;
        }
    }
    else
      valid = false;

    return valid;

  } // checkReservationID

  private boolean  checkFlightNum ()
  //
  // checkFlightNum determines if the flight number is valid
  //
  {
    boolean        valid = true;  // indicates if the flight number is valid
    short          i;      // used to iterate thru the chars in flightNum
    StringBuffer      tempFltNum = new StringBuffer ();
                      // used in right justification

    //
    // flightNum must be composed of digits only
    //
    for (i = 0; i<flightNum.length (); i++)
    {
      if (!Character.isDigit (flightNum.charAt (i)))
        valid = false;
    }

    // right justify flightNum and zero-fill
    //
    if (valid)
    {
      for (i = 0; i<3; i++)
        if (i < 3 - flightNum.length ())
          tempFltNum.append ('0');
        else
          tempFltNum.append (flightNum.charAt (i + flightNum.length () - 3));

      flightNum = tempFltNum.toString ();

    }
    return valid;

  } // checkFlightNum

  private boolean checkSeatNum ()
  //
  // checkSeatNum determines if the seat number is valid
  //
  {
    boolean        valid = true;  // indicates if the flight number is valid
    short          i;      // used to iterate through chars in seatNum
    StringBuffer  tempSeatNum = new StringBuffer ();
                      // used in right justification

    //
    // seatNum must be composed of digits followed by a single char
    //
    if (Character.isLetter (seatNum.charAt (seatNum.length () - 1)))
    {
      for (i = 0; i< (seatNum.length () - 1); i++)
      {
        if (!Character.isDigit (seatNum.charAt (i)))
          valid = false;
      }
    }
    else
      valid = false;

    // right justify seatNum and zero-fill
    //
    if (valid)
    {
      for (i = 0; i<4; i++)
        if (i < 4 - seatNum.length ())
          tempSeatNum.append ('0');
        else
          tempSeatNum.append (seatNum.charAt (i + seatNum.length () - 4));

      seatNum = tempSeatNum.toString ();
    }

    return valid;

  } // checkSeatNum

  private boolean alreadyExists ()
  //
  // alreadyExists determines if the reservationID of the current object already exists in the file
  //
  {
    boolean        found = false;  // indicates if passenger already exists
    boolean        EOF = false;
    String          searchID;  // the passengerID for which to search
    File          fileExists = new File ("fltRec.dat");
                      // used to test if file exists
    CFlightRecord tempFltRec;    // used to read in object from flight record file

    if (!fileExists.exists ())
      return false;

    searchID = reservationID;

    try
    {
      ObjectInputStream in = new ObjectInputStream (new FileInputStream ("fltRec.dat"));

      while (!EOF)
      {
        try
        {
          //
          // determine if the passenger object already exists
          //
          tempFltRec = (CFlightRecord)in.readObject ();

          //
          // check if there is a match with the searchID
          //
          if (tempFltRec.getReservationID ().toLowerCase ().
              compareTo (searchID.toLowerCase ()) == 0)
          {
            found = true;
            this.Copy (tempFltRec);
            break;
          }
        } // try

        catch (EOFException e)
        {
          EOF = true;
        }

      } // while

      in.close ();
    } // try

    catch (Exception e)
    {
      e.printStackTrace (System.out);
    }

    reservationID = searchID;
    return found;

  } // alreadyExists

  private boolean seatReserved ()
  //
  // seatReserved determines if the seat is already reserved
  //
  {
    boolean        found = false;  // indicates if seat reserved
    File          fileExists = new File ("fltRec.dat");
                      // used to test if file exists
    boolean        EOF = false;
    CFlightRecord tempFltRec;    // used to read in object from flight record file

    if (!fileExists.exists ())
      return false;

    try
    {
      ObjectInputStream in = new ObjectInputStream (new FileInputStream ("fltRec.dat"));

      while (!EOF)
      {
        try
        {
          //
          // check if there is a match with the current seat
          //
          tempFltRec = (CFlightRecord)in.readObject ();

          if ((flightNum.toUpperCase ().compareTo (tempFltRec.getFlightNum ()) == 0)
              && (seatNum.toUpperCase ().compareTo (tempFltRec.
                  getSeatNum ()) == 0)
              && (tempFltRec.getFlightDate () == flightDate))
          {
            found = true;
            break;
          }

        } // try

        catch (EOFException e)
        {
          EOF = true;
        }

      } // while

      in.close ();
    } // try

    catch (Exception e)
    {
      e.printStackTrace (System.out);
    }

    return found;

  } // seatReserved

} // class CFlightRecord


class CReport
{
  //
  // data members
  //

  protected Date      fromDate;  // fromDate and toDate represent the
  protected Date      toDate;  // range of dates used in the report
  protected short      recsPerScreen;  // # of records in a report per screen
  protected boolean    printHeader;  // indicates if theHeader is always to be
                      // printed
  protected String      theHeader;  // title of the report

  //
  // accessor methods
  //

  public Date    getFromDate ()    { return fromDate; }
  public Date    getToDate  ()    { return toDate; }

  //
  // mutator methods
  //
  public void    setFromDate (Date f)  { fromDate = f; }
  public void    setToDate (Date t)  { toDate = t; }

  //
  // overridden methods
  //
  // Description:
  //    -getQualifications:    This method retrieves from the user any values
  //                that are needed to determine if a record
  //                qualifies for inclusion in the report
  //    -qualifiesForReport:  This method applies the qualifications to a
  //                flight record to see if it should be in the report
  //    -printRecord:      This method prints a flight record to the screen
  //                based on the type of report
  //

  protected void getQualifications ()
  //
  // getQualifications is the default method for getting the qualifications.
  // It obtains the start and end dates that define the range of the report
  //
  {
    boolean        dateOK = false;  // indicates if a date was properly entered
    boolean        rangeOK = false;  // indicates if report range properly entered
    String          fromStrDate, toStrDate;
    SimpleDateFormat flightDateFormat = new SimpleDateFormat ("MMM/dd/yyyy");
    Calendar toCalendar = new GregorianCalendar ();
                      // used to convert to date
    Calendar fromCalendar = new GregorianCalendar ();
                      // used to convert from date

    AirGourmetUtilities.clearScreen ();

    while (!rangeOK)
    {
      //
      // retrieve and validate a value for the start date of the report
      //
      while (!dateOK)
      {
        System.out.print ("Enter the start date for the report: ");

        fromStrDate = AirGourmetUtilities.readString ();
        dateOK = true;

        try
        {
          fromDate = flightDateFormat.parse (fromStrDate);
          fromCalendar.setTime (fromDate);
        }
        catch (ParseException pe)
        {
          System.out.println ("\n\tYou entered the date incorrectly.\n");
          System.out.println ("\tPlease use the format mmm/dd/yyyy.\n\n");
          dateOK = false;
        }

      }

      //
      // retrieve and validate a value for the end date of the report
      //
      dateOK = false;
      while (!dateOK)
      {
        System.out.print ("Enter the end date for the report: ");

        toStrDate = AirGourmetUtilities.readString ();
        dateOK = true;

        try
        {
          toDate = flightDateFormat.parse (toStrDate);
          toCalendar.setTime (toDate);
        }
        catch (ParseException pe)
        {
          System.out.println ("\n\tYou entered the date incorrectly.\n");
          System.out.println ("\tPlease use the format mmm/dd/yyyy.\n\n");
          dateOK = false;
        }

      }

      //
      // validate the report date range
      //
      if ((toCalendar.after (fromCalendar)) || (toCalendar.equals (fromCalendar)))
        rangeOK = true;
      else
      {
        System.out.println ("\n\tThe 'end' date is less than the 'start' date.\n");
        System.out.println ("\tPlease enter a valid date range.\n\n");
      }
    } // while (!rangeOK)

  } // getQualifications

  protected boolean qualifiesForReport (CFlightRecord aFlightRecord) {return true;}

  protected void printRecord (CFlightRecord aFlightRecord) {}

  //
  // public method
  //
  public void print ()
  //
  // print is the default method for printing a report.  It iterates through all of the records in the
  // flight record file.  For each record that qualifies for the report, it invokes function printRecord
  //
  {
    int          numRecs = 0;  // count of total flight records
    boolean        EOF = false;
    File          fileExists = new File ("fltRec.dat");
                      // used to test if file exists
    CFlightRecord tempFltRec;    // used to read in object from flight record file

    if (fileExists.exists ())
    {
      getQualifications ();
      AirGourmetUtilities.clearScreen ();

      try
      {
        ObjectInputStream in = new ObjectInputStream (new FileInputStream
            ("fltRec.dat"));

        while (!EOF)
        {
          try
          {
            //
            // determine if the passenger object already exists
            //
            tempFltRec = (CFlightRecord)in.readObject ();

            //
            // check if the flight date is within the given range
            // and that this the record qualifies for the report
            //
            if (qualifiesForReport (tempFltRec))
            {
              if (printHeader)
              {
                //
                // pause the screen after every recsPerScreen flight records
                //
                if (( (numRecs % recsPerScreen) == 0) && (numRecs != 0))
                {
                  System.out.println ("\n\n Press <ENTER> to view" 
                        + " the next screen...");
                  AirGourmetUtilities.pressEnter ();
                }

                //
                // display a header message when needed
                //
                if ((numRecs % recsPerScreen) == 0)
                {
                  AirGourmetUtilities.clearScreen ();
                  System.out.println ("\n\n                         Air Gourmet\n");
                  System.out.println ("      " + theHeader);
                }

              } // if (printHeader)

              //
              // call the method to handle this record
              //
              printRecord (tempFltRec);

              numRecs++;

            } // if (qualifiesForReport (tempFltRec))

          } // try

          catch (EOFException e)
          {
          EOF = true;
          }

        } // while

      in.close ();
      } // try

      catch (Exception e)
      {
      e.printStackTrace (System.out);
      }

      //
      // print the report trailer
      //
      if (printHeader)
      {
        if (numRecs == 0)
          System.out.println ("\n\n\tThere were no records to print...\n\n");

        System.out.println ("\n\nPress <ENTER> to return to main menu...");
        AirGourmetUtilities.pressEnter ();
      }

    } // if (fileExists.exists ())

  } // print

} // class CReport

class CCatererReport extends CReport
{
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

} // class CCatererReport

class COnBoardReport extends CReport
{
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

//class CNotLoadedReport extends CReport
//{
//  protected boolean qualifiesForReport (CFlightRecord aFlightRecord)
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
//
//} // class CNotLoadedReport

class CPoorQualityReport extends CReport
{
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

//---------------------------------------------------------------------------------------------------------------------------------------------------

class CLowSodiumReport extends CReport
{
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
    SimpleDateFormat flightDateFormat = new SimpleDateFormat ("MMM/dd/yyyy");
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


class CPercentageReport extends CReport
{
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

class AirGourmetUtilities
{
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

} // class AirGourmetUtilities

public class ag2
{
  public static void main (String[] args)
  {
    AirGourmetUtilities.displayMainMenu ();
  }

}  // class ag2
