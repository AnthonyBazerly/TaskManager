package com.backend.services;

import com.backend.models.Messages;
import com.backend.dtos.MessagesDto;
import com.backend.repos.ChatsRepo;
import com.backend.repos.EmployeesRepo;
import com.backend.repos.MessagesRepo;
import com.backend.mappers.MessagesMapper;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MessagesService {
    private final MessagesRepo messagesRepo;
    private final ChatsRepo chatRepo;
    private final EmployeesRepo employeesRepo;
    private final MessagesMapper messagesMapper;

    public MessagesService(MessagesRepo messagesRepo, ChatsRepo chatRepo, EmployeesRepo employeesRepo,
            MessagesMapper messagesMapper) {
        this.messagesRepo = messagesRepo;
        this.chatRepo = chatRepo;
        this.employeesRepo = employeesRepo;
        this.messagesMapper = messagesMapper;
    }

    public List<MessagesDto> getAllMessages() {
        return messagesRepo.findAll().stream()
                .map(messagesMapper::toDto)
                .collect(Collectors.toList());
    }

    public MessagesDto getJobTypeById(Long id) {
        return messagesRepo.findById(id)
                .map(messagesMapper::toDto)
                .orElse(null);
    }

    public MessagesDto createJobType(MessagesDto dto) {
        Messages jobtype = messagesMapper.toEntity(dto, chatRepo, employeesRepo, messagesRepo);
        Messages saved = messagesRepo.save(jobtype);
        return messagesMapper.toDto(saved);
    }

    public MessagesDto updateJobType(Long id, MessagesDto jobDto) {
        return messagesRepo.findById(id)
                .map(existing -> {
                    existing = messagesMapper.toEntity(jobDto, chatRepo, employeesRepo, messagesRepo);
                    Messages updated = messagesRepo.save(existing);
                    return messagesMapper.toDto(updated);
                })
                .orElse(null);
    }

    public void deleteJobType(Long id) {
        messagesRepo.deleteById(id);
    }
}