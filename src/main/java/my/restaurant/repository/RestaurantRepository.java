package my.restaurant.repository;

import my.restaurant.entity.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional(readOnly = true)
public interface RestaurantRepository extends JpaRepository<Restaurant, Integer> {

    @Override
    public List<Restaurant> findAll();

    //public List<Restaurant> save(Restaurant restaurant);

    @Override
    public Restaurant findOne(Integer id);

    @Override
    public boolean exists(Integer id);

    @Override
    public long count();

    @Override
    @Transactional
    @Modifying
    public void delete(Integer id);
}
