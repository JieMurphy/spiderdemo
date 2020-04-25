package com.jie.service;

import com.jie.dao.ResourMapper;
import com.jie.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ResourService {
    public int 已通过 = 1;
    public int 待审核 = 0;
    public int 待删除 = -1;

    @Autowired
    private ResourMapper resourMapper;

    @Autowired
    private UserService userService;

    @Autowired
    private FileService fileService;

    public Resour findResourById(int id)
    {
        return resourMapper.findResourById(id);
    }

    public List<ResourModel> findResourByMatch(String data)
    {
        return resoursToModels(resourMapper.findResourByMatch(data));
    }

    public List<ResourModel> findResourByFilter(Classify classify)
    {
        SortModel sortModel = new SortModel();
        switch (classify.getLevel())
        {
            case 1:
                sortModel.setFirst(classify.getId());
                break;
            case 2:
                sortModel.setSecond(classify.getId());
                break;
            case 3:
                sortModel.setThird(classify.getId());
                break;
            case 4:
                sortModel.setForth(classify.getId());
                break;
                default:
                    break;
        }
        return resoursToModels(resourMapper.findByFilter(sortModel));
    }

    public boolean saveResour(Resour resour)
    {
        resour.setTitle("*");
        resour.setStatus(待删除);
        resourMapper.saveResour(resour);
        return true;
    }

    public List<ResourModel> findResourByUserid(int id)
    {
        return resoursToModels(resourMapper.findResourByUserid(id));
    }

    public boolean deleteResour(Resour resour,User user)
    {
        if(user.getPower() != userService.管理员 && resour.getUser_id() != user.getId())
        {
            return false;
        }
        fileService.delete(resour.getPath());
        resourMapper.deleteResour(resour.getId());
        return true;
    }

    public List<Resour> findAll()
    {
        return resourMapper.findAll();
    }

    public List<ResourModel> findByStatus(int status,User user)
    {
        if(user.getPower() == userService.普通用户)
        {
            return null;
        }
        return resoursToModels(resourMapper.findByStatus(status));
    }

    public boolean updateStatus(Resour resour,User user,int status)
    {
        if(user.getPower() == userService.普通用户)
        {
            return false;
        }
        resour.setStatus(status);
        resourMapper.updateStatusById(resour);
        return true;
    }

    public void updatePathEtc(Resour resour)
    {
        resourMapper.updatePathBodyFtype(resour);
    }

    public boolean updateTitleEtc(Resour resour)
    {
        Resour resour1 = resourMapper.findResourByPath(resour.getPath());
        if(resour1.getStatus() != 待删除)
        {
            return false;
        }
        resour.setId(resour1.getId());
        resour.setBody("简介：" + resour.getBody() + " " + resour1.getBody());
        resourMapper.updateTitleUserEtc(resour);
        resourMapper.updateClassifies(resour);
        resour.setStatus(待审核);
        resourMapper.updateStatusById(resour);
        return true;
    }

    public ResourModel resourToModel(Resour resour)
    {
        ResourModel resourModel = new ResourModel();
        resourModel.setTitle(resour.getTitle());
        resourModel.setBody(resour.getBody());
        resourModel.setFtype(resour.getFtype());
        resourModel.setId(resour.getId());
        resourModel.setPath(resour.getPath());

        User user = userService.findUserById(String.valueOf(resour.getUser_id()));
        resourModel.setUser_name(user.getName());
        return resourModel;
    }

    public List<ResourModel> resoursToModels(List<Resour> resours)
    {
        List<ResourModel> resourModels = new ArrayList<>();
        for(int i = 0;i < resours.size();i++)
        {
            resourModels.add(resourToModel(resours.get(i)));
        }
        return resourModels;
    }
}
