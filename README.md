# DearMe-BE
나의 하루에, AI가 건네는 따뜻한 한마디 🌙
</br>

## 📍 프로젝트 배경
- **프로젝트 명**: Dear Me
- **목적**: 사용자의 감정 기록 및 AI 상담 기능을 제공하는 감정 케어 웹 서비스
  
<img width="360" height="240" alt="DearMe배경(낮)" src="https://github.com/user-attachments/assets/0081a152-ca14-439a-b27c-8390bb676091" />
<img width="360" height="240" alt="DearMe배경(밤)" src="https://github.com/user-attachments/assets/a594b78c-bf39-4d9b-be70-00085b66729e" />



## 🖥️ Backend
| <img src="https://github.com/eun-seoo.png?size=120" width="120"/> | 
|:---:|
|[김은서](https://github.com/eun-seoo)|


</br>

## ⚙️ 기술 스택
- **Backend:** Spring Boot 3.x, JPA, MySQL
- **Infra:** Docker, GitHub Actions
- **AI Integration:** OpenAI GPT API

</br>


## 🌿 기능 목록
### 📝 메모 도메인

#### 1️⃣ 메모 작성 (`POST /api/memos`)
- [ ] 사용자는 감정 이모지, 감정 점수, 제목, 내용을 입력할 수 있다.
- [ ] 입력값 검증 (`date`, `emoji`, `emotionScore`, `title`, `content`)
- [ ] 동일 날짜 중복 작성 불가
- [ ] DB 저장 후 생성된 `memoId` 반환
- [ ] 정상 시 `201 Created`, 유효성 오류 시 `400 Bad Request`

#### 2️⃣ 전체 메모 리스트 조회 (`GET /api/memos`)
- [ ] 사용자의 모든 메모를 `X-Client-Id` 기준으로 조회한다.
- [ ] `memoId`, `date`, `emoji`, `title`, `emotionScore` 반환
- [ ] 성공 시 `200 OK`

#### 3️⃣ 특정 메모 상세 조회 (`GET /api/memos/{memoId}`)
- [ ] 경로의 ID를 검증한다.
- [ ] 요청한 사용자의 `X-Client-Id`와 일치하지 않으면 `403 Forbidden`
- [ ] 성공 시 `content` 반환

#### 4️⃣ AI 감정 공감 답장 생성 (`POST /api/memos/counsel`)
- [ ] 사용자가 선택한 메모 ID 배열을 보낸다.
- [ ] 평균 감정 점수(`stressScore`) 계산
- [ ] OpenAI API 호출 → AI 응답 생성
- [ ] 실패 시 기본 문구 반환

</br>

## ⚙️ API 명세서
| 기능 | Method | Endpoint | 설명 |
|------|---------|-----------|------|
| 메모 작성 | POST | `/api/memos` | 감정 메모 생성 |
| 전체 조회 | GET | `/api/memos` | 모든 메모 조회 |
| 상세 조회 | GET | `/api/memos/{memoId}` | 특정 메모 상세 조회 |
| AI 상담 | POST | `/api/memos/counsel` | AI 감정 답장 생성 |


</br>

## 🧪 테스트 전략 (TDD)
1. Repository 테스트부터 작성 (데이터 검증)
2. Service 단위 테스트 (비즈니스 로직)
3. Controller 테스트 (MockMvc)
4. 예외 및 경계 테스트 추가

</br>

## 💡 컨벤션
- **Commit:** Angular Style (`feat:`, `fix:`, `refactor:`)
- **Branch:** `feat/#3-memo-domain`
- **PR Template:** 기능 단위 명세 중심으로 작성
- **Code Style:** 15라인 이하 함수, SRP 원칙 준수


