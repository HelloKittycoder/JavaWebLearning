var App = function () {

    // 加载菜单
    var loadMenuContent = function () {
        $("#loadMenu").click(function () {
            var basePath = $("#basePath").val();
            $.ajax({
                url:basePath+"show",
                type:"post",
                success:function (responseText) {
                    // 递归嵌套菜单数组
                    // console.log(responseText);
                    var html = assembleMenuHtml(responseText);
                    $("#content").html(html);
                }
            });
        });
    }

    // 组装菜单
    var assembleMenuHtml = function (menuArr) {
        var html = "";
        var size = menuArr.length;
        if (size > 0) {
            html += "<ul>";
            for (var i = 0; i < size; i++) {
                html += "<li>" + menuArr[i].name + assembleMenuHtml(menuArr[i].children) +"</li>";
            }
            html += "</ul>";
        }
        return html;
    }

    return {
        loadMenuContent : function () {
            return loadMenuContent();
        }
    }
}();