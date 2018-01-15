package fr.larbotech.sample.microservices.pricingservice;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/pricing")
public class PricingController {

    @RequestMapping("")
    public String getResponse() {
        return "12345 €";
    }
}
