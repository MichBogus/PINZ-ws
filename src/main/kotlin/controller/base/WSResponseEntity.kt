package controller.base

import model.base.BaseWebserviceResponse
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity

class WSResponseEntity<R : BaseWebserviceResponse>(response: R, status: HttpStatus) : ResponseEntity<R>(response, status)