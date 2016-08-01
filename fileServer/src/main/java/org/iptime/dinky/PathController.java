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
	
	private String savePath = "/";
	
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
		String[] pathList = savePath.split("/");
//		List<String> pathList = new ArrayList<String>();
		model.addAttribute("pathList", pathList);
		model.addAttribute("path", savePath);
		model.addAttribute("list", pathService.readPath(savePath));
		
		model.addAttribute("serverTime", formattedDate );
		
		return "fileupload";
	}
	
	@RequestMapping(value="/getPath", method=RequestMethod.POST)
	public @ResponseBody ArrayList<String> getPath(String pathValue){
		return pathService.readPath(pathValue);
	}
	
	@RequestMapping(value="/pathConfig", method=RequestMethod.POST)
	public String pathConfig(String path){
		
		this.savePath = pathService.pathValidation(path) ? path : this.savePath;
		
		logger.info("savePath : "+savePath);
		
		return "redirect:/";
	}
	
	@RequestMapping(value="/pathChange", method=RequestMethod.GET)
	public String pathChange(String path, Model model){
		
		
		
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
		File target = new File(savePath, fileName);
		FileCopyUtils.copy(fileData, target);
	}
	
}
