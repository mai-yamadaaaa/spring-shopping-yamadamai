package com.example.demo.controller;

import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.model.Account;

@Controller
public class AccountController {

	@Autowired
	HttpSession session;

	@Autowired
	Account account;

	@GetMapping({ "/", "/login", "/logout" })
	public String Index(
			@RequestParam(name = "error", defaultValue = "") String error,
			Model model) {
		//セッション情報を全てクリアする
		session.invalidate();

		if (error.equals("notLoggedIn")) {
			model.addAttribute("message", "ログインしてください");
		}

		return "login";
	}

	//ログインを実行
	@PostMapping("login")
	public String login(
			@RequestParam("name") String name,
			Model model) {
		//名前が空の場合にエラーとする
		if (name == null || name.length() == 0) {
			model.addAttribute("message", "名前を入れてください");
			return "login";
		}

		//セッション管理されたアカウント情報に名前をセット
		account.setName(name);

		//「items」へのリダイレクト
		return "redirect:/items";
	}

}
