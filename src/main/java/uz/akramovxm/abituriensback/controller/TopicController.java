package uz.akramovxm.abituriensback.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.akramovxm.abituriensback.dto.response.Response;
import uz.akramovxm.abituriensback.dto.view.TopicDTO;
import uz.akramovxm.abituriensback.service.TopicService;

import java.util.List;

@RestController
@RequestMapping("/topics")
public class TopicController {
    @Autowired
    private TopicService topicService;

    @GetMapping
    public Response getAll() {
        List<TopicDTO> topics = topicService.getAllTopics();

        return new Response(HttpStatus.OK.name(), topics);
    }
}
