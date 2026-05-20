package com.example.service;

import com.example.domain.Employee;
import com.example.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 従業員情報を操作するServiceクラス.
 */
@Service
@Transactional
public class EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;

    /**
     * 従業員一覧を全検索する業務処理を行うメソッド.
     *
     * @return RepositoryのfindAll()で返ってきたEmployee型のオブジェクトのリスト
     */
    public List<Employee> showList() {
        return employeeRepository.findAll();
    }

    /**
     * 従業員情報をid指定で検索する.
     *
     * @param id ID
     * @return 指定されたIDを持つ従業員
     */
    public Employee showDetail(Integer id) {
        return employeeRepository.findById(id);
    }

    /**
     * 扶養人数を更新する.
     *
     * @param employeeId 従業員ID
     * @param newCount   更新する扶養人数
     */
    public void updateDependentsCount(Integer employeeId, Integer newCount) {
        Employee employee = employeeRepository.findById(employeeId);
        employee.setDependentsCount(newCount);
        employeeRepository.update(employee);
    }
}
