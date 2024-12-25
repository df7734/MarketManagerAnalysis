package dfedotov.university.market.controller;

import dfedotov.university.market.service.RequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @Autowired
    private RequestService requestService;

    @GetMapping("/test")
    public String test() {
        requestService.sendRequests();
        return "Requests sent";
    }
}
