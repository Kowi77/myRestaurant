package my.restaurant.entity;

//import javax.validation.constraints.NotNull;

import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "restaurants")
public class Restaurant {

    @Id
    @Column(name = "Id")
    @GeneratedValue
    //@NotNull
    private Integer id;

    @Column(name = "name")
    //@NotNull
    private String name;

    @OneToMany(mappedBy = "restaurant", fetch = FetchType.LAZY)
    private List<Dish> dishesForLunch;

    @OneToMany(mappedBy = "restaurant", fetch = FetchType.LAZY)
    private List<User> votedUsers;

    public Restaurant(String name) {
        this.name = name;
    }

    public Restaurant(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Restaurant() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<Dish> getDishesForLunch() {
        return dishesForLunch;
    }

    public void setDishesForLunch(List<Dish> dishesForLunch) {
        this.dishesForLunch = dishesForLunch;
    }

    public List<User> getVotedUsers() {
        return votedUsers;
    }

    public void setVotedUsers(List<User> votedUsers) {
        this.votedUsers = votedUsers;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || !getClass().equals(Hibernate.getClass(o))) {
            return false;
        }
        Restaurant restaurant = (Restaurant) o;
        return getId() != null && getId().equals(restaurant.getId()) && getName().equals(restaurant.getName());
    }

    @Override
    public int hashCode() {
        return (getId() == null) ? 0 : getId();
    }

    @Override
    public String toString() {
        return "Restaurant{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}