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
public class City {

    public City(int cityId, String city, String countryId, String capital, double population) {
        this.cityId = cityId;
        this.city = city;
        this.countryId = countryId;
        this.capital = capital;
        this.population = population;
    }

    public int getCityId() {
        return cityId;
    }

    public String getCity() {
        return city;
    }

    public String getCountryId() {
        return countryId;
    }

    public String getCapital() {
        return capital;
    }

    public double getPopulation() {
        return population;
    }

    public void setCityId(int cityId) {
        this.cityId = cityId;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setCountryId(String countryId) {
        this.countryId = countryId;
    }

    public void setCapital(String capital) {
        this.capital = capital;
    }

    public void setPopulation(double population) {
        this.population = population;
    }

    @Override
    public String toString() {
        return "City{" + "cityId=" + cityId + ", city=" + city + ", countryId=" + countryId + ", capital=" + capital + ", population=" + population + '}';
    }


    
    private int cityId;
    private String city;
    private String countryId;
    private String capital;
    private double population;
    
    
}
