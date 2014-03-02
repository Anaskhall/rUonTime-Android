/**
 * 
 */
package se.kth.mobdev.ruontime.logic;

/**
 * @author Jasper
 *
 */
public class AuthenticationService {

	public boolean authenticateUser(String userName, String authenticationHash) {
		
		//authenticate user by comparing hash(challenge + userName + pwHash)
		return true;
	}
	
}
