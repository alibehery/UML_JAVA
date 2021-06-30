package titanic.xchart;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.awt.Color;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.knowm.xchart.CategoryChart;
import org.knowm.xchart.CategoryChartBuilder;
import org.knowm.xchart.PieChart;
import org.knowm.xchart.PieChartBuilder;
import org.knowm.xchart.SwingWrapper;
import org.knowm.xchart.style.Styler;

/**
 * Hello world!
 *
 */
public class XchartTask 
{
    public static void main( String[] args )
    {
        XchartTask xchart = new XchartTask();
        List<Passenger> myPassengersList = xchart.getPassengersFromJsonFile();
        xchart.graphPassengerClass(myPassengersList);
        xchart.graphPassengerAges(myPassengersList);
    }
    
    public  List<Passenger> getPassengersFromJsonFile() {
        List<Passenger> PassengersList = new ArrayList<Passenger> ();
        ObjectMapper objectMapper = new ObjectMapper ();
        objectMapper.configure (DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        try (InputStream input = new FileInputStream ("C:\\Users\\Dell\\Documents\\NetBeansProjects\\xchart\\titanic_csv.json")) {
//Read JSON file
        PassengersList = objectMapper.readValue (input, new TypeReference<List<Passenger>> () {
            });
        } catch (FileNotFoundException e) {
        e.printStackTrace ();
        } catch (IOException e) {
        e.printStackTrace ();
        }
        return PassengersList;
        }
    public void graphPassengerClass(List<Passenger> passengerList) {
        //filter to get a map of passenger class and total number of passengers in each class
        Map<String, Long> result =
        passengerList.stream ().collect (
        Collectors.groupingBy (
        Passenger::getPclass, Collectors.counting ()
        )
        );
        // Create Chart
        PieChart chart = new PieChartBuilder ().width (800).height (600).title (getClass ().getSimpleName ()).build ();
        // Customize Chart
        Color[] sliceColors = new Color[]{new Color (180, 68, 50), new Color (130, 105, 120), new Color (80, 143, 160)};
        chart.getStyler ().setSeriesColors (sliceColors);
        // Series
        chart.addSeries ("First Class", result.get ("1"));
        chart.addSeries ("Second Class", result.get ("2"));
        chart.addSeries ("Third Class", result.get ("3"));
        // Show it
        new SwingWrapper (chart).displayChart ();
        }
    public void graphPassengerAges(List<Passenger> passengerList) {
        //filter to get an array of passenger ages
        List<Float> pAges = passengerList.stream ().map (Passenger::getAge).limit (5).collect (Collectors.toList ());
        List<String> pNames = passengerList.stream ().map (Passenger::getName).limit (5).collect (Collectors.toList ());
        String[] names = new String[pNames.size ()];
        Float ages[] = new Float[pAges.size ()];
        ages = pAges.toArray (ages);
        names = pNames.toArray (names);
        //Using XCart to graph the Ages
        // Create Chart
        CategoryChart chart = new CategoryChartBuilder ().width (1024).height (768).title ("Age Histogram").xAxisTitle ("Names").yAxisTitle
        ("Age").build ();
        // Customize Chart
        chart.getStyler ().setLegendPosition (Styler.LegendPosition.InsideNW);
        chart.getStyler ().setHasAnnotations (true);
        chart.getStyler ().setStacked (true);
        // Series
        chart.addSeries ("Passenger's Ages", pNames, pAges);
        // Show it
        new SwingWrapper (chart).displayChart ();
        }
    }
