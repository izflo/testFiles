package gson.ex;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URLConnection;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import gson.Item;

public class ExGson2 {

	public static void main(String[] args) throws Exception {

		// 사용자별 todo의 전체개수와 완료한 todo의 개수를 아래 형식으로 todocount.log 파일에 저장
		// 파일 출력 형식: 사용자명 [전체todo수:00개, 완료todo수:00개]

		// 2개 주소로부터 연결하기

		URLConnection userConn = new URI("https://jsonplaceholder.typicode.com/users").toURL().openConnection();
		URLConnection todoConn = new URI("https://jsonplaceholder.typicode.com/todos").toURL().openConnection();

		String userJsonStr = "";
		String todoJsonStr = "";
		String lineStr = "";
		StringBuilder sb = null;

		try {

			// user 값 읽어와서 jsonStr에 저장하기
			if (userConn != null) {// 연결됐다면
				// user 정보 받아오기
				BufferedReader br = new BufferedReader(new InputStreamReader(userConn.getInputStream()));

				sb = new StringBuilder();
				while ((lineStr = br.readLine()) != null) {
					sb.append(lineStr);
				}
				userJsonStr = sb.toString();
			}
			// 2.gson을 사용해 json문자열->List<Post>
			Gson gson = new GsonBuilder().setPrettyPrinting().create();
			
			/*
			JsonObject => { {}, {}, [{}, {}, {}] }
			JsonArray => [{}, {}, {}]
			JsonElement => {}  마치 JsonObject 와 같은 것이 되어버림. 왜냐면 JsonObject 는 모든 데이터 형식을 가질 수 있기 때문에 {} 하나만으로 이루어진 데이터도 JsonObject가 될 수 있다.
			JsonElement.getAsJsonObject().get("key value").getAsString();
			 */
			
			JsonObject jsonObject = gson.fromJson(userJsonStr, JsonObject.class); // JsonObject.class : 어떤 객체로 변환할건지
//			System.out.println(jsonObject);
//			JsonArray items = jsonObject.getAsJsonArray("id");
//			JsonObject response = jsonObject.getAsJsonObject("response");
//			JsonObject body = response.getAsJsonObject("body");
			JsonArray items = jsonObject.getAsJsonArray();
//			System.out.println(jsonObject);
			// 인터넷에서 json문자열 가져와서 java의 클래스 객체로 만드는 법
			List<User> itemList = gson.fromJson(
					items, 
					new TypeToken<List<User>>() {}.getType()
			);
			
//			List<User> userList = gson.fromJson(items, new TypeToken<List<User>>() {
//			}.getType());
//			userList.stream().forEach(System.out::println);

			// user 값 읽어와서 jsonStr에 저장하기
//			if (todoConn != null) {// 연결됐다면
//				// todo 정보 받아오기
//				BufferedReader br = new BufferedReader(new InputStreamReader(todoConn.getInputStream()));
//
//				sb = new StringBuilder();
//				while ((lineStr = br.readLine()) != null) {
//					sb.append(lineStr);
//				}
//				todoJsonStr = sb.toString();
//
//				// 2.gson을 사용해 json문자열->List<Post>
//				Gson gson = new GsonBuilder().setPrettyPrinting().create();
////						List<Post> postList = gson.fromJson(jsonStr, new TypeToken<List<Post>>() {
////						}.getType());
//				JsonObject jsonObj = gson.fromJson(todoJsonStr, JsonObject.class);
//				
//
//				
//				// 사용자별 todo의 전체개수와 완료한 todo의 개수를 아래 형식으로 todocount.log 파일에 저장
//				// 사용자별 : userId 기준으로 분류
//				// todo의 userId랑 user의 id가 동일
//				
//			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}// main

}// class
