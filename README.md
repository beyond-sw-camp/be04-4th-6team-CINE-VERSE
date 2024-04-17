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


## 기획 (의도 / 서비스화)

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
