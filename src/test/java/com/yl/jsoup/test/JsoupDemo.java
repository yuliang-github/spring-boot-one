package com.yl.jsoup.test;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.junit.Test;

import java.io.IOException;

/**
 * @author Alex
 * @since 2018/9/14 10:54
 */
public class JsoupDemo {

    public static void main(String[] args) throws IOException {

        Connection connect = Jsoup.connect("http://news.baidu.com/");

        Document document = connect.get();

        Elements es_1 = document.select("#pane-news .hotnews");

        es_1.forEach(element -> {

            Elements ul_li = element.select("ul li");

            ul_li.forEach(li -> {

                Elements strongs = li.select("strong a");

                strongs.forEach(strong ->{

                    System.err.println("标题:" + strong.text() + ",新闻地址:" + strong.attr("href"));

                });

            });

        });
    }

    @Test
    public void demo_1() throws IOException {

        Connection connect = Jsoup.connect("http://news.baidu.com/");

        Document document = connect.get();

        Elements lis = document.select("ul li");

        lis.forEach(li -> {

            Elements links = li.select("a");

            links.forEach(link -> {

                String href = link.attr("href");

                if(href == null || !href.startsWith("http")){
                    return;
                }
                System.err.println("新闻标题:" + link.text() + ",新闻链接:" + link.attr("href"));
            });
        });

    }

}
