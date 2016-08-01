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
			
			for (int i = 0; i < list.length; i++) {
				returnList.add(list[i]);
			}
			
			return returnList;
			//return read path
		} else {
			
			
			return null;
			//return not readable
		}
		
	}
	
	public boolean pathValidation(String path){
		System.out.println(path);
		File checkObj = new File(path);
		return checkObj.isFile()||checkObj.isDirectory() ? true : false;
	}
}
