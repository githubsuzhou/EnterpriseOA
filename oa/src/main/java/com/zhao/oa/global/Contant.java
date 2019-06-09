package com.zhao.oa.global;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhao
 * 2019/5/31 0:02
 * 工具类 常量
 */

public class Contant {
    //职务
    public  static  final  String  Post_YG="员工";
    public  static  final  String  Post_BMJL="部门经理";
    public  static  final  String  Post_CW="财务";
    public  static  final  String  Post_ZJL="总经理";
    public static List<String> getPost(){
        List<String> list=new ArrayList<String>();
        list.add(Post_YG);
        list.add(Post_BMJL);
        list.add(Post_CW);
        list.add(Post_ZJL);
        return list;
    }

    //费用类别
    public static List<String> getItems(){
        List<String> list=new ArrayList<String>();
        list.add("交通");
        list.add("餐饮");
        list.add("住宿");
        list.add("办公");

        return list;
    }
    //报销单状态
    public  static  final  String  BXDC="新创建";
    public  static  final  String  BXDU="已提交";
    public  static  final  String  BXDYS="已审核";
    public  static  final  String  BXDH="打回";
    public  static  final  String  BXDZ="终止";
    public  static  final  String  BXDF="待复审";
    public  static  final  String  BXDK="已打款";

   //审核额度
   public  static  final  double  money=5000.00;
   //处理方式
   public  static  final  String  DEAALC="创建";
   public  static  final  String  DEAALSU="提交";
   public  static  final  String  DEAALU="修改";
   public  static  final  String  DEAALH="打回";
   public  static  final  String  DEAALJ="拒绝";
   public  static  final  String  DEAALPASS="通过";
   public  static  final  String  DEAALPAID="打款";
}
