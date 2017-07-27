package rabbitmq.topic;

import java.util.UUID;

import rabbitmq.untils.QUntil;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

/** 
 * 发送日志消息SendLogTopic，发送4个消息绑定不同的绑定键， "kernal.info", "cron.warning",  "auth.info", "kernel.critical"
 * @author  李林林 
 * @date 2017年7月19日 下午7:04:06 
 * @since
 */
public class SendLogTopic extends QUntil {

	public static void main(String[] args) throws Exception{
		
		Connection connection = getConnection();
		Channel channel = connection.createChannel();
		//声明转发器
		channel.exchangeDeclare(EXCHANGE_NAME_TOPIC, "topic");
		 //定义绑定键     
        String[] routing_keys = new String[] { "kernel.info", "cron.warning",    
                "auth.info", "kernel.critical" };  
        for (String routing_key : routing_keys)    
        {     
            //发送4条不同绑定键的消息  
            String msg = UUID.randomUUID().toString();    
            channel.basicPublish(EXCHANGE_NAME_TOPIC, routing_key, null, msg    
                    .getBytes());    
            System.out.println(" [x] Sent routingKey = "+routing_key+" ,msg = " + msg + ".");    
        }    
    
        channel.close();    
        connection.close();    
	}
}
