package com.redsun.platf.util;

/**
 * <p>Title        : com.webapp        </p>
 * <p>Description  :                   </p>
 * <p>Copyright    : Copyright (c) 2010</p>
 * <p>Company      : FreedomSoft       </p>
 * 
 */

/**
 * @author Dick Pan 
 * @version 1.0
 * @since   1.0
 * <p><H3>Change history</H3></p>
 * <p>2010/10/28   : Created </p>
 *
 */
import java.text.DateFormat;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Vector;
import java.util.regex.Pattern;

public class DateUtil {
        private DateUtil() {}
        public static final SimpleDateFormat DATE_FORMAT_STANDARD = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
        public static final SimpleDateFormat DATE_FORMAT_STANDARD_ALTER = new SimpleDateFormat("yyyy/MM/dd", Locale.US);
        public static final SimpleDateFormat DATE_FORMAT_STANDARD_MONTH = new SimpleDateFormat("yyyy-MM", Locale.US);
        public static final SimpleDateFormat DATE_FORMAT_WESTERN = new SimpleDateFormat("dd/MM/yyyy", Locale.US);
        public static final SimpleDateFormat DATE_FORMAT_WESTERN_DOT = new SimpleDateFormat("dd.MM.yyyy", Locale.US);
        public static final SimpleDateFormat DATE_FORMAT_WESTERN_ALTER = new SimpleDateFormat("MM/dd/yyyy", Locale.US);
        public static final SimpleDateFormat DATE_FORMAT_CN = new SimpleDateFormat("yyyy年MM月dd日", Locale.US);
        public static final SimpleDateFormat DATE_FORMAT_CN_MONTH = new SimpleDateFormat("yyyy年MM月", Locale.US);

        public static final SimpleDateFormat [] ACCEPTED_FORMAT = new SimpleDateFormat[] {
                DATE_FORMAT_STANDARD, DATE_FORMAT_STANDARD_ALTER, DATE_FORMAT_STANDARD_MONTH,
                DATE_FORMAT_WESTERN, DATE_FORMAT_WESTERN_DOT, DATE_FORMAT_WESTERN_ALTER,
                DATE_FORMAT_CN, DATE_FORMAT_CN_MONTH
        };

        public static final SimpleDateFormat TIME = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.US);
        public static final SimpleDateFormat TIME_CN = new SimpleDateFormat("yyyy年MM月dd日HH时mm分ss秒", Locale.US);
        public static final SimpleDateFormat TIME_EN = new SimpleDateFormat("HH:mm:ss, MMMM dd, yyyy", Locale.US);
        public static final SimpleDateFormat TIMESTAMP = new SimpleDateFormat("yyyyMMddHHmmssSSS", Locale.US);

        public static final SimpleDateFormat DATE_EN_YYYYMM_CAPITAL = new SimpleDateFormat("MMM yyyy", Locale.US);
        public static final SimpleDateFormat DATE_EN_UNAMBIGIOUS = new SimpleDateFormat("yyyy-MMM-dd", Locale.US);
        public static final SimpleDateFormat DATE_EN_FORMAL = new SimpleDateFormat("MMMM dd, yyyy", Locale.US);
        public static final SimpleDateFormat DATE_EN_FORMAL_MONTH = new SimpleDateFormat("MMMM yyyy", Locale.US);

        /**
         * 表示年份的正则表达式
         */
        private static final String YEAR = "((?:19|20)[0-9]{2})";
        /**
         * 表示闰年的正则表达式
         */
        private static final String LEAP_YEAR = "(19(?:[13579][26]|[2468][048]|0[48])|20(?:[13579][26]|[02468][048]))";
        /**
         * 表示大月份的正则表达式
         */
        private static final String BIG_MONTH = "(0?[13578]|1[02])";
        /**
         * 表示大月份日期的正则表达式
         */
        private static final String BIG_MONTH_DAY = "(0?[1-9]|[12][0-9]|3[01])";
        /**
         * 表示小月份的正则表达式
         */
        private static final String SMALL_MONTH = "(0?[469]|11)";
        /**
         * 表示小月份日期的正则表达式
         */
        private static final String SMALL_MONTH_DAY = "(0?[1-9]|[12][0-9]|30)";
        /**
         * 表示二月的正则表达式
         */
        private static final String FEB_MONTH = "(0?2)";
        /**
         * 表示二月日期的正则表达式（非闰年）
         */
        private static final String FEB_DAY = "(0?[1-9]|1[0-9]|2[0-8])";
        /**
         * 表示二月二十九日的正则表达式
         */
        private static final String LEAP_FEB_DAY = "(29)";
        /**
         * 表示分隔符的正则表达式
         */
        private static final String DELIMETERS = "(?:[\\.\\-/\\\\]?)";

        private static final String UNIVERSAL_YMD = String.format("%1$s%10$s%2$s%10$s%3$s|%1$s%10$s%4$s%10$s%5$s|%1$s%10$s%6$s%10$s%7$s|%8$s%10$s%6$s%10$s%9$s",
                        YEAR, BIG_MONTH, BIG_MONTH_DAY, SMALL_MONTH, SMALL_MONTH_DAY,
                        FEB_MONTH, FEB_DAY, LEAP_YEAR, LEAP_FEB_DAY, DELIMETERS);
        public static final Pattern UNIVERSAL_PATTERN_YMD = Pattern.compile(UNIVERSAL_YMD);

        private static final String UNIVERSAL_DMY = String.format("%3$s%10$s%2$s%10$s%1$s|%5$s%10$s%4$s%10$s%1$s|%7$s%10$s%6$s%10$s%1$s|%9$s%10$s%6$s%10$s%8$s",
                        YEAR, BIG_MONTH, BIG_MONTH_DAY, SMALL_MONTH, SMALL_MONTH_DAY,
                        FEB_MONTH, FEB_DAY, LEAP_YEAR, LEAP_FEB_DAY, DELIMETERS);
        public static final Pattern UNIVERSAL_PATTERN_DMY = Pattern.compile(UNIVERSAL_DMY);

        private static final String UNIVERSAL_MDY = String.format("%2$s%10$s%3$s%10$s%1$s|%4$s%10$s%5$s%10$s%1$s|%6$s%10$s%7$s%10$s%1$s|%6$s%10$s%8$s%10$s%8$s",
                        YEAR, BIG_MONTH, BIG_MONTH_DAY, SMALL_MONTH, SMALL_MONTH_DAY,
                        FEB_MONTH, FEB_DAY, LEAP_YEAR, LEAP_FEB_DAY, DELIMETERS);
        public static final Pattern UNIVERSAL_PATTERN_MDY = Pattern.compile(UNIVERSAL_MDY);

        private static final String UNIVERSAL_CN = String.format("%1$s年%2$s月%3$s日|%1$s年%4$s月%5$s日|%1$s年%6$s月%7$s日|%8$s年%6$s月%9$s日",
                        YEAR, BIG_MONTH, BIG_MONTH_DAY, SMALL_MONTH, SMALL_MONTH_DAY,
                        FEB_MONTH, FEB_DAY, LEAP_YEAR, LEAP_FEB_DAY, DELIMETERS);
        public static final Pattern UNIVERSAL_PATTERN_CN = Pattern.compile(UNIVERSAL_CN);

        private static final String UNIVERSAL_YM = String.format("%s%s(0?[1-9]|1[0-2])", YEAR, DELIMETERS);
        public static final Pattern UNIVERSAL_PATTERN_YM = Pattern.compile(UNIVERSAL_YM);

//      private static Calendar calendar = Calendar.getInstance();
//
//      private static Date parseDate(String str, Pattern p) {
//              if(StringUtil.isNullOrEmpty(str)) {
//                      return null;
//              }
//              Matcher m = p.matcher(str);
//              if (!m.matches()) {
//                      return null;
//              }
//              if(m.groupCount() != 12) {
//                      return null;
//              }
//              String year = null;
//              String month = null;
//              String day = null;
//              for(int i = 1; i < 12; i += 3) {
//                      if(m.group(i) != null) {
//                              year = m.group(i);
//                              month = m.group(i + 1);
//                              day = m.group(i + 2);
//                              break;
//                      }
//              }
//              synchronized(calendar) {
//                      calendar.set(Integer.parseInt(year), Integer.parseInt(month) - 1, Integer.parseInt(day), 0, 0 ,0);
//                      calendar.set(Calendar.MILLISECOND, 0);
//                      return calendar.getTime();
//              }
//      }
//
//      private static Date parseMonth(String str, Pattern p) {
//              if(StringUtil.isNullOrEmpty(str)){
//                      return null;
//              }
//              Matcher m = p.matcher(str);
//              if (!m.matches()) {
//                      return null;
//              }
//              if(m.groupCount() != 2) {
//                      return null;
//              }
//              String year = m.group(1);
//              String month = m.group(2);
//              synchronized(calendar) {
//                      calendar.set(Integer.parseInt(year), Integer.parseInt(month) - 1, 1, 0, 0, 0);
//                      calendar.set(Calendar.MILLISECOND, 0);
//                      return calendar.getTime();
//              }
//      }

        public static Date parse(String str) {
                if(StringUtil.isNullOrEmpty(str)) {
                        return null;
                }

                ParsePosition pp = new ParsePosition(0);
                for(SimpleDateFormat sf : ACCEPTED_FORMAT) {
                        Date date = sf.parse(str, pp);
                        if(pp.getIndex() != str.length() || pp.getIndex() == 0) {
                                continue;
                        }
                        else {
                                return date;
                        }
                }

                return null;
//              Date date = null;
//              date = parseDate(str, UNIVERSAL_PATTERN_YMD);
//              if(date != null) {
//                      return date;
//              }
//              date = parseMonth(str, UNIVERSAL_PATTERN_YM);
//              if(date != null) {
//                      return date;
//              }
//              date = parseDate(str, UNIVERSAL_PATTERN_DMY);
//              if(date != null) {
//                      return date;
//              }
//              date = parseDate(str, UNIVERSAL_PATTERN_MDY);
//              if(date != null) {
//                      return date;
//              }
//              date = parseDate(str, UNIVERSAL_PATTERN_CN);
//              if(date != null) {
//                      return date;
//              }
//              return null;
        }

        public static String getCurrentDateISO() {
                Date date = new Date(System.currentTimeMillis());
                return DATE_FORMAT_STANDARD.format(date);
        }

        public static String getCurrentDateCN() {
                Date date = new Date(System.currentTimeMillis());
                return DATE_FORMAT_CN.format(date);
        }

        public static String getCurrentTimeISO() {
                Date date = new Date(System.currentTimeMillis());
                return TIME_EN.format(date);
        }

        public static String getCurrentTimeCN() {
                Date date = new Date(System.currentTimeMillis());
                return TIME_CN.format(date);
        }

        public static String format(DateFormat format, Date d) {
                if(d == null) {
                        return null;
                }
                else {
                        return format.format(d);
                }
        }

        public static String formatTime(Date d) {
                return format(TIME, d);
        }

        public static String formatTimeCN(Date d) {
                return TIME_CN.format(d);
        }

        public static String formatTimeEN(Date d) {
                return TIME_EN.format(d);
        }

        public static String formatDateCn(Date d) {
                if(d == null){
                        return "";
                }
                return DATE_FORMAT_CN.format(d);
        }
        public static String formatDateEn(Date d) {
                if(d == null){
                        return "";
                }
                return DATE_FORMAT_STANDARD.format(d);
        }

        public static String formatDateYM(Date d) {
                if(d == null)
                        return null;
                return DATE_FORMAT_STANDARD_MONTH.format(d);
        }

        public static String formatTimeStamp(Date d) {
                if(d == null)
                        return null;
                return TIMESTAMP.format(d);
        }

        public static String getLocaleFormatTime(Date d, String language) {
                if(language.equals("EN")){
                        return DATE_FORMAT_STANDARD.format(d);
                }
                else{
                        return DATE_FORMAT_CN.format(d);
                }
        }

        public static Date[] parseDateArrayString(String strDateArray) {
                if(strDateArray == null) {
                        return null;
                }

                String [] arrDateString = strDateArray.split(",");
                if(arrDateString == null || arrDateString.length == 0) {
                        return null;
                }
                Vector<Date> arrDate = new Vector<Date>(arrDateString.length);

                for(String strDate : arrDateString) {
                        Date date = parse(strDate);
                        if(date != null) {
                                arrDate.add(date);
                        }
                }

                return arrDate.toArray(new Date[arrDate.size()]);
        }

        public static long [] diff(Date startDate, Date endDate) {
                if(startDate == null || endDate == null){
                        return null;
                }
                long start = startDate.getTime();
                long end = endDate.getTime();

                long diff [] = new long [5];
                long diffMill = end - start;
                diff[0] = diffMill / (24 * 60 * 60 * 1000); // day
                diff[1] = diffMill % (24 * 60 * 60 * 1000) / (60 * 60 * 1000); // hour
                diff[2] = diffMill % (60 * 60 * 1000) / (60 * 1000); // minute
                diff[3] = diffMill % (60 * 1000) / 1000; // second
                diff[4] = diffMill % 1000; // millisecond

                return diff;
        }

        public static SimpleDateFormat FULL_MONTH = new SimpleDateFormat("MMMM", Locale.US);
        public static String formatDateCapitalFormal(Date date) {
                if(date == null) {
                        return null;
                }
                Calendar cal = Calendar.getInstance();
                cal.setTime(date);
                return String.format("the %s day of %s, %s",
                                StringUtil.toEnglishCaptitalOrdinal(cal.get(Calendar.DAY_OF_MONTH)),
                                FULL_MONTH.format(date),
                                StringUtil.toEnglishCapital(cal.get(Calendar.YEAR)));

        }
}

