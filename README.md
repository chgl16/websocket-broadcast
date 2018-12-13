# websocket-broadcast
	WebSocket通过一个socket实现全双工异步通讯，对比HTTP基于请求应答的半双工通讯。
	其在广播和点对点实时通讯方法更优越。
>直接使用WebSocket或者SockJS（WebSocket协议的模拟，兼容性要求高）会使开发车旭很繁琐。
>所以会直接使用它的子协议STOMP（Simple (or Streaming) Text Orientated Messaging Protocol）。
>STOMP协议使用一个基于帧（frame）的格式定义消息，与HTTP的request和response类似（具有类似@RequestMapping的@MessageMapping）
>注：学习自 **《JavaEE开发的颠覆者 Spring Boot实战》** 一书
<hr>

#### 效果
![在这里插入图片描述](https://img-blog.csdnimg.cn/20181213234238262.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L2NoZW5iZXR0ZXIxOTk2,size_16,color_FFFFFF,t_70)
>每个浏览器窗口输入localhost:8080即可。全站广播。
>简单原理：
> 1. 客户端连接服务器开放的socket(连接过程有个可选的订阅操作，订阅了就会收到广播，订阅函数是个异步回调函数)
> 2. 客户端发送要广播的数据给服务器，服务器@MessageMapping接收处理，然后返回给订阅的客户端。（这里的客户端同一份代码都是订阅的）
> 3. 客户端触发订阅的回调函数，处理广播数据。
<hr>

#### 项目结构
![在这里插入图片描述](https://img-blog.csdnimg.cn/20181213224605250.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L2NoZW5iZXR0ZXIxOTk2,size_16,color_FFFFFF,t_70)

<hr>

#### [=>博客地址](https://blog.csdn.net/chenbetter1996/article/details/84996772)