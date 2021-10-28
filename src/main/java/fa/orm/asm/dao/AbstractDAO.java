package fa.orm.asm.dao;

import fa.orm.asm.entity.ImportSlipDetail;
import fa.orm.asm.hibernate.HibernateUtils;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.io.Serializable;
import java.util.List;

public class AbstractDAO<T, K> {
    Session session = null;
    Transaction transaction = null;

    /*
     * Load all data in database
     * @param table name entity
     * @return list<T>
     */
    public List<T> getAll(String entity) {
        try {
            session = HibernateUtils.getSessionFactory().openSession();
            Query<T> query = session.createQuery(" FROM " + entity);
            return query.list();
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    /*
     * Save data
     * @param entity
     * @return entity or null
     */
    public boolean save(T entity) {
        try {
            session = HibernateUtils.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            Serializable result = session.save(entity);
            transaction.commit();
            return (result != null);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    /*
     * Update data
     * @param entity
     * @return boolean
     */
    public boolean update(T entity) {
        try {
            session = HibernateUtils.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.update(entity);
            transaction.commit();
            return true;
        } catch (Exception e) {
            e.getMessage();
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return false;
    }

    /*
     * Delete by entity
     * @param entity
     * @return boolean
     */
    public boolean delete(T entity) {
        try {
            session = HibernateUtils.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.delete(entity);
            transaction.commit();
            return true;
        } catch (Exception e) {
            e.getMessage();
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return false;
    }

    /*
     * search by entity id (primary key)
     * @param class entity
     * @param key
     * @return entity
     */
    public T findById(Class<T> entity, Object key) {
        try {
            session = HibernateUtils.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            return session.find(entity, key);
        } catch (NullPointerException e) {
            e.getMessage();
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return null;
    }

    /*
     * Maximum value of properties in entity
     * @param class entity
     * @param properties
     * @return entity
     */
    public Integer getMaxValue(Class<T> entity, String properties) {
        try {
            session = HibernateUtils.getSessionFactory().openSession();
            CriteriaBuilder builder = session.getCriteriaBuilder();

            CriteriaQuery<Integer> criteriaQuery = builder.createQuery(Integer.class);
            Root<T> root = criteriaQuery.from(entity);
            criteriaQuery.select(builder.max(root.get(properties)));

            Query<Integer> query = session.createQuery(criteriaQuery);
            int maxValue = query.getSingleResult();

            return maxValue;
        } finally {
            session.close();
        }
    }

    /*
     * Find the object by value of an entity
     * @param entity
     * @param object
     * @param properties of object
     * @param value is properties
     * @return entity
     */
    public List<T> findByObjectValue(Class<T> entity, String object, String properties, Object value) {
        try {
            session = HibernateUtils.getSessionFactory().openSession();
            CriteriaBuilder builder = session.getCriteriaBuilder();

            CriteriaQuery<T> criteriaQuery = builder.createQuery(entity);
            Root<T> importSlipDetailRoot = criteriaQuery.from(entity);
            criteriaQuery.select(importSlipDetailRoot);
            criteriaQuery.where(builder.equal(importSlipDetailRoot.get(object).get(properties), value));

            Query<T> query = session.createQuery(criteriaQuery);

            List<T> result = query.getResultList();

            return result;
        } catch (Exception e) {
            e.getMessage();
        } finally {
            session.close();
        }
        return null;
    }

}
