package com.biz.cbt.service;

import java.util.Scanner;

import com.biz.cbt.config.DBConnection;
import com.biz.cbt.dao.CbtDao;
import com.biz.cbt.persistence.CbtDTO;

public class CbtServiceV1 {
	Scanner scanner;
	CbtDao cbtDao;

	public CbtServiceV1() {
		scanner = new Scanner(System.in);
		cbtDao = DBConnection.getSqlSessionFactory().openSession(true).getMapper(CbtDao.class);
	}

	public void startMenu() {
		while (true) {
			System.out.println("===============================");
			System.out.println("1.문제입력 2.문제풀이 0.종료");
			System.out.println("-------------------------------");
			System.out.print("선택 >>");
			
			try {
				int choice = Integer.valueOf(scanner.nextLine());
				if (choice == 0) {
					break;
				} else if (choice == 1) {
					this.inputMenu();
				} else if (choice == 2) {
					this.proSolve();
				} else {
					System.out.println("해당하는 숫자만 입력해주세요");
					continue;
				}
				
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println("숫자만 입력해주시기 바랍니다!!!");
			}
			
		}
	}

	protected void inputMenu() {
		while (true) {
			System.out.println("========================================");
			System.out.println("1.문제등록 2.문제수정 3.문제삭제 0.종료");
			System.out.println("----------------------------------------");
			System.out.print("선택 >>");

			try {
				int choice = Integer.valueOf(scanner.nextLine());
				if (choice == 0) {
					return;
				} else if (choice == 1) {
					this.insert();
				} else if (choice == 2) {
					this.update();
				} else if (choice == 3) {
					this.delete();
				} else {
					System.out.println("해당하는 숫자만 입력해주세요");
					continue;
				}
				
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println("숫자만 입력해주시기 바랍니다!!!");
			}
		}
	}

	protected void proSolve() {

	}

	protected void insert() {

	}

	protected void update() {

	}

	protected void delete() {

	}

	protected void oneResult(CbtDTO cbtDTO) {
		System.out.println("---------------------------------------------------------");
		System.out.println("문제번호 :" + cbtDTO.getCb_seq());
		System.out.println("문제 :" + cbtDTO.getCb_que());
		System.out.println("① :" + cbtDTO.getCb_an1());
		System.out.println("② :" + cbtDTO.getCb_an2());
		System.out.println("③ :" + cbtDTO.getCb_an3());
		System.out.println("④ :" + cbtDTO.getCb_an4());
		System.out.println("정답문항 :" + cbtDTO.getCb_anque());
		System.out.println("---------------------------------------------------------");
	}

}
