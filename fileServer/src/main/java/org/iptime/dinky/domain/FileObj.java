package org.iptime.dinky.domain;

public class FileObj {

	private String fileName;
	private long fileSize;
	private boolean isFile;
	
	public FileObj() {
	}

	public FileObj(String fileName, long fileSize, boolean isFile) {
		super();
		this.fileName = fileName;
		this.fileSize = fileSize;
		this.isFile = isFile;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public long getFileSize() {
		return fileSize;
	}

	public void setFileSize(int fileSize) {
		this.fileSize = fileSize;
	}

	public boolean getIsFile() {
		return isFile;
	}

	public void setIsFile(boolean isFile) {
		this.isFile = isFile;
	}

	@Override
	public String toString() {
		return "FileObj [fileName=" + fileName + ", fileSize=" + fileSize
				+ ", isFile=" + isFile + "]";
	}
	
	
}
