package PyramidPack;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Dell
 */
public class Pyramid {

    public Pyramid(String pharoah,String modern_name,String site,double height) {
        this.height=height;
        this.pharoah=pharoah;
        this.modern_name=modern_name;
        this.height = height;
          
        
    }
    private String pharoah;

    private String modern_name;

    private String site;

    private double height;

    /**
     * Get the value of height
     *
     * @return the value of height
     */
    public double getHeight() {
        return height;
    }

    /**
     * Set the value of height
     *
     * @param height new value of height
     */
    public void setHeight(double height) {
        this.height = height;
    }

    /**
     * Get the value of site
     *
     * @return the value of site
     */
    public String getSite() {
        return site;
    }

    /**
     * Set the value of site
     *
     * @param site new value of site
     */
    public void setSite(String site) {
        this.site = site;
    }

    /**
     * Get the value of modern_name
     *
     * @return the value of modern_name
     */
    public String getModern_name() {
        return modern_name;
    }

    /**
     * Set the value of modern_name
     *
     * @param modern_name new value of modern_name
     */
    public void setModern_name(String modern_name) {
        this.modern_name = modern_name;
    }

    /**
     * Get the value of pharoah
     *
     * @return the value of pharoah
     */
    public String getPharoah() {
        return pharoah;
    }

    /**
     * Set the value of pharoah
     *
     * @param pharoah new value of pharoah
     */
    public void setPharoah(String pharoah) {
        this.pharoah = pharoah;
    }

    @Override
    public String toString() {
        return "Pyramid{" + "pharoah=" + pharoah + ", modern_name=" + modern_name + ", site=" + site + ", height=" + height + '}';
    }

    
    
}
