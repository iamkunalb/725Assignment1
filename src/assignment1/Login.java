package assignment1;
import java.io.*;
import org.json.*;

public class Login {
	private JSONArray usersArray;
	String text = "";
	String line = null;
	int LoggedIn = 2;
	int NotLoggedIn = 1;
	int needPass = 1;
	int needAcc = 1;
	int noAcc = 0;
	int wrongPass = 0;
	
	
	public Login() throws Exception {
		BufferedReader fileReader = new BufferedReader(new FileReader("login.txt"));
		
		while((line = fileReader.readLine()) != null) {
            text = text + line;
        }  
		
        fileReader.close();  
		 
		JSONObject obj = new JSONObject(text);
		usersArray = obj.getJSONArray("users");
	}
	
	public int checkUserID(int userID) {
		int len = usersArray.length();
		for (int i=0; i<len; i++) {
			try {
				if (usersArray.getJSONObject(i).getInt("id") == userID) {
					if ((!usersArray.getJSONObject(i).has("password"))) {
						return LoggedIn;
					} else {
						return NotLoggedIn;
					}
				}
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
		
		return noAcc;
	}
	
	public int checkAccount(int userID, String accountName) {
		int len = usersArray.length();
		for (int i=0; i<len; i++) {
			try {
				if (usersArray.getJSONObject(i).getInt("id") == userID) {
						if (usersArray.getJSONObject(i).getString("username").equals(accountName)) {
							if (usersArray.getJSONObject(i).has("password")) {
								return needPass;
							} else {
								return LoggedIn;
							}
						}
				}
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
		
		return noAcc;
	}
	
	public int checkPassword(int userID, String accountName, String password) {
		int len = usersArray.length();
		for (int i=0; i<len; i++) {
			try {
				if (usersArray.getJSONObject(i).getInt("id") == userID) {
					if (usersArray.getJSONObject(i).has("password")) {
						if (usersArray.getJSONObject(i).getString("password").equals(password)) {
							if ((accountName == null)) {
								return needAcc;
							} else {
								return LoggedIn;
							}
						} else {
							return wrongPass;
						}
					} else {
						return needAcc;
					}
				}
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}	
		
		return noAcc;
	}
}
