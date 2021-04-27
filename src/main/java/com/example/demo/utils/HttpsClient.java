package com.example.demo.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.demo.common.exception.OApiException;
import org.apache.http.HttpEntity;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.util.EntityUtils;
import java.io.IOException;
import java.util.Map;

/**
 * @PROJECT_NAME: demo01
 * @PACKAGE_NAME: com.example.demo01.utils
 * @Class_NAME: SSLClient
 * @Author: zhangyongjiang
 * @DATE_TIME: 2021-4-21 上午 11:33
 * @Description:
 * @version:
 **/
public class HttpsClient {
    /**
     * 读超时时间
     */
    private static final int DEFAULT_SO_TIMEOUT = 2000;
    /**
     * 连接超时时间
     */
    private static final int DEFAULT_TIMEOUT = 2000;

    public static Map httpsPost(String url, Object data) throws Exception {
        /**
         * 定义一个Post请求
         */
        HttpPost httpPost = new HttpPost(url);
        /**
         * 定义一个相应体
         */
        CloseableHttpResponse response = null;
        /**
         * 创建一个http客户端
         */
        CloseableHttpClient httpClient = HttpClients.createDefault();
        /**
         * 创建请求配置文件
         */
        RequestConfig requestConfig = RequestConfig.custom().
                setSocketTimeout(DEFAULT_SO_TIMEOUT).
                setConnectTimeout(DEFAULT_TIMEOUT).build();
        /**
         * Post请求加载配置文件
         */
        httpPost.setConfig(requestConfig);
        /**
         * 设置请求头
         */
        httpPost.addHeader("Content-Type", "application/json");

        try {
            /**
             * 获取请求参数
             */
            StringEntity requestEntity = new StringEntity(JSON.toJSONString(data), "utf-8");
            /**
             * 加载请求参数
             */
            httpPost.setEntity(requestEntity);
            /**
             * 执行Post请求
             */
            response = httpClient.execute(httpPost, new BasicHttpContext());
            /**
             * 若响应不成功，直接返回null
             */
            if (response.getStatusLine().getStatusCode() != 200) {
                System.out.println("request url failed, http code=" + response.getStatusLine().getStatusCode()
                        + ", url=" + url);
                return null;
            }
            /**
             * 解析响应体
             */
            HttpEntity entity = response.getEntity();
            /**
             * 若响应体不为空
             */
            if (entity != null) {
                /**
                 * 将其转换成utf-8编码的字符串
                 */
                String resultStr = EntityUtils.toString(entity, "utf-8");
                /**
                 * 将字符串转换成JSON格式
                 */
                JSONObject result = JSON.parseObject(resultStr);
                /**
                 * 判断响应code
                 */
                if (result.getInteger("errcode") == null || result.getInteger("errcode") == 0) {
                    result.remove("errcode");
                    result.remove("errmsg");
                    return JSON.parseObject(JSON.toJSONString(result), Map.class);
                } else {
                    System.out.println("request url=" + url + ",return value=");
                    System.out.println(resultStr);
                    int errCode = result.getInteger("errcode");
                    String errMsg = result.getString("errmsg");
                    throw new Exception(errMsg);
                }
            }
        } catch (IOException e) {
            System.out.println("request url=" + url + ", exception, msg=" + e.getMessage());
            e.printStackTrace();
        } finally {
            if (response != null) {
                try {
                    response.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    public static Map httpsGet(String url) throws Exception {
        //创建一个HttpGet对象，将要请求的url通过构造方法传入对象
        HttpGet httpGet = new HttpGet(url);
        //创建HttpResponse对象（HttpResponse（以前用的） —> CloseableHttpResponse（现在推荐的））
        CloseableHttpResponse response = null;

        //创建默认的HttpClient实例（CloseableHttpClient已经替代了以前的DefaultHttpClient）
        CloseableHttpClient httpClient = HttpClients.createDefault();

        //设置获取数据的超时时间（setSocketTimeout），建立连接的超时时间（setConnectTimeout）
        RequestConfig requestConfig = RequestConfig.custom().
                setSocketTimeout(DEFAULT_SO_TIMEOUT).
                setConnectTimeout(DEFAULT_TIMEOUT).build();

        //将配置的超时时间给创建好的HttpGet对象
        httpGet.setConfig(requestConfig);

        try {

            //执行HttpGet，返回response。BasicHttpContext的作用是为我们的会话增加状态信息
            response = httpClient.execute(httpGet, new BasicHttpContext());

            /**
             * 200状态码：表示请求已成功，请求所希望的响应头或数据体将随此响应返回
             */
            //如果响应出现了问题，后台打印状态码和要访问的url
            if (response.getStatusLine().getStatusCode() != 200) {

                System.out.println("request url failed, http code=" + response.getStatusLine().getStatusCode()
                        + ", url=" + url);
                return null;
            }

            /**
             * 项目中需要调用外部的接口，接口返回HttpResponse 对象，
             * 在取返回值里的内容的时候，httpResponse.getEntity()不能调用多次，只能调用获取一次，存成一个临时的变量接收一下，在用这个临时变量进行后续的逻辑，
             * 如果不用变量接收的话，第二次再调用的时候取出来的对象就是null，导致后续处理报空指针异常！
             */
            //取返回值（response）里的内容，给到变量entity
            HttpEntity entity = response.getEntity();
            if (entity != null) {

                //把内容转化为字符串，并以utf-8形式编码
                String resultStr = EntityUtils.toString(entity, "utf-8");
                //将resultStr解析为一个JSONObject对象并返回
                JSONObject result = JSON.parseObject(resultStr);

                if (result.getInteger("errcode") == null || result.getInteger("errcode") == 0) {
                    //JSON.parseObject(String text, Class clazz)，作用就是将指定的JSON字符串转换成自己的实体类的对象
                    return JSON.parseObject(JSON.toJSONString(result), Map.class);
                } else {
                    System.out.println("request url=" + url + ",return value=");
                    int errCode = result.getInteger("errcode");
                    String errMsg = result.getString("errmsg");
                    throw new OApiException(errCode, errMsg);
                }
            }
        } catch (IOException e) {
            System.out.println("request url=" + url + ", exception, msg=" + e.getMessage());
            e.printStackTrace();
        } finally {
            if (response != null) {
                try {
                    response.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return null;
    }
}