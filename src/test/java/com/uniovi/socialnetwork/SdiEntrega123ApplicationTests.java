package com.uniovi.socialnetwork;

import com.uniovi.socialnetwork.pageobjects.*;
import com.uniovi.socialnetwork.services.InsertSampleDataService;
import com.uniovi.socialnetwork.services.UsersService;
import com.uniovi.socialnetwork.util.SeleniumUtils;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private InsertSampleDataService insertSampleDataService;

    @Autowired
    private UsersService usersService;

    @BeforeEach
    public void setUp(){
        driver.navigate().to(URL);
        usersService.deleteAll();
        insertSampleDataService.init();
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
        PO_View.checkElementBy(driver, "text", PO_View.getP().getString("user.list.title", PO_Properties.getSPANISH()));
        Assertions.assertEquals(URL + "/user/list", driver.getCurrentUrl());
        PO_LoginView.logOut(driver);
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
        PO_LoginView.logOut(driver);
    }

    @Test
    @Order(6)
    void PR06(){
        PO_LoginView.logIn(driver, "user01@email.com", "user01");
        SeleniumUtils.textIsNotPresentOnPage(driver, "Eliminar");
        Assertions.assertEquals(URL + "/user/list", driver.getCurrentUrl());
        PO_LoginView.logOut(driver);
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
        PO_LoginView.logIn(driver, "user01@email.com", "wrongPass");
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
        Assertions.assertEquals(15,list.size());
        PO_LoginView.logOut(driver);
    }

    @Test
    @Order(12)
    void PR12(){
        PO_LoginView.logIn(driver,"admin@email.com","admin");

        //Clickamos en el checkbox del usuario 1 y al boton eliminar
        PO_PrivateView.clickById(driver,"user01@email.com");
        PO_PrivateView.clickById(driver, "btnEliminar");

        List<WebElement> list = SeleniumUtils.waitLoadElementsBy(driver,"class","userRow", PO_View.getTimeout());
        Assertions.assertEquals(14,list.size());

        SeleniumUtils.textIsNotPresentOnPage(driver,"User01");

        PO_LoginView.logOut(driver);
    }

    @Test
    @Order(13)
    void PR13(){
        PO_LoginView.logIn(driver,"admin@email.com","admin");


        PO_PrivateView.clickById(driver,"user15@email.com");
        PO_PrivateView.clickById(driver,"btnEliminar");

        List<WebElement> list = SeleniumUtils.waitLoadElementsBy(driver,"class","userRow", PO_View.getTimeout());
        Assertions.assertEquals(14,list.size());

        SeleniumUtils.textIsNotPresentOnPage(driver,"User15");

        PO_LoginView.logOut(driver);
    }

    @Test
    @Order(14)
    void PR14(){
        PO_LoginView.logIn(driver,"admin@email.com","admin");

        PO_PrivateView.clickById(driver,"7");
        PO_PrivateView.clickById(driver,"10");
        PO_PrivateView.clickById(driver,"12");

        PO_PrivateView.clickById(driver,"btnEliminar");

        List<WebElement> list = SeleniumUtils.waitLoadElementsBy(driver,"class","userRow", PO_View.getTimeout());
        Assertions.assertEquals(12,list.size());

        SeleniumUtils.textIsNotPresentOnPage(driver,"User07");
        SeleniumUtils.textIsNotPresentOnPage(driver,"User10");
        SeleniumUtils.textIsNotPresentOnPage(driver,"User12");

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
        Assertions.assertEquals(4,list.size());
        SeleniumUtils.textIsNotPresentOnPage(driver,"User02");


        PO_LoginView.logOut(driver);
    }

    @Test
    @Order(19)
    void PR19(){
        //We log in as user01 and send an invitation to user05
        PO_LoginView.logIn(driver, "user01@email.com", "user01");
        //driver.navigate().to(URL + "/user/list");
        PO_PrivateView.clickById(driver, "send_user05@email.com");
        PO_LoginView.logOut(driver);

        //We log in as user02
        PO_LoginView.logIn(driver, "user05@email.com", "user05");
        driver.navigate().to(URL + "/invitation/list");

        List<WebElement> element = driver.findElements(By.id("invitation_User01")); //We check if we have an invitation from User01.
        Assertions.assertEquals(1, element.size());
        PO_LoginView.logOut(driver);
    }

    @Test
    @Order(20)
    void PR20(){
        //We log in as user01 and send an invitation to user05
        PO_LoginView.logIn(driver, "user01@email.com", "user01");
        driver.navigate().to(URL + "/user/list");
        PO_PrivateView.clickById(driver, "send_user05@email.com");

        //We check that the button disappear and we can't send an invitation to user05
        List<WebElement> element = driver.findElements(By.id("sendInvitation_5"));
        Assertions.assertEquals(0, element.size());
        PO_LoginView.logOut(driver);
    }

    @Test
    @Order(21)
    void PR21(){
        //We log in as user01 go to the invitations list
        PO_LoginView.logIn(driver, "user01@email.com", "user01");
        PO_PrivateView.clickById(driver, "friends-menu");
        PO_PrivateView.clickById(driver, "invitation-list");

        //We check that there are 3 invitations
        List<WebElement> element = driver.findElements(By.cssSelector("#invitationList tr"));
        Assertions.assertEquals(4, element.size()); //3 invitation rows + the header
        PO_LoginView.logOut(driver);
    }

    @Test
    @Order(22)
    void PR22(){
        PO_LoginView.logIn(driver, "user01@email.com", "user01");
        driver.navigate().to(URL + "/invitation/list");

        PO_PrivateView.clickById(driver,"invitationAccept_User05");

        //We check that there are 3 invitations
        List<WebElement> element = driver.findElements(By.cssSelector("#invitationList tr"));
        Assertions.assertEquals(3, element.size()); //3 invitation rows + the header

        SeleniumUtils.textIsNotPresentOnPage(driver,"User05");

    }

    @Test
    @Order(24)
    void PR24(){
        PO_LoginView.logIn(driver, "user01@email.com", "user01");
        driver.navigate().to(URL+ "/post/add");

        String checkText = "Publicacion para prueba";
        PO_PrivateView.fillAddPostForm(driver, checkText, "Texto de la publicación para prueba");

        PO_PrivateView.goToPage(driver, 2);

        List<WebElement> elements = PO_View.checkElementBy(driver, "text", "Publicacion para prueba") ;
        Assertions.assertEquals(checkText, elements.get(0).getText());
    }

    @Test
    @Order(25)
    void PR25(){
        PO_LoginView.logIn(driver, "user01@email.com", "user01");
        driver.navigate().to(URL+ "/post/add");

        PO_PrivateView.fillAddPostForm(driver, "     ", "Texto de la publicación para prueba");

        List<WebElement> result =  PO_SignUpView.checkElementByKey(driver, "Error.post.add.tittle",
                PO_Properties.getSPANISH() );

        String checkText = PO_HomeView.getP().getString("Error.post.add.tittle",
                PO_Properties.getSPANISH());
        Assertions.assertEquals(checkText , result.get(0).getText());
    }

    @Test
    @Order(26)
    void PR26(){
        PO_LoginView.logIn(driver, "user01@email.com", "user01");
        driver.navigate().to(URL+ "/post/list");

        List<WebElement> list = driver.findElements(By.name("post"));
        Assertions.assertEquals(5, list.size());
    }




    @Order(29)
    void PR29(){
        //Comprobamos el mensaje de bienvenida
        PO_HomeView.checkChangeLanguageText(driver, "welcome.message");

        //Vamos a la pagina de login y comprobamos el mensaje de Identificate
        driver.navigate().to(URL + "/login");
        PO_HomeView.checkChangeLanguageText(driver, "login.message");

        //Iniciamos sesion y comprobamos la lista de usarios
        PO_LoginView.logIn(driver, "user01@email.com", "user01");
        PO_HomeView.checkChangeLanguageText(driver, "user.list.title");
        PO_HomeView.checkChangeLanguageText(driver, "user.list.name");

        //Comprobamos la barra de navegacion
        PO_HomeView.checkChangeLanguageText(driver, "nav.post");
        PO_HomeView.checkChangeLanguageText(driver, "nav.friend");

        //Vamos a la lista de amigos y comprobamos el texto
        driver.navigate().to(URL + "/user/friends");
        PO_HomeView.checkChangeLanguageText(driver, "friend.list.title");
        PO_HomeView.checkChangeLanguageText(driver, "user.list.lastName");

        PO_LoginView.logOut(driver);
    }

    @Test
    @Order(30)
    void PR30(){
        //Comprobamos que nos redirige a la vista de log in
        driver.navigate().to(URL + "/user/list");
        PO_View.checkElementBy(driver, "text", PO_View.getP().getString("login.message", PO_Properties.getSPANISH()));
        Assertions.assertEquals(URL + "/login", driver.getCurrentUrl());
    }

    @Test
    @Order(31)
    void PR31() {
        //Comprobamos que nos redirige a la vista de log in
    }

    @Test
    @Order(44)
    void PR44(){
        PO_SignUpView.signUp(driver, "test@email.com", "testUser", "testUser", "123456", "123456");
        driver.navigate().to(URL + "/post/add");
        PO_PrivateView.fillAddPostFormWithImage(driver, "Publicacion", "Publicacion con imagen", "C:\\Dev\\imagenPrueba.png");
        driver.navigate().to(URL + "/post/list");

        List<WebElement> images = driver.findElements(By.className("img-fluid"));
        Assertions.assertEquals(1, images.size());
    }

    @Test
    @Order(45)
    void PR45(){
        PO_SignUpView.signUp(driver, "test@email.com", "testUser", "testUser", "123456", "123456");
        driver.navigate().to(URL + "/post/add");
        PO_PrivateView.fillAddPostForm(driver, "Publicacion", "Publicacion sin imagen");
        driver.navigate().to(URL + "/post/list");

        List<WebElement> images = driver.findElements(By.tagName("img-fluid"));
        Assertions.assertEquals(0, images.size());
    }



}
