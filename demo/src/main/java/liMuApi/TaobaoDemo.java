package liMuApi;

import java.util.ArrayList;
import java.util.List;
import org.apache.http.message.BasicNameValuePair;
import untils.Base64Untils;

/** 
 * @author  李林林 
 * @date 2017年7月10日 下午1:11:32 
 * @since
 */
public class TaobaoDemo extends AbstractCredit {

    //业务参数
    public static final String method = "api.taobao.get";//请求接口
    public static final String bizType = "taobao";//业务类型
    public static final String callBackUrl = "";//回调地址
    public static final String username = "15021101146";//用户名---需客户指定
    public static final String password = "a8220137";//密码---需客户指定
    public static final String loginType = "qr";//登陆类型推荐【qr】 normal:正常登陆、qr:手机扫描登陆、cookie:通过cookie登陆


    public static void main(String[] args) throws Exception {

        //启动服务
        TaobaoDemo service  = new TaobaoDemo();
        service.process();

    }

	public void process() throws Exception{
        System.out.println("开始获取淘宝信息");

        try {

            //提交受理请求对象
            List<BasicNameValuePair> reqParam = new ArrayList<BasicNameValuePair>();
            reqParam.add(new BasicNameValuePair("apiKey", apiKey));//API授权
            reqParam.add(new BasicNameValuePair("version", version));//调用的接口版本
            reqParam.add(new BasicNameValuePair("callBackUrl", callBackUrl));//callBackUrl
            reqParam.add(new BasicNameValuePair("method", method));//接口名称
            reqParam.add(new BasicNameValuePair("username", username));//账号
            reqParam.add(new BasicNameValuePair("password",  Base64Untils.encoder(password)));//密码
            reqParam.add(new BasicNameValuePair("loginType", loginType));//推荐类型
            reqParam.add(new BasicNameValuePair("sign", getSign(reqParam)));//请求参数签名

            //提交受理请求
            doProcess(reqParam);

        }catch (Exception ex){
            System.out.println("开始获取淘宝信息异常：" + ex);
        }
	}


    /**
     * 获取业务类型
     */
    @Override
    public String getBizType(){
        return bizType;
    }
}
