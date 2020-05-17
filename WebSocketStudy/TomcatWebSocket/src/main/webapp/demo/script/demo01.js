var websocket = null;
var Demo01 = function () {

    var ctx = $("#ctx").val();

    // 绑定事件
    var initEvent = function () {
        // 建立连接
        $("#connect").on("click", function () {
            if ($(this).hasClass("disabled")) {
                return false;
            }
            createWebSocket();
        });

        // 断开连接
        $("#disconnect").on("click", function () {
            if ($(this).hasClass("disabled")) {
                return false;
            }
            disconnect();
        });

        // 发送消息
        $("#sendMessage").on("click", function () {
            if ($(this).hasClass("disabled")) {
                return false;
            }
            var msg = prompt("填写消息内容");
            if (msg) {
                websocket.send(msg);
            }
        });

        $("#clearLog").on("click", function () {
            $("#logArea").text("");
        });
    }

    // 获取url前缀
    var getUrlPrefix = function () {
        if (window.location.protocol == 'http:') {
            return "ws://" + window.location.host;
        } else {
            return "wss://" + window.location.host;
        }
    }

    // 创建websocket连接
    var createWebSocket = function () {
        if (websocket == null) {
            websocket = new WebSocket(getUrlPrefix() + ctx + "/example01");
            websocket.onopen = function(event) {
                printLog("连接已创建");
                $("#connect").addClass("disabled");
                $("#disconnect, #sendMessage").removeClass("disabled");
            }
            websocket.onclose = function () {
                printLog("连接被关闭");
                $("#connect").removeClass("disabled");
                $("#disconnect, #sendMessage").addClass("disabled");
            }
            websocket.onerror = function () {
                printLog("连接出错");
                $("#disconnect, #sendMessage").addClass("disabled");
            }
            websocket.onmessage = function (event) {
                printLog("客户端收到消息：" + event.data);
            }
        } else {
            printLog("连接已创建");
        }
    }

    // 断开连接
    var disconnect = function () {
        if (websocket != null) {
            websocket.close();
            websocket = null;
        }
    }

    // 获取当前时间 2020-5-13 22:30:10
    var now = function () {
        var d = new Date();
        var year = d.getFullYear();
        var month = d.getMonth() + 1;
        var day = d.getDate();
        var hour = d.getHours();
        var minute = d.getMinutes();
        var second = d.getSeconds();
        return year + "-" + month + "-" + day + " " + hour + ":" + minute + ":" + second;
    }

    // 打印日志
    var printLog = function (message) {
        $("#logArea").append("[" + now() + "]" + message + "\n");
    }


    return {
        init: function () {
            initEvent();
        }
    };
}();

Demo01.init();
