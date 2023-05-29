import jdk.jfr.DataAmount;

import javax.persistence.Column;
import javax.persistence.IdClass;
import java.io.Serializable;
import java.util.Objects;


public class SubscriptionKey implements Serializable {
    @Column(name = "student_id", insertable = false, updatable = false)
    private Integer studentId;
    @Column(name = "course_id", insertable = false, updatable = false)
    private Integer courseId;


    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }



    @Override
    public boolean equals(Object o){
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SubscriptionKey that = (SubscriptionKey) o;
        return studentId == that.getStudentId() &&
                courseId == that.getCourseId();
    }

    @Override
    public int hashCode(){
        return Objects.hash(studentId,courseId);
    }
}
