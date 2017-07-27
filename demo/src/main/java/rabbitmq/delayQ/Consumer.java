package rabbitmq.delayQ;

import rabbitmq.untils.QUntil;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.QueueingConsumer;

/** 
 * @author  李林林 
 * @date 2017年7月20日 下午2:15:15 
 * @since
 */
public class Consumer extends QUntil {
	 public static void main(String[] args) throws Exception {  
	        Connection connection = getConnection();  
	        Channel channel = connection.createChannel();  
	        // 声明队列  
	        channel.queueDeclare(QUEUE_NAME_DELAY, true, false, false, null);  
	        QueueingConsumer consumer = new QueueingConsumer(channel);  
	        // 指定消费队列  
	        channel.basicConsume(QUEUE_NAME_DELAY, true, consumer);  
	        while (true) {  
	            // nextDelivery是一个阻塞方法（内部实现其实是阻塞队列的take方法）  
	            QueueingConsumer.Delivery delivery = consumer.nextDelivery();  
	            String message = new String(delivery.getBody());  
	            System.out.println("received message:" + message + ",date:" + System.currentTimeMillis());  
	        }  
	    }  
}
