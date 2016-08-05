package org.iptime.dinky.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.inject.Inject;

import org.apache.commons.io.IOUtils;
import org.iptime.dinky.domain.FileObj;
import org.iptime.dinky.service.PathReaderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * Handles requests for the application home page.
 */
@Controller
public class PathController {
	
	private static final Logger logger = LoggerFactory.getLogger(PathController.class);
	
	private String savedPath = "/";
	private String changedPath = "";
	
	@Autowired
	PathReaderService pathService;
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		String targetPath = changedPath.equals("") ? savedPath : changedPath;
		
		model.addAttribute("path", targetPath);
		model.addAttribute("parentPath", savedPath.compareTo(targetPath)!=0 ? targetPath.replaceFirst("[\\w\\d._-]+/$", "") : null);
		model.addAttribute("pathLimit", savedPath);
		model.addAttribute("fileList", pathService.readPath(targetPath));
		
		model.addAttribute("serverTime", formattedDate );
		
		return "fileupload";
	}
	
	@RequestMapping(value="/getPath", method=RequestMethod.POST)
	public @ResponseBody ArrayList<FileObj> getPath(String pathValue){
		return pathService.readPath(pathValue);
	}
	
	@RequestMapping(value="/pathConfig", method=RequestMethod.POST)
	public String pathConfig(String path){
		
		this.savedPath = pathService.pathValidation(path) ? path : this.savedPath;
		this.changedPath = "";
		
		logger.info("savedPath : "+savedPath);
		logger.info("changePath reset : "+changedPath);
		
		return "redirect:/";
	}
	
	@RequestMapping(value="/pathChange", method=RequestMethod.GET)
	public String pathChange(String path, Model model){
		this.changedPath = pathService.isDir(path) ? path : this.changedPath;
		
		return "redirect:/";
	}
	
	@RequestMapping(value="/uploadFile", method=RequestMethod.POST)
	public String upload(MultipartFile file, String path, RedirectAttributes ra) throws Exception {
		logger.info("originalName: "+file.getOriginalFilename());
		logger.info("size : "+file.getSize());
		logger.info("contentType: "+file.getContentType());
		boolean result = doUploadFile(file.getOriginalFilename(),path , file.getBytes());
		logger.info("result : "+result);
		ra.addFlashAttribute("result", result ? "uploadSuccess" : "No permission. Fuck Off");
		
		return "redirect:/";
	}
	
	@RequestMapping(value="/deleteFile", method=RequestMethod.GET)
	public String deleteFile(Model model, String path){
		
		logger.info("delete req : "+path);
		logger.info("result : "+pathService.deleteFile(path));
		
		return "redirect:/";
	}
	
	private boolean doUploadFile(String fileName, String path, byte[] fileData) {
		File target = new File(path, fileName);
		try {
			FileCopyUtils.copy(fileData, target);
		} catch (Exception e) {
			//System.out.println(e.getMessage());
			e.printStackTrace();
			return false;
		}
		
		return true;
	}
	
	@ResponseBody
	@RequestMapping(value="/download", method=RequestMethod.GET)
	private ResponseEntity<byte[]> downloadFile(String fileName, String path) throws Exception{
		
		InputStream in = null;
		ResponseEntity<byte[]> entity = null;
		
		logger.info("path : "+path);
		logger.info("File name : "+fileName);
		
		try {
			HttpHeaders headers = new HttpHeaders();
			
			in = new FileInputStream(path+fileName);
			
			headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
			headers.add("Content-Disposition", "attachment; filename=\""+new String(fileName.getBytes("UTF-8"),"ISO-8859-1")+"\"");
			//추가작업 필요
			
			entity = new ResponseEntity<byte[]>(IOUtils.toByteArray(in), headers, HttpStatus.CREATED);
		} catch (Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<byte[]>(HttpStatus.BAD_REQUEST);
		} finally {
			in.close();
		}
		
		return entity;
	}
	
}
