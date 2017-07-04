import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.pipeline.ConsolePipeline;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Html;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dy on 2017/6/23.
 */
public class WechatMain implements PageProcessor{
    private Site site = Site.me().setRetryTimes(3).setSleepTime(0);


    public void process(us.codecraft.webmagic.Page page) {
        page.addTargetRequests(page.getHtml().links().regex("(https://mp\\.weixin\\.qq\\.com/cgi-bin/message?.*?)").all());
        List<WechatMsg> list =  new ArrayList<WechatMsg>();
        List<String> msgList = page.getHtml().xpath("//ul[@id='listContainer']" +
                "//li[@class='message_item ']").all();
        if (msgList == null || msgList.size() == 0) {
            page.setSkip(true);
        } else {
            for (int i = 0; i < msgList.size(); i++) {
                WechatMsg wechatMsg = new WechatMsg();
                wechatMsg.setMsgSendDate(new Html(msgList.get(i)).xpath("//div[@class='message_time']/text()").toString());
                wechatMsg.setMsgSenderName(new Html(msgList.get(i)).xpath("//div[@class='message_info']" +
                        "//div[@class='user_info']" +
                        "//a[@class='remark_name']/text()").toString());
                wechatMsg.setMsgCongtent(new Html(msgList.get(i)).xpath("//div[@class='message_content text']//div[@class='wxMsg']/text()").toString());
                list.add(wechatMsg);
            }
            page.putField("wechatMsgList", list);
        }
    }

    public Site getSite() {
        return site;
    }

    public static void main(String[] args) {
        Spider
                .create(new WechatMain())
                .setDownloader(new WeChatSeleniumDownloader("/Users/dy/Desktop/javaPractice/chromedriver"))
                .addPipeline(new ConsolePipeline())
                .addUrl("https://mp.weixin.qq.com/")
                .thread(2)
                .run();
//        Login.login();
    }
}
