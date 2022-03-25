package ApiMonitoring;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class Company {

    private static final Logger logger = LoggerFactory.getLogger(Company.class);
    private static final String CONST_DATETIME_EXCEPTION = "Formats Date Error.";


    public Company() {
        // Given start Date
        String start_date
                = "10-01-2018 01:10:20";

        // Given end Date
        String end_date
                = "11-01-2018 01:10:20";

        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

        try{
            LocalDateTime FromDate = LocalDateTime.parse(start_date, format);
            LocalDateTime ToDate = LocalDateTime.parse(end_date, format);
            int result = CalculateDiffDate("D", FromDate, ToDate);
            logger.debug(String.valueOf(result));

        }catch (Exception e){
            logger.error(String.valueOf(e));
        }

    }



    //region public static double: CalculateDiffDate
    public static int CalculateDiffDate(String IndicatorDMY, LocalDateTime FromDate, LocalDateTime ToDate) {
        int DiffValue = 0;                  //Declare and Initialise result var
        Duration DiffDate;
        try {
            switch (IndicatorDMY) {
                case "D":
                    DiffDate = Duration.between(FromDate, ToDate);
                    DiffValue = (int) DiffDate.toDays();
                    break;
            }
        } catch (Exception e) {
            logger.error(String.valueOf(e));
        }
        return DiffValue;


    }
    //endregion public static int: CalculateDiffDate


    /// <summary>
    /// public method used to Validate Date Field details
    /// </summary>
    /// <param name="v_intDay">Integer Day</param>
    /// <param name="v_intMonth">Integer Month</param>
    /// <param name="v_intYear">Integer Year</param>
    /// <returns>True if date is correct</returns>
    //region public static boolean : IsValidDate
    public static boolean IsValidDate(int v_intDay, int v_intMonth, int v_intYear) {
        boolean blnValidateStatus = false;


        if (v_intDay > 0 && v_intMonth > 0 && v_intMonth < 13 && v_intYear > 0) {
            // Get the number of days in that month
            YearMonth yearMonthObject = YearMonth.of(v_intYear, v_intMonth);
            int intDaysInMonth = yearMonthObject.lengthOfMonth();


            if (v_intDay <= intDaysInMonth) {
                blnValidateStatus = true;
            }
        }
        return blnValidateStatus;
    }
    //#endregion


    /// <summary>
    /// Checks whether time is valid
    /// </summary>
    /// <param name="v_intHour">integer hour</param>
    /// <param name="v_intMinute">integer minute</param>
    /// <returns>True if it is valid</returns>
    //region public static bool: IsValidTime
    public static boolean IsValidTime(int v_intHour, int v_intMinute) {
        boolean blIsTime = true;


        if (v_intHour < 0 || v_intMinute < 0) {
            blIsTime = false;
        } else {
            if (v_intHour > 24)
                blIsTime = false;


            if (v_intMinute > 60)
                blIsTime = false;
        }
        return blIsTime;
    }
    //endregion


    /// <summary>
    /// public method used to format Date Field details
    /// </summary>
    /// <param name="dt">DateTime to be formatted (dd-mm-yyyy hh:mm:ss am)</param>
    /// <returns>"Error" if there is errors. Else it will return the formatted string format (dd-mm-yyyy)</returns>
    //region public static string: FormatDate (LocalDateTime dt)
    public static String FormatDate(LocalDateTime dt) throws Exception {
        String formattedDate = "";

        try {
            if (IsValidDate(dt.getDayOfMonth(), dt.getMonthValue(), dt.getYear())) {
                if (IsValidTime(dt.getHour(), dt.getMinute())) {
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy hh:mm");
                    formattedDate = dt.format(formatter);
                } else {
                    throw new Exception(CONST_DATETIME_EXCEPTION);
                }
            } else {
                throw new Exception(CONST_DATETIME_EXCEPTION);
            }
        } catch (Exception e) {
            throw new Exception(CONST_DATETIME_EXCEPTION);
        }


        return formattedDate;
    }
    //endregion


/*    /// <summary>
    /// Converts string date (dd-MM-yyyy) to datetime
    /// </summary>
    /// <param name="strFormattedDate">String in dd-MM-yyyy format</param>
    /// <returns>Datetime</returns>
    //region public static DateTime: ConvertToDateTime
    public static LocalDateTime ConvertToDateTime(String strFormattedDate) {
        LocalDateTime dt;
        //DateTime dt = new DateTime();
        if (IsValidFormattedDate(strFormattedDate)) {
            Locale EngGB = new Locale("en-GB");
            //System.Globalization.CultureInfo ciEngGB = new System.Globalization.CultureInfo("en-GB", true);


            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(, EngGB);
            String pattern = ((SimpleDateFormat) formatter).toPattern();
            String localPattern = ((SimpleDateFormat) formatter).toLocalizedPattern();


            dt = LocalDateTime.parse(strFormattedDate, formatter);
        }
        return dt;
    }
    //endregion*/


    /// <summary>
    /// Checks whether it is a valid formatted date (dd-mm-yyyy)
    /// </summary>
    /// <param name="strFormatedDate">string in dd-mm-yyyy format</param>
    /// <returns>True if it is a valid formatted date</returns>
    //region public static bool: IsValidFormattedDate
    public static boolean IsValidFormattedDate(String strFormattedDate) {
        boolean blIsValid = false;

        try {
            String[] arrDate;
            String strDeliminator = "/";


            strFormattedDate = strFormattedDate.trim();
            arrDate = strFormattedDate.split(strDeliminator);
            if (IsValidDate(
                    Integer.parseInt((arrDate[0].trim())),
                    Integer.parseInt((arrDate[1].trim())),
                    Integer.parseInt((arrDate[2].trim())))) {
                blIsValid = true;
            }


        } catch (Exception e) {
        }
        return blIsValid;
    }
    //endregion


}