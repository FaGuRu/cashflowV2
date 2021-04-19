package persistencia;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

import java.util.List;

public class CashFlowDAO {
    private static SessionFactory factory;
    private static ServiceRegistry serviceRegistry;

    public CashFlowDAO(String manejador) {
        System.err.println("Iniciando");
        try {
            Configuration configuration = new Configuration();
            System.err.println("Leyendo configuracion.");
            configuration.configure(manejador);
            serviceRegistry = new ServiceRegistryBuilder().applySettings(configuration.getProperties()).buildServiceRegistry();
            factory = configuration.buildSessionFactory(serviceRegistry);
            System.out.println("Exito");
        } catch (Throwable ex) {
            System.err.println("No se puede crear la Sesion manejador 1" + ex);
            throw new ExceptionInInitializerError(ex);
        }

    }

    public void addCashFlow(CashFlow c1)
    {
        Session session=factory.openSession();
        session.beginTransaction();
        session.save(c1);

        session.getTransaction().commit();
        session.close();
    }

    public List getCashFlow(){
        Session session=factory.openSession();
        Criteria cr= session.createCriteria(CashFlow.class);
        List empList1= cr.list();
        return empList1;
    }

    public List getCashFlowType(String type){
        Session session=factory.openSession();
        Criteria cr= session.createCriteria(CashFlow.class);
        cr.add(Restrictions.eq("type",type));
        List empList1= cr.list();
        return empList1;
    }



}
