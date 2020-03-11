# 手动通知  githup上修改了配置文件，config server能实时改变，config client 需要发送下面命令才能刷新配置
curl -X POST "http://localhost:3355/actuator/refresh"

