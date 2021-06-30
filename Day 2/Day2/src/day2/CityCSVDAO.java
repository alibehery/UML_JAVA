/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package day2;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Dell
 */
public class CityCSVDAO {  
   
    City createCity(String [] fields){
        int cityId;
        if (fields[0].isEmpty()){
        cityId = 0;
            }
        else {
            cityId = Integer.parseInt(fields[0]);
        }
        String city = fields[1];
        String countryId = fields[2];
        String capital = fields[3];
        double population;
        try{
        population = Double.parseDouble(fields[4]);
            }
        catch(Exception ex) {
            population=0;
        }
        return new City(cityId, city, countryId, capital, population);
        
    }
    List<City> readCitiesFromCSV(String fileName){
       List<City> listOfCities = new ArrayList<City>();
       File citiesFile = new File(fileName);
       List<String> lines = new ArrayList<String>();
        try {
            lines = Files.readAllLines(citiesFile.toPath());
        } catch (IOException ex) {
            System.out.println("Can't read File info duo to"+ ex);
        }
        for(int i = 1; i<lines.size(); i++){
            String line = lines.get(i);
            String [] fields =line.split(",");
            for(int y = 0; y<fields.length; y++){
                fields[y] = fields[y].trim();
            }
            listOfCities.add(createCity(fields));
            
        }
       
    return listOfCities;   
    }
    
}
