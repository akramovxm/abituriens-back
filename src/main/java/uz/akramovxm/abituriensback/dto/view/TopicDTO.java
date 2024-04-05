package uz.akramovxm.abituriensback.dto.view;

import lombok.Builder;
import lombok.Data;

import java.util.Set;

@Data
@Builder
public class TopicDTO {
    private String title;
    private String path;
    private Set<TopicDTO> children;
}
