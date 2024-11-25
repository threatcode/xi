package edu.action.college;

import java.awt.image.renderable.ParameterBlock;
import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.List;

import javax.media.jai.InterpolationNearest;
import javax.media.jai.JAI;
import javax.media.jai.OpImage;
import javax.media.jai.RenderedOp;
import javax.servlet.ServletContext;

import net.coobird.thumbnailator.Thumbnails;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.google.gson.Gson;
import com.sun.media.jai.codec.ImageCodec;
import com.sun.media.jai.codec.ImageEncoder;
import com.sun.media.jai.codec.SeekableStream;

import edu.action.application.Application;
import edu.action.common.BaseAction;
import edu.dao.college.AdmissionDAO;
import edu.dto.IpAddressDTO;
import edu.dto.application.ResponseDTO;
import edu.dto.college.ApplicantInfoDTO;
import edu.dto.college.CollegeCourseDTO;
import edu.dto.college.CollegeDTO;
import edu.dto.college.CollegeSubject;
import edu.utils.Configuration;
import edu.utils.UploadResponse;
import edu.utils.UploadToProvider;

public class Registration extends BaseAction {
	
	static Logger mLogger = Logger.getLogger(Registration.class);
	private boolean imageValidYN ;
	private InputStream inputStream;
	
	private ServletContext servlet;
	private String pShift_id;
	private String pVersion_id;
	private String pGroup_id;
	private String roll_no;
	private String sscReg;
	private String board_id;
	private String boardname;
	private String passing_year;
	private String reg_no;
	private String applicationID;
	private String hgroup_id;
	private String sub4;
	private String sub5;
	private String sub6;
	private String sub7;
	private String esif;
	private String eiin;
	
	private String class_roll;
	private String sections;
	private String first_name;
	private String nationality;
	private String hdistrict;
	private String religion;
	private File fileUpload;
	private String fileUploadContentType;
	private String fileUploadFileName;
	private String starttime;
	private String endtime;
	
	public String getStarttime() {
		return starttime;
	}
	public void setStarttime(String starttime) {
		this.starttime = starttime;
	}
	public String getEndtime() {
		return endtime;
	}
	public void setEndtime(String endtime) {
		this.endtime = endtime;
	}
	public String getFirst_name() {
		return first_name;
	}
	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}
	public ServletContext getServlet() {
		return servlet;
	}
	public void setServlet(ServletContext servlet) {
		this.servlet = servlet;
	}
	public String getPShift_id() {
		return pShift_id;
	}
	public void setPShift_id(String shift_id) {
		pShift_id = shift_id;
	}
	public String getPVersion_id() {
		return pVersion_id;
	}
	public void setPVersion_id(String version_id) {
		pVersion_id = version_id;
	}
	public String getPGroup_id() {
		return pGroup_id;
	}
	public void setPGroup_id(String group_id) {
		pGroup_id = group_id;
	}
	public String getRoll_no() {
		return roll_no;
	}
	public void setRoll_no(String roll_no) {
		this.roll_no = roll_no;
	}
	public String getBoard_id() {
		return board_id;
	}
	public void setBoard_id(String board_id) {
		this.board_id = board_id;
	}
	public String getPassing_year() {
		return passing_year;
	}
	public void setPassing_year(String passing_year) {
		this.passing_year = passing_year;
	}
	public String getReg_no() {
		return reg_no;
	}
	public void setReg_no(String reg_no) {
		this.reg_no = reg_no;
	}
	public ServletContext getServletContext()
	{
		return servlet;
	}
	public void setServletContext(ServletContext servlet)
	{
		this.servlet = servlet;
	}
	
	
	
	
	
	
	
	
	
	public String svgLoad(){
		
		CollegeDTO userDto = (CollegeDTO) session.get("user");
		
		if(userDto == null){
			return "input";
		}
		
		AdmissionDAO svgLoadDAO = new AdmissionDAO();
		List<CollegeCourseDTO> shiftList = svgLoadDAO.getShiftList(userDto.getEiin());
		request.setAttribute("shiftList", shiftList);
		List<CollegeCourseDTO> versionList = svgLoadDAO.getVersionList(userDto.getEiin());
		request.setAttribute("versionList", versionList);
		List<CollegeCourseDTO> groupList = svgLoadDAO.getGroupList(userDto.getEiin());
		request.setAttribute("groupList", groupList);
		
		request.setAttribute("userInfo", userDto);
				
		return "success";

				
	}
	public String getDataForRegistration(){
		
		CollegeDTO userDto = (CollegeDTO) session.get("user");
		if(userDto == null){
			return "input";
		}
		AdmissionDAO adao = new AdmissionDAO();

		List<ApplicantInfoDTO> lstNonRegistered = adao.getDataForRegistration(pShift_id,pVersion_id,pGroup_id,userDto.getEiin());
		request.setAttribute("nonRegistered", lstNonRegistered);
		request.setAttribute("hiddenShiftID", pShift_id);
		request.setAttribute("hiddenVersionID", pVersion_id);
		request.setAttribute("hiddenGroupID", pGroup_id);
		request.setAttribute("userInfo", userDto);
		
		List<CollegeSubject> lstCollegeSubject = null;
		if(session.get("collegeSubject")!=null)
			lstCollegeSubject = (List<CollegeSubject>)session.get("collegeSubject");
		else
		{
			lstCollegeSubject = adao.getCollegeSubject(userDto.getEiin(), Integer.parseInt(userDto.getBoard_id()));
			session.put("collegeSubject",lstCollegeSubject);
		}
		
		request.setAttribute("successMessage", request.getAttribute("successMessage") == null ? "" : request.getAttribute("successMessage"));
	    request.setAttribute("errorMessage", request.getAttribute("errorMessage") == null ? "" : request.getAttribute("errorMessage"));
	    
		return "success";

				
	}
	public String getIndividualData(){
		
		CollegeDTO userDto = (CollegeDTO) session.get("user");
		if(userDto == null){
			return "input";
		}
		AdmissionDAO adao = new AdmissionDAO();

		ApplicantInfoDTO lstNonRegistered = adao.getIndividualData(applicationID);
		String responses = lstNonRegistered.getFatherName()+"###"+lstNonRegistered.getMotherName()+"###"+lstNonRegistered.getDob()
				+"###"+lstNonRegistered.getGender()+"###"+lstNonRegistered.getGpa()+"###"+lstNonRegistered.getGroupName()
				+"###"+lstNonRegistered.getVersionName()+"###"+lstNonRegistered.getShiftName()+"###"+lstNonRegistered.getSessions();
//		System.out.println(responses);
		try {
			response.setContentType("text/html");
			response.getWriter().write(responses);
		} catch (Exception e) {
		}
		return null;

				
	}
	public String savePhoto(){
		Thread t1 = new Thread(new Runnable() {
	         public void run() {
	     		AdmissionDAO adao = new AdmissionDAO();
	    		adao.savePhoto(starttime, endtime);
	         }
	    });  
	    t1.start();
		

		return null;
	}
	
	
	
	public String submitRegData()
	{
		CollegeDTO userDto = (CollegeDTO) session.get("user");
		if(userDto == null){
			return "input";
		}
		
		
//		IpAddressDTO ipAdress = getIpAddressDTO();
//		System.out.println(sscReg);
//		System.out.println(first_name);
//		System.out.println(roll_no);
//		System.out.println(board_id);
//		System.out.println(passing_year);
//		System.out.println(applicationID);
//		System.out.println(boardname);
//		System.out.println(hgroup_id);
//		System.out.println(sub6);
//		System.out.println(sub7);
//		System.out.println(fileUpload.getName());


		
		try{////////check true false
			if(!changePhoto())
			{
				response.setContentType("text/html");
				response.getWriter().write("Invalid Photo!!!");
				return null;
			}
		}
		catch(Exception ex){
			ex.printStackTrace();
			try{
				response.setContentType("text/html");
				response.getWriter().write("Photo upload problem!!!");
			}
			catch(Exception ex1){
				ex1.printStackTrace();
			}
			return null;
				
		}
		
		try {
			if(sub4==null)sub4="0";
			if(sub5==null)sub5="0";
			if(sub6==null)sub6="0";
			if(sub7==null || sub7.equalsIgnoreCase(""))sub7="0";
			
			roll_no=applicationID.substring(6);
			
			if(AdmissionDAO.RregistrationInsert(Integer.parseInt(esif), userDto.getEiin(), applicationID, roll_no, Integer.parseInt(board_id), Integer.parseInt(passing_year), 
					Integer.parseInt(hgroup_id), sub4, sub5, sub6, 
					sub7, class_roll, sections, nationality, hdistrict, religion, getIpAddressDTO())>0)
			{
				response.setContentType("text/html");
				response.getWriter().write("Registration Successfull");
				return null;
			}

		
			
			//response.setContentType("text/html");
			//response.getWriter().write("hi delwar");
		} catch (Exception e) {
			try
			{
				if(e.getMessage().contains("REGISTRATION_UQ1"))
				{
					response.setContentType("text/html");
					response.getWriter().write("Duplicate eSIF");
				}
				else if (e.getMessage().contains("REGISTRATION_UQ2"))
				{
					response.setContentType("text/html");
					response.getWriter().write("Duplicate Class Roll");
				}	
				else
				{
					response.setContentType("text/html");
					response.getWriter().write(e.getMessage());
				}
			}
			catch(Exception e1){}
			e.printStackTrace();
		}
		return null;
	}
	
	
	// Photo Validation
		public String photoValidation() throws Exception {


			
				
			CollegeDTO userDto = (CollegeDTO) session.get("user");
			//String PhotoName= board_id+passing_year+roll_no;
			String PhotoName= "19"+board_id+"_"+userDto.getEiin()+sscReg;
			String PhotoCatagory="applicantPhoto/"+userDto.getBoard_name()+"/"+userDto.getEiin();
			
				imageValidYN=false;
				Gson gson = new Gson();
				String json="";
		        //System.out.println("Test");
			    String type = fileUploadContentType.split("/")[1];
			    if (type.endsWith("jpeg")) {
			      type = "jpeg";
			    }
			    else if (type.endsWith(
			            "jpg")) {
			      type = "jpg";
			    }
			    else if (type.endsWith("JPG")) {
			      type = "jpg";
			    }
			    else if (type.endsWith("JPEG")) {
			      type = "jpg";
			    }
			    else {
			      inputStream = IOUtils.toInputStream("error#Not a Valid type. Only Supported Image Type is '.jpg'", "UTF-8");
			       json = gson.toJson("error#Not a Valid type. Only Supported Image Type is '.jpg'");
				  setJsonResponse(json);
			      return null;
			    }

			    FileInputStream in = new FileInputStream(fileUpload);
			    BufferedInputStream bf = new BufferedInputStream(in);
			    byte[] b = new byte[(int) fileUpload.length()];
			    bf.read(b);

			    String t = "";
			    if (type.equalsIgnoreCase("jpeg") || type.equalsIgnoreCase("jpg")) {
			      t = "JPEG";
			    }
			    else {
			      inputStream = IOUtils.toInputStream("error#Not a Valid type. Only Supported Image Type is '.jpg'", "UTF-8");
			      json = gson.toJson("error#Not a Valid type. Only Supported Image Type is '.jpg'");
				  setJsonResponse(json);
			      return null;
			    }

			    int dimX = 120;
			    int dimY = 150;
			    byte[] finaldata = null;
			    try {

			      ByteArrayInputStream is = new ByteArrayInputStream(b);
			      SeekableStream s = SeekableStream.wrapInputStream(is, true);
			      RenderedOp objImage = JAI.create("stream", s);
			      ((OpImage) objImage.getRendering()).setTileCache(null);

			      float width = (float) objImage.getWidth();
			      float height = (float) objImage.getHeight();

//			      if (width > 125 || width < 115 || height > 155 || height < 145) {
//
//			        inputStream = IOUtils.toInputStream("error#Image with 120 px Width and 150 px Height are allowed to upload.", "UTF-8");
//			        json = gson.toJson("error#Image with 120 px Width and 150 px Height are allowed to upload.");
//					setJsonResponse(json);
//				    return null;
//			      }
			      float aspectRatio = height/width;
			      if(aspectRatio<1.15 || aspectRatio>1.40)
			      {
			    	  try{
			    	  inputStream = IOUtils.toInputStream("error#Please upload PORTRAIT photo", "UTF-8");
				        json = gson.toJson("error#Please upload PORTRAIT photo");
						setJsonResponse(json);
				    	  }catch (Exception ex)
				    	  {ex.printStackTrace();}
					    return null;
			      }
						File newFile = new File(request.getSession().getId()+".jpg");
						try
						{
							Thumbnails.of(fileUpload).size(120, 150).toFile(newFile);
							fileUpload = newFile;
						}
						catch(Exception e)
						{
							e.printStackTrace();
						}			    	  
			      

			      ParameterBlock pb = new ParameterBlock();
			      pb.addSource(objImage); // The source image
			      pb.add(dimX / width); // The xScale
			      pb.add(dimY / height); // The yScale
			      pb.add(0.0F); // The x translation
			      pb.add(0.0F); // The y translation
			      pb.add(new InterpolationNearest()); // The interpolation

			      objImage = JAI.create("scale", pb, null);

			      ByteArrayOutputStream out1 = new ByteArrayOutputStream();

			      ImageEncoder imgEnc = ImageCodec.createImageEncoder(t, out1, null);
			      imgEnc.encode(objImage);
			      finaldata = out1.toByteArray();

			      if ((fileUpload.length() / 1024) > 15) {
			    	  try{
			        inputStream = IOUtils.toInputStream("error#Maximum allowed Photo size is 15 Kb", "UTF-8");
			        json = gson.toJson("error#Maximum allowed Photo size is 15 Kb");
					setJsonResponse(json);
			    	  }catch (Exception ex)
			    	  {ex.printStackTrace();}
				    return null;
			      }


			    }
			    catch (Exception e) {
			      e.printStackTrace();
			      mLogger.error("Exception during File Upload Operation(Photo Change).");

			      inputStream = IOUtils.toInputStream("error#Probelm in Uploading the Image", "UTF-8");
			      json = gson.toJson("error#Probelm in Uploading the Image");
				  setJsonResponse(json);
				  return null;
			    }

			    String filepath = PhotoName+ ".jpg";
			    File fileToCreate = new File(filepath);
			    FileOutputStream out = new FileOutputStream(fileToCreate);
			    out.write(finaldata);
			    UploadResponse uploadResponse = UploadToProvider.getInstance().upload(fileToCreate, "tmpPhoto");
			    ServletActionContext.getRequest().getSession().setAttribute("uploadedImage", finaldata);

			    fileToCreate.delete();

			    inputStream = IOUtils.toInputStream(uploadResponse.getResponseText(), "UTF-8");
			    json = gson.toJson("success");
//				setJsonResponse(json);
				imageValidYN=true;
				return null;
			  }
	
			public boolean changePhoto() throws Exception{
				CollegeDTO userDto = (CollegeDTO) session.get("user");
				//String PhotoName= board_id+passing_year+roll_no;
				String PhotoName= "19"+board_id+"_"+userDto.getEiin()+sscReg;
				String PhotoCatagory="applicantPhoto/"+userDto.getBoard_name()+"/"+userDto.getEiin();
				photoValidation();
				 Gson gson = new Gson();
				 ResponseDTO response=new ResponseDTO();
				
				if(imageValidYN)
				{
			    //setApplicant();

			
							
							 Configuration configuration = Configuration.getInstance();
							
							
							String localFilePath=configuration.getConfigurationMap().get(configuration.getEnvironment() + ".application.image.local.directory")
			                        + "applicantPhoto";
							
							File destFile = new File(localFilePath,PhotoName+ ".jpg");
							FileUtils.copyFile(fileUpload, destFile);
							
						//	System.out.println(localFilePath);
							
							String localUploadedFilePath=configuration.getConfigurationMap().get(configuration.getEnvironment() + ".application.image.local.directory")
			                        + "applicantPhoto/"+PhotoName+ ".jpg";
							File f1 = new File(localUploadedFilePath);
							 
		/*					String tempFilePath=configuration.getConfigurationMap().get(configuration.getEnvironment() + ".application.image.local.directory")
			                        + "tmpPhoto/"+ preview_ssc_roll+ "_" + preview_ssc_board +"_"+preview_ssc_year+ ".jpg";*/
							
		/*					System.out.println(tempFilePath);
							File f1 = new File(tempFilePath);*/
							
							
					        if(f1.exists()){
							
							   File fileToCreate = new File(PhotoName+ ".jpg");
							   FileUtils.copyFile(fileUpload, fileToCreate);
							   UploadResponse uploadResponse = UploadToProvider.getInstance().upload(fileToCreate, PhotoCatagory);
							   fileToCreate.delete();
							   
							   if (uploadResponse.getStatusCode() == 200)
							   {
								   if(uploadResponse.getResponseText().contains(".jpg"))
								   {
								   response.setStatus("SUCCESS");
								   response.setMessage("Your photo has been changed successfully.");
								   
								   System.out.println("Applicant ID:"+PhotoName+", Photo has been uploaded successfully.");
								   return true;
								   }
								   else
								   {
									   response.setMessage("photo upload problem");
									   
									   System.out.println("Applicant ID:"+PhotoName+", Photo has been uploaded successfully.");
									   return false;
								   }
							   }
							   
							   else
								{
								   response.setStatus("ERROR");
								   response.setMessage("Photo not uploaded.Please try again...!!!");
								   

								   
									System.out.println("Photo not uploaded.Please try again...!!!");
									
									 return false;
								}
							    

						}
							else
							{
								response.setStatus("ERROR");
								response.setMessage("File upload error.Please try again...!!!");
								

								System.out.println("File upload error.Please try again...!!!");
								 return false;
							}
								  

					}
				
				   String json = gson.toJson(response);
//				   setJsonResponse(json);	

				   return false;
				}	
	
	
	public String getDataForRegistrationManual(){
				
				CollegeDTO userDto = (CollegeDTO) session.get("user");
				if(userDto == null){
					return "input";
				}
				AdmissionDAO adao = new AdmissionDAO();

				List<ApplicantInfoDTO> lstNonRegistered = adao.getDataForRegistrationManual(pShift_id,pVersion_id,pGroup_id,userDto.getEiin());
				request.setAttribute("nonRegistered", lstNonRegistered);
				request.setAttribute("hiddenShiftID", pShift_id);
				request.setAttribute("hiddenVersionID", pVersion_id);
				request.setAttribute("hiddenGroupID", pGroup_id);
				request.setAttribute("userInfo", userDto);
				
				List<CollegeSubject> lstCollegeSubject = null;
				if(session.get("collegeSubject")!=null)
					lstCollegeSubject = (List<CollegeSubject>)session.get("collegeSubject");
				else
				{
					lstCollegeSubject = adao.getCollegeSubject(userDto.getEiin(), Integer.parseInt(userDto.getBoard_id()));
					session.put("collegeSubject",lstCollegeSubject);
				}
				
				request.setAttribute("successMessage", request.getAttribute("successMessage") == null ? "" : request.getAttribute("successMessage"));
			    request.setAttribute("errorMessage", request.getAttribute("errorMessage") == null ? "" : request.getAttribute("errorMessage"));
			    
				return "success";

						
			}
			
	public String getIndividualDataManual(){
		
		CollegeDTO userDto = (CollegeDTO) session.get("user");
		if(userDto == null){
			return "input";
		}
		AdmissionDAO adao = new AdmissionDAO();

		ApplicantInfoDTO lstNonRegistered = adao.getIndividualDataManual(applicationID);
		String responses = lstNonRegistered.getFatherName()+"###"+lstNonRegistered.getMotherName()+"###"+lstNonRegistered.getDob()
				+"###"+lstNonRegistered.getGender()+"###"+lstNonRegistered.getGpa()+"###"+lstNonRegistered.getGroupName()
				+"###"+lstNonRegistered.getVersionName()+"###"+lstNonRegistered.getShiftName()+"###"+lstNonRegistered.getSessions();
//		System.out.println(responses);
		try {
			response.setContentType("text/html");
			response.getWriter().write(responses);
		} catch (Exception e) {
		}
		return null;

				
	}
	public String submitRegDataManual()
	{
		CollegeDTO userDto = (CollegeDTO) session.get("user");
		if(userDto == null){
			return "input";
		}
		
		
//		IpAddressDTO ipAdress = getIpAddressDTO();
//		System.out.println(sscReg);
//		System.out.println(first_name);
//		System.out.println(roll_no);
//		System.out.println(board_id);
//		System.out.println(passing_year);
//		System.out.println(applicationID);
//		System.out.println(boardname);
//		System.out.println(hgroup_id);
//		System.out.println(sub6);
//		System.out.println(sub7);
//		System.out.println(fileUpload.getName());


		
		try{////////check true false
			if(!changePhoto())
			{
				response.setContentType("text/html");
				response.getWriter().write("Invalid Photo!!!");
				return null;
			}
		}
		catch(Exception ex){ex.printStackTrace();}
		
		try {
			if(sub4==null)sub4="0";
			if(sub5==null)sub5="0";
			if(sub6==null)sub6="0";
			if(sub7==null || sub7.equalsIgnoreCase(""))sub7="0";
			
			if(AdmissionDAO.RegInsertManual(Integer.parseInt(esif), userDto.getEiin(), applicationID, roll_no, Integer.parseInt(board_id), Integer.parseInt(passing_year), 
					Integer.parseInt(hgroup_id), sub4, sub5, sub6, 
					sub7, class_roll, sections, nationality, hdistrict, religion, getIpAddressDTO())>0)
			{
				response.setContentType("text/html");
				response.getWriter().write("Registration Successfull");
				return null;
			}

		
			
			//response.setContentType("text/html");
			//response.getWriter().write("hi delwar");
		} catch (Exception e) {
			try
			{
				if(e.getMessage().contains("REGISTRATION_UQ1"))
				{
					response.setContentType("text/html");
					response.getWriter().write("Duplicate eSIF");
				}
				else if (e.getMessage().contains("REGISTRATION_UQ2"))
				{
					response.setContentType("text/html");
					response.getWriter().write("Duplicate Class Roll");
				}	
				else
				{
					response.setContentType("text/html");
					response.getWriter().write(e.getMessage());
				}
			}
			catch(Exception e1){}
			e.printStackTrace();
		}
		return null;
	}		
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public File getFileUpload() {
		return fileUpload;
	}
	public void setFileUpload(File fileUpload) {
		this.fileUpload = fileUpload;
	}
	public String getFileUploadContentType() {
		return fileUploadContentType;
	}
	public void setFileUploadContentType(String fileUploadContentType) {
		this.fileUploadContentType = fileUploadContentType;
	}
	public String getFileUploadFileName() {
		return fileUploadFileName;
	}
	public void setFileUploadFileName(String fileUploadFileName) {
		this.fileUploadFileName = fileUploadFileName;
	}
	public boolean isImageValidYN() {
		return imageValidYN;
	}
	public void setImageValidYN(boolean imageValidYN) {
		this.imageValidYN = imageValidYN;
	}
	public InputStream getInputStream() {
		return inputStream;
	}
	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}
	public String getApplicationID() {
		return applicationID;
	}
	public void setApplicationID(String applicationID) {
		this.applicationID = applicationID;
	}
	public String getBoardname() {
		return boardname;
	}
	public void setBoardname(String boardname) {
		this.boardname = boardname;
	}
	public static Logger getMLogger() {
		return mLogger;
	}
	public static void setMLogger(Logger logger) {
		mLogger = logger;
	}
	public String getHgroup_id() {
		return hgroup_id;
	}
	public void setHgroup_id(String hgroup_id) {
		this.hgroup_id = hgroup_id;
	}
	public String getSub4() {
		return sub4;
	}
	public void setSub4(String sub4) {
		this.sub4 = sub4;
	}
	public String getSub5() {
		return sub5;
	}
	public void setSub5(String sub5) {
		this.sub5 = sub5;
	}
	public String getSub6() {
		return sub6;
	}
	public void setSub6(String sub6) {
		this.sub6 = sub6;
	}
	public String getSub7() {
		return sub7;
	}
	public void setSub7(String sub7) {
		this.sub7 = sub7;
	}
	public String getNationality() {
		return nationality;
	}
	public void setNationality(String nationality) {
		this.nationality = nationality;
	}
	public String getHdistrict() {
		return hdistrict;
	}
	public void setHdistrict(String hdistrict) {
		this.hdistrict = hdistrict;
	}
	public String getReligion() {
		return religion;
	}
	public void setReligion(String religion) {
		this.religion = religion;
	}
	public String getClass_roll() {
		return class_roll;
	}
	public void setClass_roll(String class_roll) {
		this.class_roll = class_roll;
	}
	public String getEsif() {
		return esif;
	}
	public void setEsif(String esif) {
		this.esif = esif;
	}
	public String getEiin() {
		return eiin;
	}
	public void setEiin(String eiin) {
		this.eiin = eiin;
	}
	public String getSections() {
		return sections;
	}
	public void setSections(String sections) {
		this.sections = sections;
	}
	public String getSscReg() {
		return sscReg;
	}
	public void setSscReg(String sscReg) {
		this.sscReg = sscReg;
	}

}
