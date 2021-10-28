package fa.orm.asm.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import fa.orm.asm.entity.*;
import fa.orm.asm.service.EmployeeService;
import fa.orm.asm.service.ProductService;
import fa.orm.asm.service.WarehouseService;
import org.apache.poi.ss.usermodel.*;

public class ImportSlipReadExcel extends ReadExcelFile{

    private final int COLUMN_PRODUCT_ID = 0;
    private final int COLUMN_DATE = 3;
    private final int COLUMN_NUMBER = 4;
    private final int COLUMN_EMPLOYEE_ID = 5;
    private final int COLUMN_WAREHOUSE_ID = 6;

    /*
     * Read excel file in import product sheet with index is 0
     * @param excelFilePath
     * @return List<ImportSlips>
     */
    public List<ImportSlip> readExcelImportSlip(String excelFilePath) throws IOException, ParseException {
        List<ImportSlip> importSlipList = new ArrayList<>();
        InputStream inputStream = new FileInputStream(new File(excelFilePath));
        Workbook workbook = getWorkbook(inputStream, excelFilePath);
        //Index of sheet is 0
        Sheet sheet = workbook.getSheetAt(0);
        Iterator<Row> iterator = sheet.iterator();
        while (iterator.hasNext()) {
            Row nextRow = iterator.next();
            if (nextRow.getRowNum() == 0 || nextRow.getRowNum() == 1) {
                continue;
            }

            Iterator<Cell> cellIterator = nextRow.cellIterator();

            ImportSlip importSlip = new ImportSlip();
            Set<ImportSlipDetail> importDetail = new HashSet<>();
            ImportSlipDetail importSlipDetail = new ImportSlipDetail();

            while (cellIterator.hasNext()) {
                Cell cell = cellIterator.next();
                Object cellValue = getCellValue(cell);
                if (cellValue == null || cellValue.toString().isEmpty()) {
                    continue;
                }
                int columnIndex = cell.getColumnIndex();
                switch (columnIndex) {
                    case COLUMN_PRODUCT_ID:
                        int product_id = new BigDecimal((double) cellValue).intValue();
                        Product product = new ProductService().findById(Product.class, product_id);
                        importSlipDetail.setProduct(product);
                        importDetail.add(importSlipDetail);
                        break;
                    case COLUMN_DATE:
                        DataFormatter dataFormatter = new DataFormatter();
                        Date date = new SimpleDateFormat("yyyy-MM-dd").parse(dataFormatter.formatCellValue(cell)) ;
                        importSlip.setImport_date(date);
                        break;
                    case COLUMN_NUMBER:
                        int number = new BigDecimal((double) cellValue).intValue();
                        importSlipDetail.setImport_number(number);
                        importDetail.add(importSlipDetail);
                        break;
                    case COLUMN_EMPLOYEE_ID:
                        int employee_id = new BigDecimal((double) cellValue).intValue();
                        Employee employee = new EmployeeService().findById(Employee.class, employee_id);
                        importSlip.setEmployee(employee);
                        break;
                    case COLUMN_WAREHOUSE_ID:
                        int warehouse_id = new BigDecimal((double) cellValue).intValue();
                        Warehouse warehouse = new WarehouseService().findById(Warehouse.class, warehouse_id);
                        importSlip.setWarehouse(warehouse);
                        break;
                    default:
                        break;
                }

            }
            importSlip.setImportSlipDetails(importDetail);
            importSlipList.add(importSlip);
        }
        return importSlipList;
    }
}
