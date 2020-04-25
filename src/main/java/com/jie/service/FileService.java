package com.jie.service;

import com.jie.model.Resour;
import org.apache.tika.Tika;
import org.apache.tika.exception.TikaException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Component
public class FileService {
    static private String path = "E:\\新建文件夹 (2)\\VS2012_ULT_chs\\";
    //static private String path = "/root/resources/";

    @Autowired
    private ResourService resourService;

    public String upload(MultipartFile file)
    {
        if(file.isEmpty())
        {
            return "";
        }
        Resour resour = new Resour();
        resourService.saveResour(resour);

        String fileName = resour.getId() + "_" + file.getOriginalFilename();
        String filePath = path + fileName;
        if(write(file,filePath) == false)
        {
            return "";
        }
        resour.setPath(fileName);
        resour.setFtype(getFileType(filePath));
        resour.setBody(getContext(filePath));
        resourService.updatePathEtc(resour);
        return fileName;
    }

    public boolean write(MultipartFile file,String filePath)
    {
        File dest = new File(filePath);
        if(dest.getParentFile().exists() == false)
        {
            dest.getParentFile().mkdir();
        }
        try {
            file.transferTo(dest);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    public void download(HttpServletResponse response,String fileName) throws UnsupportedEncodingException {
        if(fileName != null)
        {
            Path file = Paths.get(path,fileName);
            if(Files.exists(file))
            {
                fileName = fileName.substring(fileName.indexOf("_") + 1);
                response.setContentType("application/octet-stream;charset=utf-8");
                response.addHeader("Content-Disposition", String.format("attachment; filename=\"%s\"", URLEncoder.encode(fileName, "utf-8")));
                try {
                    Files.copy(file,response.getOutputStream());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public boolean delete(String fileName)
    {
        String filePath = path + fileName;
        File file = new File(filePath);
        if(file.exists() == false || file.delete() == false)
        {
            return false;
        }
        return true;
    }

    public String getFileType(String filePath)
    {
        File file= new File(filePath);
        Tika tika = new Tika();
        try {
            String fileType = tika.detect(file);
            if(fileType != null)
            {
                return fileType;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

    public String getContext(String filePath)
    {
        File file = new File(filePath);
        Tika tika = new Tika();
        try {
            String fileContext = tika.parseToString(file);
            return fileContext;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TikaException e) {
            return "";
        }
        return "";
    }
}
