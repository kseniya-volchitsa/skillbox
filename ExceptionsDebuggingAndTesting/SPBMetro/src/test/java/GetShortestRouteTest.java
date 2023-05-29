import core.Line;
import core.Station;
import junit.framework.TestCase;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class GetShortestRouteTest extends TestCase {
        StationIndex stationIndex;
    @Override
    protected void setUp() throws Exception {
        final String DATA_FILE = "src/test/resources/testmap.json";
        stationIndex = new StationIndex();
        try {
            JSONParser parser = new JSONParser();
            JSONObject jsonData = (JSONObject) parser.parse(getJsonFile(DATA_FILE));

            JSONArray linesArray = (JSONArray) jsonData.get("lines");
            parseLines(linesArray, stationIndex);

            JSONObject stationsObject = (JSONObject) jsonData.get("stations");
            parseStations(stationsObject, stationIndex);

            JSONArray connectionsArray = (JSONArray) jsonData.get("connections");
            parseConnections(connectionsArray, stationIndex);
        } catch (Exception ex) {
            ex.printStackTrace();
        }


    }
    static void parseConnections(JSONArray connectionsArray, StationIndex stationIndex) {
        connectionsArray.forEach(connectionObject ->
        {
            JSONArray connection = (JSONArray) connectionObject;
            List<Station> connectionStations = new ArrayList<>();
            connection.forEach(item ->
            {
                JSONObject itemObject = (JSONObject) item;
                int lineNumber = ((Long) itemObject.get("line")).intValue();
                String stationName = (String) itemObject.get("station");

                Station station = stationIndex.getStation(stationName, lineNumber);
                if (station == null) {
                    throw new IllegalArgumentException("core.Station " +
                            stationName + " on line " + lineNumber + " not found");
                }
                connectionStations.add(station);
            });
            stationIndex.addConnection(connectionStations);
        });
    }

    static void parseStations(JSONObject stationsObject, StationIndex stationIndex) {
        stationsObject.keySet().forEach(lineNumberObject ->
        {
            int lineNumber = Integer.parseInt((String) lineNumberObject);
            Line line = stationIndex.getLine(lineNumber);
            JSONArray stationsArray = (JSONArray) stationsObject.get(lineNumberObject);
            stationsArray.forEach(stationObject ->
            {
                Station station = new Station((String) stationObject, line);
                stationIndex.addStation(station);
                line.addStation(station);
            });
        });
    }

    static void parseLines(JSONArray linesArray, StationIndex stationIndex) {
        linesArray.forEach(lineObject -> {
            JSONObject lineJsonObject = (JSONObject) lineObject;
            Line line = new Line(
                    ((Long) lineJsonObject.get("number")).intValue(),
                    (String) lineJsonObject.get("name")
            );
            stationIndex.addLine(line);
        });
    }


    private String getJsonFile(String DATA_FILE) {
        StringBuilder builder = new StringBuilder();
        try {
            List<String> lines = Files.readAllLines(Paths.get(DATA_FILE));
            lines.forEach(line -> builder.append(line));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return builder.toString();
    }

    public void testGetShortestRouteOnTheLine(){
        RouteCalculator routeCalculator = new RouteCalculator(stationIndex);
        List<Station> route = routeCalculator.getShortestRoute(stationIndex.getStation("Петровская"),stationIndex.getStation("Дынная"));
        String actual = "";
        int i = 0;
        for(Station s : route){
            actual += s.getName();
            i++;
            if(i < route.size()){
                actual += ' ';
            }
        }
        String expected = "Петровская Арбузная Дынная";
        assertEquals(expected,actual);
    }

    public void testGetShortestRouteWithOneConnection(){
        RouteCalculator routeCalculator = new RouteCalculator(stationIndex);
        List<Station> route = routeCalculator.getShortestRoute(stationIndex.getStation("Петровская"),stationIndex.getStation("Тыквенная"));
        String actual = "";
        int i = 0;
        for(Station s : route){
            actual += s.getName();
            i++;
            if(i < route.size()){
                actual += ' ';
            }
        }
        String expected = "Петровская Арбузная Морковная Тыквенная";
        assertEquals(expected,actual);
    }

    public void testGetShortestRouteWithTwoConnections(){
        RouteCalculator routeCalculator = new RouteCalculator(stationIndex);
        List<Station> route = routeCalculator.getShortestRoute(stationIndex.getStation("Дынная"),stationIndex.getStation("Зефир"));
        String actual = "";
        int i = 0;
        for(Station s : route){
            actual += s.getName();
            i++;
            if(i < route.size()){
                actual += ' ';
            }
        }
        String expected = "Дынная Арбузная Морковная Яблочная Пастила Зефир";
        assertEquals(expected,actual);
    }


}
