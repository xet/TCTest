import java.util.HashMap;


public class UserList {
	private HashMap<String,User> users;
	public UserList() {
		this.users = new HashMap<String,User>();
	}
	public void addUser(String username) throws UserNameAlreadyExistsException {
		if (this.users.containsKey(username)) { // Username already exists
			throw new UserNameAlreadyExistsException();
		}
		User tmpUser = new User();
		this.users.put(username, tmpUser);
		System.out.println(" OK.");
		
	}
}
