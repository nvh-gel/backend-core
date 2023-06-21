package com.eden.backendcore.service.impl;

import com.eden.backendcore.mapper.ProjectMapper;
import com.eden.backendcore.model.Project;
import com.eden.backendcore.repository.ProjectRepository;
import com.eden.backendcore.service.ProjectService;
import com.eden.backendcore.viewmodel.ProjectVM;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProjectServiceImpl implements ProjectService {

    private final ProjectMapper projectMapper = Mappers.getMapper(ProjectMapper.class);
    private final ProjectRepository projectRepository;

    @Override
    @Transactional
    public ProjectVM create(ProjectVM request) {
        Project project = projectMapper.toModel(request);
        project.setCreatedAt(LocalDateTime.now());
        project.setUpdatedAt(LocalDateTime.now());
        return projectMapper.toViewModel(projectRepository.save(project));
    }

    @Override
    public String createOnQueue(ProjectVM request) {
        UUID uuid = UUID.randomUUID();
        request.setUuid(uuid);
        this.create(request);
        return uuid.toString();
    }

    @Override
    public Iterable<ProjectVM> findAll(int page, int size) {
        Pageable pageable = PageRequest.of(page > 0 ? page - 1 : 0, size, Sort.Direction.ASC, "id");
        return projectMapper.toViewModel(projectRepository.findAll(pageable));
    }

    @Override
    public ProjectVM findById(Long id) {
        return projectMapper.toViewModel(projectRepository.findById(id).orElse(null));
    }

    @Override
    @Transactional
    public ProjectVM update(ProjectVM request) {
        Project existing = projectRepository.findById(request.getId()).orElse(null);
        if (existing == null) {
            return null;
        }
        Project toUpdate = projectMapper.toModel(request);
        projectMapper.mapUpdate(existing, toUpdate);
        existing.setUpdatedAt(LocalDateTime.now());
        Project updated = projectRepository.save(existing);
        return projectMapper.toViewModel(updated);
    }

    @Override
    public String updateOnQueue(ProjectVM request) {
        UUID uuid = UUID.randomUUID();
        this.update(request);
        return uuid.toString();
    }

    @Override
    @Transactional
    public ProjectVM delete(Long id) {
        Project existing = projectRepository.findById(id).orElse(null);
        if (existing == null) {
            return null;
        }
        projectRepository.deleteById(id);
        existing.setUpdatedAt(LocalDateTime.now());
        return projectMapper.toViewModel(existing);
    }

    @Override
    public String deleteOnQueue(Long id) {
        UUID uuid = UUID.randomUUID();
        this.delete(id);
        return uuid.toString();
    }
}
