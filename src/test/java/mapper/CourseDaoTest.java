package mapper;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import pojo.Course;
import tools.TestDao;

import java.util.List;

import static org.junit.Assert.*;

/**
 * @author lk
 * 2018/7/20 19:54
 * @description:
 */
public class CourseDaoTest extends TestDao {

    @Autowired
    private CourseDao courseDao;

    @Test
    public void findCourse() { List<Course> list = courseDao.findCourse(1);
    }
}