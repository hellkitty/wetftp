package org.iptime.dinky.service;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.PosixFilePermission;
import java.nio.file.attribute.PosixFilePermissions;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.iptime.dinky.domain.FileObj;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class PathReaderService {

	private Logger logger = LoggerFactory.getLogger(PathReaderService.class);
	
	public ArrayList<FileObj> readPath(String path){
		//String path1 = "/data/db";
		
		//assertEquals(true, pathValidation("/data/db"));
		
		if(pathValidation(path)){
			
			File reqPath = new File(path);
			
			String[] list = reqPath.list();
			ArrayList<FileObj> returnList = new ArrayList<FileObj>();
			
			ArrayList<FileObj> dirList = new ArrayList<FileObj>();
			ArrayList<FileObj> fileList = new ArrayList<FileObj>();
			
			try {
				for (int i = 0; i < list.length; i++) {
					File tmpFile = new File(path+list[i]);
					
					Path tmpPath = Paths.get(path+list[i]);
					Set<PosixFilePermission> permission = Files.getPosixFilePermissions(tmpPath);
					
					FileObj fileObj = new FileObj();

					Date date = new Date(tmpFile.lastModified());
					DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy hh-MM-ss");
					
					
					fileObj.setFileName(list[i]);
					fileObj.setFileSize(tmpFile.length());
					fileObj.setIsFile(tmpFile.isFile());
					fileObj.setLastModify(dateFormat.format(date));
					fileObj.setWritePermission(tmpFile.canWrite());
					fileObj.setReadPermission(tmpFile.canRead());
					fileObj.setExecPermission(tmpFile.canExecute());
					fileObj.setFilePermission(
							(tmpFile.isFile() ? "-" : "d")
							+(PosixFilePermissions.toString(permission))
							);
					fileObj.setFileOwner(Files.getOwner(tmpPath).toString());
					
					logger.info("get file : "+fileObj);
					
					if(fileObj.getIsFile()){
						fileList.add(fileObj);
					} else {
						dirList.add(fileObj);
					}
					
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			returnList.addAll(dirList);
			returnList.addAll(fileList);
			
			return returnList;
			//return read path
		} else {
			
			
			return null;
			//return not readable
		}
		
	}
	
	public boolean deleteFile(String path){
		return (new File(path)).delete();
	}
	
	public boolean pathValidation(String path){
		return this.isFile(path)||this.isDir(path) ? true : false;
	}
	
	public boolean isDir(String path){
		File checkObj = new File(path);
		return checkObj.isDirectory() ? true : false;
	}
	
	public boolean isFile(String path){
		File checkObj = new File(path);
		return checkObj.isFile() ? true : false;
	}
}
