package io.ex;

import java.io.File;

public class ExFileTree {

	public static void main(String[] args) {
		printFileTree(new File("C:/"));
		

	}//main
	
	private static void printFileTree(File file) {
		File[] files = file.listFiles(); // file에 있는 "디렉토리들" 저장
		if(files!=null) {
			for(File f:files) {
				if(f.isDirectory()) {
					System.out.println("["+f.getName()+"]");
					printFileTree(f); // 재귀 호출
				}else {
					System.out.println("\t"+f.getName()+"("+f.length()+")");
				}
			}
		}
	}

}//class
