package com.uniovi.socialnetwork.pageobjects;

import com.uniovi.socialnetwork.util.SeleniumUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class PO_HomeView extends PO_NavView{

    static public void checkWelcomeToPage(WebDriver driver, int language){
        SeleniumUtils.waitLoadElementsBy(driver,"text",p.getString("welcome.message",language),getTimeout());
    }

    static public List<WebElement> getWelcomeMessageText(WebDriver driver, int language){
        return SeleniumUtils.waitLoadElementsBy(driver,"text",p.getString("welcome.message",language),getTimeout());
    }

    static public void checkChangeLanguage(WebDriver driver,String textLanguage1,String textLanguage,int locale1,int locale2){
        //Esperamos que se cargue el saludo de bienvenida en español
        PO_HomeView.checkWelcomeToPage(driver,locale1);
        //Cambiamos de idioma
        PO_HomeView.changeLanguage(driver,textLanguage);
        //Comprobamos que el texto haya cambiado
        PO_HomeView.checkWelcomeToPage(driver,locale2);
        //Volvemos a Español
        PO_HomeView.changeLanguage(driver,textLanguage1);
        //Esperamos a que se cargue el saludo de bienvenida en Español
        PO_HomeView.checkWelcomeToPage(driver,locale1);

    }

}
