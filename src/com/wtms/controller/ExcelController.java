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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.jfinal.aop.Before;
import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.tx.Tx;
import com.sargeraswang.util.ExcelUtil.ExcelLogs;
import com.wtms.bean.StandardOperateTicketHeadBean;
import com.wtms.service.OperateTicketService;
import com.wtms.util.ExcelUtil;

public class ExcelController extends Controller{
	
	static OperateTicketService ticketService = new OperateTicketService();
	public static List<File> filelist = new ArrayList<File>();
	public static Pattern pt = Pattern.compile("^\\d{2,3}[a-zA-Z]{1}");
	public static String opTicketIdHead = "DR";

	public static void main(String[] args) throws FileNotFoundException {
		String path = "C:\\Users\\guoce\\Desktop\\0.4kV污泥干化#1、#2车间盘404L、404M抽屉式开关（国产施耐德）";
		List<File> files = getFileList(path);
		files.stream().forEach(System.out::println);
		Map<String, ArrayList<Map>> allMaps = new HashMap<String, ArrayList<Map>>();
		for (File f : files) {
		    InputStream inputStream= new FileInputStream(f);
		    
		    ExcelLogs logs =new ExcelLogs();
		    String key = "";  //这张操作票的idname
		    Map<String, ArrayList<Map>> importExcel = ExcelUtil.importExcel(Map.class, inputStream, "yyyy/MM/dd HH:mm:ss", logs, key, 0);
		    
		    allMaps.putAll(importExcel);
		}
		String segment = ""; //段
        
        StandardOperateTicketHeadBean headBean = new StandardOperateTicketHeadBean();
		
		for (String sk : allMaps.keySet()) {
			 String[] skList = sk.split("\\^");
			 {
				 headBean.setFactory(skList[0]);
				 headBean.setTicketNum(skList[1]);
				 headBean.setTicketType(skList[2]);
				 headBean.setUnit(skList[3]);
				 headBean.setSet(skList[4]);
				 headBean.setProfession(skList[5]);
				 headBean.setTask(skList[6]);
				 headBean.setSwitchName(skList[7]);
				 headBean.setSwitchNum(skList[8]);
				 headBean.setQRcode(skList[9]);
				 headBean.setSort(skList[10]);
				 headBean.setEditor(skList[11]);
				 headBean.setEditDate(skList[12]);
				 headBean.setReviewer(skList[13]);
				 headBean.setReviewDate(skList[14]);
				 headBean.setApprover(skList[15]);
				 headBean.setApproveDate(skList[16]);
			 }
			Matcher m = pt.matcher(skList[8]);
			if(m.find()){
				segment = m.group(0);
			}
			headBean.setSegment(segment);
			System.out.println(sk);

			System.out.println(allMaps.get(sk));

			System.out.println("====================================================================");
			//每个sheet执行一次落表
			for (Map contentMap : allMaps.get(sk)) {
				//查找数据库中最大的操作票id
			    int maxOTid = Integer.parseInt(ticketService.getMaxOperateTicketId());
			    String otid = opTicketIdHead+String.format("%08d", maxOTid+1);
			    contentMap.put("otid", otid);
				ticketService.saveOperateContentByMap(headBean,contentMap);
			}
		}
	}
	@Before(Tx.class)
	public static void opTicketImport(String path) throws FileNotFoundException{
		
		List<File> files = getFileList(path);
		files.stream().forEach(System.out::println);
		Map<String, ArrayList<Map>> allMaps = new HashMap<String, ArrayList<Map>>();
		for (File f : files) {
		    InputStream inputStream= new FileInputStream(f);
		    
		    ExcelLogs logs =new ExcelLogs();
		    String key = "";  //这张操作票的idname
		    Map<String, ArrayList<Map>> importExcel = ExcelUtil.importExcel(Map.class, inputStream, "yyyy/MM/dd HH:mm:ss", logs, key, 0);
		    
		    allMaps.putAll(importExcel);
		}
		String segment = ""; //段
        
        StandardOperateTicketHeadBean headBean = new StandardOperateTicketHeadBean();
		
		for (String sk : allMaps.keySet()) {
			 String[] skList = sk.split("\\^");
			 {
				 headBean.setFactory(skList[0]);
				 headBean.setTicketNum(skList[1]);
				 headBean.setTicketType(skList[2]);
				 headBean.setUnit(skList[3]);
				 headBean.setSet(skList[4]);
				 headBean.setProfession(skList[5]);
				 headBean.setTask(skList[6]);
				 headBean.setSwitchName(skList[7]);
				 headBean.setSwitchNum(skList[8]);
				 headBean.setQRcode(skList[9]);
				 headBean.setSort(skList[10]);
				 headBean.setEditor(skList[11]);
				 headBean.setEditDate(skList[12]);
				 headBean.setReviewer(skList[13]);
				 headBean.setReviewDate(skList[14]);
				 headBean.setApprover(skList[15]);
				 headBean.setApproveDate(skList[16]);
			 }
			Matcher m = pt.matcher(skList[8]);
			if(m.find()){
				segment = m.group(0);
			}
			headBean.setSegment(segment);
			System.out.println(sk);

			System.out.println(allMaps.get(sk));

			System.out.println("====================================================================");
			//支持重复导入，导入前把该段下 开关下 操作任务相同的删掉
			ticketService.deleteOperateContentByMap(headBean);
			//每个sheet执行一次落表
			int maxOTid = Integer.parseInt(ticketService.getMaxOperateTicketId());
			String otid = opTicketIdHead+String.format("%08d", maxOTid+1);
			for (Map contentMap : allMaps.get(sk)) {
				//查找数据库中最大的操作票id
			    contentMap.put("otid", otid);
				ticketService.saveOperateContentByMap(headBean,contentMap);
			}
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
