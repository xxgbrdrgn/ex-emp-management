package com.example.controller;

import com.example.domain.Administrator;
import com.example.form.InsertAdministratorForm;
import com.example.form.LoginForm;
import com.example.service.AdministratorService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 管理者登録画面のコントローラー.
 */
@Controller
@RequestMapping("/")
public class AdministratorController {
    @Autowired
    private AdministratorService administratorService;
    @Autowired
    private HttpSession session;

    /**
     * 管理者画面を表示する.
     *
     * @param form フォーム
     * @return 管理者画面
     */
    @GetMapping("/to-insert")
    public String toInsert(InsertAdministratorForm form) {
        return "administrator/insert";
    }

    /**
     * 管理者情報を登録する.
     *
     * @param form 挿入する管理者の情報を持つformオブジェクト
     * @return ログイン画面
     */
    @PostMapping("/insert")
    public String insert(InsertAdministratorForm form) {
        Administrator administrator = new Administrator();
        BeanUtils.copyProperties(form, administrator);
        administratorService.insert(administrator);
        return "redirect:/";
    }

    /**
     * ログイン画面に遷移.
     *
     * @param form 入力された管理者情報を保持するformオブジェクト
     * @return ログイン画面
     */
    @GetMapping("/")
    public String toLogin(LoginForm form) {
        return "administrator/login";
    }

    /**
     * login処理を行う.
     *
     * @param form  フォーム
     * @param model requestスコープ
     * @return 従業員情報一覧画面
     */
    @PostMapping("/login")
    public String login(LoginForm form, Model model) {
        Administrator administrator = administratorService.login(form.getMailAddress(), form.getPassword());

        if (administrator == null) {
            model.addAttribute("errorMessage", "メールアドレスまたはパスワードが違います");
            return "administrator/login";
        } else {
            session.setAttribute("administratorName", administrator);
            return "redirect:/employee/show-list";
        }


    }
}
