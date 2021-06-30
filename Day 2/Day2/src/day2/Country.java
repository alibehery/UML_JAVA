/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package day2;

/**
 *
 * @author Dell
 */
public class Country {

    public Country(String country, String code) {
        this.country = country;
        this.code = code;
    }

    public String getCountry() {
        return country;
    }

    public String getCode() {
        return code;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return "Country{" + "country=" + country + ", code=" + code + '}';
    }
    private String country;
    private String code;
            
    
}
