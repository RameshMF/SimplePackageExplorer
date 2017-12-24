package com.mycompany.spe;

import java.beans.XMLEncoder;
import java.io.BufferedOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.List;

public class XmlWritter {

	public static void write(List<ClassInfo> classesInfo) throws Exception {

		for (ClassInfo classInfo : classesInfo) {
			writeIndivual(classInfo);
		}
	}

	private static void writeIndivual(ClassInfo classInfo) throws Exception {
		XMLEncoder encoder = new XMLEncoder(
				new BufferedOutputStream(new FileOutputStream(classInfo.getClassName())));
		encoder.writeObject(classInfo);
		encoder.close();
	}

	public static void saveWholeClassesInfo(List<ClassInfo> classesInfo) throws FileNotFoundException {
		XMLEncoder encoder = new XMLEncoder(
				new BufferedOutputStream(new FileOutputStream("all_classes_info")));
		encoder.writeObject(classesInfo);
		encoder.close();
	}
}
