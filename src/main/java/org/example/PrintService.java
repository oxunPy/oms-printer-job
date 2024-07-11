package org.example;

import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRPrintServiceExporter;
import net.sf.jasperreports.engine.export.JRPrintServiceExporterParameter;

import javax.print.DocFlavor;
import javax.print.PrintServiceLookup;
import javax.print.attribute.AttributeSet;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.standard.Copies;
import java.awt.print.PrinterJob;
import java.io.IOException;
import java.util.*;

public class PrintService {

    public List<OsmTicket> fillOsmKMTicketsToPrint(int nCopy) throws IOException {
        List<OsmTicket> result = new ArrayList<>();
//        for(int i = 0; i < nCopy; i ++ ) {
//            OsmTicket ticket = new OsmTicket();
//            ticket.setKmCode(GenerateQrCode.generateQRCode("010478011659005821kB-QUr=i,vEnoWY;4DtL 91UZF0 92Si9IMc9w3xNbJ63Znq/jSvI1NQYUww/ozYmbl9HVX00="));
//            ticket.setAbIcon(this.getClass().getResourceAsStream("/asl_belgi.png"));
//            ticket.setGoodName("Настенный газовый двухконтурная котел Airfel Digifel Duo 18 KW");
//            ticket.setCountry("Турция");
//            ticket.setKm("010478011659005821kB-QUr=i,vEnoWY;4DtL 91UZF0 92Si9IMc9w3xNbJ63Znq/jSvI1NQYUww/ozYmbl9HVX00=");
//            result.add(ticket);
//        }

        OsmTicket ticket = new OsmTicket();
        ticket.setKmCode(GenerateQrCode.generateQRCode("010478011659005821kB-QUr=i,vEnoWY;4DtL 91UZF0 92Si9IMc9w3xNbJ63Znq/jSvI1NQYUww/ozYmbl9HVX00="));
        ticket.setAbIcon(this.getClass().getResourceAsStream("/asl_belgi.png"));
        ticket.setGoodName("Настенный газовый двухконтурная котел Airfel Digifel Duo 18 KW");
        ticket.setCountry("Турция");
        ticket.setKm("010478011659005821kB-QUr=i,vEnoWY;4DtL 91UZF0 92Si9IMc9w3xNbJ63Znq/jSvI1NQYUww/ozYmbl9HVX00=");
        result.add(ticket);

        OsmTicket ticket1 = new OsmTicket();
        ticket1.setKmCode(GenerateQrCode.generateQRCode("010478011659008921FjdMUYZ4;1eXmPq:hDWQ\\u001D91UZF0\\u001D92aU1NOdN6mq3tTkiYgTG6VuMemzSiXbT2jliGeMu7xn4="));
        ticket1.setAbIcon(this.getClass().getResourceAsStream("/asl_belgi.png"));
        ticket1.setGoodName("AIRFEL - 120 HEATPRO");
        ticket1.setCountry("Turkie");
        ticket1.setKm("010478011659008921FjdMUYZ4;1eXmPq:hDWQ\\u001D91UZF0\\u001D92aU1NOdN6mq3tTkiYgTG6VuMemzSiXbT2jliGeMu7xn4=");
        result.add(ticket1);

        OsmTicket ticket2 = new OsmTicket();
        ticket2.setKmCode(GenerateQrCode.generateQRCode("0104780116590089214MgG,KAEnytoC7n?_mI'\\u001D91UZF0\\u001D92dWZvdGfFLKbl2cnxJ3z3WpdlPxeGxTdPDkd166nHV08="));
        ticket2.setAbIcon(this.getClass().getResourceAsStream("/asl_belgi.png"));
        ticket2.setGoodName("AIRFEL - 120 HEATPRO");
        ticket2.setCountry("Turkie");
        ticket2.setKm("0104780116590089214MgG,KAEnytoC7n?_mI'\\u001D91UZF0\\u001D92dWZvdGfFLKbl2cnxJ3z3WpdlPxeGxTdPDkd166nHV08=");
        result.add(ticket2);

        OsmTicket ticket3 = new OsmTicket();
        ticket3.setKmCode(GenerateQrCode.generateQRCode("010478011659008921-(dIqpqalaA,tUOQ2!Xi\\u001D91UZF0\\u001D92Q0QrNOZI4F0qeR8rvShHWjGQmjINPTo8KvwiNETW2Zc=\n"));
        ticket3.setAbIcon(this.getClass().getResourceAsStream("/asl_belgi.png"));
        ticket3.setGoodName("AIRFEL - 120 HEATPRO");
        ticket3.setCountry("Turkie");
        ticket3.setKm("0104780116590089214MgG,KAEnytoC7n?_mI'\\u001D91UZF0\\u001D92dWZvdGfFLKbl2cnxJ3z3WpdlPxeGxTdPDkd166nHV08=");
        result.add(ticket3);

        return result;
    }


    public JasperPrint printActionNCopy(int nCopy) throws IOException {
        JasperReport jrReport = ReportUtilNative.getReport("AB_km");

        Map<String, Object> propertyMap = new HashMap<>();
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(fillOsmKMTicketsToPrint(nCopy));

        JasperPrint jrPrint = null;
        try {
            jrPrint = PrintService.createJasperPrint(jrReport, propertyMap, dataSource);
        } catch (JRException e) {
            e.printStackTrace();
        }
        return jrPrint;
    }


    /*########################*/

    public static JasperPrint createJasperPrint(JasperReport report, Map<String, Object> properties, JRDataSource dataSource) throws JRException {
        return JasperFillManager.fillReport(report, properties, dataSource);
    }

    public static void printQuitely(JasperPrint jasperPrint, String printerName) throws JRException {
        PrinterJob printerJob = PrinterJob.getPrinterJob();
        javax.print.PrintService printService = printerJob.getPrintService();
        javax.print.PrintService[] services = PrintServiceLookup.lookupPrintServices((DocFlavor)null, (AttributeSet)null);
        javax.print.PrintService xp_365b = null;
        javax.print.PrintService[] var6 = services;
        int var7 = services.length;

        for(int var8 = 0; var8 < var7; ++var8) {
            javax.print.PrintService service = var6[var8];
            if (service.getName().equals(printerName)) {
                printService = service;
                break;
            }
        }

        PrintRequestAttributeSet printRequestAttributeSet = new HashPrintRequestAttributeSet();
        printRequestAttributeSet.add(new Copies(printerJob.getCopies()));
        JRPrintServiceExporter exporter = new JRPrintServiceExporter();
        exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
        exporter.setParameter(JRPrintServiceExporterParameter.PRINT_SERVICE, printService);
        exporter.setParameter(JRPrintServiceExporterParameter.PRINT_SERVICE_ATTRIBUTE_SET, printService.getAttributes());
        exporter.setParameter(JRPrintServiceExporterParameter.PRINT_REQUEST_ATTRIBUTE_SET, printRequestAttributeSet);
        exporter.setParameter(JRPrintServiceExporterParameter.DISPLAY_PAGE_DIALOG, Boolean.FALSE);
        exporter.setParameter(JRPrintServiceExporterParameter.DISPLAY_PRINT_DIALOG, Boolean.FALSE);
        exporter.exportReport();
    }

    public static void printQuitely(JasperPrint jasperPrint) throws JRException {
        PrinterJob printerJob = PrinterJob.getPrinterJob();

        if (printerJob.printDialog()) {
            javax.print.PrintService printService = printerJob.getPrintService();
//            javax.print.PrintService printService = PrintServiceLookup.lookupDefaultPrintService();

            JRPrintServiceExporter exporter;
            PrintRequestAttributeSet printRequestAttributeSet = new HashPrintRequestAttributeSet();
            printRequestAttributeSet.add(new Copies(printerJob.getCopies()));

            // these are deprecated
            exporter = new JRPrintServiceExporter();
            exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
            exporter.setParameter(JRPrintServiceExporterParameter.PRINT_SERVICE, printService);
            exporter.setParameter(JRPrintServiceExporterParameter.PRINT_SERVICE_ATTRIBUTE_SET, printService.getAttributes());
            exporter.setParameter(JRPrintServiceExporterParameter.PRINT_REQUEST_ATTRIBUTE_SET, printRequestAttributeSet);
            exporter.setParameter(JRPrintServiceExporterParameter.DISPLAY_PAGE_DIALOG, Boolean.FALSE);
            exporter.setParameter(JRPrintServiceExporterParameter.DISPLAY_PRINT_DIALOG, Boolean.FALSE);
            exporter.exportReport();
        }
    }

    public static void printDefaultQuitely(JasperPrint jasperPrint) throws JRException {
        PrinterJob printerJob = PrinterJob.getPrinterJob();

        javax.print.PrintService printService = PrintServiceLookup.lookupDefaultPrintService();

        JRPrintServiceExporter exporter;
        PrintRequestAttributeSet printRequestAttributeSet = new HashPrintRequestAttributeSet();
        printRequestAttributeSet.add(new Copies(printerJob.getCopies()));

        // these are deprecated
        exporter = new JRPrintServiceExporter();
        exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
        exporter.setParameter(JRPrintServiceExporterParameter.PRINT_SERVICE, printService);
        exporter.setParameter(JRPrintServiceExporterParameter.PRINT_SERVICE_ATTRIBUTE_SET, printService.getAttributes());
        exporter.setParameter(JRPrintServiceExporterParameter.PRINT_REQUEST_ATTRIBUTE_SET, printRequestAttributeSet);
        exporter.setParameter(JRPrintServiceExporterParameter.PRINT_REQUEST_ATTRIBUTE_SET, printRequestAttributeSet);
        exporter.setParameter(JRPrintServiceExporterParameter.DISPLAY_PAGE_DIALOG, Boolean.FALSE);
        exporter.setParameter(JRPrintServiceExporterParameter.DISPLAY_PRINT_DIALOG, Boolean.FALSE);
        exporter.exportReport();
//        }
    }

}
