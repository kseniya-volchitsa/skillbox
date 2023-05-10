import core.Line;
import core.Station;
import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.concurrent.RecursiveTask;

public class ParseJSON {

   File file;
   ArrayList<File> jsonFiles;

   ArrayList<Station> parseJSONStations;

    public ArrayList<File> getJsonFiles() {
        return jsonFiles;
    }

    ParseJSON (String path){
       file = new File(path);
       jsonFiles = new ArrayList<>();
       parseJSONStations = new ArrayList<>();
       setJsonFiles(file);
   }

    public void setJsonFiles(File file) {


        File[] folderEntries = file.listFiles();
        for (File entry : folderEntries)
        {
            if (entry.isDirectory())
            {
                setJsonFiles(entry);
            }else if (getFileExtension(entry).equals("json")){
                jsonFiles.add(entry);
            } else {continue;}

        }

        for (File f : jsonFiles){
            createParseJSONStations(f.getPath());
        }
    }

    private static String getFileExtension(File file){
        String fileName = file.getName();
        if (fileName.lastIndexOf(".") != -1 && fileName.lastIndexOf(".") != 0)
            return fileName.substring(fileName.lastIndexOf(".")+1);
        else return "";
    }


    private void createParseJSONStations(String path)
    {
        try
        {
            JSONParser parser = new JSONParser();
            JSONArray jsonData = (JSONArray) parser.parse(getJsonFile(path));

            parseStations(jsonData);
        }
        catch(Exception ex) {
            ex.printStackTrace();
        }
    }
    private void parseStations(JSONArray linesArray)
    {
       for (Object lineObject : linesArray){
            JSONObject lineJsonObject = (JSONObject) lineObject;
            String name = lineJsonObject.get("station_name").toString().replaceAll("ё", "е");;
            String stringDepth = lineJsonObject.get("depth").toString().replaceAll(",",".");
            if (stringDepth.equals("?")){
                continue;
            } else {
                Double depth = Double.parseDouble(stringDepth);
                Station station = new Station(name, depth);
                parseJSONStations.add(station);
            }
        }
    }

    private static String getJsonFile(String dataFile)
    {
        StringBuilder builder = new StringBuilder();
        try {
            List<String> lines = Files.readAllLines(Paths.get(dataFile));
            lines.forEach(line -> builder.append(line));
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        return builder.toString();
    }

}
