import java.sql.*;

public class InsertLinkedPurchaseList {

    String url = "jdbc:mysql://localhost:3306/practice2";
    String user = "root";
    String pass = "123456";

    public void insert(String purchaselist){
        String[] connect = {url, user, pass};
        String[] array = stringToArray(purchaselist);


        array[0] = array[0].replaceAll("PurchaseList", "LinkedPurchaseList");
        array[array.length-1] = array[array.length-1].replaceAll("PurchaseList", "LinkedPurchaseList");
        array[1] = "(course_id, student_id, " + array[1];
        for (int i = 2; i < array.length; i++){
            array[i] = stringWithIds(array[i], connect);
        }

        String result = "";
        for (String s : array){
            result +=s;
        }

        try {

            Connection connection = DriverManager.getConnection(url, user, pass);
            PreparedStatement preLinkedPurchaseList = connection.prepareStatement(result);
            preLinkedPurchaseList.execute();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static String[] stringToArray(String string) {
        String[] result = string.split("\\(");

        return result;
    }

    public static String stringWithIds(String string, String[] connect) {
        String ids = "";

        try {
            Connection connection = DriverManager.getConnection(connect[0], connect[1], connect[2]);
            Statement statement = connection.createStatement();

            String[] arrayString = string.split(",");

            ResultSet resultCourse = statement.executeQuery("SELECT id FROM courses WHERE name = " + arrayString[1]);

            while (resultCourse.next()) {
                ids += resultCourse.getString("id") + ",";
            }
            resultCourse.close();

            ResultSet resultStudent = statement.executeQuery("SELECT id FROM students WHERE name = " + arrayString[0]);

            while (resultStudent.next()) {
                ids += resultStudent.getString("id") + ",";
            }
            resultStudent.close();

            statement.close();
            connection.close();

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        String result = "(" + ids + string;
        return result;
    }

}
