package my.restaurant.service;

import my.restaurant.entity.Dish;
import my.restaurant.repository.DishRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DishService {

    @Autowired
    DishRepository repository;

    public Dish save(Dish dish){
        return repository.save(dish);
    }

    public Dish get(int id) {
        Dish dish = repository.findOne(id);
        return dish;
    }

    public Dish getWithRestaurant(int id){
        Dish dish = repository.findWithRestaurant(id);
        return dish;
    }

    public void delete(int id){
        repository.delete(id);
    }

    public List<Dish> getAll(){
        return repository.findAll();
    }

    public boolean isNew(int id){
        return !repository.exists(id);
    }
}
