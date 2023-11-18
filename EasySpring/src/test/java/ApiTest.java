
import cn.hutool.core.io.IoUtil;
import com.zhshio.springframework.beans.PropertyValue;
import com.zhshio.springframework.beans.PropertyValues;
import com.zhshio.springframework.beans.factory.config.BeanDefinition;
import com.zhshio.springframework.beans.factory.config.BeanReference;
import com.zhshio.springframework.beans.factory.support.DefaultListableBeanFactory;
import com.zhshio.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import com.zhshio.springframework.core.io.DefaultResourceLoader;
import com.zhshio.springframework.core.io.Resource;
import com.zhshio.springframework.test.bean.UserDao;
import com.zhshio.springframework.test.bean.UserService;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;


/**
 * @Auther: 张帅
 * @Date: 2023/11/9 - 11 - 09 - 18:15
 * @Description: PACKAGE_NAME
 * @version: 1.0
 */
public class ApiTest {
    private DefaultResourceLoader resourceLoader;

    @Before
    public void init() {
        resourceLoader = new DefaultResourceLoader();
    }

    @Test
    public void test_xml() {
        // 1.初始化 BeanFactory
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

        // 2. 读取配置文件&注册Bean
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory);
        reader.loadBeanDefinitions("classpath:spring.xml");

        // 3. 获取Bean对象调用方法
        UserService userService = (UserService) beanFactory.getBean("userService", UserService.class);
        String result = userService.queryUserInfo();
        System.out.println("测试结果：" + result);
    }


    @Test
    public void test_classpath() throws IOException {
        Resource resource = resourceLoader.getResource("classpath:important.properties");
        InputStream inputStream = resource.getInputStream();
        String content = IoUtil.readUtf8(inputStream);
        System.out.println(content);
    }

    @Test
    public void test_file() throws IOException {
        Resource resource = resourceLoader.getResource("src/test/resources/important.properties");
        InputStream inputStream = resource.getInputStream();
        String content = IoUtil.readUtf8(inputStream);
        System.out.println(content);
    }

    @Test
    public void test_url() throws IOException {
        // 网络原因可能导致GitHub不能读取，可以放到自己的Gitee仓库。读取后可以从内容中搜索关键字；OLpj9823dZ
        Resource resource = resourceLoader.getResource("https://github.com/fuzhengwei/small-spring/blob/main/important.properties");
        InputStream inputStream = resource.getInputStream();
        String content = IoUtil.readUtf8(inputStream);
        System.out.println(content);
    }


//    @Test
//    public void test_BeanFactory() {
//        // 1.初始化 BeanFactory
//        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
//
//        // 2. UserDao 注册
//        beanFactory.registerBeanDefinition("userDao", new BeanDefinition(UserDao.class));
//
//        // 3. UserService 设置属性[uId、userDao]
//        PropertyValues propertyValues = new PropertyValues();
//        propertyValues.addPropertyValue(new PropertyValue("uId", "10001"));
//        propertyValues.addPropertyValue(new PropertyValue("userDao",new BeanReference("userDao")));
//
//        // 4. UserService 注入bean
//        BeanDefinition beanDefinition = new BeanDefinition(UserService.class, propertyValues);
//        beanFactory.registerBeanDefinition("userService", beanDefinition);
//
//        // 5. UserService 获取bean
//        UserService userService = (UserService) beanFactory.getBean("userService");
//        userService.queryUserInfo();
//    }

}
