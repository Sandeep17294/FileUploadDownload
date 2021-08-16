package com.aetins.web.pojo;


public class DownloadFileResponse {

	private String fileName;
	private MultipartInputStreamFileResource fileData;
	
	public DownloadFileResponse(String fileName, MultipartInputStreamFileResource fileData) {
		super();
		this.fileName = fileName;
		this.fileData = fileData;
	}
	
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public MultipartInputStreamFileResource getFileData() {
		return fileData;
	}
	public void setFileData(MultipartInputStreamFileResource fileData) {
		this.fileData = fileData;
	}
	
}
