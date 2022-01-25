<img src="doc/imgs/그래픽 이미지.png" width="50%">

# Project: SleepNow
* 슬립 나우
* 개인 프로젝트
* 2020.01 ~ 2020.01

## 0. Overview
슬립나우는 자러 갈 시간이나 일어 날 시간을 선택하게 되면 나이에 따른 적절한 수면시간을 추천해주는 앱입니다.  
수면 단계에 따른 시간을 분석하여 자고 일어났을 때 최대한 피곤하지 않고 개운한 느낌이 들도록 해줍니다.  

## 1. Function
* 나이 선택 기능
* 시간 선택 기능
* 수면시간 계산 기능

## 2. Tech
- SharedPreference
- TimePicker
- MVVM 패턴 (viewmodel+databinding+livedata)
- EventWrapper (viewmodel에서 dialog를 띄우기 위함)
- SimpleDateFormat (날짜 형식 변환)
- CustomDialog
- TabLayout
- RecyclerView (databinding + decoration)
- OutlineTextView

## 3. UI

<img src="doc/imgs/splash.jpg" width="20%"> <img src="doc/imgs/selectage(dark).jpg" width="20%"> <img src="doc/imgs/selectage(white).jpg" width="20%">  
<img src="doc/imgs/main(white).jpg" width="20%"> <img src="doc/imgs/calcul(white).jpg" width="20%"> <img src="doc/imgs/setting(white).jpg" width="20%">