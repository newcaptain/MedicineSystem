<%@ page language="java" pageEncoding="utf-8" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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

<body class="layui-anim layui-anim-up">
<div class="x-nav">
	<a class="layui-btn layui-btn-small" style="line-height:1.6em;margin-top:3px;float:right"
	   href="javascript:location.replace(location.href);" title="刷新">
		<i class="layui-icon" style="line-height:30px">ဂ</i>
	</a>
</div>
<div class="x-body">
	<div class="layui-row">
		<form class="layui-form layui-col-md12 x-so">
			<input type="text" name="cname" placeholder="请输入姓名" autocomplete="off" class="layui-input">
			<button class="layui-btn" lay-submit="" lay-filter="search"><i class="layui-icon">&#xe615;</i></button>
		</form>
	</div>
	<xblock>
		<button class="layui-btn layui-btn-danger" onclick="delAll()"><i class="layui-icon"></i>批量删除</button>
		<button class="layui-btn" onclick="x_admin_show('添加用户','./client-add.html',720,550)"><i class="layui-icon"></i>添加
		</button>
		<span class="x-right" style="line-height:40px">共有数据：<c:out value="${rows}"/> 条</span>
	</xblock>
	<table class="layui-table">
		<thead>
			<tr>
				<th>
					<div class="layui-unselect header layui-form-checkbox" lay-skin="primary"><i
							class="layui-icon">&#xe605;</i></div>
				</th>
				<th>ID</th>
				<th>姓名</th>
				<th>性别</th>
				<th>年龄</th>
				<th>地址</th>
				<th>手机</th>
				<th>备注</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody id="normal_table">
			<c:forEach var="item" items="${list}">
				<tr>
					<td>
						<div class="layui-unselect layui-form-checkbox" lay-skin="primary" data-id='${item.cno}'><i class="layui-icon">&#xe605;</i>
						</div>
					</td>
					<td><c:out value="${item.cno}"/> </td>
					<td><c:out value="${item.cname}"/> </td>
					<td><c:out value="${item.csex}"/> </td>
					<td><c:out value="${item.cage}"/> </td>
					<td><c:out value="${item.caddress}"/> </td>
					<td><c:out value="${item.cphone}"/> </td>
					<td><c:out value="${item.cremark}"/> </td>
					<!-- <td class="td-status">
					  <span class="layui-btn layui-btn-normal layui-btn-mini">已启用</span></td> -->
					<td class="td-manage">
						<button class="layui-btn layui-btn layui-btn-xs" onclick="x_admin_show('编辑','/ClientEdit?id=${item.cno}',720,550)"><i
								class="layui-icon">&#xe642;</i>编辑
						</button>
						<button class="layui-btn-danger layui-btn layui-btn-xs" onclick="member_del(this,${item.cno})"
						        href="javascript:;"><i class="layui-icon">&#xe640;</i>删除
						</button>
						<button class="layui-btn layui-btn-warm layui-btn-xs" onclick="x_admin_show('已购药品','/ClientOrder?cno=${item.cno}',580, 550)"><i
								class="layui-icon">&#xe63c;</i>订单
						</button>
					</td>
				</tr>
			</c:forEach>
		</tbody>
		<tbody id="search_table"></tbody>
	</table>
	<div class="page">
		<div>
			<c:if test="${rows != 0}">
				<a class="prev" href="/ClientList?currentPage=1">&lt;&lt;</a>
			</c:if>
			<c:forEach var="i" items="${currentPage-1},${currentPage},${currentPage+1}">
				<c:if test="${(i>0 && i<=pageCount)}">
					<c:if test="${i != currentPage}">
						<a class="num" href="/ClientList?currentPage=<c:out value="${i}"/>">${i}</a>
					</c:if>
					<c:if test="${i == currentPage}">
						<span class="current">${i}</span>
					</c:if>
				</c:if>
			</c:forEach>
			<c:if test="${rows != 0}">
				<a class="next" href="/ClientList?currentPage=${pageCount}">&gt;&gt;</a>
			</c:if>
		</div>
	</div>
</div>
<script>
    layui.use('form', function () {
        var form = layui.form;
        $ = layui.jquery;

        var normaltb = $('#normal_table');
        var searchtb = $('#search_table');
        var page = $('.page');

        form.on("submit(search)", function (data) {
	        if (data.field.cname.trim() == '') {
		        normaltb.show(100);
		        page.show(100);
		        searchtb.hide(100);
	        } else {
				normaltb.hide();
				page.hide();
				searchtb.show(100);
                $.ajax({
                    url: '/api/search/client',
                    type: 'GET',
                    data: data.field,
                    success: function (res) {
                        if (res.code == 0) {
                            // 找到该顾客
                            // 隐藏正常信息
                            searchtb.empty();
                            for (let i=0; i<res.data.length; i++) {
                                let item = res.data[i];
                                console.log(item);
                                let html = `
						    <tr>
								<td>
									<div class="layui-unselect layui-form-checkbox" lay-skin="primary" data-id=\${item.cno}><i class="layui-icon">&#xe605;</i>
									</div>
								</td>
								<td>\${item.cno}</td>
								<td>\${item.cname}</td>
								<td>\${item.csex}</td>
								<td>\${item.cage}</td>
								<td>\${item.caddress}</td>
								<td>\${item.cphone}</td>
								<td>\${item.cremark}</td>
								<!-- <td class="td-status">
								  <span class="layui-btn layui-btn-normal layui-btn-mini">已启用</span></td> -->
								<td class="td-manage">
									<button class="layui-btn layui-btn layui-btn-xs" onclick="x_admin_show('编辑','/ClientEdit?id=\${item.cno}',720,550)"><i
											class="layui-icon">&#xe642;</i>编辑
									</button>
									<button class="layui-btn-danger layui-btn layui-btn-xs" onclick="member_del(this,\${item.cno})"
									        href="javascript:;"><i class="layui-icon">&#xe640;</i>删除
									</button>
									<button class="layui-btn layui-btn-warm layui-btn-xs" onclick="x_admin_show('已购药品','/ClientOrder?cno=\${item.cno}',580, 550)"><i
											class="layui-icon">&#xe63c;</i>订单
									</button>
								</td>
							</tr>
						    `;
                                searchtb.append(html);
                            }
                        } else {
                            //无该顾客信息
	                        searchtb.empty();
	                        var html = '<tr><td colspan="9" style="text-align: center">没有该顾客</td></tr>'
	                        searchtb.append(html);
                        }
                    }
                });
	        }
			return false;
        });
    });

    /*用户-删除*/
    function member_del(obj, id) {
        layer.confirm('确认删除该顾客吗？', function (index) {
            var load = layer.load();
			$.ajax({
				url: '/api/client/delete',
				type: 'POST',
				data: {cno: id},
				success: function (res) {
				    layer.close(load);
					if (res.code == 0) {
					    $(obj).parents("tr").remove();
					    layer.msg("已删除", {icon:1, time: 1000});
					} else {
					    layer.alert(res.msg, {icon: 2});
					}
                }
			});
        });
    }

    function delAll(argument) {
        var data = tableCheck.getData();
        if (data.length  < 1) {
            layer.alert("未选中顾客, 请重试", {icon: 2});
        } else {
            layer.confirm('确认要删除吗？', function (index) {
                var load = layer.load();
                $.ajax({
	                url: '/api/client/deleteAll',
	                type: 'POST',
	                data: {cnos: data},
	                success: function (res) {
	                    layer.close(load);
		                if (res.code == 0) {
		                    // 正常删除顾客
                            $(".layui-form-checkbox").not(".header").parents("tr").remove();
			                layer.msg("删除成功", {icon: 1});
		                } else {
		                    // 删除失败
			                layer.alert(res.msg, {icon: 2});
		                }
                    }
                });
            });
        }
		return false;
    }
</script>
</body>

</html>