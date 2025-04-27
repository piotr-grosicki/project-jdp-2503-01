package com.kodilla.ecommercee.service;

import com.kodilla.ecommercee.controller.GroupNotFoundException;
import com.kodilla.ecommercee.domain.Group;
import com.kodilla.ecommercee.domain.GroupDto;
import com.kodilla.ecommercee.repository.GroupRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GroupService {

    private final GroupRepository groupRepository;

    public List<Group> getAllGroups() {
        return groupRepository.findAll();
    }

    public Group findGroupById(final Long id) throws GroupNotFoundException {
        return groupRepository.findById(id)
                .orElseThrow(GroupNotFoundException::new);
    }

    public Group saveGroup(final Group group) {
        return groupRepository.save(group);
    }

    public Group updateGroup(final Long id, final GroupDto groupDto) throws GroupNotFoundException {
        Group group = groupRepository.findById(id)
                .orElseThrow(GroupNotFoundException::new);

        group.setName(groupDto.getName());
        group.setDescription(groupDto.getDescription());

        return groupRepository.save(group);
    }

    public void deleteGroup(final Long id) throws GroupNotFoundException {
        if (!groupRepository.existsById(id)) {
            throw new GroupNotFoundException();
        }
        groupRepository.deleteById(id);
    }
}
