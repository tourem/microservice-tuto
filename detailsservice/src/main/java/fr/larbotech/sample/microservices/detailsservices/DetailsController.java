package fr.larbotech.sample.microservices.detailsservices;


import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/details")
public class DetailsController {


    @RequestMapping("")
    public String getResponse() {
        return "details";
    }

    @RequestMapping("/{id}")
    public String details(@PathVariable String id) {
        return "details service 8081 - "+id;
    }

}
