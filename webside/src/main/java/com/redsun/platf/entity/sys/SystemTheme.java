package com.redsun.platf.entity.sys;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.redsun.platf.entity.BaseEntity;
/**
 * 
 * @author dick pan 
 * @version 1.0
 * @since   1.0
 * <p><H3>Change history</H3></p>
 * <p>2011/3/4   : Created </p>
 *                 only config in xml file
 * <p>2011/11/4  : dick pan change to entity </p>
 */

@Entity
// 表名与类名不相同时重新定义表名.
@Table(name = "SYS_THEME")
// 默认的缓存策略.
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class SystemTheme extends BaseEntity {

    
	private static final long serialVersionUID = 6454776063286570207L;
	
	private String theme;
    private String description;

    public SystemTheme() {

    }

    public SystemTheme(String theme, String description) {
	this.theme = theme;
	this.description = description;
    }

   
    public String getDescription() {
	return description;
    }

    public void setDescription(String description) {
	this.description = description;
    }

    /*
     * 名稱唯一
     */
    @Column(length = 50,nullable=false,unique = true)
    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    @Override
    public String toString() {
	return "Theme [theme=" + theme + ",description=" + description + "]" ;
    }

}
