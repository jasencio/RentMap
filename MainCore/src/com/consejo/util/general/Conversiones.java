package com.consejo.util.general;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.sql.Clob;


public class Conversiones {

	public static Clob stringToClob(String source) throws Exception {
		return new javax.sql.rowset.serial.SerialClob(source.toCharArray());

	}

	public static String clobToString(Clob cl) throws Exception {
		if (cl == null)
			return "";
		StringBuffer strOut = new StringBuffer();
		String aux = null;
		BufferedReader br = new BufferedReader(cl.getCharacterStream());
		while ((aux = br.readLine()) != null)
			strOut.append(aux);
		return strOut.toString();
	}
	
	// convert InputStream to String
		public static String getStringFromInputStream(InputStream is) throws Exception {
	 
			BufferedReader br = null;
			StringBuilder sb = new StringBuilder();
	 
			String line;
			try {
	 
				br = new BufferedReader(new InputStreamReader(is));
				while ((line = br.readLine()) != null) {
					sb.append(line);
					sb.append("\n");
				}
	 
			} catch (IOException e) {
				throw new Exception(e);
			} finally {
				if (br != null) {
					try {
						br.close();
					} catch (IOException e) {
						//e.printStackTrace();
					}
				}
			}
	 
			return sb.toString();
	 
		}
		
		public static String stringToUTF8(String mensaje){
			
			try {
				return new String(mensaje.getBytes(),"UTF-8");				
			} catch (UnsupportedEncodingException e) {
				return mensaje;
			}
					
					
					
			
		}
		
		
		



}
