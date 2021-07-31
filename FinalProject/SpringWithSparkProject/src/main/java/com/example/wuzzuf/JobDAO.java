package com.example.wuzzuf;

// Data Access Object Pattern
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;


public class JobDAO {

    List<Job> job = new ArrayList<>();

    public List<Job> readjobFromCsv(String rawFileName){

        File jobFile = new File(rawFileName);
        List<String> lines = new ArrayList<>();

        try{

            lines = Files.readAllLines(jobFile.toPath());

        }catch(IOException e){

            System.out.println("An Issue Has Been Happend"+e);
        }

        for (int lineIdx = 1; lineIdx < lines.size(); lineIdx++){

            String line = lines.get(lineIdx);
            String[] fields = line.split(",");

            for(int fieldIndex = 0; fieldIndex < fields.length; fieldIndex++){

                fields[fieldIndex] = fields[fieldIndex].trim();

            }

            Job jobs = new Job(fields[0],fields[1],fields[2],fields[3],fields[4],fields[5],fields[6],fields[7]);
            job.add(jobs);
        }

        System.out.println("Total Number of Job is : "+job.size());
        return job;

    }

}