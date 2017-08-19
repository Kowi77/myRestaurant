package my.restaurant.entity;


//import javax.validation.constraints.NotNull;

import org.hibernate.Hibernate;

import javax.persistence.*;

@Entity
@Table(name = "dishes")
public class Dish {

    @Id
    @Column(name = "Id")
    @GeneratedValue
   // @NotNull
    private Integer id;

    @Column(name = "price")
   // @NotNull
    private int price;

    @Column(name = "description")
    //@NotNull
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restId")
    private Restaurant restaurant;

    public Dish( String description, int price, Restaurant restaurant) {
        this.description = description;
        this.price = price;
        this.restaurant = restaurant;
    }

    public Dish(int id, String description, int price, Restaurant restaurant) {
        this.id = id;
        this.price = price;
        this.description = description;
        this.restaurant = restaurant;
    }

    public Dish() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
        Dish dish = (Dish) o;
        return getId() != null && getId().equals(dish.getId()) && getDescription().equals(dish.getDescription()) && getPrice() == dish.getPrice();
                //&& getRestaurant().equals(dish.getRestaurant());
    }

    @Override
    public int hashCode() {
        return (getId() == null) ? 0 : getId();
    }

    @Override
    public String toString() {
        return "Dish{" +
                "id=" + id +
                ", price=" + price +
                ", description='" + description + '\'' +
                ", restaurant=" + restaurant +
                '}';
    }
}
