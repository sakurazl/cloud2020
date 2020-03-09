package com.zl.springcloud.lib;

import org.springframework.cloud.client.ServiceInstance;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 手写轮询算法
 */
@Component
public class MyLB implements LoadBalancer{

    //定义初始值，原子性
    private AtomicInteger atomicInteger=new AtomicInteger(0);
    //获取下一个自增id
    public final int getAndIncrement(){
        int current;
        int next;
        do {
            current=this.atomicInteger.get();
            //超过最大值，为0，重新计数
            next=current>=Integer.MAX_VALUE?0:current+1;
        }while (!atomicInteger.compareAndSet(current,next));//自旋锁
        System.out.println("第几次访问："+next);
        return next;

    }

    /**
     * 负载均衡算法:rest接口第几次请求数%服务器集群总数量=实际调用服务器位置下标,每次服务重启动后rest接口计数从1开始.
     *
     * @param serviceInstances
     * @return
     */
    @Override
    public ServiceInstance instances(List<ServiceInstance> serviceInstances) {
        int index = getAndIncrement() % serviceInstances.size();
        return serviceInstances.get(index);
    }
}
