# Analytics
## Introduction
This a take home exercise project. The objective of the exercise is to develop a command line tool that takes two files as 
arguments on the command line (the SalesData.csv file and ProductData.json file) 
and prints to standard out the five product types with the best peak / non-peak sales ratio.

## How the tool works

The tool is developed with Java and packaged as a Jar file. It accepts 2 parameters indicating which the path to the files to 
process i.e. path to SalesData.csv and ProductData.json respectively, if the two parms are not provided the tool will use the local cached files. 
To run it you will have to follow below instructions
  <ol>
    <li> if you don't have java development tool kit install jdk </li>
    <li> if you don't have maven install maven</li>
    <li> download/clone this projoct code</li>
    <li> navigate to the directory containing this project</li>
    <li> run this command <b>mvn clean compile assembly:single</b></li>
    <li> after the build is success run this command <b>java -jar target/datanalytics-0.0.1-SNAPSHOT-jar-with-dependencies.jar</b></li>
    <li> a list of the five product types with the best peak / non-peak sales ratio will be printed</li>
  </ol>

## Things i should have done if i had more time

Below are the things, that i think i should have done on this project:
<ul>
  <li> Using concurrence to load and read data Synchronously</li>
  <li> Store data in a database</li>
  <li> Use Spring boot so that i can leverage its cool features like dependencies injection via autowiring, and database access</li>
  <li> Build cool User interface that can facilitate to filter by dates, add number of items to return, show statistical graphs..</li>
  <li> Write good unit tests</li>
</ul>
