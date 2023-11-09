import com.zhshio.springframework.BeanDefinition;
import com.zhshio.springframework.BeanFactory;
import com.zhshio.springframework.test.bean.UserService.UserService;
import org.junit.Test;


/**
 * @Auther: 张帅
 * @Date: 2023/11/9 - 11 - 09 - 18:15
 * @Description: PACKAGE_NAME
 * @version: 1.0
 */
public class ApiTest {
    @Test
    public void test_BeanFactory() {
        // 1.初始化 BeanFactory
        BeanFactory beanFactory = new BeanFactory();

        // 2.注册 bean
        BeanDefinition beanDefinition = new BeanDefinition(new UserService());
        beanFactory.registerBeanDefinition("userService", beanDefinition);

        // 3.获取 bean
        UserService userService = (UserService) beanFactory.getBean("userService");
        userService.queryUserInfo();
    }

}
