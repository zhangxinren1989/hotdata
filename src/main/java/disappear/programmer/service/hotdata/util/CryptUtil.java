
package disappear.programmer.service.hotdata.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class CryptUtil
{
	private static MessageDigest messageDigest;
	private CryptUtil(){}
	
	public static String md5EncryptString(String plaintext)
	{
		StringBuilder md5encode = new StringBuilder();
		
		try
		{
			// 获取MD5实例
			messageDigest = MessageDigest.getInstance("MD5");
			messageDigest.update(plaintext.getBytes());// 此处传入要加密的byte类型值
			byte[] digest = messageDigest.digest();// 此处得到的是md5加密后的byte类型值
			
			for (byte b : digest) {  
				md5encode.append(String.format("%02X", b)); // 10进制转16进制，X 表示以十六进制形式输出，02 表示不足两位前面补0输出  
	        }
		}
		catch(NoSuchAlgorithmException e)
		{
			e.printStackTrace();
		}
		
		return md5encode.toString().toLowerCase();
	}
}

