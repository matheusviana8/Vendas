/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Model.Cliente;
import Util.HibernateUtil;
import org.hibernate.Session;

/**
 *
 * @author MATHEUS
 */
public class ClienteDao {

    private Session session;

    public void inserir(Cliente cliente) {
        session = HibernateUtil.getSessionFactory().openSession();

        try {
            session.beginTransaction();
            session.save(cliente);
            session.getTransaction().commit();

        } finally {
            session.close();

        }
    }
}
