package pojo;

/**
 *学生用户实体类
 */
public class Student {
    /**
     * 主键id
     */
    private int studentId;
    /**
     * 外键，关系账户表id
     */
    private int accountId;
    /**
     * 学生名字
     */
    private String studentName;
    /**
     * 学生年龄
     */
    private int studentAge;
    /**
     * 学生性别
     */
    private String studentSex;
    /**
     * 学生所在班级
     */
    private int classId;

    /**
     * 获取学生主键id
     * @return 返回主键id
     */
    public int getStudentId() {
        return studentId;
    }

    /**
     * 设置主键id
     * @param studentId id参数
     */
    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    /**
     * 获取账户id
     * @return 返回账户id
     */
    public int getAccountId() {
        return accountId;
    }

    /**
     * 设置账户id
     * @param accountId 传入账户id
     */
    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    /**
     * 获取学生名字
     * @return 返回名字
     */
    public String getStudentName() {
        return studentName;
    }

    /**
     * 设置名字
     * @param studentName 设置参数
     */
    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    /**
     * 获取年龄
     * @return 返回年龄
     */
    public int getStudentAge() {
        return studentAge;
    }

    /**
     * 设置年龄
     * @param studentAge 参数
     */
    public void setStudentAge(int studentAge) {
        this.studentAge = studentAge;
    }

    /**
     * 获取性别
     * @return 返回性别
     */
    public String getStudentSex() {
        return studentSex;
    }

    /**
     * 设置性别
     * @param studentSex 返回性别
     */
    public void setStudentSex(String studentSex) {
        this.studentSex = studentSex;
    }

    /**
     * 获取班级id
     * @return
     */
    public int getClassId() {
        return classId;
    }

    /**
     * 设置班级id
     * @param classId 班级id
     */
    public void setClassId(int classId) {
        this.classId = classId;
    }

    @Override
    public String toString() {
        return "Student{" +
                "studentId=" + studentId +
                ", accountId=" + accountId +
                ", studentName='" + studentName + '\'' +
                ", studentAge=" + studentAge +
                ", studentSex='" + studentSex + '\'' +
                ", classId=" + classId +
                '}';
    }
}
