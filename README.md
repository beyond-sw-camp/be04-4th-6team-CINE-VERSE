# CINE-VERSE 프로젝트
![로고](https://github.com/Jihye1101/jenkins-boot/assets/153909291/757b8cf3-313d-4262-acc2-5edcc35d1838)

### 팀명: 시내버스
### 팀원

- 팀원 : **송동준**
[<img src="https://img.shields.io/badge/Github-Link-181717?logo=Github">](https://github.com/dongjunsong)

- 팀원 : **양지혜**
[<img src="https://img.shields.io/badge/Github-Link-181717?logo=Github">](https://github.com/Jihye1101)

- 팀원 : **윤재은**
[<img src="https://img.shields.io/badge/Github-Link-181717?logo=Github">]()

- 팀원 : **조수빈**
[<img src="https://img.shields.io/badge/Github-Link-181717?logo=Github">]()

---

## 영화 마니아들을 위한 전문 커뮤니티 사이트 기획서
--

#### 1. 서비스 기획 배경

- **현 상황**: 매니아를 위한 영화 커뮤니티의 부재. 대형 플랫폼의 영화 카페는 폐쇄되었고, 기존의 사이트들은 너무 복잡하며 다양한 주제를 아우르고 있어 영화만을 전문으로 다루는 공간이 필요.
- **필요성**: 영화 매니아들이 자신의 영화 취향을 공유하고, 심도 깊은 토론을 할 수 있는 전문 커뮤니티의 필요가 대두됨.

#### 2. 서비스 목표

- **전문성과 편안함**: 영화만을 전문으로 다루는 커뮤니티 제공으로 사용자들이 보다 쉽게 정보를 얻고 공유할 수 있는 플랫폼 마련.
- **안전한 이용 환경**: 서버에 위험한 정보를 저장하지 않고, 이용자의 개인정보 보호를 최우선으로 함(예: 쿠키에 회원들의 개인정보를 담지 않음).
- **활발한 소통 공간**: 사용자들이 자유롭게 정보를 공유하고, 영화에 대한 리뷰나 비평을 자유롭게 표현할 수 있는 오픈된 커뮤니티.

#### 3. 서비스 차별화 전략

- **포인트 시스템**: 영화 퀴즈 참여를 통해 포인트를 획득하고, 이 포인트를 사용하여 다양한 뱃지(예: 마블, DC 코믹스 뱃지)를 구매할 수 있음.
- **뱃지 활용**: 구매한 뱃지는 사용자의 프로필 또는 닉네임 옆에 표시되어, 사용자의 관심사와 전문성을 나타낼 수 있음. 이는 사용자간의 소속감을 형성하고, 커뮤니티 내에서의 신뢰도를 증가시키는 역할을 함.

#### 4. 기대 효과

- **전문화된 플랫폼**: 영화만을 주제로 하는 전문화된 커뮤니티로서의 자리매김.
- **사용자 참여 유도**: 포인트 및 뱃지 시스템을 통한 게임화 요소는 사용자의 지속적인 참여를 유도.
- **커뮤니티 활성화**: 특정 주제에 집중함으로써 사용자간의 더 깊은 연결과 상호 작용을 촉진.

이러한 사이트는 영화 덕후들에게 맞춤화된 정보를 제공하며, 안전하고 편안한 환경에서 영화에 대한 열정을 공유할 수 있는 최적의 공간이 될 것입니다.
퇴근 후 시내버스 타고 영화 한 편 어때요?


<img src="https://capsule-render.vercel.app/api?type=waving&color=0:5ecfa4,100:57b762&height=200&text=CICD&animation=twinkling&fontColor=ffffff&fontSize=50" />
CINE-VERSE 프로젝트의 Jenkins 파이프라인을 통한 CI/CD 환경 설정 문서입니다.

## 전제 조건 및 설치 환경
이 프로젝트를 시작하기 전에 다음 소프트웨어와 도구가 설치되어 있어야 합니다.

- **IntelliJ**: JDK 17 설치 필요
- **Docker**: 버전 4.28
- **Jenkins**: 버전 2.452
- **Kubernetes**: 버전 1.92.2
- **Ngrok**
- **Webhook**: GitHub와 연동 설정

## Credential 설정
안전한 개발을 위해 다음 크리덴셜을 설정합니다.

1. **MY-GITHUB-KEY**: RSA 키
2. **DOCKERHUB-PASSWORD**: Docker Hub 업로드를 위한 계정 정보
3. **SECRET-ID**: GitHub에 업로드되면 안 되는 `application.yml`

## Jenkins Pipeline 설정
### Git 저장소와 Webhook 연결
- 변경사항 감지 후 Jenkins가 자동으로 빌드를 시작합니다.

### RSA 키 생성
- Jenkins가 Webhook을 통해 변경된 소스 코드를 감지하고, SSH에 사용될 RSA 키를 생성합니다.

### Pipeline 생성 및 설정
- Jenkins Tools에서 `Java (OpenJDK 17)` 및 `Gradle (ver 8.7)` 설정

### Plugin 설치
- `Publish Over SSH` 및 `Pipeline: Stage View` 설치

### Pipeline 스크립트
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
      nodePort: 30007                   # 외부 접속 포트번
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



## 추가 기능

1. CGV 실시간 인기 영화 순위 크롤링

Python을 사용하여 requests와 BeautifulSoup 라이브러리로 CGV에서 영화 데이터(실시간 인기 영화 순위 10)를 크롤링하고 json으로 저장
메인 화면에 들어가면 사용자는 실시간 영화 정보를 통해 최신 트렌드를 파악 가능

중요 코드:
```
url = 'http://www.cgv.co.kr/movies/'
res = requests.get(url)
soup = BeautifulSoup(res.text, 'html.parser')
```

2. OpenAI API를 이용한 챗봇 만들기

JavaScript를 사용하여 OpenAI의 API로부터 챗봇의 응답을 받아 처리하는 기능 구현
api 키는 .env 환경변수 처리를 통해 외부에 노출되지 않도록 보안 처리
영화 추천 등 다양한 주제에 대한 빠르고 효율적인 대화 가능
https://platform.openai.com/usage에서 API request 조회 가능
![API request 조회 가능](https://github.com/Jihye1101/jenkins-boot/assets/153909291/3f027249-706c-4450-afd3-443913a68786)

중요 코드:
```
async function sendMessage() {
  const message = userMessage.value.trim();
  if (message.length === 0) return;
```

## DB모델링

### 개념모델링
![개념모델링](https://github.com/Jihye1101/jenkins-boot/assets/153909291/444fc562-7a17-4c39-83e5-5471262a2c29)

### 논리모델링
![논리모델링](https://github.com/Jihye1101/jenkins-boot/assets/153909291/4a1a47bc-7ec0-4f89-a6f0-0d7ec683f1fd)

### 물리모델링
![물리모델링](https://github.com/Jihye1101/jenkins-boot/assets/153909291/cc90d895-444d-4378-b12a-dc940a5df2d6)

## 와이어 프레임
![와이어프레임](https://github.com/Jihye1101/jenkins-boot/assets/153909291/100c95ea-aff5-4c34-bb35-9aedd6c75ead)
(화살표 수정중)

## 기능 명세서
![기능명세서1](https://github.com/Jihye1101/jenkins-boot/assets/153909291/0ac9c3ea-933a-4245-adc6-bdb00d0f24f4)

## 요구사항 명세서

## 테스트 명세서
