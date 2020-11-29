$(function () {
    $.get("/WebShop/top.html", function(data) {
        $("#top").html(data);
    })
    $.get("/WebShop/bottom.html", function(data) {
        $("#bottom").html(data);
    })
    $.get("/WebShop/admintop.html", function(data) {
        $("#admintop").html(data);
    })
})