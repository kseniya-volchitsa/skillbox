import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "students")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;
    private int age;

    @Column(name = "registration_date")
    private Date registrationDate;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "subscriptions",
            joinColumns = {@JoinColumn (name = "student_id")},
            inverseJoinColumns = {@JoinColumn (name = "course_id")}
    )
    private List<Course> coursesSub;
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "linkedpurchaselist",
            joinColumns = {@JoinColumn (name = "student_id")},
            inverseJoinColumns = {@JoinColumn (name = "course_id")}
    )
    private List<Course> coursesPur;

    public List<Course> getCoursesSub() {
        return coursesSub;
    }

    public void setCoursesSub(List<Course> coursesSub) {
        this.coursesSub = coursesSub;
    }

    public List<Course> getCoursesPur() {
        return coursesPur;
    }

    public void setCoursesPur(List<Course> coursesPur) {
        this.coursesPur = coursesPur;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Date getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
    }

}
