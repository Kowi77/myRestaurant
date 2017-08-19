package my.restaurant.test.testData;

import my.restaurant.entity.Dish;
import my.restaurant.entity.Restaurant;
import my.restaurant.entity.User;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Андрей on 19.08.2017.
 */
public class TestData {

    public static final Restaurant RESTAURANT_1 = new Restaurant(1, "Агава");
    public static final Restaurant RESTAURANT_2 = new Restaurant(2, "Бурбон");
    public static final Restaurant RESTAURANT_3 = new Restaurant(3, "Скоморохи");

    public static final Dish DISH_1 = new Dish(1, "Пиво", 100, RESTAURANT_1);
    public static final Dish DISH_2 = new Dish(2, "Салат Цезарь", 999, RESTAURANT_1);
    public static final Dish DISH_3 = new Dish(3, "Лапша", 140, RESTAURANT_2);
    public static final Dish DISH_4 = new Dish(4, "Шашлык", 160, RESTAURANT_2);
    public static final Dish DISH_5 = new Dish(5, "Плов", 80, RESTAURANT_2);
    public static final Dish DISH_6 = new Dish(6, "Уха", 40, RESTAURANT_2);
    public static final Dish DISH_7 = new Dish(7, "Вино", 60, RESTAURANT_3);
    public static final Dish DISH_8 = new Dish(8, "Манка", 1, RESTAURANT_3);
    public static final Dish DISH_9 = new Dish(9, "Гречка", 99, RESTAURANT_3);

    public static final User USER_1 = new User(1, "Андрей", RESTAURANT_1);
    public static final User USER_2 = new User(2, "Юля", RESTAURANT_2);
    public static final User USER_3 = new User(3, "Златан", RESTAURANT_3);

    public static final Restaurant[] RESTAURANTS = {RESTAURANT_1, RESTAURANT_2, RESTAURANT_3};
    public static final Dish [] DISHES = {DISH_1, DISH_2, DISH_3, DISH_4, DISH_5, DISH_6, DISH_7, DISH_8, DISH_9};
    public static final User[] USERS = {USER_1, USER_2, USER_3};

}
