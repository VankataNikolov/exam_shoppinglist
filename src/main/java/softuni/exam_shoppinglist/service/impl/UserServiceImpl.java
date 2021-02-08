package softuni.exam_shoppinglist.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam_shoppinglist.model.entity.UserEntity;
import softuni.exam_shoppinglist.model.service.UserServiceModel;
import softuni.exam_shoppinglist.repository.UserRepository;
import softuni.exam_shoppinglist.security.CurrentUser;
import softuni.exam_shoppinglist.service.UserService;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final CurrentUser currentUser;

    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper, CurrentUser currentUser) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.currentUser = currentUser;
    }

    @Override
    public UserServiceModel registerUser(UserServiceModel userServiceModel) {
        UserEntity newUser = this.userRepository.save(this.modelMapper.map(userServiceModel, UserEntity.class));

        return this.modelMapper.map(newUser, UserServiceModel.class);
    }

    @Override
    public UserServiceModel authenticate(String username, String password) {
        UserEntity byUsername = this.userRepository.findByUsername(username).orElse(null);

        if(byUsername != null && byUsername.getPassword().equals(password)){
            return this.modelMapper.map(byUsername ,UserServiceModel.class);
        }
        return null;
    }

    @Override
    public void login(UserServiceModel userAuthenticate) {
        this.currentUser.setId(userAuthenticate.getId());
        this.currentUser.setUsername(userAuthenticate.getUsername());
    }
}
