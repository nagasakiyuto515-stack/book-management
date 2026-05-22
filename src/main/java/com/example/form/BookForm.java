package com.example.form;

import jakarta.validation.constraints.*;
import com.example.book_management.validation.ValidISBN;
import com.example.book_management.validation.ValidPrice;

public class BookForm {
    @NotBlank(message = "タイトルは必須です")
    @Size(min = 1, max = 100, message = "タイトルは1〜100文字である必要があります")
    private String title;

    @NotBlank(message = "著者は必須です")
    @Size(min = 1, max = 100, message = "著者は1〜100文字である必要があります")
    private String author;

    @NotBlank(message = "ISBNは必須です")
    @ValidISBN  // カスタムバリデーション
    private String isbn;

    @NotNull(message = "価格は必須です")
    @ValidPrice  // カスタムバリデーション
    private Double price;

    // ★★★ ここに引数なしコンストラクタを明示的に追加します ★★★
    public BookForm() {
    }

    // ゲッター・セッター（ここは変更なし）
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getAuthor() { return author; }
    public void setAuthor(String author) { this.author = author; }

    public String getIsbn() { return isbn; }
    public void setIsbn(String isbn) { this.isbn = isbn; }

    public Double getPrice() { return price; }
    public void setPrice(Double price) { this.price = price; }
}