package com.biz.cbt.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import com.biz.cbt.persistence.CbtDTO;

public class CbtServiceV2 extends CbtServiceV1 {

	List<CbtDTO> cbList;
	List<CbtDTO> wList; //오답리스트 
	List<CbtDTO> anList; //정답리스트
	int score = 0;

	public CbtServiceV2() {
		cbList = new ArrayList<CbtDTO>();
	}

	public void wrongAnswerList(List<CbtDTO> wList) {
		System.out.println("====================================================================");
		System.out.println("|			    오답리스트				   |");
		System.out.println("====================================================================");
		for (CbtDTO cbtDTO : wList) {
			this.oneResult(cbtDTO);

		}

	}

	public void answerList(List<CbtDTO> anList) {
		System.out.println("====================================================================");
		System.out.println("|			    정답리스트				   |");
		System.out.println("====================================================================");
		for (CbtDTO cbtDTO : anList) {
			this.oneResult(cbtDTO);

		}
		System.out.println("########################################################################");
		System.out.println("점수 : " + score);
	}

	protected void proSolve() {
		wList = new ArrayList<CbtDTO>();
		anList = new ArrayList<CbtDTO>();
		cbList = cbtDao.selectAll();
		Collections.shuffle(cbList);
		int locate = 4; // 5문제를 풀때마다 오답,정답 리스트를 보여주기 위해서 선언
		score = 0;
		String[] arr = new String[4];
		int re = 1; // 다시풀기의 기회가 몇번 남았는지 보여주기 위해 선언

		for (int i = 0; i < 20; i++) {

			CbtDTO que = cbList.get(i);
			arr[0] = que.getCb_an1();
			arr[1] = que.getCb_an2();
			arr[2] = que.getCb_an3();
			arr[3] = que.getCb_an4();

			Collections.shuffle(Arrays.asList(arr));
			System.out.println("-Q 를 입력하면 중간에 종료됩니다");
			System.out.println("=====================================================================");
			System.out.printf("%d. %s\n", i + 1, que.getCb_que());
			System.out.println("---------------------------------------------------------------------");

			System.out.println("① " + arr[0]);
			System.out.println("② " + arr[1]);
			System.out.println("③ " + arr[2]);
			System.out.println("④ " + arr[3]);
			System.out.println("---------------------------------------------------------------------");
			System.out.print("정답 >>");

			try {

				String answer = scanner.nextLine();
				if (answer.trim().isEmpty()) {
					System.out.println("정답을 입력해주세요");
					i = i - 1;
					continue;
				}

				if (answer.equalsIgnoreCase("-Q")) {
					this.wrongAnswerList(wList);
					this.answerList(anList);
					return;
				}

				if (arr[Integer.valueOf(answer) - 1].equalsIgnoreCase(que.getCb_anque() + "")) {
					System.out.println("※ 정답입니다(Enter:다음문제풀기)");
					String next = scanner.nextLine();
					CbtDTO anDTO = new CbtDTO();
					anDTO.setCb_seq(i + 1);
					anDTO.setCb_que(que.getCb_que());
					anDTO.setCb_an1(arr[0]);
					anDTO.setCb_an2(arr[1]);
					anDTO.setCb_an3(arr[2]);
					anDTO.setCb_an4(arr[3]);
					anDTO.setCb_anque(answer);
					anList.add(anDTO);

					if (next.trim().isEmpty()) {
						score += 5;
						re = 1;
						if (locate == i) {
							this.wrongAnswerList(wList);
							this.answerList(anList);
							locate += 5;
						}
						continue;
					}

				} else {

					System.out.printf("※ 틀렸습니다(R:다시풀기 기회는 (%d)번, N:다음문제풀기)", re);
					
					String again = scanner.nextLine();

					if (re == 0 || again.equalsIgnoreCase("N") || again.equalsIgnoreCase("-Q")) {
						CbtDTO cbtDTO = new CbtDTO();
						cbtDTO.setCb_seq(i + 1);
						cbtDTO.setCb_que(que.getCb_que());
						cbtDTO.setCb_an1(arr[0]);
						cbtDTO.setCb_an2(arr[1]);
						cbtDTO.setCb_an3(arr[2]);
						cbtDTO.setCb_an4(arr[3]);
						cbtDTO.setCb_anque(answer);
						wList.add(cbtDTO);
					}
					
					if (again.equalsIgnoreCase("-Q")) {
						this.wrongAnswerList(wList);
						this.answerList(anList);
						return;
					}

					if (again.equalsIgnoreCase("R")) {
						if (re == 0) {
							// wList.remove(i-1);
							System.out.println("다시풀기 기회는 한번뿐입니다 다음문제로");
							re = 1;
							continue;
						}
						i = i - 1;
						re = 0;

					} else {
						
						if (locate == i) {
							this.wrongAnswerList(wList);
							this.answerList(anList);
							locate += 5;
						}

						if (again.equalsIgnoreCase("N")) {
							re = 1;
							continue;
						}
					}
				}
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println("문자입력은 안됩니다");
				i=i-1;
			}
		}
		System.out.println("모든 문제를 풀었습니다");

	}

	protected void insert() {
		CbtDTO cbtDTO = new CbtDTO();

		while (true) {

			System.out.print("문제번호 (Enter:자동등록)");
			String seq = scanner.nextLine();
			if (seq.equals("-Q"))
				return;

			if (seq.trim().isEmpty()) {
				String strSeq = cbtDao.getMaxSeq();
				int intSeq = Integer.valueOf(strSeq);
				intSeq++;

				System.out.println("생성된 seq:" + intSeq);
				cbtDTO.setCb_seq(intSeq);
			}
			break;
		}

		while (true) {

			System.out.print("문제입력 >>");
			String que = scanner.nextLine();
			if (que.equals("-Q"))
				return;

			if (!que.trim().isEmpty()) {
				cbtDTO.setCb_que(que);
			} else {
				System.out.println("문제를 반드시 입력해주세요");
				continue;
			}
			break;
		}

		while (true) {
			System.out.print("문항1번 >>");
			String an1 = scanner.nextLine();
			if (an1.equals("-Q"))
				return;

			if (!an1.trim().isEmpty()) {
				cbtDTO.setCb_an1(an1);
			} else {
				System.out.println("문항1번을 반드시 입력해주세요");
				continue;
			}
			break;
		}

		while (true) {
			System.out.print("문항2번 >>");
			String an2 = scanner.nextLine();
			if (an2.equals("-Q"))
				return;

			if (!an2.trim().isEmpty()) {
				cbtDTO.setCb_an2(an2);
				;
			} else {
				System.out.println("문항2번을 반드시 입력해주세요");
				continue;
			}
			break;
		}

		while (true) {
			System.out.print("문항3번 >>");
			String an3 = scanner.nextLine();
			if (an3.equals("-Q"))
				return;

			if (!an3.trim().isEmpty()) {
				cbtDTO.setCb_an3(an3);
			} else {
				System.out.println("문항3번을 반드시 입력해주세요");
				continue;
			}
			break;
		}

		while (true) {
			System.out.print("문항4번 >>");
			String an4 = scanner.nextLine();
			if (an4.equals("-Q"))
				return;

			if (!an4.trim().isEmpty()) {
				cbtDTO.setCb_an4(an4);
			} else {
				System.out.println("문항4번을 반드시 입력해주세요");
				continue;
			}
			break;
		}

		while (true) {
			System.out.print("정답문항 >>");
			String anQue = scanner.nextLine();
			if (anQue.equals("-Q"))
				return;

			if (!anQue.trim().isEmpty()) {
				cbtDTO.setCb_anque(anQue);
			} else {
				System.out.println("정답문항을 반드시 입력해주세요");
				continue;
			}
			break;
		}

		int ret = cbtDao.insert(cbtDTO);
		if (ret > 0) {
			System.out.println("※ 등록 되었습니다");
		} else {
			System.out.println("등록되지 않았습니다");
		}
	}

	protected void update() {
		CbtDTO cbtDTO = new CbtDTO();

		while (true) {

			System.out.print("업데이트할 seq를 입력하시오 :");
			String strSEQ = scanner.nextLine();
			int intSeq = Integer.valueOf(strSEQ);
			if (strSEQ.equals("-Q"))
				return;

			cbtDTO = cbtDao.findBySeq(intSeq);
			if (strSEQ.trim().isEmpty()) {
				System.out.println("seq를 다시 입력해주세요");
				continue;
			} else {
				this.oneResult(cbtDTO);
			}
			break;
		}

		while (true) {

			System.out.print("문제 수정 >>");
			String que = scanner.nextLine();
			if (que.equals("-Q"))
				return;

			if (!que.trim().isEmpty()) {
				cbtDTO.setCb_que(que);
			} else {

			}
			break;
		}

		while (true) {
			System.out.print("문항1번 수정 >>");
			String an1 = scanner.nextLine();
			if (an1.equals("-Q"))
				return;

			if (!an1.trim().isEmpty()) {
				cbtDTO.setCb_an1(an1);
			} else {

			}
			break;
		}

		while (true) {
			System.out.print("문항2번 수정>>");
			String an2 = scanner.nextLine();
			if (an2.equals("-Q"))
				return;

			if (!an2.trim().isEmpty()) {
				cbtDTO.setCb_an2(an2);

			} else {

			}
			break;
		}

		while (true) {
			System.out.print("문항3번 수정>>");
			String an3 = scanner.nextLine();
			if (an3.equals("-Q"))
				return;

			if (!an3.trim().isEmpty()) {
				cbtDTO.setCb_an3(an3);
			} else {

			}
			break;
		}

		while (true) {
			System.out.print("문항4번 수정 >>");
			String an4 = scanner.nextLine();
			if (an4.equals("-Q"))
				return;

			if (!an4.trim().isEmpty()) {
				cbtDTO.setCb_an4(an4);
			} else {

			}
			break;
		}

		while (true) {
			System.out.print("정답문항 >>");
			String anQue = scanner.nextLine();
			if (anQue.equals("-Q"))
				return;

			if (!anQue.trim().isEmpty()) {
				cbtDTO.setCb_seq(Integer.valueOf(anQue));
			} else {

			}
			break;
		}

		int ret = cbtDao.update(cbtDTO);
		if (ret > 0) {
			System.out.println("※ 업데이트 되었습니다");
		} else {
			System.out.println("업데이트 되지 않았습니다");
		}
	}

	protected void delete() {
		while (true) {
			System.out.print("삭제할 seq 입력:");
			String strSEQ = scanner.nextLine();
			int intSEQ = Integer.valueOf(strSEQ);
			if (strSEQ.equals("-Q"))
				return;

			CbtDTO dto = cbtDao.findBySeq(intSEQ);
			if (dto == null) {
				System.out.println("존재하지 않는 seq 입니다");
				continue;
			}
			this.oneResult(dto);

			System.out.print("삭제하시겠습니까?(Enter:삭제, -Q:quit)");
			String yesNo = scanner.nextLine();

			if (yesNo.trim().isEmpty()) {

				int ret = cbtDao.delete(intSEQ);
				if (ret > 0) {
					System.out.println("데이터 삭제완료");
				} else {
					System.out.println("데이터 삭제실패");
				}
			} else {
				return;
			}
			break;
		}
	}
}
