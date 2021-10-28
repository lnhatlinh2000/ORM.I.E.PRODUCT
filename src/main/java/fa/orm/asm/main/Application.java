package fa.orm.asm.main;

import fa.orm.asm.hibernate.HibernateUtils;

public class Application {
    public static void main(String[] args) {
        HibernateUtils.getSessionFactory();
    }
}
