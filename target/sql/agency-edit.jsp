<%@ page pageEncoding="UTF-8" %>
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
		<div class="layui-form-item">
			<label for="L_no" class="layui-form-label">
				<span class="x-red">*</span>编号
			</label>
			<div class="layui-input-inline">
				<input type="text" id="L_no" name="ano" readonly
				       autocomplete="off" class="layui-input" value="${agency.ano}">
			</div>
		</div>
		<div class="layui-form-item">
			<label for="L_name" class="layui-form-label">
				<span class="x-red">*</span>姓名
			</label>
			<div class="layui-input-inline">
				<input type="text" id="L_name" name="aname" required="" lay-verify="required"
				       autocomplete="off" class="layui-input" value="${agency.aname}">
			</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">
				<span class="x-red">*</span>性别
			</label>
			<div class="layui-input-block">
				<input type="radio" name="asex" value="男" title="男" ${agency.asex == "男"?"checked":""}>
				<input type="radio" name="asex" value="女" title="女" ${agency.asex == "女"?"checked":""}>
			</div>
		</div>
		<div class="layui-form-item">
			<label for="L_phone" class="layui-form-label">
				<span class="x-red">*</span>手机
			</label>
			<div class="layui-input-inline">
				<input type="text" id="L_phone" name="aphone" required="" lay-verify="required|phone"
				       autocomplete="off" class="layui-input" value="${agency.aphone}">
			</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label"><span class="x-red">*</span>备注</label>
			<div class="layui-input-block">
				<input type="radio" name="aremark" title="超级管理员" value="超级管理员" ${agency.aremark == "超级管理员"?"checked":""}>
				<input type="radio" name="aremark" title="普通员工" value="普通员工" ${agency.aremark == "普通员工"?"checked":""}>
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
                url: "/api/agency/edit",
                type: "POST",
                data: data.field,
	            success: function(res) {
                    layer.close(load);
                    if (res.code == 0) {
                        layer.msg("修改成功",{icon: 1});
                        var index = parent.layer.getFrameIndex(window.name);
                        parent.layer.close(index);
                        parent.location.reload();
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