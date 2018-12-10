package com.divae.hazelcast.scaleout.controller;

import com.divae.hazelcast.scaleout.services.WebcrawlingService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {

    private final WebcrawlingService webcrawlingService;

    public MainController(WebcrawlingService webcrawlingService) {
        this.webcrawlingService = webcrawlingService;
    }


    @GetMapping("/add-url")
    public ResponseEntity addCrawlingUrl(final String url) {
        webcrawlingService.addCrawlingUrl(url);

        return new ResponseEntity(HttpStatus.OK);
    }


}
