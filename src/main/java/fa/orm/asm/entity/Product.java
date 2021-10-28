package fa.orm.asm.entity;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Set;

@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int product_id;

    @Pattern(regexp = "^\\D{40}+$", message = "name invalid!!")
    @Column(nullable = false)
    private String product_name;

    @NotBlank
    @Column(nullable = false)
    private String supplier;

    @NotNull(message = "Price NullPointerException!!")
    @Column(nullable = false)
    private float price;

    @Column
    private int inventory;

    @NotNull(message = "Import slip NullPointerException!!")
    @ManyToOne
    @JoinColumn(name = "product_type_id", referencedColumnName = "product_type_id")
    private ProductType productType;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "product")
    private Set<ImportSlipDetail> importSlipDetails;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "product")
    private Set<ExportSlipDetail> expDetail;

    public Product() {

    }

    public Product(int product_id, String product_name, String supplier, float price, int inventory, ProductType productType) {
        this.product_id = product_id;
        this.product_name = product_name;
        this.supplier = supplier;
        this.price = price;
        this.inventory = inventory;
        this.productType = productType;
    }

    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public String getSupplier() {
        return supplier;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getInventory() {
        return inventory;
    }

    public void setInventory(int inventory) {
        this.inventory = inventory;
    }

    public ProductType getProductType() {
        return productType;
    }

    public void setProductType(ProductType productType) {
        this.productType = productType;
    }

    public Set<ImportSlipDetail> getImportSlipDetails() {
        return importSlipDetails;
    }

    public void setImportSlipDetails(Set<ImportSlipDetail> importSlipDetails) {
        this.importSlipDetails = importSlipDetails;
    }

    public Set<ExportSlipDetail> getExpDetail() {
        return expDetail;
    }

    public void setExpDetail(Set<ExportSlipDetail> expDetail) {
        this.expDetail = expDetail;
    }
}
