package com.aetins.web.exception;

public class FileStorageException extends RuntimeException {

	private static final long serialVersionUID = -7439229665297101663L;

	public FileStorageException(String message) {
		super(message);
	}
	
	public FileStorageException(String message,Throwable th) {
		super(message, th);
	}
}
