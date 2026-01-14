package gson.ex;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

public class ExGson2 {

	public static void main(String[] args) throws Exception {


		// 2개 주소로부터 연결하기

		URLConnection userConn = new URI("https://jsonplaceholder.typicode.com/users").toURL().openConnection();
		URLConnection todoConn = new URI("https://jsonplaceholder.typicode.com/todos").toURL().openConnection();
		Gson gson = new GsonBuilder().setPrettyPrinting().create();

		String userJsonStr = "";
		String todoJsonStr = "";
		String lineStr = "";
		StringBuilder sb = null;

		BufferedWriter bw = null;
		
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
				
				br.close();
			}
			// 2.gson을 사용해 json문자열->List<Post>

			List<User> userList = gson.fromJson(userJsonStr, new TypeToken<List<User>>() {
			}.getType());

			// user 값 읽어와서 jsonStr에 저장하기
			if (todoConn != null) {// 연결됐다면
				// todo 정보 받아오기
				BufferedReader br = new BufferedReader(new InputStreamReader(todoConn.getInputStream()));

				sb = new StringBuilder();
				while ((lineStr = br.readLine()) != null) {
					sb.append(lineStr);
				}
				todoJsonStr = sb.toString();
				
				br.close();
			} // if

			// 2.gson을 사용해 json문자열->List<Post>
			List<Todo> todoList = gson.fromJson(todoJsonStr, new TypeToken<List<Todo>>() {
			}.getType());

			// 사용자별 todo의 전체개수와 완료한 todo의 개수를 아래 형식으로 todocount.log 파일에 저장
			// 사용자별 : userId 기준으로 분류
			// todo의 userId랑 user의 id가 동일
			
			//사용자별 todo의 전체개수
			//todoList를 userId로 groupby > key: userId, value: isCompleted
			Map<Integer, List<Boolean>> todoMap 
				= todoList.stream()
					.collect(
							Collectors.groupingBy(
									Todo::getUserId,
									Collectors.mapping(Todo::isCompleted, Collectors.toList())
							)
					);


			//users에서 키:id, 값:이름 
			Map<Integer, String> nameMap 
			= userList.stream()
				.collect(Collectors.toMap(User::getId, User::getName));
			
			// 파일 출력 형식: 사용자명 [전체todo수:00개, 완료todo수:00개]
			// 사용자별 todo의 전체개수와 완료한 todo의 개수를 아래 형식으로 todocount.log 파일에 저장
			bw = new BufferedWriter(new FileWriter("/Users/yeji/eclipse-workspace/코딩 테스트/todocount.log"));
			for(Integer i : todoMap.keySet()) {
				long doneCnt = todoMap.get(i).stream().filter(v-> v==true).count(); //완료된거 수 계산
				bw.write(nameMap.get(i) + " [전체todo수:" + todoMap.get(i).size() + "개, 완료todo수:" + doneCnt + "개]\n");
			}
			bw.flush();
			

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			bw.close();
		}

	}// main

}// class
