package com.redsun.platf.util;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springside.modules.orm.PropertyFilter;
import org.springside.modules.orm.PropertyFilter.PropertyType;

public class PropertyFilterUtil {

    private static final String split = "_";

    /**
     * @author P.Y.C
     * @since 1.0.0 (2011/03/16)
     * 
     *        Build a ProperFilter String 根据给定的类、字段、查找内容，建立查找条件
     * @param Class
     *            entity class
     * @param operationType
     *            比较操作符 EQ LQ LIKE
     * @param searchField
     *            查找的字段名称
     * @param searchString
     *            查找的内容
     * 
     * @return list< PropertyFilter>
     */
    public static List<PropertyFilter> buildSearchFilter(Class<?> clzz,
	    PropertyFilter.MatchType operationType, String searchField,
	    String searchString) {

	String propertyTypeCode = decodePropertyType(clzz, searchField);

	List<PropertyFilter> searchfilters = new ArrayList<PropertyFilter>();
	// String split = "_";
	String filterName = operationType + propertyTypeCode + split
		+ searchField;// eg.LIKES_NAME_OR_LOGIN_NAME

	searchfilters.add(new PropertyFilter(filterName, searchString));
	return searchfilters;
    }

    /**
     * 
     * @param clzz
     * @param operationType
     * @param searchField
     * @param searchString
     * @return
     */
    public static List<PropertyFilter> buildSearchFilter(Class<?> clzz,
	    String operationType, String searchField, String searchString) {

	String propertyTypeCode = decodePropertyType(clzz, searchField);

	List<PropertyFilter> searchfilters = new ArrayList<PropertyFilter>();

	// build filter string eg.LIKES_NAME_OR_LOGIN_NAME

	operationType = operationType.toUpperCase();
	// contains ==>like
	if (operationType.equals("CN")) {
	    operationType = PropertyFilter.MatchType.LIKE.toString();
	}

	String filterName = operationType + propertyTypeCode + split
		+ searchField;

	// System.out.println(filterName+",operationType:"+operationType);

	searchfilters.add(new PropertyFilter(filterName, searchString));
	return searchfilters;
    }

    /**
     * get simple code of gen type
     * 
     * @param clazz
     * @param searchField
     * @return 类别代码
     * @see buildSearchFilter
     */
    private static String decodePropertyType(Class<?> clazz, String searchField) {
	Field fieldName = null;
	try {
	    fieldName = clazz.getDeclaredField(searchField);
	} catch (SecurityException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	} catch (NoSuchFieldException e) {
	    // TODO Auto-generated catch block
	    throw new IllegalArgumentException(String.format(
		    "类\"%s\"中不存在字段名\"%s\"！", clazz.getCanonicalName(),
		    searchField));
	    // e.printStackTrace();
	}

	EnumSet<PropertyType> set = EnumSet.allOf(PropertyType.class);

	Map<Class<?>, String> codeMap = new HashMap<Class<?>, String>(set
		.size());
	for (PropertyType tc : set) {
	    codeMap.put(tc.getValue(), tc.toString());
	    // System.out.println(tc.getClass().getSimpleName());
	    // System.out.println(tc.getValue());
	    // System.out.println(tc.toString());
	}
	return eval(codeMap, fieldName.getType());

    }

    /**
     * search type code from map<type,code>
     * 
     * @param codeMap
     * @param clzzType
     * @return 类别代码
     * @see convertPropertyCode
     */
    private static String eval(Map<Class<?>, String> codeMap, Class<?> clzzType) {
	if (clzzType == null)
	    return null;

	if (codeMap.containsKey(clzzType)) {
	    return codeMap.get(clzzType);
	} else {
	    throw new IllegalArgumentException(String.format(
		    "代码\"%s\"不是一个合法的类别代码！", clzzType));
	}
    }
}
