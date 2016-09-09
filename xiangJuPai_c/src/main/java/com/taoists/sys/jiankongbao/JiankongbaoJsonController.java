package com.taoists.sys.jiankongbao;

import java.awt.Color;
import java.awt.Font;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.taoists.common.bean.CommonJsonBean;
import com.taoists.common.image.WaterMarkUtils;

@Controller
@RequestMapping("/iJiankongbao")
public class JiankongbaoJsonController {

    @ResponseBody
    @RequestMapping(value="/heartbeat",produces="text/plain;charset=UTF-8")
    public String listSendOrder(){
        
        WaterMarkUtils.pressImage("F:/logo.png", "F:/poster.png",  145,  1050, 0, 0);
        WaterMarkUtils.pressText ("熊蜀黍", "F:/poster.png", "黑体", Font.BOLD, Color.RED, 38, 250, 946);
        Gson gson = new Gson();
        CommonJsonBean jbean = new CommonJsonBean();
        return gson.toJson(jbean);
    }
}
