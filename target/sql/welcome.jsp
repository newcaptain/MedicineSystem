<%@page pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>医药信息管理系统</title>
        <meta name="renderer" content="webkit">
        <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
        <meta name="viewport" content="width=device-width,user-scalable=yes, minimum-scale=0.4, initial-scale=0.8,target-densitydpi=low-dpi" />
        <link rel="shortcut icon" href="/favicon.ico" type="image/x-icon" />
        <link rel="stylesheet" href="./css/font.css">
        <link rel="stylesheet" href="./css/xadmin.css">
    </head>
    <body>
    <div class="x-body layui-anim layui-anim-up">
        <blockquote class="layui-elem-quote">欢迎管理员!</blockquote>
        <fieldset class="layui-elem-field">
            <legend>数据统计</legend>
            <div class="layui-field-box">
                <div class="layui-col-md12">
                    <div class="layui-card">
                        <div class="layui-card-body">
                            <div class="layui-carousel x-admin-carousel x-admin-backlog" lay-anim="" lay-indicator="inside" lay-arrow="none" style="width: 100%; height: 90px;">
                                <div carousel-item="">
                                    <ul class="layui-row layui-col-space10 layui-this">
                                        <li class="layui-col-xs3">
                                            <a href="javascript:;" class="x-admin-backlog-body">
                                                <h3>顾客数</h3>
                                                <p>
                                                    <cite>${cnum}</cite></p>
                                            </a>
                                        </li>
                                        <li class="layui-col-xs3">
                                            <a href="javascript:;" class="x-admin-backlog-body">
                                                <h3>销售人员数</h3>
                                                <p>
                                                    <cite>${anum}</cite></p>
                                            </a>
                                        </li>
                                        <li class="layui-col-xs3">
                                            <a href="javascript:;" class="x-admin-backlog-body">
                                                <h3>药品数</h3>
                                                <p>
                                                    <cite>${mnum}</cite></p>
                                            </a>
                                        </li>
                                        <li class="layui-col-xs3">
                                            <a href="javascript:;" class="x-admin-backlog-body">
                                                <h3>订单数</h3>
                                                <p>
                                                    <cite>${onum}</cite></p>
                                            </a>
                                        </li>
                                    </ul>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </fieldset>
        <fieldset class="layui-elem-field">
            <legend>系统信息</legend>
            <div class="layui-field-box">
                <table class="layui-table">
                    <tbody>
                        <tr>
                            <th>服务器地址</th>
                            <td>10.86.2.36</td></tr>
                        <tr>
                            <th>操作系统</th>
                            <td>Windows 10 x64</td></tr>
                        <tr>
                            <th>运行环境</th>
                            <td>jdk 11</td></tr>
                        <tr>
                            <th>Tomcat版本</th>
                            <td>9.0.12</td></tr>
                        <tr>
                            <th>MYSQL版本</th>
                            <td>8.0.13</td></tr>
                    </tbody>
                </table>
            </div>
        </fieldset>
    </div>
    </body>
</html>