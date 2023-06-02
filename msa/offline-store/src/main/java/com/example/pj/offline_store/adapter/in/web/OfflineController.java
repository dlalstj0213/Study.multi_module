package com.example.pj.offline_store.adapter.in.web;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@RequestMapping("/offline")
@RequiredArgsConstructor
public class OfflineController {

    private Environment environment;

    @GetMapping("")
    ResponseEntity<String> getOffline(HttpServletRequest request) {
        String response = """
                serverPort: %s
                """;
        return ResponseEntity.ok().body(String.format(response, request.getServerPort()));
    }

    @PostMapping("")
    ResponseEntity<HashMap<String, Object>> postOffline(@RequestBody HashMap<String, Object> data) {
        return ResponseEntity.status(HttpStatus.CREATED).body(data);
    }

    @GetMapping("/check")
    String checkAuth(HttpSession session) {
        Object obj = session.getAttribute("username");
        if (obj != null) return (String) obj;
        return "No Value";
    }
}