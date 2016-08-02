package org.iptime.dinky;

import java.io.File;
import java.util.ArrayList;

import org.springframework.stereotype.Service;

@Service
public class PathReaderService {

	public ArrayList<String> readPath(String path){
		//String path1 = "/data/db";
		
		//assertEquals(true, pathValidation("/data/db"));
		
		if(pathValidation(path)){
			
			File reqPath = new File(path);
			
			String[] list = reqPath.list();
			ArrayList<String> returnList = new ArrayList<String>();
			
			try {
				for (int i = 0; i < list.length; i++) {
					returnList.add(list[i]);
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
