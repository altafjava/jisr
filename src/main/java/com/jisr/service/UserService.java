package com.jisr.service;

import com.jisr.dto.UserDTO;
import com.jisr.model.User;

public interface UserService {

	User registerUser(UserDTO userDTO);

}
