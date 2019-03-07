package com.syntaxphoenix.smoothtimber.utilities;

import java.security.SecureRandom;

public class Keys {
	
	private static final String ALPHABET = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final SecureRandom RANDOM = new SecureRandom();
	
	public static String generateKey(Integer lenght) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < lenght; ++i) {
            sb.append(ALPHABET.charAt(RANDOM.nextInt(ALPHABET.length())));
        }
        return sb.toString();
	}

}
