package com.uniovi.socialnetwork.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class PO_LoginView extends PO_NavView{

    static public void fillLoginForm(WebDriver driver, String emailp, String passwordp){

        WebElement username = driver.findElement(By.name("username"));
        username.click();
        username.clear();
        username.sendKeys(emailp);

        WebElement password = driver.findElement(By.name("password"));
        password.click();
        password.clear();
        password.sendKeys(passwordp);

        By boton = By.className("btn");
        driver.findElement(boton).click();
    }

    static public void logIn(WebDriver driver, String username, String password, String checkText){
        //Vamos al formulario de logueo.
        PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
        //Rellenamos el formulario
        PO_LoginView.fillLoginForm(driver, username, password);
        //Comprobamos que entramos en la pagina privada
        PO_View.checkElementBy(driver, "text", checkText);
    }

    static public void logOut(WebDriver driver){
        String loginText = PO_HomeView.getP().getString("signup.message", PO_Properties.getSPANISH());
        PO_PrivateView.clickOption(driver, "logout", "text", loginText);
    }
}
