package com.example.PDFBox.service;

import com.lowagie.text.*;
import com.lowagie.text.pdf.PdfWriter;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Service
public class PDFGenerateService {
    public void export(HttpServletResponse response) throws IOException {
        Document document = new Document((PageSize.A4));
        PdfWriter.getInstance(document, response.getOutputStream());

        document.open();

        Font fontTitle = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        fontTitle.setSize(20);

        Paragraph para = new Paragraph("This is TITLE",fontTitle);
        para.setAlignment(Paragraph.ALIGN_CENTER);

        Font fontParagraph = FontFactory.getFont(FontFactory.HELVETICA);
        fontParagraph.setSize(12);

        Paragraph para2 = new Paragraph("This is a paragraph.", fontParagraph);
        para2.setAlignment(Paragraph.ALIGN_LEFT);

        document.add(para);
        document.add(para2);
        document.close();

    }
}
