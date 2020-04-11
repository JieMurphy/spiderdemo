package com.jie.controller;

import com.jie.api.UserLoginToken;
import com.jie.model.CommonResult;
import com.jie.model.User;
import com.jie.model.UserModel;
import com.jie.service.Logger;
import com.jie.service.TokenService;
import com.jie.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "用户相关接口")
@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private Logger logger;

    @Autowired
    private TokenService tokenService;

    @ApiOperation(value = "注册",notes = "只需填用户名、密码与邮箱")
    @PostMapping("/sign")
    public CommonResult sign(@RequestBody User user)
    {
        logger.logMessage(user.getName() + "",user.getPassword());
        if(userService.saveUser(new User(user.getName(),user.getPassword(),user.getEmail())) == false)
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

    @ApiOperation(value = "修改密码",notes = "需要登录，只需填新的密码")
    @UserLoginToken
    @PutMapping({""})
    public CommonResult updatePWD(@RequestBody UserModel userModel)
    {
        User user = userService.findUserById(tokenService.getTokenUserId());
        user.setPassword(userModel.getPassword());
        if(userService.updateUser(user) == false)
        {
            logger.logMessage(user.getName(),"修改失败");
            return CommonResult.failed("修改失败！");
        }
        logger.logMessage("修改密码：",user.getName());
        return CommonResult.success("修改成功");
    }

    @ApiOperation(value = "修改权限为超级用户",notes = "需要管理员权限")
    @UserLoginToken
    @PutMapping("/up/{id}")
    public CommonResult upPower(@PathVariable String id)
    {
        User user1 = userService.findUserById(tokenService.getTokenUserId());
        User user = userService.findUserById(id);
        user.setPower(userService.超级用户);
        if(userService.updateUser(user,user1) == false)
        {
            logger.logMessage(user.getName(),"修改失败");
            return CommonResult.failed("修改失败！");
        }
        logger.logMessage(user.getName(),"修改成功");
        return CommonResult.failed("修改成功！");
    }

    @ApiOperation(value = "修改权限为普通用户",notes = "需要管理员权限")
    @UserLoginToken
    @PutMapping("/down/{id}")
    public CommonResult downPower(@PathVariable String id)
    {
        User user1 = userService.findUserById(tokenService.getTokenUserId());
        User user = userService.findUserById(id);
        user.setPower(userService.普通用户);
        if(userService.updateUser(user,user1) == false)
        {
            logger.logMessage(user.getName(),"修改失败");
            return CommonResult.failed("修改失败！");
        }
        logger.logMessage(user.getName(),"修改成功");
        return CommonResult.failed("修改成功！");
    }

    @ApiOperation(value = "获取某个用户信息",notes = "需要以管理员身份登录")
    @GetMapping("/{id}")
    @UserLoginToken
    public User personal(@PathVariable int id)
    {
        User user = userService.findUserById(tokenService.getTokenUserId());
        logger.logMessage("个人详情：",user.getName());
        return userService.findUserById(String.valueOf(id),user);
    }

    @ApiOperation(value = "所有用户信息",notes = "需要以管理员身份登录")
    @GetMapping({""})
    @UserLoginToken
    public List<User> users()
    {
        User user = userService.findUserById(tokenService.getTokenUserId());
        return userService.findAll(user);
    }


    @ApiOperation(value = "删除用户",notes = "需要以管理员身份登录")
    @UserLoginToken
    @DeleteMapping(value = "/{id}")
    public CommonResult deleteUser(@PathVariable int id)
    {
        User user = userService.findUserById(tokenService.getTokenUserId());
        if(userService.deleteUser(id,user) == false)
        {
            logger.logMessage(user.getName(),"删除失败");
            return CommonResult.failed("删除失败！");
        }
        logger.logMessage(user.getName(),"删除成功");
        return CommonResult.success("删除成功！");
    }
}
