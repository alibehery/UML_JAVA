/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package day2;

import java.util.function.BiPredicate;
import java.util.function.Predicate;

/**
 *
 * @author Dell
 */
public class StringUtils {

    public String betterString(String s1,String s2,BiPredicate<String,String> bp){
        if (bp.test( s1,s2)){
            return s1;}
        else{
            return s2;}
        
        
    }
    public boolean checkAplphapet(String s, Predicate<String> p){
        return p.test(s);
        
    }
    
}
