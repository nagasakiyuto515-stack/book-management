package com.example.book_management.service; // 1. パッケージ名が合っているか確認

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.example.book_management.repository.UserRepository;
import com.example.book_management.model.User;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository; // お使いのRepositoryクラス名に合わせてください

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username).orElse(null); 
        
        if (user == null) {
            throw new UsernameNotFoundException("ユーザーが見つかりません: " + username);
        }

        // DBから取得したロール（例: "ADMIN" や "ROLE_ADMIN"）
        String rawRole = user.getRole();
        // もし頭に "ROLE_" がついていたら取り除く（.roles()メソッドが自動で後から付与するため）
        if (rawRole.startsWith("ROLE_")) {
            rawRole = rawRole.replace("ROLE_", "");
        }

        return org.springframework.security.core.userdetails.User.withUsername(user.getUsername())
                .password(user.getPassword())
                .roles(rawRole) // ★ .authorities() ではなく .roles() を使うと安全です
                .build();
    }
}