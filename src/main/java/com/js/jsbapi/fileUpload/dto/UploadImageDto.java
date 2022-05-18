package com.js.jsbapi.fileUpload.dto;

import org.springframework.web.multipart.MultipartFile;

public class UploadImageDto {
	private MultipartFile file;
	private String module;
	private String uuid;
	public MultipartFile getFile() {
		return file;
	}
	public void setFile(MultipartFile file) {
		this.file = file;
	}
	public String getModule() {
		return module;
	}
	public void setModule(String module) {
		this.module = module;
	}
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	
	@Override
	public String toString() {
		return "UploadImageDto [file=" + file + ", module=" + module + ", uuid=" + uuid + "]";
	}

}