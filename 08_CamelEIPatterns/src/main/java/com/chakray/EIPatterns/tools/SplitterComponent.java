package com.chakray.EIPatterns.tools;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SplitterComponent {
    public List<String> splitInput(String body) {
        return List.of(body.split(","));
    }
}
