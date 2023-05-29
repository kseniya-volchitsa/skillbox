import core.Station;
import org.json.JSONArray;
import org.json.JSONObject;
import javax.swing.text.DateFormatter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CreateJsonStationsResult {
    ArrayList<Station> stations;

    ArrayList<Station> jsonStations;

    ArrayList<Station> csvStations;
    public CreateJsonStationsResult(ArrayList<Station> stations,  ArrayList<Station> jsonStations, ArrayList<Station> csvStations){
        this.stations = stations;
        this.jsonStations = jsonStations;
        this.csvStations = csvStations;
    }

    public void createJson() throws Exception {
        addDepths();
        addFinishedDate();

        JSONObject stationsDetail = new JSONObject();
        JSONArray stationDetail = new JSONArray();



        for (int i = 0; i < stations.size(); i++){
            JSONObject station = new JSONObject();
            station.put("name", stations.get(i).getName());
            station.put("line", stations.get(i).getLine().getName());
            if (stations.get(i).getDate().isEmpty()){
                station.put("date", "-");
            } else {
                station.put("date", stations.get(i).getDate());
            }
            station.put("depth", stations.get(i).getDepth());
            station.put("hasConnection", stations.get(i).isHasConnection());
            stationDetail.put(station);
        }

        stationsDetail.put("stations", stationDetail);

        String path = "C:/Users/пользователь/IdeaProjects/FilesAndNetwork/filesandnetwork/DataCollector/data/resultStations.json";

        //Files.write(Paths.get(path),stationsDetail.toString().getBytes(StandardCharsets.UTF_8));

        List<String> stringList = new ArrayList<>();

        stringList.add("{");
        stringList.add("\t\"stations\": [");
        int count = 0;
        List<Object> stationsJson = stationDetail.toList();
        for (Object lineObject : stationsJson){
            stringList.add("\t\t{");
            String[] line = stringToArray(lineObject.toString());
            stringList.add("\t\t\t\"name\": \"" + line[4].substring(5) + "\",");
            stringList.add("\t\t\t\"line\": \"" + line[3].substring(5) + "\",");
            stringList.add("\t\t\t\"date\": \"" + line[0].substring(5) + "\",");
            stringList.add("\t\t\t\"depth\": " + line[1].substring(6) + ",");
            stringList.add("\t\t\t\"hasConnection\": " + line[2].substring(14));
            if (count < stationsJson.size()-1){
                stringList.add("\t\t},");
            } else{
                stringList.add("\t\t}");
            }
            count++;
        }
        stringList.add("\t]");
        stringList.add("}");
        Files.write(Paths.get(path), stringList, StandardOpenOption.CREATE);

    }

    public String[] stringToArray(String string){
        String[] result = string.replaceAll("\\{", "").
                replaceAll("\\}", "").split(", ");

        return result;
    }
    public void addDepths(){

        for(int i = 0; i < stations.size(); i++){
            for (int j = 0; j < jsonStations.size(); j++){
                if (stations.get(i).equals(jsonStations.get(j))){
                    if(stations.get(i).getDepth() == 0 || jsonStations.get(j).getDepth() < stations.get(i).getDepth()){
                        stations.get(i).setDepth(jsonStations.get(j).getDepth());
                        jsonStations.remove(jsonStations.get(j));
                        if(j>0) {
                            j--;
                        }
                    }
                }
            }
        }


    }

    public void addFinishedDate(){
        for(int i = 0; i < stations.size(); i++){
            for (int j = 0; j < csvStations.size(); j++){
                if (stations.get(i).equals(csvStations.get(j))){
                    stations.get(i).setDate(csvStations.get(j).getDate());
                    csvStations.remove(csvStations.get(j));
                    if(j>0) {
                        j--;
                    }
                }
            }
        }


    }
}
