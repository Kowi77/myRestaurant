package my.restaurant.repository;

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
public interface UserRepository extends JpaRepository<User, Integer> {

    @Override
    public List<User> findAll();

    @Override
    @Transactional
    User save(User dish);

    @Override
    public User findOne(Integer id);

    @Query("SELECT u FROM User u LEFT JOIN FETCH u.restaurant WHERE u.id=:id")
    public User findWithRestaurant(@Param("id") int id);

    @Override
    public boolean exists(Integer id);

    @Override
    public long count();

    @Override
    @Transactional
    @Modifying
    public void delete(Integer id);


}
