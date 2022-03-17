package com.uniovi.socialnetwork.pageobjects;


import com.uniovi.socialnetwork.util.SeleniumUtils;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class PO_NavView extends PO_View {

    public static void clickOption(WebDriver driver, String textOption, String criterio, String targetText){
        //Clickamos en la opcion de registro y esperamos a que se cargue el enlace de Registro
        List<WebElement> elements = SeleniumUtils.waitLoadElementsBy(driver,"@href",textOption,getTimeout());
        //Tiene que haber solo un elemento
        Assertions.assertEquals(1,elements.size());
        //Click
        elements.get(0).click();
        //Esperamos a que sea visible un elemento concreto
        elements = SeleniumUtils.waitLoadElementsBy(driver,criterio,targetText,getTimeout());
        //Assert
        Assertions.assertEquals(1,elements.size());
    }

    public static void changeLanguage(WebDriver driver,String textLanguage){
        //Clickamos en la opcion idioma
        List<WebElement> languageButton = SeleniumUtils.waitLoadElementsBy(driver,"id","btnLanguage",getTimeout());
        languageButton.get(0).click();
        //Esperamos que aparezca el menu desplegable
        SeleniumUtils.waitLoadElementsBy(driver,"id","languageDropdownMenuButton",getTimeout());
        //Clickamos la opcion ingles
        List<WebElement> selectedLanguage = SeleniumUtils.waitLoadElementsBy(driver,"id",textLanguage,getTimeout());
        selectedLanguage.get(0).click();
    }

    static public void selectOption(WebDriver driver,String option,String path,int opcionPanel){
        //Pinchamos en la opcion de notas
        List<WebElement> elements = PO_View.checkElementBy(driver,option,
                path);
        elements.get(opcionPanel).click();


    }
}
