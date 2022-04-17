package userService.service;

import org.jetbrains.annotations.NotNull;
import org.supercsv.io.CsvBeanWriter;
import org.supercsv.io.ICsvBeanWriter;
import org.supercsv.prefs.CsvPreference;
import userService.entity.User;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class CSVGenerateService {
    private List<User> listUsers;
    public CSVGenerateService(List<User> ListUser){
        this.listUsers = ListUser;
//        log.info("This are User In DB: " + listUsers);
    }
    public void exportCSV(@NotNull HttpServletResponse response) throws IOException {
        ICsvBeanWriter csvWriter=new CsvBeanWriter(response.getWriter(), CsvPreference.STANDARD_PREFERENCE);

        String[] header ={"ID","First Name","Last Name","Email"};
        String[] field_mapping ={"userid","firstname","lastname","email"};

        csvWriter.writeHeader(header);

        for(User user : listUsers) csvWriter.write(user, field_mapping);
        csvWriter.close();}}
