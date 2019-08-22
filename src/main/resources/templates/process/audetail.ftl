<#include "/common/commoncss.ftl">
<link rel="stylesheet" href="css/common/tanchuang.css" />
<style>
a {
	color: black;
}

a:hover {
	text-decoration: none;
}



.text {
	min-height: 114px;
}
.reciver{
	position: relative;
    top: -23px;
    float: right;
    right: 4px;
    cursor: pointer;
}
.text{
	height:114px;
}
.page{
	margin-bottom: 25px;
    margin-top: 15px;
    margin-left: -10px;
    margin-right: -10px;
}
.shuxian{
	height: 30px;
    border-left: 1px solid #eee;
    margin-left: 35px;
    margin-bottom: 8px;
    margin-top: 8px;
}
.shen{
	min-height:50px;
	border:0px solid red;
	margin-left: 13px;
    margin-right: 17px;
    position: relative;
}
.content{
	display: inline-block;
    background-color: #eee;
    width: 95%;
    min-height: 80px;
    border-radius: 5px;
    margin-left: 54px;
}
.pa{
	padding-left: 13px;
    
}
</style>


<div class="row" style="padding-top: 10px;">
	<div class="col-md-2">
		<h1 style="font-size: 24px; margin: 0;" class="">${typename}</h1>
	</div>
	<#--<div class="col-md-10 text-right">
		<a href="##"><span class="glyphicon glyphicon-home"></span> 首页</a> > <a
			disabled="disabled">${typename}</a>
	</div>-->
</div>
<div class="row" style="padding-top: 15px;">
	<div class="col-md-12">
		
		<div class="bgc-w box">
			
			<div class="box-header">
				<h3 class="box-title">
					<a href="javascript:history.back();" class="label label-default"
						style="padding: 5px;">
						 <i class="glyphicon glyphicon-chevron-left"></i> <span>返回</span>
					</a>
				</h3>
			</div>
		<form action="susave">
		
			<div class="box-body">
				<#if statusid==25 ||statusid==26>
					<div>
						<i class="glyphicon glyphicon-record" style="color:#9E9E9E;padding-left: 13px;"></i> 结束审核
					</div>
					<div class="shuxian"></div>
				</#if>
				<#list revie as list>
				
				<div class="shen">
					<p>${(list.poname)!''}：${(list.username)!''}<span class="pull-right">${(list.retime)!''}</span></p>
					<div>
					<div style="display:inline-block;position:absolute;">
					<#if list.img?? && list.img!=''>
						<img style="width: 50px;height: 50px;border-radius: 72px; position: relative;bottom:0px;right:0px;"
							src="/image/${list.img}" />
						<#else>
						<img style="width: 50px;height: 50px;border-radius: 72px; position: relative;bottom:0px;right:0px;"
							src="images/timg.jpg" alt="images"/>
					</#if>	
					</div>
					<div class="content">
						<p class="pa" style="padding-top: 9px;">审核状态:<i class="label ${list.statuscolor}">${(list.restatus)!''}</i></p>
						<p class="pa" >审核意见：${(list.des)!''}</p>
					</div>
					</div>
				</div>
				<div class="shuxian"></div>
				</#list>
				<div class="shen">
					<p>申请人：${(process.userId.userName)!''}<span class="pull-right">${(process.applyTime)!''}</span></p>
					<div >
					<div style="display:inline-block;position:absolute;">
					<#if process.userId.imgPath?? && process.userId.imgPath!=''>
						<img style="width: 50px;height: 50px;border-radius: 72px; position: relative;bottom:0px;right:0px;"
							src="/image/${process.userId.imgPath}" />
						<#else>
						<img style="width: 50px;height: 50px;border-radius: 72px; position: relative;bottom:0px;right:0px;"
							src="images/timg.jpg" alt="images"/>
					</#if>	
					</div>
					<div class="content">
					<#if typename=="费用报销">
					<p class="pa" style="padding-top: 9px;">报销总金额：${(bu.allMoney)!''}</p>
					<p class="pa" >报销理由：${(process.processDescribe)!''}</p>
					</#if>
					<#if typename=="付款申请">
					<p class="pa" style="padding-top: 9px;">付款总金额：${(bu.allMoney)!''}</p>
					<p class="pa" >付款理由：${(process.processDescribe)!''}</p>
					</#if>
					<#if typename=="出差费用">
					<p class="pa" style="padding-top: 9px;">申请总金额：${(bu.money)!''}</p>
					<p class="pa" >申请理由：${(process.processDescribe)!''}</p>
					</#if>
					<#if typename=="出差申请" || typename=="加班申请" ||typename=="请假申请">
					<p class="pa" style="padding-top: 9px;">开始时间：${(process.startTime)!''}</p>
					<p class="pa" >结束时间：${(process.endTime)!''}</p>
					<p class="pa" >申请理由：${(process.processDescribe)!''}</p>
					</#if>
					<#if typename=="转正申请">
					<p class="pa" style="padding-top: 9px;">试用/实习职位：${(position.position.name)!''}</p>
					<p class="pa" >开始时间：${(process.startTime)!''}</p>
					<p class="pa" >结束时间：${(process.endTime)!''}</p>
					</#if>
					<#if typename=="离职申请">
					<p class="pa" style="padding-top: 9px;">在职岗位：${(position.position.name)!''}</p>
					<p class="pa" >离职理由：${(process.processDescribe)!''}</p>
					</#if>
					</div>
					</div>
				</div>
				<div class="shuxian"></div>
				<div>
					<i class="glyphicon glyphicon-record" style="color:#9E9E9E;padding-left: 13px;"></i> 开始申请
				</div>
				<div class="page-header page"></div>
				<div class="col-md-6 form-group" style="z-index: 1;">
					<label class="control-label">审核状态</label>
						 <select class="form-control" name="statusId" class="sele">
							<option value="25">已批准</option>
							<option value="26">未通过</option>
						</select>
				</div>
				<#if typename=="请假申请" && positionid!=7 ||typename=="费用报销" && positionid!=3||typename=="出差申请" && positionid!=3 ||typename=="付款申请" && positionid!=3 ||typename=="加班申请" && positionid!=5>   <#--不等于三就显示-->
                        <div class="col-md-6 form-group" style="position: relative;">
							<#--<#if typename=="请假申请" && shen.getUserId==2  && positionid==5 || typename=="请假申请" && shen.getUserId==3  && positionid==5 >
                                <label class="control-label" data-toggle="modal" data-target="#myModal">下一步审核人</label>
                                <input name="username" type="text" id="recive_list"
                                       class="form-control " readonly="readonly" style="background-color:#fff;"
                                       value="范琳琳"/>
								</#if>-->
							<#if  positionid==5  >
							<label class="control-label" data-toggle="modal" data-target="#myModal">下一步审核人</label>
                                <input name="username" type="text" id="recive_list"
                                       class="form-control " readonly="readonly" style="background-color:#fff;"
                                       value="范琳琳"/>
							</#if>

								<#if  positionid==4 || positionid==6 || positionid==8 >
								 <label class="control-label" data-toggle="modal" data-target="#myModal">下一步审核人</label>
                            <input name="username" type="text" id="recive_list"
                                   class="form-control " readonly="readonly" style="background-color:#fff;" value="高芳"/>
								</#if>
									<#if  positionid==3>  <#--老板-->
								 <label class="control-label" data-toggle="modal" data-target="#myModal">下一步审核人</label>
                            <input name="username" type="text" id="recive_list"
                                   class="form-control " readonly="readonly" style="background-color:#fff;" value="孙晓爽"/>
							</#if>
                        </div>
				</#if>
				 <div class="col-md-6 form-group" style="float: none;">
					<label class="control-label">审核理由</label>
					<textarea class="form-control text" name="advice" >已审核</textarea>
				</div> 
				
			</div>
			<div class="box-footer" style="padding-left: 26px;">
			<input type="text" style="display:none;" value="${typename}" name="type"/>
			<input type="text" style="display:none;" name="proId" value="${(process.processId)!''}"/>
                <#--onclick="javascript:{this.disabled=true; }"-->
			<#if typename=="请假申请">   <#--人事-->
				<#if  ustatusid ==23>
					<#if positionid==7 >
					<input class="btn btn-success" id="save"  type="submit" value="审核并结案" />
						<#else>
						<input class="btn btn-info" id="saves"  type="submit" value="审核并流转" name="liuzhuan"/>
					</#if>
				</#if>
				<#elseif typename=="加班申请">   <#--财务-->
					<#if  ustatusid ==23>
						<#if positionid==5 >
						<input class="btn btn-success" id="save"  type="submit" value="审核并结案" />
						<#else>
						<input class="btn btn-info" id="saves"  type="submit" value="审核并流转" name="liuzhuan"/>
						</#if>
					</#if>
			    <#elseif ustatusid ==23>  <#--其它-->
				<#if positionid==3>
					<input class="btn btn-success" id="save"  type="submit" value="审核并结案" />
				<#else>
					<input class="btn btn-info liu" id="saves"  type="submit" value="审核并流转" name="liuzhuan" />
				</#if>

				<input class="btn btn-success jie" id="save"  type="submit" value="审核并结案"  style="display:none;"/>
				</#if>



				<input class="btn btn-default" id="cancel" type="button" value="取消"
					onclick="window.history.back();" />
                <br/>
                <br/>
                <p style="font-size:15px;color:red">温馨提示:请勿重复点击,点击完请稍等片刻...</p>
			</div>
			</form>
		</div>
	</div>
</div>
<script>
	$(function(){
		$(".text").click(function(){
			var se=$("select").find("option:selected").text();
			if(se=="未通过"){
				$(".liu").css("display","none");
				$(".jie").css("display","inline-block");
			}else{
                $(".jie").css("display","none");
                $(".liu").css("display","inline-block");
			}
		});
		
		$("select").each(function(){
			console.log("ss");
			var se=$("select").find("option:selected").text();
			console.log(se);
		});


       /* $("save").click(function(){
            $(this).attr("disabled","disabled");
            document.getElementById("save").setAttribute("disabled", true);
        });

        $("saves").click(function(){
            $(this).attr("disabled","disabled");
            document.getElementById("saves").setAttribute("disabled", true);
        });*/

    })

</script>

<#include "/common/reciver.ftl">
