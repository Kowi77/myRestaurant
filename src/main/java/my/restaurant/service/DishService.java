package my.restaurant.service;

import my.restaurant.entity.Dish;
import my.restaurant.repository.DishRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DishService {

    @Autowired
    DishRepository repository;


    public Dish get(int id) {
        Dish dish = repository.findOne(id);
        return dish;//meal != null && meal.getUser().getId() == userId ? meal : null;
    }
}
