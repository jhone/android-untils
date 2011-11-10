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
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.TreeMap;
import java.util.regex.Pattern;

import com.redsun.platf.util.convertor.Convertor;


public final class StringUtil {
        public static final String EMPTY = "";
        public static final Pattern PATTERN_BLANK = Pattern.compile("[\\s]*");
        public static final String EMAIL_PATTERN_STR = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,}){1}$";
        public static final String PASSWORD_PATTERN_STR = "^[A-Za-z0-9~!@#\\$%\\^\\&\\*\\(\\)\\-_\\+=\\[\\]\\{\\}\\\\|;:\\\'\\\"<>,\\.\\?/]{6,16}$";
        /**
         * 判断字符串是否为空，可能是空串或者null
         * @param str
         * @return
         */
        public static boolean isNullOrEmpty(String str) {
                return (str == null || EMPTY.equals(str));
        }

        /**
         * 判断字符串是否不为空，既不是null也不是空串
         * @param str
         * @return
         */
        public static boolean isNotEmpty(String str) {
                return (str != null && !EMPTY.equals(str));
        }

        public static boolean isNotBlank(String str) {
                if(str == null) {
                        return false;
                }
                else {
                        return !PATTERN_BLANK.matcher(str).matches();
                }
        }



        private static final Pattern latin = Pattern.compile("[\\p{InBasicLatin}\\p{InLatin_1_Supplement}\\p{InLatin_Extended_A}\\p{InLatin_Extended_B}]*");
        /**
         * 判断字符串是否全部为英文字母（ASCII）
         * @param str
         * @return
         */
        public static boolean isFullLatin(String str) {
                if(str == null) return true;
                return latin.matcher(str).matches();
        }

        /**
         * 判断是否为汉字字符串
         * @param str
         * @return
         */
        private static final Pattern chinese = Pattern.compile("[\\u4E00-\\u9FFF]*");
        private static final Pattern non_chinese = Pattern.compile("[^\\u4E00-\\u9FFF]+");
        public static boolean isFullChinese(String str) {
                if(str == null) return true;
                return chinese.matcher(str).matches();
        }
        public static boolean isFullChineseIgnoreSpace(String str) {
                if(str == null) return true;
                return chinese.matcher(clearSpace(str)).matches();
        }
        public static String clearNonChinese(String str) {
                if(str == null) return str;
                return non_chinese.matcher(str).replaceAll("");
        }

        /**
         * 不可打印的字符：ASCII码中去掉可打印的。
         */
        private static final String UNPRITABLE = "[\\x00-\\xFF&&[^\\p{Print}]]";
        private static final Pattern PATTERN_TO_REMOVE = Pattern.compile("[\\x00-\\xFF&&[^\\p{Print}]]|^\\s+|\\s+$");
        private static final Pattern PATTERN_TO_REDUCE = Pattern.compile("\\s{2,}");
        /**
         * 删除重复的空白
         * @param str
         * @return
         */
        public static String clearDuplicateSpace(String str) {
                if(str == null) return null;
                //String res = str.replaceAll(UNPRITABLE, " ");
                //return res.trim().replaceAll("\\s{2,}", " ");
                return PATTERN_TO_REMOVE.matcher(PATTERN_TO_REDUCE.matcher(str).replaceAll(" ")).replaceAll("");
        }

        /**
         * 删除所有空白
         * @param str
         * @return
         */
        public static String clearSpace(String str) {
                if(str == null) return null;

                String res = str.replaceAll(UNPRITABLE, " ");
                return res.trim().replaceAll("\\s+", "");
        }

//        /**
//         * 将繁体汉字转化为简体汉字
//         * @param str
//         * @return
//         */
//        public static String simplify(String str){
//                if(str == null) {
//                        return null;
//                }
//                return TSChineseMapping.convertChineseT2S(str);
//        }

        /**
         * 将null转化为空串，避免NullPointerException
         * @param str
         * @return
         */
        public static String avoidNull(String str) {
                return avoidNull(str, EMPTY);
        }

        /**
         * 将null转化为指定空串，避免NullPointerException
         * @param str
         * @return
         */
        public static String avoidNull(String str, String empty) {
                if(str == null) {
                        return empty;
                }
                else {
                        return str;
                }
        }

        /**
         * 将空串转化为null
         * @param str
         * @return
         */
        public static String avoidEmpty(String str) {
                if(str == null || EMPTY.equals(str)) {
                        return null;
                }
                else {
                        return str;
                }
        }

        /**
         * 安全地转化为全大写字母
         * @param str
         * @return
         */
        public static String toUpper(String str) {
                if(str == null) {
                        return null;
                }
                else {
                        return str.toUpperCase();
                }
        }

        public static String toFirstUpper(String str) {
                if(str == null) {
                        return null;
                }

                String [] components = str.split("\\s+");
                StringBuilder sb = new StringBuilder();

                for(String c : components) {
                        if(!c.isEmpty()) {
                                sb.append(c.substring(0, 1).toUpperCase());
                                sb.append(c.substring(1).toLowerCase());
                        }
                        sb.append(" ");
                }
                if(sb.length() > 0) {
                        sb.deleteCharAt(sb.length() - 1);
                }
                return sb.toString();
        }

        /**
         * trim的安全版本
         * @param str
         * @return
         */
        public static String safeTrim(String str) {
                if(str == null) {
                        return null;
                }
                else {
                        return str.trim();
                }
        }

        public static boolean isValidEmail(String str) {
                return avoidNull(str).toLowerCase().matches(EMAIL_PATTERN_STR);
        }

        public static boolean isValidPassword(String str) {
                return avoidNull(str).toLowerCase().matches(PASSWORD_PATTERN_STR);
        }

        private static final String [] CHINESE_CAPITAL_NUMBER = {
                "零", "壹", "贰", "叁", "肆", "伍", "陆", "柒", "捌", "玖"
        };
        public static String toChineseCapitalAmount(Double d) {
                String number = String.format("%.2f", d);
                StringBuffer buffer = new StringBuffer();

                String integer = number.substring(0, number.length() - 3);
                if("0".equals(integer)) {
                        return buffer.toString();
                }
                if(integer.length() > 8) {
                        buffer.append(toChineseCapital4(integer.substring(0, integer.length() - 8)));
                        buffer.append("亿");
                        integer = integer.substring(integer.length() - 8, integer.length());
                }
                if(integer.length() > 4) {
                        buffer.append(toChineseCapital4(integer.substring(0, integer.length() - 4)));
                        buffer.append("万");
                        integer = integer.substring(integer.length() - 4, integer.length());
                }
                buffer.append(toChineseCapital4(integer));
                buffer.append("圆");

                String jiao = number.substring(number.length() - 2 , number.length() - 1);
                String fen = number.substring(number.length() - 1, number.length());
                if("0".equals(jiao)) {
                        if("0".equals(fen)) {
                                buffer.append("整");
                        }
                        else {
                                buffer.append("零");
                                buffer.append(CHINESE_CAPITAL_NUMBER[Integer.parseInt(fen)]);
                                buffer.append("分整");
                        }
                }
                else {
                        buffer.append(CHINESE_CAPITAL_NUMBER[Integer.parseInt(jiao)]);
                        buffer.append("角");
                        if(!"0".equals(fen)) {
                                buffer.append(CHINESE_CAPITAL_NUMBER[Integer.parseInt(fen)]);
                                buffer.append("分");
                        }
                        buffer.append("整");
                }
                return buffer.toString();
        }

        private static String [] CHINESE_CAPITAL_UNIT = {"", "拾", "佰", "仟"};
        private static String toChineseCapital4(String str) {
                if(str.length() > 4) {
                        str = str.substring(str.length() - 4, str.length());
                }
                StringBuffer buffer = new StringBuffer();

                for(int i = 0; i < str.length() && i < 4; i++) {
                        toChineseCapitalIteration(buffer, str, i);
                }

                if(buffer.charAt(buffer.length() -1) == '零') {
                        buffer.delete(buffer.length() - 1, buffer.length());
                }

                return buffer.toString();
        }

        private static void toChineseCapitalIteration(StringBuffer buffer, String str, int index) {
                if(str.charAt(index) == '0') {
                        if(buffer.length() > 0 && buffer.charAt(buffer.length() - 1) == '零') {
                                return;
                        }
                        else {
                                buffer.append("零");
                                return;
                        }
                }
                else {
                        buffer.append(CHINESE_CAPITAL_NUMBER[Integer.parseInt(str.substring(index, index + 1))]);
                        buffer.append(CHINESE_CAPITAL_UNIT[str.length() - index - 1]);
                        return;
                }

        }

        public static <T> String join(String delimeter, T [] array) {
                return join(delimeter, Arrays.asList(array));
        }

        public static <T> String join(String delimeter, Collection<T> c) {
                if(c.size() == 0) {
                        return EMPTY;
                }
                StringBuffer buffer = new StringBuffer();
                for(T obj : c) {
                        buffer.append(obj.toString());
                        buffer.append(delimeter);
                }
                int length = buffer.length();
                buffer.delete(length - delimeter.length(), length);
                return buffer.toString();
        }

        public static <T> String join(String delimeter, Collection<T> c, Convertor<? super T, String> convertor) {
                if(c.size() == 0) {
                        return EMPTY;
                }
                StringBuffer buffer = new StringBuffer();
                for(T obj : c) {
                        buffer.append(convertor.convert(obj));
                        buffer.append(delimeter);
                }
                int length = buffer.length();
                buffer.delete(length - delimeter.length(), length);
                return buffer.toString();
        }


        public static Comparator<List<String>> listComparator = new Comparator<List<String>> () {
//              //  @Override
                public int compare(List<String> o1, List<String> o2) {
                        for(int i = 0; i < Math.min(o1.size(), o2.size()); i++) {
                                int elmentCompare = o1.get(i).compareTo(o2.get(i));
                                if(elmentCompare != 0) {
                                        return elmentCompare;
                                }
                        }
                        return o1.size() - o2.size();
                }

        };

        /**
         * 将基数词专为英文大写的序数词，支持到billion
         * @param number 基数数值
         * @return 英文大写的序数词
         */
        public static String toEnglishCapital(int number) {
                if(number == 0) {
                        return "zero";
                }
                int billion = number / 1000000000;
                int million = (number / 1000000) % 1000;
                int thousand = (number / 1000) % 1000;
                int remainder = number % 1000;

                StringBuffer sb = new StringBuffer();
                if(billion > 0) {
                        sb.append(String.format("%s %s ", toEnglishCapital3Digit(billion), billion > 1 ? "billions" : "billion"));
                }
                if(million > 0) {
                        if(million < 100 & billion > 0) {
                                sb.append("and ");
                        }
                        sb.append(String.format("%s %s ", toEnglishCapital3Digit(million), million > 1 ? "millions" : "million"));
                }
                if(thousand > 0) {
                        if(thousand < 100 & (million > 0 || billion > 0)) {
                                sb.append("and ");
                        }
                        sb.append(String.format("%s %s ", toEnglishCapital3Digit(thousand), thousand > 1 ? "thousands" : "thousand"));
                }
                if(remainder > 0) {
                        if(remainder < 100 & (thousand > 0 || million > 0 || billion > 0 )) {
                                sb.append("and ");
                        }
                        sb.append(toEnglishCapital3Digit(remainder));
                }

                return sb.toString().trim();
        }

        public static String toEnglishCaptitalOrdinal(int number) {
                String cardinal = toEnglishCapital(number);

                int lastSpace = cardinal.lastIndexOf(" ");
                int lastHyphen = cardinal.lastIndexOf("-");

                if(lastSpace == -1 && lastHyphen == -1) {
                        return ordinal.get(cardinal);
                }
                else {
                        int pos = Math.max(lastHyphen, lastSpace) + 1;
                        StringBuffer sb = new StringBuffer();
                        sb.append(cardinal.substring(0, pos));
                        sb.append(ordinal.get(cardinal.substring(pos, cardinal.length())));
                        return sb.toString();
                }



        }


        private static final TreeMap<Integer, String> cardinal;
        static {
                cardinal = new TreeMap<Integer, String>();
                cardinal.put(1, "one");
                cardinal.put(2, "two");
                cardinal.put(3, "three");
                cardinal.put(4, "four");
                cardinal.put(5, "five");
                cardinal.put(6, "six");
                cardinal.put(7, "seven");
                cardinal.put(8, "eight");
                cardinal.put(9, "nine");
                cardinal.put(10, "ten");
                cardinal.put(11, "eleven");
                cardinal.put(12, "twelve");
                cardinal.put(13, "thirteen");
                cardinal.put(14, "fourteen");
                cardinal.put(15, "fifteen");
                cardinal.put(16, "sixteen");
                cardinal.put(17, "seventeen");
                cardinal.put(18, "eighteen");
                cardinal.put(19, "nineteen");
                cardinal.put(20, "twenty");
                cardinal.put(30, "thirty");
                cardinal.put(40, "forty");
                cardinal.put(50, "fifty");
                cardinal.put(60, "sixty");
                cardinal.put(70, "seventy");
                cardinal.put(80, "eighty");
                cardinal.put(90, "ninety");
        }

        private static final TreeMap<String, String> ordinal;
        static {
                ordinal = new TreeMap<String, String>();
                ordinal.put("one", "first");
                ordinal.put("two", "second");
                ordinal.put("three", "third");
                ordinal.put("four", "fourth");
                ordinal.put("five", "fifth");
                ordinal.put("six", "sixth");
                ordinal.put("seven", "seventh");
                ordinal.put("eight", "eighth");
                ordinal.put("nine", "ninth");
                ordinal.put("ten", "tenth");
                ordinal.put("eleven", "eleventh");
                ordinal.put("twelve", "twelfth");
                ordinal.put("thirteen", "thirteenth");
                ordinal.put("fourteen", "fourteenth");
                ordinal.put("fifteen", "fifteenth");
                ordinal.put("sixteen", "sixteenth");
                ordinal.put("seventeen", "seventeenth");
                ordinal.put("eighteen", "eighteenth");
                ordinal.put("nineteen", "nineteenth");
                ordinal.put("twenty", "twentieth");
                ordinal.put("thrity", "thirtieth");
                ordinal.put("forty", "fortieth");
                ordinal.put("fifty", "fiftieth");
                ordinal.put("sixty", "sixtieth");
                ordinal.put("seventy", "seventieth");
                ordinal.put("eighty", "eightieth");
                ordinal.put("ninety", "ninetieth");
                ordinal.put("hundred", "hundredth");
                ordinal.put("hundreds", "hundredth");
                ordinal.put("thousand", "thousandth");
                ordinal.put("million", "millionth");
                ordinal.put("millions", "millionth");
                ordinal.put("billion", "billionth");
                ordinal.put("billions", "billionth");
        }

        private static String toEnglishCapital3Digit(int number) {
                if(number == 0) {
                        return "zero";
                }
                if(number > 1000) {
                        number = number % 1000;
                }

                int hundred = 0;
                if(number > 99) {
                        hundred = number / 100;
                }
                int remainder = number - hundred * 100;

                StringBuffer sb = new StringBuffer();
                if(hundred == 1) {
                        sb.append(String.format("%s %s", cardinal.get(hundred), "hundred"));
                }
                else if(hundred > 1) {
                        sb.append(String.format("%s %s", cardinal.get(hundred), "hundreds"));
                }

                if(hundred > 0 && remainder > 0) {
                        sb.append(" and ");
                }

                if(remainder > 0) {
                        if(remainder < 20) {
                                sb.append(cardinal.get(remainder));
                        }
                        else {
                                int ten = remainder / 10;
                                if(remainder % 10 > 0) {
                                        sb.append(String.format("%s-%s", cardinal.get(ten * 10), cardinal.get(remainder % 10)));
                                }
                                else {
                                        sb.append(cardinal.get(ten * 10));
                                }
                        }
                }
                return sb.toString();
        }

        private static String [] CHINESE_NUMBER = new String [] {
                "零", "一", "二", "三", "四", "五", "六", "七", "八", "九"
        };

        public static String toChineseSerial(int number) {
                StringBuffer buffer = new StringBuffer();

                while(number > 0) {
                        int remainer = number % 10;
                        buffer.insert(0, CHINESE_NUMBER[remainer]);
                        number = number / 10;
                }

                return buffer.toString();
        }

        public static String toChinese(int number) {
                if(number == 0) {
                        return "零";
                }

                int yi = number / 100000000;
                int wan = number / 10000 % 10000;
                int remainer = number % 10000;

                StringBuffer buffer = new StringBuffer();
                if(yi > 0) {
                        buffer.append(toChinese4Digit(yi)).append("亿");
                }
                if(wan > 0) {
                        if(yi > 0 && wan < 1000) {
                                buffer.append("零");
                        }
                        buffer.append(toChinese4Digit(wan)).append("万");
                }
                if(remainer > 0) {
                        if((yi > 0 || wan > 0) && remainer < 1000) {
                                buffer.append("零");
                        }
                        buffer.append(toChinese4Digit(remainer));
                }

                return buffer.toString();
        }

        private static String toChinese4Digit(int number) {
                if(number > 10000) {
                        number = number % 10000;
                }

                int thousand = number / 1000;
                int hundred = number / 100 % 10;
                int ten = number / 10 % 10;
                int remainer = number % 10;

                StringBuffer buffer = new StringBuffer();

                if(thousand > 0) {
                        buffer.append(CHINESE_NUMBER[thousand]).append("千");
                }
                if(hundred > 0) {
                        buffer.append(CHINESE_NUMBER[hundred]).append("百");
                }
                if(ten > 0) {
                        if(thousand > 0 && hundred == 0) {
                                buffer.append("零");
                        }
                        if(ten > 1) {
                                buffer.append(CHINESE_NUMBER[ten]);
                        }
                        buffer.append("十");
                }
                if(remainer > 0) {
                        if(ten == 0 && (thousand > 0 || hundred > 0)) {
                                buffer.append("零");
                        }
                        buffer.append(CHINESE_NUMBER[remainer]);
                }

                return buffer.toString();
        }

        public static Convertor<Object, String> converterObjectToString = new Convertor<Object, String>() {

//              //  @Override
                public String convert(Object s) {
                        // TODO Auto-generated method stub
                        return s == null ? null : s.toString();
                }

        };

        public static Convertor<String, Integer> convertorStringToInteger = new Convertor<String, Integer>(){

//              //  @Override
                public Integer convert(String s) {
                        // TODO Auto-generated method stub
                        if (s == null) return null;
                        return Integer.parseInt(s);
                }

        };

        public static Convertor<String, Date> convertorStringToDate = new Convertor<String, Date>(){

//              //  @Override
                public Date convert(String s) {
                        // TODO Auto-generated method stub
                        return DateUtil.parse(s);
                }

        };

        public static Convertor<Date, String> convertorDateToString = new Convertor<Date, String>() {

//              //  @Override
                public String convert(Date s) {
                        // TODO Auto-generated method stub
                        return DateUtil.formatDateEn(s);
                }

        };

        public static Convertor<String, String> convertorClearSpace = new Convertor<String, String> () {

//              //  @Override
                public String convert(String s) {
                        // TODO Auto-generated method stub
                        return StringUtil.clearSpace(s);
                }

        };
        public static Convertor<String, String> convertorClearDuplicateSpaces = new Convertor<String, String> () {

//              //  @Override
                public String convert(String s) {
                        // TODO Auto-generated method stub
                        return StringUtil.clearDuplicateSpace(s);
                }

        };
        public static Convertor<String, String> convertorClearNonChinese = new Convertor<String, String> () {

              //  @Override
                public String convert(String s) {
                        // TODO Auto-generated method stub
                        return StringUtil.clearNonChinese(s);
                }

        };
        public static Convertor<String, String> convertorForceNull = new Convertor<String, String> () {

              //  @Override
                public String convert(String s) {
                        // TODO Auto-generated method stub
                        return StringUtil.avoidEmpty(s);
                }

        };
        public static Convertor<String, String> convertorAvoidNull = new Convertor<String, String> () {

              //  @Override
                public String convert(String s) {
                        // TODO Auto-generated method stub
                        return StringUtil.avoidNull(s);
                }

        };
        public static String chainConvertors(String str, Convertor<String, String> ... convertors) {
                String res = str;
                for(int i = 0; i < convertors.length; i++) {
                        res = convertors[i].convert(res);
                }
                return res;
        }
}
