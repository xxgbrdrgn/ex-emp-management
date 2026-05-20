package com.example.repository;

import com.example.domain.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * employeesテーブルを操作するリポジトリ(Dao).
 */
@Repository
public class EmployeeRepository {
    /**
     * Employeeオブジェクトを生成するローマッパー.
     */
    private static final RowMapper<Employee> EMPLOYEE_ROW_MAPPER = (rs, i) -> {
        // ここに結果の処理を書く
        Employee employee = new Employee();
        employee.setId(rs.getInt("id"));
        employee.setName(rs.getString("name"));
        employee.setImage(rs.getString("image"));
        employee.setGender(rs.getString("gender"));
        employee.setHireDate((Date) rs.getDate("hire_date"));
        employee.setMailAddress(rs.getString("mail_address"));
        employee.setZipCode(rs.getString("zip_code"));
        employee.setAddress(rs.getString("address"));
        employee.setTelephone(rs.getString("telephone"));
        employee.setSalary(rs.getInt("salary"));
        employee.setCharacteristics(rs.getString("characteristics"));
        employee.setDependentsCount(rs.getInt("dependents_count"));
        return employee;
    };

    @Autowired
    private NamedParameterJdbcTemplate template;

    /**
     * 従業員一覧情報を入社日順（降順）で取得する.
     * 従業員が存在しない場合はサイズ０件の従業員一覧を返す
     *
     * @return 登録されている従業員情報のリスト
     */

    public List<Employee> findAll() {
        String sql = """
                select id, name, image, gender, hire_date, mail_address, zip_code,
                       address, telephone, salary, characteristics, dependents_count
                from employees
                order by hire_date desc""";

        return template.query(sql, EMPLOYEE_ROW_MAPPER);
    }

    /**
     * idを除く従業員情報を更新する.
     *
     * @param employee 更新する従業員情報を保持するemployeeオブジェクト
     */
    public void update(Employee employee) {
        SqlParameterSource param = new BeanPropertySqlParameterSource(employee);

        String sql = """
                update employees
                set     name = :name,
                        image = :image,
                        gender = :gender,
                        hire_date = :hireDate,
                        mail_address = :mailAddress,
                        zip_code = :zipCode,
                        address = :address,
                        telephone = :telephone,
                        salary = :salary,
                        characteristics = :characteristics,
                        dependents_count = :dependentsCount
                where   id = :id
                """;


        template.update(sql, param);
    }

}
