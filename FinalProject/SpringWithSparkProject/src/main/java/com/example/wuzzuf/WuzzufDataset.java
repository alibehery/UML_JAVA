/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.wuzzuf;



/**
 *
 * @author Ali Behery
 */


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.util.Pair;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.function.FilterFunction;
import org.apache.spark.ml.clustering.KMeans;
import org.apache.spark.ml.clustering.KMeansModel;
import org.apache.spark.ml.feature.StringIndexer;
import org.apache.spark.ml.feature.VectorAssembler;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Encoders;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;
import org.apache.spark.sql.types.StructType;
import org.json.simple.JSONObject;
import org.knowm.xchart.*;
import org.knowm.xchart.style.Styler;

import scala.collection.JavaConverters;


import java.awt.*;
import java.util.List;


import java.util.*;
import java.io.IOException;

import java.util.Arrays;

import java.util.stream.Collectors;

import static org.apache.spark.sql.functions.*;
import static org.apache.spark.sql.functions.regexp_replace;

//@SuppressWarnings("unchecked")
public class WuzzufDataset {
    private Dataset<Row> jobsDF;
    private SparkSession sparkSession;
    private Dataset<Row> Jobs_RAW;
    private List<Job> jobslist;
    public WuzzufDataset() {
        Logger.getLogger("org").setLevel(Level.ERROR);
        sparkSession = SparkSession.builder()
                .appName("wuzzuf jobs")
                .master("local[3]")
                .config("spark.some.config.option", "some-value")
                .getOrCreate();

        jobsDF = sparkSession.read().option("header", true).csv("src\\main\\resources\\Wuzzuf_Jobs.csv");
        jobsDF = jobsDF.na().drop("any").distinct();
        jobsDF = jobsDF.dropDuplicates();
                //.filter((FilterFunction<Row>) row -> !row.get(5).equals("null Yrs of Exp"));

        //SQL
        jobsDF.createOrReplaceTempView ("Jobs_RAW");
        Jobs_RAW = sparkSession
                .sql ("SELECT CAST(Title as string) Title, CAST(Company as string) Company," +
                        "CAST(Location as string) Location, CAST(Type as string) Type," +
                        "CAST(Level as string) Level, CAST(YearsExp as string) YearsExp," +
                        "CAST(Country as string) Country, CAST(Skills as string) Skills" +
                        " FROM Jobs_RAW");
        JobDAO JobDAO = new JobDAO();
        jobslist = JobDAO.readjobFromCsv("src/main/resources/Wuzzuf_Jobs.csv");
    }


//print the head of dataset
    public String head(int n)
    {
        String headValues = jobsDF.showString(10,40,false);
        return headValues;
        // showstring
    }


    // 2. Display structure and summary of the data.
    public String getStructure()
    {
        StructType structure = jobsDF.schema();
        return structure.prettyJson();
    }


    public String getSummary()
    {

        return jobsDF.summary().showString(10,40,false);

    }

//4. Count the jobs for each company and display that in order
//            (What are the most demanding companies for jobs?

    public String mostDemandingCompanies(int n){
        Jobs_RAW.createOrReplaceTempView ("Jobs_Count");
        String companiesJobcount = sparkSession.sql ("SELECT COUNT(Title), Company FROM Jobs_Count GROUP BY Company " +
                "ORDER BY COUNT(Title) DESC" ).showString(n,40,false);
        return companiesJobcount;
    }
    //5. Show step 4 in a pie chart
//    Pair<Integer, String>
//    public String plotCompanyPieChart(int n) throws IOException
    public String plotCompanyPieChart(int n) throws IOException
    {

    //filter to get a map of passenger class and total number of passengers in each class
        Map<String, Long> result =
                jobslist.stream ().collect (
                    Collectors.groupingBy (
                            Job::getCompany, Collectors.counting ()
                    )
            );
        LinkedHashMap<String, Long> reverseSortedMap = new LinkedHashMap<>();

        //Use Comparator.reverseOrder() for reverse ordering
                result.entrySet()
                        .stream()
                        .sorted(java.util.Map.Entry.comparingByValue(Comparator.reverseOrder())).limit (n)
                        .forEachOrdered(x -> reverseSortedMap.put(x.getKey(), x.getValue()));

        // pie chart
        PieChart chart = new PieChartBuilder ().width (1400).height (700).title ("Companies Pie-Chart").build ();
        chart.getStyler().setLegendPosition(Styler.LegendPosition.OutsideS);
        chart.getStyler().setLegendLayout(Styler.LegendLayout.Horizontal);
        // Customize Chart
        Color[] sliceColors = new Color[]{new Color (11, 125, 203), new Color (68, 66, 67),
                new Color (10, 177, 5),new Color (63, 173, 239),
                new Color (231, 5, 32)};
        chart.getStyler ().setSeriesColors (sliceColors);
        // Series
        reverseSortedMap.forEach((k,v) -> chart.addSeries (k, v));




        BitmapEncoder.saveBitmap(chart, "src/main/resources/company_pie_chart.JPG", BitmapEncoder.BitmapFormat.JPG);
        return  (  "src/main/resources/company_pie_chart.JPG");
    }


//    6. Find out What are it the most popular job titles?
    public String mostPopularJobs(int n){
        Jobs_RAW.createOrReplaceTempView ("Job_Titles_Count");
        String mostPopularJobscount = sparkSession.sql ("SELECT COUNT(Company), Title FROM Job_Titles_Count GROUP BY Title " +
                "ORDER BY COUNT(Company) DESC" ).showString(n,40,false);
        return mostPopularJobscount;


    }
//            7. Show step 6 in bar chart

    public String PlotTitleForCompany(int n) throws IOException
    {

        //filter to get a map of passenger class and total number of passengers in each class
        Map<String, Long> result =
                jobslist.stream ().collect (
                        Collectors.groupingBy (
                                Job::getTitle, Collectors.counting ()
                        )
                );
        LinkedHashMap<String, Long> reverseSortedMap = new LinkedHashMap<>();

//Use Comparator.reverseOrder() for reverse ordering
        result.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder())).limit (10)
                .forEachOrdered(x -> reverseSortedMap.put(x.getKey(), x.getValue()));

        System.out.println("Reverse Sorted Map   : " + reverseSortedMap);
        List<String> titles = reverseSortedMap.keySet().stream ().collect (Collectors.toList ());
        List<Long> counts = reverseSortedMap.values().stream ().collect (Collectors.toList ());

        // Create Chart
        CategoryChart chart = new CategoryChartBuilder().width (1024).height (768).title (" Job Titles Histogram").xAxisTitle ("Title").yAxisTitle
                ("Count").build ();
        // Customize Chart
        chart.getStyler ().setLegendPosition (Styler.LegendPosition.InsideNW);
        chart.getStyler().setLegendLayout(Styler.LegendLayout.Horizontal);
        chart.getStyler ().setHasAnnotations (true);
        chart.getStyler ().setStacked (true);
        // Series
        chart.addSeries ("Job Titles count", titles, counts);


        BitmapEncoder.saveBitmap(chart, "src/main/resources/title_bar_chart.png", BitmapEncoder.BitmapFormat.PNG);
        return  (  "src/main/resources/title_bar_chart.png") ;
    }


//              8. Find out the most popular areas?
public String mostPopularareas(int n){
    Jobs_RAW.createOrReplaceTempView ("most_popular_area");
    String mostPopularAreascount = sparkSession.sql ("SELECT COUNT(Company), Location FROM most_popular_area GROUP BY Location " +
            "ORDER BY COUNT(Company) DESC" ).showString(n,40,false);
    return mostPopularAreascount;


}

//            9. Show step 8 in bar chart

    public String plotLocationBarChart(int n) throws IOException
    {

        //filter to get a map of passenger class and total number of passengers in each class
        Map<String, Long> result =
                jobslist.stream ().collect (
                        Collectors.groupingBy (
                                Job::getLocation, Collectors.counting ()
                        )
                );
        LinkedHashMap<String, Long> reverseSortedMap = new LinkedHashMap<>();

//Use Comparator.reverseOrder() for reverse ordering
        result.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder())).limit (10)
                .forEachOrdered(x -> reverseSortedMap.put(x.getKey(), x.getValue()));

        System.out.println("Reverse Sorted Map   : " + reverseSortedMap);
        List<String> locationList = reverseSortedMap.keySet().stream ().collect (Collectors.toList ());
        List<Long> counts = reverseSortedMap.values().stream ().collect (Collectors.toList ());
        String[] location = new String[locationList.size ()];
        Long [] count = new Long[counts.size ()];
        count = counts.toArray (count);
        location = locationList.toArray (location);
        // Create Chart
        CategoryChart chart = new CategoryChartBuilder().width (1024).height (768).title (" Job Titles Histogram").xAxisTitle ("Title").yAxisTitle
                ("Count").build ();
        // Customize Chart
        chart.getStyler ().setLegendPosition (Styler.LegendPosition.InsideNW);
        chart.getStyler().setLegendLayout(Styler.LegendLayout.Horizontal);
        chart.getStyler ().setHasAnnotations (true);
        chart.getStyler ().setStacked (true);
        // Series
        chart.addSeries ("Job Titles count", locationList, counts);

        BitmapEncoder.saveBitmap(chart, "src/main/resources/Areas_Bar_chart.png", BitmapEncoder.BitmapFormat.PNG);
        return ( "src/main/resources/Areas_Bar_chart.png");
    }


//    10. Print skills one by one and how many each repeated and  order the output to find out the most important skills
//    required?



        public String  getMostImportantSkills(int n ) throws JsonProcessingException {

        JavaRDD<String> skillByRow = jobsDF.select("Skills").as(Encoders.STRING()).javaRDD();

        JavaRDD<String> skills = skillByRow.flatMap(skill ->
            Arrays.asList(skill.trim()
                    .toLowerCase()
                    .trim()
                    .split(","))
                    .iterator());
            skills = skills.flatMap(skill ->
                    Arrays.asList(skill.trim()
                            .toLowerCase()
                            .trim()
                            .split(","))
                            .iterator());

        List<Map.Entry> skillsCounts = skills
                .countByValue()
                .entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue())
                .collect(Collectors.toList());
            Collections.reverse(skillsCounts);
            int size = skillsCounts.size();
            String Wen = "";

            for(int i = 0; i <n;i++)
            {
               Wen += ("#" + (i) + " - " + skillsCounts.get(i).getKey() + " : " + skillsCounts.get(i).getValue()) + "\n";
            }




        return Wen;
    }


//    11. Factorize the YearsExp feature and convert it to numbers in new col. (Bonus)

    public String getFactorizedYearsOfExp(int n)
    {
        StringIndexer indexer = new StringIndexer().setInputCol("YearsExp").setOutputCol("Factorized YearsExp");
        jobsDF = indexer.fit(jobsDF).transform(jobsDF);
        return jobsDF.showString(n,40,false);
    }



//    12. Apply K-means for job title and companies (Bonus)


    public String kMeansAlgorithm()
    {
        Dataset<Row> dataset = jobsDF.as("data");
        String[] cols = {"Title", "Company"};
        String[] factorizedCols = {"TitleFactorized", "CompanyFactorized"};

        for(int i = 0; i < cols.length; i++)
        {
            StringIndexer indexer = new StringIndexer();
            indexer.setInputCol(cols[i]).setOutputCol(factorizedCols[i]);
            dataset = indexer.fit(dataset).transform(dataset);
        }

        for(int i = 0; i < cols.length; i++)
            dataset = dataset.withColumn(factorizedCols[i], dataset.col(factorizedCols[i]).cast("double"));


        VectorAssembler vectorAssembler = new VectorAssembler();
        vectorAssembler.setInputCols(factorizedCols).setOutputCol("features");
        Dataset<Row> trainData = vectorAssembler.transform(dataset);

        KMeans kmeans = new KMeans().setK(3).setSeed(1L);
        kmeans.setFeaturesCol("features");
        KMeansModel model = kmeans.fit(trainData);

        return "Model Distance Measure: " + model.getDistanceMeasure()
                + "\nNumber of Features: " + model.numFeatures()
               +
                "\nNumber of iterations: " + model.getMaxIter()
               +
                "\nModel Centers:" + Arrays.toString(model.clusterCenters())
               ;
    }


}
