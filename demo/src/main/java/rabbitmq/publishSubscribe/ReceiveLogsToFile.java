package rabbitmq.publishSubscribe;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import rabbitmq.untils.QUntil;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.QueueingConsumer;

/** 
 * 创建一个队列，然后将队列与转发器绑定，然后将消费者与该队列绑定，然后写入日志文件
 * @author  李林林 
 * @date 2017年7月18日 下午3:01:08 
 * @since
 */
public class ReceiveLogsToFile extends QUntil {

	public static void main(String[] args) throws Exception{

		Connection connection = getConnection();
		Channel channel = connection.createChannel();
		/**
		 * 声明转发器类型
		 * direct  处理路由键，需要将一个队列绑定到交换器上，要求该消息与一个特定的的路由键完全匹配
		 * font	        不处理路由键，只需要简单的的将队列绑定到交换器上，一个发送到交换机的消息都会被转发到与该交换机绑定的所有队列
		 * topic   将路由键和某模式进行匹配。此时队列需要绑定要一个模式上。符号“#”匹配一个或多个词，符号“*”匹配不多不少一个词
		 */
		channel.exchangeDeclare(EXCHANGE_NAME, "fanout");
		///创建一个非持久的、唯一的且自动删除的队列且队列名称由服务器随机产生一般情况这个名称与amq.gen-JzTY20BRgKO-HjmUJj0wLg 类似。
		String queueName = channel.queueDeclare().getQueue();
		//将一个队列绑定到转发器上，设置binding  
		channel.queueBind(queueName, EXCHANGE_NAME, "");

		System.out.println(" [*] Waiting for messages. To exit press CTRL+C");  
		//创建消费者
		QueueingConsumer consumer = new QueueingConsumer(channel);
		//指定接收者
		/**
		 * 队列名
		 * true 自动确认
		 * 确认失败返回的消费者
		 */
		channel.basicConsume(queueName, true, consumer);

		while(true){
			//等待接收
			QueueingConsumer.Delivery delivery =  consumer.nextDelivery();
			print2File(new String(delivery.getBody()));

		}
	}

	/**
	 * 输出日志文件
	 * @author: 李林林
	 * @date:2017年7月18日 下午3:19:13
	 * @param msg void
	 */
	private static void print2File(String msg) {  
		try {  
			String dir = System.getProperty("user.dir");  
			System.out.println(dir);
			String logFileName = new SimpleDateFormat("yyyy-MM-dd").format(new Date());  
			File file = new File(dir, logFileName + ".txt");  
			FileOutputStream fos = new FileOutputStream(file, true);  
			fos.write((msg + "\r\n").getBytes());  
			fos.flush();  
			fos.close();  
		} catch (FileNotFoundException e) {  
			e.printStackTrace();  
		} catch (IOException e) {  
			e.printStackTrace();  
		}  
	}  

}
