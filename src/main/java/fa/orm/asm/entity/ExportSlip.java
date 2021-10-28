package fa.orm.asm.entity;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.Set;

@Entity
public class ExportSlip {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int export_id;

    @NotNull
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(nullable = false)
    private Date export_date ;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "exportSlip", fetch = FetchType.EAGER)
    private Set<ExportSlipDetail> exportSlipDetails;

    @NotNull(message = "Warehouse NullPointerException!!")
    @ManyToOne
    @JoinColumn(name = "warehouse_id", referencedColumnName = "warehouse_id")
    private Warehouse warehouse;

    @NotNull(message = "Employee NullPointerException!!")
    @ManyToOne
    @JoinColumn(name = "employee_id", referencedColumnName = "employee_id")
    private Employee employee;

    public ExportSlip() {

    }

    public ExportSlip(int export_id, Date export_date, Warehouse warehouse, Employee employee) {
        this.export_id = export_id;
        this.export_date = export_date;
        this.warehouse = warehouse;
        this.employee = employee;
    }

    public int getExport_id() {
        return export_id;
    }

    public void setExport_id(int export_id) {
        this.export_id = export_id;
    }

    public Date getExport_date() {
        return export_date;
    }

    public void setExport_date(Date export_date) {
        this.export_date = export_date;
    }

    public Set<ExportSlipDetail> getExportSlipDetails() {
        return exportSlipDetails;
    }

    public void setExportSlipDetails(Set<ExportSlipDetail> exportSlipDetails) {
        this.exportSlipDetails = exportSlipDetails;
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
