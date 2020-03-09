package com.zl.springcloud.lib;

import org.springframework.cloud.client.ServiceInstance;

import java.util.List;

public interface LoadBalancer {
    //获取存活的服务器实例列表
    ServiceInstance instances(List<ServiceInstance> serviceInstances);
}
