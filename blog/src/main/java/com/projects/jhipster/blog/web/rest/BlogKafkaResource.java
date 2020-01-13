package com.projects.jhipster.blog.web.rest;

import com.projects.jhipster.blog.service.BlogKafkaProducer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/blog-kafka")
public class BlogKafkaResource {

    private final Logger log = LoggerFactory.getLogger(BlogKafkaResource.class);

    private BlogKafkaProducer kafkaProducer;

    public BlogKafkaResource(BlogKafkaProducer kafkaProducer) {
        this.kafkaProducer = kafkaProducer;
    }

    @PostMapping(value = "/publish")
    public void sendMessageToKafkaTopic(@RequestParam("message") String message) {
        log.debug("REST request to send to Kafka topic the message : {}", message);
        this.kafkaProducer.sendMessage(message);
    }
}
