package mapper;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import pojo.Student;
import tools.TestDao;

import static org.junit.Assert.*;

/**
 * @author lk
 * 2018/7/20 16:02
 * @description:
 */
public class StudentDaoTest extends TestDao {

    @Autowired
    private StudentDao studentDao;

    @Test
    public void updateStu() {
        Student student = new Student();
        student.setStudentAge(18);
        student.setStudentId(1);
        studentDao.updateStu(student);
    }
}