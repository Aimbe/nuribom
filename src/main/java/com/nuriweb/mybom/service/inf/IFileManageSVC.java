package com.nuriweb.mybom.service.inf;



import org.springframework.web.multipart.MultipartFile;

public interface IFileManageSVC {
	
//	C:\dev2020\spring_ws\.metadata\.plugins\org.eclipse.wst.server.core\tmp0\
//	wtpwebapps\NuriBom\resources\images\center
	public static final String DEF_CT_DEST = "/resources/images/center";
	public static final String DEF_CT_PRO_DEST = "/resources/images/center/programs";
	
	String DEF_UPLOAD_DEST = "/uploads";
	String DEF_UPLOAD_PREFIX = "UP_";
	
	String DEF_BD_DEST = "/resources/images/board";
	

	String BOARD ="board";
	String CENTER ="center";
	
	
	//단일파일 처리  									-subPath => board or center
	String writeUploadFile(MultipartFile upfile, String realPath,String subPath);

	String writeCenterFile(MultipartFile upfile, String realPath, String destination);

	
}
