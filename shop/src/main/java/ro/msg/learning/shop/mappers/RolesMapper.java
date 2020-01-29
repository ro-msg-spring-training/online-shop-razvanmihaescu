package ro.msg.learning.shop.mappers;

import org.springframework.stereotype.Component;
import ro.msg.learning.shop.dtos.RolesDto;
import ro.msg.learning.shop.entities.Roles;

@Component
public class RolesMapper {
    public RolesDto convertToDto(Roles roles) {
        return RolesDto.builder()
                .name(roles.getName())
                .build();
    }

    public Roles convertToEntity(RolesDto rolesDto) {
        return Roles.builder()
                .name(rolesDto.getName())
                .build();
    }
}
