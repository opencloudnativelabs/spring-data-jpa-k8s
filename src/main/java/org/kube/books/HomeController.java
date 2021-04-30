package org.kube.books;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {
    Logger logger = LoggerFactory.getLogger(HomeController.class);
    @GetMapping("/")
    public String index(){
        logger.info("Logging Index Method");
        return "Welcome to the Book World";
    }
}
