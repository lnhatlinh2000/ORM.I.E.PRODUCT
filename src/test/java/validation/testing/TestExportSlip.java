package validation.testing;

import fa.orm.asm.entity.*;
import fa.orm.asm.service.*;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import validation.ValidatorTesting;

import javax.validation.Validator;
import java.util.Date;

public class TestExportSlip {

    EmployeeService employeeService = new EmployeeService();
    WarehouseService warehouseService = new WarehouseService();
    ProductService productService = new ProductService();
    ExportSlipService exportSlipService = new ExportSlipService();
    ExportSlipDetailService exportDetailService = new ExportSlipDetailService();

    ValidatorTesting validator = new ValidatorTesting<>();

    @Test
    void testExportProduct() {
        Employee employee = employeeService.findById(Employee.class, 1);
        Warehouse warehouse = warehouseService.findById(Warehouse.class, 2);
        Product product = productService.findById(Product.class, 1);

        ExportSlip exportSlip = new ExportSlip();
        exportSlip.setExport_date(new Date());
        exportSlip.setEmployee(employee);
        exportSlip.setWarehouse(warehouse);

        ExportSlipDetail exportDetail = new ExportSlipDetail();
        exportDetail.setExportSlip(exportSlip);
        exportDetail.setExport_number(3);
        exportDetail.setProduct(product);

        if (validator.testHibernateValidator(exportSlip)
                && validator.testHibernateValidator(exportDetail)) {
            Assert.assertTrue(exportSlipService.save(exportSlip));

            int exportId = exportSlipService.getMaxValue(ExportSlip.class, "export_id");
            ExportSlip exportSlipById = exportSlipService.findById(ExportSlip.class, exportId);

            if (exportSlipById != null) {
                Assert.assertTrue(exportDetailService.save(exportDetail));
            }
        }
    }

    @Test
    void testSaveAllExportSlipProduct(){
        Assert.assertTrue(exportSlipService.saveAll());
    }
}
