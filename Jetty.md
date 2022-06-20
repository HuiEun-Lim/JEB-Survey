# Jetty 

## 담당 역할
프로젝트 총괄, 프로젝트 설계 및 환경 세팅, 로고 디자인, 설문 리스트, My Survey, chart.js를 이용한 설문 결과 페이지, 설문 응답 여부 체크, 설문 응답, 메인 화면, Esther와 Bonnie에게 들이대며 각종 오류 잡기


## 1. 프로젝트 구상
본격적인 프로젝트에 앞서 구글 문서에 대략적인 프로젝트 구상을 하고 동기들에게 공유를 했습니다.
![image](https://user-images.githubusercontent.com/81278806/174548964-be155776-f349-4d5c-a3c0-2ac07e209713.png)

역할도 미리 만들어 놓고 각자 하고 싶은 파트를 담당하였습니다.
![image](https://user-images.githubusercontent.com/81278806/174549293-70ace43c-9549-49cc-a587-d9116c4b27a6.png)

## 2. 화면 설계
DataBase 컬럼 추출을 위해선 화면 설계가 필요하다는 판단을 하여 각자 맡은 파트에 대한 화면을 설계하였습니다.
화면 설계서의 원본은 project_design 폴더에서 확인할 수 있습니다.

![image](https://user-images.githubusercontent.com/81278806/174549602-b41037ed-24be-4092-95e8-0417a07529fa.png)
![image](https://user-images.githubusercontent.com/81278806/174549657-2ce378db-5113-41fc-b63f-5274c41ac43d.png)
![image](https://user-images.githubusercontent.com/81278806/174549687-3588a6bc-fdcd-4e48-b22f-c8cebd54898f.png)


## 3. DB 설계
논리적 설계를 erd로 구현한 후 동기들에게 설명을 하며 부족한 점을 메꾸는 형식으로 진행했습니다.
![image](https://user-images.githubusercontent.com/81278806/174549871-6d23371d-de7e-493e-9614-cd62b6c27a2d.png)

DB 테이블 기술서를 만들어 논리적/물리적 DB를 정의하고 DDL을 작성하여 후에 참고 및 사용을 할 수 있게 만들었습니다.
또한 각자 트랜잭션 시나리오대로 쿼리를 사전에 작성하게 한 후 도움이 필요한 부분이나 부족한 부분을 보충해주었습니다.
DB 테이블 기술서도 project_design 폴더에서 확인할 수 있습니다.
![image](https://user-images.githubusercontent.com/81278806/174550338-ee271def-0be3-4b94-a1d7-80a96a706be7.png)

## 4. 기능 구현
### 1. 설문 리스트
등록된 설문 조사들을 조회할 수 있는 페이지입니다.
제목/등록자(닉네임)/설문 번호로 검색을 할 수 있으며 한 페이지에 몇개의 설문 조사를 볼 수 있는지 설정도 가능합니다.
![image](https://user-images.githubusercontent.com/81278806/174551101-800545a8-fc5a-4eb9-8880-56903b43b70b.png)
![image](https://user-images.githubusercontent.com/81278806/174551602-c8ec187c-d9d1-4a78-a185-aee8d5a45420.png)
![image](https://user-images.githubusercontent.com/81278806/174551712-ecb388e9-31b1-4287-932c-eeff681997ad.png)

### 2. 설문 상세
설문 리스트에서 설문 제목을 클릭할 경우 설문 상세 페이지로 이동합니다.
이 곳에서 이 설문 조사는 무슨 설문 조사인지 확인할 수 있으며 응답할 수 있습니다.
목록 보기를 클릭할 경우 기존 페이지와 검색 결과가 유지된 설문 리스트로 이동합니다.
![image](https://user-images.githubusercontent.com/81278806/174556358-830a18ab-b7cb-4432-af91-bd5acfac2e20.png)

### 3. 설문 응답
설문 상세 페이지에서 응답하기를 클릭한다면
기존에 응답하지 않은 설문 조사의 경우 설문 응답하기 페이지로 이동합니다.
![image](https://user-images.githubusercontent.com/81278806/174556655-56cd08dc-120c-49ad-aaf4-d748cfe1a893.png)

모든 문항에 답변을 한 후 제출하기를 누르면 설문 조사의 답변이 저장됩니다. (Bonnie와 공동 제작)
![image](https://user-images.githubusercontent.com/81278806/174557242-dd58abaa-9d7d-4ccf-abe8-cf4cd09ca411.png)
![image](https://user-images.githubusercontent.com/81278806/174557519-9fc58327-f27b-4529-aa7f-28adb252e5b0.png)

이미 응답을 한 설문이라면 다음과 같은 알림이 뜨고 설문에 응답할 수 없습니다.
![image](https://user-images.githubusercontent.com/81278806/174557642-bb303a89-915c-4c8c-9423-963edbf4bad3.png)

### 4. My Survey
접속한 회원이 만든 설문 조사들을 조회할 수 있는 페이지입니다.
설문 리스트와 동일하게 검색이 가능합니다.
설문 제목을 누르면 설문 조사를 수정 및 삭제할 수 있는 페이지(Esther 제작)로 이동하며
우측의 결과 보기 버튼을 클릭할 경우 설문 조사의 결과를 볼 수 있는 결과 페이지로 이동합니다.
![image](https://user-images.githubusercontent.com/81278806/174559236-9dc2eb65-c004-4b58-a69d-960727337332.png)

### 5. 설문 결과
My Survey에서 결과 보기 버튼을 클릭하면 설문 조사의 결과를 알 수 있습니다.
상단에는 설문의 기본적인 정보가 출력됩니다.
목록 보기를 클릭시 My Survey에 기존 검색 키워드와 페이지가 유지된 상태로 이동됩니다.

* 단답형의 경우 리스트로 출력이 되며 우측에 답변 수가 표현되기 때문에 중복된 답변이 개별적으로 출력되지 않습니다.
* 장문형의 경우 단답형과 동일하게 리스트로 출력되지만 중복된 답변이 거의 없을 것이라는 판단 하에 중복된 답변 수를 적지 않았습니다.
* 드롭다운과 객관식의 경우 파이차트 형태로 시각화하였습니다.
* 체크박스의 경우 막대 그래프로 시각화하였습니다.
![image](https://user-images.githubusercontent.com/81278806/174559928-4e475a5f-49a0-4630-89e6-f2ef236be217.png)
![image](https://user-images.githubusercontent.com/81278806/174560012-c1d5b5f3-d706-4f77-b03b-373061cab7a6.png)
![image](https://user-images.githubusercontent.com/81278806/174560240-eb687532-7476-4b7e-8135-7faa6dddd401.png)
![image](https://user-images.githubusercontent.com/81278806/174560274-ce0780da-6103-4545-b168-e0909c4fa005.png)

### 6. 메인
로그인을 하지 않은 상태로 메인을 방문한다면 다음과 같은 화면이 출력됩니다.
![image](https://user-images.githubusercontent.com/81278806/174560562-511cbf38-3da7-4283-90b9-da22d4cce605.png)

회원으로 메인에 접속한다면 회원의 닉네임과 함께 다음과 같이 출력됩니다.
![image](https://user-images.githubusercontent.com/81278806/174560609-2d2d0e81-60fc-4451-883c-25a0c86de6d3.png)

## 5. 로고 디자인
조금이나마 있어보이게 만들기 위하여 급하게 뚝딱뚝딱 만든 로고입니다.

![logo_black](https://user-images.githubusercontent.com/81278806/174561158-4fee48e3-8fe3-43bb-8f93-abdd18bfabc5.png)

![logo_name_black](https://user-images.githubusercontent.com/81278806/174561138-783b4ce5-bc1e-43bc-aef9-abe252de3974.png)

![main_logo](https://user-images.githubusercontent.com/81278806/174561122-810c25cc-f150-4e7b-8466-801a8a4b014b.png)





