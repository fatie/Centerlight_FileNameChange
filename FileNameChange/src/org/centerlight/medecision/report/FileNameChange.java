package org.centerlight.medecision.report;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileFilter;
import java.io.IOException;

import javax.swing.JFileChooser;

public class FileNameChange {
	private final static String defaultFolder = "C:\\Users\\FGuo\\Desktop\\medecision report";
	private File dir;
	private BufferedWriter bf;

	
	
	private File chooseFile(String defaultFolder){
		JFileChooser fileChooser = new JFileChooser(defaultFolder);
		fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		fileChooser.setAcceptAllFileFilterUsed(false);
		if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION){
			return fileChooser.getSelectedFile();
		}
		else {
			System.out.println("No file selected\n");
			return null;
		}
	}
	
	public FileNameChange() throws IOException{
		this(defaultFolder);
	}
	public FileNameChange(String folderPath) throws IOException{
		this.dir = chooseFile(folderPath);
	}
	public File getFileFolder(){
		return this.dir;
	}
	
	public void changeFileName(File fileFolder) throws IOException{
		File[] subDirs = fileFolder.listFiles(new FileFilter() {
		    public boolean accept(File pathname) {
		        return true;//pathname.isDirectory();
		    }
		});
		
		for (File subDir : subDirs) {
			if (subDir.isDirectory()) {
				changeFileName(subDir);
			} else {
				String newFilePath;
				String tempName = subDir.getName();
				String fullPath = subDir.getAbsolutePath();
				String time = fullPath.substring(fullPath.indexOf("201"),fullPath.indexOf("201")+8);
				if(tempName.indexOf("201") > -1){
					tempName=tempName.replace("_", "").replace("-", "");
					newFilePath = "C:\\Users\\FGuo\\Desktop\\medecision report\\renamedFile\\"+time+"_"+tempName.substring(0,tempName.indexOf("201"))+tempName.substring(tempName.indexOf('.'));
					
					
	//				for (int i = 0; i < temp.length(); i++) {
	//					bf.append(temp.charAt(i));
	//				}
	//				bf.append("\n");
				} else {
					newFilePath = "C:\\Users\\FGuo\\Desktop\\medecision report\\renamedFile\\"+time + "_" + tempName;
				}
				
				subDir.renameTo(new File(newFilePath));
			}
		}
	}
	
	public void closeBuffer() throws IOException{
		bf.close();
	}
}
