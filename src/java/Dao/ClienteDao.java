/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Modelo.Cliente;
import Modelo.Produto;
import Util.HibernateUtil;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporter;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.hibernate.Session;
import org.primefaces.model.DefaultStreamedContent;

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

    public List<Cliente> listar() {
        session = HibernateUtil.getSessionFactory().openSession();

        try {
            session.beginTransaction();
            List<Cliente> lista = session.createQuery("from Cliente").list();
            session.getTransaction().commit();
            return lista;
        } finally {
            session.close();

        }
    }

    public DefaultStreamedContent emitir() throws JRException {

        System.out.println("GERANDO RELATORIO...");

        List<Cliente> lista = listar();
        List<Produto> lista2 = new ProdutoDao().listar();
        
        Map parametros = new HashMap();
        parametros.put("listaProduto", lista2);
        
        InputStream relatorioStream = this.getClass().getResourceAsStream("/relatorio/report1.jrxml");
        JasperReport report = JasperCompileManager.compileReport(relatorioStream);
        JasperPrint print = JasperFillManager.fillReport(report, parametros, new JRBeanCollectionDataSource(lista));

        ByteArrayOutputStream Teste = new ByteArrayOutputStream();
        JRExporter exporter = new net.sf.jasperreports.engine.export.JRPdfExporter();
        //JRExporter exporter = new net.sf.jasperreports.engine.export.JRHtmlExporter();
        //JRExporter exporter = new net.sf.jasperreports.engine.export.JRXlsExporter();
        //JRExporter exporter = new net.sf.jasperreports.engine.export.JRXmlExporter();
        //JRExporter exporter = new net.sf.jasperreports.engine.export.JRCsvExporter();

        //exporter.setParameter(JRExporterParameter.OUTPUT_FILE_NAME, pdfFile);
        exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, Teste);
        exporter.setParameter(JRExporterParameter.JASPER_PRINT, print);
        exporter.exportReport();

        return new DefaultStreamedContent(new ByteArrayInputStream(Teste.toByteArray()), "application/pdf", "Cadastro de Clientes.pdf");

    }

    public Cliente buscarPorId(int id) {
        session = HibernateUtil.getSessionFactory().openSession();

        try {
            session.beginTransaction();
            Cliente cliente = (Cliente) session.createQuery("from Cliente where Id=:id")
                    .setInteger("id", id)
                    .uniqueResult();
            session.getTransaction().commit();
            return cliente;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            session.close();

        }
    }
}
