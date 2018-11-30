import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.UUID;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;

/**
 * @author Abdul Ghiasy
 * <br/>
 * <b>Note:</b><br/> This class consists of the specified variables, and involves
 * the specified getter/setter methods to achieve encapsulation for the user class;
 */

public class User {
	public static final String FILENAME = "/home/fayezg/Desktop/users.json";
    private String userId = UUID.randomUUID().toString();
    private String userEmail;
    private String userName;
    private String password;
    ArrayList<Note> notes = new ArrayList<Note>();
    ArrayList<Deadline> deadlines = new ArrayList<Deadline>();

    public User(String userEmail, String userName, String password) {
        this.userEmail = userEmail;
        this.userName = userName;
        this.password = password;
    }
    
/**
 * list of created user accounts will be saved onto json file, and
 * the use of gson here is to deserialize the specified json into an object of
 * the specified class
 */
    
    public static void saveNote(String username, Note note) {
    	// get current users from file
    	User[] users = null;
    	Gson gson = new Gson();
    	try {
			users = gson.fromJson(new FileReader(User.FILENAME), User[].class);
		} catch (JsonSyntaxException | JsonIOException | FileNotFoundException e) {
			System.out.print("User.saveNote: Can't read file: " + e.getMessage());
		}
    	if(users != null) {
    		ArrayList<User> list = new ArrayList<User>(Arrays.asList(users));
    		// find user
    		for(int i=0; i<list.size(); i++) {
    			if(list.get(i).getUserName().equals(username)) {
    	    		// copy user to newUser
    				User newUser = User.getUser(username);
    	    		// add note to newUser
    				newUser.addNote(note);
    				// update user in users with newUser
    				list.set(i, newUser);
    			}
    		}	
    		users = list.toArray(users);
    	}
    	// save updated user to file
    	try (Writer writer = new FileWriter(User.FILENAME)) {
    	    Gson gsonb = new GsonBuilder().create();
    	    gsonb.toJson(users, writer);
			System.out.println("User.save: User " + users[users.length-1].userName + " saved ...");
		} catch (JsonIOException | IOException e) {
			System.out.print("User.save: Can't write to users.json file...");
			e.printStackTrace();
		}
    }
    
    public static void saveDeadline(String username, Deadline deadline) {
    	// get current users from file
    	User[] users = null;
    	Gson gson = new Gson();
    	try {
			users = gson.fromJson(new FileReader(User.FILENAME), User[].class);
		} catch (JsonSyntaxException | JsonIOException | FileNotFoundException e) {
			System.out.print("User.saveNote: Can't read file: " + e.getMessage());
		}
    	if(users != null) {
    		ArrayList<User> list = new ArrayList<User>(Arrays.asList(users));
    		// find user
    		for(int i=0; i<list.size(); i++) {
    			if(list.get(i).getUserName().equals(username)) {
    	    		// copy user to newUser
    				User newUser = User.getUser(username);
    	    		// add deadline to newUser
    				newUser.addDeadline(deadline);
    				// update user in users with newUser
    				list.set(i, newUser);
    			}
    		}	
    		users = list.toArray(users);
    	}
    	// save updated user to file
    	try (Writer writer = new FileWriter(User.FILENAME)) {
    	    Gson gsonb = new GsonBuilder().create();
    	    gsonb.toJson(users, writer);
			System.out.println("User.save: User " + users[users.length-1].userName + " saved ...");
		} catch (JsonIOException | IOException e) {
			System.out.print("User.save: Can't write to users.json file...");
			e.printStackTrace();
		}
    }
    
    public static void save(User u) {
    	// get current users from file
    	User[] users = null;
    	Gson gson = new Gson();
    	try {
			users = gson.fromJson(new FileReader(User.FILENAME), User[].class);
		} catch (JsonSyntaxException | JsonIOException | FileNotFoundException e) {
			System.out.print("User.save: Can't read file: " + e.getMessage());
		}
    	// create new user
    	if(users == null) {
    		users = new User[1];
    		users[0] = u;
    	} else {
    		ArrayList<User> list = new ArrayList<User>(Arrays.asList(users));
    		list.add(u);
    		users = new User[list.size()];
    		users = list.toArray(users);
    	}
    	// save new user to file
    	try (Writer writer = new FileWriter(User.FILENAME)) {
    	    Gson gsonb = new GsonBuilder().create();
    	    gsonb.toJson(users, writer);
			System.out.println("User.save: User " + users[users.length-1].userName + " saved ...");
		} catch (JsonIOException | IOException e) {
			System.out.print("User.save: Can't write to users.json file...");
			e.printStackTrace();
		}
	}
    
/**
 * getters and setters used here to encapsulate the particular fields, but keep
 * the values themselves private
 */
    
	public String getUserId() {
        return userId;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
        save(this);
    }

    public void setPassword(String password) {
        this.password = password;
        save(this);
    }

    public void setUserName(String userName) {
        this.userName = userName;
        save(this);
    }

    public void addNote(Note note) {
        notes.add(note);
    }

    public void removeNote(String noteId) {
        for(int i = 0; i < notes.size(); i++) {
            if(notes.get(i).noteId == noteId) {
                notes.remove(notes.get(i));
                save(this);
            }
        }
    }

    public void addDeadline(Deadline deadline) {
        deadlines.add(deadline);
    }

    public void removeDeadline(String deadlineId) {
        for(int i = 0; i < deadlines.size(); i++) {
            if(deadlines.get(i).deadlineId == deadlineId) {
                deadlines.remove(deadlines.get(i));
                save(this);
            }
        }
    }
    
    public static User getUser(String username) {
    	Gson gson = new Gson();
		User[] users;
		try {
			users = gson.fromJson(new FileReader(User.FILENAME), User[].class);
		} catch (JsonSyntaxException | JsonIOException | FileNotFoundException e1) {
			System.out.println("Error reading users.json");
			return null;
		}
		if(users != null) {
			for(int i=0; i<users.length; i++) {
				if(users[i].getUserName().equals(username)) {
					System.out.println("User.getUser: Found user " + users[i].getUserName() + " in users.json ...");
					return users[i];
				}
			}
		}
		return null;
    }
}
