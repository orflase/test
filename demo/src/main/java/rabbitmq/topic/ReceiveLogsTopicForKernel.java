package rabbitmq.topic;

import java.io.IOException;

import rabbitmq.untils.QUntil;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.QueueingConsumer;

/** 
 * 定义接收kernel.*消息的消费者
 * @author  李林林 
 * @date 2017年7月19日 下午7:09:11 
 * @since
 */
public class ReceiveLogsTopicForKernel extends QUntil {

	public static void main(String[] args) throws Exception {
		Connection connection = getConnection();
		Channel channel = connection.createChannel();
		//声明转发器
		channel.exchangeDeclare(EXCHANGE_NAME_TOPIC, "topic");
		//随机生成队列
		String queueName = channel.queueDeclare().getQueue();
		//接收所有与kernel相关的消息 
		channel.queueBind(queueName, EXCHANGE_NAME_TOPIC, "kernel.*");
		System.out.println(" [*] Waiting for messages about kernel. To exit press CTRL+C");    

		QueueingConsumer consumer = new QueueingConsumer(channel);
		channel.basicConsume(queueName, true, consumer);
		while(true){
			QueueingConsumer.Delivery delivery = consumer.nextDelivery();
			String message = new String(delivery.getBody());    
			String routingKey = delivery.getEnvelope().getRoutingKey();
			System.out.println(" [x] Received routingKey = " + routingKey    
					+ ",msg = " + message + ".");    
		}

	}
}
