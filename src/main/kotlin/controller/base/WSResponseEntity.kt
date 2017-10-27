package controller.base

import workflow.model.base.BaseWebserviceResponse
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity

class WSResponseEntity(response: BaseWebserviceResponse, status: HttpStatus) : ResponseEntity<BaseWebserviceResponse>(response, status)