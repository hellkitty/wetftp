package org.iptime.dinky;

import java.io.File;
import java.util.ArrayList;

import org.iptime.dinky.domain.FileObj;
import org.springframework.stereotype.Service;

@Service
public class PathReaderService {

	public ArrayList<FileObj> readPath(String path){
		//String path1 = "/data/db";
		
		//assertEquals(true, pathValidation("/data/db"));
		
		if(pathValidation(path)){
			
			File reqPath = new File(path);
			
			String[] list = reqPath.list();
			ArrayList<FileObj> returnList = new ArrayList<FileObj>();
			
			try {
				for (int i = 0; i < list.length; i++) {
					File tmpFile = new File(path+list[i]);
					returnList.add(new FileObj(list[i], tmpFile.length(), tmpFile.isFile()));
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			
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
