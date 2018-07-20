package mapper;

import pojo.Student;

/**
 * dao接口连接数据库
 */
public interface StudentDao {
    /**
     * 根据session中的账号信息查询学生信息
     * @param accountId 账号id
     * @return 返回封装了信息的学生类
     */
     Student findStuInfo(int accountId);

    /**
     * 更新学生信息，判断信息是否为空
     * @param student 将信息封装在类中，传入到
     * @return 返回影响的行数，判断是否更新信息
     */
     int updateStu(Student student);
}
