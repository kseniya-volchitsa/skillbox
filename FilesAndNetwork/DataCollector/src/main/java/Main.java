import core.Station;
import org.json.JSONArray;

import java.io.File;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.concurrent.ForkJoinPool;

public class Main {
    public static void main(String[] args) throws Exception {

        ParseHtml parseHtml = new ParseHtml("https://skillbox-java.github.io/");


        String folderPath = "C:/Users/пользователь/IdeaProjects/FilesAndNetwork/filesandnetwork/DataCollector/data/zip";
        ParseJSON parseJSON = new ParseJSON(folderPath);
        ParseCSV parseCSV = new ParseCSV(folderPath);
        CreateJsonStationsResult result = new CreateJsonStationsResult(parseHtml.stations, parseJSON.parseJSONStations, parseCSV.parseCsvStations);
        result.createJson();
    }
}