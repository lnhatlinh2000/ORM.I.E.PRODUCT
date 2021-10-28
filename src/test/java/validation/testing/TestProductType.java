package validation.testing;

import fa.orm.asm.dao.AbstractDAO;
import fa.orm.asm.entity.ProductType;
import fa.orm.asm.service.ProductTypeService;
import org.junit.Assert;

import org.junit.jupiter.api.Test;
import validation.ValidatorTesting;

import java.util.List;

public class TestProductType {

    ProductTypeService productTypeService = new ProductTypeService();
    ValidatorTesting<ProductType> validator = new ValidatorTesting<>();

    @Test
    public void testSaveProductType() {
        ProductType productType = new ProductType();
        productType.setName("laptopRollBack");
        if (validator.testHibernateValidator(productType)) {
            Assert.assertNotNull(productTypeService.save(productType));
        }
    }

    @Test
    void testUpdateProductType() throws Exception {
        ProductType productType = new ProductType(1, "laptopUpdate");
        Assert.assertTrue(productTypeService.update(productType));
        ProductType productTypeById = productTypeService.findById(ProductType.class, 1);
        Assert.assertEquals("laptopUpdate", productTypeById.getName());
    }

    @Test
    void testFindById() throws Exception {
        ProductType productTypeById = productTypeService.findById(ProductType.class, 1);
        Assert.assertEquals("laptopUpdate", productTypeById.getName());
    }

    @Test
    void testDeleteProductType() throws Exception {
        int proTypeId = productTypeService.getMaxValue(ProductType.class, "product_type_id");
        ProductType productType = productTypeService.findById(ProductType.class, proTypeId);
        Assert.assertTrue(productTypeService.delete(productType));
    }

    @Test
    void testGetAll() throws Exception {
        List<ProductType> listProType = productTypeService.getAll("ProductType");
        Assert.assertEquals(3, listProType.size());
        ProductType proType = listProType.get(0);
        Assert.assertEquals("laptopUpdate", proType.getName());
    }

}
