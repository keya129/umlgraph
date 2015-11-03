import java.io.*;
import java.util.*;


public class Parser {
	List<String> classList;
	List<String> interfaceList;
	public void getClasses(File[] files) throws IOException{
		BufferedReader br = null;
		classList = new ArrayList<String>();
		interfaceList = new ArrayList<String>();
		String sCurrentLine;
		for (File f : files) {
			br = new BufferedReader(new FileReader(f));
			while((sCurrentLine = br.readLine()) != null){
				if (!sCurrentLine.isEmpty()){
					sCurrentLine = sCurrentLine.trim().replaceAll("\\s+", " ");
					if(sCurrentLine.indexOf("class") != -1 || sCurrentLine.indexOf("interface") != -1){
						String[] wordList = sCurrentLine.split(" ");
						for(int i=0;i<wordList.length;i++){
							if(wordList[i].equals("class")){
								classList.add(wordList[++i]);
								break;
							}else if(wordList[i].equals("interface")){
								interfaceList.add(wordList[++i]);
								break;
							}
						}
					}
				}
			}
		}
	}



	public String hasClass(String line){
		line = line.trim().replaceAll("<", "< ");
		line = line.trim().replaceAll(">", " >");
		line = line.trim().replaceAll(";", " ;");
		line = line.trim().replaceAll("\\s+", " ");
		List<String> wordList = new ArrayList<String>(Arrays.asList(line.split(" ")));
		if(wordList.contains("class") || wordList.contains("interface")){
			return null;
		}
		for (String word : wordList) {
			if(classList.contains(word) || interfaceList.contains(word)){
				return word;
			}
		}
		return null;
	}

	public boolean isAMethod(String line){
		if((line.indexOf("(")!=-1 && line.indexOf(")")!=-1) && (line.indexOf(";")==-1 || line.indexOf("abstract")!=-1))
		{
			return true;
		}
		return false;
	}

	public boolean hasScopeVariable(String line){
		line = line.trim().replaceAll("\\s+", " ");
		List<String> wordList = new ArrayList<String>(Arrays.asList(line.split(" ")));
		if(wordList.contains("public") || wordList.contains("private") || wordList.contains("protected")){
			return true;
		}
		return false;
	}

	public boolean hasDataTypeVariable(String line){
		line = line.trim().replaceAll("\\s+", " ");
		line = line.trim().replaceAll("\\[", "");
		line = line.trim().replaceAll("\\]", "");
		List<String> wordList = new ArrayList<String>(Arrays.asList(line.split(" ")));
		if(wordList.contains("byte") || wordList.contains("short") || wordList.contains("int") || wordList.contains("long") || wordList.contains("float") || wordList.contains("double")|| wordList.contains("boolean") || wordList.contains("char") || wordList.contains("Byte") || wordList.contains("Short") || wordList.contains("Int") || wordList.contains("Long") || wordList.contains("Float") || wordList.contains("Double")|| wordList.contains("Boolean") || wordList.contains("Char") || wordList.contains("String")){
			return true;
		}
		return false;
	}

	public static void main(String[] args) {
		BufferedReader br = null;
		BufferedReader br2 = null;
		BufferedReader br3 = null;
		BufferedReader br4 = null;
		BufferedReader br5 = null;
		BufferedWriter bw = null;
		BufferedReader findImport = null;
		try {
			String sCurrentLine;
			String sCurrentLine2 = "";
			//String sCurrentLineImp = "";
			String str, str1;
			int hasImportCount = 0;

			File[] files = new File(args[0]).listFiles(new FilenameFilter() {
				public boolean accept(File dir, String filename) {
					return filename.endsWith(".java");
				}
			});

			Parser parser = new Parser();
			parser.getClasses(files);
			System.out.print("classList---"+parser.classList);
			System.out.print("interfaceList---"+parser.interfaceList);
			File filex = new File("copied-files/");
				String[] myFiles;
				if (filex.isDirectory()) {
					myFiles = filex.list();
					for (int i = 0; i < myFiles.length; i++) {
						File myFile = new File(filex, myFiles[i]);
						myFile.delete();
					}
				}
			for (File f : files) {
				boolean hasAssociation = false;
				boolean needsHiddenClass = false;
				String hiddenClass = "";
				File file =new File("copied-files/"+f.getName());
		    	if(!file.exists()){
		    	   file.createNewFile();
		    	}
		    	bw = new BufferedWriter(new FileWriter(file));
		    	br = new BufferedReader(new FileReader(f));
		    	br2 = new BufferedReader(new FileReader(f));

				br3 = new BufferedReader(new FileReader(f));
		    	br4 = new BufferedReader(new FileReader(f));
				br5 = new BufferedReader(new FileReader(f));
				String lineGet, lineGet2;
				boolean flagGetSet = false;
				while((lineGet=br3.readLine()) != null){
					if(lineGet.contains(" get") && lineGet.contains("(") && lineGet.contains("public")){
						while((lineGet2=br4.readLine()) != null){
							if(lineGet2.contains(" set") && lineGet2.contains("(") && lineGet2.contains("public")){
								flagGetSet = true;
							}
						}
					}
				}
		    	findImport = new BufferedReader(new FileReader(f));
		    	while((sCurrentLine = findImport.readLine()) != null){
		    		if(sCurrentLine.indexOf("import java.")!=-1){
		    			hasImportCount++;
		    		}
		    	}
		    	if(hasImportCount != 0){
		    		while((sCurrentLine = br.readLine()) != null){
		    			sCurrentLine2 = br2.readLine();
		    			if(sCurrentLine.indexOf("import java.")!=-1){
		    				hasImportCount--;
		    			}
		    			bw.write(sCurrentLine);
		    			bw.newLine();
		    			if(hasImportCount == 0){
		    				break;
		    			}
		    		}
		    	}
				bw.write("/** ");
				bw.newLine();
				bw.write("* @opt edgecolor"+"\"yellow\"");
				bw.newLine();
				bw.write("* @opt nodefontname \"Times\"");
				bw.newLine();
				bw.write("* @opt bgcolor \".7 .9 1\"");
				bw.newLine();
				bw.write("* @opt nodefillcolor \"#a0a0a0\"");
				bw.newLine();
				bw.write("* @opt nodefontsize 14");
				bw.newLine();
				bw.write(" * @opt operations");
				bw.newLine();
				bw.write(" * @opt all");
				bw.newLine();
				bw.write(" * @opt attributes");
				bw.newLine();
				bw.write(" * @opt types");
				bw.newLine();
				bw.write(" * @hidden");
				bw.newLine();
				bw.write(" */");
				bw.newLine();
				bw.write("class UMLOptions {}");
				bw.newLine();
				String checkgetset="";
				String getHiddenClassLine;
				while ((getHiddenClassLine = br5.readLine()) != null){
					needsHiddenClass = false;
					getHiddenClassLine = getHiddenClassLine.trim().replaceAll("\\s+", " ");
					if (!getHiddenClassLine.isEmpty()){
						if(!parser.isAMethod(getHiddenClassLine) && parser.hasScopeVariable(getHiddenClassLine) && !parser.hasDataTypeVariable(getHiddenClassLine) && parser.hasClass(getHiddenClassLine)==null){
							getHiddenClassLine = getHiddenClassLine.trim().replaceAll(";", " ;");
							getHiddenClassLine = getHiddenClassLine.trim().replaceAll("\\s+", " ");
							List<String> wordList = new ArrayList<String>(Arrays.asList(getHiddenClassLine.split(" ")));
							if(!wordList.contains("class") && !wordList.contains("interface") && !wordList.contains("void") && wordList.size() == 4){
								System.out.println(wordList);
								needsHiddenClass = true;
								hiddenClass = wordList.get(1);
							}
						}
					}
					if(needsHiddenClass){
						bw.newLine();
						bw.write("/**");
						bw.newLine();
						bw.write("*@hidden");
						bw.newLine();
						bw.write("*/");
						bw.newLine();
						bw.write("class " + hiddenClass);
						bw.newLine();
						bw.write("{");
						bw.newLine();
						bw.write("}");
						bw.newLine();
					}
				}

				while ((sCurrentLine = br.readLine()) != null){
					sCurrentLine = sCurrentLine.trim().replaceAll("\\s+", " ");
					if (!sCurrentLine.isEmpty()){
						String className;
						if((className=parser.hasClass(sCurrentLine)) != null){
							if(!hasAssociation){
								bw.write("/**");
								bw.newLine();
								hasAssociation = true;
							}
							if(!parser.isAMethod(sCurrentLine))
							{
								if((sCurrentLine.indexOf("=new ")!=-1 || sCurrentLine.indexOf("= new ")!=-1) && sCurrentLine.indexOf("Collection")==-1 && !parser.hasScopeVariable(sCurrentLine)){
									for(String interfaceName :parser.interfaceList){
										if(sCurrentLine.indexOf(interfaceName)!=-1){
											String s = " * @depend - <uses> - "+interfaceName.trim();
											bw.write(s);
									    	bw.newLine();
										}
									}
								}else if (sCurrentLine.indexOf("collection")!=-1 || sCurrentLine.indexOf("Collection") !=-1 ){
									sCurrentLine = sCurrentLine.trim().replaceAll("<", " < ");
									sCurrentLine = sCurrentLine.trim().replaceAll(">", " > ");
									sCurrentLine = sCurrentLine.trim().replaceAll("\\s+", " ");
									str1="";
									String[] wordList = sCurrentLine.split(" ");
									for(int i=0;i<wordList.length;i++){
										if(wordList[i].equals(">")){
											i++;
											str1 = wordList[i];
											break;
										}
									}
									String s = " * @assoc 0 " + str1.trim() + " * " + className.trim();
							    	bw.write(s);
							    	bw.newLine();
								}else{
									str = "";
									sCurrentLine = sCurrentLine.trim().replaceAll(";", " ;");
									sCurrentLine = sCurrentLine.trim().replaceAll("\\s+", " ");
									String[] wordList = sCurrentLine.split(" ");
									for(int i=0;i<wordList.length;i++){
										if(wordList[i].equals(className)){
											i++;
											str = wordList[i];
											break;
										}
									}
									String s = " * @assoc 1 " + str.trim() + " 1 " + className.trim();
							    	bw.write(s);
							    	bw.newLine();
								}
							}else if(parser.isAMethod(sCurrentLine)){
								sCurrentLine = sCurrentLine.trim().replaceAll("\\s+", " ");
								String[] wordList = sCurrentLine.split(" ");
								for(int i=0;i<wordList.length;i++){
									if(parser.interfaceList.contains(wordList[i])){
										String s = " * @depend - <uses> - "+wordList[i].trim();
								    	bw.write(s);
								    	bw.newLine();
									}
								}
							}
						}
					}
				}
				if(hasAssociation){
					bw.write(" */");
					bw.newLine();
				}
				boolean match1 = false;
				boolean match2 = false;
				boolean flag1=false;
				boolean flag2=false;
				boolean flag3=false;
				boolean flag4=false;
				boolean hasgetset=false;
				while ((sCurrentLine2 = br2.readLine()) != null){
					if(flagGetSet){
						flag3=false;
						flag4=false;
						String mreg = sCurrentLine2;
						if (mreg.contains(" get") && mreg.contains("(") && mreg.contains("public")){
							match1 = true;flag1=true;
							String repthis;
							int flagset=0;
							while((repthis=br2.readLine())!=null && flagset==0){
								if(repthis.contains("return")){
									flagset=1;
									repthis=repthis.replace("return","").trim();
									repthis=repthis.replace("this.","").trim();
									repthis=repthis.replace(";","").trim();
									checkgetset=repthis;
								}
							}
						}
						if (mreg.contains(" set") && mreg.contains("(") && mreg.contains("public")){
							match2 = true;flag2=true;
						//checkgetset=newcgs;

						}
						if (mreg.contains("}") && flag1==true){
							flag3=true;flag1=false;
						}
						if (mreg.contains("}") && flag2==true){
							flag4=true;flag2=false;
						}
						if (flag4==true && flag3==true){
							hasgetset=true;
						}
					}
				if((parser.hasClass(sCurrentLine2) == null || parser.isAMethod(sCurrentLine2) ) && sCurrentLine2.indexOf("protected")==-1 && sCurrentLine2.indexOf("package ")==-1  && (flag1==false
						&& flag2==false && flag3==false && flag4==false)){
					bw.write(sCurrentLine2);
					bw.newLine();
				}
			}
			bw.close();
			String line="",oldtext="";
			File filexx =new File("copied-files/"+f.getName());
			String currentDirectory = filexx.getAbsolutePath();
			BufferedReader readerx = new BufferedReader(new FileReader(currentDirectory));
			while((line=readerx.readLine())!=null)
			{
				if(flagGetSet && line.contains(checkgetset))
				line=line.replace("private", "public");
				oldtext+=line+"\r\n";
			}
			FileWriter writerx=new FileWriter(filexx);
			writerx.write(oldtext);
			writerx.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (br != null)br.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
AssociationParser();
	}


public static void AssociationParser () {

		try{

			File folder = new File("copied-files/");
			//System.out.println("currentDirectory" + )
			File[] listOfFiles = folder.listFiles();
			 for (File file : listOfFiles) {
				System.out.println(file.getName());
			//	FileReader fileReader =  new FileReader("C:/UMLProj/copied-files/" + file.getName());
					BufferedReader 	br = new BufferedReader(new FileReader(file));
					System.out.println("Reading file"+ file.getName());
					String sCurrentLine;
					String sCurrentLine2;
					String plainText = "";
					while ((sCurrentLine = br.readLine()) != null){
						plainText+=sCurrentLine + "\r\n";
						if (!sCurrentLine.isEmpty()){
							sCurrentLine = sCurrentLine.trim().replaceAll("\\s+", " ");
							if(sCurrentLine.indexOf("assoc") != -1){
								String[] wordList = sCurrentLine.split(" ");
								int l = wordList.length;
								String s1 =  wordList[--l] ;
								System.out.println("Association class Printing "+s1);
								String str = s1.concat(".java");
								System.out.println("Appending java " + str);
									for (File files : listOfFiles) {
										if (files.getName().equals(str)) {
											BufferedReader 	brf = new BufferedReader(new FileReader(files));
											String bufferOld = "";
											while ((sCurrentLine2 = brf.readLine()) != null){
												bufferOld+=sCurrentLine2 + "\r\n";
												if(sCurrentLine2.indexOf("assoc") != -1){
													String[] wordList2 = sCurrentLine2.split(" ");
													int len = wordList2.length;
													String s2 =  wordList2[--len] ;
													String str2 = s2.concat(".java");
													System.out.println("In file " + files.getName() + " found " + s2);
													if (file.getName().equals(str2)){
														System.out.println("assoc found with same class");
													//	BufferedReader 	brf1 = new BufferedReader(new FileReader(file));
													//	System.out.println(sCurrentLine);
														String removecolon1 = sCurrentLine.replace(";", "");
														System.out.println(removecolon1);
														String[] wordListCur = removecolon1.split(" ");
														System.out.println(wordListCur[2] + " " + wordListCur[4]);

														String removecolon2 = sCurrentLine2.replace(";", "").trim();
														System.out.println(removecolon2);
														String[] wordListOth = removecolon2.split(" ");
														System.out.println(wordListOth[2] + " " + wordListOth[4]);
														String star = "*"	;

														 String rpl= "* @assoc " + wordListOth[4] + " - " + wordListCur[4] + " " + wordListCur[5];
															System.out.println("Replace" + rpl);
														  plainText =  plainText.replace(sCurrentLine,rpl);
														  bufferOld =  bufferOld.replace(sCurrentLine2,"");
														 //System.out.println("plainText.............." + plainText);
														 //System.out.println("bufferOld.............."+ bufferOld);

													}

												}
											}//while2
											 //System.out.println("Finallll bufferOld.............." + bufferOld);
											 FileWriter writer = new FileWriter(files);
											 writer.write(bufferOld);
											 writer.close();


										}
									}
								}
							}

					}//while
					 FileWriter writer1 = new FileWriter(file);
					 writer1.write(plainText);
					 writer1.close();

				 }//for
		}//try

		catch (IOException e) {
			e.printStackTrace();
		}


}//class
}
