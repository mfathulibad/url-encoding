package com.example.demo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.util.Base64;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

@RestController
@RequestMapping("/sample")
@RequiredArgsConstructor
public class SampleController {

  private final ObjectMapper objMap = new ObjectMapper();

  @Data
  @Builder
  @NoArgsConstructor
  @AllArgsConstructor
  private static class Parameters {
    private String param1;
    private String param2;
    private Integer param3;
    private Double param4;
  }

  @RequestMapping("/test")
  public String home(){
      return "Hello World!";
  }
  
  @GetMapping
  public ResponseEntity<?> getSomething(@RequestParam("parameters") String parameters) throws JsonProcessingException {
    byte[] decodedBytes = Base64.getUrlDecoder().decode(parameters);
    String decodedUrl = new String(decodedBytes);
    var input = objMap.readValue(decodedUrl, Parameters.class);
    return ResponseEntity.ok(input);
  }

  @RequestMapping("/encode")
  public RedirectView encode(RedirectAttributes attributes){

    String json = "{\"param1\":\"value1\",\"param2\":\"value2\",\"param3\":42,\"param4\":3.14}";
    String encodedString = Base64.getUrlEncoder().encodeToString(json.getBytes());

    // Redirect to the getSomething endpoint with the encoded string as a query parameter
    attributes.addAttribute("parameters", encodedString);

    return new RedirectView("/sample");

  }
  
}
