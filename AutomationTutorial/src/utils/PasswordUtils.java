package utils;

import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

/**
 *Password Utils - Encrypt & Decrypt the password
 *@author Sangam
 */
public class PasswordUtils {
	
	
	
private static final String ALGO = "AES";
private static final byte[] keyValue = new byte[] {'T','h','e','B','e','s','t','S','e','c','r','e','t','K','e','y'};

/**
 * 
 * encryptString - Encrypts the passed value and returns the encrypted string
 * @param data
 * @return
 * @throws InvalidKeyException
 * @throws NoSuchAlgorithmException
 * @throws NoSuchPaddingException
 */
public static String encryptString(String data) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException{
	String encryptedValue = "";
	try {
		Key key = generateKey();
		Cipher c = Cipher.getInstance(ALGO);
		
		c.init(Cipher.ENCRYPT_MODE, key);
		byte[] encodedValue = c.doFinal(data.getBytes());
		encryptedValue = Base64.encodeBytes(encodedValue);
		System.out.println("Encrypted String for "+ data + " = "+ encryptedValue);
	} catch (IllegalBlockSizeException e) {
 		e.printStackTrace();
	} catch (BadPaddingException e) {
 		e.printStackTrace();
	}
	
	 
	return encryptedValue;
	
	
}


/**
 * decryptString - decrypts the encrypted String and retuns the decrypted value
 * @param encryptedData
 * @return
 * @throws Exception
 */
public static String decryptString(String encryptedData) throws Exception{
	String decryptedValue = "";
	try {
		Key key = generateKey();
		Cipher c = Cipher.getInstance(ALGO);
		
		c.init(Cipher.DECRYPT_MODE, key);
		byte[] decodedValue = Base64.decode(encryptedData);
		byte[] decValue = c.doFinal(decodedValue);
		decryptedValue = new String(decValue);
		System.out.println("Decrypted String for "+ encryptedData + " = "+ decryptedValue);
	
	} catch (IllegalBlockSizeException e) {
 		e.printStackTrace();
	} catch (BadPaddingException e) {
 		e.printStackTrace();
	}
 	 
	return decryptedValue;
	
	
}




private static Key generateKey(){
	Key key = new SecretKeySpec(keyValue, ALGO);
	return key;
}

}
