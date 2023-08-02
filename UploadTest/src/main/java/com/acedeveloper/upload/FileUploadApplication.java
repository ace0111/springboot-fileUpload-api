package com.acedeveloper.upload;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@SpringBootApplication
public class FileUploadApplication {

    public static void main(String[] args) {
        SpringApplication.run(FileUploadApplication.class, args);
    }

    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) {
        // Process the uploaded file
        // Replace this code with your own logic to handle the uploaded file

        if (!file.isEmpty()) {
            // File received successfully
            return ResponseEntity.ok("File uploaded successfully.");
        } else {
            // No file received
            return ResponseEntity.badRequest().body("No file received.");
        }
    }
}
