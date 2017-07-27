package rabbitmq.headers;

import java.util.Date;
import java.util.Hashtable;
import java.util.Map;

import org.springframework.amqp.core.ExchangeTypes;

import rabbitmq.untils.QUntil;

import com.rabbitmq.client.AMQP.BasicProperties;
import com.rabbitmq.client.AMQP.BasicProperties.Builder;  
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

/** 
 * @author  李林林 
 * @date 2017年7月20日 上午10:41:58 
 * @since
 */
public class Producer extends QUntil{

	@SuppressWarnings("deprecation")
	public static void main(String[] args) throws Exception {
		Connection connection = getConnection();  
		Channel channel = connection.createChannel();  
		//声明转发器
		channel.exchangeDeclare(EXCHANGE_NAME_HEADER, ExchangeTypes.HEADERS, false, true, null);
		String message = new Date().toLocaleString() + " : log something";  
		Map<String,Object> headers =  new Hashtable<String, Object>();  
		headers.put("aaa", "01234");  
		Builder properties  =  new BasicProperties.Builder(); 
		properties .headers(headers);
		channel.basicPublish(EXCHANGE_NAME_HEADER, "", properties .build(), message.getBytes());
        System.out.println("Sent message :'" + message + "'");  
		channel.close();
		connection.close();
		
		

	}
}
