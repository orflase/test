package rabbitmq.workqueue;

/** 
 * 消费工作队列Work
 * @author  李林林 
 * @date 2017年7月6日 上午10:35:20 
 * @since
 */
import rabbitmq.untils.QUntil;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.QueueingConsumer;

public class Work extends QUntil{
	public static void main(String[] args) throws Exception {
		//区分不同工作进程的输出    
		int hashCode = Work.class.hashCode();
		//创建连接
		Connection connection = getConnection();
		Channel channel = connection.createChannel();
		boolean durable = true; //设置消息持久化
		//声明一个队列
		channel.queueDeclare(QUEUW_WORLK, durable, false, false, null);
		//创建一个消费者
		QueueingConsumer consumer = new QueueingConsumer(channel);

		/** 
		 * ack= true: Round-robin 转发   消费者被杀死，消息会丢失 
		 * ack=false:消息应答 ，为了保证消息永远不会丢失，RabbitMQ支持消息应答（message acknowledgments）。 
		 * 消费者发送应答给RabbitMQ，告诉它信息已经被接收和处理，然后RabbitMQ可以自由的进行信息删除。 
		 * 如果消费者被杀死而没有发送应答，RabbitMQ会认为该信息没有被完全的处理，然后将会重新转发给别的消费者。 
		 * 通过这种方式，你可以确认信息不会被丢失，即使消者偶尔被杀死。 
		 * 消费者需要耗费特别特别长的时间是允许的。 
		 *  
		 */  
		boolean ack = false ; //打开应答机制   ,不自动应答
		// 指定消费队列    
		channel.basicConsume(QUEUW_WORLK, ack, consumer);    

		//公平转发  设置最大服务转发消息数量    只有在消费者空闲的时候会发送下一条信息。  
		int prefetchCount = 1;  
		channel.basicQos(prefetchCount);

		while (true)    
		{    
			//nextDelivery是一个阻塞方法（内部实现其实是阻塞队列的take方法）    
			//等待下一个消息传递并返回它。
			QueueingConsumer.Delivery delivery = consumer.nextDelivery();    
			String message = new String(delivery.getBody());    
			System.out.println(hashCode + " Received Message：'" + message + "'");    
			doWork(message);    
			System.out.println(hashCode + " Received Done");    
			//发送应答   
			//true承认所有的消息，包括提供的交付标记; 将一次性ack所有小于deliveryTag的消息。
			//false承认所提供的交付标记。
			channel.basicAck(delivery.getEnvelope().getDeliveryTag(), false);    

		}  
	}
	/**  
	 * 每个点耗时1s  
	 * @param task  
	 * @throws InterruptedException  
	 */    
	private static void doWork(String task) throws InterruptedException    
	{    
		for (char ch : task.toCharArray())    
		{    
			if (ch == '.')    
				System.out.println(ch);
				Thread.sleep(100);    
		}    
	}    
}
