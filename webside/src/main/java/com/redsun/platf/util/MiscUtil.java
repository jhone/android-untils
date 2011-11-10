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
import java.io.UnsupportedEncodingException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;
import javax.servlet.http.HttpServletRequest;

public class MiscUtil {

        public static final String UTF_8 = "UTF-8";
        public static final String ISO_8859_1 = "ISO-8859-1";

        private MiscUtil(){}

        public static Vector<String> retrieveIPInformation(HttpServletRequest request) {
                Vector<String> ipChain = new Vector<String>();
                String ip;
                ip = request.getRemoteAddr();
                if(!StringUtil.isNullOrEmpty(ip)) {
                        ipChain.add(ip);
                }
                ip = request.getHeader("x-forwarded-for");
                if(!StringUtil.isNullOrEmpty(ip) || "unknown".equalsIgnoreCase(ip)) {
                        ipChain.add(ip);
                }
                ip = request.getHeader("Proxy-Client-IP");
                if(!StringUtil.isNullOrEmpty(ip) || "unknown".equalsIgnoreCase(ip)) {
                        ipChain.add(ip);
                }
                ip = request.getHeader("WL-Proxy-Client-IP");
                if(!StringUtil.isNullOrEmpty(ip) || "unknown".equalsIgnoreCase(ip)) {
                        ipChain.add(ip);
                }
                return ipChain;
        }

        public static String convertISO8859toUTF8(String value) throws UnsupportedEncodingException {
                return new String(value.getBytes(ISO_8859_1),UTF_8);
        }


        public static Map<String, String> decodeParameters(HttpServletRequest request, boolean decode){
                Map<String, String> decodedMap = new HashMap<String, String>();
                try {
                        Enumeration<?> em = request.getParameterNames();
                        while(em.hasMoreElements()) {
                                String key = (String)em.nextElement();
                                String rawValue = request.getParameter(key);
                                if(decode) {
                                        String value = convertISO8859toUTF8(rawValue);
                                        decodedMap.put(key, value);
                                }
                                else {
                                        decodedMap.put(key, rawValue);
                                }
                        }
                }
                catch(UnsupportedEncodingException ue) {
                        return null;
                }
                return decodedMap;
        }
}
