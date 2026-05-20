package com.example.form;

import lombok.*;

/**
 * ログイン時に使用するフォーム
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class LoginForm {
    /**
     * メールアドレス
     */
    private String mailAddress;
    /**
     * パスワード
     */
    private String password;
}
