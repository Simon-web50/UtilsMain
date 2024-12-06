package WordToPdf;

import com.documents4j.api.DocumentType;
import com.documents4j.api.IConverter;
import com.documents4j.job.LocalConverter;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

public class WordConverterPdfUtils {
    public static void converterToPdf(String wordPath, String pdfPath) {
        try {
            InputStream inputStream = new FileInputStream(wordPath);
            OutputStream outputStream = new FileOutputStream(pdfPath);
            IConverter converter = LocalConverter.builder().build();
            // 判断文档类型
            if (wordPath.contains(".doc")) {
                converter.convert(inputStream).as(DocumentType.DOC).to(outputStream).as(DocumentType.PDF).execute();
            } else {
                converter.convert(inputStream).as(DocumentType.DOCX).to(outputStream).as(DocumentType.PDF).execute();
            }
            // 最后关闭流
            outputStream.close();
            inputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
