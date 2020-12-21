package CHM.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import CHM.model.Payment;
import CHM.service.AuthService;
import CHM.service.PaymentService;
import CHM.util.InvalidPaymentException;

@Controller
public class PaymentController {

	PaymentService paymentService;
	
	AuthService authService;
	
	@Autowired
	public void setAuthService(AuthService authService) {
		this.authService = authService;
	}
	
	@Autowired
	public void setPaymentService(PaymentService paymentService) {
		this.paymentService = paymentService;
	}
	
	@SuppressWarnings("finally")
	@RequestMapping(path = "/payment", method = RequestMethod.POST)
	@ResponseBody
	@CrossOrigin
	public ResponseEntity<Integer> createPayment(@RequestBody Payment payment, HttpServletRequest request) {
		
		int profileId = authService.profileIdFromToken(request.getHeader("auth").toString());
		Integer newPaymentId;
		ResponseEntity<Integer> re = new ResponseEntity<Integer>(new Integer(-1), HttpStatus.BAD_REQUEST);
			try {
				newPaymentId = new Integer(paymentService.createPayment(payment));
				if (newPaymentId != -1) {
					re = new ResponseEntity<Integer>(newPaymentId, HttpStatus.CREATED);
				}
			} catch (InvalidPaymentException e) {
				e.printStackTrace();
			} finally {
				return re;
			}
	}
	
	@RequestMapping(path = "/payment/{id}", method = RequestMethod.GET)
	@ResponseBody
	@CrossOrigin
	public ResponseEntity<Payment> readPaymentById(@PathVariable(name = "id")int paymentId, HttpServletRequest request) {
		
		int profileId = authService.profileIdFromToken(request.getHeader("auth").toString());
		Payment payment = paymentService.readPaymentById(paymentId);
		ResponseEntity<Payment> re = new ResponseEntity<Payment>(payment, payment == null ? HttpStatus.BAD_REQUEST : HttpStatus.OK); 
		return re;
	}
	
	@RequestMapping(path = "/payment", method = RequestMethod.GET)
	@ResponseBody
	@CrossOrigin
	public ResponseEntity<List<Payment>> readAllPayments(HttpServletRequest request) {
		
		int profileId = authService.profileIdFromToken(request.getHeader("auth").toString());
		List<Payment> paymentList = paymentService.readAllPayments();
		ResponseEntity<List<Payment>> re = new ResponseEntity<List<Payment>>(paymentList, paymentList == null ? HttpStatus.BAD_REQUEST : HttpStatus.OK);
		return re;
	}
	
	@RequestMapping(path = "/payment/profile/{id}", method = RequestMethod.GET)
	@ResponseBody
	@CrossOrigin
	public ResponseEntity<Payment> readPaymentByProfileId(@PathVariable(name = "id")int paymentId, HttpServletRequest request) {
		
		int profileId = authService.profileIdFromToken(request.getHeader("auth").toString());
		Payment payment = paymentService.readPaymentByProfileId(paymentId);
		ResponseEntity<Payment> re = new ResponseEntity<Payment>(payment, payment == null ? HttpStatus.BAD_REQUEST : HttpStatus.OK); 
		return re;
	}
	
	@SuppressWarnings("finally")
	@RequestMapping(path = "/payment", method = RequestMethod.PATCH)
	@ResponseBody
	@CrossOrigin
	public ResponseEntity<Payment> updatePayment(@RequestBody Payment payment, HttpServletRequest request) {
		
		int profileId = authService.profileIdFromToken(request.getHeader("auth").toString());
		Payment updatedPayment = null;
		ResponseEntity<Payment> re = new ResponseEntity<Payment>(updatedPayment, HttpStatus.BAD_REQUEST);
		try {
			updatedPayment = paymentService.updatePayment(payment);
			if (updatedPayment != null) {
				re = new ResponseEntity<Payment>(updatedPayment, HttpStatus.OK);
			}
		} catch (InvalidPaymentException e) {
			e.printStackTrace();
		} finally {
			return re;
		}
	}
	
	@RequestMapping(path = "/payment", method = RequestMethod.DELETE)
	@ResponseBody
	@CrossOrigin
	public ResponseEntity<Boolean> deletePayment(@RequestBody Payment payment, HttpServletRequest request){
		
		int profileId = authService.profileIdFromToken(request.getHeader("auth").toString());
		Boolean deleted = paymentService.deletePayment(payment);
		ResponseEntity<Boolean> re = new ResponseEntity<Boolean>(deleted, deleted ? HttpStatus.OK: HttpStatus.BAD_REQUEST);
		return re;
	}
}
