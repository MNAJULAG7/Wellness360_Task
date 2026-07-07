package com.project.authentication.service;

import com.project.authentication.dto.UpdateRequest;
import com.project.authentication.exception.UserNotFoundException;
import com.project.authentication.model.User;
import com.project.authentication.model.UserResonse;
import com.project.authentication.repo.UserRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

@Service
public class UserServiceImple implements UserService{

    @Autowired
    UserRepo userRepo;

    @Autowired
    ModelMapper modelMapper;
    @Override
    public UserResonse getById(Long id) {
        User user = userRepo.findById(id).orElseThrow(()->new UserNotFoundException(id));
        UserResonse ur = modelMapper.map(user,UserResonse.class);
        return ur;
    }

    @Override
    public UserResonse updateById(Long id, UpdateRequest request) {
        User user = userRepo.findById(id).orElseThrow(()->new UserNotFoundException(id));
        user.setName(request.getName());
        user.setUsername(request.getUsername());
        user.setMail(request.getMail());
        user.setMobile(request.getMobile());
        User us = userRepo.save(user);
        UserResonse ur = modelMapper.map(us,UserResonse.class);
        return  ur;
    }

    @Override
    public void deleteById(Long id) {

        if(!userRepo.existsById(id))
            throw new UserNotFoundException(id);
        userRepo.deleteById(id);

    }
}
