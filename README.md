# 校园二手管理平台（移动端）

技术栈：vue+axios+vant+springboot+mybatis-plus+mysql+sftp

环境：nginx+springboot镜像+docker

前端：1.router路由分配，组件间通过$route传值

​			2.二次封装axios

​			3.使用vant ui库和fastclick等插件解决移动端适配

后端：1.通过CorsConfig配置bean解决跨域问题

​			2.通过配置Handler组件在操作数据时模拟mysql触发器

​			3.按照springboot开发流程开发controller，service，DAO，mapper层

​			4.封装sftp上传类，负责用户上传图片，并对图片上传的大小进行限制

​			5.编写合理的优化sql和逻辑，实现了高性能模糊查询，处理sql注入等

部署：1.配置linux，使用docker容器

​			2.nginx作为前端服务器，nginx作为文件服务器，springboot镜像作为java服务端，mysql容器作为数据库

项目地址：http://47.106.211.118:1234

