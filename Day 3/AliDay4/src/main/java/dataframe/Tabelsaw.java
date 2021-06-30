/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataframe;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import tech.tablesaw.api.DoubleColumn;
import tech.tablesaw.api.StringColumn;
import tech.tablesaw.api.Table;
import tech.tablesaw.columns.Column;

/**
 *
 * @author Dell
 */
public class Tabelsaw {
    public static void tablesaw(){
        try {
            Table titanic = Table.read().csv("src\\data\\titanic.csv");
            
            System.out.println(titanic.structure());
            System.out.println(titanic.summary());
            int x = titanic.rowCount();
            double [] index = new double [x];
            for(int i = 0; i<x; i++){
                index[i] = i;
                
            }
            DoubleColumn theindex = DoubleColumn.create ("index", index);
            titanic = titanic.insertColumn(0,theindex);
            
            Table vgsales = Table.read().csv("src\\data\\vgsales.csv");
            int y = vgsales.rowCount();
            double [] index2 = new double [y];
            for(int i = 0; i<y; i++){
                index2[i] = i;
                
            }
            DoubleColumn index22 = DoubleColumn.create ("index", index2);
            vgsales = vgsales.insertColumn(0,index22);
            

            Table TablesJoined = titanic.joinOn("index").leftOuter(vgsales);
            System.out.println(TablesJoined.structure());
            
        } catch (IOException ex) {
            Logger.getLogger(Tabelsaw.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    } 
    
    public static void main(String[] args) {
        tablesaw();
    }
    
}
