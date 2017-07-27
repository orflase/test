package rabbitmq.workqueue;

/** 
 * 生产任务Task
 * @author  李林林 
 * @date 2017年7月6日 上午10:35:20 
 * @since
 */
import java.io.IOException;
import rabbitmq.untils.QUntil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.MessageProperties;

public class Task extends QUntil{

	public static void main(String[] args) throws IOException {
		//创建连接
		Connection connection = getConnection();
		Channel channel = connection.createChannel();
		boolean durable = true;//设置消息初始化 
		//Queue.DeclareOk queueDeclare(String queue, boolean durable, boolean exclusive, boolean autoDelete,
		//                         		Map<String, Object> arguments) throws IOException;
		//   durable：true、false true：在服务器重启时，能够存活
		//   exclusive ：是否为当前连接的专用队列，在连接断开后，会自动删除该队列，生产环境中应该很少用到吧
		//   autodelete：当没有任何消费者使用时，自动删除该队列
		channel.queueDeclare(QUEUW_WORLK, durable, false, false, null);
		int i=0;
		String dots ="";
		while(i<10){
			dots+=".";
			String message = "hello world!"+dots+dots.length();
			// void basicPublish(String exchange, String routingKey, BasicProperties props, byte[] body)
			//routingKey：路由键，#匹配0个或多个单词，*匹配一个单词，在topic exchange做消息转发用
			//BasicProperties prop：需要注意的是BasicProperties.deliveryMode，0:不持久化 1：持久化 这里指的是消息的持久化，配合channel(durable=true),queue(durable)可以实现，即使服务器宕机，消息仍然保留
			//byte[] body:消息内容
			channel.basicPublish("", QUEUW_WORLK, MessageProperties.PERSISTENT_TEXT_PLAIN, message.getBytes());
			System.out.println("Sent Message：'" + message + "'");
			i++;
		}
		//关闭频道和资源    
		channel.close();    
		connection.close();  
	}
}
