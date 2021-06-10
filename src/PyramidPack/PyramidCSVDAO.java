package PyramidPack;


import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;




/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Dell
 */
public class PyramidCSVDAO {
    
    public Pyramid createPyramid(String [] fields){
        String pharoah = fields[0];
        String modern_name = fields[2];
        String site = fields[4];
        double height;

        if (fields[7] == null || fields[7].isEmpty() || fields[7] == " " ){
            height = 0;
        }else{
            height = Double.parseDouble(fields[7]);
        }


        return new Pyramid(pharoah,modern_name,site,height);
        
    }
    
    
    
    public List<Pyramid> readPyramidsFromCSV(String fileName){
        List<Pyramid> listOfPyramids = new ArrayList<Pyramid>();
        File FilePyramids = new File(fileName);
        
        List<String> lines = new ArrayList<String>();
        try {
            lines = Files.readAllLines(FilePyramids.toPath());
        } catch (IOException ex) {
            System.out.println("Can't read File info duo to"+ ex);    
        }
        
        for (int i=1; i< lines.size(); i++){
            String line = lines.get(i);
            String [] fields = line.split(",");
            
            for(int y=0; y< fields.length; y++){
                fields[y] = fields[y].trim();   
            }
            listOfPyramids.add(createPyramid(fields));
               }
        return listOfPyramids;
             
    }   
    
}
