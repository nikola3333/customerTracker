package controller;

import java.util.List;

import org.hibernate.engine.internal.Nullability;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.sun.tracing.dtrace.Attributes;

import entity.Customer;
import service.CustomerService;

@Controller
@RequestMapping("/customer")
public class CustomerController {
	
	// need to inject customer service
	@Autowired
	private CustomerService customerService;
	
	@GetMapping("/list")
	public String listCustomers(Model theModel) {
		
		// get customer from the service
		List<Customer> theCustomers = customerService.getCustomers();
		
		// add the customer to the model
		theModel.addAttribute("customers", theCustomers);
		
		return "list-customers";
	}
	
	@GetMapping("/showFormForAdd")
	public String showFormForAdd(Model theModel) {
		
		// create model attribute to bind form data
		Customer theCustomer = new Customer();
		
		theModel.addAttribute("customer", theCustomer);
		
		return "customer-form";
	}
	
	
	@PostMapping("/saveCustomer")
	public String saveCustomer(@ModelAttribute("customer") Customer theCustomer) {
		
		//System.out.println("Customer je: " + theCustomer);
		
		//save the customer using our service
		customerService.saveCustomer(theCustomer);
		
		
		return "redirect:/customer/list";
		
	}
	
	@GetMapping("/loginCustomer")
	public String loginCustomer(Model model, Customer customer) {
		
		
		
		loginCustomer(customer, model);
		
		return "redirect:/customer/list";
		
	
	}
	
	@PostMapping("/loginCustomer")
	public String loginCustomer(@ModelAttribute("customer") Customer theCustomer, Model theModel) {
	
		String loggedEmail = theCustomer.getEmail();
		Customer loggedCustomer = customerService.getCustomerByEmail(loggedEmail);
		System.out.println("ULOGOVANI KORISNIK JE: " + loggedCustomer);
		
		
		String sentEmail = theCustomer.getEmail();
		String sentPassword = theCustomer.getPassword();
		
		Customer tempCustomer = customerService.getCustomerByEmail(sentEmail);
		
		
		//System.out.println("Customer je: " + tempCustomer);
		
		List<Customer> theCustomers = customerService.getCustomers();
		
		for (Customer temp : theCustomers) {
			
			if(temp.getEmail().equals(sentEmail)) {
				if(temp.getPassword().equals(sentPassword)) {
					System.out.println("Successfully logged!!");
					
					theModel.addAttribute("loggedCustomer", tempCustomer);
					
					return "redirect:/customer/list";
					
				}
			}else {
				
			}
			
		}
		
		System.out.println("WRONGGGGG!!! Wrong email or password!");
		return "redirect:/customer/login";
		
		
	}
	
	@GetMapping("/showFormForUpdate")
	public String showFormForUpdate(@RequestParam("customerId") int itheId, Model theModel) {
		
		//get the cust from the service
		Customer theCustomer = customerService.getCustomers(itheId);
		
		//set cust as model attribute to pre-populate the form
		theModel.addAttribute("customer", theCustomer);
		
		//send over to our form
		return "customer-form";
		
	}
	
	@GetMapping("/delete")
	public String deleteCustomer(@RequestParam("customerId") int theId) {
		
		//delete the customer
		customerService.deleteCustomer(theId);
		
		return "redirect:/customer/list";
		
	}
	
	@GetMapping("/addFriend")
	public String addFriend(@RequestParam("customerId") int theId) {
		
		//add customer as friend
		customerService.addFriend(theId);
		
		return "redirect:/customer/list";
		
	}
	
	@GetMapping("/login")
	public String login(Model theModel) {
		
		// get customer from the service
		List<Customer> theCustomers = customerService.getCustomers();
		
		// add the customer to the model
		theModel.addAttribute("customers", theCustomers);
		
		return "loginPage";
	}
	
	@GetMapping("/signUpPage")
	public String signUpPage() {
		
		return "signUpPage";
		
	}
	
	@PostMapping("/signUpCustomer")
	public String signUpCustomer(@ModelAttribute("customer") Customer theCustomer) {
		
		String sentFirstName = theCustomer.getFirstName();
		String sentLastName = theCustomer.getLastName();
		String sentEmail = theCustomer.getEmail();
		String sentPassword = theCustomer.getPassword();
		
		Customer temp = new Customer();
		temp.setFirstName(sentFirstName);
		temp.setLastName(sentLastName);
		temp.setEmail(sentEmail);
		temp.setPassword(sentPassword);
		
		//System.out.println("Customer koji se SignUpuje: " + temp);
		
		//save the customer using our service
		customerService.saveCustomer(temp);
								
		return "redirect:/customer/list";
		
		
	}
	
	@GetMapping("/sendMessage")
	public String sendMessage(@RequestParam("customerId") int itheId, Model theModel, @ModelAttribute("customer") Customer customer) {
		
		//get the cust from the service
		Customer theCustomer = customerService.getCustomers(itheId);
		
		//set cust as model attribute to pre-populate the form
		theModel.addAttribute("customer", customer);
		
		//set cust as model attribute to pre-populate the form
		theModel.addAttribute("customerReciever", theCustomer);
		
		//send over to our form
		return "messaging";
		
	}
	
	@PostMapping("/MessageSent")
	public String MessageSent(Model theModel, @ModelAttribute("customer") Customer customer, @ModelAttribute("senderId") Customer customerReciever, @ModelAttribute("Message") String message, @ModelAttribute("id") String id, @ModelAttribute("tempCustomer") String senderId) {
		
		//Customer sender = customerService.getCustomers(Integer.parseInt(senderId));
		Customer sender = customerService.getCustomers(15);
		Customer reciever = customerService.getCustomers(Integer.parseInt(id));

		
		System.out.println("customerId: " + senderId);
		System.out.println("id: " + id);
		
		System.out.println("SALJE: " + sender.getFirstName() + " " + sender.getLastName());
		System.out.println("PRIMA: " + reciever.getFirstName() + " " + reciever.getLastName());
		System.out.println("TEXT: " + message);
		
		//send over to our form
		return "redirect:/customer/list";
		
	}
	

}
