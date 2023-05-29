package core;

import java.time.LocalDate;
import java.util.ArrayList;

public class Station implements Comparable<Station>
{
    private Line line;
    private String name;

    private String date;

    private double depth;

    public Station(String name, String date) {
        this.name = name;
        this.date = date;
    }

    public Station(String name, double depth) {
        this.name = name;
        this.depth = depth;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setDepth(double depth) {
        this.depth = depth;
    }

    private ArrayList<Station> connection;

    public ArrayList<Station> getConnection() {
        return connection;
    }

    private boolean hasConnection;


    public Station(String name, Line line)
    {
        this.name = name;
        this.line = line;
        this.date = "";
        this.depth = 0;
        this.connection = new ArrayList<>();
        hasConnection = false;
    }

    public void addConnection(Station connect){
        connection.add(connect);
        hasConnection = true;
    }


    public Line getLine()
    {
        return line;
    }


    public void setLine(Line line){
        this.line = line;
    }
    public String getName()
    {
        return name;
    }

    public String getDate() { return date;}

    public double getDepth() { return depth;}

    public boolean isHasConnection() { return hasConnection;}
    @Override
    public int compareTo(Station station)
    {
        return name.compareToIgnoreCase(station.getName());
    }

    @Override
    public boolean equals(Object obj)
    {
        return compareTo((Station) obj) == 0;
    }

    @Override
    public String toString()
    {
        String result = "Station: name " + name + ", line " + line;
        return result;
    }
}