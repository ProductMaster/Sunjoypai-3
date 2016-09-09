package com.taoists.common.util;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

/**
 * @author Enjoy E-mail:wangyi2200@yahoo.com.cn
 * @version 创建时间：Oct 9, 2009 1:53:01 AM 类说明

 */

@SuppressWarnings({"rawtypes", "unchecked"})
public class RandomStringGenerate {
	
	StringBuffer stringBuffer;
	static Random rand = new Random();
	int length;
	Set set = new HashSet();

	public RandomStringGenerate(int length) {

		this.length = length;
		stringBuffer = new StringBuffer(length);

	}

	public String generate() {
		
		stringBuffer.delete(0, stringBuffer.length());
		int i, type, ascii;

		for (i = 0; i < this.length; i++) {

			type = rand.nextInt(3);
			
			switch (type) {

				case 0:
					ascii = rand.nextInt(10) + 48;
					break;	
				case 1:
					ascii = rand.nextInt(26) + 65;
					break;	
				default:
					ascii = rand.nextInt(26) + 97;

			}
			stringBuffer = stringBuffer.append((char) ascii);

		}
		return stringBuffer.toString().toUpperCase();
	}
	
	public String generateUniqueNum(String prefix){
		StringBuffer str = new StringBuffer(prefix);
		for (int i = this.length+1; i > 1; i--) {
			str= str.append(rand.nextInt(10));
        }
		int size = set.size();
		set.add(str.toString());
		if(set.size() == size) return generateUniqueNum(prefix);
		
       return str.toString();

	}
	
	public static void main(String []arg){
		RandomStringGenerate r = new RandomStringGenerate(6);
		Set set = new HashSet();
		for(int i = 0 ; i<110000 ; i++){
			set.add(r.generateUniqueNum(""));
			
		}
		System.out.println(set.size());
	}
}
