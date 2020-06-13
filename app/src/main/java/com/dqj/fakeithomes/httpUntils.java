package com.dqj.fakeithomes;

import android.util.Log;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class httpUntils {
    String code;
    public String Gohttp(String url, int i) {
        try {
            OkHttpClient client=new OkHttpClient();
            Request request=new Request.Builder()
                    .url(url)
                    .build();

            Response response=client.newCall(request).execute();
            if (response.isSuccessful())
            {
                switch (i) {
                    case 1:

                   //    Log.e("0000000000000000`",killxml(response.body().string()));
                        return killad(response.body().string());

                    case 2:

                    //    Log.e("0000000000000000`",killad(response.body().string()));

                    return killxml(response.body().string());
                }}


                return "xml解析数据错误";
            } catch (IOException e1) {
            e1.printStackTrace();
        }

        return "xml解析数据错误";
    }

    //channel   item

    public String killxml(String s) {
        Document document = Jsoup.parse(s);
        Elements elements = document.select("detail");
        return elements.text();

    }

    public String killad(String s) {
        Document document = Jsoup.parse(s);
        Elements elements = document.getElementsByTag("main");
        return elements.html().replace("data-original","src");

    }


}