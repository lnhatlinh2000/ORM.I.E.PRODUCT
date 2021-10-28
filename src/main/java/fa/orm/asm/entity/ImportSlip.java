package fa.orm.asm.entity;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.Set;

@Entity
public class ImportSlip {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int import_id;

    @NotNull
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(nullable = false)
    private Date import_date;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "importSlip", fetch = FetchType.EAGER)
    private Set<ImportSlipDetail> importSlipDetails;

    @NotNull(message = "Warehouse NullPointerException!!")
    @ManyToOne
    @JoinColumn(name = "warehouse_id", referencedColumnName = "warehouse_id")
    private Warehouse warehouse;

    @NotNull(message = "Employee NullPointerException!!")
    @ManyToOne
    @JoinColumn(name = "employee_id", referencedColumnName = "employee_id")
    private Employee employee;

    public ImportSlip() {

    }

    public ImportSlip(int import_id, Date import_date, Warehouse warehouse, Employee employee) {
        this.import_id = import_id;
        this.import_date = import_date;
        this.warehouse = warehouse;
        this.employee = employee;
    }

    public int getImport_id() {
        return import_id;
    }

    public void setImport_id(int import_id) {
        this.import_id = import_id;
    }

    public Date getImport_date() {
        return import_date;
    }

    public void setImport_date(Date import_date) {
        this.import_date = import_date;
    }

    public Set<ImportSlipDetail> getImportSlipDetails() {
        return importSlipDetails;
    }

    public void setImportSlipDetails(Set<ImportSlipDetail> importSlipDetails) {
        this.importSlipDetails = importSlipDetails;
    }

    public Warehouse getWarehouse() {
        return warehouse;
    }

    public void setWarehouse(Warehouse warehouse) {
        this.warehouse = warehouse;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
}
