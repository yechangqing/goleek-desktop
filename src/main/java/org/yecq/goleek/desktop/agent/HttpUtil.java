package org.yecq.goleek.desktop.agent;

import com.google.gson.Gson;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.StatusLine;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

/**
 *
 * @author yecq
 */
public final class HttpUtil {

    private static final CloseableHttpClient httpclient = HttpClients.createDefault();

    // 发送post请求，返回response body
    public static String post(String url, Object obj) {
//        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(url);
        CloseableHttpResponse response = null;
        try {
            List<NameValuePair> nvps = new LinkedList();
            if (obj != null) {
                String json = new Gson().toJson(obj);
                nvps.add(new BasicNameValuePair("json", json));
            }
            httpPost.setEntity(new UrlEncodedFormEntity(nvps, "utf-8"));
            response = httpclient.execute(httpPost);
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                HttpEntity entity = response.getEntity();
                String str = EntityUtils.toString(entity, "utf-8");
                EntityUtils.consume(entity);
                return str;
            } else {
                StatusLine status = response.getStatusLine();
                String msg = status.getStatusCode() + " " + status.getReasonPhrase();
                throw new IllegalArgumentException(msg);
            }
        } catch (Exception e) {
            throw new IllegalStateException(e.getClass().getSimpleName() + " : \n" + e.getMessage());
        } finally {
            if (response != null) {
                try {
                    response.close();
                } catch (IOException e) {
                }
            }
        }
    }

    // 发送带id的请求
    public static String post(String url, String id, Object obj) {
        HttpPost httpPost = new HttpPost(url);
        CloseableHttpResponse response = null;
        try {
            List<NameValuePair> nvps = new LinkedList();
            nvps.add(new BasicNameValuePair("id", id));
            if (obj != null) {
                String json = new Gson().toJson(obj);
                nvps.add(new BasicNameValuePair("json", json));
            }
            httpPost.setEntity(new UrlEncodedFormEntity(nvps, "utf-8"));
            response = httpclient.execute(httpPost);
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                HttpEntity entity = response.getEntity();
                String str = EntityUtils.toString(entity, "utf-8");
                EntityUtils.consume(entity);
                return str;
            } else {
                StatusLine status = response.getStatusLine();
                String msg = status.getStatusCode() + " " + status.getReasonPhrase();
                throw new IllegalArgumentException(msg);
            }
        } catch (Exception e) {
            throw new IllegalStateException(e.getClass().getSimpleName() + " : \n" + e.getMessage());
        } finally {
            if (response != null) {
                try {
                    response.close();
                } catch (IOException e) {
                }
            }
        }
    }
}
