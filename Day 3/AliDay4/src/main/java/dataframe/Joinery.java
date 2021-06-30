package dataframe;

import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import joinery.DataFrame;

/**
 * Hello world!
 *
 */
public class Joinery 
{
    public static void joinery(){
        try {
            DataFrame <Object> df = DataFrame.readCsv("src\\data\\vgsales.csv");
            DataFrame <Object> df2 = df.retain("Rank","Platform","Year","EU_Sales");
            System.out.println(df.describe());
            System.out.println(df2.describe());
            DataFrame<Object> df3 = df2.merge(df);
            System.out.println(df3.describe());
            df = df.drop("Other_Sales");
            List<Object> Publisher =  df.col("Publisher");
            df2.add(Publisher);
            DataFrame<Object> df4 = df2.join(df);
            System.out.println(df4.describe());
            
        } catch (IOException ex) {
            Logger.getLogger(Joinery.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    public static void main( String[] args )
    {
        joinery();
    }
}
