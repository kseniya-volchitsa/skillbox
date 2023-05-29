import com.fasterxml.jackson.annotation.JsonValue;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.JSONPObject;
import core.Line;
import core.Station;
import org.json.JSONArray;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;


import javax.json.*;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.StringReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.*;

public class ParseHtml {


    ArrayList<Line> lines;
    Map<Station, Set<Station>> connections;
    ArrayList<Station> stations;

    JSONObject jsonHtml;

    public ParseHtml(String link) throws Exception {
        lines = downloadLines(link);
        jsonHtml = jsonFile3();
    }

    public ArrayList<Line> getLines() {
        return lines;
    }

    private ArrayList<Line> downloadLines(String link) throws Exception {
        Document doc = Jsoup.connect(link).get();

        stations = new ArrayList<>();
        ArrayList<Line> lines = new ArrayList<>();
        connections = new TreeMap<>();
        int count = 0;

        for (Element line : doc.select(".js-depend")) {
            String numberLine = line.attr("data-depend-set").substring(6);
            String nameLine = doc.getElementsByAttributeValue("data-line", numberLine).get(0).text();
            Line newLine = new Line(numberLine, nameLine);
            lines.add(newLine);

            for (Element station : line.select("p.single-station")) {
                String name = station.text().replaceAll("[0-9]*[.]{1}", "").trim().replaceAll("ё", "е");;
                Station newStation = new Station(name, newLine);
                newLine.addStation(newStation);
                stations.add(newStation);

                for (Element connection : station.select("span.t-icon-metroln")) {
                    if (connection.attributesSize() > 0) {
                        String connect = connection.attr("title");
                        int start = connect.indexOf("«") + 1;
                        int end = connect.lastIndexOf("»");
                        String lineNumber = connection.className().substring(18);
                        String connectStationName = connect.substring(start, end).replaceAll("ё", "е");;
                        Station connectionStation = new Station(connectStationName, new Line(lineNumber, ""));
                        newStation.addConnection(connectionStation);
                        if (connections.containsKey(newStation)) {
                            connections.get(newStation).add(connectionStation);
                            stations.get(count).addConnection(connectionStation);
                        } else {
                            Set<Station> set = new TreeSet<>();
                            connections.put(newStation, set);
                            set.add(connectionStation);
                        }
                    }
                }
                count++;
            }
        }
        return lines;
    }

    public static Line searchLineByNumber(String number, ArrayList<Line> lines) {
        for (Line line : lines) {
            if (line.getNumber().equals(number)) {
                return line;
            }
        }
        return null;
    }

    public JSONObject jsonFile3() throws Exception {

        JSONObject lineWithStation = new JSONObject();
        JSONObject linesList = new JSONObject();
        JSONArray connections = new JSONArray();

        String lineHar = "[";
        //JSONArray jsonArray = new JSONObject(jsonStr).getJSONArray("creators").getJSONArray(0);
        int countLines = 0;
        for (Line line : lines) {
            JSONArray stations = new JSONArray();

            lineHar += "{\"number\": \"" + line.getNumber() + "\", \"name\": \"" + line.getName() + "\"}";
            if (countLines < lines.size() - 1) {
                lineHar += ",";
            }
            for (Station station : line.getStations()) {
                stations.put(station.getName());
                if (!station.getConnection().isEmpty()) {
                    for (Station item : station.getConnection()) {
                        JSONObject connectionStation = new JSONObject();
                        JSONObject connection = new JSONObject();
                        JSONArray arrayConnection = new JSONArray();
                        connectionStation.put("line", station.getLine().getNumber());
                        connectionStation.put("station", station.getName());
                        connection.put("line", item.getLine().getNumber());
                        connection.put("station", item.getName());

                        arrayConnection.put(connectionStation);
                        arrayConnection.put(connection);
                        connections.put(arrayConnection);
                    }
                }
            }
            linesList.put(line.getNumber(), stations);
            countLines++;
        }
        lineHar += "]";

        System.out.println(lineHar);
        JSONArray linesHaracter = new JSONArray(lineHar);

        lineWithStation.put("stations", linesList);
        lineWithStation.put("connections", connections);
        lineWithStation.put("lines", linesHaracter);

        String path = "C:/Users/пользователь/IdeaProjects/FilesAndNetwork/filesandnetwork/DataCollector/data/resultLines.json";


        //  Files.write(Paths.get(path), lineWithStation.toString().getBytes(StandardCharsets.UTF_8));
        Map<String, Object> mapStations = linesList.toMap();


        List<String> stringList = new ArrayList<>();

        stringList.add("{");
        stringList.add("\t\"stations\": {");
        int countLine = 0;
        for (String key : mapStations.keySet()) {
            stringList.add("\t\t\"" + key + "\": [");
            String[] stations = stringToArray(mapStations.get(key).toString());
            for (int i = 0; i < stations.length; i++) {
                if (i < stations.length -1){
                    stringList.add("\t\t\t\"" + stations[i] +"\",");
                } else{
                    stringList.add("\t\t\t\"" + stations[i] +"\"");
                }
            }
            if (countLine < mapStations.keySet().size() - 1){
                stringList.add("\t\t],");
            } else{
                stringList.add("\t\t]");
            }
            countLine++;
            }
        stringList.add("\t},");

        stringList.add("\t\"connections\": [");

        List<Object> connect = connections.toList();
        for (int i = 0; i < connect.size(); i++){
            String[] res = stringToArrayConnect(connect.get(i).toString());
            stringList.add("\t\t[");
            stringList.add("\t\t\t{");
            stringList.add("\t\t\t\t\"" + res[0]+ "\",");
            stringList.add("\t\t\t\t\"" + res[1]+ "\"");
            stringList.add("\t\t\t},");
            stringList.add("\t\t\t{");
            stringList.add("\t\t\t\t\"" + res[2]+ "\",");
            stringList.add("\t\t\t\t\"" + res[3]+ "\"");
            stringList.add("\t\t\t}");
            if (i < connect.size()-1){
                stringList.add("\t\t],");
            } else{
                stringList.add("\t\t]");
            }
            }
        stringList.add("\t],");

        stringList.add("\t\"lines\": [");
        List<Object> lineList = linesHaracter.toList();
        for (int i = 0; i < lineList.size(); i++){
            stringList.add("\t\t{");
            String[] list = stringToArrayLines(lineList.get(i).toString());
            stringList.add("\t\t\t" + list[0]+ "\",");
            stringList.add("\t\t\t\"" + list[1]);
            if (i < lineList.size() - 1){
                stringList.add("\t\t},");
            } else {
                stringList.add("\t\t}");
            }
        }
        stringList.add("\t]");
        stringList.add("}");

        Files.write(Paths.get(path), stringList, StandardOpenOption.CREATE);


        return lineWithStation;
        }

    public String[] stringToArray(String string){
        String[] result = string.replaceAll("\\[", "").
                replaceAll("\\]", "").split(", ");

        return result;
    }

    public String[] stringToArrayConnect(String string){
        String[] result = string.replaceAll("\\[", "").
                replaceAll("\\]", "").
                replaceAll("=", "\": \"").replaceAll("\\{", "").
                replaceAll("\\}", "").split(", ");

        return result;
    }

    public String[] stringToArrayLines(String string){
        String[] result = string.replaceAll("\\[", "").
                replaceAll("\\{", "\"").
                replaceAll("\\}", "\"").
                replaceAll("=", "\": \"").split(", ");

        return result;
    }








}
