package com.consejo.util.general;

import java.net.InetAddress;
import java.net.NetworkInterface;

public class AddressUtils {

	public static String getMacAddress() {
		
		
		 NetworkInterface a;
		  
		 try {
		  a = NetworkInterface.getByInetAddress(InetAddress.getLocalHost());
		  byte[] mac = a.getHardwareAddress();

		  StringBuilder sb = new StringBuilder();
		  for (int i = 0; i < mac.length; i++) {
		  sb.append(String.format("%02X%s", mac[i], (i < mac.length - 1) ? "-" : ""));  
		  } 
		  return sb.toString();
		  } catch (Exception e) {
		  //e.printStackTrace(); 
		  return null;
		  }
		
	}
	
}
