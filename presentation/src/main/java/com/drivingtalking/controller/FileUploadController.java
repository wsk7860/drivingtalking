package com.drivingtalking.controller;

import com.drivingtalking.exception.ControllerException;
import com.drivingtalking.service.IFileUploadService;
import com.drivingtalking.util.ContextManager;
import com.drivingtalking.util.FileUploadModel;
import com.drivingtalking.util.GridFsMetaData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Iterator;

//@Controller
//@RequestMapping("/file")
public class FileUploadController {

    @Autowired
    private IFileUploadService fileUploadService;

    @RequestMapping("/upload")
    public String upload(HttpServletRequest request) throws Exception {

        String memberId = ContextManager.getSessionMember().getId();
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());

        String fileId = null;
        if(multipartResolver.isMultipart(request)) {
            MultipartHttpServletRequest multiRequest=(MultipartHttpServletRequest)request;
            Iterator iter=multiRequest.getFileNames();
            while(iter.hasNext())
            {
                //一次遍历所有文件
                MultipartFile file=multiRequest.getFile(iter.next().toString());
                if(file!=null)
                {
                    fileId = fileUploadService.saveFile(file.getInputStream(),file.getName(),file.getContentType(),new GridFsMetaData(memberId));
                }
            }
        }
        if(fileId == null) {
            throw  new ControllerException("上传文件失败");
        }

        return fileId;
    }

    @RequestMapping("/show/{id}")
    public void show(@PathVariable("id") String id, HttpServletResponse response) throws IOException {
        FileUploadModel file = fileUploadService.findById(id);
        writeToResponse(file,response);
    }

    @RequestMapping("/download/{id}")
    public void download(@PathVariable("id") String id,HttpServletResponse response) throws IOException{
        FileUploadModel file = fileUploadService.findById(id);
        response.setCharacterEncoding("utf-8");
        response.setContentType("multipart/form-data");
        response.setHeader("Content-Disposition", "attachment;fileName=" + file.getFileName());
        writeToResponse(file,response);
    }

    private void writeToResponse(FileUploadModel file,HttpServletResponse response)  throws IOException{
        OutputStream outputStream = response.getOutputStream();
        int count;
        byte[] buffer = new byte[1024 * 8];
        while ((count = file.getInputStream().read(buffer)) != -1) {
            outputStream.write(buffer,0,count);
        }
        outputStream.flush();
        outputStream.close();
        file.getInputStream().close();
    }
}
