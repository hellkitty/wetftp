package org.iptime.dinky;

import java.io.File;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

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
	public @ResponseBody ArrayList<String> getPath(String pathValue){
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
	public String upload(MultipartFile file) throws Exception {
		logger.info("originalName: "+file.getOriginalFilename());
		logger.info("size : "+file.getSize());
		logger.info("contentType: "+file.getContentType());
		
		doUploadFile(file.getOriginalFilename(), file.getBytes());
		
		return "redirect:/";
	}
	
	private void doUploadFile(String fileName, byte[] fileData) throws Exception {
		File target = new File(savedPath, fileName);
		FileCopyUtils.copy(fileData, target);
	}
	
}
