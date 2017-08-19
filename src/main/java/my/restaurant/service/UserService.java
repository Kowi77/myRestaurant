package my.restaurant.service;

import my.restaurant.entity.User;
import my.restaurant.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Андрей on 19.08.2017.
 */
@Service
public class UserService {

    @Autowired
    UserRepository repository;

    public User save(User user){
        return repository.save(user);
    }

    public User get(int id) {
        User user = repository.findOne(id);
        return user;
    }

    public User getWithRestaurant(int id){
        User user = repository.findWithRestaurant(id);
        return user;
    }

    public void delete(int id){
        repository.delete(id);
    }

    public List<User> getAll(){
        return repository.findAll();
    }

    public boolean isNew(int id){
        return !repository.exists(id);
    }
}
