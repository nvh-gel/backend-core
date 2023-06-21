package com.eden.backendcore.service.impl;

import com.eden.backendcore.mapper.NewsMapper;
import com.eden.backendcore.model.News;
import com.eden.backendcore.repository.NewsRepository;
import com.eden.backendcore.service.NewsService;
import com.eden.backendcore.viewmodel.NewsVM;
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
public class NewsServiceImpl implements NewsService {

    private final NewsMapper newsMapper = Mappers.getMapper(NewsMapper.class);
    private final NewsRepository newsRepository;

    @Override
    @Transactional
    public NewsVM create(NewsVM request) {
        News news = newsMapper.toModel(request);
        news.setCreatedAt(LocalDateTime.now());
        news.setUpdatedAt(LocalDateTime.now());
        return newsMapper.toViewModel(newsRepository.save(news));
    }

    @Override
    public String createOnQueue(NewsVM request) {
        UUID uuid = UUID.randomUUID();
        request.setUuid(uuid);
        this.create(request);
        return uuid.toString();
    }

    @Override
    public Iterable<NewsVM> findAll(int page, int size) {
        Pageable pageable = PageRequest.of(page > 0 ? page - 1 : 0, size, Sort.Direction.DESC, "id");
        return newsMapper.toViewModel(newsRepository.findAll(pageable));
    }

    @Override
    public NewsVM findById(Long id) {
        return null;
    }

    @Override
    @Transactional
    public NewsVM update(NewsVM request) {
        News existing = newsRepository.findById(request.getId()).orElse(null);
        if (existing == null) {
            return null;
        }
        News toUpdate = newsMapper.toModel(request);
        newsMapper.mapUpdate(existing, toUpdate);
        existing.setUpdatedAt(LocalDateTime.now());
        return newsMapper.toViewModel(newsRepository.save(existing));
    }

    @Override
    public String updateOnQueue(NewsVM request) {
        UUID uuid = UUID.randomUUID();
        this.update(request);
        return uuid.toString();
    }

    @Override
    @Transactional
    public NewsVM delete(Long id) {
        News existing = newsRepository.findById(id).orElse(null);
        if (existing == null) {
            return null;
        }
        existing.setUpdatedAt(LocalDateTime.now());
        newsRepository.deleteById(id);
        return newsMapper.toViewModel(existing);
    }

    @Override
    public String deleteOnQueue(Long id) {
        UUID uuid = UUID.randomUUID();
        this.delete(id);
        return uuid.toString();
    }
}
