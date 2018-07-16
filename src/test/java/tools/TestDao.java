package tools;

import mapper.AccountDao;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author lk
 * 2018/7/16 10:42
 * @description: 提供  spring 和 mybatis 的集成测试 dao接口 的功能作为父类 给子类
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:applicationcontext.xml"})
public class TestDao {
    @Autowired
    public AccountDao accountDao;
}
