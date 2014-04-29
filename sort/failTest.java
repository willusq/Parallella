import java.io.*;
import java.nio.file.*;
import java.util.*;
public class failTest{
	private static final String failedLocation =  "./failed.txt";
	private static final String baseDir =  "./epiphany3/";
	private static ArrayList<ArrayList> pass = new ArrayList();
	private static ArrayList<String> chip = new ArrayList();
	private static ArrayList<String> testName;
	private static int[] testPass = new int[21];
	private static int[] testFail = new int[21];
	private static int[] testInc = new int[21];
	private static int[] testDS = new int[21];
	public static void initiate(){
	String[] nameList = {"memory_test_simple.0.0.log", "memory_test_simple.3.3.log", "memory_test_march-g.0.0.log", "memory_test_march-g.3.3.log", "loopback.0.0.log", "emesh.0.0.log", "emesh.0.1.log", "emesh.0.2.log", "emesh.0.3.log", "emesh.1.0.log", "emesh.1.1.log", "emesh.1.2.log",  "emesh.1.3.log", "emesh.2.0.log", "emesh.2.1.log", "emesh.2.2.log", "emesh.2.3.log", "emesh.3.0.log", "emesh.3.1.log",  "emesh.3.2.log",   "emesh.3.3.log"  };
	testName = new ArrayList(Arrays.asList(nameList));
	for(int i = 0; i<21; ++i){
	
	testPass[i] = 0;
	testFail[i] = 0;
	testInc[i] = 0;
	testDS[i] = 0;

	}
	}	

	public static void testDir(File f, int loc){
		if(f.isDirectory()){
			File[] dir = f.listFiles();
			int limit = dir.length;


			for(int i = 0; i < limit; ++i){
				String name = dir[i].getName().replace("/home/linaro/Epiphany/epiphany3/", "");
				String out = testFileSuccess(dir[i]);
				
				if(testName.indexOf(name) != -1){
					if(chip.indexOf(f.getName()) != -1){
						if(out == "123"){
							pass.get(chip.indexOf(f.getName())).set(testName.indexOf(name),"p");
						}else if(out == "dead"){
							pass.get(chip.indexOf(f.getName())).set(testName.indexOf(name),"f");
						}else if(out == "none"){
							pass.get(chip.indexOf(f.getName())).set(testName.indexOf(name),"i");
						}
					}
					else System.out.println(f.getName() + " Does not Exist");
				}else{
					System.out.println(name + " gone at " + "/home/linaro/Epiphany/epiphany3/" + f.getName() + name);

				    
				     boolean success = dir[i].delete();
				     if (success) {
				        System.out.println("The file has been successfully deleted"); 
				     }

					

				}
			}
		}
	}
						

	public static String testFileSuccess(File f){
		try{
			BufferedReader br = new BufferedReader(new FileReader(f));String line;
			while ((line = br.readLine()) != null) {
				if(line.indexOf("0x0 <start>:	0x12345678")>=0){
					return "123";
				}
				if(line.indexOf("0x0 <start>:	0xdeadbeef")>=0){
					return "dead";
				}

			}

			br.close();
		}
		catch(IOException ex){
		}
		return "none";
	}	
	public static void printData(){
/*	for(int i = 0; i<22; ++i){
			System.out.print("     ");			
			for(int j = 0; j<21; ++j){
				char nextLetter;				
				if(testName.get(j).length() > i){
					nextLetter = testName.get(j).charAt(i);
				}else nextLetter = ' ';
				System.out.print("|" + nextLetter);
			}
			
			System.out.println("|");
		}
		for(int i = 0; i < chip.toArray().length; i++){
			String space = "";
			for(int j = 0; j < (5-chip.get(i).length()); ++j){ space += " ";}
			System.out.print(chip.get(i) + space);
			for(int j = 0; j < 21; j++){
				String next = "d";
				if(pass.get(i).get(j) != null) next = (String)pass.get(i).get(j);
				System.out.print("|" + next);
			}
		System.out.println("|");
		


		}

*/
	try {
		BufferedWriter out = new BufferedWriter(new FileWriter("data.txt"));	 		
		out.write("------------------------------------------------------------------------------------------------------------------------------------\n");;		
		for(int i = 0; i<23; ++i){
			out.write("     ");			
			for(int j = 0; j<21; ++j){
				char nextLetter;		
				if(testName.get(j).replace(".log", "").length() > i){
					nextLetter = testName.get(j).charAt(i);
				}else nextLetter = ' ';
				out.write("|  " + nextLetter + "  ");
			}
			
			out.write("|" + "\n");
		} 		
		out.write("------------------------------------------------------------------------------------------------------------------------------------\n");
		for(int i = 0; i < chip.toArray().length; i++){
			String space = "";
			for(int j = 0; j < (5-chip.get(i).length()); ++j){ space += " ";}
			out.write(chip.get(i) + space);
			for(int j = 0; j < 21; j++){
				String next = " ";
				if(pass.get(i).get(j) != null) next = (String)pass.get(i).get(j);
				out.write("|  " + next + "  ");
				if(next==" "){
				testDS[j]++;
				}else if(next=="p"){
				testPass[j]++;
				}else if(next=="f"){
				testFail[j]++;
				}else if(next=="i"){
				testInc[j]++;
				}
			}
		out.write("|" + "\n");
		} 		
		out.write("------------------------------------------------------------------------------------------------------------------------------------\n");
		out.write("------------------------------------------------------------------------------------------------------------------------------------\n");


		out.write("p    ");		
		for(int i = 0; i<21; ++i){
			String space = "  ";		
			if(testPass[i]-10>=0) space = " ";		
			if(testPass[i]-100>=0) space = "";			
			out.write("|  " + testPass[i] + space);

		
		}

		
		out.write("|\nf    ");		
		for(int i = 0; i<21; ++i){
			String space = "  ";			
			if(testFail[i]-10>=0) space = " ";	
			if(testFail[i]-100>=0) space = "";			
			out.write("|  " + testFail[i] + space);

		
		}

		
		out.write("|\ni    ");		
		for(int i = 0; i<21; ++i){
			String space = "  ";			
			if(testInc[i]-10>=0) space = " ";	
			if(testInc[i]-100>=0) space = "";			
			out.write("|  " + testInc[i] + space);

		
		}

		
		out.write("|\nd    ");		
		for(int i = 0; i<21; ++i){
			String space = "  ";		
			if(testDS[i]-10>=0) space = " ";		
			if(testDS[i]-100>=0) space = "";			
			out.write("|  " + testDS[i] + space);

		
		}
		out.write("|");

		out.close();
        
		} catch (IOException e) {}
		
	

	}

	public static void main(String[] args){
		int i = 0;
		initiate();
		try{
			BufferedReader br = new BufferedReader(new FileReader(new File(failedLocation)));String line;
			while ((line = br.readLine()) != null) {
				if(line.indexOf("\n") != -1){
					line.replace("\n","");
				}
				pass.add(new ArrayList());
				for(int j = 0; j<23; ++j){pass.get(i).add(null);}
				chip.add(line);
				testDir(new File(baseDir + line), i);
				++i;
			}

			br.close();
		}catch(IOException ex){}
		printData();
	}
}
