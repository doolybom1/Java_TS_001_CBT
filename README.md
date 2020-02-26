### 기출문제 문제풀이 프로그램
* oracle 11g
* java 8
* mybatis 3.5.3

```
기출문제 프로젝트는 정보처리기사 기출문제를 가지고 만들었으며 총20문제를 풀게되고 20문제는 랜덤하게 나오게 된다.   
다 풀었을 시에는 오답, 정답리스트에서 틀린문제와 맞은 문제를 확인할 수 있게되고 최종점수가 나오도록 구현하였다.
```

문제풀이는 틀렸을 경우에 다시 풀 기회를 1번 더 주게되고 그래도 틀리게 되면 다음문제로 넘어가게 된다   
풀때마다 문제 정답은 계속해서 바뀌게 된다 아래 이미지를 보면 다시 풀었을 때 문제들의 문항 번호가 변경된 것을 볼 수 있다
<img src="https://github.com/doolybom1/Java_TS_001_CBT/blob/master/images/%EB%AC%B8%EC%A0%9C%ED%92%80%EC%9D%B4.png"> 

20문제를 다 풀거나 중간에 -Q를 입력받게되면 오답리스트와 정답리스트를 보여주게되고 한문제당 5점씩 계산해서 점수를 알려준다
<img src="https://github.com/doolybom1/Java_TS_001_CBT/blob/master/images/%EC%98%A4%EB%8B%B5%EC%A0%95%EB%8B%B5%EB%A6%AC%EC%8A%A4%ED%8A%B8.png">

<b>문제삭제</b>     
삭제할 문제의 seq를 입력하고 존재하지 않은 seq가 있을 시에는 확인하여 존재하지 않는다고 알려주고   
존재하는 seq라면 어떤 문제인지 화면에 보여준다   
<img src="https://github.com/doolybom1/Java_TS_001_CBT/blob/master/images/%EB%AC%B8%EC%A0%9C%EC%82%AD%EC%A0%9C.png">

<b>문제등록</b>   
<img src="https://github.com/doolybom1/Java_TS_001_CBT/blob/master/images/%EB%AC%B8%EC%A0%9C%EB%93%B1%EB%A1%9D.png">

<b>문제수정</b>   
문제 수정시에 enter를 누르면 기존의 데이터를 그대로 업데이트하게 된다   
<img src="https://github.com/doolybom1/Java_TS_001_CBT/blob/master/images/%EB%AC%B8%EC%A0%9C%EC%88%98%EC%A0%95.png">

