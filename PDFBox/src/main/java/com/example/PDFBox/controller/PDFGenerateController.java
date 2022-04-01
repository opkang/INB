package com.example.PDFBox.controller;

import com.example.PDFBox.service.PDFGenerateService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class PDFGenerateController {

    private final PDFGenerateService pdfGenerateService;

    public PDFGenerateController(PDFGenerateService pdfGenerateService) {
        this.pdfGenerateService = pdfGenerateService;
    }

    @GetMapping("/pdf/generate")
    public void generatePDF(HttpServletResponse response) throws IOException {
        response.setContentType("application/pdf");
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd:hh:mm:ss");
        String current = dateFormat.format(new Date());

        String headerKey = "Content-Disposition";

        String headerValue = "attachment; filename =pdf_" + current + ".pdf";
        response.setHeader(headerKey,headerValue);

        this.pdfGenerateService.export(response);
    }

}
