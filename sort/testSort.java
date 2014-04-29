import java.io.*;
import java.util.*;

public class testSort{
	private static final String testDir = "./epiphany3";
	private static final int testTotal = 21;
	private static File[] allTest;
	private static int[] testCount = new int[testTotal];
	private static int[] testPass = new int[testTotal];
	private static ArrayList testName = new ArrayList();
	private static ArrayList incomplete = new ArrayList();
	public static void initiate(){
			String[] nameList = {"memory_test_simple.0.0.log", "memory_test_simple.3.3.log", "memory_test_march-g.0.0.log", "memory_test_march-g.3.3.log", "loopback.0.0.log", "emesh.0.0.log", "emesh.0.1.log", "emesh.0.2.log", "emesh.0.3.log", "emesh.1.0.log", "emesh.1.1.log", "emesh.1.2.log",  "emesh.1.3.log", "emesh.2.0.log", "emesh.2.1.log", "emesh.2.2.log", "emesh.2.3.log", "emesh.3.0.log", "emesh.3.1.log",  "emesh.3.2.log",   "emesh.3.3.log"  };
testName = new ArrayList(Arrays.asList(nameList));
                for(int i = 0; i<testTotal;++i){
			testCount[i] = 0;
			testPass[i] = 0;
		}
	}
	public static void testDir(File dir){
		File[] allInDir;
		if(dir.isDirectory()){
                        allInDir = dir.listFiles();
			if (dir.getName().matches("[0-9]+")){
				
				for(int i = 0; i<allInDir.length;++i){
			
					try{
						if(!testFileSuccess(allInDir[i]) && testName.indexOf(allInDir[i].getName()) != -1){
							testCount[testName.indexOf(allInDir[i].getName())]++;
			                                if(dir.getName().matches("[0-9]+") && incomplete.indexOf(dir.getName()) == -1){
                                  				incomplete.add(dir.getName());
								System.out.println(dir.getName());
							}

						}else{
							testPass[testName.indexOf(allInDir[i].getName())]++;
						}
					}catch(ArrayIndexOutOfBoundsException ex){
					}
				}
	                }
		}

	}

	public static boolean testFileSuccess(File f){
		try{
			BufferedReader br = new BufferedReader(new FileReader(f));String line;
			while ((line = br.readLine()) != null) {
				if(line.indexOf("0x0 <start>:	0x12345678")>=0){				
					return true;
				}
			}

			br.close();
		}
		catch(IOException ex){
		}
		return false;
	}
	public static void printData(){		
		for(int i = 0; i < testPass.length;++i){
			System.out.println(testName.get(i) + " Pass: " + testPass[i] + " | Fail: " + testCount[i]);
		}
		System.out.println("Incomplete: " + incomplete.toArray().length);
		try {
			BufferedWriter out = new BufferedWriter(new FileWriter("failed.txt"));		
			for(int i = 0; i < incomplete.toArray().length; ++i){
		            out.write(incomplete.get(i) + "\n");
			}
 		           out.close();
        } catch (IOException e) {}
	}

	public static void main(String[] args){
		File root = new File(testDir);
		if(root.isDirectory()){
			allTest = root.listFiles();
			initiate();			
			for(int i = 0; i<allTest.length;++i){
				System.out.println("Testing: " + allTest[i]);
				testDir(allTest[i]);
                        }
		printData();

		}
	}
}
