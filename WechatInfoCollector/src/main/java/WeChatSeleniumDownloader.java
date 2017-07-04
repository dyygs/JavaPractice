import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.downloader.Downloader;
import us.codecraft.webmagic.selector.Html;
import us.codecraft.webmagic.selector.PlainText;

import java.io.Closeable;
import java.io.IOException;
import java.util.Map;

/**
 * 使用Selenium调用浏览器进行渲染。目前仅支持chrome。<br>
 * 需要下载Selenium driver支持。<br>
 *
 * @author code4crafter@gmail.com <br>
 *         Date: 13-7-26 <br>
 *         Time: 下午1:37 <br>
 */
public class WeChatSeleniumDownloader implements Downloader, Closeable {

	private Logger logger = Logger.getLogger(getClass());

	private int sleepTime = 0;

	private WebDriver webDriver;

	private static final String DRIVER_PHANTOMJS = "phantomjs";

	/**
	 * 新建
	 *
	 * @param chromeDriverPath chromeDriverPath
	 */
	public WeChatSeleniumDownloader(String chromeDriverPath) {
		System.getProperties().setProperty("webdriver.chrome.driver",
				chromeDriverPath);
		webDriver = new ChromeDriver();
	}

	/**
	 * Constructor without any filed. Construct PhantomJS browser
	 *
	 * @author bob.li.0718@gmail.com
	 */
	public WeChatSeleniumDownloader() {
		// System.setProperty("phantomjs.binary.path",
		// "/Users/Bingo/Downloads/phantomjs-1.9.7-macosx/bin/phantomjs");
	}

	/**
	 * set sleep time to wait until load success
	 *
	 * @param sleepTime sleepTime
	 * @return this
	 */
	public WeChatSeleniumDownloader setSleepTime(int sleepTime) {
		this.sleepTime = sleepTime;
		return this;
	}

	public Page download(Request request, Task task) {
		checkInit();

		webDriver.get(request.getUrl());

		//找到名为"loginName"的元素，填写帐号
		webDriver.findElement(By.id("account")).clear();
		webDriver.findElement(By.id("account")).sendKeys("hnxxtmail@163.com");

		//找到名为"loginPassword"的元素，填写密码
		webDriver.findElement(By.id("pwd")).clear();
		webDriver.findElement(By.id("pwd")).sendKeys("gzptxxtwxzxzxkc");

		//找到登录按钮，点击
		webDriver.findElement(By.id("loginBt")).click();
		String url = webDriver.getCurrentUrl();
		try {
			Thread.sleep(10 * 1000);
		} catch (Exception e) {

		}
		while (!url.equals(webDriver.getCurrentUrl())) {
			System.out.println("登录成功");
			break;
		}
		WebDriver.Options manage = webDriver.manage();
		Site site = task.getSite();
		if (site.getCookies() != null) {
			for (Map.Entry<String, String> cookieEntry : site.getCookies()
					.entrySet()) {
				Cookie cookie = new Cookie(cookieEntry.getKey(),
						cookieEntry.getValue());
				manage.addCookie(cookie);
			}
		}


		WebElement webElement = webDriver.findElement(By.xpath("/html"));
		String content = webElement.getAttribute("outerHTML");
		Page page = new Page();
		page.setRawText(content);
		page.setHtml(new Html(content, request.getUrl()));
		page.setUrl(new PlainText(request.getUrl()));
		page.setRequest(request);
		return page;
	}

	private void checkInit() {

	}


	public void setThread(int thread) {

	}

	public void close() throws IOException {

	}
}
