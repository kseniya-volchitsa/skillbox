import core.Station;

import javax.swing.text.DateFormatter;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class ParseCSV {
    File file;
    ArrayList<File> csvFiles;

    ArrayList<Station> parseCsvStations;

    public ArrayList<File> getCsvFiles() {
        return csvFiles;
    }

    ParseCSV (String path){
        file = new File(path);
        csvFiles = new ArrayList<>();
        parseCsvStations = new ArrayList<>();
        setCsvFiles(file);
    }

    private void setCsvFiles(File file) {
        File[] folderEntries = file.listFiles();
        for (File entry : folderEntries)
        {
            if (entry.isDirectory())
            {
                setCsvFiles(entry);
            }else if (getFileExtension(entry).equals("csv")){
                csvFiles.add(entry);
            } else {continue;}
        }

        for (File f : csvFiles) {
            try {
                List<String> lines = Files.readAllLines(Paths.get(f.getPath()));
                for (int i = 1; i < lines.size(); i++) {
                    String[] fragments = lines.get(i).split(",");
                    if (fragments.length != 2) {
                        System.out.println("Wrong line: " + lines.get(i));
                        continue;
                    } else {

                        String name = fragments[0].replaceAll("ё", "е");
                        //DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
                        LocalDate date = LocalDate.parse(stringDate(fragments[1]));
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
                        String data = formatter.format(date);
                        Station station = new Station(name, data);
                        parseCsvStations.add(station);
                    }
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    private static String getFileExtension(File file){
        String fileName = file.getName();
        if (fileName.lastIndexOf(".") != -1 && fileName.lastIndexOf(".") != 0)
            return fileName.substring(fileName.lastIndexOf(".")+1);
        else return "";
    }

    private static String stringDate(String date){
        String result = "";
        String[] l = date.split("\\.");

        result = l[2] + "-" + l[1] + "-" + l[0];
        return result;
    }
}
