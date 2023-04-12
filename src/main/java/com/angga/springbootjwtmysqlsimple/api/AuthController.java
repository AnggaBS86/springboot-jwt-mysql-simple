package com.angga.springbootjwtmysqlsimple.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.angga.springbootjwtmysqlsimple.api.model.AuthenticateRequest;
import com.angga.springbootjwtmysqlsimple.api.model.AuthenticateResponse;
import com.angga.springbootjwtmysqlsimple.api.services.MyHttpRequestService;
import com.angga.springbootjwtmysqlsimple.api.services.UserService;
import com.angga.springbootjwtmysqlsimple.api.util.JwtUtil;

@RestController
public class AuthController {

	/**
	 * constants url
	 */
	private final String urlJobApi = "http://dev3.dansmultipro.co.id/api/recruitment/positions";

	/**
	 * @var AuthenticationManager
	 */
	@Autowired
	private AuthenticationManager authenticationManager;

	/**
	 * @var UserService
	 */
	@Autowired
	private UserService userService;

	@Autowired
	private JwtUtil jwtUtil;

	/**
	 * Hello world test
	 * 
	 * @return String
	 */
	@RequestMapping(value = "/hello-world")
	public String helloWorld() {
		return "Hello World";
	}

	/**
	 * Get job detail by ID
	 * 
	 * This method used for getting json data from `urlJobListDetail`
	 * Then getting the result as json
	 * 
	 * @param jobId String
	 * @returnString
	 */
	@RequestMapping(value = "/jobs/{jobId}", method = RequestMethod.GET, consumes = { "application/json" })
	public String getJobDetail(@PathVariable String jobId) {
		var urlJobListDetail = urlJobApi + "/" + jobId;
		return MyHttpRequestService.getResponseFromUrl(urlJobListDetail);
	}

	/**
	 * Get jobs data
	 * 
	 * This method used for getting jobs data
	 * 
	 * @return String
	 */
	@RequestMapping(value = "/jobs", method = RequestMethod.GET, consumes = { "application/json" })
	public String getJob() {
		var urlJobList = urlJobApi + ".json";
		return MyHttpRequestService.getResponseFromUrl(urlJobList);
	}

	/**
	 * This method used for authorization purpose
	 * With the username and password that filled in table `user`
	 * 
	 * @param request AuthenticateRequest
	 * @return ResponseEntity<?>
	 * 
	 * @throws Exception
	 */
	@PostMapping("/auth")
	public ResponseEntity<?> creatAuthToken(@RequestBody AuthenticateRequest request) throws Exception {
		try {
			authenticationManager
					.authenticate(
							new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
		} catch (BadCredentialsException ex) {
			throw new Exception("Incorrect Username and Password combination!", ex);
		}

		final UserDetails userDetails = userService.loadUserByUsername(request.getUsername());
		final String token = jwtUtil.generateToken(userDetails);

		return ResponseEntity.ok(new AuthenticateResponse(token));

	}
}
