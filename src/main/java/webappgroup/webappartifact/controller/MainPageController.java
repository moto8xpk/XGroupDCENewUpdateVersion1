package webappgroup.webappartifact.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.multipart.support.ByteArrayMultipartFileEditor;

import webappgroup.webappartifact.model.MyUploadForm;
import webappgroup.webappartifact.service.WordService;
import webappgroup.webappartifact.constain.Constants;
import webappgroup.webappartifact.dao.QuestionService;
import webappgroup.webappartifact.entity.Question;

@Controller
@RequestMapping("/*")
public class MainPageController {

	@Autowired
	private QuestionService questionService;

	// Phương thức này được g�?i mỗi lần có Submit.
	@InitBinder
	public void initBinder(WebDataBinder dataBinder) {
		Object target = dataBinder.getTarget();
		if (target == null) {
			return;
		}
		System.out.println("Target=" + target);

		if (target.getClass() == MyUploadForm.class) {

			// �?ăng ký để chuyển đổi giữa các đối tượng multipart thành byte[]
			dataBinder.registerCustomEditor(byte[].class, new ByteArrayMultipartFileEditor());
		}
	}

	// GET: Hiển thị trang form upload
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String mainpage(Model model) {

		MyUploadForm myUploadForm = new MyUploadForm();
		model.addAttribute("myUploadForm", myUploadForm);

		return "mainMenu";
	}

	// POST: Xử lý Upload
	@RequestMapping(value = "/", method = RequestMethod.POST)
	public String uploadOneFileHandlerPOST(HttpServletRequest request, //
			Model model, //
			@ModelAttribute("myUploadForm") MyUploadForm myUploadForm) {
		System.out.println(model);
		return this.doUpload(request, model, myUploadForm);
	}

	private String doUpload(HttpServletRequest request, Model model, //
			MyUploadForm myUploadForm) {

		String description = myUploadForm.getDescription();
		System.out.println("Description: " + description);

		// Thư mục gốc upload file.
		String uploadRootPath = request.getServletContext().getRealPath("upload");
		System.out.println("uploadRootPath=" + uploadRootPath);

		File uploadRootDir = new File(uploadRootPath);
		//
		// Tạo thư mục gốc upload nếu nó không tồn tại.
		if (!uploadRootDir.exists()) {
			uploadRootDir.mkdirs();
		}
		CommonsMultipartFile[] fileDatas = myUploadForm.getFileDatas();
		//
		List<File> uploadedFiles = new ArrayList<File>();
		for (CommonsMultipartFile fileData : fileDatas) {

			// Tên file gốc tại Client.
			String name = fileData.getOriginalFilename();
			System.out.println("Client File Name = " + name);

			if (name != null && name.length() > 0) {
				try {
					// Tạo file tại Server.
					File serverFile = new File(uploadRootDir.getAbsolutePath() + File.separator + name);

					// Luồng ghi dữ liệu vào file trên Server.
					BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
					stream.write(fileData.getBytes());
					stream.close();
					//
					uploadedFiles.add(serverFile);
					System.out.println("Write file: " + serverFile);

				} catch (Exception e) {
					System.out.println("Error Write file: " + name);
				}
			}
		}
		// read file docx after upload file
		try {
			// this.handleUploadFile(uploadedFiles);
			XWPFDocument docx = WordService.loadFile(uploadedFiles.get(0).getAbsolutePath().toString());
			// String titleContent = WordService
			// .getAllPartTitle(docx)
			// .get(0)
			// .getParagraphText();
			List<String> questionContent = WordService.getAllQuestionContent(docx);
			List<String> answerContentList = WordService.getAllAnswerContent(docx);
			List<String> allAnswerContent = WordService.getAllAnswerContent(docx);
			List<String> rightAnswerContentHighLines = WordService.readFileAllParagraphIncludeHighline(docx);

			List<String> answerContents = new ArrayList<String>();
			List<String> answerKeys = new ArrayList<String>();
			List<String> rightAnswerKeys = new ArrayList<String>();

			int current = 0;
			int key=0;
			for (String identifyContent : rightAnswerContentHighLines) {
				String identifyContentKey=identifyContent.substring(0,1);
				for (int x = key; x <= answerContentList.size() - 1; x++) {
					String answercontentKey = answerContentList.get(x).substring(0, 1);
					if (identifyContentKey.equals(answercontentKey) && x <= 3) {
						rightAnswerKeys.add(answercontentKey);
						x = 4;
						current = 3;
						key=x;
						break;
					}
					else if (identifyContentKey.equals(answercontentKey)) {
						rightAnswerKeys.add(answercontentKey);
						current = current + 4;
						key=current;
						break;
					}
				}

			}
			for (String answer : allAnswerContent) {
				answerContents.add(answer.substring(3));
			}

			for (String answer : allAnswerContent) {
				answerKeys.add(answer.substring(0, 1));
			}

			XWPFParagraph paraClassCode = WordService.readFileAllParagraphIncludeBold(docx);
			String strParaClassCode = paraClassCode.getParagraphText().replace(Constants.FORMAT_CLASS_CODE_FRONT, "")
					.replace(Constants.FORMAT_CLASS_CODE_END, "");
			String classCode = strParaClassCode;
			List<Question> questionList=new ArrayList<Question>();	
			int currents = 0;
			int getKeyX = 0;
			// List<Question> questionList=new ArrayList<Question>();
			for (int y = 0; y <= questionContent.size() - 1; y++) {
				String questionConten = questionContent.get(y);
				String rightAnswerKey = rightAnswerKeys.get(y);

				Question question = new Question();
				String numberQuestion=questionService.getQuestionNumber();
				int number=Integer.parseInt(numberQuestion);
				question.setQuestionId(number+2);
				question.setQuestionContent(questionConten);
				question.setAnswer(rightAnswerKey);
				question.setClassId(classCode);

				for (int x = getKeyX; x <= answerContents.size() - 1; x++) {
					String answer = answerContents.get(x);
					if (currents == 0) {
						question.setAnswersA(answer);
						currents++;
					} else if (currents == 1) {
						question.setAnswersB(answer);
						currents++;
					} else if (currents == 2) {
						question.setAnswersC(answer);
						currents++;
					} else if (currents == 3) {
						question.setAnswersD(answer);
						currents = 0;
						getKeyX = x;
//						questionService.save(question);
						questionList.add(question);
						break;
					}
				}
			}
			questionService.save(questionList);
			System.out.println("succsess !");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("fuck you!");
		
		model.addAttribute("description", description);
		model.addAttribute("uploadedFiles", uploadedFiles);
		File file=new File(uploadedFiles.get(0).getAbsolutePath().toString());
		try {
			if(Files.deleteIfExists(file.toPath()))
			{
				System.out.println("docx file is deleted");
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return "uploadResult";
	}

//	@RequestMapping(value = "/", method = RequestMethod.GET)
//	public String doUploadDB(List<String> questionContents, List<String> answerList, List<String> rightAnswerKeys,
//			String classId) {
//		int current = 0;
//		int getKeyX = 0;
//		// List<Question> questionList=new ArrayList<Question>();
//		for (int y = 0; y <= questionContents.size() - 1; y++) {
//			String questionContent = questionContents.get(y);
//			String rightAnswerKey = rightAnswerKeys.get(y);
//
//			Question question = new Question();
//			question.setQuestionContent(questionContent);
//			question.setAnswer(rightAnswerKey);
//			question.setClassId(classId);
//
//			for (int x = getKeyX; x <= answerList.size() - 1; x++) {
//				String answer = answerList.get(x);
//				if (current == 0) {
//					question.setAnswersA(answer);
//					current++;
//				} else if (current == 1) {
//					question.setAnswersB(answer);
//					current++;
//				} else if (current == 2) {
//					question.setAnswersC(answer);
//					current++;
//				} else if (current == 3) {
//					question.setAnswersD(answer);
//					current = 0;
//					getKeyX = x;
//					questionService.save(question);
//					break;
//				}
//			}
//		}
//
//		return "uploadResult";
//	}

	// private void handleUploadFile(List<File> uploadedFiles) throws Exception {
	// XWPFDocument docx =
	// WordService.loadFile(uploadedFiles.get(0).getAbsolutePath().toString());
	// // String titleContent = WordService
	// // .getAllPartTitle(docx)
	// // .get(0)
	// // .getParagraphText();
	// List<String> questionContent = WordService.getAllQuestionContent(docx);
	// List<String> answerContentList = WordService.getAllAnswerContent(docx);
	// List<String> allAnswerContent = WordService.getAllAnswerContent(docx);
	// List<String> rightAnswerContentHighLines =
	// WordService.readFileAllParagraphIncludeHighline(docx);
	//
	// List<String> answerContents = new ArrayList<String>();
	// List<String> answerKeys = new ArrayList<String>();
	// List<String> rightAnswerKeys = new ArrayList<String>();
	//
	// Integer current = 0;
	//
	// for (String identifyContent : rightAnswerContentHighLines) {
	// for (int x = 0; x <= answerContentList.size() - 1; x++) {
	// String answercontent = answerContentList.get(x);
	// if (identifyContent.equals(answercontent.substring(answercontent.indexOf(".")
	// + 2)) && x <= 3) {
	// rightAnswerKeys.add(answercontent.substring(0, 1));
	// x = 3;
	// current = 3;
	// }
	// if (identifyContent.equals(answercontent.substring(answercontent.indexOf(".")
	// + 2))) {
	// rightAnswerKeys.add(answercontent.substring(0, 1));
	// current = current + 4;
	// x = current;
	// }
	// }
	//
	// }
	// for (String answer : allAnswerContent) {
	// answerContents.add(answer.substring(3));
	// }
	//
	// for (String answer : allAnswerContent) {
	// answerKeys.add(answer.substring(0, 1));
	// }
	//
	// XWPFParagraph paraClassCode =
	// WordService.readFileAllParagraphIncludeBold(docx);
	// String strParaClassCode =
	// paraClassCode.getParagraphText().replace(Constants.FORMAT_CLASS_CODE_FRONT,
	// "")
	// .replace(Constants.FORMAT_CLASS_CODE_END, "");
	// String classCode = strParaClassCode;
	//
	// this.doUploadDB(questionContent, answerContents, rightAnswerKeys, classCode);
	// }

}
