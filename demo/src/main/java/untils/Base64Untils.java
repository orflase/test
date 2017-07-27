package untils;

import org.apache.commons.codec.binary.Base64;

/** 
 * base64加密解密
 * @author  李林林 
 * @date 2017年7月10日 上午10:47:22 
 * @since
 */
public class Base64Untils {

	/**
	 * 使用commons-codec的base64 加密字符串 
	 * @author: 李林林
	 * @date:2017年7月10日 上午11:15:28
	 * @param str
	 * @return String
	 */
	public static String encoder(String str)  
	{  

		return new String(Base64.encodeBase64(str.getBytes()));  
	}  

	/**
	 * 使用commons-codec的base64 解密字符串 
	 * @author: 李林林
	 * @date:2017年7月10日 上午11:15:20
	 * @param str
	 * @return String
	 */
	public static String decoder(String str)  
	{  
		return new String(Base64.decodeBase64(str.getBytes()));  

	}  


}
