package com.jie.controller;

import com.jie.api.UserLoginToken;
import com.jie.model.CommonResult;
import com.jie.service.FileService;
import com.jie.service.Logger;
import com.jie.service.TokenService;
import com.jie.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Api(tags = "文件相关接口")
@RestController
@RequestMapping("/file")
public class FileController {

    @Autowired
    private Logger logger;

    @Autowired
    private FileService fileService;

    @ApiOperation(value = "上传文件",notes = "需要用户登录,仅接收文件，返回新的文件名")
    @UserLoginToken
    @PostMapping("")
    public CommonResult upload(@RequestParam("file")MultipartFile file)
    {
        String path = fileService.upload(file);
        if(path == null)
        {
            logger.logMessage("上传失败：",file.getOriginalFilename());
            return CommonResult.failed("上传失败");
        }
        logger.logMessage("上传成功：",file.getOriginalFilename());
        return  CommonResult.success(path);
    }

    @ApiOperation(value = "下载文件",notes = "需提供文件名，非文件全路径")
    @GetMapping("/{path}")
    public void download(@PathVariable String path,HttpServletResponse response) throws UnsupportedEncodingException {
        logger.logMessage("文件下载：",path);
        fileService.download(response,path);
    }
}
