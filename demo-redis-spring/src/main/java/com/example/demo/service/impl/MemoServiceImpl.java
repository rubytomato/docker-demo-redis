package com.example.demo.service.impl;

import com.example.demo.entity.Memo;
import com.example.demo.repository.MemoRepository;
import com.example.demo.service.MemoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Slf4j
public class MemoServiceImpl implements MemoService {

  private final MemoRepository memoRepository;

  public MemoServiceImpl(MemoRepository repository) {
    this.memoRepository = repository;
  }

  @Transactional(readOnly = true)
  @Override
  public Optional<Memo> findById(Long id) {
    return memoRepository.findById(id);
  }

  @Transactional(readOnly = true)
  @Override
  public Page<Memo> findAll(Pageable page) {
    return memoRepository.findAll(page);
  }

  @Transactional(timeout = 10)
  @Override
  public void store(Memo memo) {
    memoRepository.save(memo);
  }

  @Transactional(timeout = 10)
  @Override
  public void removeById(Long id) {
    memoRepository.deleteById(id);
  }

}