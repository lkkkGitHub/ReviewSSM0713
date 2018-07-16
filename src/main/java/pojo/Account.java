package pojo;

/**
 * 数据库 账号表 实体类
 */
public class Account {
    /**
     * 账号表 主键 id自增
     */
    private long accountId;
    /**
     * 登陆账号
     */
    private String loginId;
    /**
     * 登陆密码
     */
    private String loginPwd;
    /**
     * 用户角色识别标志  1 为学生  2 为老师
     */
    private long type;

    /**
     * 获取账号主键
     *
     * @return 账号主键
     */
    public long getAccountId() {
        return accountId;
    }

    /**
     * set方法
     *
     * @param accountId 传入主键id
     */
    public void setAccountId(long accountId) {
        this.accountId = accountId;
    }

    /**
     * get 登陆id
     *
     * @return 返回登陆id
     */
    public String getLoginId() {
        return loginId;
    }

    /**
     * set方法
     *
     * @param loginId 传入登陆id
     */
    public void setLoginId(String loginId) {
        this.loginId = loginId;
    }

    /**
     * get 登陆密码
     *
     * @return 登陆密码
     */
    public String getLoginPwd() {
        return loginPwd;
    }

    /**
     * set 登陆密码
     *
     * @param loginPwd 传入登陆密码
     */
    public void setLoginPwd(String loginPwd) {
        this.loginPwd = loginPwd;
    }

    /**
     * get 角色识别标记
     *
     * @return 角色识别标记
     */
    public long getType() {
        return type;
    }

    /**
     * set 角色识别标记
     *
     * @param type 传入标记
     */
    public void setType(long type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Account{"
                + "accountId=" + accountId
                + ", loginId='" + loginId + '\''
                + ", loginPwd='" + loginPwd + '\''
                + ", type="
                + type
                + '}';
    }
}
