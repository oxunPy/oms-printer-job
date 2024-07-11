package org.example;

import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.util.JRLoader;
import org.apache.commons.io.IOUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.file.Files;

public class ReportUtilNative {
    private static Log logger = LogFactory.getLog(ReportUtilNative.class);

    private final static String USER_REPORT_DIR = System.getProperty("user.dir") + "/printerlayouts/";
    public final static String DEFAULT_REPORT_DIR = ReportUtilNative.class.getResource("/jrxml/").getPath();
    private final static String PREFIX_DIR = "/jrxml/";
    public static String USER_BARCODES_DIR = System.getProperty("user.dir") + "/barcodes/";


    public static JasperReport getReport(String folder, String reportName) {
        try {

            InputStream in = ReportUtilNative.class.getResourceAsStream(PREFIX_DIR + (StringUtil.notEmpty(folder) ? folder + "/" : "") + reportName + ".jasper");
            File file = new File(USER_REPORT_DIR + (StringUtil.notEmpty(folder) ? folder + "/" : "") + reportName + ".jasper");

//            System.out.println("##Res-JASPER_FILE>>" + (in != null || file.exists()));

//            if (in != null) return getDefaultReport(folder, reportName);
            if (file.exists()) return getUserReport(folder, reportName);
            return compileReport(folder, reportName);
        } catch (Exception e) {
            logger.error("Could not load report " + reportName);
            e.printStackTrace();
            return null;
        }
    }

    public static JasperReport getReportByFullPath(String reportFullPathName, String reportName){

//        String reportName = getReportNameFromFullPath(reportFullPathName);
        File reportFile = new File(USER_REPORT_DIR + reportName + ".jasper");
        if(reportFile.exists()){
            return getUserReport(reportName);
        }

        InputStream in = null;
        try {
            in = ReportUtilNative.class.getResourceAsStream(reportFullPathName);
            return compileReportByInputStream(reportName, in);
        } catch (Exception e) {
            e.printStackTrace();
        }
        throw new NullPointerException();
    }

    private static JasperReport compileReport(String folder, String reportName) throws Exception {
        InputStream in = null;
        InputStream in2 = null;
        FileOutputStream out = null;
        File jasperFile = null;

        try {
            in = ReportUtilNative.class.getResourceAsStream(PREFIX_DIR + (StringUtil.notEmpty(folder) ? folder + "/" : "") + reportName + ".jrxml");

            if (in == null) return null;

            File dir = new File(USER_REPORT_DIR + (StringUtil.notEmpty(folder) ? folder + "/" : ""));


            if (!dir.exists()) dir.mkdirs();

            jasperFile = new File(dir, reportName + ".jasper");

            out = new FileOutputStream(jasperFile);
            JasperCompileManager.compileReportToStream(in, out);

            in2 = Files.newInputStream(jasperFile.toPath());
            return (JasperReport) JRLoader.loadObject(in2);
        } catch (Exception e) {
            IOUtils.closeQuietly(out);
            if (jasperFile != null) jasperFile.delete();
            e.printStackTrace();
            throw e;
        } finally {
            IOUtils.closeQuietly(in);
            IOUtils.closeQuietly(in2);
            IOUtils.closeQuietly(out);
        }
    }

    private static JasperReport getDefaultReport(String folder, String reportName) {
        InputStream resource = null;

        try {
            resource = ReportUtilNative.class.getResourceAsStream(PREFIX_DIR + (StringUtil.notEmpty(folder) ? folder + "/" : "") + reportName + ".jasper");
            return (JasperReport) JRLoader.loadObject(resource);
        } catch (Exception e) {
            logger.error(e);
            return null;
        } finally {
            IOUtils.closeQuietly(resource);
        }
    }

    private static JasperReport getUserReport(String folder, String reportName) {
        InputStream resource = null;

        try {
            resource = Files.newInputStream(new File(USER_REPORT_DIR + (StringUtil.notEmpty(folder) ? folder + "/" : "") + reportName + ".jasper").toPath());
            return (JasperReport) JRLoader.loadObject(resource);
        } catch (Exception e) {
            logger.error(e);
            return null;
        } finally {
            IOUtils.closeQuietly(resource);
        }
    }

    public static JasperReport getUserReport(String reportName) {
        InputStream resource = null;

        try {
            resource = Files.newInputStream(new File(USER_REPORT_DIR + reportName + ".jasper").toPath());
            return (JasperReport) JRLoader.loadObject(resource);
        } catch (Exception e) {
            logger.error(e);
            return null;
        } finally {
            IOUtils.closeQuietly(resource);
        }
    }

    public static InputStream extractPrintImageAsInputStream(JasperPrint jasperPrint, int pageIndex, float zoom) {
        final String extension = "jpg";
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        InputStream is = null;
        try{
            BufferedImage image = (BufferedImage) JasperPrintManager.printPageToImage(jasperPrint, pageIndex, zoom);
            ImageIO.write(image, extension, byteArrayOutputStream);         //write image to outputStream
            is = new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
        } catch (IOException | JRException e) {
            e.printStackTrace();
        }
        return is;
    }

    private static JasperReport compileReportByInputStream(String reportName, InputStream in) throws Exception {
        InputStream outputInputStream = null;
        FileOutputStream out = null;
        File jasperFile = null;

        if(in == null) return null;

        try{
            File dir = new File(USER_REPORT_DIR);
            if(!dir.exists()) dir.mkdirs();

            jasperFile = new File(dir, reportName + ".jasper");
            out = new FileOutputStream(jasperFile);
            JasperCompileManager.compileReportToStream(in, out);        // writing into jasperFile from inputStream with outputStream

            outputInputStream = Files.newInputStream(jasperFile.toPath());
            return (JasperReport) JRLoader.loadObject(outputInputStream);

        } catch (Exception e) {
            IOUtils.closeQuietly(out);
            if(jasperFile != null) jasperFile.delete();
            e.printStackTrace();
            throw e;
        }finally{
            IOUtils.closeQuietly(in);
            IOUtils.closeQuietly(outputInputStream);
            IOUtils.closeQuietly(out);

        }
    }

    private static JasperReport compileReport(String reportName) throws Exception {
        InputStream in = ReportUtilNative.class.getResourceAsStream((PREFIX_DIR) + reportName + ".jrxml");
        return compileReportByInputStream(reportName, in);
    }

    public static JasperReport getReport(String reportName) {
        try {
            File file = new File(USER_REPORT_DIR + reportName + ".jasper");
            if (file.exists()) return getUserReport(reportName);
            return compileReport(reportName);
        } catch (Exception e) {
            logger.error("Could not load report " + reportName);
            e.printStackTrace();
            return null;
        }
    }
}
