package com.redsun.platf.entity.stock;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;

import com.redsun.platf.entity.IdEntity;
import com.redsun.platf.entity.stock.type.ProductCategory;
import com.redsun.platf.entity.stock.type.StoragePrice;
import com.redsun.platf.entity.sys.SystemCompany;

/**
 * storage库别.
 * 
 * 使用JPA annotation定义ORM关系. 使用Hibernate annotation定义JPA 1.0未覆盖的部分.
 * 
 * @author calvin
 */
@Entity
// 表名与类名不相同时重新定义表名.
@Table(name = "MB_STORAGE")
// 默认的缓存策略.
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
// enum type define
@TypeDefs( {
	@TypeDef(name = "category", typeClass = ProductCategory.Type.class),
	@TypeDef(name = "price", typeClass = StoragePrice.Type.class) })
public class MbStorage extends IdEntity {

    private String no;
    private String name;
    private String description;

    /** 所属公司 **/
    private SystemCompany company;

    /** 仓库存放产品类别 应对应到enum object **/
    private ProductCategory categoryType;
    /** 单价类别 **/
    private StoragePrice priceType;

    /** 不分AB品 **/
    private boolean noab = false;
    /*** 就记到小类库存 ***/
    private boolean onlyXlph = false;

    // private List<SystemCompany> companysList = Lists.newArrayList();//
    // 有序的关联对象集合

    // 字段非空且唯一, 用于提醒Entity使用者及生成DDL.
    @Column(nullable = false, unique = true)
    public String getName() {
	return name;
    }

    public void setName(String name) {
	this.name = name;
    }

    @Override
    public String toString() {
	return ToStringBuilder.reflectionToString(this);
    }

    @Column(nullable = false, unique = true)
    public String getNo() {
	return no;
    }

    public void setNo(String no) {
	this.no = no;
    }

    public String getDescription() {
	return description;
    }

    public void setDescription(String description) {
	this.description = description;
    }

    @Column(name = "noab", columnDefinition = "bit default b'0'")
    public boolean isNoab() {
	return noab;
    }

    public void setNoab(boolean noab) {
	this.noab = noab;
    }

    @Column(name = "only_xlph", columnDefinition = "bit default b'0'")
    public boolean isOnlyXlph() {
	return onlyXlph;
    }

    public void setOnlyXlph(boolean onlyXlph) {
	this.onlyXlph = onlyXlph;
    }

    // // 多对一单向
    // @ManyToOne
    // // 中间表定义,表名采用默认命名规则
    // @JoinColumn(name = "company_id")
    // // Fecth策略定义
    // @Fetch(FetchMode.SUBSELECT)
    // // 集合按id排序.
    // @OrderBy("id")
    // // 集合中对象id的缓存.
    // @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    // public List<SystemCompany> getCompanysList() {
    // return companysList;
    // }
    //
    // public void setCompanysList(List<SystemCompany> companysList) {
    // this.companysList = companysList;
    // }

    @Type(type = "category")
    @Column(name = "ckType")
    public ProductCategory getCategoryType() {
	return categoryType;
    }

    public void setCategoryType(ProductCategory categoryType) {
	this.categoryType = categoryType;
    }

    @Type(type = "price")
    @Column(name = "djType")
    public StoragePrice getPriceType() {
	return priceType;
    }

    public void setPriceType(StoragePrice priceType) {
	this.priceType = priceType;
    }

    // 一对一单向
    @OneToOne
    // 中间表定义,表名采用默认命名规则
    @JoinColumn(name = "company_id")
    // 集合按id排序.
    @OrderBy("id")
    // 集合中对象id的缓存.
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    public SystemCompany getCompany() {
	return company;
    }

    public void setCompany(SystemCompany company) {
	this.company = company;
    }
}