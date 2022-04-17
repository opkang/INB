package userService.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.mock.web.MockHttpServletResponse;
import userService.entity.User;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static org.mockito.Mockito.*;

class CSVGenerateServiceTest {
    @Mock
    List<User> listUsers;
    @InjectMocks
    CSVGenerateService cSVGenerateService;
    @Mock
    private UserService service;
    private HttpServletResponse response = new MockHttpServletResponse();
    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        response.encodeURL("http://localhost:8080/pdf/convert");
        response.setContentType("application/csv");
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename =User List" + ".csv";
        response.setHeader(headerKey,headerValue);
    }

    @Test
    void testExportCSV() throws IOException {
        List<User> listUsers = service.listAll();
        CSVGenerateService exporterCSV = new CSVGenerateService(listUsers);
        exporterCSV.exportCSV(response);

    }
}

