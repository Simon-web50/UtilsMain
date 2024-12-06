package OfdToPdf;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class CDPToPDFConverter {

    public static void main(String[] args) {
        String cdpFilePath = "C:\\Users\\12416\\Desktop\\62015001\\62015001-JC001-0-0.CDP";
        String pdfFilePath = "C:\\Users\\12416\\Desktop\\62015001\\62015001-JC001-0-0.pdf";
        String charset = "GBK"; // 根据CDP文件的实际编码来设置

        try (PdfWriter writer = new PdfWriter(pdfFilePath);
             PdfDocument pdfDoc = new PdfDocument(writer);
             Document document = new Document(pdfDoc);
             BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(cdpFilePath), StandardCharsets.UTF_8))) {

            String line;
            while ((line = reader.readLine()) != null) {
                document.add(new Paragraph(line)); // 将读取的每一行添加到PDF文档中
            }

            document.close(); // 关闭Document对象，以完成PDF的创建

            System.out.println("PDF file created successfully!");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}