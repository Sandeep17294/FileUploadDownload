package com.aetins.web.serviceImpl;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Base64;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.aetins.web.service.FileUploadDownloadService;
import com.aetins.web.utils.FileUtils;

@Service
public class ServiceImpl implements FileUploadDownloadService{
	private Logger logger = LoggerFactory.getLogger(ServiceImpl.class);
	
	@Value("${file.upload.path}")
	private String filePath;
	
	@Value("${file.upload.path.business}")
	private String filePathBusiness;

	@Override
	public String storeFile(String policyNo, String serviceRequestNo, MultipartFile file) {
		String fileName = StringUtils.cleanPath(file.getOriginalFilename());
		try {
			fileName = StringUtils.cleanPath(file.getOriginalFilename());
			//check weather file name is correct
			if (fileName.contains("..")) {
				logger.error("Uploaded file name contains invalid path sequence {}", fileName);
				throw new Exception("Filename contains invalid path sequence ! " + fileName);

			}
			String checkPolicyDir = filePath +"/"+ policyNo;
			String checkServiceReqDir = filePath +"/"+ policyNo +"/"+ serviceRequestNo;
			File policyDir = new File(checkPolicyDir);
			File serviceRequestDir = new File(checkServiceReqDir);
			Path createPolicyDir = Paths.get(checkPolicyDir);
			Path createServiceDir = Paths.get(checkServiceReqDir);
			if (!policyDir.exists()) Files.createDirectories(createPolicyDir);
			if (!serviceRequestDir.exists()) Files.createDirectory(createServiceDir);
			Path finalFileUploadDir = Paths.get(checkServiceReqDir);
			Path destinationPath = finalFileUploadDir.resolve(fileName);
			Files.copy(file.getInputStream(), destinationPath, StandardCopyOption.REPLACE_EXISTING);
			logger.info("File uploaded successfully ! {}", destinationPath.toString());
			return fileName;
		} catch (Exception e) {
			logger.error("File upload un successed with cause : {}", e.getCause());
			e.printStackTrace();
		}
		return fileName;
	}

	
	@Override
	public String downloadFile(String fileName, String fileDownloadedPath) {
		MultipartFile multipartFile = null;
		String encode = null;
		try {
			File file = new File(fileName);
			File checkFile = new File(fileDownloadedPath+"/"+fileName);
			if (checkFile.exists()) {
				multipartFile = new MockMultipartFile("file", file.getName(), "text/plain", IOUtils.toByteArray(new FileInputStream(FileUtils.getFileFromDir(fileName, fileDownloadedPath))));
				encode = Base64.getEncoder().encodeToString(multipartFile.getBytes());
			} else {
				encode = "No Such File Avaialble";
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Exception caught while downloading file with cause : {}",e.getCause());
		}
		return encode;
	}


	@Override
	public String storeBusinessDoc(MultipartFile file) {
		String fileName = StringUtils.cleanPath(file.getOriginalFilename());
		try {
			fileName = StringUtils.cleanPath(file.getOriginalFilename());
			if (fileName.contains("..")) {
				logger.error("Uploaded file name contains invalid path sequence {}", fileName);
				throw new Exception("Filename contains invalid path sequence ! " + fileName);
			}
			Path finalFileUploadDir = Paths.get(filePathBusiness);
			Path destinationPath = finalFileUploadDir.resolve(fileName);
			Files.copy(file.getInputStream(), destinationPath, StandardCopyOption.REPLACE_EXISTING);
			logger.info("File uploaded successfully ! {}", destinationPath.toString());
			return fileName;
		}catch (Exception e) {
			logger.error("File upload un successed with cause : {}", e.getCause());
			e.printStackTrace();
		}
		return fileName;
	}


	@Override
	public String storeImage(String userName, MultipartFile file) {
		String checkPolicyDir = null;
		String fileName = StringUtils.cleanPath(file.getOriginalFilename());
		try {
			fileName = StringUtils.cleanPath(file.getOriginalFilename());
			//check weather file name is correct
			if (fileName.contains("..")) {
				logger.error("Uploaded file name contains invalid path sequence {}", fileName);
				throw new Exception("Filename contains invalid path sequence ! " + fileName);

			}
		    checkPolicyDir = filePath +"/"+ userName;
			//String checkServiceReqDir = filePath +"/"+ policyNo +"/"+ serviceRequestNo;
			File policyDir = new File(checkPolicyDir);
			//File serviceRequestDir = new File(checkServiceReqDir);
			Path createPolicyDir = Paths.get(checkPolicyDir);
			//Path createServiceDir = Paths.get(checkServiceReqDir);
			if (!policyDir.exists()) Files.createDirectories(createPolicyDir);
			//if (!serviceRequestDir.exists()) Files.createDirectory(createServiceDir);
			Path finalFileUploadDir = Paths.get(checkPolicyDir);
			
			File dir = new File(checkPolicyDir);
			
			if(dir.isDirectory() == false) {
				System.out.println("Not a directory. Do nothing");
				
			}
			File[] listFiles = dir.listFiles();
			for(File fille : listFiles){
				System.out.println("Deleting "+fille.getName());
				fille.delete();
			}
			
			Path destinationPath = finalFileUploadDir.resolve(fileName);
//			 boolean result = Files.deleteIfExists(destinationPath); // added sandeep
//			 if (result) 
//	                System.out.println("File is deleted"); 
//	            else
//	                System.out.println("File does not exists"); 
			InputStream inputStream = file.getInputStream();
			Files.copy(inputStream, destinationPath, StandardCopyOption.REPLACE_EXISTING);
			inputStream.close();
			logger.info("File uploaded successfully ! {}", destinationPath.toString());
			return fileName;
		} catch (Exception e) {
			logger.error("File upload un successed with cause : {}", e.getCause());
			e.printStackTrace();
		}
		return fileName;
	}


	@Override
	public String imageDownload(String fileDownloadedPath) {
		MultipartFile multipartFile = null;
		String encode = null;
		try {
			
			File checkFile = new File(fileDownloadedPath);
			if (checkFile.exists()) {
				String fileName = FileUtils.getFileNameFromDir(fileDownloadedPath);
				multipartFile = new MockMultipartFile("file", fileName, "text/plain", IOUtils.toByteArray(new FileInputStream(FileUtils.getFileFromDir(fileName, fileDownloadedPath))));
				encode = Base64.getEncoder().encodeToString(multipartFile.getBytes());
			} else {
				encode = "No Such File Avaialble";
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Exception caught while downloading file with cause : {}",e.getCause());
		}
		return encode;
	}

}
