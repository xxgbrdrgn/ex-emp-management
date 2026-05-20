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
 * 管理者登録画面のコントローラー
 */
@Controller

@RequestMapping("/")
public class AdministratorController {
    @Autowired
    private AdministratorService administratorService;
    @Autowired
    private HttpSession session;

    /**
     * administrator/insertにフォワードする処理.
     *
     * @param form 入力情報をもつオブジェクトform
     * @return insertのpath
     */
    @GetMapping("/to-insert")
    public String toInsert(InsertAdministratorForm form) {
        return "administrator/insert";
    }

    /**
     * 管理者DBにinsertする.
     *
     * @param form 挿入する管理者の情報を持つformオブジェクト
     * @return "/"へリダイレクト
     */
    @PostMapping("/insert")
    public String insert(InsertAdministratorForm form) {
        Administrator administrator = new Administrator();
        BeanUtils.copyProperties(form, administrator);
        administratorService.insert(administrator);
        return "redirect:/";
    }

    /**
     * administrator/loginにフォワードする.
     *
     * @param form 入力された管理者情報を保持するformオブジェクト
     * @return /loginのpath
     */
    @GetMapping("/")
    public String toLogin(LoginForm form) {
        return "administrator/login";
    }

    /**
     * login処理を行う.
     *
     * @param form  ログインに必要な情報を保持するformオブジェクト
     * @param model エラーメッセージを格納するためのrequestスコープ
     * @return 従業員情報のリストを参照する/show-listのpath
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
