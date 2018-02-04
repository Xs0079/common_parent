package com.youxinpai.common.util.web.http;

import com.youxinpai.common.util.web.ResponseResult;
import org.apache.http.Header;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.*;

import static com.youxinpai.common.util.constant.Constant.UTF8_CHARSET;


/**
 * httpclient utils
 * Created by Xs on 2017/11/28.
 */
public class HttpClientUtils {

    private static final Logger logger = LoggerFactory.getLogger(HttpClientUtils.class);
    private static CloseableHttpClient defaulHttpClient = HttpClients.createDefault();
    private static final Header[] NULL_HEADER = new Header[0];

    private static int connectTimeout = 2000;
    private static int connectionRequestTimeout = 2000;
    private static int socketTimeout = 5000;

    private static RequestConfig config;

    public static void setConfig(RequestConfig config) {
        HttpClientUtils.config = config;
    }

    static {
        buildRequestConfig(connectTimeout, connectionRequestTimeout, socketTimeout);
    }

    /**
     * @param connectTimeout           connectTimeout 建立连接超时时间
     * @param connectionRequestTimeout 获取连接超时时间
     * @param socketTimeout            从服务器读取数据的超时时间
     * @return
     */
    public static RequestConfig buildRequestConfig(int connectTimeout, int connectionRequestTimeout, int socketTimeout) {
        RequestConfig.Builder copyConfig = RequestConfig.copy(RequestConfig.DEFAULT);
        copyConfig.setConnectTimeout(connectTimeout);
        copyConfig.setConnectionRequestTimeout(connectionRequestTimeout);
        copyConfig.setSocketTimeout(socketTimeout);
        return config;
    }

    /**
     * 无参get请求
     *
     * @param url
     * @return
     * @throws URISyntaxException
     * @throws IOException
     */
    public static ResponseResult doGet(String url) throws URISyntaxException, IOException {
        return doGet(url, null, NULL_HEADER);
    }

    /**
     * 无参post请求
     *
     * @param url
     * @return
     * @throws IOException
     */
    public static ResponseResult doPost(String url) throws IOException {
        return doPost(url, null, NULL_HEADER, null);
    }

    /**
     * 有参get请求
     *
     * @param url
     * @return
     * @throws URISyntaxException
     * @throws IOException
     */
    public static ResponseResult doGet(String url, Map<String, String[]> params) throws URISyntaxException, IOException {
        return doGet(url, params, NULL_HEADER);
    }

    /**
     * 有参post请求
     *
     * @param url
     * @param params
     * @return
     * @throws IOException
     */
    public static ResponseResult doPost(String url, Map<String, String[]> params) throws IOException {
        return doPost(url, params, null, null);
    }


    /**
     * 有参get请求
     *
     * @param url
     * @param params
     * @return
     * @throws IOException
     */
    public static ResponseResult doGetReq(String url, Map<String, String> params) throws URISyntaxException, IOException {
        return doGet(url, convertParams(params), NULL_HEADER);
    }

    /**
     * 有参post请求
     *
     * @param url
     * @param params
     * @return
     * @throws IOException
     */
    public static ResponseResult doPostReq(String url, Map<String, String> params) throws IOException {
        return doPost(url, convertParams(params), null, null);
    }


    /**
     * 带header的 get 请求
     *
     * @param url
     * @param params
     * @param headers
     * @return
     * @throws URISyntaxException
     * @throws IOException
     */
    public static ResponseResult doGet(String url, Map<String, String[]> params, Map<String, String> headers) throws URISyntaxException, IOException {
        return doGet(url, params, convertHeader(headers));
    }

    /**
     * 带header的 post 请求
     *
     * @param url
     * @param params
     * @param headers
     * @return
     * @throws URISyntaxException
     * @throws IOException
     */
    public static ResponseResult doPost(String url, Map<String, String> params, Map<String, String> headers) throws URISyntaxException, IOException {
        return doPost(url, convertParams(params), convertHeader(headers), null);
    }

    /**
     * 带charset的 post 请求
     *
     * @param url
     * @param params
     * @param headers
     * @param charset
     * @return
     * @throws URISyntaxException
     * @throws IOException
     */
    public static ResponseResult doPost(String url, Map<String, String> params, Map<String, String> headers, String charset) throws URISyntaxException, IOException {
        return doPost(url, convertParams(params), null, charset);
    }


    /**
     * 带header的 get 请求
     *
     * @param url
     * @param params
     * @param headers
     * @return
     * @throws URISyntaxException
     * @throws IOException
     */
    public static ResponseResult doGet(String url, Map<String, String[]> params, Header[] headers) throws URISyntaxException, IOException {
        URIBuilder uriBuilder = new URIBuilder(url);

        if (params != null) {
            for (String key : params.keySet()) {
                for (String v : params.get(key)) {
                    uriBuilder.setParameter(key, v);
                }
            }
        }

        HttpGet httpGet = new HttpGet(uriBuilder.build());
        httpGet.setConfig(config);

        if (!ObjectUtils.isEmpty(headers)) {
            httpGet.setHeaders(headers);
        }

        return getHttpResult(httpGet);
    }

    /**
     * 带header的 post 请求
     *
     * @param url
     * @param params
     * @param headers
     * @return
     * @throws IOException
     */
    public static ResponseResult doPost(String url, Map<String, String[]> params, Header[] headers) throws IOException {
        return doPost(url, params, headers, null);
    }


    /**
     * 带header的 post 请求
     *
     * @param url
     * @param params
     * @param headers
     * @param charset
     * @return
     * @throws IOException
     */
    public static ResponseResult doPost(String url, Map<String, String[]> params, Header[] headers, String charset) throws IOException {
        HttpPost httpPost = new HttpPost(url);
        httpPost.setConfig(config);

        if (params != null) {
            List<NameValuePair> parameters = new ArrayList<>(params.size());
            for (String key : params.keySet()) {
                for (String v : params.get(key)) {
                    parameters.add(new BasicNameValuePair(key, v));
                }
            }

            UrlEncodedFormEntity formEntity = new UrlEncodedFormEntity(parameters, StringUtils.isEmpty(charset) ? UTF8_CHARSET : charset);
            httpPost.setEntity(formEntity);
        }

        if (!ObjectUtils.isEmpty(headers)) {
            httpPost.setHeaders(headers);
        }

        return getHttpResult(httpPost);
    }

    /**
     * 执行post json请求
     *
     * @param url
     * @param jsonParam
     * @return
     * @throws IOException
     */
    public static ResponseResult doPostJson(String url, String jsonParam) throws IOException {
        return doPostJson(url, jsonParam, null);
    }

    /**
     * 执行post json请求
     *
     * @param url
     * @param jsonParam
     * @param charset
     * @return
     * @throws IOException
     */
    public static ResponseResult doPostJson(String url, String jsonParam, String charset) throws IOException {
        HttpPost httpPost = new HttpPost(url);
        httpPost.setConfig(config);
        httpPost.setEntity(new StringEntity(jsonParam, StringUtils.isEmpty(charset) ? UTF8_CHARSET : charset));
        httpPost.setHeader(HeaderUtil.JSON_HEADER);
        return getHttpResult(httpPost);
    }


    /**
     * 执行http请求,获取请求结果
     *
     * @param httpUriRequest
     * @return
     * @throws IOException
     */
    private static ResponseResult getHttpResult(HttpUriRequest httpUriRequest) throws IOException {
        try (CloseableHttpResponse response = defaulHttpClient.execute(httpUriRequest)) {
            ResponseResult<String> responseResult = new ResponseResult<>();
            responseResult.setCode(response.getStatusLine().getStatusCode());
            responseResult.setData(EntityUtils.toString(response.getEntity(), UTF8_CHARSET));
            return responseResult;
        }
    }


    /**
     * 转换param
     *
     * @param params
     * @return
     */
    private static Map<String, String[]> convertParams(Map<String, String> params) {
        Assert.notNull(params, "传入的参数headers不能为null");
        Map<String, String[]> formatParams = new HashMap<>(params.size());
        params.keySet().forEach(key -> formatParams.put(key, new String[]{params.get(key)}));
        return formatParams;
    }


    /**
     * 转换header
     *
     * @param headers
     * @return
     */
    private static Header[] convertHeader(Map<String, String> headers) {
        Assert.notNull(headers, "传入的参数headers不能为null");
        Header[] header = new Header[headers.size()];
        Set<Map.Entry<String, String>> entries = headers.entrySet();
        Iterator<Map.Entry<String, String>> iterator = entries.iterator();

        for (int x = 0; x < entries.size(); x++) {
            Map.Entry<String, String> next;
            if (iterator.hasNext()) {
                next = iterator.next();
                header[x] = new BasicHeader(next.getKey(), next.getValue());
            }
        }

        return header;
    }
}
