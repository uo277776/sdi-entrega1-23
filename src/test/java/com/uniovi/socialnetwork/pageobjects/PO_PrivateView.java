package com.uniovi.socialnetwork.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class PO_PrivateView extends PO_NavView{

    static public void click(WebDriver driver, String xpath, int index){
        List<WebElement> elements = PO_View.checkElementBy(driver, "free", xpath);
        elements.get(index).click();
    }

    static public void clickById(WebDriver driver, String id){
        List<WebElement> elements = PO_View.checkElementBy(driver, "@id", id);
        elements.get(0).click();
    }
}
