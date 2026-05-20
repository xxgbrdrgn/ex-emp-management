package com.example.service;

import com.example.domain.Administrator;
import com.example.repository.AdministratorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
/**
 * 管理者情報を操作するサービス
 */
public class AdministratorService {
    @Autowired
    private AdministratorRepository administratorRepository;

    /**
     * 管理者情報を挿入する.
     *
     * @param administrator 挿入する管理者情報を持つadministratorオブジェクト
     */
    public void insert(Administrator administrator) {
        administratorRepository.insert(administrator);
    }

}
