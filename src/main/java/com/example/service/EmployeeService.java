package com.example.service;

import com.example.domain.Employee;
import com.example.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
/**
 * 従業員情報を操作するServiceクラス.
 */
public class EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;

    /**
     * 従業員一覧を全権検索する業務処理を行うメソッド.
     *
     * @return RepositoryのfindAll()で返ってきたEmployee型のオブジェクトのリスト
     */
    public List<Employee> showList() {
        return employeeRepository.findAll();
    }
}
