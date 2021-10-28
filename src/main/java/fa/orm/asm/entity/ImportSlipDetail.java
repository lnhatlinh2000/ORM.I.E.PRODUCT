package fa.orm.asm.entity;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Entity
public class ImportSlipDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int import_detail_id;

    @Min(value = 0, message = "import number invalid!!")
    @Column(nullable = false)
    private int import_number;

    @NotNull(message = "Product NullPointerException!!")
    @ManyToOne
    @JoinColumn(name = "product_id", referencedColumnName = "product_id")
    private Product product;

    @NotNull(message = "Import slip NullPointerException!!")
    @ManyToOne
    @JoinColumn(name = "import_id", referencedColumnName = "import_id")
    private ImportSlip importSlip;

    public ImportSlipDetail() {

    }

    public ImportSlipDetail(int import_detail_id, int import_number, Product product, ImportSlip importSlip) {
        this.import_detail_id = import_detail_id;
        this.import_number = import_number;
        this.product = product;
        this.importSlip = importSlip;
    }

    public int getImport_detail_id() {
        return import_detail_id;
    }

    public void setImport_detail_id(int import_detail_id) {
        this.import_detail_id = import_detail_id;
    }

    public int getImport_number() {
        return import_number;
    }

    public void setImport_number(int import_number) {
        this.import_number = import_number;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public ImportSlip getImportSlip() {
        return importSlip;
    }

    public void setImportSlip(ImportSlip importSlip) {
        this.importSlip = importSlip;
    }
}
