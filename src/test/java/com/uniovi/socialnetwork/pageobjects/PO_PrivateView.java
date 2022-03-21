package com.uniovi.socialnetwork.pageobjects;

import com.uniovi.socialnetwork.util.SeleniumUtils;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class PO_PrivateView extends PO_NavView{

    static public void click(WebDriver driver, String xpath, int index){
        List<WebElement> elements = PO_View.checkElementBy(driver, "free", xpath);
        elements.get(index).click();
    }

    static public void clickById(WebDriver driver, String id) {
        List<WebElement> elements = PO_View.checkElementBy(driver, "@id", id);
        elements.get(0).click();
    }

    static public void fillFormAddMark(WebDriver driver, int userOrder, String descriptionp, String scorep){
        SeleniumUtils.waitSeconds(driver,5);
        new Select(driver.findElement(By.id("user"))).selectByIndex(userOrder);
        //Campo descripcion
        WebElement description = driver.findElement(By.name("description"));
        description.clear();
        description.sendKeys(descriptionp);
        WebElement score = driver.findElement(By.name("score"));
        score.click();
        score.clear();
        score.sendKeys(scorep);
        By boton = By.className("btn");
        driver.findElement(boton).click();
    }

    static public void fillAddPostForm(WebDriver driver, String tittlep, String textp){
        //We wait 5 seconds for the DOM to load because on some computers it fails.
        SeleniumUtils.waitSeconds(driver, 5);

        WebElement tittle = driver.findElement(By.id("tittle"));
        tittle.clear();
        tittle.sendKeys(tittlep);

        WebElement text = driver.findElement(By.id("text"));
        text.clear();
        text.sendKeys(textp);

        By botton = By.className("btn");
        driver.findElement(botton).click();
    }


    static public void contarFilas(WebDriver driver,int number){
        //Contamos numero de filas
        List<WebElement> list = SeleniumUtils.waitLoadElementsBy(driver,"free","//tbody/tr",
                PO_View.getTimeout());
        Assertions.assertEquals(number,list.size());
    }

    static public void goToPage(WebDriver driver, int numberPage){
        //Esperamos a que se muestren los enlaces de paginación de la lista de notas
        List<WebElement> elements = PO_View.checkElementBy(driver, "free", "//a[contains(@class, 'page-link')]");

        //Nos vamos a una página
        elements.get(numberPage).click();
    }

}
