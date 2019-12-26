

/*Android手机端设备访问*/
var uaTest = /Android|webOS|Windows Phone|iPhone|ucweb|ucbrowser|iPod|BlackBerry/i.test(navigator.userAgent.toLowerCase());
var touchTest = 'ontouchend' in document;
if (uaTest && touchTest) {
    window.location.href = 'http://m.dgsydl.com';   // 如果是Android手机端设备访问  回调装到这个页面http://m.dgsydl.com
}



// 加入收藏 兼容360和IE6
function shoucang(sTitle, sURL) {
    try {
        window.external.addFavorite(sURL, sTitle);
    }
    catch (e) {
        try {
            window.sidebar.addPanel(sTitle, sURL, "");
        }
        catch (e) {
            alert("加入收藏失败，请使用Ctrl+D进行添加");
        }
    }
}