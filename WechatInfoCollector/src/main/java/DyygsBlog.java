import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;

import java.util.List;

/**
 * Created by dy on 2017/6/23.
 */
public class DyygsBlog implements PageProcessor {

    public static final String URL_LIST_SUFFIX = "/archives/page/\\d+";
    public static final String URL_LIST_PREFIX = "https://dyygs.github.io";
    public static final String URL_POST_SUFFIX = "/\\d+/\\d+/\\d+/.*";
    public static final String URL_POST_PREFIX = "https://dyygs.github.io";

    private Site site = Site
            .me()
            .setSleepTime(3000)
            .setUserAgent(
                    "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_7_2) AppleWebKit/537.31 (KHTML, like Gecko) Chrome/26.0.1410.65 Safari/537.31");

    public void process(Page page) {
        //列表页
        if (page.getUrl().regex("/archives").match()) {
//            page.addTargetRequests(page.getHtml().xpath("//div[@class='collection-title']").links().regex(URL_POST).all());
//            page.addTargetRequests(page.getHtml().links().regex(URL_LIST).all());
            List<String> postList = page.getHtml().links().regex(URL_POST_SUFFIX).all();
            for(String s:postList) {
                s = URL_POST_PREFIX + s;
            }
            page.addTargetRequests(postList);
            List<String> list = page.getHtml().links().regex(URL_LIST_SUFFIX).all();
            for(String s:list) {
                s = URL_LIST_PREFIX + s;
            }
            page.addTargetRequests(list);
            //文章页
        } else {
            DyygsCell dyygsCell = new DyygsCell();
            dyygsCell.setTitle(page.getHtml().xpath("//h1[@class='post-title']/text()").toString());
            dyygsCell.setContent(page.getHtml().xpath("//div[@class='post-body']/tidyText()").toString());
            dyygsCell.setDate(page.getHtml().xpath("//time[@title='Post created']/text()").toString());
            page.putField("dyygsCell", dyygsCell);
        }
    }

    public Site getSite() {
        return site;
    }

    public static void main(String[] args) {
        Spider
                .create(new DyygsBlog())
                .addUrl("https://dyygs.github.io/archives")
                .addPipeline(new TestPipeline())
                .thread(5)
                .run();
    }
}
