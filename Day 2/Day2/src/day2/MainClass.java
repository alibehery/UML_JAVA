/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package day2;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import static java.util.stream.Collectors.toList;

/**
 *
 * @author Dell
 */
public class MainClass {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        
        //////////////////////////////////////////////////////
        //Lab 1
        /* Develop and application that reads two files for cities and countries 
            and store each in a List.*/
        CityCSVDAO cityDAO = new CityCSVDAO();
        List<City> citiesList = cityDAO.readCitiesFromCSV("C:\\Users\\Dell\\Downloads\\cities.csv");
//        System.out.println(citiesList);

        CountryCSVDAO countryDAO = new CountryCSVDAO();
        List<Country> countriesList = countryDAO.readCountriesFromCSV("C:\\Users\\Dell\\Downloads\\countries.csv");
//        System.out.println(countriesList); 

        /*Create a map that uses the country code as keys and a list of cities as 
            the value for each country. */

        Map<String,List<City>> country = new HashMap<>();
        for(int i = 0; i<citiesList.size(); i++){
            City city = citiesList.get(i);
            
            if(country.get(city.getCountryId())== null){
                List<City> l = new ArrayList<>();
                l.add(city);
                country.put(city.getCountryId(), l);
            }
            else{
                country.get(city.getCountryId()).add(city);
            }
        }
//        country.forEach((k,v)->System.out.println("country code: "+k+"  city List:  "+v));
        


           /*For a given country code sort the cities according to the population*/
        country.forEach((k,v)-> {
            List<City> sortedList = v.stream()
                                        .sorted(Comparator.comparing(City::getPopulation))
                                            .collect(Collectors.toList());
            country.put(k , sortedList);});


///////////////////////////////////////////////////////////////
//Lab 2
        
        
        String string1 = "hello";
        String string2 = "Welcome23";
        
        StringUtils y = new StringUtils();
        
        System.out.println(y.betterString(string1,string2,(s1,s2)->s1.length()>s2.length()));
        
        System.out.println(y.checkAplphapet(string1,(s)->{
            for(int i = 0; i<s.length();i++){
                if(!Character.isLetter(s.charAt(i))){
                    return false;
                }
            }
            return true;
        }));
        
        System.out.println(y.checkAplphapet(string2,(s)->{
            for(int i = 0; i<s.length();i++){
                if(!Character.isLetter(s.charAt(i))){
                    return false;
                }
            }
            return true;
        }));

/////////////////////////////////////////////////
//Lab 3

//         Highest population city of each country

        Map<String,List<City>> popMap = citiesList.stream().collect(Collectors.groupingBy(City::getCountryId));
        popMap.forEach((k,v)->v.stream()
                    .map(City::getPopulation)
                        .sorted().collect(Collectors.toList()));

        popMap.forEach((k,v)->
        System.out.println("Country code:"+k+"  highest population"+
                v.stream().max(Comparator.comparing(City::getPopulation))
        ));
        
        List<Optional<Double>> highestPopulationPerCountry = popMap.values().stream()
                .map(x -> x.stream().map(City::getPopulation).max(Double::compare))
                .collect( Collectors.toList());

            
            
        // Highest population capital
        
        Map<String,List<City>> capitalMap = citiesList.stream().collect(Collectors.groupingBy(City::getCapital));
        capitalMap.forEach((k,v)->
        System.out.println("Capital type:"+k+"  highest population"+
                v.stream().max(Comparator.comparing(City::getPopulation))
        ));
        
        List<Optional<Double>> HighestPopulationCapital = capitalMap.values().stream()
                .map(x -> x.stream().map(City::getPopulation).max(Double::compare))
                .collect( Collectors.toList());       

    }
    
    
}
