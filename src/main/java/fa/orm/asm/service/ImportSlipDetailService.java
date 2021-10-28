package fa.orm.asm.service;

import fa.orm.asm.dao.AbstractDAO;
import fa.orm.asm.entity.ImportSlipDetail;
import fa.orm.asm.entity.Product;
import fa.orm.asm.hibernate.HibernateUtils;
import org.hibernate.Session;
import org.hibernate.query.Query;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class ImportSlipDetailService extends AbstractDAO<ImportSlipDetail, Integer> {

    ProductService productService = new ProductService();

    /*
     * Saves all properties of the Import Split Detail entity
     * Check the product's existence to update the quantity when the product is in stock
     * @param importSlipDetail
     * @return boolean
     */
    @Override
    public boolean save(ImportSlipDetail entity) {
        List<ImportSlipDetail> importSlipDetail =
                findByObjectValue(ImportSlipDetail.class, "product", "product_id", entity.getProduct().getProduct_id());
        if(importSlipDetail != null){
            Product product = productService.findById(Product.class, entity.getProduct().getProduct_id());
            product.setInventory(product.getInventory() + entity.getImport_number());
            productService.update(product);
            return super.save(entity);
        } else {
            Product product = productService.findById(Product.class, entity.getProduct().getProduct_id());
            productService.save(product);
            return super.save(entity);
        }
    }
}
