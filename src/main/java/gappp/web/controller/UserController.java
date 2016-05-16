package gappp.web.controller;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLConnection;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.FileCopyUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.multipart.MultipartFile;

import gappp.model.Application;
import gappp.model.Degree;
import gappp.model.Department;
import gappp.model.OtherField;
import gappp.model.OtherFieldValue;
import gappp.model.Program;
import gappp.model.User;
import gappp.model.dao.ApplicationDao;
import gappp.model.dao.DegreeDao;
import gappp.model.dao.DepartmentDao;
import gappp.model.dao.OtherFieldDao;
import gappp.model.dao.OtherFieldValueDao;
import gappp.model.dao.ProgramDao;
import gappp.model.dao.RoleDao;
import gappp.model.dao.StatusDao;
import gappp.model.dao.UserDao;
import gappp.web.validator.LoginValidator;
import gappp.web.validator.RegisterValidator;

@Controller
@Scope("session")
public class UserController {

	@Autowired
    private ApplicationDao applicationDao;
	@Autowired
    private StatusDao statusDao;
    @Autowired
    private UserDao userDao;
    @Autowired
    private RoleDao roleDao;
    @Autowired
    private DepartmentDao departmentDao;
    @Autowired
    private ProgramDao programDao;
    @Autowired
    private DegreeDao degreeDao;
    @Autowired
    private OtherFieldDao otherFieldDao;
    @Autowired
    private OtherFieldValueDao otherFieldValueDao;
    
    @Autowired
    private RegisterValidator registerValidator;
    @Autowired
    private LoginValidator loginValidator;
    
    @Autowired
    private WebApplicationContext context;

    @RequestMapping("/users.html")
    public String users( ModelMap models )
    {
        models.put( "users", userDao.getUsers() );
        return "users";
    }

    @RequestMapping(value = "/login.html",method = RequestMethod.GET)
    public String login(ModelMap models)
    {
    	models.put("user", new User());
        return "login";
    }
    
    @RequestMapping(value = "/login.html",method = RequestMethod.POST)
    public String login(@ModelAttribute User user,BindingResult result,ModelMap models,HttpServletRequest request)
    {
    	loginValidator.validate(user, result);
    	if(result.hasErrors()) return "login";
    	
    	if(user.geteMail() != "" && user.getPassword() != ""){
    		User users = userDao.getUser(user.geteMail(), user.getPassword());
    		if(users == null){
    			models.put("invalid", "Invalid email or password...");
    			return "login";
    		}
    		else{
    			request.getSession().setAttribute("username",users.getFirstName() + " " + users.getLastName());
    			request.getSession().setAttribute("role",users.getRole().getRoleName());
    			request.getSession().setAttribute("email",users.geteMail());

    			if(users.getRole().getRoleName().equals("admin")){
        			return "redirect:adminhome.html";
        		}else if(users.getRole().getRoleName().equals("staff")){
        			return "redirect:staffhome.html";
        		}else {
        			return "redirect:studenthome.html";
        		}
    		}
    	}else{
    		return "login";
    	}
    }
    
    @RequestMapping(value = "/register.html",method = RequestMethod.GET)
    public String register(ModelMap models)
    {
    	models.put("user", new User());
        return "register";
    }
    
    @RequestMapping(value = "/register.html",method = RequestMethod.POST)
    public String register(@ModelAttribute User user,BindingResult result)
    {
    	registerValidator.validate(user, result);
    	if(result.hasErrors()) return "register";
    	
    	user.setRole(roleDao.getRole("student"));
    	userDao.saveUser(user);
        return "redirect:login.html";
    }
    
    @RequestMapping(value = "/staffhome.html",method = RequestMethod.GET)
    public String staffhome(HttpServletRequest request)
    {
    	if(request.getSession().getAttribute("role") != null){
    		if(!request.getSession().getAttribute("role").equals("student"))
    			return "redirect:login.html";
    	}else{
    		return "redirect:login.html";
    	}
        return "staffhome";
    }
    
    @RequestMapping(value = "/studenthome.html",method = RequestMethod.GET)
    public String studenthome(ModelMap models,HttpServletRequest request)
    {
    	if(request.getSession().getAttribute("role") != null){
    		if(!request.getSession().getAttribute("role").equals("student"))
    			return "redirect:login.html";
    	}else{
    		return "redirect:login.html";
    	}
    	models.put("applications", applicationDao.getApplicationsByEmail((String) request.getSession().getAttribute("email")));
        return "studenthome";
    }
    
    @RequestMapping(value = "/adminhome.html",method = RequestMethod.GET)
    public String adminhome(ModelMap models,HttpServletRequest request)
    {
    	if(request.getSession().getAttribute("role") != null){
    		if(!request.getSession().getAttribute("role").equals("admin"))
    			return "redirect:login.html";
    	}else{
    		return "redirect:login.html";
    	}
    	
    	models.put("department", new Department());
    	models.put("departments",departmentDao.getDepartment());
    	models.put("program", new Program());
    	models.put("programs", programDao.getProgram());
    	models.put("otherField", new OtherField());
    	models.put("otherFields", otherFieldDao.getOtherField());
        return "adminhome";
    }
    
    @RequestMapping(value = "/adminhome.html",method = RequestMethod.POST)
    public String adminhome(@RequestParam String action,@ModelAttribute Department department,
    		@ModelAttribute Program program,@ModelAttribute OtherField otherField,BindingResult result,
    		@RequestParam String prgmdeptname,@RequestParam String otherdeptname)
    {
    	if(action.equals("Add Department")){
    		departmentDao.saveDepartment(department);
            return "redirect:adminhome.html";
    	}else if(action.equals("Add Program")){
    		Department departmentObj = departmentDao.getDepartment(prgmdeptname);
    		program.setDepartment(departmentObj);
    		programDao.saveProgram(program);
            return "redirect:adminhome.html";
    	}else if(action.equals("Add Additional Requirement")){
    		Department departmentObj = departmentDao.getDepartment(otherdeptname);
    		otherField.setDepartment(departmentObj);
    		otherFieldDao.saveOtherField(otherField);
            return "redirect:adminhome.html";
    	}
    	return "redirect:adminhome.html";
    }
    
    @RequestMapping(value = "/deleteDpt.html")
    public String deleteDpt(HttpServletRequest request)
    {
    	Integer id = Integer.parseInt(request.getQueryString().split("=")[1]);
    	departmentDao.deleteDepartment(id);
    	return "redirect:adminhome.html";
    }
    
    @RequestMapping(value = "/deletePrgm.html")
    public String deletePrgm(HttpServletRequest request)
    {
    	Integer id = Integer.parseInt(request.getQueryString().split("=")[1]);
    	programDao.deleteProgram(id);
    	return "redirect:adminhome.html";
    }
    
    @RequestMapping(value = "/deleteOther.html")
    public String deleteOther(HttpServletRequest request)
    {
    	Integer id = Integer.parseInt(request.getQueryString().split("=")[1]);
    	otherFieldDao.deleteOtherField(id);
    	return "redirect:adminhome.html";
    }
    
    @RequestMapping(value = "/editDpt.html",method = RequestMethod.GET)
    public String editDpt(HttpServletRequest request,ModelMap models)
    {
    	Integer id = Integer.parseInt(request.getQueryString().split("=")[1]);
    	models.put("id", id);
    	models.put("departments", departmentDao.getDepartment(id));
    	return "editDpt";
    }
    
    @RequestMapping(value = "/editDpt.html",method = RequestMethod.POST)
    public String editDpt(@RequestParam String deptname,@RequestParam String id)
    {
    	departmentDao.updateDepartment(deptname, Integer.parseInt(id));
    	return "redirect:adminhome.html";
    }
    
    @RequestMapping(value = "/editPrgm.html",method = RequestMethod.GET)
    public String editPrgm(HttpServletRequest request,ModelMap models)
    {
    	Integer id = Integer.parseInt(request.getQueryString().split("=")[1]);
    	models.put("programs", programDao.getProgram(id));
    	models.put("departments",departmentDao.getDepartment());
    	return "editPrgm";
    }
    
    @RequestMapping(value = "/editPrgm.html",method = RequestMethod.POST)
    public String editPrgm(@RequestParam String progName,@RequestParam String id,@RequestParam String prgmdeptname)
    {
    	programDao.updateDepartment(progName, departmentDao.getDepartmentID(prgmdeptname), Integer.parseInt(id));
    	return "redirect:adminhome.html";
    }
    
    @RequestMapping(value = "/editOther.html",method = RequestMethod.GET)
    public String editOther(HttpServletRequest request,ModelMap models)
    {
    	Integer id = Integer.parseInt(request.getQueryString().split("=")[1]);
    	models.put("others", otherFieldDao.getOtherField(id));
    	models.put("departments",departmentDao.getDepartment());
    	return "editOther";
    }
    
    @RequestMapping(value = "/editOther.html",method = RequestMethod.POST)
    public String editOther(@RequestParam String nameField,@RequestParam String typeField
    	,@RequestParam Boolean required,@RequestParam Integer id,@RequestParam String otherdeptname)
    {
    	otherFieldDao.updateDepartment(nameField,typeField,required, departmentDao.getDepartmentID(otherdeptname), id);
    	return "redirect:adminhome.html";
    }
    
    @RequestMapping(value = "/viewApplication.html",method = RequestMethod.GET)
    public String viewApplication(ModelMap models,HttpServletRequest request,@RequestParam String applicationid)
    {
    	if(request.getSession().getAttribute("role") != null){
    		if(!request.getSession().getAttribute("role").equals("student"))
    			return "redirect:login.html";
    	}else{
    		return "redirect:login.html";
    	}
    	models.put("application", applicationDao.getApplication(Integer.parseInt(applicationid)));
        return "viewApplication";
    }
    
    @RequestMapping(value = "/addApplication.html",method = RequestMethod.GET)
    public String addApplication(ModelMap models,HttpServletRequest request)
    {
    	if(request.getSession().getAttribute("role") != null){
    		if(!request.getSession().getAttribute("role").equals("student"))
    			return "redirect:login.html";
    	}else{
    		return "redirect:login.html";
    	}
    	models.put("user", userDao.getUserByEmail((String) request.getSession().getAttribute("email")));
    	models.put("application", new Application());
    	models.put("departments",departmentDao.getDepartment());
        return "addApplication";
    }
    
    @RequestMapping(value = "/addApplication.html",method = RequestMethod.POST)
    public String addApplication(@ModelAttribute Application application,HttpServletRequest request,
    							 @RequestParam String deptname,@RequestParam String prgmname,@RequestParam String dateofbirth)
    {
    	Date date = new Date();
    	application.setApplicationDate(date);
    	if(!dateofbirth.equals(""))
    	{
    		DateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
        	try {
    			date = sdf.parse(dateofbirth);
    		} catch (ParseException e) {
    		}
    	}
    	application.setDob(date);
    	application.setCin(0);
    	application.setDepartment(departmentDao.getDepartment(deptname));
    	application.setProgram(programDao.getProgramByName(prgmname,deptname));
    	application.setStatus(statusDao.getStatusByName("Not Submitted"));
    	application.setUsers(userDao.getUserByEmail((String) request.getSession().getAttribute("email")));
    	Application newapplication = applicationDao.saveApplication(application);
        return "redirect:saveAcademic.html?applicationid=" + newapplication.getId();
    }
    
    @RequestMapping(value = "/saveAcademic.html",method = RequestMethod.GET)
    public String saveAcademic(ModelMap models,HttpServletRequest request,@RequestParam String applicationid)
    {
    	if(request.getSession().getAttribute("role") != null){
    		if(!request.getSession().getAttribute("role").equals("student"))
    			return "redirect:login.html";
    	}else{
    		return "redirect:login.html";
    	}
    	if(!applicationid.equals("")){
    		Application application = applicationDao.getApplication(Integer.parseInt(applicationid));
    		if(application != null){
    			models.put("degrees",degreeDao.getDegrees(Integer.parseInt(applicationid)));
    			models.put("degree", new Degree());
                return "saveAcademic";
    		}
    		else{
    			return "redirect:studenthome.html";
    		}
    	}else{
    		return "redirect:studenthome.html";
    	}
    }
    
    @RequestMapping(value = "/saveAcademic.html",method = RequestMethod.POST)
    public String saveAcademic(@ModelAttribute Degree degree,@RequestParam String appid,
    		@RequestParam MultipartFile transcriptfile) throws IllegalStateException, IOException
    {
    	degree.setTranscript("");
    	degree.setApplication(applicationDao.getApplication(Integer.parseInt(appid)));
    	degree = degreeDao.saveDegree(degree);
    	if(!transcriptfile.isEmpty()){
    		String originalFileName = transcriptfile.getOriginalFilename();
	    	String newFileName = originalFileName.substring(originalFileName.lastIndexOf('.'));
	    	newFileName = degree.getId() + newFileName;
	    	String path = context.getServletContext().getRealPath("/WEB-INF/files/" + newFileName);
	    	transcriptfile.transferTo(new File(path));
	    	degreeDao.updateDegreeFileName(degree.getId(), newFileName);
    	}
        return "redirect:saveAcademic.html?applicationid=" + appid;
    }
    
    @RequestMapping(value = "/downloadFile.html")
    public void downloadFile(@ModelAttribute Degree degree,@RequestParam String type,
    		@RequestParam String filename,@RequestParam String applicationid,
    		HttpServletResponse response) throws IllegalStateException, IOException
    {
    	String path = context.getServletContext().getRealPath("/WEB-INF/files/" + filename);
    	File file = new File(path);
    	
    	String mimeType= URLConnection.guessContentTypeFromName(file.getName());
		if(mimeType==null){
			mimeType = "application/octet-stream";
		}
        response.setContentType(mimeType);
        response.setHeader("Content-Disposition", String.format("attachment; filename=\"" + file.getName() +"\""));
        response.setContentLength((int)file.length());
		InputStream inputStream = new BufferedInputStream(new FileInputStream(file));
        FileCopyUtils.copy(inputStream, response.getOutputStream());
    }
    
    @RequestMapping(value = "/saveOtherRequiremt.html",method = RequestMethod.GET)
    public String saveOtherRequiremt(ModelMap models,HttpServletRequest request,@RequestParam String applicationid)
    {
    	if(request.getSession().getAttribute("role") != null){
    		if(!request.getSession().getAttribute("role").equals("student"))
    			return "redirect:login.html";
    	}else{
    		return "redirect:login.html";
    	}
    	if(!applicationid.equals("")){
    		Application application = applicationDao.getApplication(Integer.parseInt(applicationid));
    		if(application != null){
    			models.put("otherfields",otherFieldDao.getOtherField(application.getDepartment().getDeptName()));
    			models.put("otherFieldValue", new OtherFieldValue());
    			models.put("otherFieldValues",otherFieldValueDao.getOtherFieldValues(Integer.parseInt(applicationid)));
                return "saveOtherRequiremt";
    		}
    		else{
    			return "redirect:studenthome.html";
    		}
    	}else{
    		return "redirect:studenthome.html";
    	}
    }
    
    @RequestMapping(value = "/saveOtherRequiremt.html",method = RequestMethod.POST)
    public String saveOtherRequiremt(@ModelAttribute OtherFieldValue otherFieldValue,@RequestParam String appid,
    		@RequestParam String otherid)
    {
    	otherFieldValue.setApplication(applicationDao.getApplication(Integer.parseInt(appid)));
    	otherFieldValue.setOtherField((otherFieldDao.getOtherField(Integer.parseInt(otherid))));
    	otherFieldValueDao.saveOtherFieldValue(otherFieldValue);
        return "redirect:saveOtherRequiremt.html?applicationid=" + appid;
    }
    
    @RequestMapping(value = "/uploadOtherFile.html",method = RequestMethod.POST)
    public String uploadOtherFile(@ModelAttribute OtherFieldValue otherFieldValue,@RequestParam String appid,
    		@RequestParam String otherid,@RequestParam MultipartFile otherfile) throws IllegalStateException, IOException
    {
    	otherFieldValue.setApplication(applicationDao.getApplication(Integer.parseInt(appid)));
    	otherFieldValue.setOtherField((otherFieldDao.getOtherField(Integer.parseInt(otherid))));
    	otherFieldValue = otherFieldValueDao.saveOtherFieldValue(otherFieldValue);
    	if(!otherfile.isEmpty()){
    		String originalFileName = otherfile.getOriginalFilename();
	    	String newFileName = originalFileName.substring(originalFileName.lastIndexOf('.'));
	    	newFileName = "other_" + otherFieldValue.getId() + newFileName;
	    	String path = context.getServletContext().getRealPath("/WEB-INF/files/" + newFileName);
	    	otherfile.transferTo(new File(path));
	    	otherFieldValueDao.updateOtherFileName(otherFieldValue.getId(), newFileName);
    	}
        return "redirect:saveOtherRequiremt.html?applicationid=" + appid;
    }
    
    @RequestMapping(value = "/editApplication.html",method = RequestMethod.GET)
    public String editApplication(ModelMap models,HttpServletRequest request,@RequestParam String applicationid)
    {
    	if(request.getSession().getAttribute("role") != null){
    		if(!request.getSession().getAttribute("role").equals("student"))
    			return "redirect:login.html";
    	}else{
    		return "redirect:login.html";
    	}
    	Application application = applicationDao.getApplication(Integer.parseInt(applicationid));
    	models.put("application",application);
    	models.put("departments",departmentDao.getDepartment());
    	models.put("programs", programDao.getProgram(application.getDepartment().getDeptName()));
        return "editApplication";
    }
    
    @RequestMapping(value = "/editApplication.html",method = RequestMethod.POST)
    public String editApplication(@RequestParam String applicationid,@RequestParam String term,
    		@RequestParam String deptname,@RequestParam String prgmname,@RequestParam String firstName,
    		@RequestParam String lastName,@RequestParam String dateofbirth,@RequestParam String gender,
    		@RequestParam String citizenCountry,@RequestParam String phoneno)
    {
    	Department department = departmentDao.getDepartment(deptname);
    	Program program = programDao.getProgramByName(prgmname,deptname);
    	Date date = null;
    	if(!dateofbirth.equals(""))
    	{
    		DateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
        	try {
    			date = sdf.parse(dateofbirth);
    		} catch (ParseException e) {
    		}
    	}
    	applicationDao.updateApplication(term, department, program, firstName,
    			lastName, date, gender, citizenCountry, phoneno, Integer.parseInt(applicationid));
    	 return "redirect:editAcademic.html?applicationid=" + applicationid;
    }
    
    @RequestMapping(value = "/editAcademic.html",method = RequestMethod.GET)
    public String editAcademic(ModelMap models,HttpServletRequest request,@RequestParam String applicationid)
    {
    	if(request.getSession().getAttribute("role") != null){
    		if(!request.getSession().getAttribute("role").equals("student"))
    			return "redirect:login.html";
    	}else{
    		return "redirect:login.html";
    	}
    	if(!applicationid.equals("")){
    		Application application = applicationDao.getApplication(Integer.parseInt(applicationid));
    		if(application != null){
    			models.put("degrees",degreeDao.getDegrees(Integer.parseInt(applicationid)));
    			models.put("degree", new Degree());
                return "editAcademic";
    		}
    		else{
    			return "redirect:studenthome.html";
    		}
    	}else{
    		return "redirect:studenthome.html";
    	}
    }
    
    @RequestMapping(value = "/editAcademic.html",method = RequestMethod.POST)
    public String editAcademic(@ModelAttribute Degree degree,@RequestParam String appid,
    		@RequestParam MultipartFile transcriptfile) throws IllegalStateException, IOException
    {
    	degree.setTranscript("");
    	degree.setApplication(applicationDao.getApplication(Integer.parseInt(appid)));
    	degree = degreeDao.saveDegree(degree);
    	if(!transcriptfile.isEmpty()){
    		String originalFileName = transcriptfile.getOriginalFilename();
	    	String newFileName = originalFileName.substring(originalFileName.lastIndexOf('.'));
	    	newFileName = degree.getId() + newFileName;
	    	String path = context.getServletContext().getRealPath("/WEB-INF/files/" + newFileName);
	    	transcriptfile.transferTo(new File(path));
	    	degreeDao.updateDegreeFileName(degree.getId(), newFileName);
    	}
        return "redirect:editAcademic.html?applicationid=" + appid;
    }
    
    @RequestMapping(value = "/editOldAcademic.html",method = RequestMethod.POST)
    public String editOldAcademic(@RequestParam String appid,@RequestParam String degreeid,
    		@RequestParam String universityName,@RequestParam String degreeName,@RequestParam String major,
    		@RequestParam String gpa,@RequestParam String timePeriod,
    		@RequestParam MultipartFile transcriptfile) throws IllegalStateException, IOException
    {
    	Application application = applicationDao.getApplication(Integer.parseInt(appid));
    	degreeDao.updateDegree(universityName, degreeName, major, Double.parseDouble(gpa), timePeriod, application, Integer.parseInt(degreeid));
    	if(!transcriptfile.isEmpty()){
    		String fileNameDatabase = degreeDao.getFileName(Integer.parseInt(degreeid));
    		String dbpath = context.getServletContext().getRealPath("/WEB-INF/files/" + fileNameDatabase);
    		File file = new File(dbpath);
    		if(file.exists())
    			file.delete();
    		
    		String originalFileName = transcriptfile.getOriginalFilename();
	    	String newFileName = originalFileName.substring(originalFileName.lastIndexOf('.'));
	    	newFileName = degreeid + newFileName;
	    	String path = context.getServletContext().getRealPath("/WEB-INF/files/" + newFileName);
	    	transcriptfile.transferTo(new File(path));
	    	degreeDao.updateDegreeFileName(Integer.parseInt(degreeid), newFileName);
    	}
        return "redirect:editAcademic.html?applicationid=" + appid;
    }
    
    @RequestMapping(value = "/removeAcademic.html")
    public String removeAcademic(@RequestParam String applicationid,@RequestParam String degreeid) throws IllegalStateException, IOException
    {
    	String fileNameDatabase = degreeDao.getFileName(Integer.parseInt(degreeid));
    	if(!fileNameDatabase.equals("")){
    		String dbpath = context.getServletContext().getRealPath("/WEB-INF/files/" + fileNameDatabase);
    		File file = new File(dbpath);
    		if(file.exists())
    			file.delete();
    	}
    	degreeDao.removeDegree(Integer.parseInt(degreeid));
        return "redirect:editAcademic.html?applicationid=" + applicationid;
    }
    
    @RequestMapping(value = "/editOtherRequiremt.html",method = RequestMethod.GET)
    public String editOtherRequiremt(ModelMap models,HttpServletRequest request,@RequestParam String applicationid)
    {
    	if(request.getSession().getAttribute("role") != null){
    		if(!request.getSession().getAttribute("role").equals("student"))
    			return "redirect:login.html";
    	}else{
    		return "redirect:login.html";
    	}
    	if(!applicationid.equals("")){
    		Application application = applicationDao.getApplication(Integer.parseInt(applicationid));
    		if(application != null){
    			models.put("otherfields",otherFieldDao.getOtherField(application.getDepartment().getDeptName()));
    			models.put("otherFieldValue", new OtherFieldValue());
    			models.put("otherFieldValues",otherFieldValueDao.getOtherFieldValues(Integer.parseInt(applicationid)));
                return "editOtherRequiremt";
    		}
    		else{
    			return "redirect:studenthome.html";
    		}
    	}else{
    		return "redirect:studenthome.html";
    	}
    }
    @RequestMapping(value = "/editOtherRequiremt.html",method = RequestMethod.POST)
    public String editOtherRequiremt(@RequestParam String appid,@RequestParam String otherid,
    		@RequestParam String othervalueID,@RequestParam String OtherValue)
    {
    	Boolean flag = false;
    	if(!othervalueID.equals("")){
    		if(Integer.parseInt(othervalueID) != 0){
    			flag = true;
    		}
    	}
    	if(flag == false){
    		OtherFieldValue otherFieldValue = new OtherFieldValue();
        	otherFieldValue.setApplication(applicationDao.getApplication(Integer.parseInt(appid)));
        	otherFieldValue.setOtherField((otherFieldDao.getOtherField(Integer.parseInt(otherid))));
        	otherFieldValue.setOtherValue(OtherValue);
        	otherFieldValueDao.saveOtherFieldValue(otherFieldValue);
    	}else{
    		otherFieldValueDao.updateOtherFileName(Integer.parseInt(othervalueID), OtherValue);
    	}
    	
        return "redirect:editOtherRequiremt.html?applicationid=" + appid;
    }
    
    @RequestMapping(value = "/uploadeditOtherFile.html",method = RequestMethod.POST)
    public String uploadeditOtherFile(@RequestParam String appid,@RequestParam String othervalueID,
    		@RequestParam String otherid,@RequestParam MultipartFile otherfile) throws IllegalStateException, IOException
    {
    	if(!otherfile.isEmpty()){
    		Boolean flag = false;
        	if(!othervalueID.equals("")){
        		if(Integer.parseInt(othervalueID) != 0){
        			flag = true;
        		}
        	}
        	if(flag == false){
        		OtherFieldValue otherFieldValue = new OtherFieldValue();
    	    	otherFieldValue.setApplication(applicationDao.getApplication(Integer.parseInt(appid)));
    	    	otherFieldValue.setOtherField((otherFieldDao.getOtherField(Integer.parseInt(otherid))));
    	    	otherFieldValue = otherFieldValueDao.saveOtherFieldValue(otherFieldValue);
    	    	
    	    	String originalFileName = otherfile.getOriginalFilename();
    	    	String newFileName = originalFileName.substring(originalFileName.lastIndexOf('.'));
    	    	newFileName = "other_" + otherFieldValue.getId() + newFileName;
    	    	String path = context.getServletContext().getRealPath("/WEB-INF/files/" + newFileName);
    	    	otherfile.transferTo(new File(path));
    	    	otherFieldValueDao.updateOtherFileName(otherFieldValue.getId(), newFileName);
        	}else{
        		String fileNameDatabase = otherFieldValueDao.getFileName(Integer.parseInt(othervalueID));
            	if(!fileNameDatabase.equals("")){
            		String dbpath = context.getServletContext().getRealPath("/WEB-INF/files/" + fileNameDatabase);
            		File file = new File(dbpath);
            		if(file.exists())
            			file.delete();
            	}
            	
            	String originalFileName = otherfile.getOriginalFilename();
    	    	String newFileName = originalFileName.substring(originalFileName.lastIndexOf('.'));
    	    	newFileName = "other_" + othervalueID + newFileName;
    	    	String path = context.getServletContext().getRealPath("/WEB-INF/files/" + newFileName);
    	    	otherfile.transferTo(new File(path));
    	    	otherFieldValueDao.updateOtherFileName(Integer.parseInt(othervalueID), newFileName);
        	}
    	}
        return "redirect:editOtherRequiremt.html?applicationid=" + appid;
    }
    
    @RequestMapping(value = "/deleteOtherRequirement.html")
    public String deleteOtherRequirement(@RequestParam String othervalueID,@RequestParam String type,
    		@RequestParam String applicationid) throws IllegalStateException, IOException
    {
    	if(type.equals("file")){
    		String fileNameDatabase = otherFieldValueDao.getFileName(Integer.parseInt(othervalueID));
        	if(!fileNameDatabase.equals("")){
        		String dbpath = context.getServletContext().getRealPath("/WEB-INF/files/" + fileNameDatabase);
        		File file = new File(dbpath);
        		if(file.exists())
        			file.delete();
        	}
    	}
    	
    	otherFieldValueDao.removeOtherFieldValue(Integer.parseInt(othervalueID));
        return "redirect:editOtherRequiremt.html?applicationid=" + applicationid;
    }
    
    @RequestMapping(value = "/submitApplication.html",method = RequestMethod.GET)
    public String submitApplication(@RequestParam String applicationid)
    {
    	applicationDao.updateApplicationStatuswithDate(Integer.parseInt(applicationid),statusDao.getStatusByName("New Submitted"));
        return "redirect:studenthome.html";
    }
    
    @RequestMapping(value = "/logout.html",method = RequestMethod.GET)
    public String logout(HttpServletRequest request)
    {
    	request.getSession().removeAttribute("username");
    	request.getSession().removeAttribute("role");
    	
    	return "redirect:login.html";
    }
    
    @RequestMapping(value = "/programFmDept.html")
    @ResponseBody
    public String programFmDept(@RequestParam String deptname)
    {
    	String programlist = "";
    	List<Program> programs = new ArrayList<Program>();
    	if(!deptname.equals("Select Department"))
    	{
    		programs = programDao.getProgram(deptname);
    		for(Program program : programs)
        	{
        		programlist = programlist + "<option value='" + program.getProgName() + "'>" + program.getProgName() +"</option>";
        	}
    	}
    	programlist = programlist + "<option value='Select Program' selected>Select Program</option>";
        return programlist;
    }
}