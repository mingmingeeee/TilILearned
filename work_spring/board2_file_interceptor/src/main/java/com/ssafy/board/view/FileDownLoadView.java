package com.ssafy.board.view;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.util.FileCopyUtils;
import org.springframework.web.servlet.view.AbstractView;

public class FileDownLoadView extends AbstractView {

	public FileDownLoadView() {
		setContentType("apllication/download; charset=UTF-8");
	}
	
	@Override
	protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request, HttpServletResponse response) throws Exception {
		// 가상의 뷰 작성 => 다운로드할 때의 내용
		ServletContext ctx = getServletContext();
		String realPath = ctx.getRealPath("/upload"); // upload의 실제 경로 얻어내기
		
		Map<String, Object> fileInfo = (Map<String, Object>) model.get("downloadFile"); // 전송받은 모델(파일 정보)
        
        String saveFolder = (String) fileInfo.get("sfolder");	// 파일 경로
        String originalFile = (String) fileInfo.get("ofile");	// 원본 파일명(화면에 표시될 파일 이름)
        String saveFile = (String) fileInfo.get("sfile");    	// 암호화된 파일명(실제 저장된 파일 이름)
        File file = new File(realPath + File.separator  + saveFolder, saveFile);
		
        response.setContentType(getContentType());
        response.setContentLength((int) file.length());
        
        String header = request.getHeader("User-Agent");
        boolean isIE = header.indexOf("MSIE") > -1 || header.indexOf("Trident") > -1;
        String fileName = null;
        // IE는 다르게 처리
        if (isIE) {
        	fileName = URLEncoder.encode(originalFile, "UTF-8").replaceAll("\\+", "%20"); // internet explore냐?
        } else {
            fileName = new String(originalFile.getBytes("UTF-8"), "ISO-8859-1"); // 아니면 이렇게 처리 
        }
        
        // attachment 내가 다운받으려는 filename 알려줌 
        // headr의 속성이 무얼 의미하는 지 찾아보긔~
        response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\";");
        response.setHeader("Content-Transfer-Encoding", "binary"); // binary로 바꾸게 되면 그냥 다운로드로 바뀌어버림!
        OutputStream out = response.getOutputStream(); // output stream 만들고
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(file); 
            FileCopyUtils.copy(fis, out); // 실질적으로 ouput에서 보내주는 걸 받아서 내 컴퓨터에 다운받게 됨
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if(fis != null) {
                try { 
                    fis.close(); 
                }catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        out.flush();
    }
}
