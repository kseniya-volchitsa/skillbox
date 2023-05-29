import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;

public class Expenses {

public static int count = 0;

    private HashMap<String, Double> expenses;
    public Expenses(String path){
        expenses = loadExpensesFromFile(path);
    }
    public HashMap<String, Double> getExpenses() {
        return expenses;
    }
    public static HashMap<String, Double> loadExpensesFromFile(String path){
        HashMap<String, Double> expenses = new HashMap<>();
        try{
            List<String> lines = Files.readAllLines(Paths.get(path));
            for (int i = 1; i < lines.size(); i++){
                String[] fragments = lines.get(i).split(",");
                if (fragments.length != 8) {
                    System.out.println("Wrong line: " + lines.get(i));
                    continue;
                }

                if (Double.parseDouble(fragments[6]) == 0){
                    String name = formatName(fragments[5]);
                    if(expenses.containsKey(name))
                    {
                        double sum = expenses.get(name) + Double.parseDouble(fragments[7]);
                        expenses.
                                put(name, sum);
                    } else {
                    expenses.put(name, Double.parseDouble(fragments[7]));
                    }
                }
            }
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
        System.out.println(count);
        return expenses;
    }

    public static String formatName(String name){

        String result = "";
        int start = name.lastIndexOf("\\")+1;
        if (start == 0){
            start = name.lastIndexOf("/")+1;
        }
        int end = name.indexOf('.')-2;

        /*

        int end = name.indexOf("[0-9]{2}[\\.]{1}[0-9]{2}");
        */
        if (start < 0){
            result = "delete";
        } else{
            result = name.substring(start,end).trim();
        }

        count++;
        return result.toString();
    }


}
