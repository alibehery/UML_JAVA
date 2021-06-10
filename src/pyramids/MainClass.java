/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pyramids;

import PyramidPack.Pyramid;
import PyramidPack.PyramidCSVDAO;
import java.util.List;

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
    }
    
}
