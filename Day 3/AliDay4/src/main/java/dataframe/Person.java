/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataframe;

/**
 *
 * @author Dell
 */
public class Person {
    private final String pclass;
    private final String survived;
    private final String name;
    private final String age;
    private final String sibsp;
    private final String parch;
    private final String ticket;
    private final String fare;    
    private final String cabin;
    private final String embarked;
    private final String boat;    
    private final String body;
    private final String home_des;     

    public Person(String pclass, String survived, String name, String age, String sibsp, String parch, String ticket, String fare, String cabin, String embarked, String boat, String body, String home_des) {
        this.pclass = pclass;
        this.survived = survived;
        this.name = name;
        this.age = age;
        this.sibsp = sibsp;
        this.parch = parch;
        this.ticket = ticket;
        this.fare = fare;
        this.cabin = cabin;
        this.embarked = embarked;
        this.boat = boat;
        this.body = body;
        this.home_des = home_des;
    }





    public String getPclass() {
        return pclass;
    }

    public String getSurvived() {
        return survived;
    }

    public String getName() {
        return name;
    }

    public String getAge() {
        return age;
    }

    public String getSibsp() {
        return sibsp;
    }

    public String getParch() {
        return parch;
    }

    public String getTicket() {
        return ticket;
    }

    public String getFare() {
        return fare;
    }

    public String getCabin() {
        return cabin;
    }

    public String getEmbarked() {
        return embarked;
    }

    public String getBody() {
        return body;
    }

    public String getHome_des() {
        return home_des;
    }

    public String getBoat() {
        return boat;
    }

    @Override
    public String toString() {
        return "Person{" + "pclass=" + pclass + ", survived=" + survived + ", name=" + name + ", age=" + age +'}';
    }
    
}
