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


    static public void contarFilas(WebDriver driver,int number){
        //Contamos numero de filas
        List<WebElement> list = SeleniumUtils.waitLoadElementsBy(driver,"free","//tbody/tr",
                PO_View.getTimeout());
        Assertions.assertEquals(number,list.size());
    }
}
