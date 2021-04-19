package persistencia;

import com.sun.javafx.scene.web.Printable;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

import java.sql.*;

import java.util.ArrayList;
import java.util.List;


public class RecordDAO {

    private static SessionFactory factory;
    private static ServiceRegistry serviceRegistry;

    public RecordDAO(String manejador) {
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

    public void addRecord(Record c1)
    {
        Session session=factory.openSession();
        session.beginTransaction();
        session.save(c1);

        session.getTransaction().commit();
        session.close();
    }

    public List getRecord(){
        Session session=factory.openSession();
        Criteria cr= session.createCriteria(Record.class);
        List empList1= cr.list();
        return empList1;
    }

    public List getMonthRecord(int numberMonth, String _type)
    {
        String sql = "SELECT company_name,\n"+
                "SUM(CASE WHEN week_num=1 THEN Amount END) as week1,\n"+
                "SUM(CASE WHEN week_num=2 THEN Amount END) as week2,\n"+
                "SUM(CASE WHEN week_num=3 THEN Amount END) as week3,\n"+
                "SUM(CASE WHEN week_num=4 THEN Amount END) as week4,\n"+
                "SUM(CASE WHEN week_num=5 THEN Amount END) as week5\n"+
                "FROM record\n"+
                "WHERE type= '"+_type +"' AND MONTH(month)= "+numberMonth+"\n"+
                "GROUP BY company_name\n";

        Connection connection = SQLConnection.getConnection();
        List<PrintableRecord> Records = new ArrayList<>();
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
                PrintableRecord record = new PrintableRecord(company_name,week1,week2,week3,week4,week5);
                Records.add(record);
            }

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        System.out.println(Records);
        return Records;

    }





}
