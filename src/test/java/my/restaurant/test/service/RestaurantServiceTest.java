package my.restaurant.test.service;

import my.restaurant.config.DataConfig;
import my.restaurant.entity.Dish;
import my.restaurant.entity.Restaurant;
import my.restaurant.entity.User;
import my.restaurant.service.DishService;
import my.restaurant.service.RestaurantService;
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

public class RestaurantServiceTest {

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
    private RestaurantService service;

    @Before
    public void setUp() throws Exception {
        em = emf.createEntityManager();
    }

    @Test
    public void testGet() {
        Assert.assertEquals(service.get(1), RESTAURANT_1);
        Assert.assertNotEquals(service.get(2), RESTAURANT_1);
    }

    @Test
    public void testSave() {
        //test new
        RESTAURANTS[0] = null;
        RESTAURANTS[0] = service.save(RESTAURANT_1);
        Assert.assertEquals(RESTAURANTS[0], RESTAURANT_1);
        //test update
        RESTAURANTS[2] = new Restaurant(3, "Риберишкин кабак");
        RESTAURANTS[2] = service.save(RESTAURANT_3);
        Assert.assertEquals(RESTAURANTS[2], RESTAURANT_3);
    }

    @Test
    public void testDelete() {
        service.delete(3);
        Assert.assertTrue(service.isNew(3));
    }

    @Test
    public void testGetWithUsers() {
        Restaurant restaurant = service.getWithUsers(2);
        Assert.assertEquals(restaurant, RESTAURANT_2);
        Assert.assertNotEquals(restaurant, RESTAURANT_1);
        User[] users = {USER_2};
        Assert.assertArrayEquals(restaurant.getVotedUsers().toArray(), users);
    }

    @Test
    public void testGetWithDishes() {
        Restaurant restaurant = service.getWithDishes(3);
        Assert.assertEquals(restaurant, RESTAURANT_3);
        Assert.assertNotEquals(restaurant, RESTAURANT_2);
        Dish [] dishes = {DISH_7, DISH_8, DISH_9};
        Assert.assertArrayEquals(restaurant.getDishesForLunch().toArray(), dishes);
    }

    @Test
    public void testGetAll() {
        Assert.assertArrayEquals(RESTAURANTS, service.getAll().toArray());
    }

    @Test
    public void testIsNew(){
        Assert.assertFalse(service.isNew(2));
        Assert.assertTrue(service.isNew(15));
    }
}