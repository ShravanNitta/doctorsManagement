package interview.assignment.patients.utilities;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Util {
    private static final Logger logger = LoggerFactory.getLogger(Util.class);
    public static Timestamp convertClientTimeToTimestamp(String date){
        try {
            Date parsedDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.FFF").parse(date);
            Timestamp timestamp = new java.sql.Timestamp(parsedDate.getTime());
            logger.info("Converted Timestamp ::: "+timestamp);
            return timestamp;
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }
    public static Timestamp convertUITimeToTimestamp(String dateTime){
        try {
            DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm");
            Date parsedDate = formatter.parse(dateTime);
            Timestamp timestamp = new java.sql.Timestamp(parsedDate.getTime());
            logger.info("Converted Timestamp ::: "+timestamp);
            return timestamp;
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }
}
