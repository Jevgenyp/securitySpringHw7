package org.example.securityspring.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ResourceController {

    @GetMapping("/private-data")
    public String privateData() {
        return "Private data accessible only by ADMIN";
    }

    @GetMapping("/public-data")
    public String publicData() {
        return "Public data accessible by all authenticated users";
    }
}
