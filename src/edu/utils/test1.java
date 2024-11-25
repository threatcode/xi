package edu.utils;

import java.security.NoSuchAlgorithmException;
import java.util.List;

import edu.dao.UserDAO;
import edu.dto.UserDTO;

public class test1 {
	
	public static void main(String[] args) throws NoSuchAlgorithmException {
//		System.getProperty(key)
		//System.out.println(System.getProperty("os.name").toLowerCase());
		
		UserDAO uDAO = new UserDAO();
		
		List<UserDTO> userList = uDAO.getUserList("college");
		
		Mdjavahash hash = new Mdjavahash();
		UserDTO user = null;
		String enc =null;
		
		for(int i = 0; i<userList.size(); i++ )
		{			 
			user=userList.get(i);
			
			enc =hash.getHashPass(user.getPassword());
			
			int a = UserDAO.updateUser(user.getUserid(), enc);
					
			
		}
		
		
		
	}

}
