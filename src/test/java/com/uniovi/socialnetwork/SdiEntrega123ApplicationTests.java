package com.uniovi.socialnetwork;

import com.uniovi.socialnetwork.pageobjects.*;
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


    //static String PathFirefox = "C:\\Program Files\\Mozilla Firefox\\firefox.exe";
    //static String Geckodriver = "C:\\Path\\geckodriver-v0.30.0-win64.exe";
    static String PathFirefox = "C:\\Users\\buhos\\AppData\\Local\\Mozilla Firefox\\firefox.exe";
    static String Geckodriver = "C:\\Users\\buhos\\Documents\\Universidad\\Cuarto\\Segundo Cuatri\\SDI\\Utilidad\\PL-SDI-Sesión5-material\\geckodriver-v0.30.0-win64.exe";
    //static String Geckodriver = "C:\\Dev\\geckodriver-v0.30.0-win64.exe";

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
    @Order(1)
    void PR01(){
        PO_SignUpView.signUp(driver, "test@email.com", "testName", "testLastName", "testPass", "testPass");
        PO_View.checkElementBy(driver, "text", "Usuarios");
        Assertions.assertEquals(URL + "/user/list", driver.getCurrentUrl());
    }

    @Test
    @Order(2)
    void PR02(){
        PO_SignUpView.signUp(driver, "", "", "", "", "");
        PO_View.checkElementBy(driver, "text", "Identifícate");
        Assertions.assertEquals(URL + "/signup", driver.getCurrentUrl());
    }

    @Test
    @Order(3)
    void PR03(){
        PO_SignUpView.signUp(driver, "prueba@email.com", "testName", "testLastName", "testPass", "wrongPass");
        String checkText = PO_HomeView.getP().getString("Error.signup.passwordConfirm.coincidence", PO_Properties.getSPANISH());
        PO_View.checkElementBy(driver, "text", checkText);
    }

    @Test
    @Order(4)
    void PR04(){
        PO_SignUpView.signUp(driver, "user01@email.com", "User01", "User01", "user01", "user01");
        String checkText = PO_HomeView.getP().getString("Error.signup.email.duplicate", PO_Properties.getSPANISH());
        PO_View.checkElementBy(driver, "text", checkText);
    }

    @Test
    @Order(5)
    void PR05(){
        PO_LoginView.logIn(driver, "admin@email.com", "admin");
        PO_View.checkElementBy(driver, "text", "Eliminar");
        Assertions.assertEquals(URL + "/user/list", driver.getCurrentUrl());
    }

    @Test
    @Order(6)
    void PR06(){
        PO_LoginView.logIn(driver, "user01@email.com", "user01");
        SeleniumUtils.textIsNotPresentOnPage(driver, "Eliminar");
        Assertions.assertEquals(URL + "/user/list", driver.getCurrentUrl());
    }

    @Test
    @Order(7)
    void PR07(){
        PO_LoginView.logIn(driver, "", "");
        Assertions.assertEquals(URL + "/login", driver.getCurrentUrl());
    }

    @Test
    @Order(8)
    void PR08(){
        PO_LoginView.logIn(driver, "user01@emai.com", "wrongPass");
        PO_View.checkElementBy(driver, "text", PO_View.getP().getString("Error.login", PO_Properties.getSPANISH()));
        Assertions.assertEquals(URL + "/login?error", driver.getCurrentUrl());
    }

    @Test
    @Order(9)
    void PR09(){
        PO_LoginView.logIn(driver, "user01@email.com", "user01");
        PO_LoginView.logOut(driver);

        String checkText = "Identifícate";
        List<WebElement> result = PO_View.checkElementBy(driver, "text", checkText);
        Assertions.assertEquals(checkText, result.get(0).getText());

        Assertions.assertEquals(URL + "/login?logout", driver.getCurrentUrl());
    }

    @Test
    @Order(10)
    void PR10(){
        String checkText = "Desconectar";
        SeleniumUtils.waitTextIsNotPresentOnPage(driver, checkText, PO_View.getTimeout());
    }

    @Test
    @Order(11)
    void PR11(){
        PO_LoginView.logIn(driver,"admin@email.com","admin");

        List<WebElement> list = SeleniumUtils.waitLoadElementsBy(driver,"class","userRow", PO_View.getTimeout());
        Assertions.assertEquals(16,list.size());
    }

    @Test
    @Order(12)
    void PR12(){
        PO_LoginView.logIn(driver,"admin@email.com","admin");

        //Clickamos en el checkbox del usuario 1 y al boton eliminar
        PO_PrivateView.clickById(driver,"User01");
        PO_PrivateView.clickById(driver, "btnEliminar");

        List<WebElement> list = SeleniumUtils.waitLoadElementsBy(driver,"class","userRow", PO_View.getTimeout());
        Assertions.assertEquals(15,list.size());

        SeleniumUtils.textIsNotPresentOnPage(driver,"User01");

        PO_LoginView.logOut(driver);
    }

    @Test
    @Order(13)
    void PR13(){
        PO_LoginView.logIn(driver,"admin@email.com","admin");


        PO_PrivateView.clickById(driver,"testName");
        PO_PrivateView.clickById(driver,"btnEliminar");

        List<WebElement> list = SeleniumUtils.waitLoadElementsBy(driver,"class","userRow", PO_View.getTimeout());
        Assertions.assertEquals(14,list.size());

        SeleniumUtils.textIsNotPresentOnPage(driver,"testName");

        PO_LoginView.logOut(driver);
    }


    @Test
    @Order(15)
    void PR15(){
        PO_LoginView.logIn(driver,"user02@email.com","user02");

        for(int i = 0;i<2;i++){
            List<WebElement> list = SeleniumUtils.waitLoadElementsBy(driver,"class","userRow", PO_View.getTimeout());
            Assertions.assertEquals(5,list.size());
            SeleniumUtils.textIsNotPresentOnPage(driver,"user02");
            PO_PrivateView.clickById(driver,"siguiente");
        }
        List<WebElement> list = SeleniumUtils.waitLoadElementsBy(driver,"class","userRow", PO_View.getTimeout());
        Assertions.assertEquals(3,list.size());
        SeleniumUtils.textIsNotPresentOnPage(driver,"User02");

        PO_LoginView.logOut(driver);

    }


    @Test
    @Order(1)
    void PR19(){
        //We log in as user01 and send an invitation to user02
        PO_LoginView.logIn(driver, "user02@email.com", "user02");
        driver.navigate().to(URL + "/user/list");
        PO_PrivateView.clickById(driver, "sendInvitation_4");
        PO_LoginView.logOut(driver);

        //We log in as user02
        PO_LoginView.logIn(driver, "user04@email.com", "user04");
        driver.navigate().to(URL + "/invitation/list");

        List<WebElement> element = driver.findElements(By.id("invitation_User02")); //We check if we have an invitation from User01.
        Assertions.assertEquals(1, element.size());
    }

    @Test
    @Order(2)
    void PR20(){
        //We log in as user01 and send an invitation to user02
        PO_LoginView.logIn(driver, "user03@email.com", "user03");
        driver.navigate().to(URL + "/user/list");
        PO_PrivateView.clickById(driver, "sendInvitation_5");

        //We check that the button disappear and we can't send an invitation to user02
        List<WebElement> element = driver.findElements(By.id("sendInvitation_5"));
        Assertions.assertEquals(0, element.size());
    }

    @Test
    @Order(3)
    void PR21(){
        //We log in as user01 go to the invitations list
        PO_LoginView.logIn(driver, "user05@email.com", "user05");
        driver.navigate().to(URL + "/invitation/list");

        //We check that there are 3 invitations
        List<WebElement> element = driver.findElements(By.cssSelector("#invitationList tr"));
        Assertions.assertEquals(4, element.size()); //3 invitation rows + the header
    }

    @Test
    @Order(22)
    void PR22(){
        PO_LoginView.logIn(driver, "user05@email.com", "user05");
        driver.navigate().to(URL + "/invitation/list");

        PO_PrivateView.clickById(driver,"invitationAccept_User10");

        //We check that there are 3 invitations
        List<WebElement> element = driver.findElements(By.cssSelector("#invitationList tr"));
        Assertions.assertEquals(3, element.size()); //3 invitation rows + the header

        SeleniumUtils.textIsNotPresentOnPage(driver,"User10");




    }

}
