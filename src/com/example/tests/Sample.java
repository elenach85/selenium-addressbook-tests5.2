package com.example.tests;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Sample {

	public static void main(String[] args) {	
	//String line=",test-1317336690,test474498694,!";
	//String[] part = line.split(",");
    //System.out.println(part[2]);
  //  System.out.println(line.split(",").length);
		String text=":";
		String extracttext=getHomeTel(text);
		if (extracttext==null) {
			System.out.println("regex:" + "");
		}
		System.out.println(getHomeTel(text));		
	}
	public static String getHomeTel(String text) {
		Pattern regex = Pattern.compile("H:\\s*\\w*[\\w*\\-*]+w*");
		Matcher matcher = regex.matcher(text);
		if (matcher.find()) {
			return matcher.group();
		} else {
			return "";
		
		}
}
}