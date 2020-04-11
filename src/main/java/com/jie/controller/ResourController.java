package com.jie.controller;

import com.jie.api.UserLoginToken;
import com.jie.model.*;
import com.jie.service.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Api(tags = "资源相关接口")
@RestController
@RequestMapping("/resources")
public class ResourController {
    @Autowired
    private UserService userService;

    @Autowired
    private Logger logger;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private ResourService resourService;

    @Autowired
    private ClassifyService classifyService;

    @ApiOperation(value = "所有资源",notes = "")
    @GetMapping({""})
    public List<Resour> resources()
    {
        return resourService.findAll();
    }

    @ApiOperation(value = "上传记录",notes = "需要用户登录")
    @UserLoginToken
    @GetMapping("/history")
    public List<ResourModel> history()
    {
        String id = tokenService.getTokenUserId();
        logger.logMessage("历史记录：",id);
        return resourService.findResourByUserid(Integer.parseInt(id));
    }

    @ApiOperation("关键字搜索")
    @GetMapping("/search/{keywords}")
    public List<ResourModel> search(@PathVariable String keywords)
    {
        logger.logMessage("关键字：",keywords);
        return resourService.findResourByMatch(keywords);
    }

    @ApiOperation("分类搜索")
    @GetMapping("/filter/{class_id}")
    public List<ResourModel> filter(@PathVariable int class_id)
    {
        Classify classify = classifyService.findById(class_id);
        logger.logMessage("分类：" ,classify + " ");
        return resourService.findResourByFilter(classify);
    }

    @ApiOperation("查看资源详情")
    @GetMapping("/{id}")
    public Resour datails(@PathVariable int id)
    {
        return resourService.findResourById(id);
    }

    @ApiOperation(value = "审核中的资源",notes = "需要以超级用户以上身份登录")
    @UserLoginToken
    @GetMapping("/audit")
    public List<ResourModel> audit()
    {
        User user = userService.findUserById(tokenService.getTokenUserId());
        return resourService.findByStatus(resourService.待审核,user);
    }

    @ApiOperation(value = "通过资源",notes = "需要以超级用户以上身份登录")
    @UserLoginToken
    @PutMapping("/pass")
    public CommonResult pass(@RequestParam int id)
    {
        User user = userService.findUserById(tokenService.getTokenUserId());
        Resour resour = resourService.findResourById(id);
        if(resour == null || resourService.updateStatus(resour,user,resourService.已通过) == false)
        {
            return CommonResult.failed("权限不足或资源不存在");
        }
        return CommonResult.success(resour.getTitle() + "审核通过");
    }

    @ApiOperation(value = "砍掉资源",notes = "需要以超级用户以上身份登录")
    @UserLoginToken
    @PutMapping("/cut")
    public CommonResult cut(@RequestParam int id)
    {
        User user = userService.findUserById(tokenService.getTokenUserId());
        Resour resour = resourService.findResourById(id);
        if(resour == null || resourService.updateStatus(resour,user,resourService.待删除) == false)
        {
            return CommonResult.failed("权限不足或资源不存在");
        }
        return CommonResult.success(resour.getTitle() + "待删除");
    }

    @ApiOperation(value = "上传",notes = "需要用户登录")
    @UserLoginToken
    @PostMapping("")
    public CommonResult upload(@RequestBody Resour resource)
    {
        User user = userService.findUserById(tokenService.getTokenUserId());
        resource.setUser_id(user.getId());
        logger.logMessage("上传：",user.getName() + resource.getTitle());
        if(resourService.saveResour(resource) == false)
        {
            return CommonResult.failed("上传失败");
        }

        return CommonResult.success("上传成功，待审核");
    }

    @ApiOperation(value = "删除资源",notes = "需要超级用户以上权限")
    @UserLoginToken
    @DeleteMapping("/{id}")
    public CommonResult delete(@PathVariable int id)
    {
        logger.logMessage("删除：", id + " ");
        User user = userService.findUserById(tokenService.getTokenUserId());
        Resour resour = resourService.findResourById(id);
        if(resour == null ||resourService.deleteResour(resour,user) == false)
        {
            return CommonResult.success("删除失败");
        }
        return CommonResult.success("删除成功");
    }

}
