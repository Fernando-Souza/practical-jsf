package com.book;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;
import java.util.logging.Logger;

@Named
@RequestScoped
public class Calculadora {
    
    private double param1;
    private double param2;
    private double result;
    
    private static final Logger LOGGER = Logger.getLogger("Calculadora");
    
    public Calculadora(){
        LOGGER.log(java.util.logging.Level.INFO,"ctor CALCULADORA!!!!");
    }

    public double getParam1() {
        return param1;
    }

    public void setParam1(double param1) {
        this.param1 = param1;
    }

    public double getParam2() {
        return param2;
    }

    public void setParam2(double param2) {
        this.param2 = param2;
    }

    public double getResult() {
        return result;
    }

    public void setResult(double result) {
        this.result = result;
    }
    
    public String adicionar(){
     
        result= param1+param2;
        return "";
    }
    
    public String subtrair(){
        
        result = param1 - param2;
        return "";
    }
    
    public String multiplicar(){
        
        result = param1 * param2;
        return "";
    }
    
    public String dividir(){
        try{
            result = param1/param2;
        } catch(Exception e){
            
            System.out.println(e.getMessage());
        }
        
        return "";
    } 
    
    
}