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
}
