package com.example.book_management.controller; // ★ パッケージ名をこれに合わせる

import com.example.book_management.service.UserService; // ★ 必要に応じてパッケージを書き換え
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.security.crypto.password.PasswordEncoder;

@Controller
public class AuthController {
    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // ユーザ作成画面表示
    @GetMapping("/register")
    public String registerForm() {
        return "register";
    }

    // AuthController.java の中に追加すると便利です
@GetMapping("/")
public String index() {
    return "redirect:/login"; // トップページにアクセスされたら自動でログインへ転送
}

    // ユーザ作成処理
    @PostMapping("/register")
public String registerUser(@RequestParam String username, 
                           @RequestParam String password,
                           @RequestParam String role,
                           Model model) {
    try {
        // ❌ コントローラー側での暗号化（passwordEncoder.encode）は削除！
        // ⭕ 生のpasswordをそのままサービスに渡します（サービス側で暗号化されるため）
        userService.registerUser(username, password, role); 
        
    } catch (RuntimeException e) {
        model.addAttribute("error", e.getMessage());
        return "register";
    }
    return "redirect:/login";
}

    // ログイン画面表示
    @GetMapping("/login")
    public String loginForm() {
        return "login";
    }
}