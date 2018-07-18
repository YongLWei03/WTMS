package com.wtms.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.log4j.Logger;

import com.jfinal.core.Controller;
import com.jfinal.kit.PathKit;
import com.jfinal.upload.UploadFile;
import com.wtms.bean.MessageBean;
import com.wtms.service.UserService;

public class FileUploadController extends Controller {
	
//	private final Logger logger = LoggerFactory.getLogger(FileUploadController.class);
	
	private final Logger logger = Logger.getLogger(FileUploadController.class);
	
	static UserService userService = new UserService();

	public void index(){
		logger.error("文件导入开始");
		MessageBean resp = new MessageBean();
        try {
        	String dirStr = PathKit.getWebRootPath()+File.separator+"upload";
        	File uploadDir = new File(dirStr);
        	if(uploadDir.isDirectory()){
        		for (File f : uploadDir.listFiles()) {
					f.delete();
				}
        	}
            UploadFile file = getFile();
            System.out.println("--------file--------");
            // 获取文件后缀名并转化为小写,用于校验
            String fileName = file.getFileName();
            String fileType = fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length()).toLowerCase();
            if(!fileType.equals("xlsx")&&!fileType.equals("xls")){
            	resp.setCode(0);
            	resp.setMessage("文件格式错误");
            	return;
            }
            File delfile = file.getFile();
            System.out.println("=========="+delfile.getPath());
            Map<String ,String> map = new HashMap<String, String>();
            map.put("filePath", delfile.getPath());
            map.put("fileSize", delfile.length()/1024+"");
            
            ExcelController.opTicketImport(dirStr);
            
            resp.setCode(1);
            resp.setData(map);
            resp.setMessage("文件上传成功");
        } catch (Exception e) {
            e.printStackTrace();
            resp.setMessage("文件上传失败");
        }
        
        renderJson(resp);
    }
	
	public void importExcel() throws FileNotFoundException{
		String path = "C:\\Users\\guoce\\Desktop\\0.4kV污泥干化#1、#2车间盘404L、404M抽屉式开关（国产施耐德）";
		ExcelController.opTicketImport(path);
	}
}
