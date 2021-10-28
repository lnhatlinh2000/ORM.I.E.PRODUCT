package fa.orm.asm.entity;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Entity
public class WarehouseAddress {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int warehouse_address_id;

    @NotBlank
    @Column(nullable = false)
    private String address;

    @NotBlank
    @Column(nullable = false)
    private String address_detail;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "warehouseAddress", fetch = FetchType.EAGER)
    private Set<Warehouse> warehouses;

    @NotNull(message = "Employee NullPointerException!!")
    @ManyToOne
    @JoinColumn(name = "employee_id", referencedColumnName = "employee_id")
    private Employee employee;

    public WarehouseAddress() {

    }

    public WarehouseAddress(int warehouse_address_id, String address, String address_detail, Employee employee) {
        this.warehouse_address_id = warehouse_address_id;
        this.address = address;
        this.address_detail = address_detail;
        this.employee = employee;
    }

    public int getWarehouse_address_id() {
        return warehouse_address_id;
    }

    public void setWarehouse_address_id(int warehouse_address_id) {
        this.warehouse_address_id = warehouse_address_id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddress_detail() {
        return address_detail;
    }

    public void setAddress_detail(String address_detail) {
        this.address_detail = address_detail;
    }

    public Set<Warehouse> getWarehouses() {
        return warehouses;
    }

    public void setWarehouses(Set<Warehouse> warehouses) {
        this.warehouses = warehouses;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
}
