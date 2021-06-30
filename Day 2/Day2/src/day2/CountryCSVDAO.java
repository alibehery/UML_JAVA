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
public class CountryCSVDAO {
    Country createCountry(String [] fields){

        String country = fields[0];
        String code = fields[1];

        return new Country(country, code);
        
    }
    List<Country> readCountriesFromCSV(String fileName){
       List<Country> listOfCountries = new ArrayList<Country>();
       File countriesFile = new File(fileName);
       List<String> lines = new ArrayList<String>();
        try {
            lines = Files.readAllLines(countriesFile.toPath());
        } catch (IOException ex) {
            System.out.println("Can't read File info duo to"+ ex);
        }
        for(int i = 1; i<lines.size(); i++){
            String line = lines.get(i);
            String [] fields =line.split(",");
            for(int y = 0; y<fields.length; y++){
                fields[y] = fields[y].trim();
            }
            listOfCountries.add(createCountry(fields));
            
        }
       
        return listOfCountries;   
    }
    
}
