# Custom gradle script

프로젝트 기준으로 사용하기 편한 그래들 스크립트 파일들이 위치하는 폴터

## init.gradle

### `init-msa`

intelliJ나 혹은 다른 IDE에서 제공하는 모듈 생성 도구를 사용해도 되지만, 도메인에 따라 생성되어야하는 다수의 모듈을 일일이 생성해야하는 것이 번거롭기 때문에
그래들 스크립트에 태스크를 정의해서 명령어 입력시 자동으로 기본적인 모듈 구조를 한번에 생성하기 위해 사용한다.

- 옵션
  - `moduleName` (required) : 모듈명을 입력 (메인 도메인명을 입력하면 됨)

```shell
./gradlew -q init-msa -PmoduleName=모듈명

# gradle 사용해도 상관없지만 개인이 설치한 gradle 버전이 각각 다르기 때문에 
# Project 기준으로 일관된 버전을 사용하기 위해 gradlew로 실행한다. 
```

> 모듈이 정상적으로 생성된 후 그래플 프로젝트 reload 가 자동으로 실행되는데, reload가 실행되지 않으면
> IDE의 그래들 도구에서 reload(refresh) gradle project 태스크를 직접 실행해준다.

- 실행 완료 후 디랙토리 구조 결과 예시
  - e.g. `./gradlew -q init-msa PmoduleName=sample`
```text
{root-project}
...
ㅏ msa
  ㅏ sample                      # 도메인
    ㅏ adapter-in-web            # 웹 어뎁터 계층
    ㅏ adapter-out-persistence   # 영속성 어뎁터 계층
    ㅏ app-domain                # 애플리케이션 도메인 엔터티 계층
    ㅏ app-port-in               # 애플리케이션 인 포트 계층
    ㅏ app-port-out              # 애플리케이션 아웃 포트 계층
    ㅏ app-service               # 애플리케이션 서비스 계층
    ㅏ boot                      # MSA(도메인) 실행 주체
    ㅣ ㅏ src
    ㅣ ㅣ ㅏ main
    ㅣ ㅣ ㅣ ㅏ java
    ㅣ ㅣ ㅣ ㅣ ㅏ com.{}.{}.sample... (생략)
    ㅣ ㅣ ㅣ ㅣ ㄴ com.{}.{}.SampleApplication.java
    ㅣ ㅣ ㅣ ㅏ resources
    ㅣ ㅣ ㅏ test
    ㅣ ㅣ ㅣ ㅏ java
    ㅣ ㅣ ㅣ ㅣ ㅏ com.{}.{}.sample... (생략)
    ㅣ ㅣ ㅣ ㅏ resources
    ㅣ ㅣ ㅣ ㅣ ㅏ... (생략)
    ㅣ ㄴ .gitignore
    ㅣ ㄴ .build.gradle
```