import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
    String path = "data/movementList.csv";
    Expenses exp = new Expenses(path);

        HashMap<String, Double> expense = exp.getExpenses();
        for (Map.Entry<String, Double> entry : expense.entrySet()){
            System.out.println("Операция: " + entry.getKey() + ", сумма:  " + entry.getValue());
        }
    }


    
    }
