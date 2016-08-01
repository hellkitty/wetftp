package org.iptime.dinky;

import java.io.File;

import org.junit.Test;
import static org.junit.Assert.*;

public class PathReaderTest {

	@Test
	public String[] readPath(){
		String path1 = "/data/db";
		
		//assertEquals(true, pathValidation("/data/db"));
		
		if(pathValidation(path1)){
			
			File reqPath = new File(path1);
			
			return reqPath.list();
			//return read path
		} else {
			
			
			return null;
			//return not readable
		}
		
	}
	
	public boolean pathValidation(String path){
		File checkObj = new File(path);
		return checkObj.isFile()||checkObj.isDirectory() ? true : false;
	}
	
	public String[] readPathFiles(String path){
		return null;
	}
}
