package service;

import mapper.StudentDao;
import org.springframework.stereotype.Service;
import pojo.Student;

import javax.annotation.Resource;

/**
 * 学生表service连接controller和dao接口
 */
@Service
public class StudentService {

    /**
     * 实例化dao对象
     */
    @Resource
    private StudentDao studentDao;

    /**
     * 将控制器的session中id传入dao接口中，查询信息
     * @param accountId session的账号id
     * @return 返回一个封装了学生信息的student类
     */
    public Student findStuInfo(int accountId) {
        return studentDao.findStuInfo(accountId);
    }

    /**
     * 更新学生信息，对传入的用户输入信息和原本的学生信息进行比较，信息未进行更新时，返回给controller  3，更新了信息即返回dao的执行结果
     * @param student 封装了用户输入信息的student类
     * @param studentInfo 封装了用户未修改之前的完整数据
     * @return 返回影响的行数，判断是否更新成功
     */
    public int updateStu(Student student, Student studentInfo) {
        //x计数，当用户未对信息进行修改时，x++ 修改了不变；当x=3时即用户未进行任何修改，即反馈给controller
        int x = 0;
        //信息未进行更新的x的最大值
        final int max = 3;
        //studentXml是最后传入到dao接口中的student类，当x=3时，报错不传入，0=<x<3时 传入到dao接口中
        Student studentXml = new Student();
        studentXml.setStudentId(studentInfo.getStudentId());
        if ((student.getStudentName()).equals(studentInfo.getStudentName())) {
            x++;
            studentXml.setStudentName(null);
        } else {
            studentXml.setStudentName(student.getStudentName());
        }
        if (student.getStudentAge() == studentInfo.getStudentAge()) {
            x++;
            studentXml.setStudentAge(0);
        } else  {
            studentXml.setStudentAge(student.getStudentAge());
        }
        if ((student.getStudentSex()).equals(studentInfo.getStudentSex())) {
            x++;
            studentXml.setStudentSex(null);
        } else {
            studentXml.setStudentSex(student.getStudentSex());
        }
        if (x == max) {
            return x;
        } else {
            return studentDao.updateStu(studentXml);
        }
    }
}
