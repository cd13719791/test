package com.moyou.moyouRms.main;

import java.io.FileReader;
import java.util.Map;

import javax.script.Bindings;
import javax.script.ScriptContext;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.util.Base64Utils;

import com.moyou.moyouRms.util.JsonUtil;
import com.moyou.moyouRms.util.StringUtil;

public class Jsssoup {

	 @SuppressWarnings("all")
    public static void main(String[] args) {
        // 输入的链接
        String yg365 = "http://m.zijiecdn.cn/group/6442897774722679309/?iid=12395247980&app=news_article";
//        String yg365 = "http://www.365yg.com/group/6444865527784407566/?iid=12461954640&app=news_article&tt_from=android_share&utm_medium=toutiao_android&utm_campaign=client_share";

        String befoCrc = "http://ib.365yg.com/video/urls/v/1/toutiao/mp4/";
        String classPath = Jsssoup.class.getResource("/").toString();
        System.out.println(classPath);
        String jsPath = classPath + "videourl.js";
        jsPath = jsPath.substring(5);
        System.out.println(jsPath);
        String base64Url = "";
        try {
            @SuppressWarnings("all")
			Document document1 = Jsoup.connect(yg365).ignoreContentType(true).get();
            Document document = Jsoup.connect("http://m.toutiao.com/a6442897774722679309").header("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:33.0) Gecko/20100101 Firefox/33.0").get();  
            Elements e = document.getElementsByTag("script");
            String videoId = "";
            a: for (Element element : e) {
                String script = element.data().toString();
                if (script.contains("videoid")) {
                    String subScript = script.substring(script.indexOf("videoid:'") + "videoid:'".length(),
                            script.indexOf("share_url"));
                    videoId = subScript.substring(0, subScript.indexOf("',"));
                    System.out.println(videoId);
                    break a;
                }
            }
            // tt.ixigua.com/e8519b81a549e4cc7f56e9d1b4eeee1e/599e75a7/video/m/22090b075805b4643babb54e1a0eaccf57a114f86000008730a882a3f0/","pic":"http://p3.pstatp.com/origin/373d00108f7cdc1d3836"}
            befoCrc = befoCrc + "a5763d04003f4482ad069509676005fd"; // 重新构建url
            ScriptEngine engine = new ScriptEngineManager().getEngineByName("javascript");
            Bindings bind = engine.createBindings();
            bind.put("factor", 0);
            engine.setBindings(bind, ScriptContext.ENGINE_SCOPE);

            engine.eval(new FileReader(jsPath));
            Object js_result = engine.eval("crc32(' + " + befoCrc + "')");
            base64Url = js_result.toString();
            Document doc = Jsoup.connect(base64Url).ignoreContentType(true).get();
            Element body = doc.body();
            String json = body.text();
            Map<String,Object> jsonMap = JsonUtil.toMap(json);
           
			Map<String,Object> dataMap = (Map<String,Object>)jsonMap.get("data");
            Map<String,Object> video_listMap = (Map<String,Object>)dataMap.get("video_list");
            Map<String,Object> video_1Map = (Map<String,Object>)video_listMap.get("video_1");
            System.out.println(StringUtil.getStr(video_1Map.get("main_url")));
            /*String base64 = json.substring(json.indexOf("main_url\":\"") + "main_url\":\"".length(),
                    json.indexOf("\",\"preload_interval"));*/
            String base64 = StringUtil.getStr(video_1Map.get("main_url"));
            byte[] bytes = Base64Utils.decodeFromString(base64);
            // 结果-视频链接
            String result = new String(bytes);
            System.out.println(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
