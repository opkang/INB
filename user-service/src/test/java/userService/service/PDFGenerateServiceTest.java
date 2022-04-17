package userService.service;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletResponse;
import userService.entity.User;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import static org.mockito.Mockito.*;
class PDFGenerateServiceTest {
    @Mock
    List<User> listUsers;
    @Mock
    Logger log;
    @InjectMocks
    PDFGenerateService pDFGenerateService;
    @Mock
    private UserService service;
    private HttpServletResponse response = new MockHttpServletResponse();
    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        response.encodeURL("http://localhost:8080/pdf/generate");
        response.setContentType("application/pdf");
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename =User List" + ".pdf";
        response.setHeader(headerKey,headerValue);
    }

    @Test
    void testExportBox() throws IOException {
        List<User> listUsers = service.listAll();
        PDFGenerateService exporter = new PDFGenerateService(listUsers);
        exporter.exportBox(response);
    }
}
