package Persistencia;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

import Persistencia.User;

public class UserDAO {


    private static SessionFactory factory;
    private static ServiceRegistry serviceRegistry;

    public UserDAO(String manejador) {
        System.err.println("Iniciando");
        try {
            Configuration configuration = new Configuration();
            System.err.println("Leyendo configuracion.");
            configuration.configure(manejador);
            serviceRegistry = new ServiceRegistryBuilder().applySettings(configuration.getProperties()).buildServiceRegistry();
            factory = configuration.buildSessionFactory(serviceRegistry);
        } catch (Throwable ex) {
            System.err.println("No se puede crear la Sesion manejador 1" + ex);
            throw new ExceptionInInitializerError(ex);
        }

    }

    public List getUsers(){
        Session session=factory.openSession();
        Criteria cr= session.createCriteria(User.class);
        List empList1= cr.list();
        return empList1;
    }


}