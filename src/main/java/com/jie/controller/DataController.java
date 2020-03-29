package com.jie.controller;

import com.jie.api.UserLoginToken;
import com.jie.model.*;
import com.jie.service.Logger;
import com.jie.service.ResourService;
import com.jie.service.TokenService;
import com.jie.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api(tags = "大部分接口")
@RestController
public class DataController {
    @Autowired
    private UserService userService;

    @Autowired
    private Logger logger;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private ResourService resourService;

    @ApiOperation("注册")
    @PostMapping("/sign")
    public CommonResult sign(@RequestBody UserModel user)
    {
        logger.logMessage(user.getName() + "",user.getPassword());
        if(userService.saveUser(new User(user.getName(),user.getPassword())) == false)
        {
            logger.logMessage(user.getName(),"注册失败");
            return CommonResult.failed("用户名已存在！");
        }
        logger.logMessage(user.getName(),"注册成功");
        return CommonResult.success("注册成功");
    }

    @ApiOperation("登录")
    @PostMapping("/login")
    public CommonResult login(@RequestBody UserModel user){
        logger.logMessage(user.getName() + "",user.getPassword());
        if(userService.judge(user.getName(),user.getPassword()) == false)
        {
            logger.logMessage(user.getName(),"登录失败");
            return CommonResult.validateFailed();
        }
        JSONObject jsonObject = new JSONObject();
        String token = tokenService.getToken(userService.findUserByName(user.getName()));
        jsonObject.put("token",token);
        logger.logMessage(user.getName(),"登录成功");
        return CommonResult.success(jsonObject,user.getName());
    }

    @ApiOperation("修改密码")
    @UserLoginToken
    @PostMapping("/updatePWD")
    public CommonResult updatePWD(@RequestBody SearchModel searchModel)
    {
        User user = userService.findUserById(tokenService.getTokenUserId());
        user.setPassword(searchModel.getKeywords());
        if(userService.updateUser(user) == false)
        {
            logger.logMessage(user.getName(),"修改失败");
            return CommonResult.failed("修改失败！");
        }
        logger.logMessage("修改密码：",user.getName());
        return CommonResult.success("修改成功");
    }

    @ApiOperation("个人详情")
    @UserLoginToken
    @PostMapping("/person")
    public User logout()
    {
        User user = userService.findUserById(tokenService.getTokenUserId());
        logger.logMessage("个人详情：",user.getName());
        return user;
    }

    @ApiOperation("所有用户")
    @UserLoginToken
    @PostMapping("/users")
    public List<User> users()
    {
        User user = userService.findUserById(tokenService.getTokenUserId());
        if(user.getPower() != "管理员")
        {
            return null;
        }
        return userService.findAll();
    }

    @ApiOperation("上传")
    @UserLoginToken
    @PostMapping("/upload")
    public CommonResult upload(@RequestBody Resour resour)
    {
        User user = userService.findUserById(tokenService.getTokenUserId());
        resour.setUser_id(user.getId());
        logger.logMessage("上传：",user.getName());
        if(resourService.saveResour(resour) == false)
        {
            return CommonResult.failed("上传失败");
        }
        return CommonResult.success("上传成功，待审核");
    }

    @ApiOperation("删除资源")
    @UserLoginToken
    @PostMapping("/delete")
    public CommonResult delete(@RequestBody SearchModel searchModel)
    {
        logger.logMessage("删除：", searchModel.getKeywords());
        User user = userService.findUserById(tokenService.getTokenUserId());
        Resour resour1 = resourService.findResourById(searchModel.getKeywords());
        if(resour1 == null ||(user.getPower() != "管理员" && resour1.getUser_id() != user.getId()))
        {
            return CommonResult.success("删除失败");
        }
        resourService.deleteResour(resour1.getId());
        return CommonResult.success("删除成功");
    }

    @ApiOperation("上传记录")
    @UserLoginToken
    @PostMapping("/history")
    public List<ResourModel> history()
    {
        String id = tokenService.getTokenUserId();
        logger.logMessage("登出：",id);
        return resourService.findResourByUserid(Integer.parseInt(id));
    }

    @ApiOperation("关键字搜索")
    @PostMapping("/search")
    public List<ResourModel> search(@RequestBody SearchModel data)
    {
        logger.logMessage("关键字：",data.toString());
        return resourService.findResourByMatch(data.getKeywords());
    }

    @ApiOperation("分类搜索")
    @PostMapping("/filter")
    public List<ResourModel> filter(@RequestBody SortModel data)
    {
        logger.logMessage("分类：" ,data.toString());
        data.setFirst(1);
        return resourService.findResourByFilter(data);
    }

    @ApiOperation("查看资源详情")
    @PostMapping("/datails")
    public Resour datails(@RequestBody SearchModel searchModel)
    {
        return resourService.findResourById(searchModel.getKeywords());
    }

    @ApiOperation("审核中的资源")
    @UserLoginToken
    @PostMapping("/audit")
    public List<ResourModel> audit()
    {
        User user = userService.findUserById(tokenService.getTokenUserId());
        if(user.getPower() != "管理员")
        {
            return null;
        }
        return resourService.findByStatus("审核中");
    }

    @ApiOperation("通过资源")
    @UserLoginToken
    @PostMapping("/pass")
    public CommonResult pass(@RequestBody SearchModel searchModel)
    {
        User user = userService.findUserById(tokenService.getTokenUserId());
        Resour resour = resourService.findResourById(searchModel.getKeywords());
        if(user.getPower() != "管理员" || resour == null)
        {
            return CommonResult.failed("权限不足或资源不存在");
        }
        resour.setStatus("已通过");
        resourService.updateStatusById(resour);
        return CommonResult.success(resour.getTitle() + "审核通过");
    }

    @ApiOperation("砍掉资源")
    @UserLoginToken
    @PostMapping("/cut")
    public CommonResult cut(@RequestBody SearchModel searchModel)
    {
        User user = userService.findUserById(tokenService.getTokenUserId());
        Resour resour = resourService.findResourById(searchModel.getKeywords());
        if(user.getPower() != "管理员" || resour == null)
        {
            return CommonResult.failed("权限不足或资源不存在");
        }
        resour.setStatus("待删除");
        resourService.updateStatusById(resour);
        return CommonResult.success(resour.getTitle() + "待删除");
    }

    @ApiOperation("所有资源")
    @UserLoginToken
    @PostMapping("/resources")
    public List<Resour> resources()
    {
        User user = userService.findUserById(tokenService.getTokenUserId());
        if(user.getPower() != "管理员")
        {
            return null;
        }
        return resourService.findAll();
    }
}
