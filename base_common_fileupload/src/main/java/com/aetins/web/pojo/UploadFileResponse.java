package com.aetins.web.pojo;

public class UploadFileResponse {

	private String fileName;
	private String fileType;
	private long size;
	
	
	public UploadFileResponse(String fileName2, String contentType, long size2) {
		// TODO Auto-generated constructor stub
		this.fileName = fileName;
		this.fileType = fileType;
		this.size = size;
	}
	
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getFileType() {
		return fileType;
	}
	public void setFileType(String fileType) {
		this.fileType = fileType;
	}
	public long getSize() {
		return size;
	}
	public void setSize(long size) {
		this.size = size;
	}
	
	@Override
	public String toString() {
		return "UploadFileResponse [fileName=" + fileName + ", fileType=" + fileType + ", size=" + size + "]";
	}
	
}
