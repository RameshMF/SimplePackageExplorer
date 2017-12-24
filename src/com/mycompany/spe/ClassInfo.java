package com.mycompany.spe;

import java.util.List;

public class ClassInfo {

	private String className;
	private String superClassName;
	private List<String> classInterface;
	private List<String> fields;
	private List<String> methods;
	private List<String> providers;
	
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	public String getSuperClassName() {
		return superClassName;
	}
	public void setSuperClassName(String superClassName) {
		this.superClassName = superClassName;
	}
	public List<String> getClassInterface() {
		return classInterface;
	}
	public void setClassInterface(List<String> classInterface) {
		this.classInterface = classInterface;
	}
	public List<String> getFields() {
		return fields;
	}
	public void setFields(List<String> fields) {
		this.fields = fields;
	}
	public List<String> getMethods() {
		return methods;
	}
	public void setMethods(List<String> methods) {
		this.methods = methods;
	}
	public List<String> getProviders() {
		return providers;
	}
	public void setProviders(List<String> providers) {
		this.providers = providers;
	}
	
}
