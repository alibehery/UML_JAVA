/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pyramids;

import PyramidPack.Pyramid;
import PyramidPack.PyramidCSVDAO;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author Dell
 */
public class MainClass {
    public static void main(String[] args){
        PyramidCSVDAO pDAO = new PyramidCSVDAO();
        List<Pyramid> pyramids = pDAO.readPyramidsFromCSV("C:\\Users\\Dell\\Downloads\\pyramids.csv");
        int i = 0;
        for(Pyramid p:pyramids){
            System.out.println("#"+(i++)+p);
        }
        List<Pyramid> pyramidsList = new ArrayList<>();
        for (Pyramid pyramid : pyramids) {
            if (pyramid.getHeight() ==0){
                continue;
            }
            else {
                pyramidsList.add(pyramid);
            }
            
        }
        
        
        //Lab Exercise 4
        
        
        List<Double> sortedHeight = pyramidsList.stream()
                .map(Pyramid::getHeight)
                .sorted()
                .collect(Collectors.toList());
        System.out.println(sortedHeight.size());
        double median, lowerQuartile, upperQuartile;

        if (sortedHeight.size() % 2 == 0) {
            int x = sortedHeight.size() / 2;
            median = (sortedHeight.get(x)+sortedHeight.get(x+1))/2;
            System.out.println("median is "+median);
            if(x % 2 == 0){
                int y = x/2;
                lowerQuartile = (sortedHeight.get(y)+sortedHeight.get(y+1))/2;
                upperQuartile = (sortedHeight.get(x+y)+sortedHeight.get(x+y+1))/2;
            }
            else{
                int y = (int) (x/2 + 0.5);
                lowerQuartile = sortedHeight.get(y);
                upperQuartile = sortedHeight.get(x+y);
            }
            System.out.println("lowerQuartile is "+lowerQuartile);
            System.out.println("upperQuartile is "+upperQuartile);
        }
        else {
            int x = (int) ((sortedHeight.size() / 2)+.5);
            median = sortedHeight.get(x-1);
            System.out.println("median is "+median);
            int y = (x-1)/2;
            lowerQuartile = (sortedHeight.get(y)+sortedHeight.get(y+1))/2;
            upperQuartile = (sortedHeight.get(x+y)+sortedHeight.get(x+y+1))/2;
            System.out.println("lowerQuartile is "+lowerQuartile);
            System.out.println("upperQuartile is "+upperQuartile);            
        }
 
    }
    
}
