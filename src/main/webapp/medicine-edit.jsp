<%@page language="java" pageEncoding="utf-8" %>
<%--<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>--%>
<!DOCTYPE html>
<html>

<head>
	<meta charset="UTF-8">
	<title>医药信息管理系统</title>
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
		<div class="layui-form-item">
			<label for="L_no" class="layui-form-label">
				<span class="x-red">*</span>编号
			</label>
			<div class="layui-input-inline">
				<input type="text" id="L_no" name="mno" class="layui-input" readonly value="${medicine.mno}">
			</div>
		</div>
		<div class="layui-form-item">
			<label for="L_name" class="layui-form-label">
				<span class="x-red">*</span>药品名
			</label>
			<div class="layui-input-inline">
				<input type="text" id="L_name" name="mname" required lay-verify="required"
				       autocomplete="off" class="layui-input" value="${medicine.mname}">
			</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">
				<span class="x-red">*</span>服用方式
			</label>
			<div class="layui-input-block">
				<input type="radio" name="mmode" value="外服" title="外服" ${medicine.mmode=="外服"?"checked":""}>
				<input type="radio" name="mmode" value="内服" title="内服" ${medicine.mmode=="内服"?"checked":""}>
			</div>
		</div>
		<div class="layui-form-item">
			<label for="L_efficacy" class="layui-form-label">
				<span class="x-red">*</span>功效
			</label>
			<div class="layui-input-inline">
				<input type="text" id="L_efficacy" name="mefficacy" required lay-verify="required"
				       autocomplete="off" class="layui-input" value="${medicine.mefficacy}">
			</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">
			</label>
			<button class="layui-btn" lay-filter="edit" lay-submit="">
				修改
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
        form.on('submit(edit)', function (data) {
            var load = layer.load();
            $.ajax({
	            url: '/api/medicine/edit',
	            type: 'POST',
	            data: data.field,
	            success: function (res) {
	                layer.close(load);
		            if (res.code == 0) {
		                layer.msg("修改成功",{icon: 1}, function () {
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