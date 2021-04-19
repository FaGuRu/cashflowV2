package persistencia;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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

    public List getCashFlowEntrada(int numberMonth)
    {
        String sql = "SELECT concept,\n"+
                "SUM(CASE WHEN week_num=1 THEN amount END) as week1,\n"+
                "SUM(CASE WHEN week_num=2 THEN amount END) as week2,\n"+
                "SUM(CASE WHEN week_num=3 THEN amount END) as week3,\n"+
                "SUM(CASE WHEN week_num=4 THEN amount END) as week4,\n"+
                "SUM(CASE WHEN week_num=5 THEN amount END) as week5\n"+
                "FROM cashflow\n"+
                "where type = 'Entrada' and EXTRACT(MONTH FROM date) ='" + numberMonth + "'\n"+
                "GROUP BY concept";

        Connection connection = SQLConnection.getConnection();
        List<PrintableFlow> Flows = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet results = statement.executeQuery();
            while (results.next()) {
                String company_name = results.getString(1);
                float week1 = results.getFloat(2);
                float week2 = results.getFloat(3);
                float week3 = results.getFloat(4);
                float week4 = results.getFloat(5);
                float week5 = results.getFloat(6);
                PrintableFlow flow = new PrintableFlow(company_name,week1,week2,week3,week4,week5);
                Flows.add(flow);
            }

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        System.out.println(Flows);
        return Flows;
    }

    public List getCashFlowSalida(int numberMonth)
    {
        String sql = "SELECT category.classification,\n"+
                "SUM(CASE WHEN week_num=1 THEN amount END) as week1,\n"+
                "SUM(CASE WHEN week_num=2 THEN amount END) as week2,\n"+
                "SUM(CASE WHEN week_num=3 THEN amount END) as week3,\n"+
                "SUM(CASE WHEN week_num=4 THEN amount END) as week4,\n"+
                "SUM(CASE WHEN week_num=5 THEN amount END) as week5\n"+
                "FROM cashflow join category on category.idcategory  = cashflow.category\n"+
                "where type = 'Salida' and EXTRACT(MONTH FROM date) ='" + numberMonth + "'\n"+
                "GROUP BY category.classification";


        Connection connection = SQLConnection.getConnection();
        List<PrintableFlow> Flows = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet results = statement.executeQuery();
            while (results.next()) {
                String company_name = results.getString(1);
                float week1 = results.getFloat(2);
                float week2 = results.getFloat(3);
                float week3 = results.getFloat(4);
                float week4 = results.getFloat(5);
                float week5 = results.getFloat(6);
                PrintableFlow flow = new PrintableFlow(company_name,week1,week2,week3,week4,week5);
                Flows.add(flow);
            }

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        System.out.println(Flows);
        return Flows;
    }



}
