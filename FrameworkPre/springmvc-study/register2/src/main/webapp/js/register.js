$(function() {
    var username = false;
    var password = false;
    var repassword = false;
    // 用户名不为空
    $("#username").blur(function () {
        if ($(this).val() == "") {
            $(this).next().css("color", "red").html("X");
            username = false;
        } else {
            $(this).next().css("color", "green").html("√");
            username = true;
        }
    });

    // 密码必须是6-12位，包含字母数字下划线
    $("#password").blur(function () {
        // 在js中要求正则两侧//
        if (!$(this).val().match(/^\w{6,12}$/)) {
            $(this).next().css("color", "red").html("X");
            password = false;
        } else {
            $(this).next().css("color", "green").html("√");
            password = true;
        }
    });
    // 密码必须和确认密码一样
    $("#repassword").blur(function () {
        if ($(this).val()==""||$(this).val()!=$("#password").val()) {
            $(this).next().css("color", "red").html("X");
            repassword = false;
        } else {
            $(this).next().css("color", "green").html("√");
            repassword = true;
        }
    });

    $("#submitBtn").click(function () {
        if (username == false || password == false || repassword == false || $("#imageFile").val() == "") {
            alert("请填写完整信息");
            return false;
        }
    });
});