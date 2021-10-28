package fa.orm.asm.entity;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.Date;

@Entity
public class ExportSlipDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int export_detail_id;

    @Min(value = 0, message = "export number invalid!!")
    @Column(nullable = false)
    private int export_number;

    @NotNull(message = "Export slip NullPointerException!!")
    @ManyToOne
    @JoinColumn(name = "export_id", referencedColumnName = "export_id")
    private ExportSlip exportSlip;

    @NotNull(message = "Product NullPointerException!!")
    @ManyToOne
    @JoinColumn(name = "product_id", referencedColumnName = "product_id")
    private Product product;

    public ExportSlipDetail() {

    }

    public ExportSlipDetail(int export_detail_id, int export_number, ExportSlip exportSlip, Product product) {
        this.export_detail_id = export_detail_id;
        this.export_number = export_number;
        this.exportSlip = exportSlip;
        this.product = product;
    }

    public int getExport_detail_id() {
        return export_detail_id;
    }

    public void setExport_detail_id(int export_detail_id) {
        this.export_detail_id = export_detail_id;
    }

    public int getExport_number() {
        return export_number;
    }

    public void setExport_number(int export_number) {
        this.export_number = export_number;
    }

    public ExportSlip getExportSlip() {
        return exportSlip;
    }

    public void setExportSlip(ExportSlip exportSlip) {
        this.exportSlip = exportSlip;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
