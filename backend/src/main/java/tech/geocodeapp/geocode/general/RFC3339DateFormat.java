package tech.geocodeapp.geocode.general;

import java.text.FieldPosition;
import java.text.SimpleDateFormat;
import java.util.Date;


public class RFC3339DateFormat extends SimpleDateFormat {

  private static final long serialVersionUID = 1L;

  // Same as ISO8601DateFormat but serializing milliseconds.
  @Override
  public StringBuffer format( Date date, StringBuffer toAppendTo, FieldPosition fieldPosition ) {


    toAppendTo.append( date.toString() );

    return toAppendTo;
  }

}