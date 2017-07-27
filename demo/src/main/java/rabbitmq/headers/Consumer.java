package rabbitmq.headers;

import java.util.Hashtable;
import java.util.Map;

import org.springframework.amqp.core.ExchangeTypes;

import rabbitmq.untils.QUntil;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.QueueingConsumer;

/** 
 * Headers类型的exchange使用的比较少，
 * 它也是忽略routingKey的一种路由方式。是使用Headers来匹配的。
 * Headers是一个键值对，可以定义成Hashtable。发送者在发送的时候定义一些键值对，
 * 接收者也可以再绑定时候传入一些键值对，两者匹配的话，则对应的队列就可以收到消息。
 * 匹配有两种方式all和any。这两种方式是在接收端必须要用键值"x-mactch"来定义。
 * all代表定义的多个键值对都要满足，而any则代码只要满足一个就可以了。
 * fanout，direct，topic exchange的routingKey都需要要字符串形式的，
 * 而headers exchange则没有这个要求，因为键值对的值可以是任何类型。
 * @author  李林林 
 * @date 2017年7月20日 上午11:13:16 
 * @since
 */
public class Consumer extends QUntil{
	private final static String QUEUE_NAME = "header-queue";  

	public static void main(String[] args) throws Exception {
		Connection connection = getConnection();  
		Channel channel = connection.createChannel();  
		//声明转发器
		channel.exchangeDeclare(EXCHANGE_NAME_HEADER, ExchangeTypes.HEADERS,false,true,null);  
		channel.queueDeclare(QUEUE_NAME,false, false, true,null);  

		Map<String, Object> headers = new Hashtable<String, Object>();  
		headers.put("x-match", "any");//all any  
		headers.put("aaa", "01234");  
		headers.put("bbb", "56789");  
		// 为转发器指定队列，设置binding 绑定header键值对  
		channel.queueBind(QUEUE_NAME,EXCHANGE_NAME_HEADER, "", headers);  
		QueueingConsumer consumer = new QueueingConsumer(channel);
		channel.basicConsume(QUEUE_NAME, true, consumer);
		System.out.println("~~~~~~~");
		while(true){
			QueueingConsumer.Delivery delivery = consumer.nextDelivery();
			String message  = new String(delivery.getBody());
			System.out.println(message);  
		}
	}
}
