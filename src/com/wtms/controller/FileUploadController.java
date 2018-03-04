package com.wtms.controller;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import com.jfinal.core.Controller;
import com.jfinal.upload.UploadFile;
import com.wtms.bean.MessageBean;

public class FileUploadController extends Controller {

	public void index(){
		MessageBean resp = new MessageBean();
        try {
            UploadFile file = getFile();
            System.out.println("--------file--------");
            File delfile = file.getFile();
            System.out.println("=========="+delfile.getPath());
            resp.setCode(1);
            Map<String ,String> map = new HashMap<String, String>();
            map.put("filePath", delfile.getPath());
            map.put("fileSize", delfile.length()/1024+"");
            resp.setData(map);
        } catch (Exception e) {
            e.printStackTrace();
            resp.setMessage("文件上传失败");
        }
        
        renderJson(resp);
    }
}
