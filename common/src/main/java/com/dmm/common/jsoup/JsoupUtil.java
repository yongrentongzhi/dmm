package com.dmm.common.jsoup;



import com.dmm.common.pojo.Content;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class JsoupUtil {



    /**
     * 输入京东搜索网址，解析京东商品信息
     *
     * @param url 搜索网址（url携带keywords）
     * @return List<Content> 包含所有商品信息的集合
     */
    public static List<Content> parseJD(String url) {

        Document document = null;
        try {
            document = Jsoup.parse(new URL(url), 3000000);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Element element = document.getElementById("J_goodsList");

        Elements li = element.getElementsByTag("li");

            List<Content> contentList = new ArrayList<>();
            for (Element element1 : li) {
                String img = element1.getElementsByTag("img").eq(0).attr("src");
                String price = element1.getElementsByClass("p-price").eq(0).text();
                String title = element1.getElementsByClass("p-name").eq(0).text();
                Content content = new Content();
                content.setImg(img);
                content.setPrice(price);
                content.setTitle(title);

                contentList.add(content);
            }


            return contentList;

    }

}
