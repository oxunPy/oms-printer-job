package org.example;

import com.sun.org.apache.regexp.internal.REProgram;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.ExporterFilter;
import net.sf.jasperreports.engine.export.HtmlExporter;
import net.sf.jasperreports.engine.export.JsonExporter;
import net.sf.jasperreports.export.*;
import net.sf.jasperreports.export.type.HtmlBorderCollapseEnum;
import net.sf.jasperreports.export.type.HtmlSizeUnitEnum;
import org.apache.commons.io.IOUtils;

import java.io.*;
import java.nio.file.Files;

public class Main {
    public static void main(String[] args) throws IOException, JRException {

        PrintService printService = new PrintService();
        JasperPrint jrPrint = printService.printActionNCopy(10);
//        PrintService.printQuitely(jrPrint);
        tryToExportHtml(jrPrint);
//        tryToExportPdf(jrPrint);

        // create img
        InputStream imgIS = ReportUtilNative.extractPrintImageAsInputStream(jrPrint, 0, 2.5f);
        File targetFile = new File("C:/MY_PROJECTS/osm_printer_job/test.jpg");
        OutputStream outStream = Files.newOutputStream(targetFile.toPath());

        byte[] buffer = new byte[8 * 1024];
        int bytesRead;
        while ((bytesRead = imgIS.read(buffer)) != -1) {
            outStream.write(buffer, 0, bytesRead);
        }
        IOUtils.closeQuietly(imgIS);
        IOUtils.closeQuietly(outStream);

    }

    private static void tryToExportHtml(JasperPrint print) {
        try {
            HtmlExporter exporter = new HtmlExporter();


            File reportHtml = new File(System.getProperty("user.dir") + File.separator + "report.html");
            SimpleHtmlExporterOutput htmlOutput = new SimpleHtmlExporterOutput(reportHtml);
            exporter.setExporterOutput(htmlOutput);

            SimpleHtmlReportConfiguration reportConfig = new SimpleHtmlReportConfiguration();
            reportConfig.setSizeUnit(HtmlSizeUnitEnum.POINT);
            reportConfig.setWhitePageBackground(true); // Example: remove white page background
            reportConfig.setWrapBreakWord(true); // Example: word-wrap long text
            reportConfig.setIgnorePageMargins(true);
            reportConfig.setAccessibleHtml(true);
            reportConfig.setUseBackgroundImageToAlign(true);
//             Set addition configuration if needed

            exporter.setConfiguration(reportConfig);
            exporter.setExporterInput(new SimpleExporterInput(print));
            exporter.exportReport();
        } catch (JRException e) {
            throw new RuntimeException(e);
        }
    }

    private static void tryToExportPdf(JasperPrint print) {
        try {
            JasperExportManager.exportReportToPdfFile(print, "C:/MY_PROJECTS/osm_printer_job/report.pdf");
        } catch (JRException e) {
            throw new RuntimeException(e);
        }
    }


    // this code does not work somehow ?
    private static void tryToExportJson(JasperPrint print) {
        try {
            // Load filled JasperPrint object

            // Configure JSON exporter
            JsonExporter exporter = new JsonExporter();

            // Configure exporter input and output
            exporter.setExporterInput(new SimpleExporterInput(print));
            exporter.setExporterOutput(new SimpleJsonExporterOutput((new File("C:/MY_PROJECTS/osm_printer_job/report.json"))));

            // Configure JSON exporter configuration
            SimpleJsonReportConfiguration reportConfig = new SimpleJsonReportConfiguration();
            SimpleJsonExporterConfiguration exporterConfig = new SimpleJsonExporterConfiguration();

            exporter.setConfiguration(reportConfig);
            exporter.setConfiguration(exporterConfig);

            // Export report to JSON
            exporter.exportReport();
        } catch (JRException e) {
            throw new RuntimeException(e);
        }
    }
}