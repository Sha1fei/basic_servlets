package dto;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class ReadUserByIdDto {
    Long id;
}
