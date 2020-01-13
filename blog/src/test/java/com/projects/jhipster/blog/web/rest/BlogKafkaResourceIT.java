package com.projects.jhipster.blog.web.rest;

import com.projects.jhipster.blog.BlogApp;
import com.projects.jhipster.blog.service.BlogKafkaProducer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@EmbeddedKafka
@SpringBootTest(classes = BlogApp.class)
public class BlogKafkaResourceIT {

    @Autowired
    private BlogKafkaProducer kafkaProducer;

    private MockMvc restMockMvc;

    @BeforeEach
    public void setup() {
        BlogKafkaResource kafkaResource = new BlogKafkaResource(kafkaProducer);

        this.restMockMvc = MockMvcBuilders.standaloneSetup(kafkaResource)
            .build();
    }

    @Test
    public void sendMessageToKafkaTopic() throws Exception {
        restMockMvc.perform(post("/api/blog-kafka/publish?message=yolo"))
            .andExpect(status().isOk());
    }
}
