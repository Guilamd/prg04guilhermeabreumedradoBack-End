package fintech.backend.postexterno.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// DTO para representar um Post da API externa JSONPlaceholder.
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostExternoDTO {

    private Integer userId;

    private Integer id;

    private String title;

    private String body;
}
