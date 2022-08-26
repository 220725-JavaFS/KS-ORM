package com.revature;

import com.revature.daos.ORMManager;
import com.revature.daos.ORMManagerImpl;
import com.revature.models.Categories;
import com.revature.models.Clothing;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {
	
	public static void main(String[] args) {
		
		ORMManager o = new ORMManagerImpl();
		Clothing a = new Clothing("TOPS", "T-SHIRT", "RED", "XS", 1.99, 14.95);
		Categories b = new Categories();
		Class<?> c2 = a.getClass();
		Class<?> c3 = b.getClass();
		System.out.println(o.getInfoByItemNo(c2, 5));
		System.out.println(o.getInventoryOfAll(c3));
		o.removeByItemNo(a, 5);
		
		
		System.out.println("Understanding how to use reflection: ");

		StringBuilder sqlStatement = new StringBuilder();
		sqlStatement.append("insert into ");
		System.out.println("\nStarting SQL Statement: \n" + sqlStatement);
		
		Clothing x = new Clothing("TOPS", "T-SHIRT", "RED", "XS", 1.99, 14.95);
		System.out.println("\n.getClass() method: \n" + x.getClass());
		
		Class<?> c1 = x.getClass();
		System.out.println("\n.getName() method: \n" + c1.getName());
		String[] y = c1.getName().split("\\.");
		String className = y[y.length - 1];
		System.out.println("\nClass Name: \n" + className);
		sqlStatement.append(className.toLowerCase());
		
		Field[] fields = c1.getDeclaredFields();
		StringBuilder fieldBuilder = new StringBuilder();
		StringBuilder valueBuilder = new StringBuilder();

		for (Field field : fields) {

			String fieldName = field.getName();

			if (fieldName == "itemNum") {
				continue;
			}

			fieldBuilder.append(fieldName + ", ");		
			
			// obtain the appropriate getter (using the field name)
						String getterName = "get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
						System.out.println("\nGetter Name: " + getterName);
						
						try {
							// obtain the getter method from the class we are mapping
							Method getterMethod = c1.getMethod(getterName);
							System.out.println("Method: " + getterMethod);
							// invoke that method on the object that we are mapping
							Object fieldValue = getterMethod.invoke(x);
							System.out.println("Field Value: " + fieldValue);
							

							valueBuilder.append("'" + fieldValue + "', ");

						} catch (NoSuchMethodException e) {
							e.printStackTrace();
						} catch (SecurityException e) {
							e.printStackTrace();
						} catch (IllegalAccessException e) {
							e.printStackTrace();
						} catch (IllegalArgumentException e) {
							e.printStackTrace();
						} catch (InvocationTargetException e) {
							e.printStackTrace();
						}

	}
		System.out.println("\nFields: \n" + fieldBuilder);
		System.out.println("Values: \n" + valueBuilder);
		
		fieldBuilder.deleteCharAt(fieldBuilder.length() - 2);
		valueBuilder.deleteCharAt(valueBuilder.length() - 2);
		
		System.out.println("\ndeleteCharAt: \n" + fieldBuilder);
		System.out.println("deleteCharAt: \n" + valueBuilder);
		
		sqlStatement.append("(" + fieldBuilder + ") VALUES (" + valueBuilder + ");");
		System.out.println("\nCompleted SQL Statement: \n" + sqlStatement);

	}
}
