package service.register

import model.entity.utils.EntityPropertyGenerator
import org.springframework.stereotype.Service
import repository.CompanyRepository
import repository.UserRepository
import utils.converter.RequestConverter
import workflow.request.RegisterCompanyRequest
import workflow.request.RegisterUserRequest

@Service
class RegisterServiceImpl(val userRepository: UserRepository,
                          val companyRepository: CompanyRepository,
                          val converter: RequestConverter,
                          val entityPropertyGenerator: EntityPropertyGenerator) : RegisterService {

    override fun registerUser(request: RegisterUserRequest): Boolean {
        val userToBeRegistered = converter.convertRegisterUserRequestToEntity(request)

        if (companyRepository.findByCompanyCode(userToBeRegistered.companyCode) == null)
            return false

        if (userRepository.findUserByUsername(userToBeRegistered.username) != null)
            return false

        userRepository.save(userToBeRegistered)
        return true
    }

    override fun registerCompany(request: RegisterCompanyRequest): String {
        val convertedCompany = converter.convertRegisterCompanyRequestToEntity(request)
        convertedCompany.companyCode = entityPropertyGenerator.generateCompanyCode()
        companyRepository.save(convertedCompany)
        return convertedCompany.companyCode
    }
}