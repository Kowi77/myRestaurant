package my.restaurant.repository;

import my.restaurant.entity.Restaurant;
import my.restaurant.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional(readOnly = true)
public interface RestaurantRepository extends JpaRepository<Restaurant, Integer> {

    @Override
    public List<Restaurant> findAll();

    @Override
    @Transactional
    public Restaurant save(Restaurant restaurant);

    @Override
    public Restaurant findOne(Integer id);

    @Query("SELECT r FROM Restaurant r LEFT JOIN FETCH r.votedUsers WHERE r.id=:id")
    public Restaurant findWithUsers(@Param("id") int id);

    @Query("SELECT r FROM Restaurant r LEFT JOIN FETCH r.dishesForLunch WHERE r.id=:id")
    public Restaurant findWithDishes(@Param("id") int id);

   /* @Query("SELECT r FROM Restaurant r LEFT JOIN FETCH r.votedUsers, r.dishesForLunch WHERE r.id=:id")
    public Restaurant findWithUsersAndDishes(@Param("id") int id);*/

    @Override
    public boolean exists(Integer id);

    @Override
    public long count();

    @Override
    @Transactional
    @Modifying
    public void delete(Integer id);
}
