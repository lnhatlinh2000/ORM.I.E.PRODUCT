package fa.orm.asm.service;

import fa.orm.asm.dao.AbstractDAO;
import fa.orm.asm.entity.ExportSlipDetail;
import fa.orm.asm.entity.ImportSlipDetail;
import fa.orm.asm.entity.Product;

import java.sql.SQLOutput;
import java.util.List;

public class ExportSlipDetailService extends AbstractDAO<ExportSlipDetail, Integer> {

    ProductService productService = new ProductService();

    /*
     * Saves all properties of the Export Split Detail entity
     * Check and print out the stock quantity and export the product when done
     * @param exportSlipDetail
     * @return boolean
     */
    @Override
    public boolean save(ExportSlipDetail entity) {
        Product product = productService.findById(Product.class, entity.getProduct().getProduct_id());
        int exportNumber = entity.getExport_number();
        int inventoryProduct = entity.getProduct().getInventory();

        if (exportNumber < inventoryProduct) {
            int inventory = inventoryProduct - exportNumber;
            product.setInventory(inventory);
            productService.update(product);
            System.out.println("The number of " + entity.getProduct().getProduct_name() + " left in stock is " + inventory);
            return super.save(entity);
        } else {
            System.out.println("Export number invalid!!");
            System.out.println("Inventory is \"" + inventoryProduct + "\" and " +
                                "Export number is \"" + exportNumber + "\"");
            return false;
        }
    }
}
