package com.zhao.oa.service;

import com.zhao.oa.entity.ClaimVoucher;
import com.zhao.oa.entity.ClaimVoucherItem;
import com.zhao.oa.entity.DealRecord;

import java.util.List;

/**
 * Created by zhao
 * 2019/6/4 22:56
 * 报销单
 */
public interface ClaimVoucherService {
    //保存报销单
    void save(ClaimVoucher claimVoucher, List<ClaimVoucherItem> items);
    //
    ClaimVoucher get(int id);
    //报销单
    List<ClaimVoucherItem> getItems(int cvid);
    //审核记录
    List<DealRecord> getRecords(int cvid);
    //获取个人报销单
    List<ClaimVoucher> getForSelf(String sn);
    //获取待处理报销单
    List<ClaimVoucher> getForDeal(String sn);
    //修改报销单
    void updata(ClaimVoucher claimVoucher, List<ClaimVoucherItem> items);
    //提交报销单
    void submit(int id);
    //审核和打款
    void  deal(DealRecord dealRecord);
}
