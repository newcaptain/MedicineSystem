<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page pageEncoding="utf-8" %>
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
			<!--<input class="layui-input" placeholder="开始日" name="start" id="start">-->
			<!--<input class="layui-input" placeholder="截止日" name="end" id="end">-->
			<input type="text" name="username" placeholder="请输入用户名" autocomplete="off" class="layui-input">
			<button class="layui-btn" lay-submit="" lay-filter="sreach"><i class="layui-icon">&#xe615;</i></button>
		</form>
	</div>
	<xblock>
		<button class="layui-btn layui-btn-danger" onclick="delAll()"><i class="layui-icon"></i>批量删除</button>
		<button class="layui-btn" onclick="x_admin_show('添加药品','./medicine-add.html', 600, 400)"><i class="layui-icon"></i>添加药品
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
			<th>药品编号</th>
			<th>药品名称</th>
			<th>服用方式</th>
			<th>功效</th>
			<th>操作</th>
		</thead>
		<tbody>
			<c:forEach var="item" items="${list}">
				<tr>
					<td>
						<div class="layui-unselect layui-form-checkbox" lay-skin="primary" data-id='${item.mno}'><i class="layui-icon">&#xe605;</i>
						</div>
					</td>
					<td>${item.mno}</td>
					<td>${item.mname}</td>
					<td>${item.mmode}</td>
					<td>${item.mefficacy}</td>
					<td class="td-manage">
						<button class="layui-btn layui-btn-normal layui-btn-xs" onclick="x_admin_show('编辑','/MedicineEdit?mno=${item.mno}',650,500)"><i
								class="layui-icon">&#xe642;</i>编辑
						</button>
						<button class="layui-btn layui-btn-danger layui-btn-xs" onclick="medicine_del(this,${item.mno})"><i
								class="layui-icon">&#xe640;</i>删除
						</button>
						<button class="layui-btn layui-btn-warm layui-btn-xs" onclick="x_admin_show('购买','/MedicineShop?mno=${item.mno}')"><i
								class="layui-icon">&#xe698;</i>购买
						</button>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<div class="page">
		<div>
			<c:if test="${rows != 0}">
				<a class="prev" href="/MedicineList?currentPage=1">&lt;&lt;</a>
			</c:if>
			<c:forEach items="${currentPage-1},${currentPage},${currentPage+1}" var="page">
				<c:if test="${page>0 && page<=pageCount}">
					<c:if test="${page == currentPage}">
						<span class="current">${page}</span>
					</c:if>
					<c:if test="${page != currentPage}">
						<a class="num" href="/MedicineList?currentPage=${page}">${page}</a>
					</c:if>
				</c:if>
			</c:forEach>
			<c:if test="${rows != 0}">
				<a class="next" href="/MedicineList?currentPage=${pageCount}">&gt;&gt;</a>
			</c:if>

		</div>
	</div>

</div>
<script>
    function medicine_del(obj, id) {
        layer.confirm('确认要删除吗？', function (index) {
            var load = layer.load();
            $.ajax({
	            url: '/api/medicine/delete',
	            type: 'POST',
	            data: {mno: id},
	            success: function (res) {
	                layer.close(load);
		            if (res.code == 0) {
                        $(obj).parents("tr").remove();
                        layer.msg('药品删除成功!', {icon: 1, time: 1000});
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
	            url: '/api/medicine/deleteAll',
	            type: 'POST',
	            data: {mnos: data},
	            success: function (res) {
	                layer.close(load);
		            if (res.code == 0) {
                        $(".layui-form-checked").not('.header').parents('tr').remove();
                        layer.msg('药品删除成功', {icon: 1});
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