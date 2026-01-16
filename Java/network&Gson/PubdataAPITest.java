/*
[공공데이터 API 연동]
- https://www.data.go.kr/data/15057210/openapi.do
  (기상청_지상(종관, ASOS) 시간자료 조회서비스)
- 1) 2025년 12월 1일부터 12월 7일까지 서울지역 시간별 기온을 시간과 함께 출력
- 2) 날짜, 시간, 기온을 아래 형식으로 202512temp.csv로 저장
     형식) 2025/12/01,00:00,15.4c
            2025/12/01,01:00,14.5c
            ...
*/
package network;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

public class PubdataAPITest {
	
	private static final String REQUEST_URL
		= "http://apis.data.go.kr/1360000/AsosHourlyInfoService/getWthrDataList?serviceKey=53650c2acc1cfe15ce138fb3c2c3aad88c880d9d31029923e95340058997bf2f&numOfRows=500&pageNo=1&dataCd=ASOS&dateCd=HR&stnIds=108&endDt=20251207&endHh=23&startHh=00&startDt=20251201&dataType=JSON";
	
	private static final HttpClient client = HttpClient.newHttpClient(); //************************************
	
	public static void main(String[] args) {
		
		try {
			
			
			
			// 파일에 저장
			// PrintWriter : 두번째 인자로 true 주면 auto flush
			PrintWriter pw = new PrintWriter(new FileWriter(
				new File("D:/pub2511/files/202512temp.csv")	
			), true);
			
			Gson gson = new GsonBuilder().setPrettyPrinting().create();
			
			// 2. 문자열을 java 객체로 변환
			// 서버에 있는 json 데이터 보면서 구조 확인하기
			/*
			 {
			  "response": {
			    "header": {
			      "resultCode": "00",
			      "resultMsg": "NORMAL_SERVICE"
			    },
			    "body": {
			      "dataType": "JSON",
			      "items": {
			        "item": [
			          {
			            "tm": "2019-01-20 01:00",
			            "rnum": "1",
			            "stnId": "108",
			            "stnNm": "서울",
			            "ta": "3.6",
			            "taQcflg": "",
			            "rn": "",
			            "rnQcflg": "",
			            "ws": "2.5",
			            "wsQcflg": "",
			            "wd": "290",
			            "wdQcflg": "",
			            "hm": "76",
			            "hmQcflg": "",
			            "pv": "6.0",
			            "td": "-0.2",
			            "pa": "1007.8",
			            "paQcflg": "",
			            "ps": "1018.4",
			            "psQcflg": "",
			            "ss": "",
			            "ssQcflg": "9",
			            "icsr": "",
			            "dsnw": "",
			            "hr3Fhsc": "",
			            "dc10Tca": "9",
			            "dc10LmcsCa": "8",
			            "clfmAbbrCd": "",
			            "lcsCh": "3",
			            "vs": "310",
			            "gndSttCd": "",
			            "dmstMtphNo": "40",
			            "ts": "0.0",
			            "tsQcflg": "",
			            "m005Te": "0.5",
			            "m01Te": "0.0",
			            "m02Te": "0.2",
			            "m03Te": "0.7"
			          },
			 */
			
			// 전체 json문자열을 JsonObject로 변환
			// getAPIData : 1. 서버로부터 json형식을 문자열로 받음
			JsonObject jsonObject = gson.fromJson(getAPIData(), JsonObject.class);
			
			// item배열을 JsonArray로 변환
			JsonArray jsonArray = jsonObject
					.get("response").getAsJsonObject() // response를 JsonObject로 획득
					.get("body").getAsJsonObject()
					.get("items").getAsJsonObject()
					.get("item").getAsJsonArray(); // item은 JsonArray로 꺼내기
			
			// jsonArray.asList() : jsonArray를 자바의 리스트로 변환. 가공할 수 있게 ********************************
			// JsonArray를 List로 변환 후 스트림을 얻어 각 JsonObject들을 CSV형식의 문자열로 만든 후
			// 화면과 파일에 출력
			jsonArray.asList().stream().forEach(obj -> {
				String line = 
						((JsonObject) obj).get("tm").getAsString().replaceAll("-", "/").replaceAll(" ", ",")
						+ ","
						+ ((JsonObject) obj).get("ta").getAsString() + "C";
				System.out.println(line); // 화면에 출력
				// PrintWriter : println처럼 편한 형식으로 파일에 출력하기 위해서
				pw.println(line); // 파일에 출력 ******************************************
			});
			//flush/close안 하면 파일에 안 적힘**************************************
			pw.flush();
			pw.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}//main
	
	//HTTP 요청을 보내고 응답 문자열을 반환
	private static String getAPIData() throws Exception {
		// HTTP 요청 객체 (GET방식)
		HttpRequest request = HttpRequest.newBuilder()
				.uri(URI.create(REQUEST_URL)).GET().build();
		// 요청을 서버에 보내서 HTTP 응답 객체를 생성
		HttpResponse<String> response
		// request를 client한테 보내서 응답 받은 게 response
			= client.send(request, HttpResponse.BodyHandlers.ofString()); //****************************************
		
		return response.body(); //응답문자열 반환
	}//getAPIData

}// class