package webappgroup.webappartifact.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import webappgroup.webappartifact.dao.QuestionService;
import webappgroup.webappartifact.entity.Question;
 
@RestController
@RequestMapping("/Restapi/*")
public class MainRESTController {
 
	@Autowired
	private QuestionService questionService;
 
    @RequestMapping("/rest")
    @ResponseBody
    public String welcome() {
        return "Welcome to RestTemplate Example.";
    }
 
    // URL:
    // http://localhost:8080/SpringMVCRESTful/employees
    // http://localhost:8080/SpringMVCRESTful/employees.xml
    // http://localhost:8080/SpringMVCRESTful/employees.json
    @RequestMapping(value = "/questions", //
            method = RequestMethod.GET, //
            produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
    @ResponseBody
    public List<Question> getEmployees() {
        List<Question> list = questionService.getQuestions();
        return list;
    }
 
    // URL:
    // http://localhost:8080/SpringMVCRESTful/employee/{empNo}
    // http://localhost:8080/SpringMVCRESTful/employee/{empNo}.xml
    // http://localhost:8080/SpringMVCRESTful/employee/{empNo}.json
//    @RequestMapping(value = "/employee/{empNo}", //
//            method = RequestMethod.GET, //
//            produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
//    @ResponseBody
//    public Question getEmployee(@PathVariable("empNo") String empNo) {
//        return questionService.getEmployee(empNo);
//    }
 
    // URL:
    // http://localhost:8080/SpringMVCRESTful/employee
    // http://localhost:8080/SpringMVCRESTful/employee.xml
    // http://localhost:8080/SpringMVCRESTful/employee.json
//    @RequestMapping(value = "/employee", //
//            method = RequestMethod.POST, //
//            produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
//    @ResponseBody
//    public Employee addEmployee(@RequestBody Employee emp) {
// 
//        return employeeDAO.addEmployee(emp);
// 
//    }
 
    // URL:
    // http://localhost:8080/SpringMVCRESTful/employee
    // http://localhost:8080/SpringMVCRESTful/employee.xml
    // http://localhost:8080/SpringMVCRESTful/employee.json
//    @RequestMapping(value = "/employee", //
//            method = RequestMethod.PUT, //
//            produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
//    @ResponseBody
//    public Employee updateEmployee(@RequestBody Employee emp) {
// 
//        return employeeDAO.updateEmployee(emp);
//    }
// 
    // URL:
    // http://localhost:8080/SpringMVCRESTful/employee/{empNo}
//    @RequestMapping(value = "/employees/{empNo}", //
//            method = RequestMethod.DELETE, //
//            produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
//    @ResponseBody
//    public void deleteEmployee(@PathVariable("empNo") String empNo) {
//        employeeDAO.deleteEmployee(empNo);
//    }
// 
}