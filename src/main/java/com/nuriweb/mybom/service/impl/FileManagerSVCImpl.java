package com.nuriweb.mybom.service.impl;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.nuriweb.mybom.service.inf.IFileManageSVC;

@Service
public class FileManagerSVCImpl implements IFileManageSVC {


	@Override											//subPath => board or center			
	public String writeUploadFile(MultipartFile upfile, String realPath,String subPath) {
	
		if(upfile!= null && !upfile.isEmpty()) {
			
			String originFile = upfile.getOriginalFilename(); //대화상자로 선택한 파일명..
			String fileName = makeUniqueFileName(originFile);
			realPath+= fileName; 
			
			System.out.println(">> 파일 경로 realPath: "+ realPath);
			
			File dest = new File(realPath);
			
			try {
				
				upfile.transferTo(dest);
				String filePath = subPath+"/"+fileName;
				System.out.println(">> SVCImpl filePath: "+filePath);
				return filePath;
				
			} catch (IllegalStateException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
		return null;
	}
	
	
	// 센터 어드민 파일등록
	@Override														
	public String writeCenterFile(MultipartFile upfile, String realPath, String destination) {
	
		if(upfile!= null && !upfile.isEmpty()) {
			
			String originFile = upfile.getOriginalFilename(); //대화상자로 선택한 파일명..
			String fileName = makeCenterFileName(originFile);
			realPath+= fileName;
			
			System.out.println(">> 파일 경로 realPath: "+ realPath);
			
			File dest = new File(realPath);
			
			try {
				
				upfile.transferTo(dest);
				String filePath = destination+"/"+fileName;
				
				return filePath;
				
			} catch (IllegalStateException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
		return null;
	}
	
	// 센터등록시 고유파일네임생성
	public String makeCenterFileName(String originFile) {
		
		String origin = originFile.toLowerCase(); //대화상자로 선택한 파일
		int lastExtPos = origin.lastIndexOf(".");
		String oriFileName =origin.substring(0,lastExtPos); //확장자 제외 파일명
		String Ext = origin.substring(lastExtPos+1); //png,jpg...
		
		StringBuffer sb = new StringBuffer();
		sb.append(DEF_UPLOAD_PREFIX);
		sb.append(oriFileName+".");
		sb.append(Ext);
		
		System.out.println(">> 센터파일 생성 네임: "+ sb.toString());
		return sb.toString();
	} 


	

public String makeUniqueFileName(String originFile) {
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		String time = sdf.format(new Date()); 
		
		String origin = originFile.toLowerCase(); //대화상자로 선택한 파일
		int lastExtPos = origin.lastIndexOf(".");
		String oriFileName =origin.substring(0,lastExtPos); //확장자 제외 파일명
		String Ext = origin.substring(lastExtPos+1); //png,jpg...
		
		StringBuffer sb = new StringBuffer();
		sb.append(DEF_UPLOAD_PREFIX);
		sb.append(oriFileName+"_");
		sb.append(UUID.randomUUID().toString()+"_");
		sb.append(time+".");
		sb.append(Ext);
		
		System.out.println(">> 파일 생성 네임: "+ sb.toString());
		return sb.toString();
	} 

	
	
}//class


	