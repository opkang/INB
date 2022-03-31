package userService.service;


import com.lowagie.text.*;
import com.lowagie.text.Font;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import lombok.extern.slf4j.Slf4j;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.springframework.stereotype.Service;
import userService.controller.PDFGenerateController;
import userService.entity.User;

import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

@Slf4j
public class PDFGenerateService {

    private List<User> listUsers;

    public PDFGenerateService(List<User> ListUser){
        this.listUsers = ListUser;
        log.info("This are User In DB: " + listUsers);
    }

    public void exportBox(HttpServletResponse response) throws IOException{

        PDDocument documentbox = new PDDocument();
        PDPage page = new PDPage();
        documentbox.addPage(page);
        PDPageContentStream contentStream=new PDPageContentStream(documentbox,page);


        contentStream.beginText();
        contentStream.newLineAtOffset(25,page.getTrimBox().getHeight()-25);
        contentStream.setFont(PDType1Font.TIMES_BOLD,25);
        contentStream.setLeading(16.0f);
        contentStream.showText("User List");
        contentStream.newLine();
        contentStream.setFont(PDType1Font.TIMES_ROMAN,10);
        contentStream.setLeading(16.0f);


        for (User user : listUsers) {
            contentStream.showText("ID: "+String.valueOf(user.getUserID()));
            contentStream.newLine();
            contentStream.showText("First Name: "+String.valueOf(user.getFirstName()));
            contentStream.newLine();
            contentStream.showText("Last Name: "+String.valueOf(user.getLastName()));
            contentStream.newLine();
            contentStream.showText("Email: "+String.valueOf(user.getEmail()));
            contentStream.newLine();
            contentStream.newLine();
        }
        contentStream.setFont(PDType1Font.TIMES_BOLD,13);
        contentStream.showText("Total users: "+String.valueOf(listUsers.size()));
        contentStream.endText();
        contentStream.close();


        documentbox.save(response.getOutputStream());
        documentbox.close();

    }


}
