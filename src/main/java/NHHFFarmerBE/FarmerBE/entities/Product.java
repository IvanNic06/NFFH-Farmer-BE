package NHHFFarmerBE.FarmerBE.entities;

import jakarta.persistence.*;


@Table(name = "Product")
@Entity
public class Product {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    private Integer id;
    
    @Column(name = "title", unique = true, length = 200, nullable = false)
    private String title;

    @Column(name = "seller", unique = true, length = 200, nullable = false)
    private String seller;

    @Column(name = "image", unique = true, length = 200, nullable = false)
    private String image;

    @Column(name = "description", unique = true, nullable = false)
    private String description;

    @Column(name = "price", unique = true, length = 200, nullable = false)
    private String price;

    @Column(name = "weight", unique = true, length = 200, nullable = false)
    private String weight;

    @Column(name = "availability", unique = true, length = 200, nullable = false)
    private Boolean availability = true;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSeller() {
        return seller;
    }

    public void setSeller(String seller) {
        this.seller = seller;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public Boolean getAvailability() {
        return availability;
    }

    public void setAvailability(Boolean availability) {
        this.availability = availability;
    }

    @Override
    public String toString() {
        return "Product [id=" + id + ", title=" + title + ", seller=" + seller + ", image=" + image + ", description="
                + description + ", price=" + price + ", weight=" + weight + ", availability=" + availability + "]";
    }

    
    
    

    

}
