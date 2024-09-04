# 날씨 일기 프로젝트

날씨 API를 사용하여 특정 날짜의 날씨 데이터를 가져오고 일기를 작성, 조회, 수정, 삭제할 수 있는 스프링 부트 기반의 백엔드 API입니다.

## 프로젝트 개요

이 프로젝트는 사용자가 원하는 날짜에 대해 일기를 작성하고, 수정하거나 삭제할 수 있도록 API를 제공합니다. 또한 OpenWeatherMap API를 사용하여 해당 날짜의 날씨 정보를 함께 저장합니다.

## 기능

- **일기 생성**: 사용자가 특정 날짜에 대한 일기를 작성하고 저장할 수 있습니다.
- **일기 조회**: 특정 날짜 또는 날짜 범위에 해당하는 일기 목록을 조회할 수 있습니다.
- **일기 수정**: 저장된 일기의 내용을 수정할 수 있습니다.
- **일기 삭제**: 저장된 일기를 삭제할 수 있습니다.
- **날씨 정보 저장**: 매일 새벽 1시에 현재 날씨 정보를 자동으로 저장합니다.

## 기술 스택

- **프레임워크**: Spring Boot 3.3.1
- **언어**: Java 17
- **데이터베이스**: MySQL
- **빌드 도구**: Gradle
- **API 문서화**: SpringDoc OpenAPI (Swagger UI)
- **외부 API**: OpenWeatherMap API (날씨 정보 조회)
- **로그 관리**: Logback

## 주요 기능

1. **일기 생성**: 특정 날짜의 날씨 정보를 가져와 사용자가 입력한 내용과 함께 일기를 생성합니다.
2. **일기 조회**: 특정 날짜 혹은 기간에 해당하는 모든 일기를 조회합니다.
3. **일기 수정**: 특정 날짜의 일기 내용을 수정합니다.
4. **일기 삭제**: 특정 날짜의 모든 일기를 삭제합니다.
5. **날씨 데이터 저장**: 매일 일정 시간에 OpenWeatherMap API로부터 날씨 정보를 가져와 데이터베이스에 저장합니다.

## API 명세

### 1. Create Diary

- **URL**: `/create/diary`
- **Method**: `POST`
- **Description**: 특정 날짜의 날씨 정보를 기반으로 새로운 일기를 생성합니다.
- **Request Parameters**:
  - `date` (query parameter, `LocalDate` format): 일기를 작성할 날짜.
- **Request Body**:
  - `text` (string): 일기 내용.
- **Response**: 성공적으로 생성된 경우 HTTP 200 상태 코드 반환.

### 2. Read Diary

- **URL**: `/read/diary`
- **Method**: `GET`
- **Description**: 특정 날짜의 모든 일기를 조회합니다.
- **Request Parameters**:
  - `date` (query parameter, `LocalDate` format): 조회할 일기의 날짜.
- **Response**: 일기 목록을 JSON 형식으로 반환.

### 3. Read Diaries

- **URL**: `/read/diaries`
- **Method**: `GET`
- **Description**: 특정 기간 동안의 모든 일기를 조회합니다.
- **Request Parameters**:
  - `startDate` (query parameter, `LocalDate` format): 조회할 기간의 시작 날짜.
  - `endDate` (query parameter, `LocalDate` format): 조회할 기간의 종료 날짜.
- **Response**: 해당 기간의 일기 목록을 JSON 형식으로 반환.

### 4. Update Diary

- **URL**: `/update/diary`
- **Method**: `PUT`
- **Description**: 특정 날짜의 일기 내용을 수정합니다.
- **Request Parameters**:
  - `date` (query parameter, `LocalDate` format): 수정할 일기의 날짜.
- **Request Body**:
  - `text` (string): 수정할 일기 내용.
- **Response**: 수정된 일기 내용과 HTTP 200 상태 코드 반환.

### 5. Delete Diary

- **URL**: `/delete/diary`
- **Method**: `DELETE`
- **Description**: 특정 날짜의 모든 일기를 삭제합니다.
- **Request Parameters**:
  - `date` (query parameter, `LocalDate` format): 삭제할 일기의 날짜.
- **Response**: 성공적으로 삭제된 경우 HTTP 200 상태 코드 반환.

## 엔티티
- **Diary**: 사용자가 작성하는 일기 정보를 저장하는 테이블입니다. 각 레코드는 고유한 ID, 일기 내용, 날짜, 해당 날짜의 날씨 정보 등을 포함합니다.
  - `id` (int, primary key): 일기의 고유 식별자.
  - `date` (LocalDate): 일기가 작성된 날짜.
  - `weather` (String): 해당 날짜의 날씨 상태 (예: "Clear", "Rain").
  - `icon` (String): 날씨 아이콘 정보.
  - `temperature` (double): 해당 날짜의 기온.
  - `text` (String): 사용자가 작성한 일기 내용.

- **DateWeather**: 특정 날짜의 날씨 정보를 저장하는 테이블입니다. 매일 OpenWeatherMap API로부터 수집한 날씨 데이터를 저장합니다.
  - `date` (LocalDate, primary key): 날씨가 기록된 날짜.
  - `weather` (String): 날씨 상태 (예: "Clear", "Rain").
  - `icon` (String): 날씨 아이콘 정보.
  - `temperature` (double): 해당 날짜의 기온.

## 사용된 API

### OpenWeatherMap API

- **API URL**: `https://api.openweathermap.org/data/2.5/weather`
- **API Key**: 설정 파일 (`application.properties`)에서 관리.
- **Description**: 지정된 도시의 현재 날씨 데이터를 JSON 형식으로 제공합니다.
