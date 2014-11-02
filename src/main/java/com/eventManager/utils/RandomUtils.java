package com.eventManager.utils;

import java.util.Random;

public class RandomUtils {
	
	/**
	 * Fonction permettant de générer une String de 20 caractères aléatoire 
	 * @return String aléatoire
	 */
	public String randomURL(){
	char[] chars = "abcdefghijklmnopqrstuvwxyz1234567890ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
	StringBuilder sb = new StringBuilder();
	Random random = new Random();
	for (int i = 0; i < 20; i++) {
	    char c = chars[random.nextInt(chars.length)];
	    sb.append(c);
	}
	String output = sb.toString();
	return output;
	}
}
