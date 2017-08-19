package my.restaurant.test.service;

import my.restaurant.config.DataConfig;
import my.restaurant.entity.Dish;
import my.restaurant.entity.User;
import my.restaurant.service.DishService;
import my.restaurant.service.UserService;
import my.restaurant.test.config.TestDataBaseConfig;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.Level;

import org.junit.*;
import org.junit.rules.Stopwatch;
import org.junit.runner.Description;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static my.restaurant.test.testData.TestData.*;

@DirtiesContext
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestDataBaseConfig.class)
@WebAppConfiguration
@Sql(scripts = {"classpath:db/initDb.sql", "classpath:db/populateDb.sql"}, config = @SqlConfig(encoding = "UTF-8"))

public class UserServiceTest {

    static final Logger log = LogManager.getLogger(DishServiceTest.class);

    @Rule
    public Stopwatch stopwatch = new Stopwatch() {
        @Override
        protected void finished(long nanos, Description description) {
            log.info(String.format("%-25s %7d", description.getMethodName(), TimeUnit.NANOSECONDS.toMillis(nanos)) + " ms\n");
        }
    };

    @Resource
    private EntityManagerFactory emf;

    protected EntityManager em;

    @Autowired
    private UserService service;

    @Before
    public void setUp() throws Exception {
        em = emf.createEntityManager();
    }

    @Test
    public void testGet() {
        Assert.assertEquals(service.get(1), USER_1);
        Assert.assertNotEquals(service.get(2), USER_1);
    }

    @Test
    public void testSave() {
        //test new
        USERS[0] = null;
        USERS[0] = service.save(USER_1);
        Assert.assertEquals(USERS[0], USER_1);
        //test update
        USERS[2] = new User(3, "Риберишка", RESTAURANT_3);
        USERS[2] = service.save(USER_3);
        Assert.assertEquals(USERS[2], USER_3);
    }

    @Test
    public void testDelete() {
        service.delete(3);
        Assert.assertTrue(service.isNew(3));
    }

    @Test
    public void testGetWithRestaurant() {
        User user = service.getWithRestaurant(1);
        Assert.assertEquals(user, USER_1);
        Assert.assertNotEquals(user, USER_2);
        Assert.assertEquals(user.getRestaurant(), USER_1.getRestaurant());
    }

    @Test
    public void testGetAll() {
        Assert.assertArrayEquals(USERS, service.getAll().toArray());
    }

    @Test
    public void testIsNew(){
        Assert.assertFalse(service.isNew(2));
        Assert.assertTrue(service.isNew(15));
    }
}