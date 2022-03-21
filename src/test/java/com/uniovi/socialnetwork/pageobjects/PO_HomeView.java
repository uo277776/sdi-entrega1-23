package com.uniovi.socialnetwork.pageobjects;

import com.uniovi.socialnetwork.util.SeleniumUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class PO_HomeView extends PO_NavView{

    static public void checkChangeLanguageText(WebDriver driver, String textKey){
        //Comprobmaos el texto en español
        PO_HomeView.checkLanguage(driver, textKey, PO_Properties.getSPANISH());
        //Cambiamos de idioma
        PO_HomeView.changeLanguage(driver, "btnEnglish");
        //Comprobamos que haya cambiado a ingles
        PO_HomeView.checkLanguage(driver, textKey, PO_Properties.getENGLISH());
        //Volvemos a Español
        PO_HomeView.changeLanguage(driver, "btnSpanish");
        //Comprobamos que haya cambiado a español
        PO_HomeView.checkLanguage(driver, textKey, PO_Properties.getSPANISH());

    }

    static public void checkLanguage(WebDriver driver, String textKey, int language){
        SeleniumUtils.waitLoadElementsBy(driver, "text", p.getString(textKey, language), getTimeout());
    }

}
