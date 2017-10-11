package service

import model.entity.UserEntity
import repository.UserRepository
import org.springframework.stereotype.Service

@Service
class RegisterServiceImpl(var userRepository: UserRepository) : RegisterService {

    override fun registerUser() {
        userRepository.save(UserEntity().apply {
            username = "123"
            password = "1234"
        })

        var user = userRepository.findOne(1)
        var check = ""
    }
}