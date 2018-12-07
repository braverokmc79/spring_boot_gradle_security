package com.libqa.entity;


import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import lombok.Data;
/**
 * @author 최준호 (braverokmc79@gmail.com)
     
   @업데이트일 2018. 11. 21. 오후 4:04:38
	
 */
@Entity
@Table(name = "user")
@Data
public class User{


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id")
    private int id;

    @Column(name="phone")
    @NotBlank(message = "* 핸드폰 번호를 입력해 주세요.")
    private String phone ;  //(1) 아이디 : 핸드폰 전화번호를 입력한다. (중복 확인)  
    
    @Column(name = "password")  //(2) 비밀번호 : 관리자가 부여한 비밀번호를 입력한다. 숫자 4자리
    @Length(min = 4, message = "* 비밀번호는  4글자 이상이어야합니다.")
    @NotBlank(message = "* 비밀번호를 입력해 주세요.")
    private String password;

    @Transient
    @NotBlank(message = "* 비밀번호를 확인을 입력해 주세요.")
    private String passwordConfirm;
    
    
    @Column(name = "name")  //(3) 이름 : 이름 입력
    @NotBlank(message = "* 이름을 입력해 주세요.")
    private String name;
    

    @Column(name="birthday") // (4) 생년월일 : 19841201 순으로 입력한다.
    @NotBlank(message = "* 생년월일 입력해 주세요.")
    private String birthday;      
    
    
    @Column(name = "email")    // (5) 이메일 : 이메일을 입력한다.
    private String email;


    @Column(name="company")  // (6) 소속 : 회사에 소속 시 회사명을 입력한다. 
    private String company;
        
    @Column(name="sido")
    private String sido; // (7) 주소 : 지역 분류표(엑셀 자료)에 의거 화면에 나타난 대분류(서울, 부산....경남, 제주),
    
    @Column(name="gugun")
    private String gugun; // (7)  중분류(경남이면 시나 군까지)를 클릭하여 입력한다.
    
    
    //(8) 대표 원소 : 불 클릭하면 f, 흙은 e, 공기는 a, 물은 w를 입력한다. (f : fire, e : earth, a : air, w : water)
    @Column(name="representative_element") 
    @NotBlank(message="* 대표 원소를 선택해 주세요.")
    private String representativeElement;
    

    //(9) 바탕 원소 : 대표 원소와 동일하게 선택 후 해당 f, e, a, w를 입력한다.     
    @Column(name="base_element")  
    @NotBlank(message="* 바탕 원소를 선택해 주세요.")
    private String baseElement;

    
//  (10) 정회원 여부 : 운영자를 클릭하면 ‘1’, 정회원을 클릭하면 ‘2’, 미수금 회원은 ‘3’을 입력
//  ‘3’인 경우 결혼예정회원 입력과 조건맞춤 프로그램 사용을 제한한다.    
   //ADMIN - 운영자,  USERS 정회원 - 2 , GUEST 미수금 - 3  MASTER - 최고 관리자 - 4,  
   
    @Transient
    @NotBlank(message="* 회원등급을 선택해 주세요.")
    private String role;  
    
    @Column(name = "active")
    private int active;
	  
//  (11) 가입일 : 가입한 날자를 입력한다 (예:20181225)
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "regdate")
    private java.util.Date regdate;
        
//(12) 배우자 조건 맞춤 횟수 : 배우자 조건 맞춤 사용할 때마다 자동으로 1씩 더함    
    @Column(name = "spouse_count")
    private Integer spouseCount;

//(13) 입력한 결혼 예정 회원 수 : 회원 입력할 때마다 자동으로 1씩 더함    
    @Column(name = "to_be_married")
    private Integer toBeMarried;    
    
    
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;
    
    
    @Transient
    private String email1;
    
    @Transient
    private String email2;
        

    public boolean emailCheck() {    	
    	if(this.email1==null || this.email2==null || this.email1.equals("") || this.email2.equals("")) {    		
    		return false;
    	}

	  this.email=this.email1+"@"+this.email2;
	  String regex = "^[_a-zA-Z0-9-\\.]+@[\\.a-zA-Z0-9-]+\\.[a-zA-Z]+$";
	  Pattern p = Pattern.compile(regex);
	  Matcher m = p.matcher(email);
	  boolean isNormal = m.matches();    	 
	  return isNormal;
    }
    
    
    
    public boolean sidoGugunCheck() {
    	if(this.gugun==null ||  this.gugun.equals("")) {    		
    		return false;
    	}
    	return true;
    }
    
    //비밀번호 와 비밀번호 확인 체크
    public boolean passwordCheck() {
    	if(!this.password.equals(this.passwordConfirm)) {
    		return false;
    	}    	
    	return true;
    }
    
    
    @Transient
    private String lastName;

    
        

}








