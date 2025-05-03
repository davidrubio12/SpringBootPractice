package es.iesclaradelrey.da2d1e2425.shopmartadavidrubio.dto.api;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TaskDto {
    private Long taskId;
    private String title;
    private String description;
    private String status;
    private String categoryName;
}