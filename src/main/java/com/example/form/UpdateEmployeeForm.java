package com.example.form;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
/**
 * 従業員情報更新時に使うフォーム
 */
public class UpdateEmployeeForm {
    /**
     * 従業員ID
     */
    private String id;
    /**
     * 扶養人数
     */
    private String dependentsCount;
}
