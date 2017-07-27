package rabbitmq.helloworld;

/** 
 * rabbitmq入门helloworld，接收
 * @author  李林林 
 * @date 2017年7月6日 上午10:35:20 
 * @since
 */
import rabbitmq.untils.QUntil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.QueueingConsumer;

public class Receive extends QUntil{

	public static void main(String[] args) throws Exception {  
		//打开连接和创建频道，与发送端一样    
		Connection connection = getConnection();    
		//创建一个频道    
		Channel channel = connection.createChannel();    
		//声明队列，主要为了防止消息接收者先运行此程序，队列还不存在时创建队列。    
		/**
		 * queue      队列名
		 * durable    true声明一个持久队列(队列将在服务器重新启动后存活)
		 * exclusive  true声明一个独占队列(仅限于此连接)
		 * autoDelete true声明一个自动删除队列(服务器将在不再使用时删除它)
		 * arguments  队列的其他属性(构造参数)
		 */
		channel.queueDeclare(QUEUW_HELLO_WORLD, false, false, false, null);    
		//创建队列消费者    
		QueueingConsumer consumer = new QueueingConsumer(channel);    
		//指定消费队列    
		/**
		 * queue     队列名
		 * autoAck   true自动确认消息
		 * callback consumer
		 */
		channel.basicConsume(QUEUW_HELLO_WORLD, true, consumer);    
		while (true)    
		{    
			//nextDelivery是一个阻塞方法（内部实现其实是阻塞队列的take方法） 
			//等待下一个消息传递并返回它。
			QueueingConsumer.Delivery delivery = consumer.nextDelivery();    
			String message = new String(delivery.getBody());    
			System.out.println("Received Message：'" + message + "'");    
		}    
	}  
}
