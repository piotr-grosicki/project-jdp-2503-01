package com.kodilla.ecommercee.mapper;

import com.kodilla.ecommercee.domain.Group;
import com.kodilla.ecommercee.dto.GroupDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class GroupMapper {

    public Group mapToGroup(final GroupDto groupDto) {
        return new Group(
                groupDto.getGroupId(),
                groupDto.getName(),
                groupDto.getDescription(),
                List.of()
        );
    }

    public GroupDto mapToGroupDto(final Group group) {
        return new GroupDto(
                group.getGroupId(),
                group.getName(),
                group.getDescription()
        );
    }

    public List<GroupDto> mapToGroupDtoList(final List<Group> groups) {
        return groups.stream()
                .map(this::mapToGroupDto)
                .collect(Collectors.toList());
    }
}
