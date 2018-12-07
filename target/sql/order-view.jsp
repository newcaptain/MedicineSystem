<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt" %>
<%@page language="java" pageEncoding="utf-8" %>
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
	<style>
		html,body {
			margin: 0;
			paddin: 0;
			height: 94%;
		}
	</style>
</head>
<body>
<div class="x-body" style="background: #EEE8AA;height: 100%;" >
	<div class="layui-card">
		<div class="layui-card-header">
			订单号：${item.id}
		</div>
		<div class="layui-card-body">
			<ul>
				<li>顾客： ${item.cname}</li>
				<li>性别：${item.csex}</li>
				<li>年龄：${item.cage}</li>
				<li>症状：${item.symptom}</li>
				<li>联系方式：${item.cphone}</li>
				<li>药品名称： ${item.mname}</li>
				<li>药品功效：${item.mefficacy}</li>
				<li>服用方式：${item.mmode}</li>
				<li>销售人员：${item.aname}</li>
				<li>下单时间：<fmt:formatDate pattern="yyyy-mm-dd HH:mm" value="${item.odate}" /></li>
			</ul>
		</div>
	</div>
	<button class="layui-btn" style="margin: 40px auto; display: block;" id="confirm">确定</button>
</div>
<script>
    layui.use(['layer'], function () {
        $ = layui.jquery;
        var layer = layui.layer;

        $('#confirm').click(function () {
            var index = parent.layer.getFrameIndex(window.name);
            parent.layer.close(index);
        })

    });
</script>
</body>

</html>