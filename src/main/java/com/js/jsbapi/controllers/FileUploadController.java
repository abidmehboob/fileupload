package com.js.jsbapi.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.js.jsbapi.fileUpload.dto.FileInformation;
import com.js.jsbapi.services.FileStorageService;

@RestController
@RequestMapping("/file")
public class FileUploadController {

  private final FileStorageService fileStorageService;

  public FileUploadController(FileStorageService fileStorageService) {
    this.fileStorageService = fileStorageService;
  }

  @CrossOrigin(origins = "*", allowedHeaders = "*")

  @PostMapping("/upload")
  public ResponseEntity<FileInformation> uploadFile(
      @RequestParam(name = "file", required = false) MultipartFile file,
      @RequestParam("module") String module,
      @RequestParam("uuid") String uuid) {
//      String fileName = fileStorageService.storeFile(file);
    System.out.println("request : " + file.getName());
      FileInformation fileName = fileStorageService.storeFile(file, module, uuid);


//      UploadResponse uploadResponse = new UploadResponse(fileName, fullName, dateOfBirth);

//      return ResponseEntity.ok().body(uploadResponse);
    System.out.println("response : " + fileName);
      return ResponseEntity.ok().body(fileName);
  }
//  @GetMapping ("/download/{module}/{uuid}/{fileName}")
//    public ResponseEntity<Resource> downloadFile(@PathVariable String module, @PathVariable String uuid, @PathVariable String fileName) throws FileNotFoundException {
//      return fileStorageService.downloadFile(module, uuid, fileName);
//    }

//    @GetMapping ("/export")
//    public void exportFile(HttpServletResponse response) throws IOException {
//      fileStorageService.exportFile(response);
//    }

//  @GetMapping ("/export")
//  public ResponseEntity<Resource> exportFile(HttpServletResponse response) throws IOException {
//    return fileStorageService.exportFile(response);
//  }
}
