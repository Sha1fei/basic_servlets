package dto;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class DeleteUserDto {
    Integer id;
}
