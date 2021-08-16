package com.aetins.web.controller;

import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.aetins.web.service.FileUploadDownloadService;

@RestController
public class FileUploadDownloadController {
	private Logger logger = LoggerFactory.getLogger(FileUploadDownloadController.class);
	
	@Autowired
	private FileUploadDownloadService service;
	
	@Value("${file.download.path}")
	private String fileDownloadedPath;
	
	@Value("${file.upload.path.business}")
	private String fileDownloadBusinessDoc;
	
	@Value("${file.Report.download.path}")
	private String filePolicyReportFie;
	

	@PostMapping(value = "/fileUpload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	@CrossOrigin(origins = "*")
	public FileResponse upload(@RequestParam String policyNo, @RequestParam String serviceRequestNo,
						 @RequestPart(value = "files", required = true) MultipartFile file[]) {
		logger.info("File Upload : "+ file.length +", "+ policyNo +", "+ serviceRequestNo);
		String response = null;
		FileResponse fileResponse = null;
		
		try {
			Arrays.asList(file).stream().forEach(upload -> service.storeFile(policyNo, serviceRequestNo, upload));
			response = "File Uploaded Successfully";
			logger.info("File uploaded successfully !");
			return new FileResponse(response, HttpStatus.OK.toString());
		}catch(Exception e) {
			response = e.getMessage(); 
			e.printStackTrace();
			logger.error(e.getMessage());
			return new FileResponse(response, HttpStatus.OK.toString());
		}
	}
	
	@PostMapping(value = "/download")
	@CrossOrigin(origins = "*")
	public FileResponse download(@RequestParam String policyRequestNo, @RequestParam("fileName") String fileName) {
		logger.info("File Download");
		String response = null;
		
		try {
			response = service.downloadFile(fileName, fileDownloadedPath+"/"+policyRequestNo);
			logger.info("File Successfully downloaded fileName: {} downloadedPath: {}", fileName, fileDownloadedPath);
			return new FileResponse(response, HttpStatus.OK.toString());
		}catch (Exception e) {
			response = e.getMessage();
			logger.error(e.getMessage());
			
			e.printStackTrace();
			return new FileResponse(response, HttpStatus.OK.toString());
		}
		
	}
	
	@PostMapping(value = "business/fileUpload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	@CrossOrigin(origins = "*")
	public FileResponse uploadDocBusiness(@RequestParam("files") MultipartFile file[]) {
		logger.info("File Uploading Business User");
		String response = null;
		try {
			Arrays.asList(file).stream().forEach(upload -> service.storeBusinessDoc(upload));
			response = "File Uploaded Successfully";
			logger.info("File uploaded successfully ! {}");
			return new FileResponse(response, HttpStatus.OK.toString());
		}catch (Exception e) {
			response = e.getMessage();
			e.printStackTrace();
			logger.error(e.getMessage());
			return new FileResponse(response, HttpStatus.OK.toString());
		}
	}
	
	@PostMapping(value = "business/download")
	@CrossOrigin("*")
	public FileResponse downloadBusinessDoc(@RequestParam("fileName") String fileName) {
		logger.info("File Download Business Document");
		String response = null;
		try {
			logger.info("Down Load : "+ fileDownloadBusinessDoc);
			response = service.downloadFile(fileName, fileDownloadBusinessDoc);
			logger.info("File Successfully downloaded fileName: {} downloadedPath: {}", fileName, fileDownloadBusinessDoc);
			return new FileResponse(response, HttpStatus.OK.toString());
		}catch (Exception e) {
			response = e.getMessage();
			logger.error(e.getMessage());
			e.printStackTrace();
			return new FileResponse(response, HttpStatus.OK.toString());
		}
	}
	
	@GetMapping(value = "/test")
	public String getTestMsg() {
		return "TEST MSG";
	}
	
	
	
	class FileResponse{
		
		private String msg;
		private String value;
		
		public FileResponse(String msg,String value) {
			this.msg = msg;
			this.value = value;
		}
		public String getMsg() {
			return msg;
		}
		public void setMsg(String msg) {
			this.msg = msg;
		}
		public String getValue() {
			return value;
		}
		public void setValue(String value) {
			this.value = value;
		}
		
		
		
	}
	
	@PostMapping(value = "/imageUpload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	@CrossOrigin(origins = "*")
	public FileResponse imageUpload(@RequestParam String userName, @RequestPart(value = "files", required = true) MultipartFile file[]) {
		logger.info("File Upload : "+ file.length +", "+ userName );
		String response = null;
		FileResponse fileResponse = null;
		
		try {
			Arrays.asList(file).stream().forEach(upload -> service.storeImage(userName, upload));
			response = "File Uploaded Successfully";
			logger.info("File uploaded successfully !");
			return new FileResponse(response, HttpStatus.OK.toString());
		}catch(Exception e) {
			response = e.getMessage(); 
			e.printStackTrace();
			logger.error(e.getMessage());
			return new FileResponse(response, HttpStatus.OK.toString());
		}
	}
	
	@PostMapping(value = "/imageDownload")
	@CrossOrigin(origins = "*")
	public FileResponse imageDownload(@RequestParam String userName) {
		logger.info("File Download");
		String response = null;
		
		try {
			response = service.imageDownload( fileDownloadedPath+"/"+userName);
			logger.info("File Successfully downloaded fileName: {} downloadedPath: {}", userName, fileDownloadedPath);
			return new FileResponse(response, HttpStatus.OK.toString());
		}catch (Exception e) {
			response = e.getMessage();
			logger.error(e.getMessage());
			
			e.printStackTrace();
			return new FileResponse(response, HttpStatus.OK.toString());
		}
		
	}
	
	@PostMapping(value = "/report-download")
	@CrossOrigin(origins = "*")
	public FileResponse reportDownload(@RequestParam String fileName) {
		logger.info("File reportDownload");
		String response = null;
		
		try {
			response = service.downloadFile(fileName,filePolicyReportFie);
			logger.info("File Successfully reportDownload fileName: {}",fileName);
			return new FileResponse(response, HttpStatus.OK.toString());
		}catch (Exception e) {
			response = e.getMessage();
			logger.error(e.getMessage());
			
			e.printStackTrace();
			return new FileResponse(response, HttpStatus.OK.toString());
		}
		
	}
	
}
