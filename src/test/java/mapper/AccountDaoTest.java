package mapper;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import pojo.Account;
import tools.TestDao;

/**
 * @author lk
 * 2018/7/16 10:38
 * @description:
 */
public class AccountDaoTest extends TestDao {
    @Test
    public void logIn() {
        Account account = accountDao.logIn("dfdsf");
    }
}