package fa.orm.asm.service;

import fa.orm.asm.dao.AbstractDAO;
import fa.orm.asm.entity.Employee;
import fa.orm.asm.entity.ImportSlip;
import fa.orm.asm.entity.ImportSlipDetail;
import fa.orm.asm.entity.Warehouse;
import fa.orm.asm.file.ImportSlipReadExcel;
import fa.orm.asm.file.ReadExcelFile;

import java.util.List;

public class ImportSlipService extends AbstractDAO<ImportSlip, Integer> {

    ImportSlipDetailService importDetailService = new ImportSlipDetailService();
    EmployeeService employeeService = new EmployeeService();
    WarehouseService warehouseService = new WarehouseService();

    @Override
    public boolean save(ImportSlip entity) {
        Employee employee = employeeService.findById(Employee.class, entity.getEmployee().getEmployee_id());
        if (employee.getRole() == 1) {
            return super.save(entity);
        } else {
            System.out.println("Role employee invalid!!");
            return false;
        }
    }

    /*
     * Import all products
     */
    public boolean saveAll() {
        try {
            List<ImportSlip> importList = new ImportSlipReadExcel().readExcelImportSlip(ReadExcelFile.filePath);
            for (ImportSlip entity : importList) {

                ImportSlip importSlip = new ImportSlip();
                importSlip.setImport_date(entity.getImport_date());
                importSlip.setEmployee(entity.getEmployee());
                importSlip.setWarehouse(entity.getWarehouse());

                if (this.save(importSlip)) {
                    int importId = getMaxValue(ImportSlip.class, "import_id");
                    ImportSlip importSlipById = findById(ImportSlip.class, importId);

                    for (ImportSlipDetail importDetail : entity.getImportSlipDetails()) {
                        ImportSlipDetail importSlipDetail = new ImportSlipDetail();
                        importSlipDetail.setImport_number(importDetail.getImport_number());
                        importSlipDetail.setImportSlip(importSlipById);
                        importSlipDetail.setProduct(importDetail.getProduct());

                        importDetailService.save(importSlipDetail);
                    }
                } else {
                    return false;
                }
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
