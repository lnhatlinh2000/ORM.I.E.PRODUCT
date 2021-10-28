package fa.orm.asm.service;

import fa.orm.asm.dao.AbstractDAO;
import fa.orm.asm.entity.*;
import fa.orm.asm.file.ExportSlipReadExcel;
import fa.orm.asm.file.ImportSlipReadExcel;
import fa.orm.asm.file.ReadExcelFile;

import java.util.List;

public class ExportSlipService extends AbstractDAO<ExportSlip, Integer> {

    EmployeeService employeeService = new EmployeeService();
    ExportSlipDetailService exportDetailService = new ExportSlipDetailService();

    @Override
    public boolean save(ExportSlip entity) {
        Employee employee = employeeService.findById(Employee.class, entity.getEmployee().getEmployee_id());
        if(employee.getRole() == 2){
            return super.save(entity);
        } else {
            System.out.println("Employee role invalid!!");
            return false;
        }
    }

    /*
     * Import all products
     */
    public boolean saveAll() {
        try {
            List<ExportSlip> exportList = new ExportSlipReadExcel().readExcelImportSlip(ReadExcelFile.filePath);
            for (ExportSlip entity : exportList) {

                ExportSlip exportSlip = new ExportSlip();
                exportSlip.setExport_date(entity.getExport_date());
                exportSlip.setEmployee(entity.getEmployee());
                exportSlip.setWarehouse(entity.getWarehouse());

                if (this.save(exportSlip)) {
                    int exportId = getMaxValue(ExportSlip.class, "export_id");
                    ExportSlip exportSlipById = findById(ExportSlip.class, exportId);

                    for (ExportSlipDetail exportDetail : entity.getExportSlipDetails()) {
                        ExportSlipDetail exportSlipDetail = new ExportSlipDetail();
                        exportSlipDetail.setExport_number(exportDetail.getExport_number());
                        exportSlipDetail.setExportSlip(exportSlipById);
                        exportSlipDetail.setProduct(exportDetail.getProduct());

                        exportDetailService.save(exportSlipDetail);
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
