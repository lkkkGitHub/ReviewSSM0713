package service;

import mapper.AccountDao;
import org.springframework.stereotype.Service;
import pojo.Account;

import javax.annotation.Resource;

/**
 * service类，逻辑处理；连接dao接口和controller控制器
 */
@Service
public class AccountService {
    /**
     * dao接口的自动传入
     */
    @Resource
    private AccountDao accountDao;

    /**
     * 实现dao接口中简单的插入方法
     *
     * @param pojo 从controller传入一个Account对象到dao接口中
     * @return 插入返回的自增主键id的序号
     */
    public int insert(Account pojo) {
        return accountDao.insert(pojo);
    }

    /**
     * 登陆service，传入用户的登陆账号，密码；
     * 通过传入账号给dao接口查询信息，查询到了账号信息及返回一个account对象；未查询到信息返回一个空，信息为空时便返回一个空给controller
     * 当查询到的信息
     * @param loginId 登陆账号，传入dao接口中查询信息
     * @param loginPwd 登陆密码，与查询到的信息进行匹配
     * @param message 带回给controller信息的反馈；
     *                "0"即表示账号错误查询不到信息；"1"即表示密码错误;"2"即表示账号及密码正确
     * @return 账号正确时：密码正确即返回给controller正确的信息，密码错误即返回一个空；账号错误，即返回空
     */
    public Account login(String loginId, String loginPwd, StringBuffer message) {
        Account account = accountDao.logIn(loginId);
        if (account == null) {
            message.append("0");
            return null;
        } else {
            if (loginPwd.equals(account.getLoginPwd())) {
                message.append("2");
                return account;
            } else {
                message.append("1");
                return null;
            }
        }
    }
}
