package rabbitmq.untils;

/** 
 * rabbitmq配置公共类
 * @author  李林林 
 * @date 2017年7月6日 上午10:35:20 
 * @since
 */
import java.io.IOException;
import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
public class QUntil {
	public final static String USER_NAME = "admin";
	public final static String PASSWD = "admin123";
	public final static String HOST = "192.168.1.127";
	public final static int PORT = AMQP.PROTOCOL.PORT;
	//日志分类  
	protected static final String[] SEVERITIES = { "info", "warning", "error" };    
	//helloworld
	protected final static String QUEUW_HELLO_WORLD="helloworld";
	//工作队列
	protected final static String QUEUW_WORLK="workqueue-durable";
	// 转发器  
    protected final static String EXCHANGE_NAME = "ex_log";  
    protected final static  String EXCHANGE_NAME_DIRECT = "ex_logs_direct"; 
    protected final static  String EXCHANGE_NAME_TOPIC = "ex_logs_topic";
    protected final static String EXCHANGE_NAME_HEADER = "header-exchange"; 
    
    
    protected final static String QUEUE_NAME_DELAY = "queue_name_dalay"; 
    protected final static String QUEUE_NAME_TTL = "message_ttl_queue";
    
    /**
	 * 创建一个rabbitmq连接
	 * @author: 李林林
	 * @date:2017年7月6日 上午10:34:31
	 * @return
	 * @throws IOException Connection
	 */
	public final static Connection getConnection() throws IOException{
		//打开连接和创建频道，与发送端一样    
		ConnectionFactory factory = new ConnectionFactory();    
		//http://anhm.ngrok.cc
		factory.setHost(HOST);    
		//指定用户 密码  
		factory.setUsername(USER_NAME);  
		factory.setPassword(PASSWD);  
		//指定端口  
		factory.setPort(PORT);  
		//创建一个连接    
		Connection connection = factory.newConnection();    
		return connection;
	}
}
