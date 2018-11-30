import java.io.FileNotFoundException;
import java.io.FileReader;

import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;  

public class LoginDao {
	public static boolean validate(String name,String pass){
		Gson gson = new Gson();
		User[] users;
		try {
			users = gson.fromJson(new FileReader(User.FILENAME), User[].class);
		} catch (JsonSyntaxException | JsonIOException | FileNotFoundException e1) {
			System.out.println("Error reading users.json");
			return false;
		}
		if(users == null) {
			return false;
		}
		for(int i=0; i<users.length; i++) {
			if(users[i].getUserName().equals(name) && users[i].getPassword().equals(pass)) {
				return true;
			}
		}
		return false;
	}

	public static boolean userExists(String name) {
		Gson gson = new Gson();
		User[] users;
		try {
			users = gson.fromJson(new FileReader(User.FILENAME), User[].class);
		} catch (JsonSyntaxException | JsonIOException | FileNotFoundException e1) {
			System.out.println("Error reading users.json");
			return false;
		}
		if(users != null) {
			for(int i=0; i<users.length; i++) {
				if(users[i].getUserName().equals(name)) {
					System.out.println("LoginDao.userExists: Found duplicate user " + users[i].getUserName());
					return true;
				}
			}
		}
		return false;
	}
}  