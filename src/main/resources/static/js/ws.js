var stompClient = null;

/**
 * 渲染显示和按钮状态
 *
 * @param status
 */
function setConnected(status) {
    $("#connect")[0].disabled = status;
    $("#disconnect")[0].disabled = !status;
    $('#conversation-div')[0].style.visibility = status ? 'visible' : 'hidden';
    $('#response')[0].innerHTML = "";
}

/**
 * 连接
 */
function connect() {
    // 连接SockJS的endpoint名称为"/endpointWisely"
    var socket = new SockJS('/endpointWisely');
    // 使用STOMP子协议的WebSocket客户端
    stompClient = Stomp.over(socket);
    // 连接WebSocket服务端
    stompClient.connect({}, function (frame) {
        setConnected(true);
        console.log('Connected: ' + frame);
        // ##回调函数## 订阅"/topic/getResponse"目标发送的消息，来显示
        stompClient.subscribe('/topic/getResponse', function (response) {
            console.log("response: " + JSON.stringify(response));
            // 发回数据形式为{"command":"MESSAGE","headers":{"content-length":"36","message-id":"3khnmfpx-0","subscription":"sub-0","content-type":"application/json;charset=UTF-8","destination":"/topic/getResponse"},"body":"{\"responseMessage\":\"Welcome, cccc!\"}"}
            showResponse(JSON.parse(response.body).responseMessage);
        });

    });
}

/**
 * 断开连接
 */
function disconnect() {
    if (stompClient != null) {
        stompClient.disconnect();
    }
    setConnected(false);
    console.log("断开连接");
}

/**
 * 发送广播信息
 */
function sendMessage() {
    var message = $('#message').val();
    // 向目标发送消息
    stompClient.send("/broadcast", {}, JSON.stringify({'message': message}));
}

/**
 * 显示响应信息
 *
 * @param message
 */
function showResponse(message) {
    $('#response')[0].innerHTML += message + '<br>';
    /*
     10秒后清屏，
     参数code不能仅仅是一句 "$('#response')[0].innerHTML = "";"
     并发量高的时候，会有bug,可能不是第一个10秒结束
      */
    // setTimeout(function(){$('#response')[0].innerHTML = "";}, 10000);
}
