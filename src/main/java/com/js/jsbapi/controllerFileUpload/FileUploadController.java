//package com.js.jsbapi.controllerFileUpload;
//
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//import org.springframework.web.multipart.MultipartFile;
//
//import com.js.jsbapi.fileUpload.dto.FileInformation;
//import com.js.jsbapi.fileUpload.dto.UploadImageDto;
//import com.js.jsbapi.fileUpload.service.FileStorageService;
//
//@RestController
//@RequestMapping("/test")
//public class FileUploadController {
//	private final FileStorageService fileStorageService;
//
//	public FileUploadController(FileStorageService fileStorageService) {
//		this.fileStorageService = fileStorageService;
//	}
//
//	@PostMapping({ "/upload" })
//	public ResponseEntity<FileInformation> uploadFile(@RequestParam(name = "file", required = false) MultipartFile file,
//			@RequestParam("module") String module, @RequestParam("uuid") String uuid) {
//		System.out.println(" upload api : : "+"file length :  : "+(file.getOriginalFilename()) + " module"+module + "uuid : : " +uuid);
//		FileInformation fileName = this.fileStorageService.storeFile(file, module, uuid);
//		System.out.println(fileName.toString());
//		return ResponseEntity.ok().body(fileName);
//	}
//
//	@RequestMapping(value = "/uploadMultiPart", method = RequestMethod.POST)
//	public String multiFileUpload(@ModelAttribute UploadImageDto uploadImageDto) {
//		System.out.println("uploadImageDto : " + uploadImageDto.toString());
//		return "200";
//	}
//
//	
//	
//	
//	
//}