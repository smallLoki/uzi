package ru.javabegin.training.controllers;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.RequestContextUtils;

import ru.javabegin.training.objects.Diagnostic;
import ru.javabegin.training.objects.Doctor;
import ru.javabegin.training.objects.Sick;
import ru.javabegin.training.objects.newPass;
import ru.javabegin.training.impls.SQLRequestDialog;


@Controller
@SessionAttributes({"doctor", "newpass"})
public class LoginController {

	private static final Logger Logger = LoggerFactory.getLogger(LoginController.class);
	private Doctor loginDoc;
	private ArrayList<Sick> priceSick;
	private ArrayList<Diagnostic> priceDiagn;
	private ArrayList<Diagnostic> myPriceDiagn;
	
	@ModelAttribute("doctor")
	public Doctor createNewUser() {
		return new Doctor();
	}

	@ModelAttribute("newpass")
	public newPass createNewPass() {
		return new newPass();
	}
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView main(@ModelAttribute Doctor doctor, HttpSession session) {
		return new ModelAndView("login", "doctor", new Doctor());
	}
	
	@RequestMapping(value = "/login")
	public String exitAk() {
		loginDoc = null;
		priceSick = null;
		priceDiagn = null;
		myPriceDiagn = null;
		return "redirect:/";
	}

	@RequestMapping(value = "/check-user", method = RequestMethod.POST)
	public ModelAndView checkUser(@Valid @ModelAttribute("doctor") Doctor doctor, BindingResult bindingResult, ModelMap modelMap, RedirectAttributes reditrectAttributes){

		ModelAndView mav = new ModelAndView();
		if (!bindingResult.hasErrors()){

			Doctor doc = new Doctor();
			SQLRequestDialog a = new SQLRequestDialog(); 
	         try {
				Doctor b = a.loginDoctor(doctor);
				if (b.getFio() != null){
					loginDoc = b;
			     	mav.setViewName("redirect:/mainpage");
				} else {
			     	mav.setViewName("login");
			     	mav.addObject("message", "Не верный Логин или Пароль!");
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return mav;
		}

     	mav.setViewName("login");
     	mav.addObject("message", "Нет соединения с базой!");
		return mav;
	}
	
	@RequestMapping(value = "/mainpage", method = RequestMethod.GET)
	public ModelAndView goMainPage(HttpServletRequest request) {
     	ModelAndView mav = new ModelAndView();
     	ArrayList<Sick> sick = new ArrayList<Sick>();
     	ArrayList<Diagnostic> diagn = new ArrayList<Diagnostic>();
		SQLRequestDialog a = new SQLRequestDialog(); 
         try {
		    mav.addObject("welcom", "Добро пожаловать в кабинет " + loginDoc.getFio() +"!");
        	sick = a.getSick();
			if (sick != null){
		     	mav.addObject("sick", sick);
			} else {
		     	mav.addObject("T1", "Нет данных!");
			}
			priceSick = sick;

			
			diagn = a.getDiagn(0);
			if (sick != null){
		     	mav.addObject("diagn", diagn);
			} else {
		     	mav.addObject("T2", "Нет данных!");
			}
			priceDiagn = diagn;


			
		} catch (SQLException e) {
			e.printStackTrace();
		}

     	mav.setViewName("insert");
     	mav.addObject("loginDoc", loginDoc);
         
		return mav;
	}
	
	@RequestMapping(value = "/up-pass", method = RequestMethod.POST)
	public ModelAndView upPass(@ModelAttribute newPass newpass){

		boolean b;
		ModelAndView mav = new ModelAndView();
		SQLRequestDialog a = new SQLRequestDialog(); 
        try {
		    b = a.updatePass(loginDoc, newpass.getNewPass1());
			if (b){
				loginDoc.setPassword(newpass.getNewPass1());
				mav.addObject("TAB", 3);
				mav.addObject("MES", "Смена пароля прошла успешно!");
			} else {
		     	mav.addObject("TAB", 3);
				mav.addObject("MES", "Неправильно введен старый пароль!");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

     	mav.setViewName("redirect:/mainpage");
     	mav.addObject("message", "Нет соединения с базой!");
		return mav;
	}
	
	@RequestMapping(value = "/failed", method = RequestMethod.GET)
	public ModelAndView failed(){
		return new ModelAndView("login-failed", "message", "Авторизация провалена!");
	}
}
