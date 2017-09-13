package untils;


/**
 * 通过坐标计算两点间距离
 * @author 李林林
 * @date 2017年8月31日 下午5:04:20
 */
public class LocationUtils {  
	private static double EARTH_RADIUS = 6378.137;  

	private static double rad(double d) {  
		return d * Math.PI / 180.0;  
	}  

	/** 
	 * 通过经纬度获取距离(单位：米) 
	 * @param lat1 纬度1
	 * @param lng1 经度1
	 * @param lat2 纬度2
	 * @param lng2 经度2
	 * @return 
	 */  
	public static double getDistance(double lat1, double lng1, double lat2,  
			double lng2) {  
		double radLat1 = rad(lat1);  
		double radLat2 = rad(lat2);  
		double a = radLat1 - radLat2;  
		double b = rad(lng1) - rad(lng2);  
		double s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a / 2), 2)  
				+ Math.cos(radLat1) * Math.cos(radLat2)  
				* Math.pow(Math.sin(b / 2), 2)));  
		s = s * EARTH_RADIUS;  
		s = Math.round(s * 10000d) / 10000d;  
		s = s*1000;  
		return s;  
	}  
	public static void main(String[] args) {
		//116.403945,39.915143
		//116.403932,39.913282
		System.out.println(getDistance(39.915143,   116.403945,39.913282,   116.403932));
	}
}  