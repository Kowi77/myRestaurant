package my.restaurant.entity;

//import javax.validation.constraints.NotNull;

import org.hibernate.Hibernate;

import javax.persistence.*;

@Entity
@Table(name = "users")
public class User extends AbstractUser {

    @Id
    @Column(name = "Id")
    @GeneratedValue
    //@NotNull
    private Integer id;

    @Column(name = "name")
   // @NotNull
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restId")
    private Restaurant restaurant;

    public User(Integer id, String name, Restaurant restaurant) {
        this.id = id;
        this.name = name;
        this.restaurant = restaurant;
    }

    public User() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || !getClass().equals(Hibernate.getClass(o))) {
            return false;
        }
        User user = (User) o;
        return getId() != null && getId().equals(user.getId()) && getName().equals(user.getName());// && getRestaurant().equals(user.getRestaurant());
    }

    @Override
    public int hashCode() {
        return (getId() == null) ? 0 : getId();
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
