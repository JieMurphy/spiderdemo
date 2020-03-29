package com.jie.service;

import com.jie.dao.ResourMapper;
import com.jie.model.Resour;
import com.jie.model.ResourModel;
import com.jie.model.SortModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ResourService {
    @Autowired
    private ResourMapper resourMapper;

    public Resour findResourById(String id)
    {
        return resourMapper.findResourById(Integer.parseInt(id));
    }

    public List<ResourModel> findResourByMatch(String data)
    {
        return resourMapper.findResourByMatch(data);
    }

    public List<ResourModel> findResourByFilter(SortModel sortModel)
    {
        return resourMapper.findByFilter(sortModel);
    }

    public boolean saveResour(Resour resour)
    {
        if(resour.getTitle() == null || resour.getFirst() == null || resour.getSecond() == null || resour.getThird() == null || resour.getForth() == null)
        {
            return false;
        }
        resourMapper.saveResour(resour);
        return true;
    }

    public List<ResourModel> findResourByUserid(int id)
    {
        return resourMapper.findResourByUserid(id);
    }

    public void deleteResour(int id)
    {
        resourMapper.deleteResour(id);
    }

    public List<Resour> findAll()
    {
        return resourMapper.findAll();
    }

    public List<ResourModel> findByStatus(String status)
    {
        return resourMapper.findByStatus(status);
    }

    public void updateStatusById(Resour resour)
    {
        resourMapper.updateStatusById(resour);
    }
}
