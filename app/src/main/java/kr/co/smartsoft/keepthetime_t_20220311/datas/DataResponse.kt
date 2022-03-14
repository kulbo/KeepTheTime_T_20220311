package kr.co.smartsoft.keepthetime_t_20220311.datas

//  서버응답 중 : data: {} 을 파싱하기 위한 클래스

// 여러 API 에서 data {} 를 내려줄 예정

class DataResponse(
    val user : UserData,
    val token : String,
    val friends: List<UserData>,    // 친구목록을 가져올때

    val users: List<UserData>,      // 검색 결과의 사용자 목록
) {

}