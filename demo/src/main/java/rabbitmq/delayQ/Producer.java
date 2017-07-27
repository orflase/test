package rabbitmq.delayQ;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import rabbitmq.untils.QUntil;

/** 
 * @author  李林林 
 * @date 2017年7月20日 下午2:09:25 
 * @since
 */
public class Producer extends QUntil {

	public static void main(String[] args) throws Exception{

		Connection connection =getConnection();
		Channel channel = connection.createChannel();
		channel.queueDeclare(QUEUE_NAME_DELAY, true, false, false, null); 
		String message = "hello world!" + System.currentTimeMillis();  
		channel.basicPublish("delaysync.exchange", "deal.message", null, message.getBytes()); 
		System.out.println("sent message: " + message + ",date:" + System.currentTimeMillis());  
		// 关闭频道和连接  
		channel.close();  
		connection.close(); 
	}
}
