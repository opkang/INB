package userService.service;


import lombok.extern.slf4j.Slf4j;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import userService.entity.User;

import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.io.IOException;
import java.util.List;

@Slf4j
public class PDFGenerateService {

    private List<User> listUsers;

    public PDFGenerateService(List<User> ListUser){
        this.listUsers = ListUser;
        log.info("This are User In DB: " + listUsers);
    }

    public void export(HttpServletResponse response) throws IOException {
        PDDocument documentbox = new PDDocument();
        PDPage page = new PDPage();
        documentbox.addPage(page);
        PDPageContentStream contentStream=new PDPageContentStream(documentbox,page);
        contentStream.setFont(PDType1Font.COURIER, 12);
        contentStream.beginText();
        contentStream.setFont(PDType1Font.TIMES_ROMAN,10);
        contentStream.setLeading(16.0f);
        contentStream.newLineAtOffset(25,page.getTrimBox().getHeight()-25);
        for (User user : listUsers) {
            contentStream.showText(String.valueOf(user.getUserID()));
            contentStream.showText(user.getFirstName());
            contentStream.showText(user.getLastName());
            contentStream.showText(user.getEmail());
            contentStream.newLine();
        }

        contentStream.endText();
        contentStream.close();
        documentbox.save(response.getOutputStream());
        documentbox.close();
    }
}
