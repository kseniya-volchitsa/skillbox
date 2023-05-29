import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "subscriptions")
public class Subscription {

    @EmbeddedId
    private LinkedPurchaseListKey id;



    @Column(name = "subscription_date")
    private Date subscriptionDate;


    public LinkedPurchaseListKey getId() {
        return id;
    }

    public void setId(LinkedPurchaseListKey id) {
        this.id = id;
    }

    public Date getSubscriptionDate() {
        return subscriptionDate;
    }

    public void setSubscriptionDate(Date subscriptionDate) {
        this.subscriptionDate = subscriptionDate;
    }
}
