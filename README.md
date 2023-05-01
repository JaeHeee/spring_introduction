# spring_introduction

---

<h3>라이브러리</h3>

Spring Web, Thymeleaf 2가지만 추가 했지만 의존 관계인 external library들도 다 가져옴
<br>
- spring-boot-starter-logging
    - println으로 출력하면 안된다. log로 출력해야 심각한 에러만 따로 모으고 관리할 수 있구
    - logback
    - slf4j
- spring-boot-starter-test
  - junit: 테스트 프레임워크
  - mockito: mock, flutter 에도 있음
  - assertj: 테스트 코드를 좀 더 편하게 작성하게 도와줌
  - spring-test: 스프링 통합 테스트 지원



<h3>웰컴 페이지</h3>
궁금한게 있으면 https://spring.io/ 여기서 찾아라

static에 index.html을 생성하면 웰컴페이지 기능을 함
템플릿 엔진을 사용하면 동적인 화면을 만들 수 있다.

![img.png](images/img.png)
helloController의 GetMapping은 GET method의 그 GET이다.
spring이 여기서 모델을 만들어주는데 예시에서 key는 'data', value는 'hello!!'로 넣어주었다.
<br>
여기서 return의 이름이 'hello' 였다. 그러면 resources의 templates의 hello를 찾아서 렌더링한다.
화면을 찾는 것은 viewResolver가 한다.
- resources:templates/ +{ViewName}+ .html



<h3>빌드하고 실행하기</h3>

- ./gradlew build 
- cd build/libs 
- java -jar hello-spring-0.0.1-SNAPSHOT.jar

서버 배포할 때 hello-spring-0.0.1-SNAPSHOT.jar 이 파일만 복사해서 서버에 넣고 java -jar 해서 실행시키면 된다. 

안되면 ./gradlew clean build로 다시 실행


<h3>웹을 개발한다는 것은</h3>

1.정적 컨텐츠

![img_1.png](images/img_1.png)

```java
서버에서 뭔가를 하지 않고 그냥 파일을 웹브라우저에 내려준다.
스프링 부트는 정적 컨텐츠 기능을 자동으로 제공한다.

resources/static에 html 파일을 넣으면 웹브라우저에서 보여줄 수 있다.

1. 스프링 컨테이너에서 먼저 controller 쪽에 hello-static이 있는지 찾아본다.
2. 없으면 resources/static/hello-static.html을 찾아서 있으면 반환해준다.
```

2. MVC와 템플릿 엔진

![img_2.png](images/img_2.png)

```java
jsp, php 같은것들이 템플릿 엔진이다. html을 그냥 주는게 아니라 서버에서 프로그래밍을 해서 html을 동적으로 변경해서 내려준다.
이것을 하기위해서 controller, model, 템플릿 엔진화면 3가지를 mvc라고 한다.(model, view, controller)
view : 화면을 그리는데 모든 역량을 집중
model, controller : 비즈니스 로직 관련

```

3. API

![img_3.png](images/img_3.png)

```java
json format으로 client에 데이터를 전달하는 방식
@ResponseBody 어노테이션이 있으면 viewResolver에게 넘기지 않고 응답을 그대로 넘기도록 동작한다.
그런데 여기서 문자가 아니라 객체라면 디폴트는 json형태로 만들어서 http 응답에 반환한다.

viewResolver 대신 HttpMessageConverter가 동작한다.
단순히 문자면 StringConverter, 객체면 JsonConverter가 동작한다.
```
