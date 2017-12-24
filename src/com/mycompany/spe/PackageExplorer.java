package com.mycompany.spe;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class PackageExplorer {

	private static List<String> listOfClasses = new ArrayList<>();
	private static List<ClassInfo> classInfoList = new ArrayList<>();
	
	public static void listFilesForFolder(final File folder) throws Exception {

		if(folder == null){
			throw new FileNotFoundException("Please specify correct directory name");
		}
		
		if(!folder.exists()){
			throw new FileNotFoundException("Please specify correct directory name");
		}
		
	    for (final File fileEntry : folder.listFiles()) {
	        if (fileEntry.isDirectory()) {
	            //listFilesForFolder(fileEntry);
	        } else {
	        	String className = "";
	        	if(fileEntry != null && fileEntry.getName() != null){
	        		className = fileEntry.getName();
	        		className = className.substring(0,className.lastIndexOf("."));
	        	}
	        	
	        	listOfClasses.add(className);
	        	
	        }
	    }
	    
	    
	    
	  //  XmlWritter.write(classInfoList, folder.getAbsolutePath()+"/"+"xml/demo.xml");
	}

	
	private static void processJavaClasses() throws ClassNotFoundException{
		Package packageName = PackageExplorer.class.getPackage();
		for(String className : listOfClasses){
			String claaz = packageName.getName() + "."+className;
			classInfoList.add(prepareClassInfo(claaz));
		}
		
	}
	
	private static ClassInfo prepareClassInfo(String className) throws ClassNotFoundException{
		Class c = Class.forName(className);
		
		ClassInfo classInfo = new ClassInfo();
		classInfo.setClassName(ClassReflection.getClassName(c));
		classInfo.setSuperClassName(ClassReflection.getSuperClassName(c));
		classInfo.setFields(ClassReflection.getClassFields(c));
		classInfo.setMethods(ClassReflection.getClassMethods(c));
		classInfo.setProviders(ClassReflection.getClassProviders(c));
		classInfo.setClassInterface(ClassReflection.getInterfaceName(c));
		return classInfo;
	}

	private static ClassInfo displayAllClassesInfo() throws ClassNotFoundException{
		
		processJavaClasses();
		
		for(ClassInfo classInfo : classInfoList){
			displayClassInfo(classInfo);
		}
		return null;
	
	}
	
	private static void displayClassesName(){
		
		System.out.println("List of classes");
		System.out.println("---------------");
		for (int i = 0; i < listOfClasses.size(); i++) {
			System.out.println( i + ". " + listOfClasses.get(i));
		}
	}
	
	private static void displayClassInfo(ClassInfo classInfo){
		
		System.out.println("Class Details:");
		System.out.println("Name :  " + classInfo.getClassName());
		System.out.println("Superclass  :" + classInfo.getSuperClassName());
		
		if(classInfo.getClassInterface().isEmpty()){
			System.out.println("Interfaces : None");
		}else{
			System.out.println("Interfaces  :" );
		}
		
		for(String strInterface : classInfo.getClassInterface()){
			System.out.println("  " + strInterface);
		}
		
		System.out.println("Fields");
		for(String field : classInfo.getFields()){
			System.out.println("	"+field);
		}
		
		System.out.println("Methods ");
		for(String method : classInfo.getMethods()){
			System.out.println("	"+ method);
		}
		
		
		
		if(classInfo.getProviders().isEmpty()){
			System.out.println(" Providers	: None");
		}else{
			System.out.println("Providers " + classInfo.getProviders());
		}
		
		for(String provider : classInfo.getProviders()){
				System.out.println("	"+provider);
		}
	}
	
	private static void saveClassesInfoInXml() throws FileNotFoundException{
		XmlWritter.saveWholeClassesInfo(classInfoList);
	}
	
	public static void main(String[] args) {
		Scanner scanner = null;
		try {
			
			scanner = new Scanner(System.in);
			int counter =  1;
			while (counter <= 5) {
				System.out.println("Wellcome to packageExplorer - Main Menu");
				System.out.println("----------------------------------------");
				System.out.println("1. List all classes");
				System.out.println("2. View all classes");
				System.out.println("3. Save all classes");
				System.out.println("4. Load class info from xml(Extra Credit)");
				System.out.println("Enter your choice (1-4) or q to quit:");
				String input = scanner.nextLine();
				
				switch(input){
				
				case "1":
					listFilesForFolder(new File(args[0]));
					displayClassesName();
					break;
				case "2" :
					displayAllClassesInfo();
					break;
					
				case "3" :
					saveClassesInfoInXml();
					break;
				
				case "4" :
					System.out.println("Loaded all classes info from xml file");
					displayAllClassesInfo();
					break;
					
				case "q" :
					System.exit(0);
					break;
				}
				
			}
		} catch (Exception exception) {
			exception.printStackTrace();
		}

		finally {

			if (scanner != null) {
				scanner.close();
			}
		}
	}
}
