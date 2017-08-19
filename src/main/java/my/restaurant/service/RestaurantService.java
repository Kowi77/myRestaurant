package my.restaurant.service;

import my.restaurant.entity.Restaurant;
import my.restaurant.repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RestaurantService {

    @Autowired
    RestaurantRepository repository;

    public Restaurant save(Restaurant restaurant){
        return repository.save(restaurant);
    }

    public Restaurant get(int id) {
        Restaurant restaurant = repository.findOne(id);
        return restaurant;
    }

    public Restaurant getWithUsers(int id){
        Restaurant restaurant = repository.findWithUsers(id);
        return restaurant;
    }

    public Restaurant getWithDishes(int id){
        Restaurant restaurant = repository.findWithDishes(id);
        return restaurant;
    }

   /* public Restaurant getWithUsersAndDishes(int id){
        Restaurant restaurant = repository.findWithUsersAndDishes(id);
        return restaurant;
    }*/

    public void delete(int id){
        repository.delete(id);
    }

    public List<Restaurant> getAll(){
        return repository.findAll();
    }

    public boolean isNew(int id){
        return !repository.exists(id);
    }
}

