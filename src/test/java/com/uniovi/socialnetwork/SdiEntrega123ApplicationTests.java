package com.uniovi.socialnetwork;

import com.uniovi.socialnetwork.pageobjects.PO_HomeView;
import com.uniovi.socialnetwork.pageobjects.PO_LoginView;
import com.uniovi.socialnetwork.pageobjects.PO_PrivateView;
import com.uniovi.socialnetwork.pageobjects.PO_View;
import com.uniovi.socialnetwork.util.SeleniumUtils;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class SdiEntrega123ApplicationTests {

    static String PathFirefox = "C:\\Program Files\\Mozilla Firefox\\firefox.exe";
    //static String Geckodriver = "C:\\Path\\geckodriver-v0.30.0-win64.exe";
    static String Geckodriver = "C:\\Dev\\geckodriver-v0.30.0-win64.exe";

    //static String PathFirefox = "/Applications/Firefox.app/Contents/MacOS/firefox-bin";
    // static String Geckodriver = "/Users/USUARIO/selenium/geckodriver-v0.30.0-macos";

    // Común a Windows y a MACOSX
    static WebDriver driver = getDriver(PathFirefox, Geckodriver);
    static String URL = "http://localhost:8090";
    public static WebDriver getDriver(String PathFirefox, String Geckodriver) {
        System.setProperty("webdriver.firefox.bin", PathFirefox);
        System.setProperty("webdriver.gecko.driver", Geckodriver);
        driver = new FirefoxDriver(); return driver;
    }

    @BeforeEach
    public void setUp(){
        driver.navigate().to(URL);
    }

    //Después de cada prueba se borran las cookies del navegador
    @AfterEach
    public void tearDown(){
        driver.manage().deleteAllCookies();
    }
    //Antes de la primera prueba
    @BeforeAll
    static public void begin() {}

    //Al finalizar la última prueba
    @AfterAll
    static public void end() {
        //Cerramos el navegador al finalizar las pruebas
        driver.quit();
    }

    @Test
    @Order(11)
    void PR11(){
        PO_LoginView.logIn(driver,"admin@email.com","admin");

        //Entramos en la lista de usuarios
        List<WebElement> elements = PO_View.checkElementBy(driver,"id","users-menu");
        elements.get(0).click();
        //Clickamos la opcion de ver usuarios
        elements = PO_View.checkElementBy(driver,"class","dropdown-item");
        elements.get(0).click();

        List<WebElement> list = SeleniumUtils.waitLoadElementsBy(driver,"class","userRow", PO_View.getTimeout());
        Assertions.assertEquals(6,list.size());
    }

    @Test
    @Order(1)
    void PR19(){
        //We log in as user01 and send an invitation to user02
        PO_LoginView.logIn(driver, "user01@email.com", "user01");
        driver.navigate().to(URL + "/user/list");
        PO_PrivateView.clickById(driver, "sendInvitation_2");
        PO_LoginView.logOut(driver);

        //We log in as user02
        PO_LoginView.logIn(driver, "user02@email.com", "user02");
        driver.navigate().to(URL + "/invitation/list");

        List<WebElement> element = driver.findElements(By.id("invitation_User01")); //We check if we have an invitation from User01.
        Assertions.assertEquals(1, element.size());
    }

    @Test
    @Order(2)
    void PR20(){
        //We log in as user01 and send an invitation to user02
        PO_LoginView.logIn(driver, "user01@email.com", "user01");
        driver.navigate().to(URL + "/user/list");
        PO_PrivateView.clickById(driver, "sendInvitation_2");

        //We check that the button disappear and we can't send an invitation to user02
        List<WebElement> element = driver.findElements(By.id("sendInvitation_2"));
        Assertions.assertEquals(0, element.size());
    }

    @Test
    @Order(3)
    void PR21(){
        //We log in as user01 go to the invitations list
        PO_LoginView.logIn(driver, "user01@email.com", "user01");
        driver.navigate().to(URL + "/invitation/list");

        //We check that there are 3 invitations
        List<WebElement> element = driver.findElements(By.cssSelector("#invitationList tr"));
        Assertions.assertEquals(4, element.size()); //3 invitation rows + the header
    }

}
