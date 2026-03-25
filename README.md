# 칸반 보드 생성(프로젝트)


## 리팩토링
- [x] Description, Title data class -> value class 로 변경
- [x] ProfileState enum -> data class로 변경
- [x] ProgressBar 엔드포인트 수정
- [ ] Preview Sample Data 만들기
- [x] project, ModalState에서 Composable과 도메인 로직 분리

## 기능 구현 사항

**드래그 앤 드랍으로 태스크 카드의 Status 값을 변경한다**
- 비즈니스 로직
  - [ ] TaskCardData의 taskStatus 바꾸기
- UI 로직
  - [ ] 태스크 카드의 드래그 앤 드랍이 가능하게 한다.
  - [ ] 드랍된 위치에 Status 값으로 변경하는 함수를 호출한다.

**사이드 탭을 생성한다**
- 비즈니스 로직
  - [x] Project 객체 생성한다.
  - [x] Project 객체는 List<TaskCardData>와 프로젝트명을 가진다.
- UI 로직
  - [x] 사이드 탭의 상단에 "프로젝트", "4주차 미션 보드"라는 문구를 출력한다.
  - [x] 프로젝트 이름들을 사이드 탭에 출력한다.
  - [x] 선택된 프로젝트는 배경 강조 및 텍스트 컬러 강조를 한다.
  - [x] 프로젝트 이름이 사이드탭을 넘어갈 때 말줄임표를 한다.
  - [x] 프로젝트 변경 시 보드 헤더에 있는 프로젝트명을 변경한다.
  - [x] WorkSpace는 SideBar와 Board로 구성 되어있다.
  - [x] SideBar에서 Project를 선택했을 때 Board를 업데이트한다.


  