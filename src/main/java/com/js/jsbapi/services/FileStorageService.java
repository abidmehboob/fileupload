package com.js.jsbapi.services;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Date;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.js.jsbapi.fileUpload.dto.FileInformation;

@Service
public class FileStorageService {

  @Autowired
  private FileInformation fileInformation;

  private final Environment myenv;

  @Autowired
  public FileStorageService(Environment env) {
    this.myenv = env;
//    this.fileStorageLocation = Paths.get(env.getProperty("app.file.upload-dir"+UUID.randomUUID(), "./uploads/files/"+UUID.randomUUID().toString()))
//        .toAbsolutePath().normalize();
//
//    try {
//      Files.createDirectories(this.fileStorageLocation);
//    } catch (Exception ex) {
//      throw new RuntimeException(
//          "Could not create the directory where the uploaded files will be stored.", ex);
//    }
  }

  private String getFileExtension(String fileName) {
    if (fileName == null) {
      return null;
    }
    String[] fileNameParts = fileName.split("\\.");

    return fileNameParts[fileNameParts.length - 1];
  }

  public ResponseEntity<Resource> downloadFile(String module, String uuid, String fileName) throws FileNotFoundException {
    File file = new File("D:/JSBANK/uploads/" + module + "/" + uuid + "/" + fileName);
    HttpHeaders headers = new HttpHeaders();
    headers.add(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=img.png");

    InputStreamResource resource = new InputStreamResource(new FileInputStream(file));

    return ResponseEntity.ok()
            .headers(headers)
            .contentLength(file.length())
            .contentType(MediaType.IMAGE_JPEG)
            .body(resource);
  }

//  public ResponseEntity<Resource> exportFile(HttpServletResponse response) throws IOException {
//    //File file = new File("D:/JSBANK/uploads/" + module + "/" + uuid + "/" + fileName);
//    HttpHeaders headers = new HttpHeaders();
//    headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=users.xlsx");
//
//    List<User> users = new ArrayList<>();
//
//    User user = new User();
//
//    user.setName("Zaigham");
//    user.setEmail("z@a.com");
//    user.setCountry("Pakistan");
//
//    users.add(user);
//
//    user = new User();
//
//    user.setName("Saqib");
//    user.setEmail("s@a.com");
//    user.setCountry("Afghanistan");
//
//    users.add(user);
//
//    ExcelExporter excelExporter = new ExcelExporter(users);
//    excelExporter.export(response);
//
//    return ResponseEntity.ok()
//            .headers(headers)
//            .contentType(MediaType.APPLICATION_OCTET_STREAM)
//            .body(resource);
//  }

//  public void exportFile(HttpServletResponse response) throws IOException {
//    response.setContentType("application/octet-stream");
//
//    String headerKey = "Content-Disposition";
//    String headerValue = "attachment; filename=users.xlsx";
//    response.setHeader(headerKey, headerValue);
//
//    List<User> users = new ArrayList<>();
//
//    User user = new User();
//
//    user.setName("Zaigham");
//    user.setEmail("z@a.com");
//    user.setCountry("Pakistan");
//
//    users.add(user);
//
//    user = new User();
//
//    user.setName("Saqib");
//    user.setEmail("s@a.com");
//    user.setCountry("Afghanistan");
//
//    users.add(user);
//
//    ExcelExporter excelExporter = new ExcelExporter(users);
//    excelExporter.export(response);
//  }

  public FileInformation storeFile(MultipartFile file, String module, String storageUUID) {
    // Normalize file name

    String fileName =
        new Date().getTime() + "-file." + getFileExtension(file.getOriginalFilename());

    try {
      // Check if the filename contains invalid characters
      if (fileName.contains("..")) {
        fileInformation.setStatus("99");
        fileInformation.setMessage("Sorry! Filename contains invalid path sequence " + fileName);
        return fileInformation;
//        throw new RuntimeException(
//            "Sorry! Filename contains invalid path sequence " + fileName);
      }

      if(storageUUID.length() < 1){
        Path fileStorageLocation = Paths.get(myenv.getProperty("app.file.upload-dir" + module + "\\" +storageUUID, "D:\\JSBANK\\uploads\\"+module+"\\" + storageUUID))
                .toAbsolutePath().normalize();
      }

      UUID uuid = UUID.randomUUID();

      Path fileStorageLocation = Paths.get(myenv.getProperty("app.file.upload-dir" + module + "\\" +uuid, "D:\\JSBANK\\uploads\\"+module+"\\" + uuid))
              .toAbsolutePath().normalize();

      try {
        Files.createDirectories(fileStorageLocation);
      } catch (Exception ex) {
        fileInformation.setStatus("99");
        fileInformation.setMessage("Could not create the directory where the uploaded files will be stored.");
        return fileInformation;
//        throw new RuntimeException(
//                "Could not create the directory where the uploaded files will be stored.", ex);
      }

      Path targetLocation = fileStorageLocation.resolve(fileName);
      Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);

      fileInformation.setUuid(uuid.toString());
      fileInformation.setFileName(fileName);
      fileInformation.setFileStoragePath(fileStorageLocation.toString() );
      fileInformation.setFullPath(targetLocation.toString());
      fileInformation.setHttpPath("http://localhost:8081/download/" + module + "/" + uuid + "/" + fileName );

      fileInformation.setStatus("00");
      fileInformation.setMessage("File uploaded successfully!");
      return fileInformation;
    } catch (IOException ex) {
      throw new RuntimeException("Could not store file " + fileName + ". Please try again!", ex);
    }
  }
}