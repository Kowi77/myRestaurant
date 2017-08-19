package my.restaurant.repository;

import my.restaurant.entity.Dish;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Андрей on 18.08.2017.
 */
@Transactional(readOnly = true)
@Repository
public interface DishRepository extends JpaRepository<Dish, Integer> {

    @Override
    public List<Dish> findAll();

    //public List<Dish> save(Dish dish);

    @Override
    public Dish findOne(Integer id);

    @Query("SELECT d FROM Dish d LEFT JOIN FETCH d.restaurant WHERE d.id=:id")
    public Dish findWithRestaurant(@Param("id") int id);

    @Override
    public boolean exists(Integer id);

    @Override
    public long count();

    @Override
    @Transactional
    @Modifying
    public void delete(Integer id);
}
