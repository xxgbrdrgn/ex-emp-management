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

    /**
     * 管理者のログイン処理をする.
     *
     * @param mailAddress 入力されたメールアドレス
     * @param password    入力されたパスワード
     * @return 検索され、返ってきた管理者情報を保持するAdministrator型のオブジェクト
     */
    public Administrator login(String mailAddress, String password) {
        return administratorRepository.findByMailAddressAndPassword(mailAddress, password);
    }

}
