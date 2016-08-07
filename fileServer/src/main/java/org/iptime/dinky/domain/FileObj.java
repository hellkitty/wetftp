package org.iptime.dinky.domain;

public class FileObj {

	private String fileName;
	private long fileSize;
	private boolean isFile;
	private String lastModify;
	private String filePermission;
	private boolean writePermission;
	private boolean readPermission;
	private boolean execPermission;
	private String fileOwner;
	
	public FileObj(String fileName, long fileSize, boolean isFile,
			String lastModify, String filePermission, boolean writePermission,
			boolean readPermission, boolean execPermission, String fileOwner) {
		super();
		this.fileName = fileName;
		this.fileSize = fileSize;
		this.isFile = isFile;
		this.lastModify = lastModify;
		this.filePermission = filePermission;
		this.writePermission = writePermission;
		this.readPermission = readPermission;
		this.execPermission = execPermission;
		this.fileOwner = fileOwner;
	}

	public String getFileOwner() {
		return fileOwner;
	}

	public void setFileOwner(String fileOwner) {
		this.fileOwner = fileOwner;
	}

	public void setFile(boolean isFile) {
		this.isFile = isFile;
	}

	public FileObj() {
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

	public void setFileSize(long fileSize) {
		this.fileSize = fileSize;
	}

	public boolean getIsFile() {
		return isFile;
	}

	public void setIsFile(boolean isFile) {
		this.isFile = isFile;
	}

	public String getLastModify() {
		return lastModify;
	}

	public void setLastModify(String lastModify) {
		this.lastModify = lastModify;
	}

	public String getFilePermission() {
		return filePermission;
	}

	public void setFilePermission(String filePermission) {
		this.filePermission = filePermission;
	}

	public boolean isWritePermission() {
		return writePermission;
	}

	public void setWritePermission(boolean writePermission) {
		this.writePermission = writePermission;
	}

	public boolean isReadPermission() {
		return readPermission;
	}

	public void setReadPermission(boolean readPermission) {
		this.readPermission = readPermission;
	}

	public boolean isExecPermission() {
		return execPermission;
	}

	public void setExecPermission(boolean execPermission) {
		this.execPermission = execPermission;
	}

	@Override
	public String toString() {
		return "FileObj [fileName=" + fileName + ", fileSize=" + fileSize
				+ ", isFile=" + isFile + ", lastModify=" + lastModify
				+ ", filePermission=" + filePermission + ", writePermission="
				+ writePermission + ", readPermission=" + readPermission
				+ ", execPermission=" + execPermission + ", fileOwner="
				+ fileOwner + "]";
	}

}
