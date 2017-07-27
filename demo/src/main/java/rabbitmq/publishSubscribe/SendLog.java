package rabbitmq.publishSubscribe;

import java.util.Date;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import rabbitmq.untils.QUntil;

/** 
 * 发送日志 faout
 * @author  李林林 
 * @date 2017年7月18日 下午2:44:32 
 * @since
 */
public class SendLog extends QUntil {

	public static void main(String[] args) throws Exception {
		Connection connection = getConnection();
		Channel channel = connection.createChannel();

		/**
		 * 声明转发器类型
		 * direct      处理路由键，需要将一个队列绑定到交换器上，要求该消息与一个特定的的路由键完全匹配
		 * fantout	        不处理路由键，只需要简单的的将队列绑定到交换器上，一个发送到交换机的消息都会被转发到与该交换机绑定的所有队列
		 * topic       将路由键和某模式进行匹配。此时队列需要绑定要一个模式上。符号“#”匹配一个或多个词，符号“*”匹配不多不少一个词
		 */
		channel.exchangeDeclare(EXCHANGE_NAME, "fanout");
		String message = new Date().toLocaleString()+": log someting";
		//指定消息发送到的转换器
		channel.basicPublish(EXCHANGE_NAME, "", null, message.getBytes());
		System.out.println(" [x] Sent '" + message + "'");
		channel.close();
		connection.close();

	}

}
