# 칸반 보드 생성(보드)

# 기능 구현 사항

### 1. 리팩토링
- [x] 비즈니스 로직과 UI 로직 분리하기
- [x] Tags 내 const 변수 가시성 private 으로 변경하기
- [x] Modal 내 상태 분리
- [x] TextInputState 일반 class로 변경

### 2. 비즈니스 로직
- [x] TaskCardData를 진행 상태별로 분류해서 저장
- [x] 전체 할 일중 완료된 일의 비율 계산
- [x] 전체 업무 수 내보내기

### 3. UI 로직
- [x] 보드 헤더
    - "Compose Desktop 칸반 보드" 타이틀을 출력
    - 모든 Task 중 완료된 Task의 개수와 완료율 출력 ( 예: 완료율: 50% (3/6) )
    - 완료율은 인디케이터바로도 출력되어야 함
    - 새 태스크 생성 버튼 출력
    - 새 태스크 생성 버튼을 누르면 Modal이 출력
- [x] Modal
    - 생성 조건 만족 후 생성 버튼을 누르면, Modal 창 닫힘
    - TaskCard가 생성되어 Modal 창이 닫히게 되면 SnackBar를 화면 하단 센터에 출력 ("새로운 태스크가 추가되었습니다")
- [x] TaskCard 목록
    - TaskCard의 진행 상태별로 (To Do, In Progress, Done) 섹션 분리하여 출력
    - 각 진행 상태의 TaskCard 개수 출력
