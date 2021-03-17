package com.nuriweb.mybom.controller;

public class test {

	public static void main(String[] args) {
		String mainFilePath = "/resources/images/center/UP_ta_1.png";
		String pr1FilePath = "/resources/images/center/programs/UP_ta_2.png";
		String pr2FilePath = "/resources/images/center/programs/UP_ta_3.jpg";
		
		String main = (mainFilePath.substring(mainFilePath.lastIndexOf("/"))).substring(1);
		String pr1 = (pr1FilePath.substring(pr1FilePath.lastIndexOf("/"))).substring(4);
		String pr2 = (pr2FilePath.substring(pr2FilePath.lastIndexOf("/"))).substring(4);
		
		System.out.println(main);
		System.out.println(pr1);
		System.out.println(pr2);
		
		
		String tags = "부부,가족,친구";
	    System.out.println( "부부태그 있냐? " + tags.contains("친구") );  
	
	}
}
