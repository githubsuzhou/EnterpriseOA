package com.zhao.oa.controller;

import com.zhao.oa.dto.ClaimVoucherInfodto;
import com.zhao.oa.entity.ClaimVoucher;
import com.zhao.oa.entity.DealRecord;
import com.zhao.oa.entity.Employee;
import com.zhao.oa.global.Contant;
import com.zhao.oa.service.ClaimVoucherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * Created by zhao
 * 2019/6/4 23:24
 */
@Controller
@RequestMapping("/claim_voucher")
public class ClaimVoucherController {
    @Autowired
    private ClaimVoucherService claimVoucherService;
    @RequestMapping("/to_add")
    public String toAdd(Map<String,Object> map){
        map.put("items",Contant.getItems());
        map.put("info",new ClaimVoucherInfodto());
        return  "claim_voucher_add";
    }
    @RequestMapping("/add")
    public String add(HttpSession session,ClaimVoucherInfodto info){
        Employee employee=(Employee) session.getAttribute("employee");
        info.getClaimVoucher().setCreateSn(employee.getSn());
        claimVoucherService.save(info.getClaimVoucher(),info.getItems());
        //        return  "redirect:detail?id="+info.getClaimVoucher().getId();
        return  "redirect:deal";

    }
    @RequestMapping("/detail")
    public String detail(int id,Map<String,Object> map){
        map.put("claimVoucher",claimVoucherService.get(id));
        map.put("items",claimVoucherService.getItems(id));
        map.put("records",claimVoucherService.getRecords(id));
        return "claim_voucher_detail";
    }
    @RequestMapping("/self")
    public String Self(HttpSession session ,Map<String,Object> map){
        Employee employee=(Employee) session.getAttribute("employee");
        map.put("list",claimVoucherService.getForSelf(employee.getSn()));
        return "claim_voucher_self";
    }
    @RequestMapping("/deal")
    public String deal(HttpSession session ,Map<String,Object> map){
        Employee employee=(Employee) session.getAttribute("employee");
        map.put("list",claimVoucherService.getForDeal(employee.getSn()));
        return "claim_voucher_deal";
    }
    @RequestMapping("/to_update")
    public String toupdata(int id,Map<String,Object> map){
        map.put("items",Contant.getItems());
        ClaimVoucherInfodto claimVoucherInfodto=new ClaimVoucherInfodto();
        claimVoucherInfodto.setClaimVoucher(claimVoucherService.get(id));
        claimVoucherInfodto.setItems(claimVoucherService.getItems(id));
        map.put("info",claimVoucherInfodto);
        return  "claim_voucher_update";
    }
    @RequestMapping("/update")
    public String updata(HttpSession session,ClaimVoucherInfodto info){
        Employee employee=(Employee) session.getAttribute("employee");
        info.getClaimVoucher().setCreateSn(employee.getSn());
        claimVoucherService.updata(info.getClaimVoucher(),info.getItems());
        return  "redirect:deal";
    }
    @RequestMapping("/submit")
    public String submit(int id){
        claimVoucherService.submit(id);
        return  "redirect:deal";
    }

    @RequestMapping("/to_check")
    public String to_check(int id,Map<String,Object> map){
        map.put("claimVoucher",claimVoucherService.get(id));
        map.put("items",claimVoucherService.getItems(id));
        map.put("records",claimVoucherService.getRecords(id));
        DealRecord dealRecord=new DealRecord();
        dealRecord.setClaimVoucherId(id);
        map.put("record",dealRecord);
        return  "claim_voucher_check";
    }
    @RequestMapping("/check")
    public String check(HttpSession session,DealRecord dealRecord){
        Employee employee=(Employee) session.getAttribute("employee");
        dealRecord.setDealSn(employee.getSn());
        claimVoucherService.deal(dealRecord);
        return  "redirect:deal";
    }
}
