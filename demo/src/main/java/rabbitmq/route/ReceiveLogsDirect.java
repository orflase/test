package rabbitmq.route;

import java.util.Random;

import rabbitmq.untils.QUntil;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.QueueingConsumer;

/** 
 * 接收日志端
 * @author  李林林 
 * @date 2017年7月18日 下午3:52:23 
 * @since
 */
public class ReceiveLogsDirect extends QUntil {
	public static void main(String[] args) throws Exception{
		Connection connection = getConnection();
		Channel channel = connection.createChannel();
		//声明一个转发器
		channel.exchangeDeclare(EXCHANGE_NAME_DIRECT, "direct",false,false,false,null);
		//获取队列
		String queueName = channel.queueDeclare().getQueue();    
		String severity = getSeverity(); 
		// 指定binding_key    
		channel.queueBind(queueName, EXCHANGE_NAME_DIRECT, severity);
		System.out.println(" [*] Waiting for "+severity+" logs. To exit press CTRL+C");   
		QueueingConsumer consumer = new QueueingConsumer(channel);
		channel.basicConsume(queueName,true, consumer);
		while(true){
			QueueingConsumer.Delivery delivery = consumer.nextDelivery();
			String message = new String(delivery.getBody());    
			System.out.println(" [x] Received '" + message + "'");   
		}
	}
	/**
	 * 随机产生一种日志类型     
	 * @author: 李林林
	 * @date:2017年7月18日 下午3:58:10
	 * @return String
	 */
	private static String getSeverity()    
	{    
		Random random = new Random();    
		int ranVal = random.nextInt(3);    
		return SEVERITIES[ranVal];    
	}    
}
