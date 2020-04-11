package com.jie.controller;

import com.jie.api.UserLoginToken;
import com.jie.model.Classify;
import com.jie.model.CommonResult;
import com.jie.model.User;
import com.jie.service.ClassifyService;
import com.jie.service.Logger;
import com.jie.service.TokenService;
import com.jie.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "分类相关接口")
@RestController
@RequestMapping("/classifies")
public class ClassifyController {
    @Autowired
    private ClassifyService classifyService;

    @Autowired
    private UserService userService;

    @Autowired
    private Logger logger;

    @Autowired
    private TokenService tokenService;

    @ApiOperation(value = "所有分类",notes = "首页的id为1，它没有父类")
    @GetMapping({""})
    public List<Classify> getAll()
    {
        return classifyService.findAll();
    }

    @ApiOperation(value = "获取子类",notes = "输入一个父类的id，返回它的所有子类")
    @GetMapping("/{id}")
    public List<Classify> getChilds(@PathVariable int id)
    {
        return classifyService.findByParentId(id);
    }

    @ApiOperation(value = "创建分类",notes = "需要以管理员身份登录")
    @UserLoginToken
    @PostMapping("")
    public CommonResult addClassify(@RequestBody Classify classify)
    {
        User user = userService.findUserById(tokenService.getTokenUserId());
        if(classifyService.saveClassify(classify,user) == false)
        {
            logger.logUsers(user.getName(),"创建分类失败");
            return CommonResult.failed("权限不足");
        }
        logger.logUsers(user.getName(),"创建分类成功");
        return CommonResult.success("创建成功");
    }

    @ApiOperation(value = "更新分类",notes = "需要以管理员身份登录")
    @UserLoginToken
    @PutMapping("/{id}")
    public CommonResult updateClassify(@PathVariable int id,@RequestBody Classify classify)
    {
        User user = userService.findUserById(tokenService.getTokenUserId());
        classify.setId(id);
        if(classifyService.updateType(classify,user) == false)
        {
            logger.logUsers(user.getName(),"更新分类失败");
            return CommonResult.failed("权限不足");
        }
        logger.logUsers(user.getName(),"更新分类成功");
        return CommonResult.success("更新成功");
    }

    @ApiOperation(value = "删除分类",notes = "需要以管理员身份登录")
    @UserLoginToken
    @DeleteMapping(value = "/{id}")
    public CommonResult deleteClassify(@PathVariable int id)
    {
        User user = userService.findUserById(tokenService.getTokenUserId());
        if(classifyService.deleteClassify(id,user) == false)
        {
            logger.logUsers(user.getName(),"删除分类失败");
            return CommonResult.failed("权限不足");
        }
        logger.logUsers(user.getName(),"删除分类成功");
        return CommonResult.success("删除成功");
    }
}
