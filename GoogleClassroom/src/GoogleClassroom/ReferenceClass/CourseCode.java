package GoogleClassroom.ReferenceClass;

import GoogleClassroom.GUI.CoursePage;
import GoogleClassroom.LIST.DoublyLinkedList;

public class CourseCode<CourseSched> extends DoublyLinkedList<CourseSched> {
    String courseCode;
    String CourseName;

    public CourseCode(){
        this.courseCode = null;
        this.CourseName = null;
    }
    public CourseCode(String courseCode, String CourseName) {
        this.courseCode = courseCode;
        this.CourseName = CourseName;
    }
    public String getCourseCode() {return this.courseCode;}
    public String getCourseName() {return this.CourseName;}
    public void setCourseCode(String courseCode) {this.courseCode = courseCode;}
    public void setCourseName(String CourseName) {this.CourseName = CourseName;}

    //generate a proper string
    @Override
    public String toString() {
        return super.toString();
    }
}
