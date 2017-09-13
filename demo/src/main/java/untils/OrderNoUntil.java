package untils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/** 
 * 生成订单编号
 * @author  李林林 
 * @date 2017年8月10日 下午6:36:56 
 * @since
 */
public class OrderNoUntil {
	/**
	 * 生成订单编号
	 * @author: 李林林
	 * @date:2017年8月10日 下午6:37:01
	 * @return String
	 */
	public static String buildOrderNo() {
		SimpleDateFormat sdf =new SimpleDateFormat("yyMMddHHmmss");
		String time = sdf.format(new Date());
		Random random = new Random();
		int randomNumber = random.nextInt(9999) ;
		String randomNumbers = String.valueOf(randomNumber>=1000 ? randomNumber : randomNumber+1000);
		return "ss"+time+randomNumbers+System.currentTimeMillis();
	}
	
	/**
	 * 积分订单
	 * @author: 李林林
	 * @date:2017年9月8日 下午3:35:39
	 * @return String
	 */
	public static String buildOrderNo1() {
		SimpleDateFormat sdf =new SimpleDateFormat("yyyyMMddHHmmss");
		String time = sdf.format(new Date());
		Random random = new Random();
		int randomNumber = random.nextInt(9999) ;
		String randomNumbers = String.valueOf(randomNumber>=1000 ? randomNumber : randomNumber+1000);
		return time+randomNumbers;
	}
}
