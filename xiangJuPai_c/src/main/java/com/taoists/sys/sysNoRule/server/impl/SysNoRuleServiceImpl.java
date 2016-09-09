package com.taoists.sys.sysNoRule.server.impl; 

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.stereotype.Service;

import com.taoists.common.orm.dao.HibernateDaoSupport;
import com.taoists.common.util.StringUtils;
import com.taoists.sys.sysNoRule.entity.SysNoRule;
import com.taoists.sys.sysNoRule.server.SysNoRuleService;


@Service("sysNoRuleService")
public class SysNoRuleServiceImpl extends HibernateDaoSupport<SysNoRule> implements SysNoRuleService{

    public static final long NO_TYPE_ORDER = 1;

    
    //返回完整编号，列如:GB2009010100001(前缀+日期+流水号)
    public String getNextNoSerialFullNo(long noRuleId){
        SysNoRule noRule = getNoRuleById(noRuleId);
        String noSerialNo ="";
        noSerialNo +=noRule.getNoPrefix();//前缀
        noSerialNo +=showSerialNo(noRule);//日期
        noSerialNo +=getNextNoSerialNo(noRuleId);//流水号

        return noSerialNo;
    }
    
    //返回流水号：0001
    public String getNextNoSerialNo(long noRuleId){
        SysNoRule noRule = getNoRuleById(noRuleId);
        String noSerialNo ="";
        long startSerial = noRule.getNoSerial()+1;//流水号加1
        noRule.setNoSerial(startSerial);
        update(noRule);
        //FormatNumber2String fns = new FormatNumber2String(noRule.getSerialLength());
        //noSerialNo=String.valueOf(fns.format(startSerial));
        noSerialNo=StringUtils.FormatNumber2String(noRule.getSerialLength(), startSerial);
        return noSerialNo;
    }
    
    //获取单据编码规则
    public SysNoRule getNoRuleById(long noRuleId){
        return get(noRuleId);
    }   

    
    //返回日期编号，例：20090101
    private String showSerialNo(SysNoRule noRule){
        String noruleNo="";//前缀+年月日

        SimpleDateFormat   sdf =null;
        switch (noRule.getCodingPattern()){
        case 2:
            sdf   =   new   SimpleDateFormat("yyyy");//2009
            break;
        case 3:
            sdf   =   new   SimpleDateFormat("yyyyMM");//200901
            break;
        case 4:
            sdf   =   new   SimpleDateFormat("yyyyMMdd");//20090101
            break;
            }
        String sNow = sdf.format(new Date());
        if(!sNow.equals(noRule.getNoVersion())){
            noRule.setNoVersion(sNow);
            noRule.setNoSerial(noRule.INITIAL_NO_SERIAL);
        }
        String norule = noRule.getNoVersion();
        if(noRule.getYearPattern()==1){//2009否则为09
            switch (noRule.getCodingPattern()){
            case 2:
                noruleNo +=norule.substring(0, 4);
                break;
            case 3:
                noruleNo +=norule.substring(0, 6);
                break;
            case 4:
                noruleNo +=norule.substring(0, 8);
                break;
                }
        }else{
            switch (noRule.getCodingPattern()){
            case 2:
                noruleNo +=norule.substring(2, 4);
                break;
            case 3:
                noruleNo +=norule.substring(2, 6);
                break;
            case 4:
                noruleNo +=norule.substring(2, 8); 
                break;
                }
        }
                return noruleNo;
    }
}
