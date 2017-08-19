package my.restaurant.entity;

//import javax.validation.constraints.NotNull;

import javax.persistence.*;

@Entity
@Table(name = "users")
public class User extends AbstractUser {

    @Id
    @Column(name = "userId")
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

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
