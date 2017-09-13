package untils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;

/** 
 * @author  李林林 
 * @date 2017年7月10日 上午11:13:21 
 * @since
 */
public class HttpUntil {

	/**
	 * get请求
	 * @author: 李林林
	 * @date:2017年7月10日 上午11:16:26
	 * @param url
	 * @param param
	 * @return String
	 */
	public static String sendGet(String url, String param) {
		String result = "";
		BufferedReader in = null;
		try {
			String urlNameString = param!=null?(url + "?" + param):url;
			URL realUrl = new URL(urlNameString);
			// 打开和URL之间的连接
			URLConnection connection = realUrl.openConnection();
			// 设置通用的请求属性
			connection.setRequestProperty("accept", "*/*");
			connection.setRequestProperty("connection", "Keep-Alive");
			connection.setRequestProperty("user-agent",
					"Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
			// 建立实际的连接
			connection.connect();
			// 获取所有响应头字段
			Map<String, List<String>> map = connection.getHeaderFields();
			// 遍历所有的响应头字段
			for (String key : map.keySet()) {
				System.out.println(key + "--->" + map.get(key));
			}
			// 定义 BufferedReader输入流来读取URL的响应
			in = new BufferedReader(new InputStreamReader(
					connection.getInputStream()));
			String line;
			while ((line = in.readLine()) != null) {
				result += line;
			}
		} catch (Exception e) {
			System.out.println("发送GET请求出现异常！" + e);
			e.printStackTrace();
		}
		// 使用finally块来关闭输入流
		finally {
			try {
				if (in != null) {
					in.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return result;
	}

	/**
	 * post请求
	 * @author: 李林林
	 * @date:2017年7月10日 上午11:16:26
	 * @param url
	 * @param param
	 * @return String
	 */
	public static String sendPost(String url, String param) {
		PrintWriter out = null;
		BufferedReader in = null;
		String result = "";
		try {
			URL realUrl = new URL(url);
			// 打开和URL之间的连接
			URLConnection conn = realUrl.openConnection();
			// 设置通用的请求属性
			conn.setRequestProperty("accept", "*/*");
			conn.setRequestProperty("connection", "Keep-Alive");
			conn.setRequestProperty("user-agent",
					"Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
			// 发送POST请求必须设置如下两行
			conn.setDoOutput(true);
			conn.setDoInput(true);
			// 获取URLConnection对象对应的输出流
			if(param!=null){
				out = new PrintWriter(conn.getOutputStream());
				// 发送请求参数
				out.print(param);
				// flush输出流的缓冲
				out.flush();
			}
			// 定义BufferedReader输入流来读取URL的响应
			in = new BufferedReader(
					new InputStreamReader(conn.getInputStream()));
			String line;
			while ((line = in.readLine()) != null) {
				result += line;
			}
		} catch (Exception e) {
			System.out.println("发送 POST 请求出现异常！"+e);
			e.printStackTrace();
		}
		//使用finally块来关闭输出流、输入流
		finally{
			try{
				if(out!=null){
					out.close();
				}
				if(in!=null){
					in.close();
				}
			}
			catch(IOException ex){
				ex.printStackTrace();
			}
		}
		return result;
	}
	
	public static void main(String[] args) {
		sendPost("http://192.168.1.234:8088/app/user/login.html", "content=ewogICAgImhlYWRlciI6ewogICAgICAgICJvcmdDb2RlIjoic2hzaGFuc2hhbmNoZTAxIiwKICAgICAgICAidHJhbnNEYXRlIjoiMjAxNi0xMS0wNyAxNToyODozMCIsCiAgICAgICAgInRyYW5zTm8iOiJ0cmFuczIwMTYxMTE2MDAwMSIKICAgIH0sCiAgICAiYnVzaURhdGEiOnsKICAgICAgICAiYmF0Y2hObyI6IjExMjEyMSIsCiAgICAgICAgInBob25lIjoiMTU1MzgxMDM5MTAiCiAgICB9LAogICAgInNlY3VyaXR5SW5mbyI6ewogICAgICAgICJzaWduIjoiRTA1OEEwOUM0N0ExQkI1M0Y1REUxNUFBODJERERCMEMiCiAgICB9Cn0=");
	}
}
