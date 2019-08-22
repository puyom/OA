/*
package cn.gson.oasys.common;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


import cn.gson.oasys.model.entity.process.Stay;
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

public class TestDbToExcel {

    public static void main(String[] args) {
        try {
            WritableWorkbook wwb = null;

            // 创建可写入的Excel工作簿
            String fileName = "D://book.xls";
            File file=new File(fileName);
            if (!file.exists()) {
                file.createNewFile();
            }
            //以fileName为文件名来创建一个Workbook
            wwb = Workbook.createWorkbook(file);

            // 创建工作表
            WritableSheet ws = wwb.createSheet("Test Shee 1", 0);

            //查询数据库中所有的数据
            List<Stay> list= StuService.getAllByDb();
            //要插入到的Excel表格的行号，默认从0开始
//            Label labelId= new Label(0, 0, "编号(id)");//表示第
            Label labelName= new Label(0, 0, "项目类");
            Label labelSex= new Label(1, 0, "项目负责人");
            Label label1= new Label(2, 0, "项目成员及职能分工");
            Label label2= new Label(3, 0, "项目名称（起止-终止）");
            Label label3= new Label(4, 0, "项目进度");
            Label label4= new Label(5, 0, "完成");
            Label label5= new Label(6, 0, "未完成");
            Label label6= new Label(7, 0, "未完成原因、困难点");
            Label label7= new Label(8, 0, "需协同");
            Label label8= new Label(9, 0, "开始时间");
            Label label9= new Label(10, 0, "结束时间");

            ws.addCell(labelName);
            ws.addCell(labelSex);
            ws.addCell(label1);
            ws.addCell(label2);
            ws.addCell(label3);
            ws.addCell(label4);
            ws.addCell(label5);
            ws.addCell(label6);
            ws.addCell(label7);
            ws.addCell(label8);
            ws.addCell(label9);
            for (int i = 0; i < list.size(); i++) {

//                Label labelId_i= new Label(0, i+1, list.get(i).getId()+"");
                Label labelName_i= new Label(0, i+1, list.get(i).getItem());
                Label labelSex_i= new Label(1, i+1, list.get(i).getUserName());
                Label labelNum_i= new Label(2, i+1, list.get(i).getMembers());
                Label labelNum_1= new Label(3, i+1, list.get(i).getProjectName());
                Label labelNum_2= new Label(4, i+1, list.get(i).getProjectSchedule());
                Label labelNum_3= new Label(5, i+1, list.get(i).getComplete());
                Label labelNum_4= new Label(6, i+1, list.get(i).getUnfinished());
                Label labelNum_5= new Label(7, i+1, list.get(i).getPoint());
                Label labelNum_6= new Label(8, i+1, list.get(i).getCoordination());
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                Date startTime = list.get(i).getStartTime();
                String format1 = sdf.format(startTime);
                Label labelNum_7= new Label(9, i+1, format1);
                Date endTime = list.get(i).getEndTime();
                String format2 = sdf.format(endTime);
                Label labelNum_8= new Label(10, i+1,format2 );
                ws.addCell(labelName_i);
                ws.addCell(labelSex_i);
                ws.addCell(labelNum_i);
                ws.addCell(labelNum_1);
                ws.addCell(labelNum_2);
                ws.addCell(labelNum_3);
                ws.addCell(labelNum_4);
                ws.addCell(labelNum_5);
                ws.addCell(labelNum_6);
                ws.addCell(labelNum_7);
                ws.addCell(labelNum_8);
            }

            //写进文档
            wwb.write();
            // 关闭Excel工作簿对象
            wwb.close();

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}*/
