/*******************************************************************************
* Copyright 2011 See AUTHORS file.
*
* Licensed under the Apache License, Version 2.0 (the "License");

* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
*
* http://www.apache.org/licenses/LICENSE-2.0
*

* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,

* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and

* limitations under the License.
***********************************/

import java.lang.Thread;
import java.io.IOException;

public class convertThread extends Thread{
	private String picName;	
	private Thread t;
   private String threadName;
	public boolean done;
   
	convertThread(){
	threadName = "null";
		picName = null;	
	}

   convertThread(String pic, String name){
		threadName = name;
		picName = pic;				
   }
   public void run() {
			done = false;
      try {
         SimpleConvertImage.nativeGray(picName);
     	} catch(IOException e){
			}
	}
	
		public void setPic(String pic){
			picName = pic;
		}
   
   

}


