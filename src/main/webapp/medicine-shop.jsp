<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
</head>

<body>
<div class="x-body">
	<form class="layui-form">
		<div class="layui-form-item" >
			<label for="L_no" class="layui-form-label">
				<span class="x-red">*</span>药品编号
			</label>
			<div class="layui-input-inline">
				<input type="text" id="L_no" name="mno" class="layui-input" readonly value="${medicine.mno}">
			</div>
		</div>
		<div class="layui-form-item">
			<label for="L_name" class="layui-form-label">
				<span class="x-red">*</span>药品名称
			</label>
			<div class="layui-input-inline">
				<input type="text" id="L_name" class="layui-input" value="${medicine.mname}" style="border:0" readonly>
			</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">
				<span class="x-red">*</span>顾客</label>
			<div class="layui-input-inline">
				<select name="cno" lay-verify="required">
					<option value=""></option>
					<c:forEach var="client" items="${clientList}">
						<<option value="${client.cno}">${client.cname}</option>
					</c:forEach>
				</select>
			</div>
			<button class="layui-btn" onclick="x_admin_show('添加顾客','./client-add.html',720,550)"><i class="layui-icon"></i>添加
			</button>
		</div>
		<div class="layui-form-item">
			<label for="L_symptom" class="layui-form-label">
				<span class="x-red">*</span>症状
			</label>
			<div class="layui-input-inline">
				<input type="text" id="L_symptom" name="symptom" required lay-verify="required"
				       autocomplete="off" class="layui-input" >
			</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">
				<span class="x-red">*</span>员工</label>
			<div class="layui-input-inline">
				<select name="ano" lay-verify="required">
					<option value=""></option>
					<c:forEach var="agency" items="${agencyList}">
						<option value="${agency.ano}">${agency.aname}</option>
					</c:forEach>
				</select>
			</div>
			<button class="layui-btn" onclick="x_admin_show('添加员工','./agency-add.html',560,500)"><i class="layui-icon"></i>添加
			</button>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">
			</label>
			<button class="layui-btn" lay-filter="shop" lay-submit="">
				点击购买
			</button>
		</div>
	</form>
</div>
<script>
    layui.use(['form', 'layer'], function () {
        $ = layui.jquery;
        var form = layui.form
            , layer = layui.layer;

        //监听提交
        form.on('submit(shop)', function (data) {
            var load = layer.load();
            $.ajax({
	            url: '/api/medicine/shop',
	            type: 'POST',
	            data: data.field,
	            success: function (res) {
	                layer.close(load);
		            if (res.code == 0) {
		                layer.msg("购买成功",{icon: 1}, function () {
			                // 关闭当前窗口
			                var index = parent.layer.getFrameIndex(window.name);
			                parent.layer.close(index);
			                parent.location.reload();
                        })
		            } else {
		                layer.alert(res.msg, {icon: 2});
		            }
                }
            });
            return false;
        });
    });
</script>
</body>

</html>