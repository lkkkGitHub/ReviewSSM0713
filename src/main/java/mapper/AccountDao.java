package mapper;

import org.apache.ibatis.annotations.Param;
import pojo.Account;

/**
 * 账号表的dao接口 提供连接配置文件的方法
 */
public interface AccountDao {
    /**
     * 提供连接插入账号信息的接口
     *
     * @param pojo 将pojo中的Account作为对象传入方法
     * @return 返回一个自增主键
     */
    int insert(@Param("pojo") Account pojo);
}
