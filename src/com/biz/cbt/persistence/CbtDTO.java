package com.biz.cbt.persistence;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder

public class CbtDTO {
	private int cb_seq;	//nvarchar2(50 char) 문제번호
	private String cb_que;	//nvarchar2(5 char) 문제 
 	private String cb_an1;	//nvarchar2(20 char) 문항1
	private String cb_an2;	//nvarchar2(20 char) 문항2
	private String cb_an3;	//nvarchar2(20 char) 문항3
	private String cb_an4;	//nvarchar2(20 char) 문항4
	private String cb_anque;	//number 정답문항
}
