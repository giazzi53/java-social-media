package com.mackenzie.br.socialmedia.utils;

import org.springframework.stereotype.Service;

@Service
public class NameUtils {
	
	  public String capitailizeWord(String str) throws IllegalArgumentException { 
	        StringBuffer s = new StringBuffer(); 

	        char ch = ' '; 
	        for (int i = 0; i < str.length(); i++) { 
	            if (ch == ' ' && str.charAt(i) != ' ') 
	                s.append(Character.toUpperCase(str.charAt(i))); 
	            else
	                s.append(str.charAt(i)); 
	            ch = str.charAt(i); 
	        } 

	        return s.toString().trim(); 
	    } 
	  	
}
