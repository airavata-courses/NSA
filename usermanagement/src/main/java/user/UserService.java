package user;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.nsa.webapp.LoginService;
import com.nsa.webapp.LoginServiceImpl;

import com.nsa.webapp.UserRepository;

//import org.lognet.springboot.grpc.GRpcService;

import io.grpc.stub.StreamObserver;
import proto_files.User.Empty;
import proto_files.User.LoginRequest;
import proto_files.User.LoginRequestAPIResponse;
import proto_files.userGrpc.userImplBase;

@Service
public class UserService  extends userImplBase implements LoginService{
	
	private UserRepository repo;
	private LoginService loginservice;
	
	@Override
	public void login(LoginRequest request, StreamObserver<LoginRequestAPIResponse> responseObserver) {
		System.out.println("Inside User service");
		
		String username= request.getUsername();
		String password= request.getPassword();
//		
		LoginRequestAPIResponse.Builder response = LoginRequestAPIResponse.newBuilder();
		//System.out.println(response);
		
		try {
			System.out.println(username);
	UserDetails userdetails=this.loadUserByUsername(username);

	}
	catch(Exception e) {
		System.out.println(e);
	}

//	
		
		if(username.equals(password)) {
			
			response.setResponseCode(0).setResponsemessage("SUCCESS");
	
		}
		else {
//			//return failure message
			response.setResponseCode(100).setResponsemessage("FAILURE");
		}
//		
//		
		responseObserver.onNext(response.build());
		responseObserver.onCompleted();
	}
//
	@Override
	public void logout(Empty request, StreamObserver<LoginRequestAPIResponse> responseObserver) {
//		
	}
	
	//API implementation
	
	
//	
//	@Autowired
//	AuthenticationProvider authenticationProvider;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		
		com.nsa.webapp.User user= repo.findByUsername(username);
		System.out.println("In the after method"+user.toString());
		if(user==null) {
			throw new UsernameNotFoundException("USer 404");
		}
		return new LoginServiceImpl(user);
	}
	

}
