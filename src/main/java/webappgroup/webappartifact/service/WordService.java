package webappgroup.webappartifact.service;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.apache.poi.POIXMLDocumentPart;
import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import webappgroup.webappartifact.constain.Constants;

public class WordService {

	public static XWPFDocument loadFile(String pathString) throws Exception {

		// get path
		// String pathstr =
		// "C:\\Users\\USER\\eclipse-workspace8\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp1\\wtpwebapps\\DCE\\upload\\NgonNguDanhDauVanBan_17.1A_DeA.docx";
		Path path = Paths.get(pathString);
		// check if folder is .docx file
		if (pathString.endsWith(".docx")) {
			byte[] bytedata = Files.readAllBytes(path);
			// load doc file into XWPF Document
			XWPFDocument docx = new XWPFDocument(new ByteArrayInputStream(bytedata));
			return docx;
		}
		return null;
	}

	public static void readFileText(XWPFDocument docx) throws IOException {
		XWPFWordExtractor extractor = new XWPFWordExtractor(docx);
		String text = extractor.getText();
		System.out.println(text);
		extractor.close();
	}

	public static void readFileParagraph(XWPFDocument docx) throws IOException {
		List<XWPFParagraph> par = docx.getParagraphs();
		System.out.println(par.size());
	}

	public static void readFilePart(XWPFDocument docx) throws IOException {
		List<POIXMLDocumentPart> list = docx.getRelations();
		for (POIXMLDocumentPart poixmlDocumentPart : list) {
			System.out.println(poixmlDocumentPart);
		}

	}

	public static void readFileAllParagraph(XWPFDocument docx) throws IOException {
		List<XWPFParagraph> paragraphList = docx.getParagraphs();
		for (XWPFParagraph xwpfParagraph : paragraphList) {
			System.out.println(xwpfParagraph.getParagraphText());
		}
	}

	public static void readFileAllParagraphIncludeHighLine(XWPFDocument docx) throws IOException {
		List<XWPFParagraph> paragraphList = docx.getParagraphs();
		for (XWPFParagraph xwpfParagraph : paragraphList) {
			List<XWPFRun> runlist = xwpfParagraph.getRuns();
			for (XWPFRun xwpfRun : runlist) {
				if (xwpfRun != null) {
					if (xwpfRun.getCTR().getRPr() != null) {
						if (xwpfRun.getCTR().getRPr().getHighlight() != null) {
							if (xwpfRun.getCTR().getRPr().getHighlight().getVal().toString() == "yellow")
								System.out.println("fuck");
						}
					}
				}
			}
			System.out.println(xwpfParagraph.getParagraphText());
		}
	}

	public static void readFileAllParagraphWithNumId(XWPFDocument docx) {
		int index = 0;
		int indexList = 0;
		int questionList = 1;
		int answerList = 0;
		int questionNum = 1;
		int resetAnswer = 0;

		String mainString = "";
		String truncateString = "";
		List<XWPFParagraph> paragraphList = docx.getParagraphs();
		for (int indexParagraph = 0; indexParagraph <= paragraphList.size() - 1; indexParagraph++) {
			XWPFParagraph paragraph = paragraphList.get(indexParagraph);
			if (paragraph != null) {
				if (paragraph.getNumID() != null) {
					// check indexList to print Part Exam
					if (indexList == 0) {
						System.out.println(Constants.romanNumeral[indexList] + ". " + paragraph.getParagraphText());
						index++;
						indexList++;
					} else
					// check index=1,6,11 to print Question Number
					if (index == questionList) {
						// check next paragraph is null or not
						if (paragraphList.get(indexParagraph + 1) != null) {
							// check next paragraph has NumID and indexList > 0
							if (paragraphList.get(indexParagraph + 1).getNumID() == null && indexList > 0) {
								// loop for concate Question String
								for (int indexPara = indexParagraph + 1; indexPara <= paragraphList.size()
										- 1; indexPara++) {
									// check next paragraph is null or not
									if (paragraphList.get(indexPara) != null) {
										// check next paragraph has NumID or not
										if (paragraphList.get(indexPara).getNumID() == null) {
											mainString = questionNum + ". " + paragraph.getParagraphText();
											truncateString = truncateString + "\n"
													+ paragraphList.get(indexPara).getParagraphText();
											indexParagraph = indexPara;
										} else {
											break;
										}
									} else {
										break;
									}

								}
								System.out.println(mainString + truncateString);
							} else {
								System.out.println(questionNum + ". " + paragraph.getParagraphText());
							}
							mainString = "";
							truncateString = "";
						} else {
							break;
						}
						questionNum++;
						questionList = questionList + 5;
						answerList = questionList - 4;
						resetAnswer = 0;
						index++;
					} else
					// check index=1,6,11 to print Question Number
					if (index == answerList) {
						if (!(indexParagraph == paragraphList.size() - 1)) {
							// check next paragraph is null or not
							if (paragraphList.get(indexParagraph + 1) != null) {
								// check next paragraph has NumID and indexList > 0
								if (paragraphList.get(indexParagraph + 1).getNumID() == null && indexList > 0) {
									// loop for concate Question String
									for (int indexPara = indexParagraph + 1; indexPara <= paragraphList.size()
											- 1; indexPara++) {
										// check next paragraph is null or not
										if (paragraphList.get(indexPara) != null) {
											// check next paragraph has NumID or not
											if (paragraphList.get(indexPara).getNumID() == null) {
												mainString = Constants.ANSWERS[resetAnswer] + ". "
														+ paragraph.getParagraphText();
												truncateString = truncateString + "\n"
														+ paragraphList.get(indexPara).getParagraphText();
												indexParagraph = indexPara;
											} else {
												break;
											}
										} else {
											break;
										}
									}
									System.out.println(mainString + truncateString);
								} else {
									System.out.println(
											Constants.ANSWERS[resetAnswer] + ". " + paragraph.getParagraphText());
								}
								mainString = "";
								truncateString = "";
							} else {
								break;
							}
							answerList++;
							resetAnswer++;
							index++;
						} else {
							System.out.println(Constants.ANSWERS[resetAnswer] + ". " + paragraph.getParagraphText());
							resetAnswer = 0;
						}
					}
				} else {
					System.out.println(paragraph.getParagraphText());
				}
			}
		}

	}

	public static List<XWPFParagraph> getAllPartTitle(XWPFDocument docx) {
		int index = 0;
		int indexList = 0;
		int questionList = 1;
		int answerList = 0;
		int questionNum = 1;
		int resetAnswer = 0;

		String mainString = "";
		String truncateString = "";
		List<XWPFParagraph> paraTitleList = null;
		List<XWPFParagraph> paragraphList = docx.getParagraphs();
		for (int indexParagraph = 0; indexParagraph <= paragraphList.size() - 1; indexParagraph++) {
			XWPFParagraph paragraph = paragraphList.get(indexParagraph);
			if (paragraph != null) {
				if (paragraph.getNumID() != null) {
					// check indexList to print Part Exam
					if (indexList == 0) {
//						System.out.println(Constants.romanNumeral[indexList] + ". " + paragraph.getParagraphText());
						paraTitleList.add(paragraph);
						index++;
						indexList++;
					} else
					// check index=1,6,11 to print Question Number
					if (index == questionList) {
						// check next paragraph is null or not
						if (paragraphList.get(indexParagraph + 1) != null) {
							// check next paragraph has NumID and indexList > 0
							if (paragraphList.get(indexParagraph + 1).getNumID() == null && indexList > 0) {
								// loop for concate Question String
								for (int indexPara = indexParagraph + 1; indexPara <= paragraphList.size()
										- 1; indexPara++) {
									// check next paragraph is null or not
									if (paragraphList.get(indexPara) != null) {
										// check next paragraph has NumID or not
										if (paragraphList.get(indexPara).getNumID() == null) {
											mainString = questionNum + ". " + paragraph.getParagraphText();
											truncateString = truncateString + "\n"
													+ paragraphList.get(indexPara).getParagraphText();
											indexParagraph = indexPara;
										} else {
											break;
										}
									} else {
										break;
									}

								}
//								System.out.println(mainString + truncateString);
							} else {
//								System.out.println(questionNum + ". " + paragraph.getParagraphText());
							}
							mainString = "";
							truncateString = "";
						} else {
							break;
						}
						questionNum++;
						questionList = questionList + 5;
						answerList = questionList - 4;
						resetAnswer = 0;
						index++;
					} else
					// check index=1,6,11 to print Question Number
					if (index == answerList) {
						if (!(indexParagraph == paragraphList.size() - 1)) {
							// check next paragraph is null or not
							if (paragraphList.get(indexParagraph + 1) != null) {
								// check next paragraph has NumID and indexList > 0
								if (paragraphList.get(indexParagraph + 1).getNumID() == null && indexList > 0) {
									// loop for concate Question String
									for (int indexPara = indexParagraph + 1; indexPara <= paragraphList.size()
											- 1; indexPara++) {
										// check next paragraph is null or not
										if (paragraphList.get(indexPara) != null) {
											// check next paragraph has NumID or not
											if (paragraphList.get(indexPara).getNumID() == null) {
												mainString = Constants.ANSWERS[resetAnswer] + ". "
														+ paragraph.getParagraphText();
												truncateString = truncateString + "\n"
														+ paragraphList.get(indexPara).getParagraphText();
												indexParagraph = indexPara;
											} else {
												break;
											}
										} else {
											break;
										}
									}
//									System.out.println(mainString + truncateString);
								} else {
//									System.out.println(
//											Constants.ANSWERS[resetAnswer] + ". " + paragraph.getParagraphText());
								}
								mainString = "";
								truncateString = "";
							} else {
								break;
							}
							answerList++;
							resetAnswer++;
							index++;
						} else {
//							System.out.println(Constants.ANSWERS[resetAnswer] + ". " + paragraph.getParagraphText());
							resetAnswer = 0;
						}
					}
				} else {
//					System.out.println(paragraph.getParagraphText());
				}
			}
		}
		return paraTitleList;
	}
	public static List<String> getAllQuestionContent(XWPFDocument docx) {
		int index = 0;
		int indexList = 0;
		int questionList = 1;
		int answerList = 0;
		int questionNum = 1;
		int resetAnswer = 0;

		String mainString = "";
		String truncateString = "";
		List<String> paraQuestionContentList = null;
		List<XWPFParagraph> paragraphList = docx.getParagraphs();
		for (int indexParagraph = 0; indexParagraph <= paragraphList.size() - 1; indexParagraph++) {
			XWPFParagraph paragraph = paragraphList.get(indexParagraph);
			if (paragraph != null) {
				if (paragraph.getNumID() != null) {
					// check indexList to print Part Exam
					if (indexList == 0) {
//						System.out.println(Constants.romanNumeral[indexList] + ". " + paragraph.getParagraphText());
						index++;
						indexList++;
					} else
					// check index=1,6,11 to print Question Number
					if (index == questionList) {
						// check next paragraph is null or not
						if (paragraphList.get(indexParagraph + 1) != null) {
							// check next paragraph has NumID and indexList > 0
							if (paragraphList.get(indexParagraph + 1).getNumID() == null && indexList > 0) {
								// loop for concate Question String
								for (int indexPara = indexParagraph + 1; indexPara <= paragraphList.size()
										- 1; indexPara++) {
									// check next paragraph is null or not
									if (paragraphList.get(indexPara) != null) {
										// check next paragraph has NumID or not
										if (paragraphList.get(indexPara).getNumID() == null) {
//											mainString = questionNum + ". " + paragraph.getParagraphText();
											mainString = paragraph.getParagraphText();
											truncateString = truncateString + "\n"
													+ paragraphList.get(indexPara).getParagraphText();
											indexParagraph = indexPara;
										} else {
											break;
										}
									} else {
										break;
									}

								}
								paraQuestionContentList.add(mainString+truncateString);
//								System.out.println(mainString + truncateString);
							} else {
								paraQuestionContentList.add(paragraph.getParagraphText());
//								System.out.println(questionNum + ". " + paragraph.getParagraphText());
							}
							mainString = "";
							truncateString = "";
						} else {
							break;
						}
						questionNum++;
						questionList = questionList + 5;
						answerList = questionList - 4;
						resetAnswer = 0;
						index++;
					} else
					// check index=1,6,11 to print Question Number
					if (index == answerList) {
						if (!(indexParagraph == paragraphList.size() - 1)) {
							// check next paragraph is null or not
							if (paragraphList.get(indexParagraph + 1) != null) {
								// check next paragraph has NumID and indexList > 0
								if (paragraphList.get(indexParagraph + 1).getNumID() == null && indexList > 0) {
									// loop for concate Question String
									for (int indexPara = indexParagraph + 1; indexPara <= paragraphList.size()
											- 1; indexPara++) {
										// check next paragraph is null or not
										if (paragraphList.get(indexPara) != null) {
											// check next paragraph has NumID or not
											if (paragraphList.get(indexPara).getNumID() == null) {
												mainString = Constants.ANSWERS[resetAnswer] + ". "
														+ paragraph.getParagraphText();
												truncateString = truncateString + "\n"
														+ paragraphList.get(indexPara).getParagraphText();
												indexParagraph = indexPara;
											} else {
												break;
											}
										} else {
											break;
										}
									}
//									System.out.println(mainString + truncateString);
								} else {
//									System.out.println(
//											Constants.ANSWERS[resetAnswer] + ". " + paragraph.getParagraphText());
								}
								mainString = "";
								truncateString = "";
							} else {
								break;
							}
							answerList++;
							resetAnswer++;
							index++;
						} else {
//							System.out.println(Constants.ANSWERS[resetAnswer] + ". " + paragraph.getParagraphText());
							resetAnswer = 0;
						}
					}
				} else {
//					System.out.println(paragraph.getParagraphText());
				}
			}
		}
		return paraQuestionContentList;
	}
	public static List<String> getAllAnswerContent(XWPFDocument docx) {
		int index = 0;
		int indexList = 0;
		int questionList = 1;
		int answerList = 0;
		int questionNum = 1;
		int resetAnswer = 0;

		String mainString = "";
		String truncateString = "";
		List<String> paraAnswerContentList = null;
		List<XWPFParagraph> paragraphList = docx.getParagraphs();
		for (int indexParagraph = 0; indexParagraph <= paragraphList.size() - 1; indexParagraph++) {
			XWPFParagraph paragraph = paragraphList.get(indexParagraph);
			if (paragraph != null) {
				if (paragraph.getNumID() != null) {
					// check indexList to print Part Exam
					if (indexList == 0) {
//						System.out.println(Constants.romanNumeral[indexList] + ". " + paragraph.getParagraphText());
						index++;
						indexList++;
					} else
					// check index=1,6,11 to print Question Number
					if (index == questionList) {
						// check next paragraph is null or not
						if (paragraphList.get(indexParagraph + 1) != null) {
							// check next paragraph has NumID and indexList > 0
							if (paragraphList.get(indexParagraph + 1).getNumID() == null && indexList > 0) {
								// loop for concate Question String
								for (int indexPara = indexParagraph + 1; indexPara <= paragraphList.size()
										- 1; indexPara++) {
									// check next paragraph is null or not
									if (paragraphList.get(indexPara) != null) {
										// check next paragraph has NumID or not
										if (paragraphList.get(indexPara).getNumID() == null) {
//											mainString = questionNum + ". " + paragraph.getParagraphText();
											mainString = paragraph.getParagraphText();
											truncateString = truncateString + "\n"
													+ paragraphList.get(indexPara).getParagraphText();
											indexParagraph = indexPara;
										} else {
											break;
										}
									} else {
										break;
									}

								}
//								paraQuestionContentList.add(mainString+truncateString);
//								System.out.println(mainString + truncateString);
							} else {
//								paraQuestionContentList.add(paragraph.getParagraphText());
//								System.out.println(questionNum + ". " + paragraph.getParagraphText());
							}
							mainString = "";
							truncateString = "";
						} else {
							break;
						}
						questionNum++;
						questionList = questionList + 5;
						answerList = questionList - 4;
						resetAnswer = 0;
						index++;
					} else
					// check index=1,6,11 to print Question Number
					if (index == answerList) {
						if (!(indexParagraph == paragraphList.size() - 1)) {
							// check next paragraph is null or not
							if (paragraphList.get(indexParagraph + 1) != null) {
								// check next paragraph has NumID and indexList > 0
								if (paragraphList.get(indexParagraph + 1).getNumID() == null && indexList > 0) {
									// loop for concate Question String
									for (int indexPara = indexParagraph + 1; indexPara <= paragraphList.size()
											- 1; indexPara++) {
										// check next paragraph is null or not
										if (paragraphList.get(indexPara) != null) {
											// check next paragraph has NumID or not
											if (paragraphList.get(indexPara).getNumID() == null) {
//												mainString = Constants.ANSWERS[resetAnswer] + ". "
//														+ paragraph.getParagraphText();
												mainString=paragraph.getParagraphText();
												truncateString = truncateString + "\n"
														+ paragraphList.get(indexPara).getParagraphText();
												indexParagraph = indexPara;
											} else {
												break;
											}
										} else {
											break;
										}
									}
									paraAnswerContentList.add(mainString + truncateString);
//									System.out.println(mainString + truncateString);
								} else {
									paraAnswerContentList.add(paragraph.getParagraphText());
//									System.out.println(
//											Constants.ANSWERS[resetAnswer] + ". " + paragraph.getParagraphText());
								}
								mainString = "";
								truncateString = "";
							} else {
								break;
							}
							answerList++;
							resetAnswer++;
							index++;
						} else {
							paraAnswerContentList.add(paragraph.getParagraphText());
//							System.out.println(Constants.ANSWERS[resetAnswer] + ". " + paragraph.getParagraphText());
							resetAnswer = 0;
						}
					}
				} else {
//					System.out.println(paragraph.getParagraphText());
				}
			}
		}
		return paraAnswerContentList;
	}
	public static List<XWPFParagraph> getAllOtherContent(XWPFDocument docx) {
		int index = 0;
		int indexList = 0;
		int questionList = 1;
		int answerList = 0;
		int questionNum = 1;
		int resetAnswer = 0;

		String mainString = "";
		String truncateString = "";
		List<XWPFParagraph> paraOthersContentList = null;
		List<XWPFParagraph> paragraphList = docx.getParagraphs();
		for (int indexParagraph = 0; indexParagraph <= paragraphList.size() - 1; indexParagraph++) {
			XWPFParagraph paragraph = paragraphList.get(indexParagraph);
			if (paragraph != null) {
				if (paragraph.getNumID() != null) {
					// check indexList to print Part Exam
					if (indexList == 0) {
//						System.out.println(Constants.romanNumeral[indexList] + ". " + paragraph.getParagraphText());
						index++;
						indexList++;
					} else
					// check index=1,6,11 to print Question Number
					if (index == questionList) {
						// check next paragraph is null or not
						if (paragraphList.get(indexParagraph + 1) != null) {
							// check next paragraph has NumID and indexList > 0
							if (paragraphList.get(indexParagraph + 1).getNumID() == null && indexList > 0) {
								// loop for concate Question String
								for (int indexPara = indexParagraph + 1; indexPara <= paragraphList.size()
										- 1; indexPara++) {
									// check next paragraph is null or not
									if (paragraphList.get(indexPara) != null) {
										// check next paragraph has NumID or not
										if (paragraphList.get(indexPara).getNumID() == null) {
//											mainString = questionNum + ". " + paragraph.getParagraphText();
											mainString = paragraph.getParagraphText();
											truncateString = truncateString + "\n"
													+ paragraphList.get(indexPara).getParagraphText();
											indexParagraph = indexPara;
										} else {
											break;
										}
									} else {
										break;
									}

								}
//								paraQuestionContentList.add(mainString+truncateString);
//								System.out.println(mainString + truncateString);
							} else {
//								paraQuestionContentList.add(paragraph.getParagraphText());
//								System.out.println(questionNum + ". " + paragraph.getParagraphText());
							}
							mainString = "";
							truncateString = "";
						} else {
							break;
						}
						questionNum++;
						questionList = questionList + 5;
						answerList = questionList - 4;
						resetAnswer = 0;
						index++;
					} else
					// check index=1,6,11 to print Question Number
					if (index == answerList) {
						if (!(indexParagraph == paragraphList.size() - 1)) {
							// check next paragraph is null or not
							if (paragraphList.get(indexParagraph + 1) != null) {
								// check next paragraph has NumID and indexList > 0
								if (paragraphList.get(indexParagraph + 1).getNumID() == null && indexList > 0) {
									// loop for concate Question String
									for (int indexPara = indexParagraph + 1; indexPara <= paragraphList.size()
											- 1; indexPara++) {
										// check next paragraph is null or not
										if (paragraphList.get(indexPara) != null) {
											// check next paragraph has NumID or not
											if (paragraphList.get(indexPara).getNumID() == null) {
//												mainString = Constants.ANSWERS[resetAnswer] + ". "
//														+ paragraph.getParagraphText();
												mainString=paragraph.getParagraphText();
												truncateString = truncateString + "\n"
														+ paragraphList.get(indexPara).getParagraphText();
												indexParagraph = indexPara;
											} else {
												break;
											}
										} else {
											break;
										}
									}
//									paraAnswerContentList.add(mainString + truncateString);
//									System.out.println(mainString + truncateString);
								} else {
//									paraAnswerContentList.add(paragraph.getParagraphText());
//									System.out.println(
//											Constants.ANSWERS[resetAnswer] + ". " + paragraph.getParagraphText());
								}
								mainString = "";
								truncateString = "";
							} else {
								break;
							}
							answerList++;
							resetAnswer++;
							index++;
						} else {
//							paraAnswerContentList.add(paragraph.getParagraphText());
//							System.out.println(Constants.ANSWERS[resetAnswer] + ". " + paragraph.getParagraphText());
							resetAnswer = 0;
						}
					}
				} else {
					paraOthersContentList.add(paragraph);
//					System.out.println(paragraph.getParagraphText());
				}
			}
		}
		return paraOthersContentList;
	}
}
