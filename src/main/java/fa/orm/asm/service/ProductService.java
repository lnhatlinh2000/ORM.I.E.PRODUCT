package fa.orm.asm.service;

import fa.orm.asm.dao.AbstractDAO;
import fa.orm.asm.entity.Product;
import fa.orm.asm.entity.ProductType;
import fa.orm.asm.hibernate.HibernateUtils;
import org.hibernate.Session;
import org.hibernate.query.Query;


import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class ProductService extends AbstractDAO<Product, Integer> {

    Session session = null;

    /*
     * Search product with name product type. Finds any values that start with "value".
     * @param productTypeName
     * @return listProduct
     */
    public List<Product> findByName(String productTypeName) {
        try{
            session = HibernateUtils.getSessionFactory().openSession();
            CriteriaBuilder builder = session.getCriteriaBuilder();

            CriteriaQuery<Product> criteriaQuery = builder.createQuery(Product.class);
            Root<Product> productRoot = criteriaQuery.from(Product.class);
            Root<ProductType> productTypeRoot = criteriaQuery.from(ProductType.class);
            criteriaQuery.select(productRoot);

            criteriaQuery.where(builder.equal(productRoot.get("productType"),
                                productTypeRoot.get("product_type_id")),
                    builder.like(productRoot.get("productType").get("name"), productTypeName + "%"));

            Query<Product> query = session.createQuery(criteriaQuery);
            List<Product> listProduct = query.getResultList();
            return listProduct;
        } finally {
            session.close();
        }
    }

    public List<Product> productPagination(int pageNumber, int limit) {
        try{
            session = HibernateUtils.getSessionFactory().openSession();
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<Product> criteriaQuery = criteriaBuilder.createQuery(Product.class);

            Root<Product> root = criteriaQuery.from(Product.class);
            criteriaQuery.select(root);

            Query<Product> query = session.createQuery(criteriaQuery);
            query.setFirstResult((pageNumber -1) * limit);
            query.setMaxResults(limit);

            List<Product> productList = query.getResultList();
            return productList;
        } finally {
            session.close();
        }
    }

    public List<Product> getProductListPrice(){
        try {
            session = HibernateUtils.getSessionFactory().openSession();
            Query query = session.createNativeQuery("SELECT * FROM Product WHERE price > 700000").addEntity(Product.class);
            List<Product> result = query.getResultList();

            return result;
        } finally {

        }
    }
}
