package service

import model.registerworkflow.RegisterCompanyRequest
import model.registerworkflow.RegisterUserRequest
import org.springframework.stereotype.Service
import repository.CompanyRepository
import repository.UserRepository
import utils.RequestConverter

@Service
class RegisterServiceImpl(val userRepository: UserRepository,
                          val companyRepository: CompanyRepository,
                          val converter: RequestConverter) : RegisterService {

    override fun registerUser(request: RegisterUserRequest) {
        userRepository.save(converter.convertRegisterUserRequestToEntity(request))
    }

    override fun registerCompany(request: RegisterCompanyRequest) {

    }

    private fun generateCompanyCode() {

    }
}