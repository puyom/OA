<#include "/common/commoncss.ftl">
<link rel="stylesheet" href="css/common/tanchuang.css"/>
<style>
    .box-header {
        text-align: center;
        border-bottom: 0px solid #f4f4f4;
    }

    .title {
        text-align: center;
    }

    .control-label {
        display: inline-block;
        font-weight: 400;
    }

    .bo {
        margin: 0px auto;
        width: 80%;
    }

    .title {
        font-weight: 400;
        text-align: center;
        color: #2196F3;
    }

    .inside {
        width: 100%;
    }

    .inside th, .inside td {
        text-align: center;
    }

    .inside thead, .inside tfoot, .bac {
        background-color: #6d9eeb;
        color: #fff;
    }

    .inside > tbody > tr > td {
        border-top: 0px solid #ddd;
    }

    .inside > tbody > tr > td {
        border-bottom: 1px solid #ddd;
        border-left: 1px solid #ddd;
    }

    .bo > tbody > tr > td, .inside > thead > tr > th {
        border-top: 0px solid #ddd;
        border-bottom: 0px solid #ddd;
        border-left: 0px solid #ddd;
    }

    .food {
        padding-left: 10px
    }

    .bottom {
        border-bottom: 1px solid rgba(0, 0, 0, 0.31);
        height: 19px;
    }

    .wi {
        color: #2196F3;
    }
    .wi222 {
        color: #2196F3;
        float:right;
    }

    .rile {
        border-left: 1px solid #2196F3;
        border-right: 1px solid #2196F3;
    }

    .top {
        border-left: 1px solid #2196F3;
        border-right: 1px solid #2196F3;
    }

    .ss {
        border-top: 1px solid #2196F3;
    }

    .bo tr {
        height: 50px;
    }

    .top td {
        height: 30px;
    }

    .top label {
        padding-top: 16px;
        border-bottom: 2px solid #2196F3;
    }

    .top div {
        border-bottom: 1px solid rgba(0, 0, 0, 0.31);
        height: 40px;
    }

    .two label {
        padding-top: 15px;
    }

    .two div {
        margin-top: 15px;
    }

    .font {
        font-size: 16px;
    }

    .inside tr {
        height: 0px;
    }

    .inside thead th, .inside tfoot td {
        border-right: 1px solid #fff;
        font-weight: 400;
    }

    .mm {
        border-right: 0px solid #fff
    }

    .mon {
        margin-left: -8.5px;
        margin-top: -8.5px;
        height: 75px;
        color: #fff;
    }

    .out {
        border-left: 1px solid #eee;
        border-right: 1px solid #eee;
        border-bottom: 1px solid #eee;
        height: 250px;
    }

    .fo {
        border-top: 1px solid #2196F3;

    }
</style>
<div class="row" style="padding-top: 10px;">
<#--<div class="col-md-2">
		<h1 style="font-size: 24px; margin: 0;" class="">${(map.typename)!''}</h1>
	</div>
	<div class="col-md-10 text-right">
		<a href="##"><span class="glyphicon glyphicon-home"></span> 首页</a> > <a
			disabled="disabled">${(map.typename)!''}</a>
	</div>-->
</div>
<div class="row" style="padding-top: 15px;">
    <div class="col-md-12">

        <div class="bgc-w box">

            <div class="box-header">
                <table class="bo table">

                    <tr>
                        <td colspan="14" class="title"><h2>项目表明细</h2></td>
                    </tr>
                    <tr style="opacity: 0;">
                        <td colspan="14">11</td>
                    </tr>


                    <tr class="top">
                        <td colspan="14" class="wi ">
                            <div class="bottom"><label class="control-label font">项目明细</label></div>
                        </td>
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
                                    <tbody class="tbody">
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
                                    </tbody>
                                </table>
                            </div>
                        </td>
                    </tr>
                    <hr/>
                    <#--<tr class="top">
                        <td colspan="14" class="wi ">
                            <div class="bottom"><label class="control-label font">项目进度</label></div>
                        </td>
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
                                    <tbody class="tbody">
							<#list Stay2 as pro>
                            <tr class="tr">
                                <td style="border-left: 0px ;" colspan="2"><span>${pro_index+1}</span></td>
                                <td colspan="2"><span>${(pro.projectSchedule)!''}</span></td>
                                <td colspan="2"><span>${(pro.startTime)!''}</span></td>
                                <td colspan="2"><span>${(pro.endTime)!''}</span></td>
                            </tr>
                            </#list>
                                    </tbody>
                                </table>
                            </div>
                        </td>
                    </tr>-->
                </table>
            </div>
        </div>
    </div>
</div>
<script>
    $(function () {

        $('.yulan').popover({
            html: true,
            placement: 'auto right',
            trigger: 'hover click',
            template: '<div class="popover" role="tooltip"><div class="arrow"></div>'
                    + '<h3 class="popover-title"></h3><div><img src="show/${(map.filepath)!''}"style="max-width: 200px;"/></div><div class="popover-content"></div></div>'
        })
    });

    function print1() {
        document.getElementById("print1").style.display = "none";
       /* var obj=document.getElementById("print2").innerText;
        if(obj==null){

        }else{
            document.getElementsByName("print2").style.display = "none";
        }*/
        $("[name='dayin2']").hide();
        $("[name='dayin3']").hide();
        window.print();
    }
</script>