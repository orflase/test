package untils;

import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.HttpClientConnectionManager;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;

/**
 * 
 * @author 李林林
 * @date 2017年7月10日 下午1:06:34
 */
public class HttpClientConnectionManagerFactory {

    public static HttpClientConnectionManager createHttpClientConnectionManager(){
        Registry<ConnectionSocketFactory> socketFactoryRegistry = RegistryBuilder.<ConnectionSocketFactory> create()
                .register("https", TrustSSLConnectionSocketFactory.createSSLConnSocketFactory())
                .register("http", new PlainConnectionSocketFactory())
                .build();
        PoolingHttpClientConnectionManager cm =new PoolingHttpClientConnectionManager(socketFactoryRegistry);
        cm.setMaxTotal(400);
        cm.setDefaultMaxPerRoute(50);
        return cm;
    }
}