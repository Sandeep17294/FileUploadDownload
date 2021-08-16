package com.aetins.web.service;


import org.springframework.web.multipart.MultipartFile;

public interface FileUploadDownloadService {

	public String storeFile(String policyNo, String serviceRequestNo, MultipartFile file);

	public String downloadFile(String fileName, String fileDownloadedPath);

	public String storeBusinessDoc(MultipartFile upload);

	public String storeImage(String userName, MultipartFile upload);

	public String imageDownload( String string);

}
