package com.koreait.mylogin.loginweb.login;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.koreait.mylogin.loginweb.member.Member;
import com.koreait.mylogin.loginweb.session.SessionConst;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class LoginController {
	
	private final LoginService loginService;
	
	@GetMapping("/login")
	public String loginForm(@ModelAttribute("loginForm")LoginForm loginForm) {
		return "login/loginForm";
	}
	
//	@PostMapping("/login") // 쿠키에 담아주기
	public String login(@ModelAttribute LoginForm form, Model model, RedirectAttributes redirectAttributes , HttpServletResponse response) {
		Member loginMember = loginService.login(form.getLoginId(), form.getPassword());
		
		System.out.println(loginMember);
		
		if( loginMember == null) {
			// 로그인실패
			model.addAttribute("msg", "로그인실패");
			return "login/loginForm";
		}
		
		/*
		 *  - addAttribute : url 뒤에 붙는다.
		 *  - addFlashAttribute : url 뒤에 붙지 않는다.
		 */
		
		// 로그인 성공
		Cookie idCookie = new Cookie("memberId", String.valueOf(loginMember.getId()));
		response.addCookie(idCookie);
		
		redirectAttributes.addFlashAttribute("msg","로그인 성공");
		return "redirect:/";
	}
	
//	@PostMapping("/login") // 세션에 담아주기
	public String loginv2(@ModelAttribute LoginForm form, Model model, RedirectAttributes redirectAttributes , HttpServletRequest request) {
		Member loginMember = loginService.login(form.getLoginId(), form.getPassword());
		
		System.out.println(loginMember);
		
		if( loginMember == null) {
			// 로그인실패
			model.addAttribute("msg", "로그인실패");
			return "login/loginForm";
		}
		// 로그인 성공
		HttpSession session = request.getSession();
		//세션에 로그인 회원정보 보관
		session.setAttribute(SessionConst.LOGIN_MEMBER, loginMember);
		
		redirectAttributes.addFlashAttribute("msg","로그인 성공");
		return "redirect:/";
	}
	
	@PostMapping("/login") // 세션에 담아주기
	public String loginv3(@ModelAttribute LoginForm form, Model model, RedirectAttributes redirectAttributes ,
			HttpServletRequest request, @RequestParam(defaultValue = "/")String redirectURL ) {
										// 넘어온 파라미터 값이 없으면 "/" 여기로 이동하겠다.
		Member loginMember = loginService.login(form.getLoginId(), form.getPassword());
		
		System.out.println(loginMember);
		
		if( loginMember == null) {
			// 로그인실패
			model.addAttribute("msg", "로그인실패");
			return "login/loginForm";
		}
		// 로그인 성공
		HttpSession session = request.getSession();
		//세션에 로그인 회원정보 보관
		session.setAttribute(SessionConst.LOGIN_MEMBER, loginMember);
		
		redirectAttributes.addFlashAttribute("msg","로그인 성공");
		return "redirect:" + redirectURL;
	}
	
//	@PostMapping("/logout")
	public String logout(HttpServletResponse response) {
		Cookie cookie = new Cookie("memberId", null);
		cookie.setMaxAge(0);
		response.addCookie(cookie);
		return "redirect:/";
	}
	
	@PostMapping("/logout")
	public String logoutv2(HttpServletRequest request) {
		//세션을 삭제
		/*
		 *  request.getSession(true) - 세션이 있으면 기존 세션을 반환한다. 세션이 없으면 새로운 세션을 생성해서 반환한다.
		 *  request.getSession(false) - 세션이 있으면 기존 세션을 반환한다. 세션이 없으면 새로운 세션을 생성하지 않고, null을 반환
		 */
		HttpSession session = request.getSession(false); 
		if(session != null) {
			session.invalidate();
		}
		return "redirect:/";
	}
	
}
