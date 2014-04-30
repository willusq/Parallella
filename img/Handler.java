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
******************************************************************************/

import java.awt.image.BufferedImage;
import java.awt.*;
import java.lang.Thread;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
 
 
public class Handler {
	private static final int MAX_THREAD = 6;
	public static void main(String[] args){
		boolean done = false;
		int iter = 0;
		//This is where it is retrieving the files from. Change to file names
		String[] pics = {"1.jpg","2.jpg","3.jpg","4.jpg","5.jpg","6.jpg","7.jpg","8.jpg","9.jpg","10.jpg","11.jpg","12.jpg","13.jpg","14.jpg","15.jpg","16.jpg","17.jpg","18.jpg","19.jpg","20.jpg","21.jpg","22.jpg","23.jpg","24.jpg",};
		int max = MAX_THREAD;
		Thread[] threads = new Thread[MAX_THREAD];
		if(pics.length < MAX_THREAD) max = pics.length;
		for(int i = 0; i<max; ++i){
			threads[i] = new Thread();
			threads[i].start();
		}
		long start, end, diff1,diff2;
		start = System.nanoTime();
		while(!done){
			done = true;
			for(int i = 0; i<max;++i){
				if(threads[i].getState() == Thread.State.TERMINATED){
					if(iter < pics.length){	
						threads[i] = new Thread(new convertRun(pics[iter],String.valueOf(iter)));
						threads[i].start();
						done = false;	
						iter++;	
					}
				}else{
					done = false;
				}
			
			}
		}

		end = System.nanoTime();
		diff1 = end-start;

		max = 1;
		iter = 0;
		done = false;
		if(pics.length < MAX_THREAD) max = pics.length;
		threads = new Thread[max];
		for(int i = 0; i<max; ++i){
			threads[i] = new Thread();
			threads[i].start();
		}

		start = System.nanoTime();

		/*for(int i = 0; i< pics.length; ++i){
			try{
				SimpleConvertImage.gray(pics[i]);

			}catch(IOException ex){System.out.println("IOException");}

		}
		*/
		
		while(!done){
			done = true;
			for(int i = 0; i<max;++i){
				if(threads[i].getState() == Thread.State.TERMINATED){
					if(iter < pics.length){	
						threads[i] = new Thread(new convertRun(pics[iter],String.valueOf(iter)));
						threads[i].start();
						done = false;
						iter++;	
					}
				}else{
					done = false;
					
				}
			
			}
		}

		end = System.nanoTime();
		diff2 = end-start;
		System.out.println("Java with threads took " + diff1/1000000 + " milliseconds, while Java without threads took " + diff2/1000000 + " milliseconds");
	}


}
