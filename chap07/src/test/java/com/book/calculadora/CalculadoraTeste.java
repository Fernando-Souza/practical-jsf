
package com.book.calculadora;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

/**
 *
 * @author fernando
 */
public class CalculadoraTeste {
    
    private static WebDriver _driver;
    
    public CalculadoraTeste() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        
        _driver = new FirefoxDriver();
    }
    
    @AfterClass
    public static void tearDownClass() {
        
        _driver.quit();
    }
    
    @Before
    public void setUp() {
        
        _driver.get("http://localhost:8080/chap07/calculadora.xhtml");
        
        setValue("form:param1","6");
        setValue("form:param2","4");
    }
    
    private void setValue(String id,String value){
        
        WebElement element = _driver.findElement(By.id(id));
        element.clear();
        element.sendKeys(value);
    }
    
    @Test
    public void testAdd(){
        
        _driver.findElement(By.id("form:somar")).click();
        String text = _driver.findElement(By.id("form:resultado")).getText();
         assertThat(text,equalTo("10.0"));
    }
    
    @Test
    public void testSubtrair(){
        
        _driver.findElement(By.id("form:subtrair")).click();
        String text = _driver.findElement(By.id("form:resultado")).getText();
         assertThat(text,equalTo("2.0"));
    }
    
    @Test
    public void testMultiplicar(){
        
        _driver.findElement(By.id("form:multiplicar")).click();
        String text = _driver.findElement(By.id("form:resultado")).getText();
         assertThat(text,equalTo("24.0"));
    }
    
    @Test
    public void testDividir(){
        
        _driver.findElement(By.id("form:dividir")).click();
        String text = _driver.findElement(By.id("form:resultado")).getText();
         assertThat(text,equalTo("1.5"));
    }
    
    
}
