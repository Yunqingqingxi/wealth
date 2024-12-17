package com.example.wealth.common.utils;

import android.content.Context;
import android.util.Log;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.canvas.parser.PdfTextExtractor;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class DocumentConvertUtils {
    private static final String TAG = "DocumentConvertUtils";

    /**
     * Word转PDF
     * @param context 上下文
     * @param wordFile Word文件
     * @return PDF文件
     */
    public static File wordToPdf(Context context, File wordFile) {
        try {
            // 读取Word文档
            XWPFDocument wordDocument = new XWPFDocument(new FileInputStream(wordFile));
            
            // 创建输出PDF文件
            File pdfFile = new File(context.getCacheDir(), 
                    wordFile.getName().replaceAll("\\.docx?$", ".pdf"));
            
            // 创建PDF文档
            PdfWriter writer = new PdfWriter(new FileOutputStream(pdfFile));
            PdfDocument pdf = new PdfDocument(writer);
            Document document = new Document(pdf);
            
            // 转换段落
            for (XWPFParagraph paragraph : wordDocument.getParagraphs()) {
                document.add(new Paragraph(paragraph.getText()));
            }
            
            document.close();
            wordDocument.close();
            
            return pdfFile;
        } catch (Exception e) {
            Log.e(TAG, "Word转PDF失败: " + e.getMessage());
            return null;
        }
    }

    /**
     * PDF转Word
     * @param context 上下文
     * @param pdfFile PDF文件
     * @return Word文件
     */
    public static File pdfToWord(Context context, File pdfFile) {
        try {
            // 创建输出Word文件
            File wordFile = new File(context.getCacheDir(), 
                    pdfFile.getName().replaceAll("\\.pdf$", ".docx"));
            
            // 读取PDF文档
            PdfDocument pdf = new PdfDocument(new com.itextpdf.kernel.pdf.PdfReader(pdfFile));
            
            // 创建Word文档
            XWPFDocument document = new XWPFDocument();
            
            // 提取文本并写入Word文档
            int numberOfPages = pdf.getNumberOfPages();
            for (int i = 1; i <= numberOfPages; i++) {
                String text = PdfTextExtractor.getTextFromPage(pdf.getPage(i));
                XWPFParagraph paragraph = document.createParagraph();
                XWPFRun run = paragraph.createRun();
                run.setText(text);
            }
            
            // 保存文件
            FileOutputStream out = new FileOutputStream(wordFile);
            document.write(out);
            out.close();
            document.close();
            pdf.close();
            
            return wordFile;
        } catch (Exception e) {
            Log.e(TAG, "PDF转Word失败: " + e.getMessage());
            return null;
        }
    }

    /**
     * 获取文件MIME类型
     */
    public static String getMimeType(File file) {
        String name = file.getName().toLowerCase();
        if (name.endsWith(".pdf")) {
            return "application/pdf";
        } else if (name.endsWith(".doc")) {
            return "application/msword";
        } else if (name.endsWith(".docx")) {
            return "application/vnd.openxmlformats-officedocument.wordprocessingml.document";
        }
        return "*/*";
    }
} 