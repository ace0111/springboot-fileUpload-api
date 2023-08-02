//package com.luv2code.springboot.thymeleafdemo.controller;
//
//import java.io.File;
//import java.io.IOException;
//import java.nio.file.Files;
//import java.nio.file.Path;
//import java.nio.file.Paths;
//
//import javax.annotation.Resource;
//
//import org.springframework.core.io.ByteArrayResource;
//import org.springframework.http.MediaType;
//import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.multipart.MultipartFile;
//
//@Controller
//public class DemoController {
//
////	@Bean
////	public RestTemplate restTemplate() {
////	    return new RestTemplate();
////	}
//
//	private Path root = Paths.get("src/main/resources/excelSheets");
//
//	@GetMapping("/hello")
//	public String sayHello(Model theModel) {
//
//		theModel.addAttribute("theDate", new java.util.Date());
//
//		return "helloworld";
//	}
//
////	    @PostMapping("/upload")
////	    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) throws IOException {
////	    	// Save the uploaded file to a temporary location
////	        File tempFile = Files.createTempFile("upload", file.getOriginalFilename()).toFile();
////	        file.transferTo(tempFile);
////
////	        // Prepare the file data
////	        HttpHeaders headers = new HttpHeaders();
////	        headers.setContentType(MediaType.MULTIPART_FORM_DATA);
////
////	        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
////	        body.add("file", new FileSystemResource(tempFile));
////
////	        HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(body, headers);
////
////	        // Make the API call
////	        ResponseEntity<String> response = restTemplate().postForEntity("http://localhost:8081/upload", requestEntity, String.class);
////
////	        // Handle the response
////	        if (response.getStatusCode().is2xxSuccessful()) {
////	            // File uploaded successfully
////	            return ResponseEntity.ok("File uploaded successfully.");
////	        } else {
////	            // Handle the failure case
////	            return ResponseEntity.status(response.getStatusCode()).body(response.getBody());
////	        }
////	    }
////	    
////	    @GetMapping("/{filePath}")
////	    public ResponseEntity<byte[]> downloadFile(@PathVariable String filePath) {
////	        try {
////	            // Retrieve the file from the given path
////	            Path file = Paths.get(filePath);
////	            byte[] fileContent = Files.readAllBytes(file);
////
////	            // Prepare the HTTP headers
////	            HttpHeaders headers = new HttpHeaders();
////	            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
////	            headers.setContentLength(fileContent.length);
////	            headers.setContentDispositionFormData("attachment", file.getFileName().toString());
////
////	            // Return the file as a response
////	            return ResponseEntity.ok().headers(headers).body(fileContent);
////	        } catch (Exception e) {
////	            // Handle file retrieval errors
////	            return ResponseEntity.notFound().build();
////	        }
////	    }
//
//	@GetMapping("/")
//	public String index() {
//		return "upload";
//	}
//
//	@PostMapping("/upload")
//	public String uploadFile(@RequestParam("file") MultipartFile file) throws IOException {
//		Path filePath = Paths.get(root.toString(), file.getOriginalFilename());
//		if (Files.exists(filePath)) {
//			Files.delete(filePath);
//		}
//		Files.copy(file.getInputStream(), filePath);
//		return "redirect:/";
//	}
//
//	@GetMapping(path = "/download/{name}")
//	public ResponseEntity<ByteArrayResource> download(@PathVariable("name") String name) throws IOException {
//
//		File file = new File(root + name);
//		Path path = Paths.get(file.getAbsolutePath());
//		ByteArrayResource resource = new ByteArrayResource(Files.readAllBytes(path));
//
//		return ResponseEntity.ok().contentLength(file.length())
//				.contentType(MediaType.parseMediaType("application/octet-stream")).body(resource);
//	}
//
//	@PostMapping("/uploadFiles")
//	public String uploadFiles(@RequestParam("file1") MultipartFile file1, @RequestParam("file2") MultipartFile file2)
//			throws IOException {
//		Path filePath1 = Paths.get(root.toString(), file1.getOriginalFilename());
//		Path filePath2 = Paths.get(root.toString(), file2.getOriginalFilename());
//		if (Files.exists(filePath1)) {
//			Files.delete(filePath1);
//		}
//		if (Files.exists(filePath2)) {
//			Files.delete(filePath2);
//		}
//		Files.copy(file1.getInputStream(), filePath1);
//		Files.copy(file2.getInputStream(), filePath2);
//		return "redirect:/";
//	}
//}
