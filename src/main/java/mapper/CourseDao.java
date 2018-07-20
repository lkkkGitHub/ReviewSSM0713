package mapper;

import pojo.Course;

import java.util.List;

public interface CourseDao {
    List<Course> findCourse(int courseId);
}
