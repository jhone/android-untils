package com.redsun.platf.temp.annotation;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

public class GetIdTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Class<? extends Annotation> annnontationClass = GetId.class;

		Method[] methods = GetIdTest.class.getDeclaredMethods();
		for (Method method : methods) {
			/*
			 * 判断方法中是否有指定注解类型的注解
			 */
			System.out.println(method.getName());
			boolean hasAnnotation = method.isAnnotationPresent(annnontationClass);
			if (hasAnnotation) {
				/*
				 * 根据注解类型返回方法的指定类型注解
				 */
				GetId annotation = (GetId) method.getAnnotation(annnontationClass);
				System.out.println("Test( method = " + method.getName()
						+ " , id = " + annotation.idValue()
						+ " , description = "
						+ annotation.description() + " )");
				
			}
		}

	}

	@GetId(idValue = 1l)
	public void getid_1() {

	}

	@GetId(idValue = 2, description = "this id 2")
	private void getid_2() {

		System.out.println("s");
	}

	@GetId(idValue = 3)
	private void getid_3() {

	}
}
