package service;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import pojo.Account;
import mapper.AccountDao;

/**
 * service类，逻辑处理；连接dao接口和controller控制器
 */
@Service
public class AccountService{
    /**
     * dao接口的自动传入
     */
    @Resource
    private AccountDao accountDao;

    /**
     * 实现dao接口中简单的插入方法
     * @param pojo 从controller传入一个Account对象到dao接口中
     * @return 插入返回的自增主键id的序号
     */
    public int insert(Account pojo){
        return accountDao.insert(pojo);
    }

}
