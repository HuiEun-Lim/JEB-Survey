# JEB-Survey
## Jetty, Esther, Bonnie의 설문 토이 프로젝트

2021년 하반기 입사 동기 Jetty, Esther, Bonnie가 본사 대기일 때 개발 능력 향상을 위해 설문조사 토이 프로젝트를 협업하여 만듭니다.<br>
팀명 [JEB]은 각자의 닉네임 앞글자를 따왔습니다.

Jetty : HuiEun-Lim / heyho930@gmail.com <br>
Esther : indiaesther / indiaesther@naver.com <br>
Bonnie : Taehyeon130 / taehyeongim59@gmail.com

### 목표
* 직접 필요한 기능들을 분석하고 설계한다.
* 화면 설계를 통해 페이지 디자인과 데이터베이스 컬럼 추출을 한다.
* 데이터 베이스의 설계 및 구현을 한다.
* 설문 조사가 메인이므로 로그인 기능은 로그인과 회원가입만 구현하며 패스워드의 경우 암호화를 하여 저장한다.
* 설문 리스트에서 키워드를 통해 설문 조사를 검색할 수 있다.
* 로그인한 회원이 만든 설문 조사는 별도로 조회할 수 있다.
* 로그인한 회원이 만든 설문 조사는 본인만 수정할 수 있다.
* 로그인한 회원만 설문 조사에 응답할 수 있다.
* 한번 응답한 설문 조사에 재응답할 수 없다.
* 질문 추가는 동적으로 동작한다.
* 설문 조사의 결과는 chart.js로 시각화한다.
* 파견이 예정된 프로젝트에서의 개발 환경과 기술을 능숙하게 사용할 수 있다.

### 사용 기술
* SpringBoot
* Spring Security
* 빌드 관리 도구 : Maven
* DataBase : MSSQL
* Template Engine : Thymeleaf
* Lombok

### 역할 분담
#### Jetty
프로젝트 총괄, 프로젝트 설계 및 환경 세팅, 로고 디자인, 설문 리스트, My Survey, chart.js를 이용한 설문 결과 페이지, 설문 응답 여부 체크, 설문 응답, 메인 화면, Esther와 Bonnie에게 들이대며 각종 오류 잡기

#### Esther
동적 테이블을 이용한 설문지 생성/수정, 설문 삭제, 설문지 CSS, 화면 설계

#### Bonnie
Spring Security를 이용한 로그인/회원가입/로그아웃, 회원여부에 따른 페이지 접속 권한 설정, 패스워드 암호화, 실시간 ID 중복 확인, 설문 응답, 공통 네비게이션 바, 화면 설계
