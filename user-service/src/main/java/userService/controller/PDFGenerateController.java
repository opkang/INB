package userService.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import userService.entity.User;
import userService.service.PDFGenerateService;
import userService.service.UserService;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
public class PDFGenerateController {

    @Autowired
    private UserService service;

    @GetMapping("/pdf/generate")
    public void generatePDF(HttpServletResponse response) throws IOException {
        response.setContentType("application/pdf");
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd:hh:mm:ss");
        String current = dateFormat.format(new Date());

        String headerKey = "Content-Disposition";

        String headerValue = "attachment; filename =pdf_" + current + ".pdf";
        response.setHeader(headerKey,headerValue);

        List<User> listUsers = service.listAll();

        PDFGenerateService exporter = new PDFGenerateService(listUsers);

        exporter.export(response);
    }

}
