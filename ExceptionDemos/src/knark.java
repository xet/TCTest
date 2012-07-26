
public class knark {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		UserList users = new UserList();
		System.out.println("ADDING USER PER: ");
		try {
			users.addUser("per");
		}
		catch (UserNameAlreadyExistsException e) {
			System.out.println("FAILED (Number 1). Username per already exists in list");
			// TODO Auto-generated catch block
			// e.printStackTrace();
		}
		System.out.println("ADDING USER PER (AGAIN): ");
		try {
			users.addUser("per");
		}
		catch (UserNameAlreadyExistsException e) {
			System.out.println("FAILED (Number 2). Username per already exists in list");
			// TODO Auto-generated catch block
			// e.printStackTrace();
		}
	}

}
