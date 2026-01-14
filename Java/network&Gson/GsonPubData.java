package gson;

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

public class GsonPubData {

	private static final String SERVICE_KEY = "53650c2acc1cfe15ce138fb3c2c3aad88c880d9d31029923e95340058997bf2f";
	private static final String SIDO_CODE = "11";
	private static final String PAGE_NO = "1";
	private static final String NUM_OF_ROWS = "100";

	// https://apis.data.go.kr/1613000/AptListService3/getSidoAptList3?
	//	serviceKey=53650c2acc1cfe15ce138fb3c2c3aad88c880d9d31029923e95340058997bf2f
	//	&sidoCode=27&pageNo=1&numOfRows=20

	private static final String END_POINT 
			= "https://apis.data.go.kr/1613000/AptListService3/getSidoAptList3?"
			+ "serviceKey=" + SERVICE_KEY + "&sidoCode=" + SIDO_CODE + "&pageNo=" + PAGE_NO + "&numOfRows="
			+ NUM_OF_ROWS;

	public static void main(String[] args) {

		try {
			URLConnection conn = new URI(END_POINT).toURL().openConnection();

			if (conn != null) {
				BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));

				String jsonStr = "";
				String lineStr = "";
				StringBuilder sb = new StringBuilder();
				while ((lineStr = br.readLine()) != null) {
					sb.append(lineStr);
				}
				jsonStr = sb.toString();

				Gson gson = new GsonBuilder().setPrettyPrinting().create();

				JsonObject jsonObject = gson.fromJson(jsonStr, JsonObject.class); // JsonObject.class : 어떤 객체로 변환할건지
				JsonObject response = jsonObject.getAsJsonObject("response");
				JsonObject body = response.getAsJsonObject("body");
				JsonArray items = body.getAsJsonArray("items");

				// 인터넷에서 json문자열 가져와서 java의 클래스 객체로 만드는 법
				List<Item> itemList = gson.fromJson(
						items, 
						new TypeToken<List<Item>>() {}.getType()
				);
				

				itemList.stream().forEach(item-> {
					System.out.printf(
							"아파트 코드:%s, \t아파트명:%s, \t도로명주소:%s %s %s\n",
							item.getKaptCode(), item.getKaptName(), item.getAs1(), item.getAs2(), item.getAs3());
				});
				
				
			} // if

		} catch (Exception e) {
			// TODO: handle exception
		}

	}// main

}// class
