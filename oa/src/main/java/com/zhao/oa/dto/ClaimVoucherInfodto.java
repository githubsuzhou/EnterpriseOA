package com.zhao.oa.dto;

import com.zhao.oa.entity.ClaimVoucher;
import com.zhao.oa.entity.ClaimVoucherItem;

import java.util.List;

/**
 * Created by zhao
 * 2019/6/4 23:20
 */
public class ClaimVoucherInfodto {
    //报销单信息
    private ClaimVoucher claimVoucher;
    //条目信息
    private List<ClaimVoucherItem> items;

    public ClaimVoucher getClaimVoucher() {
        return claimVoucher;
    }

    public void setClaimVoucher(ClaimVoucher claimVoucher) {
        this.claimVoucher = claimVoucher;
    }

    public List<ClaimVoucherItem> getItems() {
        return items;
    }

    public void setItems(List<ClaimVoucherItem> items) {
        this.items = items;
    }
}
