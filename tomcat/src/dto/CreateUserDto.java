package dto;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class CreateUserDto {
    String first_name;
    String last_name;
    Long salary;
    Long company_id;
}
