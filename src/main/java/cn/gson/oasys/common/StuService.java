/*
package cn.gson.oasys.common;


import java.io.File;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import cn.gson.oasys.model.entity.process.Stay;
import jxl.Sheet;
import jxl.Workbook;



*/
/**
 * @author Javen
 * @Email zyw205@gmail.com
 *
 *//*

public class StuService {
    */
/**
     * 查询stu表中所有的数据
     * @return
     *//*

    public static List<Stay> getAllByDb(){
        List<Stay> list=new ArrayList<Stay>();
        try {
            DBhelper db=new DBhelper();
            String sql="select * from aoa_stay";
            ResultSet rs= db.Search(sql, null);
            while (rs.next()) {
                int id=rs.getInt("project_id");
                String item=rs.getString("item");
                String user_name=rs.getString("user_name");
                String members=rs.getString("members");
                String project_name=rs.getString("project_name");
                String project_schedule=rs.getString("project_schedule");
                String complete=rs.getString("complete");
                String unfinished=rs.getString("unfinished");
                String point=rs.getString("point");
                String coordination=rs.getString("coordination");
                Date start_time=rs.getDate("start_time");
                Date end_time=rs.getDate("end_time");


                //System.out.println(id+" "+name+" "+sex+ " "+num);
                list.add(new Stay(item, user_name, members,project_name,project_schedule,complete,unfinished,point,coordination,start_time,end_time));
            }

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return list;
    }

    */
/**
     * 查询指定目录中电子表格中所有的数据
     * @param file 文件完整路径
     * @return
     *//*

    */
/*public static List<Stay> getAllByExcel(String file){
        List<Stay> list=new ArrayList<Stay>();
        try {
            Workbook rwb=Workbook.getWorkbook(new File(file));
            Sheet rs=rwb.getSheet("Test Shee 1");//或者rwb.getSheet(0)
            int clos=rs.getColumns();//得到所有的列
            int rows=rs.getRows();//得到所有的行

            System.out.println(clos+" rows:"+rows);
            for (int i = 1; i < rows; i++) {
                for (int j = 0; j < clos; j++) {
                    //第一个是列数，第二个是行数
                    String id=rs.getCell(j++, i).getContents();//默认最左边编号也算一列 所以这里得j++
                    String item=rs.getCell(j++, i).getContents();
                    String user_name=rs.getCell(j++, i).getContents();
                    String members=rs.getCell(j++, i).getContents();
                    String projectName=rs.getCell(j++, i).getContents();
                    String projectSchedule=rs.getCell(j++, i).getContents();
                    String complete=rs.getCell(j++, i).getContents();
                    String unfinished=rs.getCell(j++, i).getContents();
                    String point=rs.getCell(j++, i).getContents();
                    String coordination=rs.getCell(j++, i).getContents();
                    String startTime=rs.getCell(j++, i).getContents();
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    Date parse1 = sdf.parse(startTime);
                    String endTime=rs.getCell(j++, i).getContents();
                    Date parse2 = sdf.parse(endTime);

//                    System.out.println("id:"+id+" name:"+name+" sex:"+sex+" num:"+num);
                    list.add(new Stay(item,user_name,members,projectName,projectSchedule,complete,unfinished,point,coordination,parse1,parse2));
                }
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return list;

    }*//*


    */
/**
     * 通过Id判断是否存在
     * @param id
     * @return
     *//*

    public static boolean isExist(int id){
        try {
            DBhelper db=new DBhelper();
            ResultSet rs=db.Search("select * from aoa_stay where project_id=?", new String[]{id+""});
            if (rs.next()) {
                return true;
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return false;
    }

    public static void main(String[] args) {
        */
/*List<StuEntity> all=getAllByDb();
        for (StuEntity stuEntity : all) {
            System.out.println(stuEntity.toString());
        }*//*


        System.out.println(isExist(1));

    }

}*/
