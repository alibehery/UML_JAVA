package dataframe;

import dataframe.Person;
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Dell
 */
public class CSV {
    
    public static List<Person> readCSV() {
    List<Person> result = new ArrayList<> ();
    Path csvFile = Paths.get("src\\data\\titanic.csv");
     try (BufferedReader reader = Files.newBufferedReader(csvFile, StandardCharsets.UTF_8)) {
    CSVFormat csv = CSVFormat.RFC4180.withHeader();
    try (CSVParser parser = csv.parse(reader)) {
    Iterator<CSVRecord> it = parser.iterator();
    it.forEachRemaining(rec -> {
    String pclass = rec.get("pclass");
    String survived = rec.get("survived");
    String name = rec.get("name");
    String age = rec.get("age");
    String sibsp = rec.get("sibsp");
    String parch = rec.get("parch");
    String ticket = rec.get("ticket");
    String fare = rec.get("fare");
    String cabin = rec.get("cabin");
    String embarked = rec.get("embarked");
    String boat = rec.get("boat");
    String body = rec.get("body");
    String home_des = rec.get("home.dest");

Person person = new Person(pclass,survived,name,age,sibsp,parch,ticket,fare,cabin,embarked,boat,body,home_des) ;
result.add(person);
});

} }     catch (IOException ex) {
            System.err.println("file not found"+ ex);
        }
return result;
}
    public static void main(String[] args) throws IOException {
        
        List<Person> readCSV;
        readCSV = readCSV();
        
        System.out.println(
       readCSV.stream().map(Person::getAge).collect(Collectors.toList()));


}

    }

