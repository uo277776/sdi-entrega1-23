package com.uniovi.socialnetwork;

import com.uniovi.socialnetwork.pageobjects.PO_LoginView;
import com.uniovi.socialnetwork.pageobjects.PO_NavView;
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

    static String PathFirefox = "C:\\Users\\buhos\\AppData\\Local\\Mozilla Firefox\\firefox.exe";
    static String Geckodriver = "C:\\Users\\buhos\\Documents\\Universidad\\Cuarto\\Segundo Cuatri\\SDI\\Utilidad\\PL-SDI-Sesión5-material\\geckodriver-v0.30.0-win64.exe";

    static WebDriver driver = getDriver(PathFirefox,Geckodriver);
    static String URL = "http://localhost:8090";

    public static WebDriver getDriver(String pathFirefox,String geckodriver){
        System.setProperty("webdriver.firefox.bin",PathFirefox);
        System.setProperty("webdriver.gecko.driver",Geckodriver);
        driver = new FirefoxDriver();
        return driver;
    }

    @BeforeEach
    public void setUp(){
        driver.navigate().to(URL);
    }

    @AfterEach
    public void tearDown(){
        driver.manage().deleteAllCookies();
    }

    @BeforeAll
    static public void begin(){

    }

    @AfterAll
    static public void end(){
        driver.quit();
    }


    @Test
    @Order(11)
    void PR11(){
        PO_LoginView.login(driver,"admin@email.com","admin");

        //Entramos en la lista de usuarios
        List<WebElement> elements = PO_View.checkElementBy(driver,"id","users-menu");
        elements.get(0).click();
        //Clickamos la opcion de ver usuarios
        elements = PO_View.checkElementBy(driver,"class","dropdown-item");
        elements.get(0).click();

        List<WebElement> list = SeleniumUtils.waitLoadElementsBy(driver,"class","userRow", PO_View.getTimeout());
        Assertions.assertEquals(6,list.size());
    }




}
