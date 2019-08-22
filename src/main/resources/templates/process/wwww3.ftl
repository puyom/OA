<#include "/common/commoncss.ftl">
<link rel="stylesheet" href="css/common/tanchuang.css" />
<style>


    /*所有的input贴合表格*/
    .input {
        border: none;
        overflow: hidden;
        height: 100%;
        width: 100%;
    }

    .font-center {
        text-align: center
    }
.modal-open {
    overflow:auto;
}
.box-header{
  text-align: center;
  border-bottom: 0px solid #f4f4f4;
}
.title{
	text-align: center;
}

.inpu{
 margin-top: -6px;

}

.control-label{
	display:inline-block;
	font-weight: 400;
}
.bo{
	margin: 0px auto;
	width: 80%;
}


.table th,.chebox,.table>tbody>tr>td{
font-weight: 400;
 text-align: center;
}
.inside{
width: 100%;
}
.inside thead{
background-color: rgba(76, 175, 95, 0.06);
}
.inside>tbody>tr>td{
 border-top: 0px solid #ddd;
}
.inside>tbody>tr>td{
border-bottom: 1px solid #ddd;
border-left: 1px solid #ddd;
}
.tdrig{
border-right: 1px solid #ddd;
}
.bo>tbody>tr>td,.inside>thead>tr>th {
    border-top: 0px solid #ddd;
    border-bottom: 0px solid #ddd;
    border-left: 0px solid #ddd;
}
.food{
	padding-left:10px
}
.zeng,.jian{
cursor: pointer;
}
.reciver{
	position: relative;
    float: right;
    margin-top: -28px;
    right: 5px;
    cursor: pointer;
}
.sub{
	position: relative;
    float: right;
    margin-top: -24px;
    right: 9px;
    cursor: pointer;
}
.text{
	min-height:100px;
}
</style>
<div class="row" style="padding-top: 10px;">
	<div class="col-md-2">
		<h1 style="font-size: 24px; margin: 0;" class="">项目小组表</h1>
	</div>
	<#--<div class="col-md-10 text-right">
		<a href="##"><span class="glyphicon glyphicon-home"></span> 首页</a> > <a
			disabled="disabled">费用报销</a>
	</div>-->
</div>
<div class="row" style="padding-top: 15px;">
	<div class="col-md-12">
		
		<div class="bgc-w box">
			<form action="project"  enctype="multipart/form-data" method="post" onsubmit="return check();" >
			<div class="box-header">
				<table class="bo table ">

				<tr >
					<td colspan="14" class="title"><h2>项目小组表</h2></td>
				</tr>
				<tr style="opacity: 0;">
					<td colspan="14">11</td>
				</tr>
				<tr>
					<td colspan="14" style="text-align: left;">
						<!--錯誤信息提示  -->
					<div class="alert alert-danger alert-dismissable" style="display:none;" role="alert">
						错误信息:<button class="thisclose close" type="button">&times;</button>
						<span class="error-mess"></span>
					</div>
					</td>
				</tr>
                    <tr >
					<#--<td  colspan="6"><input type="text" class="form-control inpu" name="proId.processName"/></td>-->
                        <td class="title"><label class="control-label">项目名称</label></td>
                        <td  colspan="6"><input type="text" class="form-control inpu " name="projectName" value="${(stay.projectName)!''}"/></td>
                        <td class="title"><label class="control-label">紧急程度</label></td>
                        <td colspan="6">
                            <select class="form-control inpu" name="emergency">
                                <option selected>${stay.emergency}</option>
							<#list uplist as harry>
                                <option >${harry}</option>
							</#list>
                            </select>
                    </tr>
                    <tr>
                        <td class="title"><label class="control-label">项目负责人</label></td>
                        <td  colspan="6"><input type="text" class="form-control inpu" name="userName" value="${(stay.userName)!''}"/></td>
                        <td class="title"><label class="control-label">主要职责</label></td>
                        <td  colspan="6"><input type="text" class="form-control inpu" name="responsibility" value="${(stay.responsibility)!''}"/></td>
                    </tr>

					<tr>
                        <td class="title"><label class="control-label">项目进度</label></td>
                        <td colspan="6"><input type="text" class="form-control inpu " name="projectSchedule" value="${(stay2.projectSchedule)!''}"/></td>
                        <td class="title" ><label class="control-label">开始日期</label></td>
                        <td  colspan="6"><input type="text" class="form-control inpu" id="starTime"  name="startTime"  value="${(stay2.startTime)!''}"/></td>
					</tr>

                    <tr>
                        <td class="title" ><label class="control-label">结束日期</label></td>
                        <td  colspan="6"><input type="text" class="form-control inpu"  id="endTime"   name="endTime"  value="${(stay2.endTime)!''}"/></td>
                        <td class="title" ><label class="control-label">项目天数</label></td>
                        <td  colspan="6"><input type="text" class="form-control inpu days"   value="请点击查看..."
                                                readonly="readonly" style="background-color:#fff;"/></td>
                    </tr>

					<tr>
                        <td class="title" ><label class="control-label">是否延期</label></td>
                        <td  colspan="6"><input type="text" class="form-control inpu days2"  value=" "
                                                readonly="readonly" style="background-color:#fff;"/></td>
					</tr>

                    <tr>
                        <td class="title"><label class="control-label">项目成员及职能分工</label></td>
                        <td  colspan="12"><textarea class="form-control text" name="members" >${(stay.members)!''}</textarea></td>
                    </tr>



                    <tr >
                        <td class="title"><label class="control-label">项目进度</label></td>
					<#--<td class="title"><label class="control-label"><a href="/worker3?projectId=${(stay.projectId)!''}">查看历史记录</a></label></td>-->
					<#--<td colspan="13" style="text-align: right;" ><i class="glyphicon glyphicon-plus zeng"></i>&nbsp;&nbsp;&nbsp;<i class="glyphicon glyphicon-minus jian"></i></td>-->
                    </tr>
                    <tr class="rile">
                        <td colspan="14">
                            <div>
                                <table class="table inside">
                                    <thead>
                                    <tr>
                                        <th colspan="2">序号</th>
                                        <th colspan="2">项目进度</th>
                                        <th colspan="2">开始时间</th>
                                        <th colspan="2">结束时间</th>
                                    </tr>
                                    </thead>
								<#--<tbody class="tbody">-->
							<#list Stay2List as pro>
                            <tr class="tr">
                                <td style="border-left: 0px ;" colspan="2"><span>${pro_index+1}</span></td>
                                <td colspan="2"><span>${(pro.projectSchedule)!''}</span></td>
                                <td colspan="2"><span>${(pro.startTime)!''}</span></td>
                                <td colspan="2"><span>${(pro.endTime)!''}</span></td>
                            </tr>
							</#list>
								<#--</tbody>-->
                                </table>
                            </div>
                        </td>
                    </tr>
                    <tr >
                        <td class="title"><label class="control-label">项目明细</label></td>
					<#--<td class="title"><label class="control-label"><a href="/worker3?projectId=${(stay.projectId)!''}">查看历史记录</a></label></td>-->
					<#--<td colspan="13" style="text-align: right;" ><i class="glyphicon glyphicon-plus zeng"></i>&nbsp;&nbsp;&nbsp;<i class="glyphicon glyphicon-minus jian"></i></td>-->
                    </tr>
                    <tr class="rile">
                        <td colspan="14">
                            <div>
                                <table class="table inside">
                                    <thead>
                                    <tr>
                                        <th colspan="2">序号</th>
                                        <th colspan="2">日期</th>
									<#--<th colspan="2">项目进度</th>-->
									<#--<th colspan="2">开始时间</th>-->
									<#--<th colspan="2">结束时间</th>-->
                                        <th colspan="2">完成</th>
                                        <th colspan="2">未完成</th>
                                        <th colspan="2">未完成原因，困难点</th>
                                        <th colspan="2">需协同</th>
                                    </tr>
                                    </thead>
								<#--<tbody class="tbody">-->
							<#list detaillist as detail>
                            <tr class="tr">
                                <td style="border-left: 0px ;" colspan="2"><span>${detail_index+1}</span></td>
                                <td colspan="2"><span>${(detail.startTime)!''}</span></td>
							<#-- <td colspan="2"><span>${(detail.projectSchedule)!''}</span></td>
                             <td colspan="2"><span>${(detail.startTime2)!''}</span></td>
                             <td colspan="2"><span>${(detail.endTime)!''}</span></td>-->
                                <td colspan="2"><span>${(detail.complete)!''}</span></td>
                                <td colspan="2"><span>${(detail.unfinished)!''}</span></td>
                                <td colspan="2" ><span>${(detail.point)!''}</span></td>
                                <td colspan="2"><span>${(detail.coordination)!''}</span></td>
                            </tr>
							</#list>
								<#--</tbody>-->
                                </table>
                            </div>
                        </td>
                    </tr>

				<#--<tr>

					<td colspan="14" style="text-align: right;">
						    &lt;#&ndash;<input   type="text" value="费用报销" name="val" hidden="hidden"/>&ndash;&gt;
                            <input   type="text" value="${(stay.projectId)!''}"  name="projectId" hidden="hidden"/>
                            <input   type="text"  value="${(stay.userId)!''}" name="userId"  hidden="hidden"/>
						<input class="btn btn-primary" id="save" type="submit" value="保存"  />
						<input class="btn btn-default" id="cancel" type="button" value="取消"
						onclick="window.history.back();" />
					</td>
					
				</tr>-->
				</table>
			</div>
			</form>
		</div>
	</div>
</div>
<input type="text" class="recive_list" style="display:none;">
<input type="text" class="ject" style="display:none;">
<#include "/common/modalTip.ftl"> 
<script>
//表单提交前执行的onsubmit()方法；返回false时，执行相应的提示信息；返回true就提交表单到后台校验与执行
function check() {
	console.log("开始进入了");
	//提示框可能在提交之前是block状态，所以在这之前要设置成none
	$('.alert-danger').css('display', 'none');
	var isRight = 1;
	$('.form-control').each(function(index) {
		// 如果在这些input框中，判断是否能够为空
		if ($(this).val() == "") {
			if($(this).hasClass("cha")){
				return true;
			}
			// 排除哪些字段是可以为空的，在这里排除
			/* if (index == 5||index == 6) {
				return true;
			}  */
			
			// 获取到input框的兄弟的文本信息，并对应提醒；
			console.log(index);
			var errorMess = "红色提示框不能为空!";
			// 对齐设置错误信息提醒；红色边框
			$(this).parent().addClass("has-error has-feedback");
			$('.alert-danger').css('display', 'block');
			// 提示框的错误信息显示
			$('.error-mess').text(errorMess);
			
			isRight = 0;
			return false;
			
		} else {
			return true;
		}
	});
	
	if (isRight == 0) {
		//modalShow(0);
		 return false;
	} else if (isRight == 1) {
		//modalShow(1);
		 return true;
	}
//	return false;
}

$(function(){
    $(".days").focus(function(){
        var $star=new Date($("#starTime").val());
        var $end=new Date($("#endTime").val());
        tt=$end.getTime()-$star.getTime();

        var $now = new Date();  //当前日期
        tt2=$end.getTime()-$now.getTime();
        tt3=$now.getTime()-$end.getTime();
        $(".days").val(Math.ceil(tt/ (24*60*60*1000)));
		if(tt2>0){
            $(".days2").val("无");
		}else{
            $(".days2").val("延期了"+Math.ceil(tt3/ (24*60*60*1000))+"天");
		}
    });

    laydate.render({
        elem: '#starTime'
        , type: "datetime",
        calendar: true,
        done: function (value) {

        }
    });

    laydate.render({
        elem: '#endTime'
        , type: "datetime",
        calendar: true,
        done: function (value) {

        }
    });
})


	$(function(){
		$('.reciver').on('click',function(){
			$('#myModal').modal("toggle");
			$(this).siblings("input").val("");
			$('.reciver').removeClass("qu");
			$(this).addClass("qu");
		});
		$(".recive_list").change(function(){
			var	$val=$(this).val();
			$(".qu").siblings("input").val($val);
		
		});
		
		$(".tbody").on("click",".sub",function(){
			$('#subject').modal("toggle");
			$(this).siblings("input").val("");
			$('.sub').removeClass("je");
			$(this).addClass("je");
		});
		$(".ject").change(function(){
			var	$val=$(this).val();
			$(".je").siblings("input").val($val);
			console.log("jinlai");
		
		});




		var i=1;
		//增加一行
		$(".zeng").click(function(){
		/*	var date=new Date();
			var nowDate=date.Format('yyyy-MM-dd hh:mm:ss');
			var star=addDate(nowDate,0);*/
			var td1 = $('<td class="chebox" colspan="2"></td>').append($('<span class="labels"></span>').append($('<label></label>').append($('<input type="checkbox" name="items"  class="val" >')).append($('<i></i>').text('✓'))));
			// var td2 = $('<td  colspan="2"></td>').append($('<input type="text" class="form-control inpu incar" name="details['+i+'].StartTime"/>').val(star));
			var td2 = $('<td colspan="2"></td>').append($('<input type="text" class="form-control inpu" name="details['+i+'].complete"/>'));
			var td3 = $('<td colspan="2"></td>').append($('<input type="text" class="form-control inpu" name="details['+i+'].unfinished"/>'));
			var td4 = $('<td colspan="2"></td>').append($('<input type="text" class="form-control inpu" name="details['+i+'].point"/>'));
			var td5 = $('<td colspan="2"></td>').append($('<input type="text" class="form-control inpu" name="details['+i+'].coordination"/>'));
            var tr = $('<tr class="tr"></tr>').append(td1).append(td2).append(td3).append(td4).append(td5);
			$('.tbody').append(tr);
			i=i+1;
		});

	
		//把tr置空
		$(".jian").click(function(){
			
			 $("[name=items]:checkbox").each(function(){
				 if(this.checked){
	    			//获取被选中了的行
	    			var $tr=$(this).parents(".tr");
					$tr.html("");
	    					
	    			}
			 })
		});
		
		
		
	});
	
	
</script>
<#--<#include "/common/reciver.ftl">-->
<#--<#include "/process/subject.ftl">-->
<#--<script type="text/javascript" src="js/common/data.js"></script>-->
<#--<script type="text/javascript" src="plugins/My97DatePicker/WdatePicker.js"></script>-->
<script src="/js/laydate/laydate.js"></script>