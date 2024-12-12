package com.jetbrains.java_interpreter.controller;

import com.jetbrains.java_interpreter.interpreter.InterpreterService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class InterpreterController {

    private final InterpreterService interpreterService;

    public InterpreterController(InterpreterService interpreterService) {
        this.interpreterService = interpreterService;
    }


    @PostMapping("/interpret")
    public ResponseEntity<String> interpret(@RequestBody String code) {
        interpreterService.interpret(code);
        return null;
    }
}
