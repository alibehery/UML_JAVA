/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.wuzzuf;

/**
 *
 * @author Ali
 */
// Job POJO class to represent entity Job
public class Job {


    private String title;
    private String company;
    private String location;
    private String type;
    private String level;
    private String yearsExp;
    private String country;
    private String skills;


    public Job(String title, String company, String location, String type, String level, String yearsExp, String country, String skills) {
        this.title = title;
        this.company = company;
        this.location = location;
        this.type = type;
        this.level = level;
        this.yearsExp = yearsExp;
        this.country = country;
        this.skills = skills;
    }


    // getter method for title
    public String getTitle() {
        return title;
    }

    // setter method for title
    public void setTitle(String title) {
        this.title = title;
    }

    // getter method for company
    public String getCompany() {
        return company;
    }

    // setter method for company
    public void setCompany(String company) {
        this.company = company;
    }

    // getter method for location
    public String getLocation() {
        return location;
    }

    // setter method for location
    public void setLocation(String location) {
        this.location = location;
    }

    // getter method for type
    public String getType() {
        return type;
    }

    // setter method for type
    public void setType(String type) {
        this.type = type;
    }

    // getter method for level
    public String getLevel() {
        return level;
    }

    // setter method for level
    public void setLevel(String level) {
        this.level = level;
    }

    // getter method for yearsExp
    public String getYearsExp() {
        return yearsExp;
    }

    // setter method for yearsExp
    public void setYearsExp(String yearsExp) {
        this.yearsExp = yearsExp;
    }

    // getter method for country
    public String getCountry() {
        return country;
    }

    // setter method for country
    public void setCountry(String country) {
        this.country = country;
    }

    // getter method for skills
    public String getSkills() {
        return skills;
    }

    // setter method for skills
    public void setSkills(String skills) {
        this.skills = skills;
    }


    @Override
    public String toString() {
        return "Job{" +
                "title=" + title +
                ", company=" + company +
                ", location=" + location +
                ", type=" + type +
                ", level=" + level +
                ", yearsExp=" + yearsExp +
                ", country=" + country +
                ", skills=" + skills + '}';
    }

}
