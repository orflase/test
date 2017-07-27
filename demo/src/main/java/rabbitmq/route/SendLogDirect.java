package rabbitmq.route;

import java.util.Random;
import java.util.UUID;
import rabbitmq.untils.QUntil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

/** 
 * 发送端
 * 发送消息时可以设置routing_key，接收队列与转发器间可以设置binding_key，接收者接收与binding_key与routing_key相同的消息
 * @author  李林林 
 * @date 2017年7月18日 下午3:33:03 
 * @since
 */
public class SendLogDirect extends QUntil {
	public static void main(String[] args) throws Exception{
		Connection connection =getConnection();
		Channel channel = connection.createChannel();
		/**
		 * 声明转发器类型
		 * direct      处理路由键，需要将一个队列绑定到交换器上，要求该消息与一个特定的的路由键完全匹配
		 * fantout	        不处理路由键，只需要简单的的将队列绑定到交换器上，一个发送到交换机的消息都会被转发到与该交换机绑定的所有队列
		 * topic       将路由键和某模式进行匹配。此时队列需要绑定要一个模式上。符号“#”匹配一个或多个词，符号“*”匹配不多不少一个词
		 */
		channel.exchangeDeclare(EXCHANGE_NAME_DIRECT, "direct",false,false,false,null);
		for (int i = 0; i < 6; i++) {
			String severity = getSeverity();    
			String message = severity + "_log :" + UUID.randomUUID().toString();  
			System.out.println(message);
			channel.basicPublish(EXCHANGE_NAME_DIRECT, severity, null, message.getBytes());
		}
		channel.close();
		connection.close();
	}

	/**
	 * 
	 * @author: 李林林
	 * @date:2017年7月18日 下午3:49:58
	 * @return String
	 */
	private static String getSeverity()    
	{    
		Random random = new Random();    
		int ranVal = random.nextInt(3);    
		return SEVERITIES[ranVal];    
	}    
}
