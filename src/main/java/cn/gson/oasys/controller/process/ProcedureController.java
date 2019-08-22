package cn.gson.oasys.controller.process;

import cn.gson.oasys.common.IdWorker;
import cn.gson.oasys.controller.MailUtils;
import cn.gson.oasys.model.dao.attendcedao.AttendceDao;
import cn.gson.oasys.model.dao.notedao.AttachmentDao;
//import cn.gson.oasys.model.dao.plandao.TrafficDao;
import cn.gson.oasys.model.dao.plandao.DetailsStayDao;
import cn.gson.oasys.model.dao.processdao.*;
import cn.gson.oasys.model.dao.system.StatusDao;
import cn.gson.oasys.model.dao.system.TypeDao;
import cn.gson.oasys.model.dao.user.UserDao;
import cn.gson.oasys.model.entity.attendce.Attends;
import cn.gson.oasys.model.entity.note.Attachment;
import cn.gson.oasys.model.entity.process.*;
import cn.gson.oasys.model.entity.system.SystemStatusList;
import cn.gson.oasys.model.entity.system.SystemTypeList;
import cn.gson.oasys.model.entity.user.User;
import cn.gson.oasys.services.process.ProcessService;
import com.alibaba.fastjson.JSON;
import com.github.pagehelper.util.StringUtil;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.mail.Session;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import javax.validation.Valid;
import java.io.*;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequestMapping("/")
public class ProcedureController {

    @Autowired
    private UserDao udao;
    @Autowired
    private SubjectDao sudao;
    @Autowired
    private StatusDao sdao;
    @Autowired
    private TypeDao tydao;
    @Autowired
    private ReviewedDao redao;
  /*  @Autowired
    private EvectionMoneyDao emdao;*/
    @Autowired
    private BursementDao budao;
    @Autowired
    private ProcessListDao prodao;
    @Autowired
    private DetailsBurseDao dedao;
    @Autowired
    private DetailsStayDao detailsStayDao;
    @Autowired
    private ProcessService proservice;
    @Autowired
    private AttachmentDao AttDao;
    @Autowired
    private StayDao sadao;
    @Autowired
    private Stay2Dao sadao2;
    @Autowired
    private Stay3Dao sadao3;
    @Autowired
    private EvectionDao edao;
    @Autowired
    private OvertimeDao odao;
    @Autowired
    private HolidayDao hdao;
    @Autowired
    private RegularDao rgdao;
    @Autowired
    private ResignDao rsdao;
    @Autowired
    private AttendceDao adao;
    @Autowired
    private MailUtils mailUtils;

    @Value("${attachment.roopath}")
    private String rootpath;

    //新增页面
    @RequestMapping("xinxeng")
    public String index() {

        return "process/procedure";
    }


    //费用报销表单
    @RequestMapping("burse")
    public String bursement(Model model, @SessionAttribute("userId") Long userId, HttpServletRequest request,
                            @RequestParam(value = "page", defaultValue = "0") int page,
                            @RequestParam(value = "size", defaultValue = "10") int size) {
        User shen = udao.findOne(userId);  //申请人
        Long fatherId = shen.getFatherId();
        User u = udao.findOne(fatherId);  //审核人
        //查找类型
        List<SystemTypeList> uplist = tydao.findByTypeModel("aoa_bursement");
        //查找费用科目生成树
        List<Subject> second = sudao.findByParentId(1L);
        List<Subject> sublist = sudao.findByParentIdNot(1L);
        proservice.index6(model, userId, page, size);
        /*IdWorker idWorker = new IdWorker();
        long l = idWorker.nextId();
        String s = String.valueOf(l);*/
        model.addAttribute("second", second);
        model.addAttribute("sublist", sublist);
        model.addAttribute("uplist", uplist);
        model.addAttribute("shen", shen);  //申请人
        model.addAttribute("u", u); //审核人
//        model.addAttribute("number", s); //审核人
        return "process/bursement";
    }



    //付款申请单
    @RequestMapping("burse2")
    public String bursement2(Model model, @SessionAttribute("userId") Long userId, HttpServletRequest request,
                            @RequestParam(value = "page", defaultValue = "0") int page,
                            @RequestParam(value = "size", defaultValue = "10") int size) {
        User shen = udao.findOne(userId);  //申请人
        Long fatherId = shen.getFatherId();
        User u = udao.findOne(fatherId);  //审核人
        //查找类型
        List<SystemTypeList> uplist = tydao.findByTypeModel("aoa_bursement");
        //查找费用科目生成树
        List<Subject> second = sudao.findByParentId(1L);
        List<Subject> sublist = sudao.findByParentIdNot(1L);
        proservice.index6(model, userId, page, size);
        /*IdWorker idWorker = new IdWorker();
        long l = idWorker.nextId();
        String s = String.valueOf(l);*/
        model.addAttribute("second", second);
        model.addAttribute("sublist", sublist);
        model.addAttribute("uplist", uplist);
        model.addAttribute("shen", shen);  //申请人
        model.addAttribute("u", u); //审核人
//        model.addAttribute("number", s); //审核人
        return "process/bursement2";
    }




    //查看项目小组表(根据当前用户)   项目列表
    @RequestMapping("worker")
    public String worker(@SessionAttribute("userId") Long userId,Model model){
        List<User> list = udao.findByUserId(userId);
        User user = list.get(0);
        String userName = user.getUserName();
        if(user.getStatus().equals("1")){
            //内部人员
            List<Stay> list2 = sadao.findByuserId(userId);   //根据当前用户去查

            List<Stay> all = sadao.findAll();   //查全部
            for( int i=0;i<all.size();i++){
                String team = all.get(i).getTeam();
                String[] split = team.split(";");
                for (String s : split) {
                    if(userName.equals(s)){
                        //显示
                        Stay stay = all.get(i);
                        list2.add(stay);
                    }
                }
            }
            model.addAttribute("worker", list2);
            model.addAttribute("user", user);
        }else{
            //外部人员
            List<Stay> list2 = sadao.findByoutId(userId);
            model.addAttribute("worker", list2);
            model.addAttribute("user", user);
        }
        return "process/flowmanage4";
    }




    //根据id查询小组表(根据项目表主键id查询项目详细)
    @RequestMapping("worker2")
    public String worker2(@SessionAttribute("userId") Long userId,Long projectId,Model model){
        List<User> list = udao.findByUserId(userId);
        User user = list.get(0);
        if(user.getStatus().equals("1")) {
            //内部人员
            Stay stay = sadao.findByprojectId(projectId);
            List<Stay2> Stay2List = sadao2.findByprojectId(projectId);
            Stay2 stay2 = Stay2List.get(Stay2List.size() - 1);        //拿到最近的一条数据。

            ArrayList<String> uplist = new ArrayList<>();
            uplist.add("正常");
            uplist.add("重要");
            uplist.add("紧急");
            ArrayList<String> uplist2 = new ArrayList<>();
            String emergency = stay.getEmergency();

            for (String s : uplist) {
                if(!(s.equals(emergency))){
                    uplist2.add(s);
                }
            }
            List<DetailsStay> details = stay.getDetails();
            model.addAttribute("detaillist", details);
            model.addAttribute("uplist", uplist2);
            model.addAttribute("stay", stay);
            model.addAttribute("stay2", stay2);
            model.addAttribute("Stay2List", Stay2List);
            return "process/wwww";
        }else{
            //外部人员
            Stay stay = sadao.findByprojectId(projectId);
            List<Stay2> Stay2List = sadao2.findByprojectId(projectId);
            Stay2 stay2 = Stay2List.get(Stay2List.size() - 1);        //拿到最近的一条数据。

            ArrayList<String> uplist = new ArrayList<>();
            uplist.add("正常");
            uplist.add("重要");
            uplist.add("紧急");
            ArrayList<String> uplist2 = new ArrayList<>();
            String emergency = stay.getEmergency();
            for (String s : uplist) {
                if(!(s.equals(emergency))){
                    uplist2.add(s);
                }
            }
            List<DetailsStay> details = stay.getDetails();
            model.addAttribute("detaillist", details);
            model.addAttribute("uplist", uplist2);
            model.addAttribute("stay", stay);
            model.addAttribute("stay2", stay2);
            model.addAttribute("Stay2List", Stay2List);
            return "process/wwww3";
        }
    }


    //根据project逻辑删除项目
    @RequestMapping("worker3")
    @Transactional
    public String worker3(Long projectId,Model model){
        sadao.updateStay(projectId);
        return "redirect:/worker";
    }


    //保存项目
    @RequestMapping("project")
    @Transactional
    public String project(@SessionAttribute("userId") Long userId,Model model, @Valid Stay stay,@Valid Stay2 stay2,BindingResult br){
        List<User> list2 = udao.findByUserId(userId);
        User user = list2.get(0);
        List<DetailsStay> details = stay.getDetails();
            for (DetailsStay detail : details) {
                if(!(detail.getUnfinished().equals("")&& detail.getPoint().equals("") && detail.getComplete().equals("") && detail.getCoordination().equals(""))) {
                    detail.setBurs(stay);
                    detail.setStartTime(new Date());
                    detail.setUser(user.getUserName());
                    detailsStayDao.save(detail);
                }
            }


        Long projectId = stay.getProjectId();
        String emergency = stay.getEmergency();
        String projectName = stay.getProjectName();
        String responsibility = stay.getResponsibility();
        String team = stay.getTeam();
        Long userId2 = stay.getUserId();
        String userName = stay.getUserName();

        Stay3 stay3 = new Stay3();
        stay3.setProjectId(projectId);
        stay3.setEmergency(emergency);
        stay3.setProjectName(projectName);
        stay3.setResponsibility(responsibility);
        stay3.setUserId(userId2);
        stay3.setUserName(userName);
        stay3.setStatus(1L);
        stay3.setTeam(team);
        sadao3.save(stay3);


        List<Stay2> list = sadao2.findByprojectId(stay.getProjectId());
        int i=0;
        for (Stay2 stay21 : list) {
            if((stay2.getProjectSchedule().equals(stay21.getProjectSchedule()))){  //如果和list有相等的就i++;
                i++;
            }
        }
        if(i==0){  //没有相等的就save
            stay2.setBurs2(stay);
            stay2.setUser(user.getUserName());
            sadao2.save(stay2);
        }
        return "redirect:/worker";
    }



   @RequestMapping("excel")   //查看所有的项目
   public String exccel(Model model){
       List<Stay> list = sadao.findAll();  //查到所有的项目
       ArrayList<Stay> list1 = new ArrayList<>();
       SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
       String format = simpleDateFormat.format(new Date());
       model.addAttribute("list", list);
       model.addAttribute("format", format);
       return "process/flowmanage3";
   }




    @RequestMapping("add")   //管理员新增项目
    public String add(Model model,@SessionAttribute("userId") Long userId,
                      @RequestParam(value = "page", defaultValue = "0") int page,
                      @RequestParam(value = "size", defaultValue = "10") int size){
        //根据userid查询user
        List<User> byUserId = udao.findByUserId(userId);
        User user = byUserId.get(0);
        String userName = user.getUserName();
        Long userId1 = user.getUserId();
        ArrayList<String> uplist = new ArrayList<>();
        uplist.add("正常");
        uplist.add("重要");
        uplist.add("紧急");

        ArrayList<String> uplist3 = new ArrayList<>();
        uplist3.add("项目立项");
        uplist3.add("项目启动");
        uplist3.add("项目沟通");
        uplist3.add("环境搭建");
        uplist3.add("对接客户");
        uplist3.add("项目结束");
        model.addAttribute("userName", userName);
        model.addAttribute("userId", userId1);
        model.addAttribute("uplist", uplist);
        model.addAttribute("uplist3", uplist3);


        {
            User shen = udao.findOne(userId);  //申请人
            Long fatherId = shen.getFatherId();
            User u = udao.findOne(fatherId);  //审核人
            //查找类型
            List<SystemTypeList> uplist2 = tydao.findByTypeModel("aoa_bursement");
            //查找费用科目生成树
            List<Subject> second = sudao.findByParentId(1L);
            List<Subject> sublist = sudao.findByParentIdNot(1L);
            proservice.index66(model, userId);
            model.addAttribute("second", second);
            model.addAttribute("sublist", sublist);
            model.addAttribute("uplist", uplist);
            model.addAttribute("shen", shen);  //申请人
            model.addAttribute("u", u); //审核人
            return "process/add";
        }
    }



    //暂无更新
    @RequestMapping("project2")
    public String project2(Model model,@SessionAttribute("userId") Long userId, @Valid Stay stay,@Valid Stay2 stay2,BindingResult br){
        sadao.save(stay);
        List<DetailsStay> details = stay.getDetails();
        for (DetailsStay detail : details) {
            detail.setBurs(stay);
            detail.setStartTime(new Date());
            detailsStayDao.save(detail);
        }
//        Stay2 stay2 = new Stay2();
//        stay2.setProjectSchedule("项目总进度");
//        stay2.setStartTime(new Date());
//        stay2.setEndTime(new Date());
        List<User> byUserId = udao.findByUserId(userId);
        User user = byUserId.get(0);
        String userName = user.getUserName();
        stay2.setUser(userName);
        stay2.setBurs2(stay);
        sadao2.save(stay2);
        return "redirect:/worker";
    }


    /**
     * 费用表单接收
     *
     * @return
     * @throws IOException
     * @throws IllegalStateException
     */
    // 点击保存
    @RequestMapping("apply")
    public String apply(@RequestParam("filePath") MultipartFile filePath, HttpServletRequest req, @Valid Bursement bu, BindingResult br,
                        @SessionAttribute("userId") Long userId) throws Exception {
        User lu = udao.findOne(userId);//申请人
        User reuser = udao.findByUserName(bu.getUsername());//审核人
        User zhuti = udao.findByUserName(bu.getNamemoney());//证明人
        Integer allinvoice = 0;
        Double allmoney = 0.0;
        Long roleid = lu.getRole().getRoleId();//申请人角色id
        Long fatherid = lu.getFatherId();//申请人父id
        Long userid = reuser.getUserId();//审核人userid
        String eamil = reuser.getEamil(); //审核人email
        String val = req.getParameter("val");
        String userName = lu.getUserName();
        IdWorker idWorker = new IdWorker();
        long l = idWorker.nextId();
        String s = String.valueOf(l);
        //角色id 3   申请人必须是自己的上级
        if ((roleid >= 3L && fatherid == userid)||roleid==3||fatherid==1) {
            List<DetailsBurse> mm = bu.getDetails();
            for (DetailsBurse detailsBurse : mm) {
                allinvoice += detailsBurse.getInvoices();
                allmoney += detailsBurse.getDetailmoney();
                detailsBurse.setBurs(bu);
            }
            //在报销费用表里面set票据总数和总金额
            bu.setAllinvoices(allinvoice);
            bu.setAllMoney(allmoney);
            bu.setUsermoney(zhuti);
            bu.setBank(bu.getBank());
            bu.setUnit(bu.getUnit());
            bu.setCard(bu.getCard());
            bu.setNumber(s);
            //set主表
            ProcessList pro = bu.getProId();
            proservice.index5(pro, val, lu, filePath, reuser.getUserName());
            budao.save(bu);
            //存审核表
            proservice.index7(reuser, pro);
        } else {
            return "common/proce";
        }
        if(val=="费用报销") {
            //发送邮件()
            Session session = mailUtils.getSession();
            MimeMessage imageMail1 = mailUtils.createImageMail1(session, eamil, userName);
            mailUtils.sendEmail(imageMail1);
        }else{
            //发送邮件() 付款申请
            Session session = mailUtils.getSession();
            MimeMessage imageMail1 = mailUtils.createImageMail111(session, eamil, userName);
            mailUtils.sendEmail(imageMail1);
        }
        return "redirect:/xinxeng";
    }




    /**
     * 查找出自己的申请
     *
     * @return
     */
    @RequestMapping("flowmanage")
    public String flowManage(@SessionAttribute("userId") Long userId, Model model,
                             @RequestParam(value = "page", defaultValue = "0") int page,
                             @RequestParam(value = "size", defaultValue = "10") int size) {
        Pageable pa = new PageRequest(page, size);
        Page<ProcessList> pagelist = prodao.findByuserId(userId, pa);
        List<ProcessList> prolist = pagelist.getContent();
        Iterable<SystemStatusList> statusname = sdao.findByStatusModel("aoa_process_list");
        Iterable<SystemTypeList> typename = tydao.findByTypeModel("aoa_process_list");
        model.addAttribute("typename", typename);
        model.addAttribute("page", pagelist);
        model.addAttribute("prolist", prolist);
        model.addAttribute("statusname", statusname);
        model.addAttribute("url", "shenser");
        return "process/flowmanage";
    }




    //下载
    @RequestMapping("download")
    public void download(HttpServletRequest request,HttpServletResponse response) throws FileNotFoundException {
        // 下载本地文件
        String fileName = "apply.pdf".toString(); // 文件的默认保存名
        // 读到流中  /home/btweb/oa/images/111.pdf
        InputStream inStream = new FileInputStream("/home/btweb/oa/images/11.pdf");// 文件的存放路径
        // 设置输出的格式
        response.reset();
        response.setContentType("bin");
        response.addHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
        // 循环取出流中的数据
        byte[] b = new byte[100];
        int len;
        try {
            while ((len = inStream.read(b)) > 0)
                response.getOutputStream().write(b, 0, len);
            inStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping("download2")
    public void download2(HttpServletRequest request,HttpServletResponse response) throws FileNotFoundException {
        // 下载本地文件
        String fileName = "Document.docx".toString(); // 文件的默认保存名
        // 读到流中  /home/btweb/oa/images/111.pdf
        InputStream inStream = new FileInputStream("/home/btweb/oa/images/11.docx");// 文件的存放路径
        // 设置输出的格式
        response.reset();
        response.setContentType("bin");
        response.addHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
        // 循环取出流中的数据
        byte[] b = new byte[100];
        int len;
        try {
            while ((len = inStream.read(b)) > 0)
                response.getOutputStream().write(b, 0, len);
            inStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


        /**
     * 查找出所有的申请
     *
     * @return
     */
    @RequestMapping("showprocess")
    public String showprocess(@SessionAttribute("userId") Long userId, Model model,
                             @RequestParam(value = "page", defaultValue = "0") int page,
                             @RequestParam(value = "size", defaultValue = "10") int size) {



        //查找所有
        List<ProcessList> prolist = prodao.findAll();
        for (ProcessList processList : prolist) {
            User user = processList.getUserId();
            Long userId1 = user.getUserId();
            String userName = prodao.findById(userId1);
            processList.getUserId().setUserName(userName);
        }
        //
        Iterable<SystemStatusList> statusname = sdao.findByStatusModel("aoa_process_list");
        Iterable<SystemTypeList> typename = tydao.findByTypeModel("aoa_process_list");
        model.addAttribute("typename", typename);
        model.addAttribute("prolist", prolist);
        model.addAttribute("statusname", statusname);
        model.addAttribute("url", "shenser");
        model.addAttribute("name1", "shenser");
        return "process/flowmanage2";
    }


    /**
     * 申请人查看流程条件查询
     */
    @RequestMapping("shenser")
    public String ser(@SessionAttribute("userId") Long userId, Model model, HttpServletRequest req,
                      @RequestParam(value = "page", defaultValue = "0") int page,
                      @RequestParam(value = "size", defaultValue = "10") int size) {
        Pageable pa = new PageRequest(page, size);
        String val = null;
        if (!StringUtil.isEmpty(req.getParameter("val"))) {
            val = req.getParameter("val");
        }
        Page<ProcessList> pagelist = null;
        List<ProcessList> prolist = null;
        SystemStatusList status = sdao.findByStatusModelAndStatusName("aoa_process_list", val);
        if (StringUtil.isEmpty(val)) {
            //空查询
            pagelist = prodao.findByuserId(userId, pa);
        } else if (!Objects.isNull(status)) {
            //根据状态和申请人查找流程
            pagelist = prodao.findByuserIdandstatus(userId, status.getStatusId(), pa);
            model.addAttribute("sort", "&val=" + val);
        } else {
            //根据审核人，类型，标题模糊查询
            pagelist = prodao.findByuserIdandstr(userId, val, pa);
            model.addAttribute("sort", "&val=" + val);
        }
        prolist = pagelist.getContent();
        Iterable<SystemStatusList> statusname = sdao.findByStatusModel("aoa_process_list");
        Iterable<SystemTypeList> typename = tydao.findByTypeModel("aoa_process_list");
        model.addAttribute("typename", typename);
        model.addAttribute("page", pagelist);
        model.addAttribute("prolist", prolist);
        model.addAttribute("statusname", statusname);
        model.addAttribute("url", "shenser");

        return "process/managetable";
    }


    /*admin的模糊查询*/
    @RequestMapping("shenser2")
    public String ser2(@SessionAttribute("userId") Long userId, Model model, HttpServletRequest req) {
        String val = null;
        if (!StringUtil.isEmpty(req.getParameter("val"))) {
            val = req.getParameter("val");
        }
        User byUserName = udao.findByUserName(val);
        List<ProcessList> prolist = null;
        SystemStatusList status = sdao.findByStatusModelAndStatusName("aoa_process_list", val);
        if (StringUtil.isEmpty(val)) {
            //空查询
//            pagelist = prodao.findByuserId(userId, pa);
        } else if (byUserName!=null) {
            //根据状态和申请人查找流程
            //根据申请人查找出userid
            Long userId1 = byUserName.getUserId();
            prolist = prodao.findByuserIdandstatus2(userId1);
            model.addAttribute("sort", "&val=" + val);
        } else {
            //根据审核人，类型，标题模糊查询
            prolist = prodao.findByuserIdandstr2(val);
            model.addAttribute("sort", "&val=" + val);
        }
//        prolist = pagelist.getContent();
        Iterable<SystemStatusList> statusname = sdao.findByStatusModel("aoa_process_list");
        Iterable<SystemTypeList> typename = tydao.findByTypeModel("aoa_process_list");
        model.addAttribute("typename", typename);
//        model.addAttribute("page", pagelist);
        model.addAttribute("prolist", prolist);
        model.addAttribute("statusname", statusname);
        model.addAttribute("url", "shenser");
        return "process/managetable2";
    }



    /**
     * 流程审核
     *
     * @return
     */
    @RequestMapping("audit")
    public String auding(@SessionAttribute("userId") Long userId, Model model,
                         @RequestParam(value = "page", defaultValue = "0") int page,
                         @RequestParam(value = "size", defaultValue = "18") int size) {
        User user = udao.findOne(userId);
        Page<AubUser> pagelist = proservice.index(user, page, size, null, model);
        List<Map<String, Object>> prolist = proservice.index2(pagelist, user);
        model.addAttribute("page", pagelist);
        model.addAttribute("prolist", prolist);
        model.addAttribute("url", "serch");
        return "process/auditing";
    }

    /**
     * 流程审核条件查询
     *
     * @return
     */
    @RequestMapping("serch")
    public String serch(@SessionAttribute("userId") Long userId, Model model, HttpServletRequest req,
                        @RequestParam(value = "page", defaultValue = "0") int page,
                        @RequestParam(value = "size", defaultValue = "18") int size) {
        User user = udao.findOne(userId);
        String val = null;
        if (!StringUtil.isEmpty(req.getParameter("val"))) {
            val = req.getParameter("val");
        }
        Page<AubUser> pagelist = proservice.index(user, page, size, val, model);
        List<Map<String, Object>> prolist = proservice.index2(pagelist, user);
        model.addAttribute("page", pagelist);
        model.addAttribute("prolist", prolist);
        model.addAttribute("url", "serch");
        return "process/audtable";
    }


    /**
     * 查看详细
     *
     * @return
     */
    @RequestMapping("particular")
    public String particular(@SessionAttribute("userId") Long userId, Model model, HttpServletRequest req) {
        User user = udao.findOne(userId);//审核人或者申请人
        User audit = null;//最终审核人
        String id = req.getParameter("id");
        Long proid = Long.parseLong(id);
        String typename = req.getParameter("typename");//类型名称
        String name = null;
        Map<String, Object> map = new HashMap<>();
        ProcessList process = prodao.findOne(proid);//查看该条申请
        Boolean flag = process.getUserId().getUserId().equals(userId);//判断是申请人还是审核人
        if (!flag) {
            name = "审核";
        } else {
            name = "申请";
        }
        map = proservice.index3(name, user, typename, process);
        if (("费用报销").equals(typename)) {
            Bursement bu = budao.findByProId(process);
            User prove = udao.findOne(bu.getUsermoney().getUserId());//证明人
            if (!Objects.isNull(bu.getOperation())) {
                audit = udao.findOne(bu.getOperation().getUserId());//最终审核人
            }
            List<DetailsBurse> detaillist = dedao.findByBurs(bu);
            String type = tydao.findname(bu.getTypeId());
            String money = ProcessService.numbertocn(bu.getAllMoney());
            String s = bu.getNumber();
            model.addAttribute("prove", prove);
            model.addAttribute("audit", audit);
            model.addAttribute("type", type);
            model.addAttribute("bu", bu);
            model.addAttribute("money", money);
            model.addAttribute("detaillist", detaillist);
            model.addAttribute("map", map);
            model.addAttribute("number", s);
            if(flag==true) {   //申请人
                model.addAttribute("user", user);
            }
            return "process/serch";
        }else if(("付款申请").equals(typename)){
            Bursement bu = budao.findByProId(process);
            User prove = udao.findOne(bu.getUsermoney().getUserId());//证明人
            if (!Objects.isNull(bu.getOperation())) {
                audit = udao.findOne(bu.getOperation().getUserId());//最终审核人
            }
            List<DetailsBurse> detaillist = dedao.findByBurs(bu);
            String type = tydao.findname(bu.getTypeId());
            String money = ProcessService.numbertocn(bu.getAllMoney());
            String s = bu.getNumber();
            model.addAttribute("prove", prove);
            model.addAttribute("audit", audit);
            model.addAttribute("type", type);
            model.addAttribute("bu", bu);
            model.addAttribute("money", money);
            model.addAttribute("detaillist", detaillist);
            model.addAttribute("map", map);
            model.addAttribute("number", s);
            if(flag==true) {   //申请人
                model.addAttribute("user", user);
            }
            return "process/serch2";
        } /*else if (("出差费用").equals(typename)) {
            Double staymoney = 0.0;
            Double tramoney = 0.0;
            EvectionMoney emoney = emdao.findByProId(process);
            String money = ProcessService.numbertocn(emoney.getMoney());
            List<Stay> staylist = sadao.findByEvemoney(emoney);
            for (Stay stay : staylist) {
                staymoney += stay.getStayMoney();
            }
            List<Traffic> tralist = tdao.findByEvection(emoney);
            for (Traffic traffic : tralist) {
                tramoney += traffic.getTrafficMoney();
            }
            model.addAttribute("staymoney", staymoney);
            model.addAttribute("tramoney", tramoney);
            model.addAttribute("allmoney", money);
            model.addAttribute("emoney", emoney);
            model.addAttribute("staylist", staylist);
            model.addAttribute("tralist", tralist);
            model.addAttribute("map", map);
            return "process/evemonserch";
        }*/ else if (("出差申请").equals(typename)) {
            Evection eve = edao.findByProId(process);
            model.addAttribute("eve", eve);
            model.addAttribute("map", map);
            return "process/eveserach";
        } else if (("加班申请").equals(typename)) {
            Overtime eve = odao.findByProId(process);
            String type = tydao.findname(eve.getTypeId());
            model.addAttribute("eve", eve);
            model.addAttribute("map", map);
            model.addAttribute("type", type);
            return "process/overserch";
        } else if (("请假申请").equals(typename)) {
            Holiday eve = hdao.findByProId(process);
            String type = tydao.findname(eve.getTypeId());
            model.addAttribute("eve", eve);
            model.addAttribute("map", map);
            model.addAttribute("type", type);
            return "process/holiserch";
        } else if (("转正申请").equals(typename)) {
            Regular eve = rgdao.findByProId(process);
            model.addAttribute("eve", eve);
            model.addAttribute("map", map);
            return "process/reguserch";
        } else if (("离职申请").equals(typename)) {
            Resign eve = rsdao.findByProId(process);
            model.addAttribute("eve", eve);
            model.addAttribute("map", map);
            return "process/resserch";
        }


        return "process/serch";
    }

    /**
     * 进入审核页面
     *
     * @return
     */
    @RequestMapping("auditing")
    public String auditing(@SessionAttribute("userId") Long userId, Model model, HttpServletRequest req,
                           @RequestParam(value = "page", defaultValue = "0") int page,
                           @RequestParam(value = "size", defaultValue = "10") int size) {
        User shen = udao.findOne(userId);  //当前登录用户
        Long fatherId = shen.getFatherId();
        User u = udao.findOne(fatherId);  //当前登录用户

        //流程id
        Long id = Long.parseLong(req.getParameter("id"));
        ProcessList process = prodao.findOne(id);
        User userId1 = process.getUserId();

        Reviewed re = redao.findByProIdAndUserId(process.getProcessId(), shen);//查找审核表


        String typename = process.getTypeNmae().trim();
        if (("费用报销").equals(typename)) {
            Bursement bu = budao.findByProId(process);
            model.addAttribute("bu", bu);
        }else if(("付款申请").equals(typename)){
            Bursement bu = budao.findByProId(process);
            model.addAttribute("bu", bu);
        } /*else if (("出差费用").equals(typename)) {
            EvectionMoney emoney = emdao.findByProId(process);
            model.addAttribute("bu", emoney);
        }*/ else if (("转正申请").equals(typename) || ("离职申请").equals(typename)) {
            User zhuan = udao.findOne(process.getUserId().getUserId());
            model.addAttribute("position", zhuan);
        }
        proservice.user(page, size, model);
        List<Map<String, Object>> list = proservice.index4(process);

        model.addAttribute("statusid", process.getStatusId());
        model.addAttribute("process", process);
        model.addAttribute("revie", list);
        model.addAttribute("size", list.size());
        model.addAttribute("statusid", process.getStatusId());
        model.addAttribute("ustatusid", re.getStatusId());
        model.addAttribute("positionid", shen.getPosition().getId());
        model.addAttribute("typename", typename);
        model.addAttribute("shenId", userId1);
        return "process/audetail";
}


    /**
     * 审核确定的页面
     *
     * @return
     */
    //向上一级申请
    @RequestMapping("susave")
    public String save(@SessionAttribute("userId") Long userId, Model model, HttpServletRequest req, Reviewed reviewed) throws Exception {
        User u = udao.findOne(userId);
        String name = null;
        String email1 = "gaofang@lanplustech.com";   //财务
        String email3 = "zhangguanhui@lanplustech.com";   //人事
        String email2 = "";
        String email4 = "fanlinlin@lanplustech.com";   //总经理
        String email5 = "fanzhongxu@lanplustech.com";   //会计
        String email6 = "sunxiaoshuang@lanplustech.com";   //会计
        String typename = req.getParameter("type");
        Long proid = Long.parseLong(req.getParameter("proId"));
        ProcessList pro = prodao.findOne(proid);//找到该条流程
        User shen = udao.findOne(pro.getUserId().getUserId());//申请人
        email2 = shen.getEamil();  //申请人email
        String userName = shen.getUserName();
        if (!StringUtil.isEmpty(req.getParameter("liuzhuan"))) {
            name = req.getParameter("liuzhuan");
        }
        if (!StringUtil.isEmpty(name)) {
            //审核并流转
            if (udao.findByUserName(reviewed.getUsername()) == null) {
                model.addAttribute("error", "请选择下一步审核人");
                return "common/proce";
            }
            User u2 = udao.findByUserName(reviewed.getUsername());//找到下一个审核人
            if (u.getUserId().equals(u2.getUserId())) {
                model.addAttribute("error", "不能选择本人");
                return "common/proce";
            }
            if (("离职申请").equals(typename)) {
                if (u.getUserId().equals(pro.getUserId().getFatherId())) {
                    if (u2.getPosition().getId().equals(5L)) {
                            proservice.save(proid, u, reviewed, pro, u2);
                    } else {
                        model.addAttribute("error", "请选财务经理。");
                        return "common/proce";
                    }
                } else {
                    if (u2.getPosition().getId().equals(7L)) {
                        proservice.save(proid, u, reviewed, pro, u2);
                    } else {
                        model.addAttribute("error", "请选人事经理。");
                        return "common/proce";
                    }
                }
            } else if (("费用报销").equals(typename) || ("付款申请").equals(typename)) {
                if (u2.getPosition().getId().equals(5L) || u2.getPosition().getId().equals(3L)) {
                        proservice.save(proid, u, reviewed, pro, u2);
                } else {
                    model.addAttribute("error", "请选财务经理。");
                    return "common/proce";
                }
            } else {
                if (u2.getPosition().getId().equals(5L)|| u2.getPosition().getId().equals(7L)|| u2.getPosition().getId().equals(3L)) {  //出差和请假
                    proservice.save(proid, u, reviewed, pro, u2);
                } else {
                    model.addAttribute("error", "请合理选择审核人。");
                    return "common/proce";
                }
            }
        } else {
            //审核并结案
            Reviewed re = redao.findByProIdAndUserId(proid, u);
            re.setAdvice(reviewed.getAdvice());
            re.setStatusId(reviewed.getStatusId());
            re.setReviewedTime(new Date());
            redao.save(re);
            pro.setStatusId(reviewed.getStatusId());//改变主表的状态
            prodao.save(pro);
            if (("请假申请").equals(typename) || ("出差申请").equals(typename)) {
                if (reviewed.getStatusId() == 25) {
                    Attends attend = new Attends();
                    attend.setHolidayDays(pro.getProcseeDays());
                    attend.setHolidayStart(pro.getStartTime());
                    attend.setUser(pro.getUserId());
                    if (("请假申请").equals(typename)) {
                        attend.setStatusId(46L);
                    } else if (("出差申请").equals(typename)) {
                        attend.setStatusId(47L);
                    }
                    adao.save(attend);
                }
            }
        }
        if (("费用报销").equals(typename)) {
            Bursement bu = budao.findByProId(pro);
            if ((shen.getFatherId().equals(u.getUserId()) && u.getUserId()!=1 && shen.getUserId()!=5)) {  //上级审批
                bu.setManagerAdvice(reviewed.getAdvice());
                budao.save(bu);
                //上级给财务发邮件
                Session session = mailUtils.getSession();
                MimeMessage imageMail1 = mailUtils.createImageMail1(session, email1, userName);
                mailUtils.sendEmail(imageMail1);
            }
            if(u.getPosition().getId() == 5){  //财务
                bu.setMoneyAdvice(reviewed.getAdvice());
                budao.save(bu);
                Session session = mailUtils.getSession();
                MimeMessage imageMail1 = mailUtils.createImageMail1(session, email4, userName);
                mailUtils.sendEmail(imageMail1);
            }
            if (u.getPosition().getId() == 3) {    //总经理
                bu.setFinancialAdvice(reviewed.getAdvice());
                bu.setBurseTime(new Date());
                bu.setOperation(u);
                budao.save(bu);
                //回复申请人
                Session session = mailUtils.getSession();
                MimeMessage imageMail11 = mailUtils.createImageMail11(session, email2, userName);
                MimeMessage imageMail12 = mailUtils.createImageMail12(session, email5, userName);
                mailUtils.sendEmail(imageMail11);
                mailUtils.sendEmail(imageMail12);
            }
        }else if(("付款申请").equals(typename)){
            Bursement bu = budao.findByProId(pro);
            if ((shen.getFatherId().equals(u.getUserId()) && u.getUserId()!=1 && shen.getUserId()!=5)) {  //上级审批
                bu.setManagerAdvice(reviewed.getAdvice());
                budao.save(bu);
                //上级给财务发邮件
                Session session = mailUtils.getSession();
                MimeMessage imageMail1 = mailUtils.createImageMail111(session, email1, userName);
                mailUtils.sendEmail(imageMail1);
            }
            if(u.getPosition().getId() == 5){  //财务
                bu.setMoneyAdvice(reviewed.getAdvice());
                budao.save(bu);
                Session session = mailUtils.getSession();
                MimeMessage imageMail1 = mailUtils.createImageMail111(session, email4, userName);
                mailUtils.sendEmail(imageMail1);
            }
            if (u.getPosition().getId() == 3) {    //总经理
                bu.setFinancialAdvice(reviewed.getAdvice());
                bu.setBurseTime(new Date());
                bu.setOperation(u);
                budao.save(bu);
                //回复申请人
                Session session = mailUtils.getSession();
                MimeMessage imageMail11 = mailUtils.createImageMail1111(session, email2, userName);
                MimeMessage imageMail12 = mailUtils.createImageMail12222(session, email5, userName);
                mailUtils.sendEmail(imageMail11);
                mailUtils.sendEmail(imageMail12);
            }
        } else if (("出差申请").equals(typename)) {
            Evection ev = edao.findByProId(pro);
            if (shen.getFatherId().equals(u.getUserId()) && u.getUserId()!=1 && shen.getUserId()!=5) {
                //上级向财务经理发送邮件
                ev.setManagerAdvice(reviewed.getAdvice());
                Session session = mailUtils.getSession();
                MimeMessage imageMail5 = mailUtils.createImageMail5(session, email1, userName);
                mailUtils.sendEmail(imageMail5);
                edao.save(ev);
            }
            if (u.getPosition().getId() == 5) {
                //财务向总经理发送邮件
                ev.setMoneyAdvice(reviewed.getAdvice());
                Session session = mailUtils.getSession();
                MimeMessage imageMail5 = mailUtils.createImageMail5(session, email4, userName);
                mailUtils.sendEmail(imageMail5);
                edao.save(ev);
            }
            if (u.getPosition().getId().equals(3L)) {
                //总经理向申请人发送邮件
                ev.setPersonnelAdvice(reviewed.getAdvice());
                Session session = mailUtils.getSession();
                MimeMessage imageMail55 = mailUtils.createImageMail55(session, email2, userName);
                mailUtils.sendEmail(imageMail55);
                edao.save(ev);
            }
        } else if (("加班申请").equals(typename)) {
            Overtime over = odao.findByProId(pro);
            if (shen.getFatherId().equals(u.getUserId())&& u.getUserId()!=4) {
                over.setManagerAdvice(reviewed.getAdvice());
                //上级向财务发消息
                Session session = mailUtils.getSession();
                MimeMessage imageMail6 = mailUtils.createImageMail7(session, email1, userName);
                mailUtils.sendEmail(imageMail6);
                odao.save(over);
            }
            if (u.getPosition().getId().equals(5L)) {
                over.setPersonnelAdvice(reviewed.getAdvice());
                //财务回复申请人
                Session session = mailUtils.getSession();
                MimeMessage imageMail6 = mailUtils.createImageMail77(session, email2, userName);
                MimeMessage imageMail66 = mailUtils.createImageMail777(session, email6, userName);
                mailUtils.sendEmail(imageMail6);
                mailUtils.sendEmail(imageMail66);
                odao.save(over);
            }
        } else if (("请假申请").equals(typename)) {
            Holiday over = hdao.findByProId(pro);
            if (shen.getFatherId().equals(u.getUserId()) && u.getUserId()!=1 && shen.getUserId()!=5) {   //上级给财务发邮件
                over.setManagerAdvice(reviewed.getAdvice());
                Session session = mailUtils.getSession();
                MimeMessage imageMail6 = mailUtils.createImageMail6(session, email1, userName);
                mailUtils.sendEmail(imageMail6);
                hdao.save(over);
            }
            if (u.getPosition().getId().equals(5L)) {   //财务向总经理发送邮件
                over.setMoneyAdvice(reviewed.getAdvice());
                Session session = mailUtils.getSession();
                MimeMessage imageMail6 = mailUtils.createImageMail6(session, email4, userName);
                mailUtils.sendEmail(imageMail6);
                hdao.save(over);
            }

            if (u.getPosition().getId().equals(3L)) {   //总经理向人事发消息
                over.setBossAdvice(reviewed.getAdvice());
                Session session = mailUtils.getSession();
                MimeMessage imageMail6 = mailUtils.createImageMail6(session, email3, userName);
                mailUtils.sendEmail(imageMail6);
                hdao.save(over);
            }

            if (u.getPosition().getId().equals(7L)) {    //人事结案回复申请人
                over.setPersonnelAdvice(reviewed.getAdvice());
                Session session = mailUtils.getSession();
                MimeMessage imageMail66 = mailUtils.createImageMail66(session, email2, userName);
                mailUtils.sendEmail(imageMail66);
                hdao.save(over);
            }
        }  /*else if (("离职申请").equals(typename)) {
            Resign over = rsdao.findByProId(pro);
            if (shen.getFatherId().equals(u.getUserId())) {
                over.setManagerAdvice(reviewed.getAdvice());
                Session session = mailUtils.getSession();
                MimeMessage imageMail7 = mailUtils.createImageMail7(session, email3, userName);
                mailUtils.sendEmail(imageMail7);
                rsdao.save(over);
            }
            if (u.getPosition().getId() == 5) {
                over.setPersonnelAdvice(reviewed.getAdvice());
                rsdao.save(over);
            } else if (u.getPosition().getId().equals(7L)) {
                over.setFinancialAdvice(reviewed.getAdvice());
                rsdao.save(over);
            }
        }*/
        return "redirect:/audit";
    }



    //出差费用
    @RequestMapping("evemoney")
    public String evemoney(Model model, @SessionAttribute("userId") Long userId, HttpServletRequest req,
                           @RequestParam(value = "page", defaultValue = "0") int page,
                           @RequestParam(value = "size", defaultValue = "10") int size) {
        Long proid = Long.parseLong(req.getParameter("id"));//出差申请的id
        ProcessList prolist = prodao.findbyuseridandtitle(userId, proid);//找这个用户的出差申请
        proservice.index6(model, userId, page, size);
        model.addAttribute("prolist", prolist);
        return "process/evectionmoney";
    }

    /**
     * 出差费用表单接收
     *
     * @param model
     * @param session
     * @param request
     * @param page
     * @param size
     * @return
     */
//    @RequestMapping("moneyeve")
    /*public String moneyeve(@RequestParam("filePath") MultipartFile filePath, HttpServletRequest req, @Valid EvectionMoney eve, BindingResult br,
                           @SessionAttribute("userId") Long userId, Model model) throws IllegalStateException, IOException {
        User lu = udao.findOne(userId);//申请人
        User shen = udao.findByUserName(eve.getShenname());//审核人
        Long roleid = lu.getRole().getRoleId();//申请人角色id
        Long fatherid = lu.getFatherId();//申请人父id
        Long userid = shen.getUserId();//审核人userid
        String val = req.getParameter("val");
        Double allmoney = 0.0;
        if (roleid >= 3L && fatherid == userid) {
            List<Traffic> ss = eve.getTraffic();
            for (Traffic traffic : ss) {
                allmoney += traffic.getTrafficMoney();
                User u = udao.findByUserName(traffic.getUsername());
                traffic.setUser(u);
                traffic.setEvection(eve);
            }
            List<Stay> mm = eve.getStay();
            *//*for (Stay stay : mm) {
                allmoney += stay.getStayMoney() * stay.getDay();
                User u = udao.findByUserName(stay.getNameuser());
                stay.setUser(u);
                stay.setEvemoney(eve);
            }*//*

            eve.setMoney(allmoney);
            //set主表
            ProcessList pro = eve.getProId();
            System.out.println(pro + "mmmmmm");
            proservice.index5(pro, val, lu, filePath, shen.getUserName());
            emdao.save(eve);
            //存审核表
            proservice.index7(shen, pro);
        } else {
            return "common/proce";
        }

        return "redirect:/flowmanage";

    }*/

    //出差申请
    @RequestMapping("evection")
    public String evection(Model model, @SessionAttribute("userId") Long userId, HttpServletRequest request,
                           @RequestParam(value = "page", defaultValue = "0") int page,
                           @RequestParam(value = "size", defaultValue = "10") int size) {

        User shen = udao.findOne(userId);  //申请人
        Long fatherId = shen.getFatherId();
        User u = udao.findOne(fatherId);  //审核人
        //查找类型
        List<SystemTypeList> outtype = tydao.findByTypeModel("aoa_evection");
        proservice.index6(model, userId, page, size);
        model.addAttribute("outtype", outtype);
        model.addAttribute("shen", shen);
        model.addAttribute("u", u);
        return "process/evection";
    }

    /**
     * 出差申请表单接收
     *
     * @param model
     * @param session
     * @param request
     * @param page
     * @param size
     * @return
     * @throws IOException
     * @throws IllegalStateException
     */
    @RequestMapping("evec")
    public String evec( HttpServletRequest req, @Valid Evection eve, BindingResult br,
                       @SessionAttribute("userId") Long userId) throws Exception {
        User lu = udao.findOne(userId);//申请人
        User shen = udao.findByUserName(eve.getNameuser());//审核人
        Long roleid = lu.getRole().getRoleId();//申请人角色id
        Long fatherid = lu.getFatherId();//申请人父id
        Long userid = shen.getUserId();//审核人userid
        String eamil = shen.getEamil();
        String userName = lu.getUserName();
        String val = req.getParameter("val");
        if ((roleid >= 3L && fatherid == userid) || roleid == 3L ||fatherid==1) {
            //set主表
            ProcessList pro = eve.getProId();
            proservice.index55(pro, val, lu, shen.getUserName());
            edao.save(eve);
            //存审核表
            proservice.index7(shen, pro);
        } else {
            return "common/proce";
        }
        Session session = mailUtils.getSession();
        MimeMessage imageMail5 = mailUtils.createImageMail5(session, eamil, userName);
        mailUtils.sendEmail(imageMail5);
        return "redirect:/xinxeng";
    }

    //加班申请
    @RequestMapping("overtime")
    public String overtime(Model model, @SessionAttribute("userId") Long userId, HttpServletRequest request,
                           @RequestParam(value = "page", defaultValue = "0") int page,
                           @RequestParam(value = "size", defaultValue = "10") int size) {
        User shen = udao.findOne(userId);  //申请人
        Long fatherId = shen.getFatherId();
        User u = udao.findOne(fatherId);  //审核人
        //查找类型
        List<SystemTypeList> overtype = tydao.findByTypeModel("aoa_overtime");
        proservice.index6(model, userId, page, size);
        model.addAttribute("overtype", overtype);
        model.addAttribute("shen", shen);
        model.addAttribute("u", u);
        return "process/overtime";
    }

    /**
     * 加班申请接收
     *
     * @param model
     * @param session
     * @param request
     * @param page
     * @param size
     * @return
     */
    @RequestMapping("over")
    public String over(HttpServletRequest req, @Valid Overtime eve, BindingResult br,
                       @SessionAttribute("userId") Long userId) throws Exception {
        User lu = udao.findOne(userId);//申请人
        String userName = lu.getUserName();
        User shen = udao.findByUserName(eve.getNameuser());//审核人
        Long roleid = lu.getRole().getRoleId();//申请人角色id
        Long fatherid = lu.getFatherId();//申请人父id
        Long userid = shen.getUserId();//审核人userid
        String eamil = shen.getEamil();
        String val = req.getParameter("val");
        if ((roleid >= 3L && fatherid == userid) || roleid == 3L ||fatherid==1) {
            //set主表
            ProcessList pro = eve.getProId();
            proservice.index8(pro, val, lu, shen.getUserName());
            odao.save(eve);
            //存审核表
            proservice.index7(shen, pro);
        } else {
            return "common/proce";
        }
        Session session = mailUtils.getSession();
        MimeMessage imageMail7 = mailUtils.createImageMail7(session, eamil, userName);
        mailUtils.sendEmail(imageMail7);
        return "redirect:/xinxeng";

    }

    //请假申请
    @RequestMapping("holiday")
    public String holiday(Model model, @SessionAttribute("userId") Long userId, HttpServletRequest request,
                          @RequestParam(value = "page", defaultValue = "0") int page,
                          @RequestParam(value = "size", defaultValue = "10") int size) {
        User shen = udao.findOne(userId);  //申请人
        Long fatherId = shen.getFatherId();
        User u = udao.findOne(fatherId);  //审核人
        //查找类型
        List<SystemTypeList> overtype = tydao.findByTypeModel("aoa_holiday");
        proservice.index6(model, userId, page, size);
        model.addAttribute("overtype", overtype);
        model.addAttribute("shen", shen);
        model.addAttribute("u", u);
        return "process/holiday";
    }

    /**
     * 请假申请接收
     *
     * @param model
     * @param session
     * @param request
     * @param page
     * @param size
     * @return
     */
    @RequestMapping("holi")
    public String holi(HttpServletRequest req, @Valid Holiday eve, BindingResult br,
                       @SessionAttribute("userId") Long userId, Model model) throws Exception {
        User lu = udao.findOne(userId);//申请人
        User shen = udao.findByUserName(eve.getNameuser());//审核人
        Long roleid = lu.getRole().getRoleId();//申请人角色id
        Long fatherid = lu.getFatherId();//申请人父id
        Long userid = shen.getUserId();//审核人userid
        String eamil = shen.getEamil();
        String userName = lu.getUserName();
        String val = req.getParameter("val");
        if ((roleid >= 3L && fatherid == userid) || roleid == 3L ||fatherid==1) {
            SystemTypeList type = tydao.findOne(eve.getTypeId());
           /* System.out.println(filePath.getOriginalFilename());
            System.out.println(filePath.getName());*/
           /* if(filePath.isEmpty()){
                model.addAttribute("error", "请上传请假相关资料");
                return "common/proce";
            }*/
            if (eve.getTypeId() == 40) {
                if (type.getTypeSortValue() < eve.getLeaveDays()) {
                    model.addAttribute("error", "婚假必须小于10天。");
                    return "common/proce";
                }
                //set主表
                ProcessList pro = eve.getProId();
                proservice.index55(pro, val, lu, shen.getUserName());
                hdao.save(eve);
                //存审核表
                proservice.index7(shen, pro);
            } else if (eve.getTypeId() == 38) {
                if (type.getTypeSortValue() < eve.getLeaveDays()) {
                    model.addAttribute("error", "单次事假必须小于4天。");
                    return "common/proce";
                }
                ProcessList pro = eve.getProId();
                proservice.index55(pro, val, lu, shen.getUserName());
                hdao.save(eve);
                //存审核表
                proservice.index7(shen, pro);
            } else if (eve.getTypeId() == 42) {
                if (type.getTypeSortValue() < eve.getLeaveDays()) {
                    model.addAttribute("error", "陪产假必须小于10天。");
                    return "common/proce";
                }
                //set主表
                ProcessList pro = eve.getProId();
                proservice.index55(pro, val, lu, shen.getUserName());
                hdao.save(eve);
                //存审核表
                proservice.index7(shen, pro);
            } else {
                //set主表
                ProcessList pro = eve.getProId();
                proservice.index55(pro, val, lu, shen.getUserName());
                hdao.save(eve);
                //存审核表
                proservice.index7(shen, pro);
            }
        } else {
            return "common/proce";
        }
        Session session = mailUtils.getSession();
        MimeMessage imageMail6 = mailUtils.createImageMail6(session, eamil, userName);
        mailUtils.sendEmail(imageMail6);
        return "redirect:/xinxeng";
    }

    //转正申请
    @RequestMapping("regular")
    public String regular(Model model, @SessionAttribute("userId") Long userId, HttpServletRequest request,
                          @RequestParam(value = "page", defaultValue = "0") int page,
                          @RequestParam(value = "size", defaultValue = "10") int size) {
        proservice.index6(model, userId, page, size);
        return "process/regular";
    }

    /**
     * 转正申请接收
     *
     * @param model
     * @param session
     * @param request
     * @param page
     * @param size
     * @return
     */
    @RequestMapping("regu")
    public String regu(HttpServletRequest req, @Valid Regular eve, BindingResult br,
                       @SessionAttribute("userId") Long userId, Model model) throws Exception {
        User lu = udao.findOne(userId);//申请人
        User shen = udao.findByUserName(eve.getNameuser());//审核人
        Long roleid = lu.getRole().getRoleId();//申请人角色id
        Long fatherid = lu.getFatherId();//申请人父id
        String userName = lu.getUserName();
        Long userid = shen.getUserId();//审核人userid
        String eamil = shen.getEamil();
        String val = req.getParameter("val");
        if (roleid >= 3L && fatherid == userid) {
            if (lu.getRole().getRoleId() == 6 || lu.getRole().getRoleId() == 7) {

                //set主表
                ProcessList pro = eve.getProId();
                proservice.index8(pro, val, lu, shen.getUserName());
                rgdao.save(eve);
                //存审核表
                proservice.index7(shen, pro);
            } else {
                model.addAttribute("error", "你不需要转正。。。");
                return "common/proce";
            }
        } else {
            return "common/proce";
        }
    /*    Session session = mailUtils.getSession();
        MimeMessage imageMail8 = mailUtils.createImageMail8(session, eamil, userName);
        mailUtils.sendEmail(imageMail8);*/
        return "redirect:/xinxeng";

    }

    //离职申请
    @RequestMapping("resign")
    public String resign(Model model, @SessionAttribute("userId") Long userId, HttpServletRequest request,
                         @RequestParam(value = "page", defaultValue = "0") int page,
                         @RequestParam(value = "size", defaultValue = "10") int size) {
        proservice.index6(model, userId, page, size);
        return "process/resign";
    }

    /**
     * 离职申请接收
     *
     * @param model
     * @param session
     * @param request
     * @param page
     * @param size
     * @return
     */
    @RequestMapping("res")
    public String res(HttpServletRequest req, @Valid Resign eve, BindingResult br,
                      @SessionAttribute("userId") Long userId, Model model) throws Exception {
        User lu = udao.findOne(userId);//申请人
        User shen = udao.findByUserName(eve.getNameuser());//审核人
        Long roleid = lu.getRole().getRoleId();//申请人角色id
        Long fatherid = lu.getFatherId();//申请人父id
        Long userid = shen.getUserId();//审核人userid
        String eamil = lu.getEamil();
        String userName = lu.getUserName();
        String val = req.getParameter("val");
        if (roleid >= 3L && fatherid == userid) {
            //set主表
            ProcessList pro = eve.getProId();
            proservice.index8(pro, val, lu, shen.getUserName());
            eve.setHandUser(udao.findByUserName(eve.getHanduser()));
            rsdao.save(eve);
            //存审核表
            proservice.index7(shen, pro);
        } else {
            return "common/proce";
        }
        Session session = mailUtils.getSession();
        MimeMessage imageMail7 = mailUtils.createImageMail7(session, eamil, userName);
        mailUtils.sendEmail(imageMail7);
        return "redirect:/xinxeng";

    }

    /**
     * 删除
     */
    @RequestMapping("sdelete")
    public String dele(HttpServletRequest req, @SessionAttribute("userId") Long userId, Model model) {
        User lu = udao.findOne(userId);//审核人
        Long proid = Long.parseLong(req.getParameter("id"));

        Reviewed rev = redao.findByProIdAndUserId(proid, lu);
        if (!Objects.isNull(rev)) {
            rev.setDel(true);
            redao.save(rev);
        } else {
            return "common/proce";
        }
        return "redirect:/audit";

    }

    /**
     * 下载文件
     *
     * @param response
     * @param fileid   多文件下载
     */
    @RequestMapping("file")
    public void downFile(HttpServletResponse response, @RequestParam("fileid") Long fileid) {
        try {
            Attachment attd = AttDao.findOne(fileid);
            File file = new File(rootpath, attd.getAttachmentPath());
            response.setContentLength(attd.getAttachmentSize().intValue());
            response.setContentType(attd.getAttachmentType());
            response.setHeader("Content-Disposition", "attachment;filename=" + new String(attd.getAttachmentName().getBytes("UTF-8"), "ISO8859-1"));
            proservice.writefile(response, file);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 图片预览
     *
     * @param response
     * @param fileid
     */
    @RequestMapping("show/**")
    public void image(Model model, HttpServletResponse response, @SessionAttribute("userId") Long userId, HttpServletRequest request)
            throws IOException {

        String startpath = new String(URLDecoder.decode(request.getRequestURI(), "utf-8"));

        String path = startpath.replace("/show", "");

        File f = new File(rootpath, path);
        System.out.println(f.getAbsolutePath());
        ServletOutputStream sos = response.getOutputStream();
        FileInputStream input = new FileInputStream(f.getPath());
        byte[] data = new byte[(int) f.length()];
        IOUtils.readFully(input, data);
        // 将文件流输出到浏览器
        IOUtils.write(data, sos);
        input.close();
        sos.close();
    }
}
