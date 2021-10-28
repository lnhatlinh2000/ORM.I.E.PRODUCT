package fa.orm.asm.file;

import fa.orm.asm.entity.*;
import fa.orm.asm.service.EmployeeService;
import fa.orm.asm.service.ProductService;
import fa.orm.asm.service.WarehouseService;
import org.apache.poi.ss.usermodel.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class ExportSlipReadExcel extends ReadExcelFile {

    private final int COLUMN_PRODUCT_ID = 0;
    private final int COLUMN_DATE = 3;
    private final int COLUMN_NUMBER = 4;
    private final int COLUMN_EMPLOYEE_ID = 5;
    private final int COLUMN_WAREHOUSE_ID = 6;

    /*
     * Read excel file in import product sheet with index is 1
     * @param excelFilePath
     * @return List<ExportSlip>
     */
    public List<ExportSlip> readExcelImportSlip(String excelFilePath) throws IOException, ParseException {
        List<ExportSlip> exportSlipList = new ArrayList<>();
        InputStream inputStream = new FileInputStream(new File(excelFilePath));
        Workbook workbook = getWorkbook(inputStream, excelFilePath);
        //Index of sheet is 1
        Sheet sheet = workbook.getSheetAt(1);
        Iterator<Row> iterator = sheet.iterator();
        while (iterator.hasNext()) {
            Row nextRow = iterator.next();
            if (nextRow.getRowNum() == 0 || nextRow.getRowNum() == 1) {
                continue;
            }

            Iterator<Cell> cellIterator = nextRow.cellIterator();

            ExportSlip exportSlip = new ExportSlip();
            Set<ExportSlipDetail> exportDetail = new HashSet<>();
            ExportSlipDetail exportSlipDetail = new ExportSlipDetail();

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
                        exportSlipDetail.setProduct(product);
                        exportDetail.add(exportSlipDetail);
                        break;
                    case COLUMN_DATE:
                        DataFormatter dataFormatter = new DataFormatter();
                        Date date = new SimpleDateFormat("yyyy-MM-dd").parse(dataFormatter.formatCellValue(cell));
                        exportSlip.setExport_date(date);
                        break;
                    case COLUMN_NUMBER:
                        int number = new BigDecimal((double) cellValue).intValue();
                        exportSlipDetail.setExport_number(number);
                        exportDetail.add(exportSlipDetail);
                        break;
                    case COLUMN_EMPLOYEE_ID:
                        int employee_id = new BigDecimal((double) cellValue).intValue();
                        Employee employee = new EmployeeService().findById(Employee.class, employee_id);
                        exportSlip.setEmployee(employee);
                        break;
                    case COLUMN_WAREHOUSE_ID:
                        int warehouse_id = new BigDecimal((double) cellValue).intValue();
                        Warehouse warehouse = new WarehouseService().findById(Warehouse.class, warehouse_id);
                        exportSlip.setWarehouse(warehouse);
                        break;
                    default:
                        break;
                }
            }
            exportSlip.setExportSlipDetails(exportDetail);
            exportSlipList.add(exportSlip);
        }
        return exportSlipList;
    }
}
