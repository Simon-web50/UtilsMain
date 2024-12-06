package WordToPdf;

public class WordToPdfTest {
    public static void main(String[] args) {
        String wordPath = "C:\\Users\\12416\\Desktop\\Learing\\其他word\\简历新.docx";
        String pdfPath = "C:\\Users\\12416\\Desktop\\Learing\\其他word\\output1.pdf";
        WordConverterPdfUtils.converterToPdf(wordPath, pdfPath);
    }
}
