package com.example.wuzzuf;

import org.apache.commons.io.IOUtils;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

@RestController
public class Controller {



    WuzzufDataset mydata = new WuzzufDataset();

    //    1. Read data set and convert it to dataframe or Spark RDD
    //    and display some from it.

    @RequestMapping(value = "/showCleanData"  , produces     =  MediaType.TEXT_PLAIN_VALUE)
    public String showData() {
        return mydata.head(10);
    }

//2. Display structure and summary of the data.
    @RequestMapping(value = "/structure" , produces     =  MediaType.TEXT_PLAIN_VALUE )
    public String getStructure(){
        return mydata.getStructure();
    }
    @RequestMapping(value = "/summary" , produces     =  MediaType.TEXT_PLAIN_VALUE )
    public String Summary(){
        return mydata.getSummary();
    }

//3. Clean the data (null, duplications)
    // included in the initialization

//4. Count the jobs for each company and display that in order
//            (What are the most demanding companies for jobs?)

    @RequestMapping(value = "/mostDemandingCompanies", produces     =  MediaType.TEXT_PLAIN_VALUE )
    public String compy() throws IOException {
        return mydata.mostDemandingCompanies(10);
    }

//5. Show step 4 in a pie chart
@RequestMapping(value = "/JobsPerCompanyPieChart", method = RequestMethod.GET,
        produces = MediaType.IMAGE_JPEG_VALUE)
public @ResponseBody byte[] getCompaniesPie() throws IOException {

    Path path  = Paths.get(mydata.plotCompanyPieChart(5));
    if (Files.exists(path) && !Files.isDirectory(path)) {
        System.out.println("exists!");

        InputStream in = Files.newInputStream(path, StandardOpenOption.READ);
        return IOUtils.toByteArray(in);
    }
    InputStream in = Files.newInputStream(path, StandardOpenOption.READ);
    return IOUtils.toByteArray(in);
}


//6. Find out What are it the most popular job titles?
@RequestMapping(value = "/mostpopularjobtitles", produces     =  MediaType.TEXT_PLAIN_VALUE )
public String PopularTitles() throws IOException {
    return mydata.mostPopularJobs(10);
}
//            7. Show step 6 in bar chart
@RequestMapping(value = "/JobsTitlesBarChart", method = RequestMethod.GET,
        produces = MediaType.IMAGE_JPEG_VALUE)
public @ResponseBody byte[] getTitleBar() throws IOException {

    Path path  = Paths.get(mydata.PlotTitleForCompany(10));
    if (Files.exists(path) && !Files.isDirectory(path)) {
        System.out.println("exists!");

        InputStream in = Files.newInputStream(path, StandardOpenOption.READ);
        return IOUtils.toByteArray(in);
    }
    InputStream in = Files.newInputStream(path, StandardOpenOption.READ);
    return IOUtils.toByteArray(in);
}



//8. Find out the most popular areas?
@RequestMapping(value = "/mostpopularareas", produces     =  MediaType.TEXT_PLAIN_VALUE )
public String PopularAreas() throws IOException {
    return mydata.mostPopularareas(10);
}
//            9. Show step 8 in bar chart
@RequestMapping(value = "/mostPopularAreasBarChart", method = RequestMethod.GET,
        produces = MediaType.IMAGE_JPEG_VALUE)
public @ResponseBody byte[] getAreasBar() throws IOException {

    Path path  = Paths.get(mydata.plotLocationBarChart(10));
    if (Files.exists(path) && !Files.isDirectory(path)) {
        System.out.println("exists!");

        InputStream in = Files.newInputStream(path, StandardOpenOption.READ);
        return IOUtils.toByteArray(in);
    }
    InputStream in = Files.newInputStream(path, StandardOpenOption.READ);
    return IOUtils.toByteArray(in);
}
//
//
//10. Print skills one by one and how many each repeated and
//    order the output to find out the most important skills
//    required?
//
    @RequestMapping(value = "/mostImportantSkills", produces     =  MediaType.TEXT_PLAIN_VALUE )
    public String Skills() throws IOException {

        return mydata.getMostImportantSkills(30);
    }

    @RequestMapping(value = "/factorizeYearsExp", produces     =  MediaType.TEXT_PLAIN_VALUE )
    public String Factorizedyears() throws IOException {

        return mydata.getFactorizedYearsOfExp(10);
    }

    @RequestMapping(value = "/K-means", produces     =  MediaType.TEXT_PLAIN_VALUE )
    public String Kmeans() throws IOException {

        return mydata.kMeansAlgorithm();
    }



}
