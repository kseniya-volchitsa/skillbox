import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
public class Main {
    public static void main(String[] args) {
        StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure("hibernate.cfg.xml").build();
        Metadata metadata = new MetadataSources(registry).getMetadataBuilder().build();
        SessionFactory sessionFactory = metadata.getSessionFactoryBuilder().build();
        sessionFactory.close();

        InsertTables insertTables = new InsertTables();
        insertTables.insert();

        InsertLinkedPurchaseList insertLinkedPurchaseList = new InsertLinkedPurchaseList();
        insertLinkedPurchaseList.insert(insertTables.purchaselist);
    }
}
