/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simulador;

import java.util.concurrent.ThreadLocalRandom;

/**
 *
 * @author Edgar
 */
public class Test {
    
    public static void main(String[] args){
        int a = ThreadLocalRandom.current().nextInt(0, 100 + 1);
        System.out.println("a: " + a);
        
        int max=5, min=3;
        double b = ThreadLocalRandom.current().nextDouble(max + 1 - min) + min;
        System.out.println("b: " + b);
    }
    
}
