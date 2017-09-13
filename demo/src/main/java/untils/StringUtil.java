package untils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

/**
 * 工具类-字符串处理
 * 
 * @author xx
 * @version 2.0
 * @since 2014年1月28日
 */
public class StringUtil extends StringUtils {
	/**
	 * 字符串空处理，去除首尾空格 如果str为null，返回"",否则返回str
	 * 
	 * @param str
	 * @return
	 */
	public static String isNull(String str) {
		if (str == null) {
			return "";
		}
		return str.trim();
	}

	/**
	 * 将对象转为字符串
	 * 
	 * @param o
	 * @return
	 */
	public static String isNull(Object o) {
		if (o == null) {
			return "";
		}
		String str = "";
		if (o instanceof String) {
			str = (String) o;
		} else {
			str = o.toString();
		}
		return str.trim();
	}

	/**
	 * 检验是否为空或空字符串
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isBlank(String str) {
		return StringUtil.isNull(str).equals("");
	}

	public static boolean isBlank(Object o) {
		return StringUtil.isNull(o).equals("");
	}

	/**
	 * 检验是否非空字符串
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isNotBlank(String str) {
		return !StringUtil.isNull(str).equals("");
	}

	/**
	 * 检验手机号
	 * 
	 * @param phone
	 * @return
	 */
	public static boolean isPhone(String phone) {
		phone = isNull(phone);
		Pattern regex = Pattern.compile("^((13[0-9])|(15[^4,\\D])|(17[0-9])|(18[0-9]))\\d{8}$");
		Matcher matcher = regex.matcher(phone);
		boolean isMatched = matcher.matches();
		return isMatched;
	}

	/**
	 * 校验是否全中文，返回true 表示是 反之为否
	 * 
	 * @param realname
	 * @return
	 */
	public static boolean isChinese(String realname) {
		realname = isNull(realname);
		Pattern regex = Pattern.compile("[\\u4e00-\\u9fa5]{2,25}");
		Matcher matcher = regex.matcher(realname);
		boolean isMatched = matcher.matches();
		return isMatched;
	}

	/**
	 * 校验邮箱格式
	 * 
	 * @param email
	 * @return
	 */
	public static boolean isEmail(String email) {
		email = isNull(email);
		Pattern regex = Pattern.compile("\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*");
		Matcher matcher = regex.matcher(email);
		boolean isMatched = matcher.matches();
		return isMatched;
	}

	/**
	 * 校验身份证号码
	 * 
	 * @param email
	 * @return
	 */
	public static boolean isCard(String cardId) {
		cardId = isNull(cardId);
		// 身份证正则表达式(15位)
		Pattern isIDCard1 = Pattern.compile("^[1-9]\\d{7}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])\\d{3}$");
		// 身份证正则表达式(18位)
		Pattern isIDCard2 = Pattern
				.compile("^[1-9]\\d{5}[1-9]\\d{3}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])\\d{3}([0-9]|X)$");
		Matcher matcher1 = isIDCard1.matcher(cardId);
		Matcher matcher2 = isIDCard2.matcher(cardId);
		boolean isMatched = matcher1.matches() || matcher2.matches();
		return isMatched;
	}

	/**
	 * 判断字符串是否为整数
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isInteger(String str) {
		if (isBlank(str)) {
			return false;
		}
		Pattern regex = Pattern.compile("\\d*");
		Matcher matcher = regex.matcher(str);
		boolean isMatched = matcher.matches();
		return isMatched;
	}

	/**
	 * 判断字符串是否为数字
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isNumber(String str) {
		if (isBlank(str)) {
			return false;
		}
		Pattern regex = Pattern.compile("(-)?\\d*(.\\d*)?");
		Matcher matcher = regex.matcher(str);
		boolean isMatched = matcher.matches();
		return isMatched;
	}

	/**
	 * 首字母大写
	 * 
	 * @param s
	 * @return
	 */
	public static String firstCharUpperCase(String s) {
		StringBuffer sb = new StringBuffer(s.substring(0, 1).toUpperCase());
		sb.append(s.substring(1, s.length()));
		return sb.toString();
	}

	/**
	 * 隐藏头几位
	 * 
	 * @param str
	 * @param len
	 * @return
	 */
	public static String hideFirstChar(String str, int len) {
		if (str == null)
			return null;
		char[] chars = str.toCharArray();
		if (str.length() <= len) {
			for (int i = 0; i < chars.length; i++) {
				chars[i] = '*';
			}
		} else {
			for (int i = 0; i < 1; i++) {
				chars[i] = '*';
			}
		}
		str = new String(chars);
		return str;
	}

	/**
	 * 隐藏末几位
	 * 
	 * @param str
	 * @param len
	 * @return
	 */
	public static String hideLastChar(String str, int len) {
		if (str == null)
			return null;
		char[] chars = str.toCharArray();
		if (str.length() <= len) {
			return hideLastChar(str, str.length() - 1);
		} else {
			for (int i = chars.length - 1; i > chars.length - len - 1; i--) {
				chars[i] = '*';
			}
		}
		str = new String(chars);
		return str;
	}

	/**
	 * 指定起始位置字符串隐藏
	 * 
	 * @param str
	 * @param index1
	 * @param index2
	 * @return
	 */
	public static String hideStr(String str, int index1, int index2) {
		if (str == null)
			return null;
		String str1 = str.substring(index1, index2);
		String str2 = str.substring(index2);
		String str3 = "";
		if (index1 > 0) {
			str1 = str.substring(0, index1);
			str2 = str.substring(index1, index2);
			str3 = str.substring(index2);
		}
		char[] chars = str2.toCharArray();
		for (int i = 0; i < chars.length; i++) {
			chars[i] = '*';
		}
		str2 = new String(chars);
		String str4 = str1 + str2 + str3;
		return str4;
	}

	/**
	 * Object数组拼接为字符串
	 * @param args
	 * @return
	 */
	public static String contact(Object[] args) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < args.length; i++) {
			sb.append(args[i]);
			if (i < args.length - 1) {
				sb.append(",");
			}
		}
		return sb.toString();
	}
	
	/**
	 * Long数组拼接为字符串
	 * @param args
	 * @return
	 */
	public static String contact(long[] args) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < args.length; i++) {
			sb.append(args[i]);
			if (i < args.length - 1) {
				sb.append(",");
			}
		}
		return sb.toString();
	}

	/**
	 * 数字数组拼接为字符串
	 * 
	 * @param arr
	 * @return
	 */
	public static String array2Str(int[] arr) {
		StringBuffer s = new StringBuffer();
		for (int i = 0; i < arr.length; i++) {
			s.append(arr[i]);
			if (i < arr.length - 1) {
				s.append(",");
			}
		}
		return s.toString();
	}

	/**
	 * 字符串数组转换为数字数组
	 * 
	 * @param strarr
	 * @return
	 */
	public static int[] strarr2intarr(String[] strarr) {
		int[] result = new int[strarr.length];
		for (int i = 0; i < strarr.length; i++) {
			result[i] = Integer.parseInt(strarr[i]);
		}
		return result;
	}


	/**
	 * 大写字母转成“_”+小写 驼峰命名转换为下划线命名
	 * 
	 * @param str
	 * @return
	 */
	public static String toUnderline(String str) {
		char[] charArr = str.toCharArray();
		StringBuffer sb = new StringBuffer();
		sb.append(charArr[0]);
		for (int i = 1; i < charArr.length; i++) {
			if (charArr[i] >= 'A' && charArr[i] <= 'Z') {
				sb.append('_').append(charArr[i]);
			} else {
				sb.append(charArr[i]);
			}
		}
		return sb.toString().toLowerCase();
	}

	/**
	 * 下划线改成驼峰样子
	 * 
	 * @param str
	 * @return
	 */
	public static String clearUnderline(String str) {
		char[] charArr = StringUtil.isNull(str).toLowerCase().toCharArray();
		StringBuffer sb = new StringBuffer();
		sb.append(charArr[0]);
		boolean isClear = false;
		for (int i = 1; i < charArr.length; i++) {
			if (charArr[i] == '_') {
				isClear = true;
				continue;
			}
			if (isClear && (charArr[i] >= 'a' && charArr[i] <= 'z')) {
				char c = (char) (charArr[i] - 32);
				sb.append(c);
				isClear = false;
			} else {
				sb.append(charArr[i]);
			}

		}
		return sb.toString();
	}

	/**
	 * String to int
	 * 
	 * @param str
	 * @return
	 */
	public static int toInt(String str) {
		if (StringUtil.isBlank(str))
			return 0;
		int ret = 0;
		try {
			ret = Integer.parseInt(str);
		} catch (NumberFormatException e) {
			ret = 0;
		}
		return ret;
	}
	/**
	 * String to Byte
	 * @param str
	 * @return
	 */
	public static byte toByte(String str) {
		if (StringUtil.isBlank(str))
			return 0;
		byte ret = 0;
		try {
			ret = Byte.parseByte(str);
		} catch (NumberFormatException e) {
			ret = 0;
		}
		return ret;
	}

	/**
	 * String to Long
	 * 
	 * @param str
	 * @return
	 */
	public static long toLong(String str) {
		if (StringUtil.isBlank(str))
			return 0L;
		long ret = 0;
		try {
			ret = Long.parseLong(str);
		} catch (NumberFormatException e) {
			ret = 0;
		}
		return ret;
	}


	/**
	 * 生成指定长度的随机字符串，字母加数字组合
	 * @param length
	 * @return
	 */
    public static String getRandomString(int length) { 
        String val = "";  
        Random random = new Random();  
        //参数length，表示生成几位随机数  
        for(int i = 0; i < length; i++) { 
            String charOrNum = random.nextInt(2) % 2 == 0 ? "char" : "num";  
            //输出字母还是数字  
            if( "char".equalsIgnoreCase(charOrNum) ) {  
                //输出是大写字母还是小写字母  
                int temp = random.nextInt(2) % 2 == 0 ? 65 : 97;  
                val += (char)(random.nextInt(26) + temp);  
            } else if( "num".equalsIgnoreCase(charOrNum) ) {  
                val += String.valueOf(random.nextInt(10));  
            }  
        }  
        return val;  
    }  
    
    /**
	 * 字符串数组转换为数字数组
	 * 
	 * @param strarr
	 * @return
	 */
	public static long[] string2longarr(String strarr) {
		if(!"".equals(strarr)&&strarr!=null){
			if(!strarr.contains(",")){
				return new long[]{Long.parseLong(strarr)};
			}else{
				String[] temp = strarr.split(",");
				long[] result =  new long[temp.length]; 
				for (int i = 0; i < result.length; i++) {
					result[i] = Long.parseLong(temp[i]);
				}
				return result;
			}
		}else{
			return null;
		}
	}
	
	/**
	 * 将字符串进行编码(默认编码为UTF-8)
	 * 
	 * @param s
	 *            被编码的字符串
	 * @return
	 */
	public static String urlEncode(String s) {
		return urlEncode(s, "UTF-8");
	}

	/**
	 * 将字符串进行编码
	 * 
	 * @param s
	 * @param enc
	 * @return
	 */
	public static String urlEncode(String s, String enc) {
		if (StringUtil.isBlank(s))
			return s;
		try {
			return URLEncoder.encode(s, enc);
		} catch (UnsupportedEncodingException e) {
			return s;
		}
	}
	
	/**
	 * 
	* @Title: mapToUrl 
	* @Description: 将map转为url字符串
	* @param params
	* @param transFlag 参数驼峰,下划线转换标志
	* @return String    返回类型 
	* @throws
	 */
	public static String mapToUrl(Map<String, String> params, boolean transFlag) {
        StringBuilder sb = new StringBuilder();
        boolean isFirst = true;
        for (String key : params.keySet()) {
            String value = params.get(key);
            String realKey = key;
            if(transFlag){
            	realKey = toUnderline(realKey);
            }
            if (isFirst) {
                sb.append(realKey + "=" + StringUtil.urlEncode(value));
                isFirst = false;
            } else {
                if (value != null) {
                    sb.append("&" + realKey + "=" + StringUtil.urlEncode(value));
                } else {
                    sb.append("&" + realKey + "=");
                }
            }
        }
        return sb.toString();
    }
	
	/**
	 * 判断对象中为空的字段名称
	 * @param source
	 * @return
	 */
	public static String[] getNullPropertyNames (Object source) {
        final BeanWrapper src = new BeanWrapperImpl(source);
        java.beans.PropertyDescriptor[] pds = src.getPropertyDescriptors();
        Set<String> emptyNames = new HashSet<String>();
        for(java.beans.PropertyDescriptor pd : pds) {
            Object srcValue = src.getPropertyValue(pd.getName());
            if (srcValue == null) {
            	emptyNames.add(pd.getName());
            }else{
            	if(srcValue.toString().equals("0")||srcValue.toString().equals("0.0")||srcValue.toString().equals("0.00")){
            		emptyNames.add(pd.getName());
            	}
            }
        }
        String[] result = new String[emptyNames.size()];
        return emptyNames.toArray(result);
    }
	
	
	 /**
     * 产生4位随机数(0000-9999)
     * @return 4位随机数
     */
    public static String getFourRandom(){
        Random random = new Random();
        String fourRandom = random.nextInt(10000) + "";
        int randLength = fourRandom.length();
        if(randLength<4){
          for(int i=1; i<=4-randLength; i++)
              fourRandom = "0" + fourRandom  ;
      }
        return fourRandom;
    }
    
    /**
	 * null 转为 0
	 * @author: 李林林
	 * @date:2017年8月29日 下午3:20:34
	 * @param obj
	 * @return String
	 */
	public static String nullToZero(Object obj){
		return null==obj?"0":obj.toString();
	}
	
	/**
	 *  null
	 * @author: 李林林
	 * @date:2017年8月29日 下午3:24:39
	 * @param obj
	 * @return String
	 */
	public static String nullToEmpty(Object obj){
		return null==obj?null:obj.toString();
	}
	
	/**
	 *  null 转为  date
	 * @author: 李林林
	 * @date:2017年8月29日 下午3:24:39
	 * @param obj
	 * @return String
	 * @throws Exception 
	 */
	public static Date nullToDate(Object obj) throws Exception{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return null==obj?null:sdf.parse(obj.toString().substring(0, 20));
	}

}
