<%--
  Created by IntelliJ IDEA.
  User: yijea
  Date: 2018/9/3
  Time: 上午6:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" %>
<%
    String path = request.getContextPath();
    String bashPath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path;
%>
<html>
<head>
    <meta charset="utf-8">
    <title>web RTC 测试</title>
    <style>
        .booth {
            width: 400px;

            background: #ccc;
            border: 10px solid #ddd;
            margin: 0 auto;
        }
    </style>
</head>
<script type="text/javascript" src="<%=bashPath%>/js/jquery-3.3.1.min.js"></script>
<script type="text/javascript" src="<%=bashPath%>/js/qrjs/grid.js"></script>
<script type="text/javascript" src="<%=bashPath%>/js/qrjs/version.js"></script>
<script type="text/javascript" src="<%=bashPath%>/js/qrjs/detector.js"></script>
<script type="text/javascript" src="<%=bashPath%>/js/qrjs/formatinf.js"></script>
<script type="text/javascript" src="<%=bashPath%>/js/qrjs/errorlevel.js"></script>
<script type="text/javascript" src="<%=bashPath%>/js/qrjs/bitmat.js"></script>
<script type="text/javascript" src="<%=bashPath%>/js/qrjs/datablock.js"></script>
<script type="text/javascript" src="<%=bashPath%>/js/qrjs/bmparser.js"></script>
<script type="text/javascript" src="<%=bashPath%>/js/qrjs/datamask.js"></script>
<script type="text/javascript" src="<%=bashPath%>/js/qrjs/rsdecoder.js"></script>
<script type="text/javascript" src="<%=bashPath%>/js/qrjs/gf256poly.js"></script>
<script type="text/javascript" src="<%=bashPath%>/js/qrjs/gf256.js"></script>
<script type="text/javascript" src="<%=bashPath%>/js/qrjs/decoder.js"></script>
<script type="text/javascript" src="<%=bashPath%>/js/qrjs/qrcode.js"></script>
<script type="text/javascript" src="<%=bashPath%>/js/qrjs/findpat.js"></script>
<script type="text/javascript" src="<%=bashPath%>/js/qrjs/alignpat.js"></script>
<script type="text/javascript" src="<%=bashPath%>/js/qrjs/databr.js"></script>
<body>
<div class="booth">
    <video id="video" width="400" height="300"></video>
    <%--<button id='tack'> snap shot</button>--%>
    <%--<canvas id='canvas' width='400' height='300'></canvas>--%>
    <%--<img id='img' src=''>--%>
    <canvas id="qr-canvas" width="400" height="300" style="display: none"></canvas>
</div>


<script>
    var video = document.getElementById('video'),
        // canvas = document.getElementById('canvas'),
        qr_canvas = document.getElementById('qr-canvas'),
        qr_ctx = qr_canvas.getContext('2d'),
        // snap = document.getElementById('tack'),
        // img = document.getElementById('img'),
        vendorUrl = window.URL || window.webkitURL;

    qr_ctx.clearRect(0, 0, 400, 300);
    //回调函数
    qrcode.callback = function (e) {
        if (e.length !== 12 || isNaN(e) || parseFloat(e).toString() === "NaN") {
            alert("验证错误");
            captureToCanvas();
        } else {
            window.location.href = "<%=bashPath%>/sell.jsp?decode=" + e;
        }
    };

    //媒体对象
    navigator.getMedia = navigator.getUserMedia ||
        navagator.webkitGetUserMedia ||
        navigator.mozGetUserMedia ||
        navigator.msGetUserMedia;
    navigator.getMedia({
        video: true, //使用摄像头对象src
        audio: false  //不适用音频
    }, function (strem) {
        //调用成功
        console.log(strem);
        video.src = vendorUrl.createObjectURL(strem);
        video.play();
        setTimeout(captureToCanvas, 500);
    }, function (error) {
        //error.code
        console.log(error);
    });

    function captureToCanvas() {
        qr_ctx.drawImage(video, 0, 0, 400, 300);
        try {
            qrcode.decode();
            // alert(decode);
        } catch (e) {
            // console.log(e);
            setTimeout(captureToCanvas, 500);
        }
    }

    // snap.addEventListener('click', function () {
    //
    //     //绘制canvas图形
    //     canvas.getContext('2d').drawImage(video, 0, 0, 400, 300);
    //
    //     //把canvas图像转为img图片
    //     img.src = canvas.toDataURL("image/png");
    //
    // })
</script>
</body>
</html>
