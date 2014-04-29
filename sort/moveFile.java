import java.io.*;
public class moveFile{
	public static void main(String[] args){
		File root = new File("/home/linaro/Epiphany/epiphany3/fileDump");
		File[] allDir = root.listFiles();
		for(int i = 0; i<allDir.length; ++i){
			if(allDir[i].getName().replace("epiphany3", "").matches("[0-9]+")){
				String newLoc = "/home/linaro/Epiphany/epiphany3/" + allDir[i].getName().replace("epiphany3", "");
				allDir[i].renameTo(new File(newLoc));
			}	
		}
	}
}
