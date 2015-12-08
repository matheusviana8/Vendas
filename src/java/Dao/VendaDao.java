/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Model.Venda;
import Util.HibernateUtil;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.List;
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
public class VendaDao {

    private Session session;

    public void inserir(Venda venda) {
        session = HibernateUtil.getSessionFactory().openSession();

        try {
            session.beginTransaction();
            session.save(venda);
            session.getTransaction().commit();

        } finally {
            session.close();

        }
    }

    public List<Venda> listar() {
        session = HibernateUtil.getSessionFactory().openSession();

        try {
            session.beginTransaction();
            List<Venda> lista = session.createQuery("from Venda").list();
            session.getTransaction().commit();
            return lista;
        } finally {
            session.close();

        }
    }

    public DefaultStreamedContent emitir() throws JRException {

        System.out.println("GERANDO RELATORIO...");

        List<Venda> lista = listar();
        InputStream relatorioStream = this.getClass().getResourceAsStream("/relatorio/Venda.jrxml");
        JasperReport report = JasperCompileManager.compileReport(relatorioStream);
        JasperPrint print = JasperFillManager.fillReport(report, null, new JRBeanCollectionDataSource(lista));
        
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

        return new DefaultStreamedContent(new ByteArrayInputStream(Teste.toByteArray()),"application/pdf", "Cadastro de Vendas.pdf");
      
    }
}
