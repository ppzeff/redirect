package com.dehssisfs.redirect;

import com.dehssisfs.redirect.model.Model;
import com.dehssisfs.redirect.repo.ModelRepo;
import com.dehssisfs.redirect.service.IpService;
import com.dehssisfs.redirect.service.MainService;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

import java.io.IOException;
import java.util.Date;
import java.util.List;

@SpringBootApplication
@EnableScheduling
public class RedirectApplication {
    @Autowired
    ModelRepo modelRepo;

    @Autowired
    MainService mainService;

    public static void main(String[] args) {
        SpringApplication.run(RedirectApplication.class, args);
    }

    @Bean
    public TaskScheduler taskScheduler() {
        final ThreadPoolTaskScheduler scheduler = new ThreadPoolTaskScheduler();
        scheduler.setPoolSize(10);
        return scheduler;
    }

    @Scheduled(fixedDelay = 10 * 1000L, initialDelay = 0)
    public void scheduled1() throws IOException {
        List<Integer> listAllCode = modelRepo.findAllCode(); // new ArrayList<>();

        List<Model> modelList = mainService.getRateByCode(listAllCode);

        for (Model elModel : modelList) {
            modelRepo.save(elModel);
            System.out.println(
                    new Date(elModel.getLastUpdate())+ " SAVE " +
                            elModel.toString());
        }
    }

    @Scheduled(fixedDelay = 60 * 1000L, initialDelay = 3 * 1000L)
    public void scheduled2() throws IOException {
        System.out.println(IpService.getIpAddress());

        HttpGet get = new HttpGet("https://dehssisfs.herokuapp.com/getip");
//            HttpGet get = new HttpGet("http://localhost:8081/ip/" + IpService.getIpAddress()+"/");
        HttpClient client = new DefaultHttpClient();
        HttpResponse response = client.execute(get);
        System.out.println(response.getStatusLine());
    }

}

