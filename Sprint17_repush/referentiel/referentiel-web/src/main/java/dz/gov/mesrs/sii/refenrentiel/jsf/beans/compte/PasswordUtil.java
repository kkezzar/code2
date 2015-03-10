/**
 * [dz.gov.mesrs.sii.refenrentiel.jsf.beans.compte.PasswordUtil.java] 
 * @author BELDI Jamel on : 25 f�vr. 2014  18:36:36
 */
package dz.gov.mesrs.sii.refenrentiel.jsf.beans.compte;

import java.security.Key;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.apache.commons.codec.binary.Base64;


/**
 * @author BELDI Jamel  on : 25 f�vr. 2014  18:36:36
 * permettant de crypter ou de d�crypter des String Java et
 *  ce en utilisant une cl� de cryptage ainsi que les outils du package Cypher
 */

public class PasswordUtil {
	private static final Log log = LogFactory.getLog(PasswordUtil.class);
	public static final String KEY = "MESRS";
	/**
	 * [PasswordUtil.PasswordUtil()] Constructor
	 * @author BELDI Jamel  on : 25 f�vr. 2014  18:36:36	
	 */
	public PasswordUtil() {
		
	}
	
	/**
	 * [PasswordUtil.encrypt] Method 
	 * @author BELDI Jamel  on : 25 f�vr. 2014  18:39:20
	 * @param password
	 * @param key
	 * @return
	 * @throws Exception 
	 */
	public static String encrypt(String password,String key) throws Exception{
		try
		{
		Key clef = new SecretKeySpec(key.getBytes("ISO-8859-1"),"Blowfish");//ISO-8859-2
		Cipher cipher=Cipher.getInstance("Blowfish");
		cipher.init(Cipher.ENCRYPT_MODE,clef);
		return new String(cipher.doFinal(password.getBytes()));
		}
		catch (Exception e)
		{
			log.error("encrypt failed", e);
			throw e;
		}
		}
	
	/**
	 * [PasswordUtil.decrypt] Method 
	 * @author BELDI Jamel  on : 25 f�vr. 2014  18:39:26
	 * @param password
	 * @param key
	 * @return
	 * @throws Exception 
	 */
	public static String decrypt(String password,String key) throws Exception{
		try
		{
		Key clef = new SecretKeySpec(key.getBytes("ISO-8859-1"),"Blowfish");
		Cipher cipher=Cipher.getInstance("Blowfish");
		cipher.init(Cipher.DECRYPT_MODE,clef);
		return new String(cipher.doFinal(password.getBytes()));
		}
		catch (Exception e)
		{
		
		log.error("decrypt failed", e);
		throw e;
		}
		}
	
	
	
	//Si vous ne souhaitez pas utiliser la crypto Cypher, vous pouvez utiliser un code plus simple :
		

		    /**
		     * [PasswordUtil.encrypt] Method 
		     * @author BELDI Jamel  on : 25 f�vr. 2014  18:43:50
		     * @param password
		     * @return
		     */
		    public static String encrypt(String password){
		        String crypte =  "";
		        for (int i=0; i<password.length();i++)  {
		            int c=password.charAt(i)^48; 
		            crypte=crypte+(char)c;
		        }
		        return crypte;
		    }

		 
		

		    /**
		     * [PasswordUtil.decrypt] Method 
		     * @author BELDI Jamel  on : 25 f�vr. 2014  18:44:06
		     * @param password
		     * @return
		     */
		    public static String decrypt(String password){
		        String aCrypter ="";
		        for (int i=0; i<password.length();i++)  {
		            int c=password.charAt(i)^48; 
		            aCrypter=aCrypter+(char)c;
		        }
		        return aCrypter;
		    }
		    
		    
		    
		   

		    private static byte[] key = {
		            0x74, 0x68, 0x69, 0x73, 0x49, 0x73, 0x41, 0x53, 0x65, 0x63, 0x72, 0x65, 0x74, 0x4b, 0x65, 0x79
		    };//"thisIsASecretKey";

		    public static String encryptAES(String strToEncrypt)
		    		throws Exception{
		        try
		        {
		            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
		            final SecretKeySpec secretKey = new SecretKeySpec(key, "AES");
		            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
		          
		            return new String(cipher.doFinal(strToEncrypt.getBytes())).replaceAll("[\\p{Cc}\\p{Cf}\\p{Co}\\p{Cn}]", " ");
		        }
		        catch (Exception e)
		        {
		            log.error("Error while encrypting", e);
		            throw e;
		        }
		      

		    }

		    public static String decryptAES(String strToDecrypt) throws Exception
		    {
		        try
		        {
		            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5PADDING");
		            final SecretKeySpec secretKey = new SecretKeySpec(key, "AES");
		            cipher.init(Cipher.DECRYPT_MODE, secretKey);
		            return new String(cipher.doFinal(strToDecrypt.getBytes()));
		        }
		        catch (Exception e)
		        {
		            log.error("Error while decrypting", e);
		            throw e;

		        }
		    }
		  
		    

		    public static void main (String [] arg){
		    	String password ="admin2014";
		    	try {
					String crypted = encryptAES(password);// encrypt(password, PasswordUtil.KEY);
					log.info(crypted);
					String decrypted = decryptAES(crypted);//decrypt(crypted, PasswordUtil.KEY);
					log.info(decrypted);
					System.out.println(decrypted);
				} catch (Exception e) {
					
					e.printStackTrace();
				}
		    }
		    
}
