package validation.testing;

import fa.orm.asm.entity.*;
import fa.orm.asm.service.*;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import validation.ValidatorTesting;

import java.util.Date;

public class TestImportSlip {

    EmployeeService employeeService = new EmployeeService();
    WarehouseService warehouseService = new WarehouseService();
    ProductService productService = new ProductService();
    ImportSlipService importSlipService = new ImportSlipService();
    ImportSlipDetailService importDetailService = new ImportSlipDetailService();

    ValidatorTesting validator = new ValidatorTesting();

    @Test
    void testImportProduct() {

        Employee employee = employeeService.findById(Employee.class, 3);
        Warehouse warehouse = warehouseService.findById(Warehouse.class, 1);
        Product product = productService.findById(Product.class, 1);

        ImportSlip importSlip = new ImportSlip();
        importSlip.setImport_date(new Date());
        importSlip.setEmployee(employee);
        importSlip.setWarehouse(warehouse);

        ImportSlipDetail importDetail = new ImportSlipDetail();
        importDetail.setImport_number(5);
        importDetail.setImportSlip(importSlip);
        importDetail.setProduct(product);

        if (validator.testHibernateValidator(importSlip) && validator.testHibernateValidator(importDetail)) {
            Assert.assertTrue(importSlipService.save(importSlip));

            int importId = importSlipService.getMaxValue(ImportSlip.class, "import_id");
            ImportSlip importSlipById = importSlipService.findById(ImportSlip.class, importId);

            if (importSlipById != null) {
                Assert.assertTrue(importDetailService.save(importDetail));
            }
        }
    }

    @Test
    void testSaveAllImportSlipProduct(){
        Assert.assertTrue(importSlipService.saveAll());
    }
}
