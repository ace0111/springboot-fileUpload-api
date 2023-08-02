package com.luv2code.springboot.thymeleafdemo.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class FileController {

	// Save the uploaded file to this folder
	private static String UPLOADED_FOLDER = "src/main/resources/excelSheets/";

	@GetMapping("/")
	public String index(Model model) {

		List<String> list = new ArrayList<String>();
		File files = new File(UPLOADED_FOLDER);
		String[] fileList = ((File) files).list();
		for (String name : fileList) {
			list.add(name);
		}
		model.addAttribute("list", list);
		return "upload";
	}

	@PostMapping("/upload")
	public String singleFileUpload(@RequestParam("file") MultipartFile file, Model model) {

		if (file.isEmpty()) {
			model.addAttribute("warning", "Please select a file to upload");
			return "upload";
		}

		try {

			byte[] bytes = file.getBytes();
			Path path = Paths.get(UPLOADED_FOLDER + file.getOriginalFilename());
			Files.write(path, bytes);

			model.addAttribute("message", "You successfully uploaded '" + file.getOriginalFilename() + "'");

		} catch (IOException e) {
			model.addAttribute("error", "Error");
			return "upload";
		}

		List<String> list = new ArrayList<String>();
		File files = new File(UPLOADED_FOLDER);
		String[] fileList = ((File) files).list();
		for (String name : fileList) {
			list.add(name);
		}
		model.addAttribute("list", list);
		return "upload";
	}
	
	@PostMapping("/uploadFiles")
	public String uploadFiles(@RequestParam("file1") MultipartFile file1, @RequestParam("file2") MultipartFile file2,Model model)
			throws IOException {
		if (file1.isEmpty() || file2.isEmpty()) {
			model.addAttribute("warning", "Please select a file to upload");
			return "upload";
		}
		
		try {

			byte[] bytes1 = file1.getBytes();
			byte[] bytes2 = file2.getBytes();
			Path path1 = Paths.get(UPLOADED_FOLDER + file1.getOriginalFilename());
			Path path2 = Paths.get(UPLOADED_FOLDER + file2.getOriginalFilename());
			Files.write(path1, bytes1);
			Files.write(path2, bytes2);

			model.addAttribute("messageFiles", "You successfully uploaded '" + file1.getOriginalFilename() + " & " + file2.getOriginalFilename() + "'");
			

		} catch (IOException e) {
			model.addAttribute("error", "Error");
			return "upload";
		}
		
		List<String> list = new ArrayList<String>();
		File files = new File(UPLOADED_FOLDER);
		String[] fileList = ((File) files).list();
		for (String name : fileList) {
			list.add(name);
		}
		model.addAttribute("list", list);
		return "upload";
		
	}
	
	@GetMapping(path = "/download/{name}")
	public ResponseEntity<Resource> download(@PathVariable("name") String name) throws IOException {

		File file = new File(UPLOADED_FOLDER + name);
		Path path = Paths.get(file.getAbsolutePath());
		ByteArrayResource resource = new ByteArrayResource(Files.readAllBytes(path));

		return ResponseEntity.ok().contentLength(file.length())
				.contentType(MediaType.parseMediaType("application/octet-stream")).body(resource);
	}

	@PostMapping(path = "/delete")
	public String delete(@RequestParam("name") String name) throws IOException {

		try {
			Files.deleteIfExists(Paths.get(UPLOADED_FOLDER + name));
		}

		catch (IOException e) {
			return "redirect:/";
		}
		return "redirect:/";
	}
}
