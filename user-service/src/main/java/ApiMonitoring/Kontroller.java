package ApiMonitoring;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Kontroller {


    @GetMapping("/test")
    public void testing(){

    }
    @GetMapping("/EditForm")
    public void editForm(){

    }
}

