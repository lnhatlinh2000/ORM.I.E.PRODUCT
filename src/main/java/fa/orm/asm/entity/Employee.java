package fa.orm.asm.entity;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.Date;
import java.util.Set;

@Entity
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int employee_id;

    @NotBlank
    @Column(nullable = false)
    private String employee_name;

    @NotNull
    @Column(nullable = false)
    private boolean gender;

    @NotNull
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(nullable = false)
    private Date birthday;

    @Email
    @Column(length = 50,nullable = false, unique = true)
    private String email;

    @Pattern(regexp = "^\\s*(?:\\+?(\\d{1,3}))?[-. (]*(\\d{3})[-. )]*(\\d{3})[-. ]*(\\d{4})(?: *x(\\d+))?\\s*$",
            message = "phone invalid!!")
    @Column(nullable = false, unique = true)
    private String phone;

    @NotBlank
    @Column(nullable = false)
    private String address;

    @Column(nullable = false)
    @Min(value = 0, message = "role invalid!!")
    @Max(value = 2, message = "role invalid!!")
    private int role;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "employee")
    private Set<WarehouseAddress> warehouseAddress;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "employee")
    private Set<ImportSlip> importSlips;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "employee")
    private Set<ExportSlip> exportSlips;

    public Employee() {

    }

    public Employee(int employee_id, String employee_name, boolean gender, Date birthday, String email, String phone, String address, int role) {
        this.employee_id = employee_id;
        this.employee_name = employee_name;
        this.gender = gender;
        this.birthday = birthday;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.role = role;
    }

    public int getEmployee_id() {
        return employee_id;
    }

    public void setEmployee_id(int employee_id) {
        this.employee_id = employee_id;
    }

    public String getEmployee_name() {
        return employee_name;
    }

    public void setEmployee_name(String employee_name) {
        this.employee_name = employee_name;
    }

    public boolean isGender() {
        return gender;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    public Set<WarehouseAddress> getWarehouseAddress() {
        return warehouseAddress;
    }

    public void setWarehouseAddress(Set<WarehouseAddress> warehouseAddress) {
        this.warehouseAddress = warehouseAddress;
    }

    public Set<ImportSlip> getImportSlips() {
        return importSlips;
    }

    public void setImportSlips(Set<ImportSlip> importSlips) {
        this.importSlips = importSlips;
    }

    public Set<ExportSlip> getExportSlips() {
        return exportSlips;
    }

    public void setExportSlips(Set<ExportSlip> exportSlips) {
        this.exportSlips = exportSlips;
    }
}
