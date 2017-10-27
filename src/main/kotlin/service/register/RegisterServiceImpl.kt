package service.register

import model.entity.utils.EntityPropertyGenerator
import workflow.request.RegisterCompanyRequest
import workflow.request.RegisterUserRequest
import org.springframework.stereotype.Service
import repository.CompanyRepository
import repository.UserRepository
import utils.converter.RequestConverter

@Service
class RegisterServiceImpl(val userRepository: UserRepository,
                          val companyRepository: CompanyRepository,
                          val converter: RequestConverter,
                          val entityPropertyGenerator: EntityPropertyGenerator) : RegisterService {

    override fun registerUser(request: RegisterUserRequest): Boolean {
        val userToBeRegistered = converter.convertRegisterUserRequestToEntity(request)

        if (userRepository.findUserByUsername(userToBeRegistered.username) != null)
            return false

        userRepository.save(userToBeRegistered)
        return true
    }

    override fun registerCompany(request: RegisterCompanyRequest) {
        val convertedCompany = converter.convertRegisterCompanyRequestToEntity(request)
        convertedCompany.companyCode = entityPropertyGenerator.generateCompanyCode()
        companyRepository.save(convertedCompany)
    }
}