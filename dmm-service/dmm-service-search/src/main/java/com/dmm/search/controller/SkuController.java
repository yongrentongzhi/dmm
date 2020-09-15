package com.dmm.search.controller;


import com.dmm.common.pojo.Result;
import com.dmm.common.pojo.StatusCode;
import com.dmm.search.service.SkuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;



@RestController
@CrossOrigin
@RequestMapping("/search")
public class SkuController {

    @Autowired
    private SkuService skuService;

    @RequestMapping("/import/{spuId}")
    public Result importEs(@PathVariable Long spuId) {

        skuService.importEs(spuId);
        return new Result( StatusCode.OK,true, "导入成功");
    }
    @RequestMapping("/export/{spuId}")
    public Result exportEs(@PathVariable Long spuId) {

        skuService.exportEs(spuId);
        return new Result( StatusCode.OK,true, "导处成功");
    }
    /**
     *
     * @param searchMap  搜索的条件 map
     * @return  resultMap  返回的结果 map
     */
    @GetMapping
    public Map search(@RequestParam(required = false) Map searchMap){
        Object pageNum = searchMap.get("pageNum");
        if(pageNum==null){
            searchMap.put("pageNum","1");
        }
        if(pageNum instanceof Integer){
            searchMap.put("pageNum",pageNum.toString());
        }

       return  skuService.search(searchMap);
    }
}
