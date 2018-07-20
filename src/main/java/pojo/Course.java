package pojo;


public class Course {

  private Long courseId;
  private String courseName;
  private java.sql.Time courseTime;
  private String courseAddress;


  public Long getCourseId() {
    return courseId;
  }

  public void setCourseId(Long courseId) {
    this.courseId = courseId;
  }


  public String getCourseName() {
    return courseName;
  }

  public void setCourseName(String courseName) {
    this.courseName = courseName;
  }


  public java.sql.Time getCourseTime() {
    return courseTime;
  }

  public void setCourseTime(java.sql.Time courseTime) {
    this.courseTime = courseTime;
  }


  public String getCourseAddress() {
    return courseAddress;
  }

  public void setCourseAddress(String courseAddress) {
    this.courseAddress = courseAddress;
  }

}
