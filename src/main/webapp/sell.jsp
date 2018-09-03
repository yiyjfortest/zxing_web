<%@ page import="java.math.BigInteger" %><%--
  Created by IntelliJ IDEA.
  User: yijea
  Date: 2018/9/3
  Time: 下午3:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%
    String path = request.getContextPath();
    String bashPath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path;
    String decode = request.getParameter("decode");
    BigInteger bigInteger = new BigInteger(decode, 2);
    String price = bigInteger.toString(10);
%>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Sell</title>

    <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
    <%--<link href="../../assets/css/ie10-viewport-bug-workaround.css" rel="stylesheet">--%>

    <link href="<%=bashPath%>/js/bootstrap-3.3.7/css/bootstrap.css" rel="stylesheet">
    <link href="<%=bashPath%>/css/cover.css" rel="stylesheet">
    <script type="text/javascript" src="<%=bashPath%>/js/jquery-3.3.1.min.js"></script>
    <script type="text/javascript" src="<%=bashPath%>/js/bootstrap-3.3.7/js/bootstrap.js"></script>

    <!-- Just for debugging purposes. Don't actually copy these 2 lines! -->
    <!--[if lt IE 9]-->
    <%--<script src="../../assets/js/ie8-responsive-file-warning.js"></script><!--[endif]-->--%>
    <%--<script src="../../assets/js/ie-emulation-modes-warning.js"></script>--%>

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9] -->
    <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <!--[endif]-->
</head>

<body>

<div class="site-wrapper">

    <div class="site-wrapper-inner">

        <div class="cover-container">

            <%--<div class="masthead clearfix">--%>
            <%--<div class="inner">--%>
            <%--<h3 class="masthead-brand">Cover</h3>--%>
            <%--<nav>--%>
            <%--<ul class="nav masthead-nav">--%>
            <%--<li class="active"><a href="#">Home</a></li>--%>
            <%--<li><a href="#">Features</a></li>--%>
            <%--<li><a href="#">Contact</a></li>--%>
            <%--</ul>--%>
            <%--</nav>--%>
            <%--</div>--%>
            <%--</div>--%>
            <div class="inner cover">
                <h1 class="cover-heading">时尚前线</h1>
                <p class="lead">衣物价格</p>
                <p class="lead"><%=price%>元</p>
                <p class="lead">
                    <a href="#" class="btn btn-lg btn-default" onclick="sell('out')">售出</a>
                    <a href="#" class="btn btn-lg btn-default" onclick="sell('in')">退回</a>
                </p>
            </div>

            <div class="mastfoot">
                <div class="inner">
                    <p>西大街</p>
                </div>
            </div>

        </div>

    </div>

</div>

<!-- Bootstrap core JavaScript
================================================== -->
<!-- Placed at the end of the document so the pages load faster -->
<%--<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>--%>
<%--<script>window.jQuery || document.write('<script src="../../assets/js/vendor/jquery.min.js"><\/script>')</script>--%>
<%--<script src="../../dist/js/bootstrap.min.js"></script>--%>
<%--<!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->--%>
<%--<script src="../../assets/js/ie10-viewport-bug-workaround.js"></script>--%>
<script>
    function sell(param) {
        var sell_url;
        var info;
        if (param === 'out') {
            sell_url = '<%=bashPath%>/sellOut.do';
            info = '出售';
        } else {
            sell_url = '<%=bashPath%>/sellIn.do';
            info = '退回';
        }
        $.ajax({
            url: sell_url,
            type: 'POST',
            data: {
                price:'<%=price%>'
            },
            timeout: 30000,
            dateType: 'json',
            success:function (data, status) {
                alert(info + "成功");
            },
            error:function (status) {
                console.log(status);
                alert(info + "失败");
            }
        })
    }
</script>
</body>
</html>
