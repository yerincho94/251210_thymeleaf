# Thymeleaf 실습 프로젝트

이 프로젝트는 Thymeleaf의 주요 기능들을 실습하기 위해 만들어졌습니다.

## Thymeleaf란?

Thymeleaf는 웹 및 독립 실행형 환경을 위한 최신 서버 측 Java 템플릿 엔진입니다. HTML 파일에서 직접 작동하여 브라우저에서 올바르게 표시할 수 있는 템플릿을 만들 수 있습니다.

주요 특징:
- **Natural Templates**: Thymeleaf 템플릿은 일반 HTML 파일처럼 보이고 작동합니다.
- **표준 방언과 표현식**: `th:*` 속성을 사용하여 HTML 태그에 동적 동작을 추가합니다.
- **확장성**: 필요에 따라 자신만의 방언을 만들 수 있습니다.
- **Spring Framework와의 통합**: Spring Boot에서 기본 템플릿 엔진으로 사용됩니다.

## 실습 내용

### 1. 기본 문법 (`syntax/basic.html`)

- **변수 표현식 `${...}`**: 컨트롤러에서 전달된 모델 데이터를 표시합니다.
  - 예: `<p th:text="${greeting}">기본 인사말</p>`
- **객체 속성 접근**: 객체의 필드나 메서드에 접근합니다.
  - 예: `<p th:text="${mentor.name}">멘토님</p>`
- **유틸리티 객체**: `#temporals`, `#lists`, `#strings` 등 다양한 유틸리티 객체를 사용하여 데이터를 가공합니다.
  - 예: `<p th:text="${#temporals.format(currentTime, 'yyyy-MM-dd HH:mm')}">...</p>`

### 2. 목록 처리 및 조건부 렌더링 (`mentor/list.html`)

- **반복 `th:each`**: 컬렉션의 각 항목에 대해 HTML 요소를 반복합니다.
  - 예: `<tr th:each="mentor : ${mentors}">...</tr>`
- **조건부 렌더링 `th:if`, `th:unless`**: 특정 조건에 따라 요소를 렌더링하거나 렌더링하지 않습니다.
  - 예: `<div th:if="${#lists.isEmpty(mentors)}">...</div>`
- **`th:switch`, `th:case`**: 여러 조건에 따라 다른 내용을 표시합니다.
  - 예:
    ```html
    <span th:switch="${mentor.specialty}">
        <span th:case="'백엔드'">🖥️ 백엔드</span>
        <span th:case="'프론트엔드'">🎨 프론트엔드</span>
    </span>
    ```
- **URL 링크 `@{...}`**: 동적인 URL을 생성합니다.
  - 예: `<a th:href="@{/mentor/{id}(id=${mentor.id})}">상세</a>`

### 3. 프래그먼트 (`fragments/common.html`, `mentor/list.html`, `mentor/detail.html`)

- **프래그먼트 정의 `th:fragment`**: 재사용 가능한 HTML 조각을 정의합니다.
  - 예: `<header th:fragment="header">...</header>`
- **프래그먼트 포함 `th:replace`, `th:insert`**: 다른 템플릿에 프래그먼트를 삽입합니다.
  - `th:replace`: 호스트 태그를 프래그먼트로 대체합니다.
  - `th:insert`: 호스트 태그 내부에 프래그먼트를 삽입합니다.
  - 예: `<header th:replace="~{fragments/common :: header}"></header>`
- **파라미터화된 프래그먼트**: 프래그먼트에 파라미터를 전달하여 동적으로 내용을 변경할 수 있습니다.
  - 정의: `<head th:fragment="htmlHead(title)">...</head>`
  - 사용: `<head th:replace="~{fragments/common :: htmlHead('멘토 목록')}"></head>`
