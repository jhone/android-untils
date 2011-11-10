package com.redsun.platf.temp.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/* 
 * 元注解@Target,@Retention,@Documented,@Inherited 
 *  
 *     @Target 表示该注解用于什么地方，可能的 ElemenetType 参数包括： 
 *         ElemenetType.CONSTRUCTOR 构造器声明 
 *         ElemenetType.FIELD 域声明（包括 enum 实例） 
 *         ElemenetType.LOCAL_VARIABLE 局部变量声明 
 *         ElemenetType.METHOD 方法声明 
 *         ElemenetType.PACKAGE 包声明 
 *         ElemenetType.PARAMETER 参数声明 
 *         ElemenetType.TYPE 类，接口（包括注解类型）或enum声明 
 *          
 *     @Retention 表示在什么级别保存该注解信息。可选的 RetentionPolicy 参数包括： 
 *         RetentionPolicy.SOURCE 注解将被编译器丢弃 
 *         RetentionPolicy.CLASS 注解在class文件中可用，但会被VM丢弃 
 *         RetentionPolicy.RUNTIME VM将在运行期也保留注释，因此可以通过反射机制读取注解的信息。 
 *          
 *     @Documented 将此注解包含在 javadoc 中 
 *      
 *     @Inherited 允许子类继承父类中的注解 
 *    
 */  
@Target(ElementType.METHOD)  
@Retention(RetentionPolicy.RUNTIME)  
@Documented  
@Inherited  

/* 
 * 定义注解 Test 
 * 注解中含有两个元素 id 和 description 
 * description 元素 有默认值 "no description" 
 */  
public @interface GetId {

	public long idValue();
	
	public String description() default "no desc."; 
}
