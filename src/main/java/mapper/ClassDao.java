package mapper;


import pojo.Class;

/**
 * 实体类dao接口
 */
public interface ClassDao {
    /**
     * 根据班级id查询学生所在的班级
     * @param classId 班级id
     * @return 封装了班级信息的Class类
     */
    Class findStuClass(int classId);
}
