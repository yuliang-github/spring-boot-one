package com.yl.encrypt.test;

import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Alex
 * @since 2019/1/15 17:58
 */
public class InterfaceTest {

    public static void main(String[] args) throws  Exception {
        String url = "http://devm.51onion.com:8888/aop-open/router";
        HttpPost post = new HttpPost(url);
        List<NameValuePair> param = new ArrayList<>();
        param.add(new BasicNameValuePair("method","ala.onion.asset.grade.transfer"));
        param.add(new BasicNameValuePair("v","3.7.1"));
        param.add(new BasicNameValuePair("contanct_no","20181112302"));
        param.add(new BasicNameValuePair("data","4ABA076291AA5D1A68CE1995072289422F415745D65BD7213F40296FE81B8C68E718DBD321BD1D5FD9F8463F4CDA2281E1A5CAE8C85A7774BE71BEAFE01E234B3D53CA8483524635BF13CD7316E605ECD08A3BEF4316D2EACE936961822BAC2CF2E0D57B83A41A89105AA00A92BACD2A463DA1E777EF7996E0F5E5E34CCA476A572C6F26F238FEC811D3995B6B49E659E8216A6C9DF6D4180302E4D3ADA0A1F99FAB91EBA012C5BF845E2524A3D48CEED922BFB950E79071825DB877F770DC7B37F305ED876D9F578DB3ECE35E82CC0073E021A5683B0CA18AA6F88B1E6B20FE979E18FC005A2296D309134C00CE3BBBAF748B6D4A356B5A7BB3CE0BCE063F228938D24445DC05E76530025B06A4E13F15921081965AB9011826D977BC1A731142A2E8EBC093663359BACFBAA78A436CB8D2DE0163C395CCADA7CFFBFE7617E0481A3573FE884650BCFD9D213D7A03145813F4753B81C1D446D635D50FF142D9A4C96F36677237BD436E8922709BEF2B5610D63C545262A8A5B06212D473A2AD7EA97919E97C15EC5F8E191D45E600CABE1D5F50FFEC43207A3162A0AC0D7DC472656CDA7975156E02D5BBF351849880026CDA98CA110E550869FEFED464F292CBBC5933826BC3A08AFE8A9FDE076E32F8F0B4D4E06BCE3D547B6BB815D923892374127C0B3FF159ABC518C4DAB90A7C"));
        UrlEncodedFormEntity urlEncodedFormEntity =  new UrlEncodedFormEntity(param, "UTF-8");
        post.setEntity(urlEncodedFormEntity);

        CloseableHttpClient httpClient = HttpClients.custom().build();
        CloseableHttpResponse res = httpClient.execute(post);
        String html = EntityUtils.toString(res.getEntity());
        System.out.println(html);
    }

    @Test
    public  void test() throws  Exception {
        String url = "https://m.51onion.com/aop-open/router";
        HttpPost post = new HttpPost(url);
        List<NameValuePair> param = new ArrayList<>();
        param.add(new BasicNameValuePair("method","ala.onion.query.banner"));
        param.add(new BasicNameValuePair("v","3.6"));
        param.add(new BasicNameValuePair("source","front"));
        UrlEncodedFormEntity urlEncodedFormEntity =  new UrlEncodedFormEntity(param, "UTF-8");
        post.setEntity(urlEncodedFormEntity);

        CloseableHttpClient httpClient = HttpClients.custom().build();
        CloseableHttpResponse res = httpClient.execute(post);
        String html = EntityUtils.toString(res.getEntity());
        System.out.println(html);
    }

}
