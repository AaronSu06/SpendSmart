/* This class acts as the main class for all of the logic behind storing and saving information.
 */

package Model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DataModel {

	
	// file path instance variables
	public static final String USER_ACCOUNT_PATH = "Data/UserAccount.json";
	public static final String USER_INFO_PATH = "Data/UserInfo.json";
	public static final String TOTAL_PIE_PATH = "Data/TotalPieInfo.json";
	public static final String TODAY_PIE_PATH = "Data/TodayPieInfo.json";
	public static final String INCOME_PIE_PATH = "Data/IncomePieInfo.json";

	// INFORMATION FOR USER ACCOUNT
	public static void generateUserAccount(String username, String password) throws IOException {
		Gson gson = new GsonBuilder().setPrettyPrinting().disableHtmlEscaping().create();

		ArrayList<UserAccount> users = new ArrayList<UserAccount>();

		// get the user information stored in the .json file so that we don't overwrite
		// it (if it exists)
		List<UserAccount> userAccountList = getUserAccounts();

		// if not null and if not empty, add it to the user ArrayList
		if (userAccountList != null && userAccountList.size() > 0) {
			for (UserAccount userInfo : userAccountList) {
				users.add(userInfo);
			}
		}

		// add the current user information to the user ArrayList
		users.add(new UserAccount(username, password));

		// read and write to the .json file
		String jsonString = gson.toJson(users);
		try (FileWriter writer = new FileWriter(USER_ACCOUNT_PATH)) {
			writer.write(jsonString);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// method to get the user accounts from the .json file
	public static List<UserAccount> getUserAccounts() throws IOException {
		Gson gson = new Gson();
		FileReader reader = new FileReader(USER_ACCOUNT_PATH);

		// try to fetch the information in the specified file path from the .json file
		try {
			Type userInfoListType = new TypeToken<List<UserAccount>>() {
			}.getType();

			List<UserAccount> userInfoList = gson.fromJson(reader, userInfoListType);

			return userInfoList;

		} catch (Exception e) {
			return null;
		}
	}

	// INFORMATION FOR TOTAL PIE INFO
	public static void generateTotalPie(String username, Map<String, Double> graphInfo) throws IOException {
		Gson gson = new GsonBuilder().setPrettyPrinting().disableHtmlEscaping().create();

		ArrayList<UserPieTotal> users = new ArrayList<UserPieTotal>();

		// get the user information stored in the .json file so that we don't overwrite
		// it (if it exists)
		List<UserPieTotal> userAccountList = getTotalPie();

		// if not null and if not empty, add it to the user ArrayList
		if (userAccountList != null && userAccountList.size() > 0) {
			for (UserPieTotal userInfo : userAccountList) {
				// if the user already has existing information saved, don't add the information
				// so that we won't have duplicates of the same user
				if (!(userInfo.getUsername().equals(username))) {
					users.add(userInfo);
				}
			}
		}

		// add the current user information to the user ArrayList
		users.add(new UserPieTotal(username, graphInfo));

		// read and write to the .json file
		String jsonString = gson.toJson(users);
		try (FileWriter writer = new FileWriter(TOTAL_PIE_PATH)) {
			writer.write(jsonString);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// method to get the user info from the .json file
	public static List<UserPieTotal> getTotalPie() throws IOException {
		Gson gson = new Gson();
		FileReader reader = new FileReader(TOTAL_PIE_PATH);

		// try to fetch the information in the specified file path from the .json file
		try {
			Type userInfoListType = new TypeToken<List<UserPieTotal>>() {
			}.getType();

			List<UserPieTotal> userInfoList = gson.fromJson(reader, userInfoListType);

			return userInfoList;

		} catch (Exception e) {
			return null;
		}
	}

	// INFORMATION FOR TODAY PIE INFO
	public static void generateTodayPie(String username, Map<String, Double> graphInfo, String date) throws IOException {
		Gson gson = new GsonBuilder().setPrettyPrinting().disableHtmlEscaping().create();

		ArrayList<UserPieToday> users = new ArrayList<UserPieToday>();

		// get the user information stored in the .json file so that we don't overwrite
		// it (if it exists)
		List<UserPieToday> userAccountList = getTodayPie();

		// if not null and if not empty, add it to the user ArrayList
		if (userAccountList != null && userAccountList.size() > 0) {
			for (UserPieToday userInfo : userAccountList) {
				// if the user already has existing information saved, don't add the information
				// so that we won't have duplicates of the same user
				if (!(userInfo.getUsername().equals(username))) {
					users.add(userInfo);
				}
			}
		}

		// add the current user information to the user ArrayList
		users.add(new UserPieToday(username, graphInfo, date));

		// read and write to the .json file
		String jsonString = gson.toJson(users);
		try (FileWriter writer = new FileWriter(TODAY_PIE_PATH)) {
			writer.write(jsonString);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// method to get the user info from the .json file
	public static List<UserPieToday> getTodayPie() throws IOException {
		Gson gson = new Gson();
		FileReader reader = new FileReader(TODAY_PIE_PATH);

		// try to fetch the information in the specified file path from the .json file
		try {
			Type userInfoListType = new TypeToken<List<UserPieToday>>() {
			}.getType();

			List<UserPieToday> userInfoList = gson.fromJson(reader, userInfoListType);

			return userInfoList;

		} catch (Exception e) {
			return null;
		}
	}

	// INFORMATION FOR INCOME PIE INFO
	public static void generateIncomePie(String username, Map<String, Double> graphInfo) throws IOException {
		Gson gson = new GsonBuilder().setPrettyPrinting().disableHtmlEscaping().create();

		ArrayList<UserPieIncome> users = new ArrayList<UserPieIncome>();

		// get the user information stored in the .json file so that we don't overwrite
		// it (if it exists)
		List<UserPieIncome> userAccountList = getIncomePie();

		// if not null and if not empty, add it to the user ArrayList
		if (userAccountList != null && userAccountList.size() > 0) {
			for (UserPieIncome userInfo : userAccountList) {
				// if the user already has existing information saved, don't add the information
				// so that we won't have duplicates of the same user
				if (!(userInfo.getUsername().equals(username))) {
					users.add(userInfo);
				}
			}
		}

		// add the current user information to the user ArrayList
		users.add(new UserPieIncome(username, graphInfo));

		// read and write to the .json file
		String jsonString = gson.toJson(users);
		try (FileWriter writer = new FileWriter(INCOME_PIE_PATH)) {
			writer.write(jsonString);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// method to get the user info from the .json file
	public static List<UserPieIncome> getIncomePie() throws IOException {
		Gson gson = new Gson();
		FileReader reader = new FileReader(INCOME_PIE_PATH);

		// try to fetch the information in the specified file path from the .json file
		try {
			Type userInfoListType = new TypeToken<List<UserPieIncome>>() {
			}.getType();

			List<UserPieIncome> userInfoList = gson.fromJson(reader, userInfoListType);

			return userInfoList;

		} catch (Exception e) {
			return null;
		}
	}

	// INFORMATION FOR TOTAL PIE INFO
	public static void generateUserInfo(String username, double totalExpense, double todayExpense, double totalIncome)
			throws IOException {
		Gson gson = new GsonBuilder().setPrettyPrinting().disableHtmlEscaping().create();

		ArrayList<UserInfo> users = new ArrayList<UserInfo>();

		// get the user information stored in the .json file so that we don't overwrite
		// it (if it exists)
		List<UserInfo> userAccountList = getUserInfo();

		// if not null and if not empty, add it to the user ArrayList
		if (userAccountList != null && userAccountList.size() > 0) {
			for (UserInfo userInfo : userAccountList) {
				// if the user already has existing information saved, don't add the information
				// so that we won't have duplicates of the same user
				if (!(userInfo.getUsername().equals(username))) {
					users.add(userInfo);
				}
			}
		}

		// add the current user information to the user ArrayList
		users.add(new UserInfo(username, totalExpense, todayExpense, totalIncome));

		// read and write to the .json file
		String jsonString = gson.toJson(users);
		try (FileWriter writer = new FileWriter(USER_INFO_PATH)) {
			writer.write(jsonString);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// method to get the user info from the .json file
	public static List<UserInfo> getUserInfo() throws IOException {
		Gson gson = new Gson();
		FileReader reader = new FileReader(USER_INFO_PATH);

		// try to fetch the information in the specified file path from the .json file
		try {
			Type userInfoListType = new TypeToken<List<UserInfo>>() {
			}.getType();

			List<UserInfo> userInfoList = gson.fromJson(reader, userInfoListType);

			return userInfoList;

		} catch (Exception e) {
			return null;
		}
	}
}
