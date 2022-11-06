import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.nio.charset.Charset;
import java.util.Random;


public class yuriiormsonTest {
    /*
    TC_11_01
    1. Открыть базовую ссылку
    2. Нажать на пункт меню Guide
    3. Подтвердить, что вы перешли на страницу со ссылкой
    https://openweathermap.org/guide и что title этой страницы OpenWeatherMap API guide - OpenWeatherMap
     */
    @Test
    public void testH1TagText_WhenOpenGuidPage() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "/Users/Yurii_Ormson/ChromeDriver/chromedriver");
        WebDriver driver = new ChromeDriver();

        String url = "https://openweathermap.org/";
        String expectedResult = "OpenWeatherMap API guide - OpenWeatherMap";
        String expectedResult1 = "https://openweathermap.org/guide";
        driver.get(url);
        Thread.sleep(5000);

        WebElement searchGuideLink = driver.findElement(
                By.xpath("//div[@id = 'desktop-menu']/ul/li[1]")
        );
        searchGuideLink.click();
        Thread.sleep(2000);

        String actualResult = driver.getTitle();
        Assert.assertEquals(actualResult, expectedResult);

        String actualResult1 = driver.getCurrentUrl();
        Assert.assertEquals(actualResult1,expectedResult1);

        driver.quit();
    }

    /*
    TC_11_02
    1. Открыть базовую ссылку
    2. Нажать на единицы измерения
    3. Подтвердить, что температура для города показана в Фарингейтах
    Imperial: °F, mph
     */
    @Test
    public void testSpanHeading_CurrentTempFahrenheit() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "/Users/Yurii_Ormson/ChromeDriver/chromedriver");
        WebDriver driver = new ChromeDriver();

        String url = "https://openweathermap.org/";
        String expectedResult = "°F";
        driver.get(url);
        Thread.sleep(5000);

        WebElement changeMeasureToFahrenheit = driver.findElement(
                By.xpath("//div[@id = 'weather-widget']/div/div/div/div/div/div[3]")
        );
        changeMeasureToFahrenheit.click();
        Thread.sleep(4000);

        WebElement spanHeading = driver.findElement(
                By.xpath("//div[@id = 'weather-widget']/div[2]/div[1]/div[1]/div[2]/div[1]/span")
        );

        String spanHeadingText = spanHeading.getText();
        String actualResult = spanHeadingText.substring(spanHeadingText.length() - 2);

        Assert.assertEquals(actualResult, expectedResult);

        driver.quit();
    }

    /*
    TC_11_03
    1. Открыть базовую ссылку
    2. Подтвердить, что внизу страницы есть панель с текстом
    “We use cookies which are essential for the site to work.
     We also use non-essential cookies to help us improve our services. Any data collected is anonymised.
     You can allow all cookies or manage them individually.”
    3. Подтвердить, что на панели внизу страницы есть 2 кнопки “Allow all” и “Manage cookies”
     */
    @Test
    public void testStickFooterPanel_WhenOpenFirstTime() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "/Users/Yurii_Ormson/ChromeDriver/chromedriver");
        WebDriver driver = new ChromeDriver();

        String url = "https://openweathermap.org/";
        String expectedResult = "We use cookies which are essential for the site to work." +
                " We also use non-essential cookies to help us improve our services." +
                " Any data collected is anonymised. You can allow all cookies or manage them individually.";
        String expectedResult1 = "Allow all";
        String expectedResult2 = "Manage cookies";
        driver.get(url);
        Thread.sleep(5000);

        WebElement stickFooterDescription = driver.findElement(
                By.xpath("//div[@id = 'stick-footer-panel']/div/div/div/div/p")
        );
        String actualResult = stickFooterDescription.getText();

        Assert.assertEquals(actualResult, expectedResult);

        WebElement allowAllButton = driver.findElement(
                By.xpath("//div[@id = 'stick-footer-panel']/div/div/div/div/div/button")
        );
        String actualResult1 = allowAllButton.getText();

        Assert.assertEquals(actualResult1, expectedResult1);

        WebElement manageCookiesLink = driver.findElement(
                By.xpath("//div[@id = 'stick-footer-panel']/div/div/div/div/div/a")
        );
        String actualResult2 = manageCookiesLink.getText();

        Assert.assertEquals(actualResult2, expectedResult2);

        driver.quit();
    }

    /*
    TC_11_04
    1. Открыть базовую ссылку
    2. Подтвердить, что в меню Support есть 3 подменю с названиями “FAQ”, “How to start” и “Ask a question”
     */
    @Test
    public void testSupportDropdown_WhenDropdownOpens() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "/Users/Yurii_Ormson/ChromeDriver/chromedriver");
        WebDriver driver = new ChromeDriver();

        String url = "https://openweathermap.org/";
        String expectedResult = "FAQ";
        String expectedResult1 = "How to start";
        String expectedResult2 = "Ask a question";
        driver.get(url);
        driver.manage().window().maximize();
        Thread.sleep(5000);

        WebElement supportDropdown = driver.findElement(
                By.xpath("//div[@id = 'support-dropdown']")
        );
        supportDropdown.click();

        WebElement supportDropdownFaq = driver.findElement(
                By.xpath("//div[@id = 'desktop-menu']/ul/li[12]/ul/li[1]/a")
        );
        String actualResult = supportDropdownFaq.getText();

        Assert.assertEquals(actualResult, expectedResult);

        WebElement supportDropdownHowToStart = driver.findElement(
                By.xpath("//div[@id = 'desktop-menu']/ul/li[12]/ul/li[2]/a")
        );
        String actualResult1 = supportDropdownHowToStart.getText();

        Assert.assertEquals(actualResult1, expectedResult1);

        WebElement supportDropdownAskAQuestion = driver.findElement(
                By.xpath("//div[@id = 'desktop-menu']/ul/li[12]/ul/li[3]/a")
        );
        String actualResult2 = supportDropdownAskAQuestion.getText();

        Assert.assertEquals(actualResult2, expectedResult2);

        driver.quit();
    }

    /*
    TC_11_05
    1. Открыть базовую ссылку
    2. Нажать пункт меню Support → Ask a question
    3. Заполнить поля Email, Subject, Message
    4. Не подтвердив CAPTCHA, нажать кнопку Submit
    5. Подтвердить, что пользователю будет показана ошибка “reCAPTCHA verification failed, please try again.”
     */
    @Test
    public void testHelpBlock_WhenCaptchaHasError() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "/Users/Yurii_Ormson/ChromeDriver/chromedriver");
        WebDriver driver = new ChromeDriver();

        String url = "https://openweathermap.org/";
        String expectedResult = "reCAPTCHA verification failed, please try again.";
        String email = Util.getSaltString() + "@redrover.com";
        byte[] array = new byte[11];
        new Random().nextBytes(array);
        String generatedString = new String(array, Charset.forName("UTF-8"));
        driver.get(url);
        driver.manage().window().maximize();
        Thread.sleep(5000);

        WebElement supportDropdown = driver.findElement(
                By.xpath("//div[@id = 'support-dropdown']")
        );
        supportDropdown.click();

        //Store the ID of the original window
        String originalWindow = driver.getWindowHandle();

        WebElement supportDropdownAskAQuestion = driver.findElement(
                By.xpath("//div[@id = 'desktop-menu']/ul/li[12]/ul/li[3]/a")
        );
        supportDropdownAskAQuestion.click();
        Thread.sleep(5000);

        //Loop through until we find a new window handle
        for (String windowHandle : driver.getWindowHandles()) {
            if (!originalWindow.contentEquals(windowHandle)) {
                driver.switchTo().window(windowHandle);
                break;
            }
        }

        WebElement emailField = driver.findElement(
                By.xpath("//form[@id]/div[5]/div/input")
        );
        emailField.click();
        emailField.sendKeys(email);

        WebElement subjectDropdown = driver.findElement(
                By.xpath("//form[@id]/div[6]/div/select")
        );
        subjectDropdown.click();

        WebElement subjectDropdownSelect = driver.findElement(
                By.xpath("//form[@id]/div[6]/div/select/option[3]")
        );
        subjectDropdownSelect.click();

        WebElement messageField = driver.findElement(
                By.xpath("//form[@id]/div[7]/div/textarea")
        );
        messageField.click();
        messageField.sendKeys(generatedString);

        WebElement submitButton = driver.findElement(
                By.xpath("//form[@id]/div[9]/input")
        );
        submitButton.click();

        WebElement reCaptchaVerificationMessage = driver.findElement(
                By.xpath("//form[@id]/div[9]/div/div[contains(@class, 'help-block')]")
        );
        String actualResult = reCaptchaVerificationMessage.getText();

        Assert.assertEquals(actualResult, expectedResult);

        driver.quit();
    }

    /*
    TC_11_06
    1. Открыть базовую ссылку
    2. Нажать пункт меню Support → Ask a question
    3. Оставить значение по умолчанию в checkbox Are you an OpenWeather user? 4. Оставить пустым поле Email
    5. Заполнить поля Subject, Message
    6. Подтвердить CAPTCHA
    7. Нажать кнопку Submit
    8. Подтвердить, что в поле Email пользователю будет показана ошибка
     */
    @Test
    public void testSpanHelpBlock_WhenEmailEmpty() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "/Users/Yurii_Ormson/ChromeDriver/chromedriver");
        WebDriver driver = new ChromeDriver();

        String url = "https://openweathermap.org/";
        String expectedResult = "can't be blank";
        byte[] array = new byte[256]; // length is bounded by 7
        new Random().nextBytes(array);
        String generatedString = new String(array, Charset.forName("UTF-8"));
        driver.get(url);
        driver.manage().window().maximize();
        Thread.sleep(5000);

        WebElement supportDropdown = driver.findElement(
                By.xpath("//div[@id = 'support-dropdown']")
        );
        supportDropdown.click();

        //Store the ID of the original window
        String originalWindow = driver.getWindowHandle();

        WebElement supportDropdownAskAQuestion = driver.findElement(
                By.xpath("//div[@id = 'desktop-menu']/ul/li[12]/ul/li[3]/a")
        );
        supportDropdownAskAQuestion.click();
        Thread.sleep(5000);

        //Loop through until we find a new window handle
        for (String windowHandle : driver.getWindowHandles()) {
            if (!originalWindow.contentEquals(windowHandle)) {
                driver.switchTo().window(windowHandle);
                break;
            }
        }
        Thread.sleep(1000);

        WebElement subjectDropdown = driver.findElement(
                By.xpath("//form[@id]/div[6]/div/select")
        );
        subjectDropdown.click();

        WebElement subjectDropdownSelect = driver.findElement(
                By.xpath("//form[@id]/div[6]/div/select/option[3]")
        );
        subjectDropdownSelect.click();
        Thread.sleep(1000);

        WebElement messageField = driver.findElement(
                By.xpath("//form[@id]/div[7]/div/textarea")
        );
        messageField.click();
        messageField.sendKeys(generatedString);
        Thread.sleep(1000);

        WebElement captchaBox = driver.findElement(
                By.xpath("//iframe[@title = 'reCAPTCHA']")
        );
        captchaBox.click();
        Thread.sleep(5000);

//        WebElement captchaCheckbox = driver.findElement(
//                By.xpath("//div[@id = 'rc-anchor-container']/div/div/div/div/span/div[1]")
//        );
//        captchaCheckbox.click();


        WebElement emailValidation = driver.findElement(
                By.xpath("//form[@id = 'new_question_form']/div[5]/div/span")
        );
        String actualResult = emailValidation.getText();

        Assert.assertEquals(actualResult, expectedResult);
    }

    /*
    TC_11_07
    1. Открыть базовую ссылку
    2. Нажать на единицы измерения
    3. Нажать на единицы измерения
    4. Подтвердить, что в результате этих действий, единицы измерения температуры изменились с F на С
     */
    @Test
    public void testSpanHeading_WhenSwitchBetweenFahrenheitCelsium() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "/Users/Yurii_Ormson/ChromeDriver/chromedriver");
        WebDriver driver = new ChromeDriver();

        String url = "https://openweathermap.org/";
        String expectedResult = "°F";
        String expectedResult1 = "°C";
        driver.get(url);
        Thread.sleep(5000);

        WebElement changeMeasureToFahrenheit = driver.findElement(
                By.xpath("//div[@id = 'weather-widget']/div/div/div/div/div/div[3]")
        );
        changeMeasureToFahrenheit.click();
        Thread.sleep(4000);

        WebElement spanHeading = driver.findElement(
                By.xpath("//div[@id = 'weather-widget']/div[2]/div[1]/div[1]/div[2]/div[1]/span")
        );

        String spanHeadingTextFahre = spanHeading.getText();
        String actualResult = spanHeadingTextFahre.substring(spanHeadingTextFahre.length() - 2);

        Assert.assertEquals(actualResult, expectedResult);

        WebElement changeMeasureToCelsium = driver.findElement(
                By.xpath("//div[@id = 'weather-widget']/div/div/div/div/div/div[2]")
        );
        changeMeasureToCelsium.click();
        Thread.sleep(4000);

        String spanHeadingTextCelsi = spanHeading.getText();
        String actualResult1 = spanHeadingTextCelsi.substring(spanHeadingTextCelsi.length() - 2);

        Assert.assertEquals(actualResult1, expectedResult1);

        driver.quit();
    }

    /*
    TC_11_08
    1. Открыть базовую ссылку
    2. Нажать на лого компании
    3. Дождаться, когда произойдет перезагрузка сайта, и подтвердить, что текущая ссылка не изменилась
     */
    @Test
    public void testHomeUrl_WhenClickOnLogo() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "/Users/Yurii_Ormson/ChromeDriver/chromedriver");
        WebDriver driver = new ChromeDriver();

        String url = "https://openweathermap.org/";
        String expectedResult = "https://openweathermap.org/";
        driver.get(url);
        Thread.sleep(5000);

        WebElement imgSrcLogoLink = driver.findElement(
                By.xpath("//ul[@id = 'first-level-nav']/li/a/img")
        );
        imgSrcLogoLink.click();
        Thread.sleep(5000);

        String actualResult = driver.getCurrentUrl();
        Assert.assertEquals(actualResult, expectedResult);

        driver.quit();
    }

    /*
    TC_11_09
    1. Открыть базовую ссылку
    2. В строке поиска в навигационной панели набрать “Rome”
    3. Нажать клавишу Enter
    4. Подтвердить, что вы перешли на страницу в ссылке которой содержатся слова “find” и “Rome”
    5. Подтвердить, что в строке поиска на новой странице вписано слово “Rome”
     */
    @Test
    public void testInputNamePlaceholder_WeatherInYourCity() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "/Users/Yurii_Ormson/ChromeDriver/chromedriver");
        WebDriver driver = new ChromeDriver();

        String url = "https://openweathermap.org/";
        String cityName = "Rome";
        String urlContainFind = "find";
        String expectedResult = "find";
        String expectedResult1 = "Rome";
        driver.get(url);
        Thread.sleep(5000);

        WebElement weatherInYourCity = driver.findElement(
                By.xpath("//div[@id = 'desktop-menu']/form/input[1]")
        );
        weatherInYourCity.click();
        weatherInYourCity.sendKeys(cityName);
        weatherInYourCity.sendKeys(Keys.ENTER);
//        weatherInYourCity.submit();

        String currentUrl = driver.getCurrentUrl();
        int containWord = currentUrl.indexOf(urlContainFind);
        String actualResult = "";
        if (containWord > 0) {
            actualResult = expectedResult;
        } else {
            actualResult = "";
        }
        Assert.assertEquals(actualResult, expectedResult);

        String actualResult1 = "";
        containWord = currentUrl.indexOf(cityName);
        if (containWord > 0) {
            actualResult1 = expectedResult1;
        } else {
            actualResult1 = "";
        }
        Assert.assertEquals(actualResult1, expectedResult1);

        WebElement searchField = driver.findElement(
                By.xpath("//input[@id = 'search_str']")
        );

        String actualResult2 = searchField.getAttribute("value");
        Assert.assertEquals(actualResult2, expectedResult1);


        driver.quit();

    }
    /*
    TC_11_10
    1. Открыть базовую ссылку
    2. Нажать на пункт меню API
    3. Подтвердить, что на открывшейся странице пользователь видит 30 оранжевых кнопок
     */
    @Test
    public void testOrangeClass_CountOrangeButton() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "/Users/Yurii_Ormson/ChromeDriver/chromedriver");
        WebDriver driver = new ChromeDriver();

        String url = "https://openweathermap.org/";
        int expectedResult = 30;
        driver.get(url);
        Thread.sleep(5000);

        WebElement linkAPI = driver.findElement(By.xpath("//a[@href = '/api']"));
        linkAPI.click();

        int actualResult = driver.findElements(By.xpath("//a[contains(@class, 'orange')]")).size();

        Assert.assertEquals(actualResult,expectedResult);

        driver.quit();
    }

}
