package uz.akramovxm.abituriensback.mapper;

import org.springframework.stereotype.Component;
import uz.akramovxm.abituriensback.dto.view.TopicDTO;
import uz.akramovxm.abituriensback.entity.Topic;

import java.util.stream.Collectors;

@Component
public class TopicMapper {
    public TopicDTO toDTO(Topic topic) {
        return TopicDTO.builder()
                .title(topic.getTitle())
                .path(topic.getPath())
                .children(topic.getChildren().stream().map(this::toDTO).collect(Collectors.toSet()))
                .build();
    }
}
