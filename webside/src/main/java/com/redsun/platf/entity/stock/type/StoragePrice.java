package com.redsun.platf.entity.stock.type;

/**
 * <p>Title        : com.webapp        </p>
 * <p>Description  :  库存计价自定议类型                   </p>
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
import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.EnumSet;
import java.util.HashMap;

import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.usertype.EnhancedUserType;

import com.redsun.platf.util.convertor.Convertor;
import com.redsun.platf.util.convertor.Stringfier;

public enum StoragePrice implements Serializable {
    AVERAGE(1, "加权平均单价", "average"), SALE(2, "销售成本单价", "sale");

    private Integer code;
    private String cnname;
    private String enname;

    private StoragePrice(Integer code, String cnname, String enname) {
	this.code = code;
	this.cnname = cnname;
	this.enname = enname;
    }

    public String getCnname() {
	return cnname;
    }

    public String getEnname() {
	return enname;
    }

    public Integer getCode() {
	return code;
    }

    public String toString() {
	return code.toString();
    }

    public static boolean isAveragePrice(StoragePrice c) {
	return c == AVERAGE;
    }

    public static boolean isSalePrice(StoragePrice c) {
	return c == SALE;
    }

   

    /**
     * 返回当前类型的所有值hashMap
     */
    public static final HashMap<Integer, StoragePrice> codeMap;
    static {
	EnumSet<StoragePrice> set = EnumSet.allOf(StoragePrice.class);
	codeMap = new HashMap<Integer, StoragePrice>(set.size());
	for (StoragePrice c : set) {
	    codeMap.put(c.code, c);
	}
    }

    public static StoragePrice eval(Integer str) {
	// TODO Auto-generated method stub
	if (str == 0) {
	    return null;
	}
	if (codeMap.containsKey(str)) {
	    return codeMap.get(str);
	} else {
	    throw new IllegalArgumentException(String.format(
		    "代码\"%d\"不是一个合法的单价类别代码！", str));
	}
    }

    /**
     * 根据类别代码显示类别
     * 
     */
    public static Convertor<Integer, StoragePrice> convertor = new Convertor<Integer, StoragePrice>() {

	@Override
	public StoragePrice convert(Integer s) {
	    // TODO Auto-generated method stub
	    return StoragePrice.eval(s);
	}

    };

    /**
     * 显示中文
     */
    public static Stringfier<StoragePrice> cnStringfier = new Stringfier<StoragePrice>() {

	@Override
	public String convert(StoragePrice s) {

	    return s.getCnname();
	}

    };
    /**
     * 显示英文
     */
    public static Stringfier<StoragePrice> enStringfier = new Stringfier<StoragePrice>() {
	
	@Override
	public String convert(StoragePrice s) {
	    
	    return s.getEnname();
	}
	
    };

    /**
     * 
     * type define
     * 
     * @author dick pan
     * @version 1.0
     * @since 1.0
     *        <p>
     *        <H3>Change history</H3>
     *        </p>
     *        <p>
     *        2011/3/4 : Created
     *        </p>
     * 
     */

    public static class Type implements EnhancedUserType {
	@Override
	public Object fromXMLString(String arg0) {
	    // TODO Auto-generated method stub
	    return StoragePrice.eval(Integer.parseInt(arg0));
	}

	@Override
	public String objectToSQLString(Object arg0) {
	    // TODO Auto-generated method stub
	    return String.format("\'%s\'", arg0.toString());
	}

	@Override
	public String toXMLString(Object arg0) {
	    // TODO Auto-generated method stub
	    return arg0.toString();
	}

	@Override
	public Object assemble(Serializable arg0, Object arg1)
		throws HibernateException {
	    // TODO Auto-generated method stub
	    return arg0;
	}

	@Override
	public Object deepCopy(Object arg0) throws HibernateException {
	    // TODO Auto-generated method stub
	    return arg0;
	}

	@Override
	public Serializable disassemble(Object arg0) throws HibernateException {
	    // TODO Auto-generated method stub
	    return (Serializable) arg0;
	}

	@Override
	public boolean equals(Object arg0, Object arg1)
		throws HibernateException {
	    // TODO Auto-generated method stub
	    if (arg0 == arg1)
		return true;
	    else
		return false;
	}

	@Override
	public int hashCode(Object arg0) throws HibernateException {
	    // TODO Auto-generated method stub
	    return arg0.hashCode();
	}

	@Override
	public boolean isMutable() {
	    // TODO Auto-generated method stub
	    return false;
	}

	@Override
	public Object nullSafeGet(ResultSet rs, String[] names, Object owner)
		throws HibernateException, SQLException {
	    // TODO Auto-generated method stub
	    String code = rs.getString(names[0]);
	    return rs.wasNull() ? null : StoragePrice.eval(Integer.parseInt(code));
	}

	@SuppressWarnings("deprecation")
	@Override
	public void nullSafeSet(PreparedStatement ps, Object value, int index)
		throws HibernateException, SQLException {
	    // TODO Auto-generated method stub
	    if (value == null) {
		ps.setNull(index, Hibernate.STRING.sqlType());
	    } else {
		ps.setString(index, value.toString());
	    }

	}

	@Override
	public Object replace(Object arg0, Object arg1, Object arg2)
		throws HibernateException {
	    // TODO Auto-generated method stub
	    return arg0;
	}

	@Override
	public Class<?> returnedClass() {
	    // TODO Auto-generated method stub
	    return StoragePrice.class;
	}

	@SuppressWarnings("deprecation")
	@Override
	public int[] sqlTypes() {
	    // TODO Auto-generated method stub
	    return new int[] { Hibernate.STRING.sqlType() };
	}
    }

}
