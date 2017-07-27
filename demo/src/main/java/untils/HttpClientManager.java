package untils;

import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

/**
 * 
 * @author 李林林
 * @date 2017年7月10日 下午1:06:40
 */
public class HttpClientManager {

    public static CloseableHttpClient getHttpClient(){
        RequestConfig requestConfig = RequestConfig.custom().setCookieSpec(CookieSpecs.DEFAULT).build();
        CloseableHttpClient httpClient = HttpClients.custom().setConnectionManager(HttpClientConnectionManagerFactory.createHttpClientConnectionManager()).setDefaultRequestConfig(requestConfig).build();
        return httpClient;
    }
}
