package com.commons.utils;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.net.URLCodec;
import org.apache.http.NameValuePair;
import org.apache.http.client.fluent.Content;
import org.apache.http.client.fluent.Form;
import org.apache.http.client.fluent.Request;
import org.apache.http.client.fluent.Response;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.List;
import java.util.Map;

/**
 * @author pengqingsong
 * @date 07/09/2017
 * @desc http请求工具类
 */
public class HttpRequestUtils {

    public static String post(String url, Map<String, String> queryParams) {
        Request post = Request.Post(url);

        if (queryParams != null && queryParams.size() > 0) {
            Form form = Form.form();
            for (Map.Entry<String, String> entry : queryParams.entrySet()) {
                form.add(entry.getKey(), entry.getValue());
            }
            List<NameValuePair> nameValuePairs = form.build();
            post.bodyForm(nameValuePairs, Charset.forName("utf8"));
        }
        try {
            Response response = post.execute();
            Content content = response.returnContent();
            return content.asString();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public static String urlEncode(String url) {
        try {
            return new URLCodec().decode(url);
        } catch (DecoderException e) {
            e.printStackTrace();
            return null;
        }
    }

}
