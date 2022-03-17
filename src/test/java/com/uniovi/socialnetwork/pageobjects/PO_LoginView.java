package com.uniovi.socialnetwork.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class PO_LoginView extends PO_NavView{

    static public void fillLoginForm(WebDriver driver,String usernamep,String passwordp){
        WebElement username = driver.findElement(By.name("username"));
        username.click();
        username.clear();
        username.sendKeys(usernamep);
        WebElement password = driver.findElement(By.name("password"));
        password.click();
        password.clear();
        password.sendKeys(passwordp);
        //Boton
        By boton = By.className("btn");
        driver.findElement(boton).click();
    }

    static public void login(WebDriver driver,String email,String password){
        PO_HomeView.clickOption(driver,"login","class","btn btn-primary");
        //Rellenamos
        PO_LoginView.fillLoginForm(driver,email,password);
    }

    static public void logout(WebDriver driver){
        String loginText = PO_HomeView.getP().getString("signup.message",PO_Properties.getSPANISH());
        PO_PrivateView.clickOption(driver,"logout","text",loginText);
    }
}
