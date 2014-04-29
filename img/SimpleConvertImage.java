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
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
 
 
public class SimpleConvertImage {
	private static String dirName = "images/";	
	static{
		System.loadLibrary("bytemgr");
	}
	public static native byte[] process(byte[] byte_array);
	
	public static boolean nativeGray(String name) throws IOException{
		BufferedImage img=ImageIO.read(new File(dirName + name));
		ByteArrayOutputStream baos=new ByteArrayOutputStream();
		ImageIO.write(img, "bmp", baos );
		byte[] bytes1=baos.toByteArray();

		byte[] bytes2 = process(bytes1);
		BufferedImage image=ImageIO.read(new ByteArrayInputStream(bytes2));
		
		ImageIO.write(image, "jpg", new File("NativeAfter" + name));
		if(bytes2[1]!=bytes1[1]){return true;}else{return true;}
	}
	public static void gray(String name) throws IOException{	
		BufferedImage img=ImageIO.read(new File(dirName + name));
		ByteArrayOutputStream baos=new ByteArrayOutputStream();
		ImageIO.write(img, "bmp", baos );
		byte[] bytes1=baos.toByteArray();
		byte[] bytes2 = bytes1;

		for (int j = 90	; j < bytes1.length; j+=3){       
			bytes2[j] = bytes1[j+1];
      bytes2[j+2] = bytes1[j+1];
    }
		
		BufferedImage image=ImageIO.read(new ByteArrayInputStream(bytes2));
		
		ImageIO.write(image, "jpg", new File("NNAfter" + name));
	}

	}
