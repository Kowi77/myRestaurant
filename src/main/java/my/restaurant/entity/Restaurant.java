package my.restaurant.entity;

//import javax.validation.constraints.NotNull;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "restaurants")
public class Restaurant {

    @Id
    @Column(name = "restId")
    @GeneratedValue
    //@NotNull
    private Integer id;

    @Column(name = "name")
    //@NotNull
    private String name;

    /*@OneToMany(mappedBy = "dishes", fetch = FetchType.LAZY)
    private List<Dish> dishesForLunch;

    @OneToMany(mappedBy = "users", fetch = FetchType.LAZY)
    private List<User> votedUsers;*/

    public Restaurant(String name) {
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

    @Override
    public String toString() {
        return "Restaurant{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}