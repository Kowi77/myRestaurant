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
import java.util.List;
import java.util.concurrent.TimeUnit;

import static my.restaurant.test.testData.TestData.*;

@DirtiesContext
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestDataBaseConfig.class)
@WebAppConfiguration
//@Sql(scripts = "classpath:db/populateDb.sql", config = @SqlConfig(encoding = "UTF-8"))

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
    public void testGet() throws Exception {
        Dish dish = service.get(1);
        Assert.assertEquals(dish.getDescription(), DISH_1.getDescription());
        Assert.assertEquals(dish.getPrice(), DISH_1.getPrice());
        Assert.assertEquals(dish.getId(), DISH_1.getId());
    }

    @Test
    public void testGetWithRestaurant() throws Exception {
        Dish dish = service.getWithRestaurant(1);
        Assert.assertEquals(dish.getDescription(), DISH_1.getDescription());
        Assert.assertEquals(dish.getPrice(), DISH_1.getPrice());
        Assert.assertEquals(dish.getId(), DISH_1.getId());
        Assert.assertEquals(dish.getRestaurant().getId(), DISH_1.getRestaurant().getId());
        Assert.assertEquals(dish.getRestaurant().getName(), DISH_1.getRestaurant().getName());
    }

    @Test
    public void testGetAll() throws Exception {
        List<Dish> dishes = service.getAll();
        Assert.assertArrayEquals(DISHES, dishes.toArray());
    }
}