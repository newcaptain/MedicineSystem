<%@page pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>欢迎页面-X-admin2.0</title>
	<meta name="renderer" content="webkit">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
	<meta name="viewport"
	      content="width=device-width,user-scalable=yes, minimum-scale=0.4, initial-scale=0.8,target-densitydpi=low-dpi"/>
	<link rel="shortcut icon" href="/favicon.ico" type="image/x-icon"/>
	<link rel="stylesheet" href="./css/font.css">
	<link rel="stylesheet" href="./css/xadmin.css">
	<script type="text/javascript" src="https://cdn.bootcss.com/jquery/3.2.1/jquery.min.js"></script>
	<script type="text/javascript" src="./lib/layui/layui.js" charset="utf-8"></script>
	<script type="text/javascript" src="./js/xadmin.js"></script>
	<!-- 让IE8/9支持媒体查询，从而兼容栅格 -->
	<!--[if lt IE 9]>
	<script src="https://cdn.staticfile.org/html5shiv/r29/html5.min.js"></script>
	<script src="https://cdn.staticfile.org/respond.js/1.4.2/respond.min.js"></script>
	<![endif]-->
</head>

<body>
<div class="x-nav">
      <span class="layui-breadcrumb">
        <a href="">首页</a>
        <a href="">演示</a>
        <a>
          <cite>导航元素</cite></a>
      </span>
	<a class="layui-btn layui-btn-small" style="line-height:1.6em;margin-top:3px;float:right"
	   href="javascript:location.replace(location.href);" title="刷新">
		<i class="layui-icon" style="line-height:30px">ဂ</i></a>
</div>
<div class="x-body">
	<div class="layui-row">
		<form class="layui-form layui-col-md12 x-so">
			<%--<input class="layui-input" placeholder="开始日" name="start" id="start">--%>
			<%--<input class="layui-input" placeholder="截止日" name="end" id="end">--%>
			<input type="text" name="username" placeholder="请输入用户名" autocomplete="off" class="layui-input">
			<button class="layui-btn" lay-submit="" lay-filter="sreach"><i class="layui-icon">&#xe615;</i></button>
		</form>
	</div>
	<xblock>
		<button class="layui-btn layui-btn-danger" onclick="delAll()"><i class="layui-icon"></i>批量删除</button>
		<button class="layui-btn" onclick="x_admin_show('添加员工','./agency-add.html',560,500)"><i class="layui-icon"></i>添加
		</button>
		<span class="x-right" style="line-height:40px">共有数据：${rows} 条</span>
	</xblock>
	<table class="layui-table">
		<thead>
		<tr>
			<th>
				<div class="layui-unselect header layui-form-checkbox" lay-skin="primary"><i
						class="layui-icon">&#xe605;</i></div>
			</th>
			<th>编号</th>
			<th>姓名</th>
			<th>性别</th>
			<th>手机</th>
			<th>备注</th>
			<th>操作</th>
		</thead>
		<tbody>
			<c:forEach var="item" items="${list}">
				<tr>
					<td>
						<div class="layui-unselect layui-form-checkbox" lay-skin="primary" data-id='${item.ano}'><i class="layui-icon">&#xe605;</i>
						</div>
					</td>
					<td>${item.ano}</td>
					<td>${item.aname}</td>
					<td>${item.asex}</td>
					<td>${item.aphone}</td>
					<td>${item.aremark}</td>
					<td class="td-manage">
						<!-- <a onclick="member_stop(this,'10001')" href="javascript:;"  title="启用">
						  <i class="layui-icon">&#xe601;</i>
						</a> -->
						<a title="编辑" onclick="x_admin_show('编辑','/AgencyEdit?ano=${item.ano}')" href="javascript:;">
							<i class="layui-icon">&#xe642;</i>
						</a>
						<a title="删除" onclick="agency_del(this,${item.ano})" href="javascript:;">
							<i class="layui-icon">&#xe640;</i>
						</a>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<div class="page">
		<div>
			<c:if test="${rows != 0}">
				<a class="prev" href="">&lt;&lt;</a>
			</c:if>
			<c:forEach items="${currentPage-1},${currentPage},${currentPage+1}" var="page">
				<c:if test="${page>0 && page<=pageCount}">
					<c:if test="${page == currentPage}">
						<span class="current">${page}</span>
					</c:if>
					<c:if test="${page != currentPage}">
						<a class="num" href="">${page}</a>
					</c:if>
				</c:if>
			</c:forEach>
			<c:if test="${rows != 0}">
				<a class="next" href="">&gt;&gt;</a>
			</c:if>
		</div>
	</div>

</div>
<script>
    layui.use('laydate', function () {
        var laydate = layui.laydate;

        //执行一个laydate实例
        laydate.render({
            elem: '#start' //指定元素
        });

        //执行一个laydate实例
        laydate.render({
            elem: '#end' //指定元素
        });
    });

    /*用户-停用*/
    function member_stop(obj, id) {
        layer.confirm('确认要停用吗？', function (index) {

            if ($(obj).attr('title') == '启用') {

                //发异步把用户状态进行更改
                $(obj).attr('title', '停用')
                $(obj).find('i').html('&#xe62f;');

                $(obj).parents("tr").find(".td-status").find('span').addClass('layui-btn-disabled').html('已停用');
                layer.msg('已停用!', {icon: 5, time: 1000});

            } else {
                $(obj).attr('title', '启用')
                $(obj).find('i').html('&#xe601;');

                $(obj).parents("tr").find(".td-status").find('span').removeClass('layui-btn-disabled').html('已启用');
                layer.msg('已启用!', {icon: 5, time: 1000});
            }

        });
    }

    /*用户-删除*/
    function agency_del(obj, no) {
        layer.confirm('确认要删除吗？', function (index) {
            var load = layer.load();
            $.ajax({
	            url: "/api/agency/delete",
	            type: 'POST',
	            data: {ano: no},
	            success: function (res) {
	                layer.close(load);
		            if (res.code == 0) {
                        $(obj).parents("tr").remove();
		                layer.msg("删除成功",{icon: 1});
		            } else {
		                layer.alert(res.msg, {icon: 2})
		            }
                }
            });
        });
    }
    function delAll(argument) {
        layer.confirm("确定要删除员工吗？", function (index) {
            var data = tableCheck.getData();
            $.ajax({
                url: '/api/agency/deleteAll',
                type: 'POST',
                data: {anos: data},
                success: function (res) {
                    if (res.code == 0) {
                        layer.msg("删除成功",{icon: 1});
                        $(".layui-form-checked").not('.header').parents('tr').remove();
                    } else {
                        layer.alert(res.msg, {icon: 2});
                    }
                }
            });
        });

    }
</script>
</body>

</html>