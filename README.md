# spiderdemo
 整合了mybatis、token、swagger、slf4j、springmvc的一个网站雏形

#接口文档
主要针对vue的axios，后端设置了跨域访问，前端不需要再多设置。接口文档采用了swagger进行自动生成，可以通过访问http://47.100.26.92:8888/swagger-ui.html进行查看。

#参数传递
接口访问统一采用post方法，接口参数统一为@RequestBody形式，有些接口没有参数。当用axios进行参数传递时，使用data：request形式，request为对应接口参数结构。

#登录验证
采用token验证方式，从登录接口登录后token码将保存在返回值的data中，前端需取出token码，在之后的每次其它访问时在headers里带上token。当然，并不是所有接口都是需要登录才能访问的。

#云端服务器
地址：47.100.26.92 账号：root 密码：Wasd123456 后端占用8888 前端备用8080（只开放了这两个端口）



