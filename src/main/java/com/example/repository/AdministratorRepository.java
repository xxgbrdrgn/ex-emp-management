package com.example.repository;

import com.example.domain.Administrator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

/**
 * administratorsテーブルを操作するリポジトリ(Dao)
 */
@Repository
public class AdministratorRepository {
    /**
     * Administratorオブジェクトを生成するローマッパー.
     */
    private static final RowMapper<Administrator> ADMINISTRATOR_ROW_MAPPER = (rs, i) -> {
        // ここに結果の処理を書く
        Administrator administrator = new Administrator();
        administrator.setId(rs.getInt("id"));
        administrator.setName(rs.getString("name"));
        administrator.setMailAddress(rs.getString("mail_address"));
        administrator.setPassword(rs.getString("password"));
        return administrator;
    };

    @Autowired
    private NamedParameterJdbcTemplate template;

    /**
     * 管理者情報を挿入するメソッド.
     *
     * @param administrator 挿入する管理者の情報
     */
    public void insert(Administrator administrator) {
        SqlParameterSource param = new BeanPropertySqlParameterSource(administrator);
        String insertSql = """
                insert into administrators(name, mail_address, password)
                            values(:name, :mailAddress, :password);
                """;

        template.update(insertSql, param);
    }

    /**
     * メールアドレスとパスワードから管理者情報を取得する.
     *
     * @param mailAddress メールアドレス
     * @param password    パスワード
     * @return 取得した管理者情報
     * 1件も検索されなければnullを返す
     *
     */

    public Administrator findByMailAddressAndPassword(String mailAddress, String password) {
        SqlParameterSource param = new MapSqlParameterSource().addValue("mailAddress", mailAddress)
                .addValue("password", password);
        String sql = """
                select id, name, mail_address, password
                from administrators
                where mail_address = :mailAddress
                        and password = :password
                """;

        try {
            //1件も検索されなかったら例外が発生する
            return template.queryForObject(sql, param, ADMINISTRATOR_ROW_MAPPER);
        } catch (Exception e) {
            //例外が発生したらnullを返す
            return null;
        }
    }
}
