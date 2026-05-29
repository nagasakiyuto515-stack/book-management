package com.example.book_management.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin") // URLの始まりを「/admin」にする設定
public class AdminController {

    // 管理者ダッシュボード画面を表示する処理
    @GetMapping("/dashboard") // これで「/admin/dashboard」のURLになります
    public String adminDashboard() {
        return "admin/dashboard"; // templates/admin/dashboard.html を呼び出す
    }
}