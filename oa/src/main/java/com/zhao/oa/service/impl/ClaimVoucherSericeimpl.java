package com.zhao.oa.service.impl;

import com.zhao.oa.dao.ClaimVoucherDao;
import com.zhao.oa.dao.ClaimVoucherItemDao;
import com.zhao.oa.dao.DealRecordDao;
import com.zhao.oa.dao.EmployeeDao;
import com.zhao.oa.entity.ClaimVoucher;
import com.zhao.oa.entity.ClaimVoucherItem;
import com.zhao.oa.entity.DealRecord;
import com.zhao.oa.entity.Employee;
import com.zhao.oa.global.Contant;
import com.zhao.oa.service.ClaimVoucherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by zhao
 * 2019/6/4 23:03
 */
@Service
public class ClaimVoucherSericeimpl implements ClaimVoucherService {
    @Autowired
    private ClaimVoucherDao claimVoucherDao;
    @Autowired
    private ClaimVoucherItemDao claimVoucherItemDao;
    @Autowired
    private DealRecordDao dealRecordDao;
    @Autowired
    private EmployeeDao employeeDao;
    public ClaimVoucherSericeimpl() {
        super();
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    public void save(ClaimVoucher claimVoucher, List<ClaimVoucherItem> items) {
        claimVoucher.setCreateTime(new Date());
        //待处理设置为创建者
        claimVoucher.setNextDealSn(claimVoucher.getCreateSn());
        claimVoucher.setStatus(Contant.BXDC);
        claimVoucherDao.insert(claimVoucher);
        //claimVoucher.getId() id在插入的时候通过 useGeneratedKeys="true" keyProperty="id"已经获取到了
        for (ClaimVoucherItem item : items) {
            item.setClaimVoucherId(claimVoucher.getId());
            claimVoucherItemDao.insert(item);
        }
    }

    public ClaimVoucher get(int id) {
        return claimVoucherDao.select(id);
    }

    public List<ClaimVoucherItem> getItems(int cvid) {
        return claimVoucherItemDao.selectByClaimVoucher(cvid);
    }

    public List<DealRecord> getRecords(int cvid) {
        return dealRecordDao.selectByClaimVoucher(cvid);
    }

    public List<ClaimVoucher> getForSelf(String sn) {
        return claimVoucherDao.selectByCreateSn(sn);
    }

    public void updata(ClaimVoucher claimVoucher, List<ClaimVoucherItem> items) {

        //待处理设置为创建者
        claimVoucher.setNextDealSn(claimVoucher.getCreateSn());
        claimVoucher.setStatus(Contant.BXDC);
        claimVoucherDao.update(claimVoucher);
        //claimVoucher.getId() id在插入的时候通过 useGeneratedKeys="true" keyProperty="id"已经获取到了
       List <ClaimVoucherItem> olds=claimVoucherItemDao.selectByClaimVoucher(claimVoucher.getId());
        for (ClaimVoucherItem old : olds) {
            boolean isHave=false;
            for (ClaimVoucherItem item : items) {
                if(item.getId()==old.getId()){
                    isHave=true;
                    break;
                }
            }
            //迭代了都没找到，把他删除
            if(!isHave){

                claimVoucherItemDao.delete(old.getId());
            }
        }
        for (ClaimVoucherItem item : items) {
            item.setClaimVoucherId(claimVoucher.getId());
            //>0表示有这个条目，更新他
            if(item.getId()!=null&&item.getId()>0){
                claimVoucherItemDao.update(item);
            }else {
                claimVoucherItemDao.insert(item);
            }
        }
    }

    public void submit(int id) {
        ClaimVoucher claimVoucher=claimVoucherDao.select(id);
        Employee employee = employeeDao.select(claimVoucher.getCreateSn());
        claimVoucher.setStatus(Contant.BXDU);
        if(employee.getPost().equals(Contant.Post_ZJL)){

            claimVoucher.setNextDealSn(employeeDao.selectByDepartmentAndPost(employee.getDepartmentSn(),Contant.Post_ZJL).get(0).getSn());
        }else {
            claimVoucher.setNextDealSn(employeeDao.selectByDepartmentAndPost(employee.getDepartmentSn(), Contant.Post_BMJL).get(0).getSn());
        }
        claimVoucherDao.update(claimVoucher);
        //记录的保存
        DealRecord dealRecord=new DealRecord();
        dealRecord.setDealWay(Contant.DEAALSU);
        dealRecord.setDealSn(employee.getSn());
        dealRecord.setClaimVoucherId(id);
        dealRecord.setDealResult(Contant.BXDU);
        dealRecord.setDealTime(new Date());
        dealRecord.setComment("无");
        dealRecordDao.insert(dealRecord);
    }

    public void deal(DealRecord dealRecord) {
        ClaimVoucher claimVoucher=claimVoucherDao.select(dealRecord.getClaimVoucherId());
        Employee employee=employeeDao.select(dealRecord.getDealSn());
        if(dealRecord.getDealWay().equals(Contant.DEAALPASS)){
            //不需要复审
            if(claimVoucher.getTotalAmount()<=Contant.money||employee.getPost().equals(Contant.Post_ZJL)){
                claimVoucher.setStatus(Contant.BXDYS);
                claimVoucher.setNextDealSn(employeeDao.selectByDepartmentAndPost(null,Contant.Post_CW).get(0).getSn());
                dealRecord.setDealTime(new Date());
                dealRecord.setDealResult(Contant.BXDYS);
            }else {
                //需要复审
                claimVoucher.setStatus(Contant.BXDF);
                claimVoucher.setNextDealSn(employeeDao.selectByDepartmentAndPost(null,Contant.Post_ZJL).get(0).getSn());
                dealRecord.setDealTime(new Date());
                dealRecord.setDealResult(Contant.BXDF);
            }
        }else if(dealRecord.getDealWay().equals(Contant.BXDH)){
            claimVoucher.setStatus(Contant.BXDK);
            claimVoucher.setNextDealSn(claimVoucher.getCreateSn());
            dealRecord.setDealTime(new Date());
            dealRecord.setDealResult(Contant.BXDH);
        }else if(dealRecord.getDealWay().equals(Contant.DEAALJ)){
            claimVoucher.setStatus(Contant.BXDZ);
            claimVoucher.setNextDealSn(null);
            dealRecord.setDealTime(new Date());
            dealRecord.setDealResult(Contant.BXDZ);
        }else if(dealRecord.getDealWay().equals(Contant.DEAALPAID)){
            claimVoucher.setStatus(Contant.BXDK);
            claimVoucher.setNextDealSn(null);
            dealRecord.setDealTime(new Date());
            dealRecord.setDealResult(Contant.BXDK);
        }
        claimVoucherDao.update(claimVoucher);
        dealRecordDao.insert(dealRecord);
    }

    public List<ClaimVoucher> getForDeal(String sn) {
        return claimVoucherDao.selectByNextDealSn(sn);
    }
}
