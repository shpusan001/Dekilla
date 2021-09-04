<p align="center">
<img width=475 src="https://user-images.githubusercontent.com/35298140/131207077-d68d8385-a13d-408f-8095-26707f540ca2.png"></img>
</p>

---

## 개요

Kotlin 기반의 TCP 소켓을 활용한 파일 전송 프로그램 입니다. 서버가 클라이언트에게 할당한 토큰(클라이언트 식별값)
으로 파일 전송의 도착지를 설정합니다.

## 아키텍처
![DekiilaStructure drawio](https://user-images.githubusercontent.com/35298140/132085824-78c0d592-6806-48ec-93c7-b5794e6eef40.png)

---

## 다운로드

클릭하면 다운로드 페이지로 이동합니다

+ 서버
    + [서버프로그램 (jar)](https://drive.google.com/file/d/1OvbpPHu0QUtDIFkL74EKxroOpeQkS9CD/view?usp=sharing)
    + [서버프로그램 (nohup 백그라운드 실행) (jar)](https://drive.google.com/file/d/1bXE7DrSSwmkI7HfkAQl1Ei5ptoTiDKM-/view?usp=sharing)
+ 클라이언트
    + [클라이언트 프로그램 (jar)](https://drive.google.com/file/d/16ndzBfQio9Lr0kct2X1kPdw4hDMq6aSf/view?usp=sharing)
    + [클라이언트 프로그램 (exe)](https://drive.google.com/file/d/1_Z1yycok_xut8fdyaoxYIHjjFkfUVz2o/view?usp=sharing)

> Java 11버전 이상에서 실행됩니다.   
> 서버프로그램 (nohup 백그라운드 실행)은 **포트가 자동으로 55555로 지정**되어 서버가 실행됩니다.

**2021-08-29(일)**

+ 한 클라이언트에서 두개이상의 다운로드가 일어나면 종료되는 문제 수정
+ 한 클라이언트에서 다른 클라이언트로 파일을 전송하고 있을 때, 도중에 둘중 하나의 클라이언트가 종료될 때 비정상처리 되는 문제 수정
+ 클라이언트 UI 중 'sendFile' -> 'Send file' 수정
+ 파일 전송 버퍼 크기 4배 증가 ( 8kbyte -> 32kbyte )

---

## 사용법

### 서버 세팅

1. 서버 프로그램을 실행한다.
2. 포트를 입력한다.
3. 서버 프로그램 세팅 완료  
   <img width="400" alt="serversetting01" src="https://user-images.githubusercontent.com/35298140/131212433-3172cc81-3b43-45d5-9ac4-dcffd1f61b20.PNG">

### 클라이언트 접속

1. 클라이언트 프로그램을 실행한다.
2. 호스트(서버의 아이피)와 포트를 입력한다. (서버프로그램에서 설정한 포트사용)  
   <img width="400" alt="dekillaclient01" src="https://user-images.githubusercontent.com/35298140/131212504-10b4b69a-5868-424b-a025-3ca5cc6018b6.PNG">
3. 클라이언트 프로그램 접속완료

### 클라이언트 사용

  <img width="500" alt="dekillaclient02" src="https://user-images.githubusercontent.com/35298140/131212974-c5c4b0f3-39bc-45b2-93ff-a1f580659828.png">

+ **구성요소**

> + My token : 이 클라이언트 고유의 토큰값
> + Download folder : 파일들이 다운로드 될 폴더
> + Target token : 파일 전송 대상 클라이언트 토큰값
> + File to send : 전송 예정 파일

+ **파일 전송 대상 클라이언트 연결**
    1. Target token 을 입력하고 connect를 클릭한다.
  > <img width="400" alt="dekillaclient03" src="https://user-images.githubusercontent.com/35298140/131213380-80e274a6-0896-4f6b-a1dc-ba1d486ea4f9.PNG">
  >
  > 1.상대는 위와같은 알림을 받게된다.  
  > 2.'Yes'를 누르면 연결을 요청한 상대 클라이언트와 연결된다.

    2. 연결 성공  
       <img width="400" alt="dekillaclient04" src="https://user-images.githubusercontent.com/35298140/131213485-799e108b-c295-40fd-8943-adde027f272a.PNG">
+ **파일 전송**
    1. 필요한 요소를 모두 설정하고 Send file을 누른다  
       <img width="500" alt="dekillaclient05" src="https://user-images.githubusercontent.com/35298140/131213512-5da4b0b8-0a87-4b91-8cf7-8adfaa14f23c.PNG">
    2. 파일 전송 성공  
       <img width="500" alt="dekillaclient06" src="https://user-images.githubusercontent.com/35298140/131213579-00614dbc-b59e-4b82-b1d0-afaf743599ad.PNG">

  > '내 클라이언트'가 연결을 요청한 상대에게 파일을 전송할 수 있다. 연결된 '상대 클라이언트'는
  > '내 클라이언트'로 파일을 전송할 수 없다. 상대가 나에게 파일을 전송하고 싶다면, 상대도
  > 나에게 연결요청을 해야한다.

---
Copyright 2021.SanghoonLee.All rights reserved.
