import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

/**
 * Created by dy on 2017/6/27.
 */
public class Login {

    public static void login() {
        System.getProperties().setProperty("webdriver.chrome.driver", "/Users/dy/Desktop/javaPractice/chromedriver");
        WebDriver webDriver = new ChromeDriver();
        webDriver.get("https://mp.weixin.qq.com/");
        WebElement webElement = webDriver.findElement(By.xpath("/html"));
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


    }
}
