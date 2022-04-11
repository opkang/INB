package userService.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.supercsv.io.CsvBeanWriter;
import org.supercsv.io.ICsvBeanWriter;
import org.supercsv.prefs.CsvPreference;
import userService.Interface.IUser;
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
//        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd:hh:mm:ss");
//        String current = dateFormat.format(new Date());

        String headerKey = "Content-Disposition";

        String headerValue = "attachment; filename =User List" + ".pdf";
        response.setHeader(headerKey,headerValue);

        List<User> listUsers = service.listAll();

        PDFGenerateService exporter = new PDFGenerateService(listUsers);

        exporter.exportBox(response);
    }

//    @GetMapping("/pdf/convert")
//    public void convertPDF_CSV(){
//        PDF_Text_CSV ptc = new PDF_Text_CSV("User List");
//    }

    @GetMapping("/pdf/convert")
    public void convertPDF_CSV(HttpServletResponse response) throws IOException{
        response.setContentType("application/octet-stream");
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename =User List" + ".csv";

        response.setHeader(headerKey,headerValue);

            List<User> listUsers = service.listAll();
        ICsvBeanWriter csvWriter=new CsvBeanWriter(response.getWriter(), CsvPreference.STANDARD_PREFERENCE);

        String[] header ={"ID","First Name","Last Name","Email"};
        String[] field_mapping ={"userid","firstname","lastname","email"};

        csvWriter.writeHeader(header);

        for(User user : listUsers){
            csvWriter.write(user,field_mapping);
        }
        csvWriter.close();
//        PDF_Text_CSV exporter = new PDF_Text_CSV(listUsers);
//        PDF_Text_CSV ptc = new PDF_Text_CSV(listUsers);
//        PDF_Text_CSV ptc = new PDF_Text_CSV("User List");
    }

}
