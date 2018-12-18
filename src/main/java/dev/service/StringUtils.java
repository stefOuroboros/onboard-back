package dev.service;

import org.springframework.stereotype.Service;

@Service
public class StringUtils {
	
	public static boolean isEmpty(String ch) {
		return ch == null || ch.trim().isEmpty() || ch.trim().equals("null");
	}
	
	public static String convert(String ch) {
		if (ch!=null && ch.equals("null")) {
			return "";
		}
		return ch;
	}
	
}
