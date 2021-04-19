package persistencia;

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

    public List getMonthRecord(int numberMonth,int week_num, String _type)
    {
        String sql = "SELECT id, week_num, type, month, company_name, amount FROM record\n"+
                "WHERE week_num ="+ week_num +"AND MONTH(month)="+ numberMonth + "AND type="+ _type+"\n"+
                "ORDER BY company_name";


        Connection connection = SQLConnection.getConnection();
        List<Record> Records = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet results = statement.executeQuery();
            while (results.next()) {
                int id = results.getInt(1);
                String type = results.getString(2);
                int week = results.getInt(3);
                Date month = results.getDate(4);
                String companyName = results.getString(5);
                float amount = results.getFloat(6);
                Record record = new Record(id, type, week, month, companyName, amount);
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
