package com.libqa.entity;


import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import lombok.Data;
/**
 * @author 최준호 (braverokmc79@gmail.com)
     
   @업데이트일 2018. 11. 21. 오후 4:04:38
	
 */

public class User3{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id")
    private int id;

    @Column(name="phone")
    private String phone ;  //(1) 아이디 : 핸드폰 전화번호를 입력한다. (중복 확인)  
    
    @Column(name = "password")  //(2) 비밀번호 : 관리자가 부여한 비밀번호를 입력한다. 숫자 4자리
    @Length(min = 4, message = "* 비밀번호는  4글자 이상이어야합니다.")
    @NotEmpty(message = "* 비밀번호를 입력해 주세요.")
    private String password;

    
    @Column(name = "name")  //(3) 이름 : 이름 입력
    @NotEmpty(message = "* 이름을 입력해 주세요.")
    private String name;
    

    @Column(name="birthday") // (4) 생년월일 : 19841201 순으로 입력한다.
    private String birthday;      
    
    
    @Column(name = "email")    // (5) 이메일 : 이메일을 입력한다.
    @Email(message = "* 유효한 이메일을 입력하십시오.")
    @NotEmpty(message = "* 이메일을 입력해 주세요.")
    private String email;


    @Column(name="company")  // (6) 소속 : 회사에 소속 시 회사명을 입력한다. 
    private String company;
        
    @Column(name="address1")
    private String address1; // (7) 주소 : 지역 분류표(엑셀 자료)에 의거 화면에 나타난 대분류(서울, 부산....경남, 제주),
    
    @Column(name="address2")
    private String address2; // (7)  중분류(경남이면 시나 군까지)를 클릭하여 입력한다.
    
    
    //(8) 대표 원소 : 불 클릭하면 f, 흙은 e, 공기는 a, 물은 w를 입력한다. (f : fire, e : earth, a : air, w : water)
    @Column(name="representative_element") 
    private String representativeElement;
    

    //(9) 바탕 원소 : 대표 원소와 동일하게 선택 후 해당 f, e, a, w를 입력한다.     
    @Column(name="base_element")  
    private String baseElement;

    
//  (10) 정회원 여부 : 운영자를 클릭하면 ‘1’, 정회원을 클릭하면 ‘2’, 미수금 회원은 ‘3’을 입력
//  ‘3’인 경우 결혼예정회원 입력과 조건맞춤 프로그램 사용을 제한한다.    
   //ADMIN - 운영자,  USERS 정회원 - 2 , GUEST 미수금 - 3  MASTER - 최고 관리자 - 4,  
    @Transient
    private String role;  
    
    @Column(name = "active")
    private int active;
	  
//  (11) 가입일 : 가입한 날자를 입력한다 (예:20181225)
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "regdate")
    private java.util.Date regdate;
        
//(12) 배우자 조건 맞춤 횟수 : 배우자 조건 맞춤 사용할 때마다 자동으로 1씩 더함    
    @Column(name = "spouse_count")
    private String spouseCount;

//(13) 입력한 결혼 예정 회원 수 : 회원 입력할 때마다 자동으로 1씩 더함    
    @Column(name = "to_be_married")
    private String toBeMarried;    
    
    
    @Column(name = "last_name")
    @NotEmpty(message = "* 성을 뺀 이름만 입력해 주세요.")
    private String lastName;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;
    
    
        

}








