import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import java.util.Random;


public class FinalTest {
    Actions actions;
    public WebDriver driver;
    JavascriptExecutor js;

    @BeforeMethod
    public void bef() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        actions = new Actions(driver);
        js = (JavascriptExecutor) driver;


    }

    public String generatorstring() {

        int StringLength = 3;
        Random random = new Random();

        String randomstring = random.ints(97, 123)
                .limit(StringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
        return randomstring;
    }

    @Test
    public void register() {
        WebDriverManager.chromedriver().setup();
        driver.manage().window().maximize();
        driver.get("http://tutorialsninja.com/demo/");
        WebElement myaccaunt = driver.findElement(By.cssSelector(".dropdown"));
        myaccaunt.click();
        actions.moveToElement(myaccaunt).perform();

        WebElement register = driver.findElement(By.xpath("//*[@id=\"top-links\"]/ul/li[2]/ul/li[1]/a"));
        register.click();
        driver.findElement(By.name("firstname")).sendKeys("Ambartsum");
        driver.findElement(By.name("lastname")).sendKeys("Karapetyan");
        driver.findElement(By.name("email")).sendKeys("ambartsum@gmail.com");
        driver.findElement(By.name("telephone")).sendKeys("555444333");
        driver.findElement(By.name("password")).sendKeys("Ab123.bA.");
        driver.findElement(By.name("confirm")).sendKeys("Ab123.bA.");
        WebElement subscribe = driver.findElement(By.cssSelector(".radio-inline input[value='1']"));
        subscribe.click();
        WebElement agree = driver.findElement(By.name("agree"));
        agree.click();

        WebElement registersubmit = driver.findElement(By.cssSelector("input[value='Continue']"));
        registersubmit.click();
        //driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        //System.out.println(driver.findElement(By.cssSelector("#account-register > div.alert.alert-danger.alert-dismissible")).getText());
    String wrong =driver.findElement(By.cssSelector("#account-register > div.alert.alert-danger.alert-dismissible")).getText();
    System.out.println(wrong);

       if (wrong=="Warning: E-Mail Address is already registered!")
        {WebElement Login =  driver.findElement(By.cssSelector("#column-right > div > a:nth-child(1)"));
        Login.click();
        driver.findElement(By.cssSelector("#input-email")).sendKeys("ambartsum@gmail.com");
        driver.findElement(By.id("input-password")).sendKeys("Ab123.bA.");
        WebElement acclogin = driver.findElement(By.cssSelector("input[value='Login']"));
        acclogin.click();

        }

        driver.findElement(By.xpath("//a[text()='Phones & PDAs']")).click();
        WebElement phonehower =driver.findElement(By.xpath("//*[@id=\"content\"]/div[2]/div[3]/div/div[1]/a/img"));
        actions.moveToElement(phonehower).perform();
    //   System.out.println(phonehower.getAttribute("title"));
       //String A = phonehower.getAttribute("title");


        if(phonehower.getAttribute("title").equalsIgnoreCase("Palm Treo Pro")){
          System.out.println("Phone name is alredy " + phonehower.getAttribute("title"));
       }else {
            System.out.println("Phone`s name is incorrect");
        }
 phonehower.click();



        String[] imageCounter;
        int imgTotal, imgCurrent;
        WebDriverWait wait = new WebDriverWait(driver,20);

        WebElement productFirstImage = driver.findElement(By.xpath("//img[@title='"+phonehower.getAttribute("title")+"']"));
    //    JavascriptExecutor js = (JavascriptExecutor)driver;
        js.executeScript("arguments[0].click();",productFirstImage);//JS executor command 2

        wait.until(ExpectedConditions.visibilityOf(driver.findElement((By.className("mfp-img")))));

        imageCounter = driver.findElement(By.className("mfp-counter")).getText().split("\\s+");

        imgTotal = Integer.valueOf(imageCounter[imageCounter.length-1]);
        imgCurrent = Integer.valueOf(imageCounter[0]);


        for (int i=0;i<=imgTotal;i++){
            System.out.println(imgCurrent + "last element");
            if (imgCurrent == imgTotal){
                System.out.println("last element");
                break;
            }else{
                wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.className("mfp-img")));
                driver.findElement(By.cssSelector("button[title*='Next']")).click();
                imageCounter = driver.findElement(By.className("mfp-counter")).getText().split("\\s+");
                imgCurrent = Integer.valueOf(imageCounter[0]);
            }



        }
        driver.findElement(By.cssSelector("button[title*='Close']")).click();




WebElement firstfoto = driver.findElement(By.xpath("//*[@id=\"content\"]/div/div[1]/ul[1]/li[1]/a/img"));
firstfoto.click();
WebElement count = driver.findElement(By.xpath("/html/body/div[2]/div/div[1]/div/figure/figcaption/div/div[2]/html/body/div[2]/div/div[1]/div/figure/figcaption/div/div[2]/html/body/div[2]/div/div[1]/div/figure/figcaption/div/div[2]/html/body/div[2]/div/div[1]/div/figure/figcaption/div/div[2]"));
   System.out.println( count.getText());

     int N=4;
        for (int i =0; i <N; i++){
            if(count.getText().equalsIgnoreCase("3 of 3")){
    i=N;
            }
            else {
                WebElement nextbtn = driver.findElement(By.cssSelector("body > div.mfp-wrap.mfp-gallery.mfp-close-btn-in.mfp-auto-cursor.mfp-ready > div > button.mfp-arrow.mfp-arrow-right.mfp-prevent-close"));
                nextbtn.click();
            }

        }
    }
}





      /*  WebElement inputemail = driver.findElement(By.cssSelector("#input-email"));
        inputemail.sendKeys("ambartsumgsmaisl.com");
        WebElement inputepasword = driver.findElement(By.cssSelector("#input-password"));
        inputepasword.sendKeys("Ab123.bA.aaa");
        WebElement log = driver.findElement(By.cssSelector("input[value='Login']"));
        log.click();*/
        // System.out.println(driver.findElement(By.xpath("//div[text()=' Warning: No match for E-Mail Address and/or Password.']")).getText());
        //  System.out.println(driver.findElement(By.cssSelector(".alert alert-danger alert-dismissible")).getText());

       // if (driver.findElement(By.xpath("//*[@id=\"account-login\"]/div[1]")).getText() == "Warning: No match for E-Mail Address and/or Password.") {
      //      driver.findElement(By.xpath("//*[@id=\"content\"]/div/div[1]/div/a")).click();
            //  WebElement register = driver.findElement(By.cssSelector("#top-links > ul > li.dropdown > ul > li:nth-child(1) > a"));
            //  register.click();
            /*driver.findElement(By.name("firstname")).sendKeys("Ambartsum");
            driver.findElement(By.name("lastname")).sendKeys("Karapetyan");
            driver.findElement(By.name("email")).sendKeys("ambartsum@gmail.com");
            driver.findElement(By.name("telephone")).sendKeys("555444333");
            driver.findElement(By.name("password")).sendKeys("Ab123.bA.");
            driver.findElement(By.name("confirm")).sendKeys("Ab123.bA.");
            WebElement subscribe = driver.findElement(By.cssSelector(".radio-inline input[value='1']"));
            subscribe.click();
            WebElement agree = driver.findElement(By.name("agree"));
            agree.click();
            // driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
            WebElement submit = driver.findElement(By.cssSelector("input[value='Continue']"));
            submit.click();
            WebElement cont = driver.findElement(By.cssSelector(".btn btn-primary"));
            cont.click();*/
    /*    } else {
            log.click();
System.out.println("gverd");
        }
        driver.findElement(By.xpath("//a[text()='Phones & PDAs']")).click();
//a[text()='Phones & PDAs']
        //  WebElement product =driver.findElement(By.cssSelector(".nav > navbar-nav:nth-child(2)"));
        // actions.moveToElement(product).perform();
        //WebDriverWait wait=new; (driver,5);
        // product.click();
        //  phonesandpdas.click();   #elements > button:nth-child(3)
        //   actions.moveToElement(Phonesandpdas).perform();

        // WebElement phonephoto= driver.findElement(By.cssSelector(".img-responsive"));
        //actions.moveToElement(phonephoto).perform();


        // WebElement phonephoto= driver.findElement(By.cssSelector(".img-responsive"));
        //  actions.moveToElement(phonephoto).perform();

    }*/

