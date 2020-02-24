package com.test.jvm;

import java.io.*;

public class ClassLoaderTest extends ClassLoader{

	private String classLoaderName;
	private final String fileExtension = ".class";

	public ClassLoaderTest(String classLoaderName){
		super();
		this.classLoaderName = classLoaderName;
	}

	public ClassLoaderTest(ClassLoader parent, String classLoaderName){
		super(parent);
		this.classLoaderName = classLoaderName;
	}

	@Override
	public String toString() {
		return "ClassLoaderTest{" +
				"classLoaderName='" + classLoaderName + '\'' +
				"} ";
	}


	@Override
	protected Class<?> findClass(String name) throws ClassNotFoundException {
		byte[] data = this.loadClassData(name);
		return this.defineClass(name, data, 0, data.length);
	}

	private byte[] loadClassData(String name){
		InputStream is = null;
		byte[] data = null;
		ByteArrayOutputStream baos = null;
		this.classLoaderName = this.classLoaderName.replace(".", "/");
		try {
			is = new FileInputStream(new File(name + this.fileExtension));
			baos = new ByteArrayOutputStream();
			int ch = 0;
			while(-1 != (ch = is.read())){
				baos.write(ch);
			}
			data = baos.toByteArray();
		} catch(IOException e) {
			e.printStackTrace();
		} finally {
			try {
				is.close();
				baos.close();
			} catch(Exception e){
				e.printStackTrace();
			}
		}
		return data;
	}

	public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
		ClassLoaderTest loader1 = new ClassLoaderTest("loader1");
		Class<?> clazz = loader1.loadClass("com.test.algorithm.hash.HashTab");
		Object obj = clazz.newInstance();
		System.out.println(obj);
		System.out.println(clazz.getClassLoader());
	}
}
