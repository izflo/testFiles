package gson.ex;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URLConnection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import gson.Post;

public class ExGson1 {

	public static void main(String[] args) throws Exception {

		// JSON문자열을 수신하여
		// List에 저장한 후
		// id값에 대하여 내림차순 정렬해서
		// 화면 출력

		// 1. https://jsonplaceholder.typicode.com/posts 로부터 json문자열 받아오기
		String uriStr = "https://jsonplaceholder.typicode.com/posts";
		// 주소에 연결
		URLConnection conn = new URI(uriStr).toURL().openConnection();

		if (conn != null) {// 연결됐다면
			// 주소로부터 내용 받아오기
			BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));

			String jsonStr = "";
			String lineStr = "";
			StringBuilder sb = new StringBuilder();
			while ((lineStr = br.readLine()) != null) {
				sb.append(lineStr);
			}
			jsonStr = sb.toString();

			// 2.gson을 사용해 json문자열->List<Post>
			Gson gson = new GsonBuilder().setPrettyPrinting().create();
			List<Post> postList = gson.fromJson(jsonStr, new TypeToken<List<Post>>() {
			}.getType());

			// 3. stream을 이용해서 정렬
			// id값에 대하여 내림차순 정렬해서
			Collections.sort(postList, new Comparator<Post>() {

				@Override
				public int compare(Post o1, Post o2) {
					return o2.getId() - o1.getId();
				}

			}); // collections

			postList.stream().forEach(System.out::println);
		}

		// 4. 출력

	}

}
