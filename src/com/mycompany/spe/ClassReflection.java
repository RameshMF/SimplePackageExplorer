package com.mycompany.spe;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ClassReflection {

	public static String getClassName(Class className) {
		return className.getSimpleName();
	}
	
	public static String getSuperClassName(Class className) {
		return className.getSuperclass().getSimpleName();
	}

	public static List<String> getInterfaceName(Class className) {
		
		Class[] interfaces = className.getInterfaces();
		List<String> interfacesImpl = new ArrayList<>();
		for(Class c : interfaces){
			interfacesImpl.add(c.getName());
		}
		return interfacesImpl;
	}

	public static List<String> getClassFields(Class className) {
		
		Field[] fields = className.getFields();
		List<String> classFields = new ArrayList<>();
		for(Field field : fields){
			classFields.add(field.getName());
		}
		return classFields;
	}

	public static List<String> getClassMethods(Class className) {
		
		Method[] methods = className.getMethods();
		List<String> classMethods = new ArrayList<>();
		for(Method method : methods){
			classMethods.add(method.getName());
		}
		return classMethods;
	}

	public static List<String> getClassProviders(Class className) {
		Map<String, List<String>> mapProviders = new HashMap<>();
		List<String> providersList = new ArrayList<>();
		Field[] fields = className.getFields();
		for(Field field : fields){
			if(field.getType().isEnum()){
				providersList.add(field.getType().getName());
			}
			
			if(field.getType().isInterface()){
				providersList.add(field.getType().getName());
			}
			
			if(!field.getType().isEnum() && !field.getType().isInterface() && !field.getType().isArray()
					&& !field.getType().isPrimitive() && !field.getType().getSimpleName().equals("String")){
			}
			
		}
		
		mapProviders.put("providers", providersList);
		return providersList;
	}

	public static String getClassClients(String className) {
		return className;
	}

	public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
		Class c = Class.forName("com.companyname.spe.ClassInfo");
		System.out.println("class Name  ===========>" + c.getSimpleName() );
		System.out.println(c.getSuperclass().getName());
		//c.getSimpleName();
		//getSuperClassName(c);
		//getClassMethods(c);
		//getClassFields(c);
		//getInterfaceName(c);
		getClassProviders(c);
	}

}
