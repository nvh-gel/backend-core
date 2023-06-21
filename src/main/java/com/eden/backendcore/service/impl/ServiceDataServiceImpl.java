package com.eden.backendcore.service.impl;

import com.eden.backendcore.mapper.ServiceMapper;
import com.eden.backendcore.repository.ServiceRepository;
import com.eden.backendcore.service.ServiceDataService;
import com.eden.backendcore.viewmodel.ServiceVM;
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
public class ServiceDataServiceImpl implements ServiceDataService {

    private final ServiceMapper serviceMapper = Mappers.getMapper(ServiceMapper.class);

    private final ServiceRepository serviceRepository;

    @Override
    @Transactional
    public ServiceVM create(ServiceVM request) {
        com.eden.backendcore.model.Service service = serviceMapper.toModel(request);
        service.setCreatedAt(LocalDateTime.now());
        service.setUpdatedAt(LocalDateTime.now());
        return serviceMapper.toViewModel(serviceRepository.save(service));
    }

    @Override
    public String createOnQueue(ServiceVM request) {
        UUID uuid = UUID.randomUUID();
        request.setUuid(uuid);
        this.create(request);
        return uuid.toString();
    }

    @Override
    public Iterable<ServiceVM> findAll(int page, int size) {
        Pageable pageable = PageRequest.of(page > 0 ? page - 1 : page, size, Sort.Direction.ASC, "id");
        return serviceMapper.toViewModel(serviceRepository.findAll(pageable));
    }

    @Override
    public ServiceVM findById(Long id) {
        return serviceMapper.toViewModel(serviceRepository.findById(id).orElse(null));
    }

    @Override
    @Transactional
    public ServiceVM update(ServiceVM request) {
        com.eden.backendcore.model.Service existing = this.serviceRepository.findById(request.getId()).orElse(null);
        if (existing == null) {
            return null;
        }
        com.eden.backendcore.model.Service toUpdate = serviceMapper.toModel(request);
        serviceMapper.mapUpdate(existing, toUpdate);
        existing.setUpdatedAt(LocalDateTime.now());
        serviceRepository.save(existing);
        return serviceMapper.toViewModel(existing);
    }

    @Override
    public String updateOnQueue(ServiceVM request) {
        UUID uuid = UUID.randomUUID();
        this.update(request);
        return uuid.toString();
    }

    @Override
    @Transactional
    public ServiceVM delete(Long id) {
        com.eden.backendcore.model.Service existing = serviceRepository.findById(id).orElse(null);
        if (existing == null) {
            return null;
        }
        serviceRepository.deleteById(id);
        existing.setUpdatedAt(LocalDateTime.now());
        return serviceMapper.toViewModel(existing);
    }

    @Override
    public String deleteOnQueue(Long id) {
        UUID uuid = UUID.randomUUID();
        this.delete(id);
        return uuid.toString();
    }
}
