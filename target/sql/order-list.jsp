<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt"%>

<%@page pageEncoding="UTF-8" %>
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
	<a class="layui-btn layui-btn-small" style="line-height:1.6em;margin-top:3px;float:right"
	   href="javascript:location.replace(location.href);" title="刷新">
		<i class="layui-icon" style="line-height:30px">ဂ</i></a>
</div>
<div class="x-body">
	<div class="layui-row">
		<form class="layui-form layui-col-md12 x-so">
			<input class="layui-input" placeholder="开始日" name="start" id="start">
			<input class="layui-input" placeholder="截止日" name="end" id="end">
			<input type="text" name="username" placeholder="请输入订单号" autocomplete="off" class="layui-input">
			<button class="layui-btn" lay-submit="" lay-filter="sreach"><i class="layui-icon">&#xe615;</i></button>
		</form>
	</div>
	<xblock>
		<button class="layui-btn layui-btn-danger" onclick="delAll()"><i class="layui-icon"></i>批量删除</button>
		<%--<button class="layui-btn" onclick="x_admin_show('添加用户','./order-add.html')"><i class="layui-icon"></i>添加--%>
		<%--</button>--%>
		<span class="x-right" style="line-height:40px">共有数据：${rows} 条</span>
	</xblock>
	<table class="layui-table">
		<thead>
		<tr>
			<th>
				<div class="layui-unselect header layui-form-checkbox" lay-skin="primary"><i
						class="layui-icon">&#xe605;</i></div>
			</th>
			<th>订单号</th>
			<th>顾客姓名</th>
			<th>药品名称</th>
			<th>销售人员</th>
			<th>下单时间</th>
			<th>操作</th>
		</tr>
		</thead>
		<tbody>
			<c:forEach var="item" items="${list}">
				<tr>
					<td>
						<div class="layui-unselect layui-form-checkbox" lay-skin="primary" data-id='${item.id}'><i class="layui-icon">&#xe605;</i>
						</div>
					</td>
					<td>${item.id}</td>
					<td>${item.cname}</td>
					<td>${item.mname}</td>
					<td>${item.aname}</td>
					<td><fmt:formatDate pattern="yyyy-mm-dd HH:mm" value="${item.odate}" /></td>
					<td class="td-manage">
						<a title="查看" onclick="x_admin_show('查看订单','/OrderView?id=${item.id}', 580, 550)" href="javascript:;">
							<i class="layui-icon">&#xe63c;</i>
						</a>
						<a title="删除" onclick="order_del(this,${item.id})" href="javascript:;">
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
				<a class="prev" href="/OrderList?currentPage=1">&lt;&lt;</a>
			</c:if>
			<c:forEach var="i" items="${currentPage-1},${currentPage},${currentPage+1}">
				<c:if test="${(i>0 && i<=pageCount)}">
					<c:if test="${i != currentPage}">
						<a class="num" href="/OrderList?currentPage=${i}"/>">${i}</a>
					</c:if>
					<c:if test="${i == currentPage}">
						<span class="current">${i}</span>
					</c:if>
				</c:if>
			</c:forEach>
			<c:if test="${rows != 0}">
				<a class="next" href="/OrderList?currentPage=${pageCount}">&gt;&gt;</a>
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

    /*订单-删除*/
    function order_del(obj, id) {
        layer.confirm('确认要删除吗？', function (index) {
            var load = layer.load();
            $.ajax({
	            url: '/api/order/delete',
	            type: 'POST',
	            data: {id: id},
	            success: function(res) {
	                layer.close(load);
	                if (res.code == 0) {
                        $(obj).parents("tr").remove();
                        layer.msg('已删除!', {icon: 1, time: 1000});
	                } else {
	                    layer.alert(res.msg, {icon: 2});
	                }
	            }
            });
        });
    }

    function delAll(argument) {
        var data = tableCheck.getData();
        layer.confirm('确认要删除吗？', function (index) {
            var load = layer.load();
            $.ajax({
	            url: '/api/order/deleteAll',
	            type: 'POST',
	            data: {ids: data},
	            success: function (res) {
	                layer.close(load);
		            if (res.code == 0) {
                        layer.msg('删除成功', {icon: 1});
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