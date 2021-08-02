# Nursing-Home
养老院管理平台

前端+后端+sql 目前没有拦截器的安全验证，属于入门使用

vue+springboot+mybatis
Nursing-home-management-platform
这是一个用于养老院管理的平台，采用的是mysql数据库
employ 是项目后端
vue-employ是项目前端
这个主要是住宿管理，的一些基本操作增删改查 
用于各位拓展练习


使用步骤：
1.mysql 运行数据库文件 建立数据库
2.前端 导入工程后
          1：npm install （自行安装好nodejs）
          2：npm run dev 运行项目
          3：浏览器lochalhost：8080 访问
3.后端 导入工程后
          1：maven加载需要的配置
          2：配置文件中修改数据库地址和账号密码
          3：运行最外层入口Application
错误：
注意如果出现前端无法访问后端的数据
  尝试将后端的loaclhost改为对应的访问地址，后端数据传输端口为8081 自行修改
