package fa.orm.asm.entity;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Set;

@Entity
public class ProductType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int product_type_id;

    @NotBlank(message = "please enter name product type!!")
    @Column(nullable = false)
    private String name;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "productType")
    private Set<Warehouse> warehouse;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "productType")
    private Set<Product> product;

    public ProductType() {

    }

    public ProductType(int product_type_id, String name) {
        this.product_type_id = product_type_id;
        this.name = name;
    }

    public int getProduct_type_id() {
        return product_type_id;
    }

    public void setProduct_type_id(int product_type_id) {
        this.product_type_id = product_type_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Warehouse> getWarehouse() {
        return warehouse;
    }

    public void setWarehouse(Set<Warehouse> warehouse) {
        this.warehouse = warehouse;
    }

    public Set<Product> getProduct() {
        return product;
    }

    public void setProduct(Set<Product> product) {
        this.product = product;
    }
}
