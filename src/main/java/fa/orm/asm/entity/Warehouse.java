package fa.orm.asm.entity;


import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.Set;

@Entity
public class Warehouse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int warehouse_id;

    @NotBlank
    @Column(nullable = false)
    private String warehouse_name;

    @Pattern(regexp = "^\\s*(?:\\+?(\\d{1,3}))?[-. (]*(\\d{3})[-. )]*(\\d{3})[-. ]*(\\d{4})(?: *x(\\d+))?\\s*$",
            message = "phone invalid!!")
    @Column(nullable = false, unique = true)
    private String warehouse_phone;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "warehouse")
    private Set<ImportSlip> impSlip;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "warehouse")
    private Set<ExportSlip> expSlip;

    @NotNull(message = "Product type NullPointerException!!")
    @ManyToOne
    @JoinColumn(name = "product_type_id", referencedColumnName = "product_type_id")
    private ProductType productType;

    @NotNull(message = "Warehouse address NullPointerException!!")
    @ManyToOne
    @JoinColumn(name = "warehouse_address_id", referencedColumnName = "warehouse_address_id")
    private WarehouseAddress warehouseAddress;

    public Warehouse() {

    }

    public Warehouse(int warehouse_id, String warehouse_name, String warehouse_phone, ProductType productType, WarehouseAddress warehouseAddress) {
        this.warehouse_id = warehouse_id;
        this.warehouse_name = warehouse_name;
        this.warehouse_phone = warehouse_phone;
        this.productType = productType;
        this.warehouseAddress = warehouseAddress;
    }

    public int getWarehouse_id() {
        return warehouse_id;
    }

    public void setWarehouse_id(int warehouse_id) {
        this.warehouse_id = warehouse_id;
    }

    public String getWarehouse_name() {
        return warehouse_name;
    }

    public void setWarehouse_name(String warehouse_name) {
        this.warehouse_name = warehouse_name;
    }

    public String getWarehouse_phone() {
        return warehouse_phone;
    }

    public void setWarehouse_phone(String warehouse_phone) {
        this.warehouse_phone = warehouse_phone;
    }

    public Set<ImportSlip> getImpSlip() {
        return impSlip;
    }

    public void setImpSlip(Set<ImportSlip> impSlip) {
        this.impSlip = impSlip;
    }

    public Set<ExportSlip> getExpSlip() {
        return expSlip;
    }

    public void setExpSlip(Set<ExportSlip> expSlip) {
        this.expSlip = expSlip;
    }

    public ProductType getProductType() {
        return productType;
    }

    public void setProductType(ProductType productType) {
        this.productType = productType;
    }

    public WarehouseAddress getWarehouseAddress() {
        return warehouseAddress;
    }

    public void setWarehouseAddress(WarehouseAddress warehouseAddress) {
        this.warehouseAddress = warehouseAddress;
    }
}
