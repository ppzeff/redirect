package com.dehssisfs.redirect;

import com.dehssisfs.redirect.service.IpService;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

import java.io.IOException;

@SpringBootApplication
@EnableScheduling
public class RedirectApplication {

    public static void main(String[] args) {
        SpringApplication.run(RedirectApplication.class, args);
    }

    @Bean
    public TaskScheduler taskScheduler() {
        final ThreadPoolTaskScheduler scheduler = new ThreadPoolTaskScheduler();
        scheduler.setPoolSize(10);
        return scheduler;
    }


    @Scheduled(fixedDelay = 60 * 1000L, initialDelay = 3 * 1000L)
    public void scheduled1() throws IOException {
        System.out.println(IpService.getIpAddress());

        HttpGet get = new HttpGet("https://dehssisfs.herokuapp.com/getip" + "/");
//            HttpGet get = new HttpGet("http://localhost:8081/ip/" + IpService.getIpAddress()+"/");
        HttpClient client = new DefaultHttpClient();
        HttpResponse response = client.execute(get);
        System.out.println(response.getStatusLine());
    }

}

