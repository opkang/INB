package userService.controller;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.mock.web.MockHttpServletResponse;
import userService.entity.User;
import userService.service.UserService;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;

import static org.mockito.Mockito.*;

class PDFGenerateControllerTest {
    @Mock
    UserService service;
    @InjectMocks
    PDFGenerateController pDFGenerateController;
    @Mock
    private HttpServletResponse response = new MockHttpServletResponse();
    @Mock
    private HttpServletResponse response2 = new MockHttpServletResponse();
    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);

        response.setContentType("application/pdf");
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename =User List" + ".pdf";
        response.setHeader(headerKey,headerValue);

        response2.setContentType("application/octet-stream");
        String headerKey_csv = "Content-Disposition";
        String headerValue_csv = "attachment; filename =User List" + ".csv";
        response2.setHeader(headerKey_csv,headerValue_csv);
    }

    @Test
    void testGeneratePDF() throws IOException {
        when(service.listAll()).thenReturn(Arrays.<User>asList(new User(Long.valueOf(1), "firstName", "lastName", "email")));
      pDFGenerateController.generatePDF(response);
    }

    @Test
    void testConvertPDF_CSV() throws IOException {
        when(service.listAll()).thenReturn(Arrays.<User>asList(new User(Long.valueOf(1), "firstName", "lastName", "email")));

        pDFGenerateController.convertPDF_CSV(response2);
    }
}

