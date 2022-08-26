package com.revature.daos;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import com.revature.utils.ConnectionUtil;

public class ORMManagerImpl implements ORMManager{

	@Override
	public void insertNewItem(Object o) {
		
			//Create initial SQL statement with stringBuilder (or Buffer)
			StringBuilder sqlStatement = new StringBuilder();
			sqlStatement.append("insert into \"");
			
			// obtain object's class path and use it to obtain table name for SQL statement
			Class<?> objectClass = o.getClass(); 
			String[] y = objectClass.getName().split("\\.");
			String className = y[y.length - 1];
			sqlStatement.append(className.toLowerCase() + "\"");
			
			//hold Objects declared fields
			Field[] fields = objectClass.getDeclaredFields();
			//create string of those declared fields
			StringBuilder fieldBuilder = new StringBuilder();
			//create string of corresponding values of those declared fields
			StringBuilder valueBuilder = new StringBuilder();
			
			//iterate over fields
			for (Field field : fields) {

				String fieldName = field.getName();

				//skip the itemNum field as it is a serial in the table
				if (fieldName == "itemNum") {
					continue;
				}

				//adds declared fields to stringBuilder
				fieldBuilder.append(fieldName + ", ");		
				
				// obtain the appropriate getter (using the field name)
				//fieldName.substring(0,1).toUpperCase() + fieldName.substring(1) Capitalizes the first letter of the fieldName 
				String getterName = "get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
							
							try {
								// obtain the getter method from the class we are mapping
								Method getterMethod = objectClass.getMethod(getterName);
								// invoke that method on the object that we are mapping (to obtain corresponding values to declared fields) 
								Object fieldValue = getterMethod.invoke(o);

								//adds field values to stringBuilder
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
			//gets rid of hanging comma at the end of each StringBuilder
			fieldBuilder.deleteCharAt(fieldBuilder.length() - 2);
			valueBuilder.deleteCharAt(valueBuilder.length() - 2);
			
			//adds fields and values to complete the SQL statement
			sqlStatement.append("(" + fieldBuilder + ") VALUES (" + valueBuilder + ");");
			
			//converts StringBuilder SQL statement to String
			String sql = sqlStatement.toString();
			
			try(Connection conn = ConnectionUtil.getConnection()) {
				PreparedStatement statement = conn.prepareStatement(sql);
				statement.executeUpdate();

			} catch (SQLException e) {
				e.printStackTrace();
			}
	}

	@Override
	public <T> List<T> getInfoByItemNo(Class<T> unknownClass, int itemNum) {
		
		try(Connection conn = ConnectionUtil.getConnection()){
			String[] x = unknownClass.getName().split("\\.");
			String className = x[x.length - 1];
			String sql = "SELECT * from \"" + className.toLowerCase() + "\" full join categories on categories.category = " + className + ".category where itemnum = " 
					+ itemNum + ";";	
			Statement statement = conn.createStatement();
			ResultSet result = statement.executeQuery(sql);
			
			ArrayList<T> unknownObjects = new ArrayList<T>();
			
			while(result.next()) { 
				try {
					// create a new instance of the class being constructed
					T newObject = unknownClass.getDeclaredConstructor().newInstance();
				
				Field[] fields = unknownClass.getFields();
				for (Field field : fields) {

					String fieldName = field.getName();

					// obtain the appropriate getter (using the field name)
					String setterName = "set" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
					try {
						// getting the type of the setter parameter, based on the field type
						Class<?> setterParamType = unknownClass.getDeclaredField(fieldName).getType();

						// obtain the setter method using the setter name and setter parameter type
						Method setter = unknownClass.getMethod(setterName, setterParamType);

						// below we define a utility method to convert the string field value to the

						// appropriate type for the field
						Object fieldValue = convertStringToFieldType(result.getString(fieldName), setterParamType);

						// we invoke the setter to populate the field of the object that's being created
						setter.invoke(newObject, fieldValue);

					} catch (NoSuchFieldException e) {
						e.printStackTrace();
					} catch (NoSuchMethodException e) {
						e.printStackTrace();
					} catch (IllegalAccessException e) {
						e.printStackTrace();
					} catch (InvocationTargetException | InstantiationException e) {
						e.printStackTrace();
					}
				}

				unknownObjects.add(newObject);
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException
				| InvocationTargetException | NoSuchMethodException | SecurityException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		}
			return unknownObjects;
	} catch (SQLException e) {
		e.printStackTrace();
	}
			return null;
	}

	@Override
	public <T> List<T> getInventoryOfAll(Class<T> unknownClass) {
		try(Connection conn = ConnectionUtil.getConnection()){
			String[] x = unknownClass.getName().split("\\.");
			String className = x[x.length - 1];
			String sql = "SELECT * from \"" + className.toLowerCase() + "\";";	
			Statement statement = conn.createStatement();
			ResultSet result = statement.executeQuery(sql);
			
			ArrayList<T> unknownObjects = new ArrayList<T>();
			
			while(result.next()) { 
				try {
					// create a new instance of the class being constructed
					T newObject = unknownClass.getDeclaredConstructor().newInstance();
				
				Field[] fields = unknownClass.getFields();
				for (Field field : fields) {

					String fieldName = field.getName();

					// obtain the appropriate getter (using the field name)
					String setterName = "set" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
					try {
						// getting the type of the setter parameter, based on the field type
						Class<?> setterParamType = unknownClass.getDeclaredField(fieldName).getType();

						// obtain the setter method using the setter name and setter parameter type
						Method setter = unknownClass.getMethod(setterName, setterParamType);

						// below we define a utility method to convert the string field value to the

						// appropriate type for the field
						Object fieldValue = convertStringToFieldType(result.getString(fieldName), setterParamType);

						// we invoke the setter to populate the field of the object that's being created
						setter.invoke(newObject, fieldValue);

					} catch (NoSuchFieldException e) {
						e.printStackTrace();
					} catch (NoSuchMethodException e) {
						e.printStackTrace();
					} catch (IllegalAccessException e) {
						e.printStackTrace();
					} catch (InvocationTargetException | InstantiationException e) {
						e.printStackTrace();
					}
				}

				unknownObjects.add(newObject);
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException
				| InvocationTargetException | NoSuchMethodException | SecurityException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		}
			return unknownObjects;
	} catch (SQLException e) {
		e.printStackTrace();
	}
			return null;
	}

	@Override
	public void updateByItemNo(Object o, int itemNum, String column, String value) {
		//Create initial SQL statement with stringBuilder (or Buffer)
		StringBuilder sqlStatement = new StringBuilder();
		sqlStatement.append("update \"");
		
		// obtain object's class path and use it to obtain table name for SQL statement
		Class<?> objectClass = o.getClass(); 
		String[] y = objectClass.getName().split("\\.");
		String className = y[y.length - 1];
		sqlStatement.append(className.toLowerCase() + "\" set " + column + "= '" + value + "' where itemnum ="  + itemNum + ";" );
		
		//converts StringBuilder SQL statement to String
		String sql = sqlStatement.toString();
		
		try(Connection conn = ConnectionUtil.getConnection()) {
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void removeByItemNo(Object o, int itemNum) {
		//Create initial SQL statement with stringBuilder (or Buffer)
		StringBuilder sqlStatement = new StringBuilder();
		sqlStatement.append("delete from \"");

		// obtain object's class path and use it to obtain table name for SQL statement
				Class<?> objectClass = o.getClass(); 
				String[] y = objectClass.getName().split("\\.");
				String className = y[y.length - 1];
				sqlStatement.append(className.toLowerCase() + "\" where itemnum = " + itemNum + ";");
				
				//converts StringBuilder SQL statement to String
				String sql = sqlStatement.toString();
				
				try(Connection conn = ConnectionUtil.getConnection()) {
					PreparedStatement statement = conn.prepareStatement(sql);
					statement.executeUpdate();
				} catch (SQLException e) {
					e.printStackTrace();
				}
	}
	
	public Object convertStringToFieldType(String input, Class<?> type)
			throws IllegalAccessException, InstantiationException, IllegalArgumentException, InvocationTargetException,
			NoSuchMethodException, SecurityException {
		switch (type.getName()) {
		case "byte":
			return Byte.valueOf(input);
		case "short":
			return Short.valueOf(input);
		case "int":
			return Integer.valueOf(input);
		case "long":
			return Long.valueOf(input);
		case "double":
			return Double.valueOf(input);
		case "float":
			return Float.valueOf(input);
		case "boolean":
			return Boolean.valueOf(input);
		case "java.lang.String":
			return input;
		default:
			return type.getDeclaredConstructor().newInstance();
		}
	}
}
