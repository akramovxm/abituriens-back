package uz.akramovxm.abituriensback.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.akramovxm.abituriensback.dto.view.TopicDTO;
import uz.akramovxm.abituriensback.mapper.TopicMapper;
import uz.akramovxm.abituriensback.repository.TopicRepository;
import uz.akramovxm.abituriensback.service.TopicService;

import java.util.List;

@Service
public class TopicServiceImpl implements TopicService {
    @Autowired
    private TopicRepository topicRepository;
    @Autowired
    private TopicMapper topicMapper;

    @Override
    public List<TopicDTO> getAllTopics() {
        return topicRepository.findAllByParentIsNull().stream().map(topicMapper::toDTO).toList();
    }
}
