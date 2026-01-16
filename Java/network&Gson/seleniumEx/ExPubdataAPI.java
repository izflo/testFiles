/*
  [주말과제 2 - ExSeleniumLand.java]
  - 서울 열린데이터광장 지하철 실시간 도착정보(JSON) 호출 후 출력
  - https://data.seoul.go.kr/dataList/OA-12764/A/1/datasetView.do
  - 호출 형식
    http://swopenAPI.seoul.go.kr/api/subway/{KEY}/json/realtimeStationArrival/{START}/{END}/{STATN_NM}
  - '역삼'에 대해 아래 7개 정보를 화면에 출력
  - subwayId (호선 코드), trainLineNm (행선 정보), updnLine (상/하행),
    , bstatnNm (종착역), arvlMsg2, arvlMsg3 (도착 메시지), barvlDt (도착예정초)
  - HttpClient와 Gson 라이브러리 사용
  - 출력 예)
   ===== 역삼 실시간 도착정보 (4건) =====
   1) [호선:1002] 행선:성수행 - 선릉방면 | 외선 | 종착:성수 | 2분 20초 후 / 교대 | 도착예정(초): 140
   2) [호선:1002] 행선:성수행 - 선릉방면 | 외선 | 종착:성수 | 5분 30초 후 / 방배 | 도착예정(초): 330
   3) [호선:1002] 행선:성수행 - 강남방면 | 내선 | 종착:성수 | 4분 30초 후 / 종합운동장 | 도착예정(초): 270
   4) [호선:1002] 행선:성수행 - 강남방면 | 내선 | 종착:성수 | 9분 후 / 잠실나루 | 도착예정(초): 540
*/

package network.ex;

public class ExPubdataAPI {

	public static void main(String[] args) {

	}//main

}//class
