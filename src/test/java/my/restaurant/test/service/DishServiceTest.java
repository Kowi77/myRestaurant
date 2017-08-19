package my.restaurant.test.service;

import my.restaurant.config.DataConfig;
import my.restaurant.entity.Dish;
import my.restaurant.service.DishService;
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

public class DishServiceTest {

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
    private DishService service;

    @Before
    public void setUp() throws Exception {
        em = emf.createEntityManager();
    }

    @Test
    public void testGet() {
        Assert.assertEquals(service.get(1), DISH_1);
        Assert.assertNotEquals(service.get(2), DISH_1);
    }

    @Test
    public void testSave() {
        //test new
        DISHES[0] = null;
        DISHES[0] = service.save(DISH_1);
        Assert.assertEquals(DISHES[0], DISH_1);
        //test update
        DISHES[1] = new Dish(2, "Салат Цезарь NEW", 120, RESTAURANT_1);
        DISHES[1] = service.save(DISH_2);
        Assert.assertEquals(DISHES[1], DISH_2);
    }

    @Test
    public void testGetWithRestaurant() {
        Dish dish = service.getWithRestaurant(1);
        Assert.assertEquals(dish, DISH_1);
        Assert.assertNotEquals(dish, DISH_2);
        Assert.assertEquals(dish.getRestaurant().getId(), DISH_1.getRestaurant().getId());
        Assert.assertEquals(dish.getRestaurant().getName(), DISH_1.getRestaurant().getName());
    }

    @Test
    public void testGetAll() {
        Assert.assertArrayEquals(DISHES, service.getAll().toArray());
    }

    @Test
    public void testIsNew(){
        Assert.assertFalse(service.isNew(2));
        Assert.assertTrue(service.isNew(15));
    }
}