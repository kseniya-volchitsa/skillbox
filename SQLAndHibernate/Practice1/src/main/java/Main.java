import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TreeSet;

public class Main {
    public static void main(String[] args) {
        //способ без sql
        //   StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
        //         .configure("hibernate.cfg.xml").build();
        // Metadata metadata = new MetadataSources(registry).getMetadataBuilder().build();
        // SessionFactory sessionFactory = metadata.getSessionFactoryBuilder().build();
        //Session session = sessionFactory.openSession();
       /* CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Course> query = builder.createQuery(Course.class);
        Root<Course> root = query.from(Course.class);
        query.select(root).orderBy(builder.desc(root.get("name")));
        List <Course> courseList = session.createQuery(query).getResultList();

        List<String> names =new ArrayList<>();

        for (Course course : courseList){
            names.add(course.getName() + " - " + getMedium(course));
        }

        for(String name : names){
            System.out.println(name);
        }*/

        //способ с sql
        String url = "jdbc:mysql://localhost:3306/skillbox";
        String user = "root";
        String pass = "123456";

        try{
            Connection connection = DriverManager.getConnection(url, user, pass);
            Statement statement = connection.createStatement();



        String result = "select p.course_name," +
                "(select (count(subscription_date))/(count(distinct month(s.subscription_date))) " +
                "from purchaselist s " +
                "where s.course_name = p.course_name )  medium " +
                "from purchaselist p " +
                "group by p.course_name";

        ResultSet resultSet = statement.executeQuery(result);

            while (resultSet.next()){
                System.out.println(resultSet.getString("course_name") +
                        " - " + resultSet.getString("medium"));
            }
            resultSet.close();
            statement.close();
            connection.close();



        } catch (Exception ex){
            ex.printStackTrace();
        }
    }
      /*  List<String> results = session.createQuery(courseNames).getResultList();

        for (String result : results){
            System.out.println(result);
        }

        sessionFactory.close();
    }

    public static int countMonth(List <Date> dates){
        TreeSet<Integer> months = new TreeSet<>();
        for (Date date : dates){
            int month = date.getMonth();
            months.add(month);
        }
        return months.size();
    }

    public static Double getMedium(Course course){
        return ((double)course.getDates().size())/((double) countMonth(course.getDates()));
    }*/

}
