
package disappear.programmer.service.hotdata.util;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

public class HttpUtil
{
	// 获得Http客户端(可以理解为:你得先有一个浏览器;注意:实际上HttpClient与浏览器是不一样的)
	private static CloseableHttpClient httpClient = null;
	private static RequestConfig requestConfig = null;
	private static CloseableHttpResponse httpResponse = null;
	private static HttpEntity httpEntity = null;
	
	static {
		httpClient = HttpClientBuilder.create().build();
		
		requestConfig = RequestConfig.custom()
				// 设置连接超时时间(单位毫秒)
				.setConnectTimeout(30000)
				// 设置请求超时时间(单位毫秒)
				.setConnectionRequestTimeout(30000)
				// socket读写超时时间(单位毫秒)
				.setSocketTimeout(30000)
				// 设置是否允许重定向(默认为true)
				.setRedirectsEnabled(true).build();
	}
	
	private HttpUtil() {}
	
	public static String requestGet(String url) {
		HttpGet httpGet = new HttpGet(url);
		// 将上面的配置信息 运用到这个Get请求里
		httpGet.setConfig(requestConfig);
		// 由客户端执行(发送)Get请求
		try
		{
			httpResponse = httpClient.execute(httpGet);
			// 从响应模型中获取响应实体
			httpEntity = httpResponse.getEntity();
			
			if(httpEntity != null)
			{
				return EntityUtils.toString(httpEntity);
			}
		}
		catch(IOException e)
		{
			e.printStackTrace();
			
		}finally {
			if(null != httpResponse) {
				try
				{
					httpResponse.close();
				}
				catch(IOException e)
				{
					e.printStackTrace();
				}
			}
		}
		
		return "";
	}
	
	public static String requestPost(String url, String contentType, Map<String, String> datas) {
		// 创建httpPost远程连接实例
        HttpPost httpPost = new HttpPost(url);
        // 为httpPost实例设置配置
        httpPost.setConfig(requestConfig);
        // 设置请求头 application/json
        httpPost.addHeader("Content-Type", contentType);
        
        try
		{
	        if(null != datas && !datas.isEmpty()) {
	        	List<NameValuePair> nvps = new ArrayList<NameValuePair>();
	        	for(String key: datas.keySet()) {
	        		nvps.add(new BasicNameValuePair(key, datas.get(key)));
	        	}
	        	
				httpPost.setEntity(new UrlEncodedFormEntity(nvps, "UTF-8"));
				// httpClient对象执行post请求,并返回响应参数对象
                httpResponse = httpClient.execute(httpPost);
                // 从响应对象中获取响应内容
                httpEntity = httpResponse.getEntity();
                if(httpEntity != null)
    			{
    				return EntityUtils.toString(httpEntity);
    			}
	        }
		}
		catch(ParseException | IOException e)
		{
			e.printStackTrace();
		}finally {
			if(null != httpResponse) {
				try
				{
					httpResponse.close();
				}
				catch(IOException e)
				{
					e.printStackTrace();
				}
			}
		}
        
        return "";
	}
}

