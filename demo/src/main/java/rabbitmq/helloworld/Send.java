package rabbitmq.helloworld;

/** 
 * rabbitmq入门helloworld，发送
 * @author  李林林 
 * @date 2017年7月6日 上午10:35:20 
 * @since
 */
import rabbitmq.untils.QUntil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

public class Send extends QUntil{

	public static void main(String[] args) throws Exception {  
		 
		//创建一个连接    
		Connection connection = getConnection();    
		//创建一个频道    
		Channel channel = connection.createChannel();    
		//指定一个队列    
		/**
		 *  queue      队列名
		 *  durable    true声明一个持久队列(队列将在服务器重新启动后存活)
		 *  exclusive  true声明一个独占队列(仅限于此连接)
		 *  autoDelete true声明一个自动删除队列(服务器将在不再使用时删除它)
		 *  arguments  队列的其他属性(构造参数)
		 */
		channel.queueDeclare(QUEUW_HELLO_WORLD, false, false, false, null);    
		//发送的消息    
		String message = "hello lill";    
		//往队列中发出一条消息    
		/**
		 * exchange     指定交换器类型，""默认
		 * routingKey   routing key
		 * props        routing headers的其他属性
		 * body         message body
		 */
		channel.basicPublish("", QUEUW_HELLO_WORLD, null, message.getBytes());    
		System.out.println("Sent Message：'" + message + "'");    
		//关闭频道和连接    
		channel.close();    
		connection.close();    
	}  
}
