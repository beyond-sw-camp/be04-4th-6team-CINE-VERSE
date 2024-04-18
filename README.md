# 🚌CINE-VERSE🎥 프로젝트

### 🚍팀명: 시내버스

<img src="https://github.com/beyond-sw-camp/be04-4th-6team-CINE-VERSE/assets/153909291/a755affb-6a73-4660-957a-fd26066a2099" width="50%" />

### 🚌팀원

- 팀원 : **송동준**
[<img src="https://img.shields.io/badge/Github-Link-181717?logo=Github">](https://github.com/dongjunsong)

- 팀원 : **양지혜**
[<img src="https://img.shields.io/badge/Github-Link-181717?logo=Github">](https://github.com/Jihye1101)

- 팀원 : **윤재은**
[<img src="https://img.shields.io/badge/Github-Link-181717?logo=Github">](https://github.com/yunjaeeun)

- 팀원 : **조수빈**
[<img src="https://img.shields.io/badge/Github-Link-181717?logo=Github">](https://github.com/chosoobin37)

---

# 🌀WBS

<details><summary>WBS</summary>
<p>
<img src="https://github.com/beyond-sw-camp/be04-4th-6team-CINE-VERSE/assets/153909291/a673d5e7-8022-47fe-a36a-d011a3a64bb0" width="100%" />
</p>
</details>

# 📌영화 마니아들을 위한 전문 커뮤니티 서비스 기획서

## 1. 서비스 기획 배경

- **현 상황**: <br> 마니아를 위한 영화 커뮤니티의 부재. <br> 대형 플랫폼의 영화 카페는 폐쇄되었고, 기존 사이트들은 화면이 복잡하고 다양한 주제를 아우르고 있어 영화만을 전문으로 다루는 공간이 필요.
- **필요성**: <br> 영화 마니아들이 자신의 영화 취향을 공유하고, 심도 깊은 토론을 할 수 있는 전문 커뮤니티의 필요성이 대두됨.

## 2. 서비스 목표

- **전문성과 편안함**: <br> 영화만을 전문으로 다루는 커뮤니티 제공으로 사용자들이 보다 쉽게 정보를 얻고 공유할 수 있는 플랫폼 마련.
- **안전한 이용 환경**: <br> 서버에 위험한 정보를 저장하지 않고, 이용자의 개인정보 보호를 최우선으로 함(예: 쿠키에 회원들의 개인정보를 담지 않음).
- **활발한 소통 공간**: <br> 사용자들이 자유롭게 정보를 공유하고, 영화에 대한 리뷰나 비평을 자유롭게 표현할 수 있는 오픈형 커뮤니티.

## 3. 서비스 차별화 전략

- **포인트 시스템**: <br> 영화 퀴즈 참여를 통해 포인트를 획득하고, 이 포인트를 사용하여 다양한 뱃지(예: 마블, DC 코믹스 뱃지)를 구매할 수 있음.
- **뱃지 활용**: <br> 구매한 뱃지는 사용자의 프로필 또는 닉네임 옆에 표시되어, 사용자의 관심사와 전문성을 나타낼 수 있음. 이는 사용자간의 소속감을 형성하고, 커뮤니티 내에서의 신뢰도를 증가시키는 역할을 함.

## 4. 기대 효과

- **전문화된 플랫폼**: <br> 영화만을 주제로 하는 전문화된 커뮤니티로서의 자리매김.
- **사용자 참여 유도**: <br> 포인트 및 뱃지 시스템을 통한 게임화 요소는 사용자의 지속적인 참여를 유도.
- **커뮤니티 활성화**: <br> 특정 주제에 집중함으로써 사용자간의 더 깊은 연결과 상호 작용을 촉진.

#### 이러한 사이트는 영화 마니아들에게 맞춤화된 정보를 제공하며, 안전하고 편안한 환경에서 영화에 대한 열정을 공유할 수 있는 최적의 공간이 될 것입니다.
#### 퇴근 후 🚌시내버스 타고 🎥영화 한 편 어때요?

---

# ✅추가 기능 설명서

## 1. CGV 실시간 인기 영화 순위 크롤링

Python을 사용하여 requests와 BeautifulSoup 라이브러리로 CGV에서 영화 데이터(실시간 인기 영화 순위 10)를 크롤링하고 json으로 저장
메인 화면에 들어가면 사용자는 실시간 영화 정보를 통해 최신 트렌드를 파악 가능

중요 코드:
```
url = 'http://www.cgv.co.kr/movies/'
res = requests.get(url)
soup = BeautifulSoup(res.text, 'html.parser')
```

## 2. OpenAI API를 이용한 챗봇 만들기
![API request 조회 가능](https://github.com/beyond-sw-camp/be04-4th-6team-CINE-VERSE/assets/153909291/fec60701-b45c-4134-9698-ab42b216d66d)

JavaScript를 사용하여 OpenAI의 API로부터 챗봇의 응답을 받아 처리하는 기능 구현
api 키는 .env 환경변수 처리를 통해 외부에 노출되지 않도록 보안 처리
영화 추천 등 다양한 주제에 대한 빠르고 효율적인 대화 가능
https://platform.openai.com/usage에서 API request 조회 가능


중요 코드:

```
async function sendMessage() {
  const message = userMessage.value.trim();
  if (message.length === 0) return;
```

---

# ⚙DB모델링

<details><summary>1. 개념모델링</summary>
<p>
<img src="https://github.com/beyond-sw-camp/be04-4th-6team-CINE-VERSE/assets/153909291/7ea5b6fe-fa98-4051-bf12-89e3fcb3619d" width="100%" />
</p>
</details>

<details><summary>2. 논리모델링</summary>
<p>
<img src="https://github.com/beyond-sw-camp/be04-4th-6team-CINE-VERSE/assets/153909291/239890fa-ea13-4a76-aa86-eb3d9cc2ab6f" width="100%" />
</p>
</details>

<details><summary>3. 물리모델링</summary>
<p>
<img src="https://github.com/beyond-sw-camp/be04-4th-6team-CINE-VERSE/assets/153909291/d5038827-8df7-4001-92fe-d352f35009b2" width="100%" />
</p>
</details>

---

# 💻와이어 프레임

<details><summary>1. 메인 - 회원 관련 페이지 (로그인)</summary>
<p>
<img src="https://github.com/beyond-sw-camp/be04-4th-6team-CINE-VERSE/assets/153909291/679f6dec-e11e-4897-9d39-4417b851e565" width="100%" />
</p>
</details>

<details><summary>2. 메인 - 회원프로필 및 뱃지 페이지</summary>
<p>
<img src="https://github.com/beyond-sw-camp/be04-4th-6team-CINE-VERSE/assets/153909291/9c09016e-96ff-4184-ac3b-6a202692538a" width="100%" />
</p>
</details>

<details><summary>3. 메인 - 최신 이벤트, 자유 게시글</summary>
<p>
<img src="https://github.com/beyond-sw-camp/be04-4th-6team-CINE-VERSE/assets/153909291/c10de869-47e1-4d35-bc33-c76a6d587089" width="100%" />
</p>
</details>

<details><summary>4. 메인 - 게시판</summary>
<p>
<img src="https://github.com/beyond-sw-camp/be04-4th-6team-CINE-VERSE/assets/153909291/5e583639-7a18-49fe-a63b-8a2e62d9747f" width="100%" />
</p>
</details>

<details><summary>5. 이벤트 게시판</summary>
<p>
<img src="https://github.com/beyond-sw-camp/be04-4th-6team-CINE-VERSE/assets/153909291/7e6bdafc-b561-4043-b41c-5affb629d368" width="100%" />
</p>
</details>

<details><summary>6. 리뷰 게시판</summary>
<p>
<img src="https://github.com/beyond-sw-camp/be04-4th-6team-CINE-VERSE/assets/153909291/d36c26a7-e587-4e40-9d15-5d87df9fcd03" width="100%" />
</p>
</details>

<details><summary>7. 정보 게시판</summary>
<p>
<img src="https://github.com/beyond-sw-camp/be04-4th-6team-CINE-VERSE/assets/153909291/41522f9b-ebe3-4446-b5f1-dda170a25368" width="100%" />
</p>
</details>

<details><summary>8. 자유 게시판</summary>
<p>
<img src="https://github.com/beyond-sw-camp/be04-4th-6team-CINE-VERSE/assets/153909291/e77a0dd9-150c-4288-af70-681dc2b992f6" width="100%" />
</p>
</details>

<details><summary>9. 문의 게시판</summary>
<p>
<img src="https://github.com/beyond-sw-camp/be04-4th-6team-CINE-VERSE/assets/153909291/08a0bbe4-3945-414f-ae53-95a32426508c" width="100%" />
</p>
</details>

---

# 📚기능/요구사항/테스트 명세서

<details><summary>1. 기능 명세서</summary>
<p>
<img src="https://github.com/beyond-sw-camp/be04-4th-6team-CINE-VERSE/assets/153909291/20829c20-0880-4ddf-9dfa-3a600ad01b67" width="100%" />
</p>
</details>


<details><summary>2. 요구사항 정의서</summary>
<p>
<img src="https://github.com/beyond-sw-camp/be04-4th-6team-CINE-VERSE/assets/153909291/526afefb-5e1a-4dd9-99db-b2bbe0c9af58" width="100%" />
</p>
</details>

<details><summary>3. 테스트 명세서</summary>
<p>
<img src="https://github.com/beyond-sw-camp/be04-4th-6team-CINE-VERSE/assets/153909291/461a5bbc-b191-4100-b8e6-f3cbea2b4fbb" width="100%" />
</p>
</details>

---

# 🅰Api 명세서
<details><summary>1. 리뷰(게시글, 댓글)</summary>

![Swagger Editor_page-0001](https://github.com/beyond-sw-camp/be04-3rd-TeamPhoenix-ahub/assets/113569573/3e6bf027-0d6a-4978-968a-53c7ac4e5e26)

</details>
<details><summary>2. 신고, 포인트, 회원</summary>
  
![Swagger Editor_page-0002](https://github.com/beyond-sw-camp/be04-3rd-TeamPhoenix-ahub/assets/113569573/04651a27-575d-405a-9d67-276da2869e9c)

</details>
<details><summary>3. 정보(게시글, 댓글), 자유(게시글, 댓글)</summary>
  
![Swagger Editor_page-0003](https://github.com/beyond-sw-camp/be04-3rd-TeamPhoenix-ahub/assets/113569573/f99a45af-36d9-4900-8fd2-957f7963a522)

</details>
<details><summary>4. 자유(게시글), 이벤트(게시글), 이메일, 뱃지</summary>
  
![Swagger Editor_page-0004](https://github.com/beyond-sw-camp/be04-3rd-TeamPhoenix-ahub/assets/113569573/d3d3c429-8a66-487e-8c19-d6a52ad97ad8)

</details>

<details><summary>5. 게시글 좋아요</summary>
  
![Swagger Editor_page-0005](https://github.com/beyond-sw-camp/be04-3rd-TeamPhoenix-ahub/assets/113569573/cf4b5914-31e5-4d39-b827-dff92946ada5)

</details>

<details><summary>6. 스키마</summary>
  
![Swagger Editor_page-0006](https://github.com/beyond-sw-camp/be04-3rd-TeamPhoenix-ahub/assets/113569573/b11e27c2-09f5-4e38-95ff-aaf423645079)
![Swagger Editor_page-0007](https://github.com/beyond-sw-camp/be04-3rd-TeamPhoenix-ahub/assets/113569573/da423192-22f1-49e0-a882-e2376ec2405f)

</details>

---

# ✔백엔드 테스트

<details>
<summary>1. 회원 </summary>

![백엔드테스트10](https://github.com/beyond-sw-camp/be04-4th-6team-CINE-VERSE/assets/153909291/cf12a37e-64ef-4c97-bb5d-8c38e892582b)

</details>

<details>
<summary>2. 정보 게시글 </summary>
  
![백엔드테스트8](https://github.com/beyond-sw-camp/be04-4th-6team-CINE-VERSE/assets/153909291/ab0fcd21-8097-497c-9a04-914c26bc6a49)

</details>

<details>
<summary>3. 정보 게시글 댓글</summary>
  
![백엔드테스트9](https://github.com/beyond-sw-camp/be04-4th-6team-CINE-VERSE/assets/153909291/f54bd183-6f90-4a8c-b157-749dbc3861b3)

</details>

<details>
<summary>4. 자유 게시글 </summary>

![백엔드테스트7](https://github.com/beyond-sw-camp/be04-4th-6team-CINE-VERSE/assets/153909291/e9023879-71d0-4c33-a820-1260d77cf45f)

</details>

<details>
<summary>5. 자유 게시글 댓글 </summary>
  
![백엔드테스트6](https://github.com/beyond-sw-camp/be04-4th-6team-CINE-VERSE/assets/153909291/b8b612a7-0a78-4458-ab94-9f42cffe94dd)

</details>

<details>
<summary>6. 리뷰 게시글 </summary>
  
![백엔드테스트1](https://github.com/beyond-sw-camp/be04-4th-6team-CINE-VERSE/assets/153909291/687fcb2c-7cae-46ab-8172-0262a9ff70e1)

</details>

<details>
<summary>7. 리뷰 게시글 댓글 </summary>

![백엔드테스트2](https://github.com/beyond-sw-camp/be04-4th-6team-CINE-VERSE/assets/153909291/96424f1e-e733-42cb-b73f-7ea32e085a67)

</details>

<details>
<summary>8. 이벤트 게시글 </summary>

![백엔드테스트4](https://github.com/beyond-sw-camp/be04-4th-6team-CINE-VERSE/assets/153909291/79c7707f-aa47-4967-ae44-7f7d1efff783)

</details>

<details>
<summary>9. 뱃지 </summary>

![백엔드테스트3](https://github.com/beyond-sw-camp/be04-4th-6team-CINE-VERSE/assets/153909291/aff0b53c-9bc9-4e45-876e-eec9de636543)

</details>

<details>
<summary>10. 신고 </summary>

![백엔드테스트4](https://github.com/beyond-sw-camp/be04-4th-6team-CINE-VERSE/assets/153909291/79c7707f-aa47-4967-ae44-7f7d1efff783)

</details>


# ✨서비스 테스트 결과 영상

## 메인

<details>
<summary>메인페이지 라우터 </summary>

![메인페이지-라우터-테스트](https://github.com/beyond-sw-camp/be04-4th-6team-CINE-VERSE/assets/153909291/f2e0df14-21b5-401a-a3de-72ba0381be48)

</details>

## 챗봇

<details>
<summary>챗봇 </summary>

![챗봇-테스트](https://github.com/beyond-sw-camp/be04-4th-6team-CINE-VERSE/assets/153909291/dabf9559-90d2-449c-aa08-ad64fa271ed3)

</details>

<details>
<summary>회원<br></summary>

<details>
<summary>회원가입 </summary>
  
![회원가입-테스트](https://github.com/beyond-sw-camp/be04-4th-6team-CINE-VERSE/assets/153909291/8c69e09b-54e7-44e2-81fd-cd706073f6dc)

</details>

<details>
<summary>아이디, 비밀번호 찾기 </summary>

<!-- 여기에 내용을 넣으세요 -->

</details>

<details>
<summary>로그인 로그아웃 </summary>

![로그인로그아웃-테스트](https://github.com/beyond-sw-camp/be04-4th-6team-CINE-VERSE/assets/153909291/0fbae4fc-c65a-4913-ac97-1a73e897692a)

</details>

<details>
<summary>회원정보 수정 </summary>

![회원정보수정-테스트](https://github.com/beyond-sw-camp/be04-4th-6team-CINE-VERSE/assets/153909291/d7c9296e-f268-4897-a08f-ebdd0e1bef8c)

</details>

<details>
<summary>회원프로필 확인 </summary>

![유저프로필-테스트](https://github.com/beyond-sw-camp/be04-4th-6team-CINE-VERSE/assets/153909291/372eeac5-4f7c-4305-aab4-284718326b47)

</details>

<details>
<summary>회원 탈퇴 </summary>

<!-- 여기에 내용을 넣으세요 -->

</details>

</details>


## 뱃지
<details>
<summary>뱃지 </summary>

![뱃지-테스트](https://github.com/beyond-sw-camp/be04-4th-6team-CINE-VERSE/assets/153909291/821a4331-f081-4573-8b8b-77b275b29db8)

</details>


## 정보 자유 게시판(정보랑 리뷰 게시판은 형식 똑같아서 정보만 작성)
<details>
<summary>게시글 전체 조회 단일 조회</summary>
  
![게시글 전체 조회 단일 조회](https://github.com/beyond-sw-camp/be04-3rd-TeamPhoenix-ahub/assets/113569573/88094177-9b70-49e2-b1af-bb3923ff8cc1)

</details>

<details>
<summary>정보 게시글 작성 </summary>

  ![정보 게시물 작성](https://github.com/beyond-sw-camp/be04-3rd-TeamPhoenix-ahub/assets/113569573/a241020e-b393-467b-80ae-8e2a10d4ccb0)

</details>

<details>
<summary>정보 게시글 삭제 </summary>

![정보 게시물 삭제](https://github.com/beyond-sw-camp/be04-3rd-TeamPhoenix-ahub/assets/113569573/44198048-2b47-4905-b323-49a6535c3945)

</details>

<details>
<summary>정보 게시글 검색</summary>


![정보 게시물 검색](https://github.com/beyond-sw-camp/be04-3rd-TeamPhoenix-ahub/assets/113569573/c7d66ab2-7410-4b6f-9128-5f5e7963ac08)

</details>
<details>
<summary>자유 게시글 작성</summary>

![자유 게시물 작성](https://github.com/beyond-sw-camp/be04-3rd-TeamPhoenix-ahub/assets/113569573/68edf6d6-9dd8-49f1-8abe-98aecd5110d9)
  
</details>

<details>
<summary>자유 게시글 삭제</summary>

![자유게시글 삭제](https://github.com/beyond-sw-camp/be04-3rd-TeamPhoenix-ahub/assets/113569573/2d91542f-aeeb-46af-a5b4-1d4769affde0)
  
</details>

<details>

<summary>자유 게시글 검색</summary>

![자유 게시글 검색](https://github.com/beyond-sw-camp/be04-3rd-TeamPhoenix-ahub/assets/113569573/727baa9d-6030-45ec-a1b9-4d9e3dd3eb63)

</details>

## 이벤트 게시판
<details>

<summary>이벤트 게시글 이동 전체 조회 단일 조회</summary> 

![이벤트 게시글 이동 전체 조회 단일 조회](https://github.com/beyond-sw-camp/be04-3rd-TeamPhoenix-ahub/assets/113569573/7ce8d265-3082-48a6-8778-9f7c53ce5ab1)

</details>

<details> 
  
<summary>이벤트 게시판 작성</summary> 

![이벤트 작성](https://github.com/beyond-sw-camp/be04-3rd-TeamPhoenix-ahub/assets/113569573/eac92e3b-e6ce-45bd-b8e8-9f837ca8ef06)

</details>

<details>

<summary>이벤트 게시판 삭제</summary>

![이벤트 게시글 삭제](https://github.com/beyond-sw-camp/be04-3rd-TeamPhoenix-ahub/assets/113569573/19c4e1bc-15a2-49a8-916b-d731eb3022bc)

</details>

<details>

<summary>이벤트 게시판 참여</summary>


![이벤트 참여](https://github.com/beyond-sw-camp/be04-3rd-TeamPhoenix-ahub/assets/113569573/ff124f91-bf94-4e3b-a576-229ee3059aa8)


</details>

## 댓글

<details>

<summary> 댓글 작성 </summary>

![댓글 작성](https://github.com/beyond-sw-camp/be04-3rd-TeamPhoenix-ahub/assets/113569573/47b6aa50-8b5c-4200-beb4-7b333bc858e4)

</details>

<details>

 <summary> 댓글 삭제 </summary>

![댓글 삭제](https://github.com/beyond-sw-camp/be04-3rd-TeamPhoenix-ahub/assets/113569573/dff38ba4-8f92-49de-a680-6342252f9ad8)
 
</details>

---

<img src="https://capsule-render.vercel.app/api?type=waving&color=0:5ecfa4,100:57b762&height=200&text=CICD&animation=twinkling&fontColor=ffffff&fontSize=50" width="100%" />
CINE-VERSE 프로젝트의 Jenkins 파이프라인을 통한 CI/CD 환경 설정 문서입니다.

# 🐳CICD 아키텍처
<img src="https://github.com/beyond-sw-camp/be04-4th-6team-CINE-VERSE/assets/153909291/04995643-7e9d-4f6a-98fe-b417f64a86d6" width="100%" />

---

# 📎전제 조건 및 설치 환경
이 프로젝트를 시작하기 전에 다음 소프트웨어와 도구가 설치되어 있어야 합니다.

- **IntelliJ**: JDK 17 설치 필요
- **Docker**: 버전 4.28
- **Jenkins**: 버전 2.452
- **Kubernetes**: 버전 1.92.2
- **Ngrok**
- **Webhook**: GitHub와 연동 설정

---

# 🔧Credential 설정
안전한 개발을 위해 다음 크리덴셜을 설정합니다.

1. **MY-GITHUB-KEY**: RSA 키
2. **DOCKERHUB-PASSWORD**: Docker Hub 업로드를 위한 계정 정보
3. **SECRET-ID**: GitHub에 업로드되면 안 되는 `application.yml`

---

# 🔗Jenkins Pipeline 설정
## Git 저장소와 Webhook 연결
- 변경사항 감지 후 Jenkins가 자동으로 빌드를 시작합니다.

## RSA 키 생성
- Jenkins가 Webhook을 통해 변경된 소스 코드를 감지하고, SSH에 사용될 RSA 키를 생성합니다.

## Pipeline 생성 및 설정
- Jenkins Tools에서 `Java (OpenJDK 17)` 및 `Gradle (ver 8.7)` 설정

## Plugin 설치
- `Publish Over SSH` 및 `Pipeline: Stage View` 설치

## Pipeline 스크립트
```groovy
pipeline {
    agent any
    tools {
        gradle 'gradle'
        jdk 'openJDK17'
    }
    environment {
        DOCKERHUB_USERNAME = 'angelajsb'
        GITHUB_URL = 'https://github.com/beyond-sw-camp/be04-4th-6team-CINE-VERSE-CICD.git'
    }
    stages {
        stage('Preparation') {
            steps {
                script {
                    sh 'docker --version' // Docker가 설치되어 있는지 확인
                }
            }
        }
        stage('Source Build') {
            steps {
                git branch: 'main', url: 'https://github.com/beyond-sw-camp/be04-4th-6team-CINE-VERSE-CICD.git'
                
                withCredentials([file(credentialsId: 'secret-id', variable: 'applicationYml')]) {
                    script {
                        if (fileExists('src/main/resources/application.yml')) {
                            sh 'rm src/main/resources/application.yml' // 파일이 존재하면 삭제
                        }
                        sh "cp $applicationYml src/main/resources/application.yml"
                    }
                }
                
                sh "chmod +x ./gradlew"
                sh "./gradlew clean build"
            }
        }
        stage('Container Build') {
            steps {
                sh "cp ./build/libs/*.jar ."
    
                sh "docker build -t ${DOCKERHUB_USERNAME}/cine-verse:latest ."

                withCredentials([usernamePassword(credentialsId: 'DOCKERHUB_PASSWORD', usernameVariable: 'DOCKERHUB_USER', passwordVariable: 'DOCKERHUB_PASS')]) {
                    sh "echo $DOCKERHUB_PASS | docker login --username $DOCKERHUB_USER --password-stdin"
                    sh "docker push ${DOCKERHUB_USERNAME}/cine-verse:latest"
                }
            }
        }
    }
}
```

## Jenkins Pipeline 실행 과정
1. 변경 코드 GitHub에 업로드
2. Webhook 통해 변화 감지
3. 감지된 코드를 SSH를 통해 보안 상태로 Jenkins에서 클론
4. 사전에 Credential에 등록한 `application.yml`을 Pipeline 스크립트에 작성된 Groovy 문법을 통해 다운로드
5. 빌드 및 테스트 실행(자동화 과정)
6. 빌드된 JAR 파일을 통해 이미지 생성 후 Docker Hub에 푸시

## deployment.yml 생성
```
apiVersion: apps/v1
kind: Deployment
metadata:
  name: boot002dep
spec:
  selector:
    matchLabels:
      app: boot002kube
  replicas: 3
  template:
    metadata:
      labels:
        app: boot002kube
    spec:
      containers:
        - name: boot-container
          image: angelajsb/cine-verse:latest    # DockerHub에 푸쉬된 가장 최근 image 사용
          imagePullPolicy: Always    
          ports:
            - containerPort: 8081        # 현재 boot project 포트번호
```

## service.yml
```
apiVersion: v1
kind: Service
metadata:
  name: boot002ser
spec:
  type: NodePort
  ports:
    - port: 8081                        # container 내부 통신 포트번호
      targetPort: 8081                  # 현재 service 중인 포트번호
      protocol: TCP
      nodePort: 30007                   # 외부 접속 포트번호
  selector:
    app: boot002kube
```

## Kubernetes
1. deployment.yml -> pod를 생성하고 관리하는 정보 정의
2. replicas -> 설정한 갯수에 따라 pod instance 유지, 과부하가 걸리거나 실패한 pod를 자동 재시작 해 항상 n개의 pods 유지
3. service.yml -> pod에 대한 단일 접점 제공 및 내부적으로 로드 밸런싱 수행
4. deployment.yml 및 service.yml 적용
        kubectl apply -f boot002dep.yml
        kubectl apply -f boot002ser.yml
5. 소스코드 변경 시 Kubernetes 재시작
        kubectl rollout restart deployment/boot002dep
        

## Vue CI/CD
1. 전제 조건 및 설치 환경
    1. VS CODE 설치
    2. VUE3 설치
    3. Docker Desktop 설치

2. vue project 경로 터미널에서 해당 project image 생성
```
docker build -t angelajsb/cine-verse-front .
```

3. DockerHub에 해당 image 푸쉬
```
docker push angelajsb/cine-verse-front
```

4. deployment.yml 생성

5. service.yml 생성

6. deployment.yml 및 service.yml 적용 (명령어 입력)

```
kubectl apply -f vue002dep.yml
kubectl apply -f vue002ser.yml
```

7. 소스코드 변경 시 이미지 재생성 후 DockerHub 푸쉬
```
docker build -t angelajsb/cine-verse-front:latest .
docker push angelajsb/cine-verse-front:latest
```

8. Kubernetes 재시작
```
kubectl rollout restart deployment/vue002dep
```

--- 

# ✏프로젝트 회고

| 이름 | 내용 |
| ----- | ----------|
|  송동준 | 이번 프로젝트에선 지금까지 배웠던 지식들을 바탕으로 DDD로 게시판 서비스 구조 등을 기획하고 DB 모델링 와이어 프레임 등으로 데이터 베이스 구조 인터페이스 디자인을 설계한 후 백 앤드에서 비즈니스 로직을 구현하면 프런트엔드에서 사용자 인터페이스를 구현한 뒤 CI/CD로 배포까지 하는 과정을 수행하였다 처음부터 끝까지 짧은 시간 내에 게시물을 구현해야 했기에 시간적 부담감도 있었으나 오래돼서 익숙지 않은 부분을 복습할 수 있어 귀중한 경험이 되었다 이번 프로젝트를 하면서 많이 부족함을 느꼈으나 더 잘할 수 있다는 자신감이 생기고 많은 것을 얻어 간 프로젝트였다 다음 프로젝트는 부족한 부분을 보완하고 백엔드와 프런트엔드의 원활한 의사소통을 통해 더 효율적이고 정확한 프로젝트를 만들어야겠다. |
|  양지혜 | 사용자 중심 설계(UCD)의 중요성을 고려하면서, 사용자의 만족을 우선으로 생각하는 서비스 개발의 필요성을 더욱 깊이 이해하게 되었습니다. 기획 - 와이어프레임 - 프론트엔드 단계를 거치며 여러 테스트를 해본 결과 사용자 경험 데이터가 쌓이고, 사용자의 편의를 고려하여 기획 및 구상하는 단계가 즐겁게 다가왔습니다. 짧은 기간 동안 많은 작업을 해야 하는 이번 프로젝트에서 팀원들과 매일 진행한 개발 진척도 체크를 통해 프로젝트의 일정을 관리하고, 문제가 발생할 경우 즉시 해결책을 모색했습니다. 이 프로젝트를 통해 얻은 다양한 피드백 경험은 앞으로 다른 서비스를 기획하고 개발할 때 큰 도움이 될 것입니다. 또한, 처음으로 프런트에 백엔드의 데이터를 받아와 데이터를 다양한 방법으로 처리하였고, 프런트의 레이아웃 및 반응형 처리에 성공했을 때 누구보다 뿌듯함을 느꼈습니다. 앞으로도 사용자의 편의성을 고려하여 좀 더 성실하게 서비스를 기획 및 구현하고 싶습니다. |
|  윤재은 | 이번 프로젝트에서 저희 팀의 주된 목표는 백엔드 서버를 구축한 후 Jenkins, Kubernetes, 그리고 Ngrok을 이용해 CI/CD 환경을 조성하여 자동적인 빌드와 배포가 이루어지게 하는 것이었습니다. 개인적으로는 이전 프로젝트에서 시도하지 못했던 S3 버킷을 통한 파일 업로드와 백엔드 서버와 프론트 서버 간의 통신을 구현하는 것이 목표였습니다. <br> 프로젝트 기간 동안 매일 아침 스크럼 회의를 통해 팀원들과 각자 맡은 부분에 대한 현재 진행 상황을 공유하고, 앞으로의 계획과 방향성에 대해 논의하며 일정을 조율했습니다. 비록 개인의 진행 상황에 따른 어려움도 있었지만, 지속적인 대화를 통해 문제들을 해결해 나갈 수 있었고, 이 과정에서 소통의 중요성을 다시 한번 깨닫게 되었습니다. <br> 백엔드의 전반적인 구현을 맡으면서, 이전에 시도해보지 못했던 SMTP를 사용한 메일 발송, S3 버킷을 이용한 파일 업로드 등을 하나씩 해결해 나갔습니다. 다른 기능들을 테스트 코드를 작성하여 하나씩 테스트해보며 큰 성취감을 얻을 수 있었습니다. 백엔드에서 만들어진 기능들을 프론트에 적용하지 못한 부분과 Redis를 사용한 이메일 인증을 구현하지 못함에서 아쉬움이 남지만 이미 구현된 기능들은 향후 고도화 과정에서 쉽게 적용될 수 있을꺼라고 생각하고 Redis를 활용한 이메일 인증은 추후 고도화 과정에서의 새로운 목표가 될 수 있다는 점에서 희망을 느낍니다. <br> 프로젝트를 통해 아키텍처를 MSA로 나누지 않고 모놀리식 방식으로 개발을 진행하면서 JPA만을 사용해 본 후 이 경험을 통해 Spring boot환경의 전반적인 백엔드 개발을 복습할 수 있었으며, MyBatis와 JPA의 장단점, 모놀리식 서비스와 MSA의 장단점을 직접 비교해 볼 수 있었습니다. |
|  조수빈 | 1. 기본 게시판 기능: 이전 프로젝트에서의 경험을 바탕으로 기본 게시판 기능을 효율적으로 구현하려 노력했습니다. 이 기능은 프로젝트의 기반을 마련하는 데 중요한 역할을 하였으며, 과거에 배운 내용을 자연스럽게 복습할 수 있는 기회가 되었습니다. <br> 2. 새로운 주력 기능: 이벤트 게시판의 퀴즈 기능 <br> 이벤트 게시판 내에 사용자가 직접 퀴즈를 만들고 다른 사용자가 풀 수 있는 퀴즈 기능을 개발했습니다. 각 퀴즈는 단 한 번만 응시할 수 있으며, 정답률을 확인할 수 있습니다. 아직 완성하지 못한 투표 기능도 추후 고도화를 통해 완성할 계획입니다. Postman을 활용해 개발 과정 중 발생하는 세부적인 오류를 신속하게 확인하고 수정하는 작업을 수행했습니다. <br> 3. DevOps 활용 및 학습 경험 <br> Docker, DockerHub, Kubernetes, Jenkins를 이용하여 CI/CD 파이프라인을 구축하는 과정을 담당했습니다. 초기에는 이러한 기술들의 개념을 이해하는 데 어려움을 겪었지만, 다양한 오류에 직면하며 문제를 해결하는 과정을 통해 점차 이해도를 높일 수 있었습니다. 젠킨스 스크립트의 오류부터 하드웨어 용량 부족에 이르기까지 다양한 문제를 다각도에서 바라보고 접근하면서, 예외 처리와 오류 해결 기술에 대해 깊이 있게 배우게 되었습니다. <br> 4. 프로젝트 관리와 기획의 중요성 <br> 이번 프로젝트는 짧은 시간 내에 다양한 기능과 파트를 구현하는 것을 목표로 삼았습니다. 이 과정에서 명확한 목표 설정과 우선순위 조정의 중요성을 깊이 느꼈습니다. 담당 기능뿐만 아니라 팀원들과의 원활한 협업과 조정 능력이 프로젝트 성공에 필수적임을 인식했습니다. 앞으로도 팀원들과 협력하여 아직 완성하지 못한 기능과 추가적인 개선 의견을 반영하여 프로젝트를 업그레이드할 계획입니다. |





