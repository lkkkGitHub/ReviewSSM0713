package service;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import mapper.StudentDao;
import pojo.Student;

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
}
