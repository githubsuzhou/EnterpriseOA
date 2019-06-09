package com.zhao.oa.dao;


import com.zhao.oa.entity.DealRecord;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository("dealRecordDao")
public interface DealRecordDao {
    void insert(DealRecord dealRecord);
    List<DealRecord> selectByClaimVoucher(int cvid);
}
