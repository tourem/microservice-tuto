package fr.larbotech.sample.microservices.productservice;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private DiscoveryClient discoveryClient;

    @Autowired
    private LoadBalancerClient loadBalancerClient;

  // @Autowired
  // private com.netflix.discovery.DiscoveryClient netflixDiscoveryClient;

    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Product find(@PathVariable String id) {
        return Product.getMockProduct(id);
    }

    @RequestMapping(value = "/details/{id}", method = RequestMethod.GET)
    public Product details(@PathVariable String id) {

        ServiceInstance serviceInstance;
        serviceInstance = discoveryClient.getInstances("details-service")
        .stream()
        .findFirst()
        .orElseThrow(() -> new RuntimeException("details-service not found"));

        ServiceInstance serviceInstanceLoadBalancer =
                loadBalancerClient.choose("details-service");

        String url = serviceInstanceLoadBalancer.getUri().toString() + "/api/details/"+id;

       // url = netflixDiscoveryClient.getNextServerFromEureka("details-service", false).getHomePageUrl()+"/details/"+id;

        ResponseEntity<String> details = restTemplate.getForEntity(url, String.class);

        Product product = Product.getMockProduct(id);
        product.setDetails(details.getBody());

        return product;
    }





}
