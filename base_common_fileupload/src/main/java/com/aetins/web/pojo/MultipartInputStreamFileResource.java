package com.aetins.web.pojo;

import java.io.IOException;
import java.io.InputStream;

import org.springframework.core.io.InputStreamResource;

public class MultipartInputStreamFileResource extends InputStreamResource{
	
	private final String filename;

	public MultipartInputStreamFileResource(InputStream inputStream, String filename) {
		super(inputStream);
		// TODO Auto-generated constructor stub
		this.filename = filename;
	}

	public String getFilename() {
		return filename;
	}

	@Override
    public long contentLength() throws IOException {
        return -1;
    }
}
