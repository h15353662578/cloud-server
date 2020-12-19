package com.hs.cloudconsumer;

import com.alibaba.dubbo.config.annotation.Reference;
import com.hs.cloudapi.service.EmailCodeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;


@Slf4j
@EnableDiscoveryClient
@SpringBootApplication
public class CloudConsumerApplication {

    public static void main(String[] args) {
        SpringApplication.run(CloudConsumerApplication.class, args);
    }
    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }

    @RestController
    @RefreshScope
    public class ConsumerController{

        @Reference
        EmailCodeService emailCodeService;

        @Autowired
        private LoadBalancerClient client;

        @Autowired
        private RestTemplate restTemplate;

        @Value("${app.msg}")
        private String msg;

        @GetMapping("/consumer")
        public String consumer(){
            log.info("------消费开始------");
            //获取服务
            ServiceInstance instance = client.choose("cloud-provider");
            //调用服务
            String result = restTemplate.getForObject(instance.getUri() + "/provider", String.class);
            log.info("------消费结束------");
            return result;
        }
    }
}
