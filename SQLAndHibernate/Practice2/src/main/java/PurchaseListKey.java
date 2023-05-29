import javax.persistence.Column;
import java.io.Serializable;
import java.util.Objects;

public class PurchaseListKey implements Serializable {

    @Column(name = "student_name", insertable = false, updatable = false)
    private String studentName;
    @Column(name = "course_name", insertable = false, updatable = false)
    private String courseName;

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PurchaseListKey that = (PurchaseListKey) o;
        return studentName == that.getStudentName() &&
                courseName == that.getCourseName();
    }

    @Override
    public int hashCode() {
        return Objects.hash(studentName, courseName);
    }
}
