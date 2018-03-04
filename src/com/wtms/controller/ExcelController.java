package com.wtms.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;

import com.jfinal.aop.Before;
import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.tx.Tx;
import com.sargeraswang.util.ExcelUtil.ExcelLogs;
import com.wtms.service.OperateTicketService;
import com.wtms.util.ExcelUtil;

public class ExcelController extends Controller{
	
	static OperateTicketService brolesService = new OperateTicketService();
	public static List<File> filelist = new ArrayList<File>();

	public static void main(String[] args) throws FileNotFoundException {
		String path = "C:\\Users\\guoce\\Desktop\\0.4kV污泥干化#1、#2车间盘404L、404M抽屉式开关（国产施耐德）";
		List<File> files = getFileList(path);
		files.stream().forEach(System.out::println);
		List<HashMap<String, ArrayList<HashMap>>> allList = new ArrayList<HashMap<String, ArrayList<HashMap>>>();
		for (File f : files) {
		    InputStream inputStream= new FileInputStream(f);
		    
		    ExcelLogs logs =new ExcelLogs();
		    String key = "";  //这张操作票的idname
		    List<Map<String, ArrayList<Map>>> importExcel = ExcelUtil.importExcel(Map.class, inputStream, "yyyy/MM/dd HH:mm:ss", logs, key, 0);
		    
		    //查找数据库中最大的操作票id
//		    int newOTid = Integer.parseInt(brolesService.getMaxOperateTicketId());
		    allList.add((HashMap<String, ArrayList<HashMap>>) importExcel);
		    for(Map<String, ArrayList<Map>> lm : importExcel){
//				for (Map m : lm) {
//					 m.put("otid", newOTid);
				     System.out.println(lm.keySet());
//				     brolesService.saveOperateContentByMap(m);
//				}
		    }
		}
	}
	@Before(Tx.class)
	public static void test() throws FileNotFoundException{

		File f=new File("C:\\Users\\guoce\\Desktop\\0.4kV污泥干化#1、#2车间盘404L、404M抽屉式开关（国产施耐德）\\0.4化学污泥干化抽屉式开关检修转热备用.xls");
	    InputStream inputStream= new FileInputStream(f);
	    
	    ExcelLogs logs =new ExcelLogs();
	    String key = "";
	    Collection<Map> importExcel = ExcelUtil.importExcel(Map.class, inputStream, "yyyy/MM/dd HH:mm:ss", logs,key, 0);
	    
	    //查找数据库中最大的操作票id
	    int newOTid = Integer.parseInt(brolesService.getMaxOperateTicketId());
	    
	    for(Map m : importExcel){
	      m.put("otid", newOTid);
	      System.out.println(m);
	      brolesService.saveOperateContentByMap(m);
	    }
	
	}
	
	public static List<File> getFileList(String strPath) {
        File dir = new File(strPath);
        File[] files = dir.listFiles(); // 该文件目录下文件全部放入数组
        if (files != null) {
            for (int i = 0; i < files.length; i++) {
                String fileName = files[i].getName();
                if (files[i].isDirectory()) { // 判断是文件还是文件夹
                    getFileList(files[i].getAbsolutePath()); // 获取文件绝对路径
                } else if (fileName.endsWith("xls")) { // 判断文件名是否以.avi结尾
                    String strFileName = files[i].getAbsolutePath();
                    System.out.println("---" + strFileName);
                    filelist.add(files[i]);
                } else {
                    continue;
                }
            }

        }
        return filelist;
    }

}
