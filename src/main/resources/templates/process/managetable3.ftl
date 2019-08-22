<div class="bgc-w box box-primary">
    <!--盒子头-->
    <div class="box-header">
        <h3 class="box-title">
            <a href="" class="label label-success" style="padding: 5px;">
                <span class="glyphicon glyphicon-refresh"></span> 刷新
            </a>
        </h3>
        <div class="box-tools">
            <div class="input-group" style="width: 220px;">
                <input type="text" class="form-control input-sm cha"
                       placeholder="审核人，类型，标题，状态查询"/>
                <div class="input-group-btn chazhao">
                    <a class="btn btn-sm btn-default"><span
                            class="glyphicon glyphicon-search"></span></a>
                </div>
            </div>
        </div>
    </div>
    <div class="box-body no-padding">
        <div class="table-responsive">
            <table class="table table-hover">
                <tr>
                <#--<th scope="col">项目类</th>-->
                    <th scope="col" >标题</th>
                    <th scope="col">项目负责人</th>
                    <th scope="col">项目进度</th>
                    <th scope="col">更新时间</th>
                    <th scope="col">完成</th>
                    <th scope="col">未完成</th>
                <#--<th scope="col">未完成原因，困难点</th>-->
                    <th scope="col">需协同</th>
                    <th scope="col">紧急程度</th>
                    <th scope="col">操作</th>
                </tr>


            <#---->
						<#list list as pro>
                            <#if (pro.details[pro.details?size-1].startTime)?string("yyyy-MM-dd") == format>
                            <tr style="background-color: green">
                            <td><span>${(pro.projectName)!''}</span></td>
                            <td><span>${(pro.userName)!''}</span></td>
                            <td><span>${pro.stay2List[pro.stay2List?size-1].projectSchedule}</span></td>

                            <td><span>${(pro.details[pro.details?size-1].startTime)}</span></td>
                            <td><span>${pro.details[pro.details?size-1].complete}</span></td>
                            <td><span>${pro.details[pro.details?size-1].unfinished}</span></td>
                        <#--<td><span>${pro.details[pro.details?size-1].point}</span></td>-->
                            <td><span>${pro.details[pro.details?size-1].coordination}</span></td>
                            <td><span>${(pro.emergency)!''}</span></td>

                            <td><a href="/worker2?projectId=${(pro.projectId)!''}" class="label xiugai"><span
                                    class="glyphicon glyphicon-search"></span> 查看</a>
                            </td>
                            </tr>
                            <#elseif (pro.details[pro.details?size-1].startTime)?string("yyyy-MM-dd") != format>
                             <tr style="background-color: yellow">
                                <td><span>${(pro.projectName)!''}</span></td>
                                <td><span>${(pro.userName)!''}</span></td>
                                <td><span>${pro.stay2List[pro.stay2List?size-1].projectSchedule}</span></td>

                                <td><span>${(pro.details[pro.details?size-1].startTime)}</span></td>
                                <td><span>${pro.details[pro.details?size-1].complete}</span></td>
                                <td><span>${pro.details[pro.details?size-1].unfinished}</span></td>
                            <#--<td><span>${pro.details[pro.details?size-1].point}</span></td>-->
                                <td><span>${pro.details[pro.details?size-1].coordination}</span></td>
                                <td><span>${(pro.emergency)!''}</span></td>

                                <td><a href="/worker2?projectId=${(pro.projectId)!''}" class="label xiugai"><span
                                        class="glyphicon glyphicon-search"></span> 查看</a>
                                </td>
                            </tr>
                            </#if>
                        </#list>
            </table>
        </div>

        <!--盒子尾-->
    <#--<#include "/common/paging.ftl">-->
    </div>
</div>
<script>
    /* 分页插件按钮的点击事件 */

    $('.baseKetsubmit').on('click', function () {
        var baseKey = $('.baseKey').val();
        $('.thistable').load('${url}?baseKey=baseKey');
    });


    $(function () {
        $(".chazhao").click(function () {
            var con = $(".cha").val();
            $(".thistable").load("shenser", {val: con});
        });

    });

</script>