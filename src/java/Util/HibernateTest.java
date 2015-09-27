/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Util;

import Model.Cliente;
import org.hibernate.Session;
import org.hibernate.Transaction;
/**
 *
 * @author MATHEUS
 */
public class HibernateTest {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        Cliente c = new Cliente();
        c.setNome("Matheus");
        c.setCidade("Fortaleza");
        
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction t = session.beginTransaction();
        session.save(c);
        t.commit();
        
        session.close();
    }
    
}
