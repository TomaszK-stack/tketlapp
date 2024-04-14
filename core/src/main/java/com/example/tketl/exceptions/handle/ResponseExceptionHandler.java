package com.example.tketl.exceptions.handle;

import com.example.tketl.archive.manager.ArchiveManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.io.FileNotFoundException;

@ControllerAdvice
public class ResponseExceptionHandler extends ResponseEntityExceptionHandler {
    private ArchiveManager manager;

    @Autowired
    public ResponseExceptionHandler(ArchiveManager manager) {
        this.manager = manager;
    }

    @ExceptionHandler(value
            = { FileNotFoundException.class })
    protected ResponseEntity<String> handleTest(FileNotFoundException ex, WebRequest request){
        manager.update(ex.getMessage(), -1);
        return ResponseEntity.ok("test");
    }
}
