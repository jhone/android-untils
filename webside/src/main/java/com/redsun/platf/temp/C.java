package com.redsun.platf.temp;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springside.modules.orm.PropertyFilter;
import org.springside.modules.orm.PropertyFilter.PropertyType;

import com.redsun.platf.entity.sys.SystemValue;

/**
 * <p>Title        : com.webapp        </p>
 * <p>Description  :                   </p>
 * <p>Copyright    : Copyright (c) 2011</p>
 * <p>Company      : FreedomSoft       </p>
 * 
 */

/**
 * @author dick pan
 * @version 1.0
 * @since 1.0
 *        <p>
 *        <H3>Change history</H3>
 *        </p>
 *        <p>
 *        2011/2/23 : Created
 *        </p>
 * 
 */
@SuppressWarnings("unchecked")
public class C {

    private String searchString = "aa";


   
    public static void main(String[] args) throws Exception,
	    NoSuchFieldException {

	C s = new C();
	String searchField = "sysKey";
	List<PropertyFilter> searchfilters = s.buildSearchFilter(searchField,
		PropertyFilter.MatchType.LIKE, "_");
	System.out.println(searchfilters);

	Class<SystemValue> c = SystemValue.class;
//	Field[] fs = c.getDeclaredFields();
//
//	for (Field f : fs) {
//	    System.out.println(f);
//	    System.out.println(f.getType());
//	}
//	Field field = c.getDeclaredField(fieldName);
//	
//	System.out.println("type of " + fieldName + " is:" + field.getType());
	Field fieldName = c.getDeclaredField(searchField);
	String fieldType = fieldName.getType().toString();
	
	EnumSet<PropertyType> set = EnumSet.allOf(PropertyType.class);
	
	Map<Class, PropertyType> codeMap = new HashMap<Class, PropertyType>(set.size());
	for (PropertyType tc : set) {
	    codeMap.put(tc.getClass(), tc);
	    System.out.println(tc.getClass());
	    System.out.println(tc.getValue());
	    System.out.println(tc.toString());
	}
	System.out.println(s.eval(codeMap,fieldType) );
	
	
	
    }

    private PropertyType  eval(Map<Class, PropertyType> codeMap,String fieldType){
	if (fieldType==null)
	    return null;
	
	if (codeMap.containsValue(fieldType)) {
	    return codeMap.get(fieldType);
	} else {
	    throw new IllegalArgumentException(String.format(
		    "代码\"%s\"不是一个合法的类别代码！", fieldType));
	}
    }
    /**
     * @return
     */
    private List<PropertyFilter> buildSearchFilter(String fileldName,
	    PropertyFilter.MatchType operationType, String split) {
	List<PropertyFilter> searchfilters = new ArrayList<PropertyFilter>();
	// String split = "_";
	String filterName = operationType
		+ PropertyFilter.PropertyType.S.toString() + split + fileldName;// eg.LIKES_NAME_OR_LOGIN_NAME
	searchfilters.add(new PropertyFilter(filterName, searchString));
	return searchfilters;
    }

}
