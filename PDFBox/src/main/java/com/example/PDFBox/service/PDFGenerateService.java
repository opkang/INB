package com.example.PDFBox.service;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType0Font;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Service
public class PDFGenerateService {
    public void export(HttpServletResponse response) throws IOException {
        PDDocument document = new PDDocument();
        PDPage firstPage = new PDPage();
        document.addPage(firstPage);

        PDPageContentStream contentStream = new PDPageContentStream(document,firstPage);
        contentStream.beginText();
        contentStream.setFont(PDType1Font.TIMES_BOLD,20);
        contentStream.setLeading(16.0f);

        contentStream.newLineAtOffset(25,firstPage.getTrimBox().getHeight()-25);


        String[] text = {
                "This is line 1",
                "this is line 2"};

        for(String i : text){
            contentStream.showText(i);
            contentStream.newLine();
        }

        contentStream.endText();
        contentStream.close();


    }
}
