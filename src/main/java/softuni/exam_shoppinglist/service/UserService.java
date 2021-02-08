package softuni.exam_shoppinglist.service;

import softuni.exam_shoppinglist.model.service.UserServiceModel;

public interface UserService {
    UserServiceModel registerUser(UserServiceModel userServiceModel);

    UserServiceModel authenticate(String username, String password);

    void login(UserServiceModel userAuthenticate);
}
