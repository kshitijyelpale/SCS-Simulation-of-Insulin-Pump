package com.projects.jhipster.blog.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class BlogKafkaConsumer {

    private final Logger log = LoggerFactory.getLogger(BlogKafkaConsumer.class);
    private static final String TOPIC = "topic_blog";

    @KafkaListener(topics = "topic_blog", groupId = "group_id")
    public void consume(String message) throws IOException {
        log.info("Consumed message in {} : {}", TOPIC, message);
    }
}
