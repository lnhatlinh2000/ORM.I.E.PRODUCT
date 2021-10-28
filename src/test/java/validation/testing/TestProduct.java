package validation.testing;

import fa.orm.asm.dao.AbstractDAO;
import fa.orm.asm.entity.Product;
import fa.orm.asm.entity.ProductType;
import fa.orm.asm.service.ProductService;
import fa.orm.asm.service.ProductTypeService;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import validation.ValidatorTesting;

import java.util.List;

public class TestProduct {

    ProductService productService = new ProductService();
    ProductTypeService productTypeService = new ProductTypeService();
    ValidatorTesting<Product> validator = new ValidatorTesting<>();
    ProductType productType;

//    @BeforeAll
//    void setUpBeforeClass() throws Exception {
//        productType = productTypeService.findById(ProductType.class, 1);
//    }
//
//    @Test
//    public void testSaveProduct() {
//        Product product = new Product();
//        product.setProduct_name("Samsung A20");
//        product.setSupplier("HCM");
//        product.setPrice(700000);
//        product.setInventory(0);
//        product.setProductType(productType);
//
//        if (validator.testHibernateValidator(product)) {
//            Assert.assertNotNull(productService.save(product));
//        }
//    }
//
//    @Test
//    void testUpdateProduct() throws Exception {
//        int productId = productService.getMaxValue(Product.class, "product_id");
//
//        Product product = productService.findById(Product.class, productId);
//        product.setProduct_name("Samsung Update");
//        product.setPrice(200000);
//        product.setSupplier("Can Tho");
//
//        Assert.assertTrue(productService.update(product));
//
//        Assert.assertEquals("Samsung Update", product.getProduct_name());
//        Assert.assertEquals(200000, product.getPrice(), 0.0);
//        Assert.assertEquals("Can Tho", product.getSupplier());
//    }
//
//    @Test
//    void testFindById() throws Exception {
//        Product productById = productService.findById(Product.class, 1);
//        Assert.assertEquals("Nokia 2000", productById.getProduct_name());
//        Assert.assertEquals(500000, productById.getPrice(), 0.0);
//        Assert.assertEquals("HCM", productById.getSupplier());
//    }
//
//    @Test
//    void testDeleteProductType() throws Exception {
//        int productId = productService.getMaxValue(Product.class, "product_id");
//        Product product = productService.findById(Product.class, productId);
//        Assert.assertTrue(productService.delete(product));
//    }
//
//    @Test
//    void testGetAll() throws Exception {
//        List<Product> productList = productService.getAll("Product");
//        Assert.assertEquals(21, productList.size());
//        Product productById = productList.get(0);
//        Assert.assertEquals("Nokia 2000", productById.getProduct_name());
//        Assert.assertEquals(500000, productById.getPrice(), 0.0);
//        Assert.assertEquals("HCM", productById.getSupplier());
//    }
//
//    @Test
//    public void testProductByTypeName() {
//        List<Product> productList = productService.findByName("p");
//        Assert.assertEquals(11, productList.size());
//        Product product = productList.get(0);
//        Assert.assertEquals(1, product.getProduct_id());
//        Assert.assertEquals(0, product.getInventory());
//        Assert.assertEquals(500000, product.getPrice(), 0.0);
//        Assert.assertEquals("Nokia 2000", product.getProduct_name());
//        Assert.assertEquals("HCM", product.getSupplier());
//        Assert.assertEquals("phone", product.getProductType().getName());
//    }
//
//    @Test
//    void testProductPagination() {
//        List<Product> productListPage1 = productService.productPagination(1, 10);
//        Assert.assertEquals(10, productListPage1.size());
//
//        List<Product> productListPage3 = productService.productPagination(3, 10);
//        Assert.assertEquals(4, productListPage3.size());
//
//        Product product = productListPage3.get(0);
//        Assert.assertEquals(21, product.getProduct_id());
//        Assert.assertEquals(0, product.getInventory());
//        Assert.assertEquals(1800000, product.getPrice(), 0.0);
//        Assert.assertEquals("Asus 1002", product.getProduct_name());
//        Assert.assertEquals("Can Tho", product.getSupplier());
//    }

    @Test
    void testGetProductListPrice(){
        List<Product> productList = productService.getProductListPrice();
        for(Product product : productList){
            System.out.println(product.getProduct_name());
            System.out.println(product.getPrice());
        }
    }
}
