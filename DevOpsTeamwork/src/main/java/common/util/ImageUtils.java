package common.util;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @author lisw
 * @since 
 */
public class ImageUtils {

	public ImageUtils() {
		// TODO Auto-generated constructor stub
	}
	/** 
     * 上传图片文件,并保存到指定的路径当中 
     */  
    @SuppressWarnings("null")

	public static String addSingleImage(HttpServletRequest request,String path1,String imgname){

		//转型为MultipartHttpRequest(重点的所在)
		MultipartHttpServletRequest multipartRequest  =  (MultipartHttpServletRequest) request;
		//  获得第1张图片（根据前台的name名称得到上传的文件）
		// if (imgname==null)imgFile
		MultipartFile imgFile1  =  multipartRequest.getFile(imgname);
		return addImage(imgFile1,path1);

	}

	public static List<String> addImages(HttpServletRequest request,String path1,String imgname){
		//转型为MultipartHttpRequest(重点的所在)
		MultipartHttpServletRequest multipartRequest  =  (MultipartHttpServletRequest) request;
		//  获得第1张图片（根据前台的name名称得到上传的文件）
		// if (imgname==null)imgFile
		List<MultipartFile> multipartFiles  =  multipartRequest.getFiles(imgname);
		List<String> filename=new ArrayList<>();
		for (MultipartFile mf:multipartFiles){
			filename.add(addImage(mf,path1));
		}
		return filename;


	}
	public static String addImage(MultipartFile imgFile1,String path1) {
		String filename=null;
		//定义一个数组，用于保存可上传的文件类型
         List<String> fileTypes = new ArrayList<String>();  
         fileTypes.add("jpg");  
         fileTypes.add("jpeg");  
         fileTypes.add("bmp");  
         fileTypes.add("gif");  
         fileTypes.add("png");
         //保存第一张图片  
         if(!(imgFile1.getOriginalFilename() ==null || "".equals(imgFile1.getOriginalFilename()))) {  
			/*下面调用的方法，主要是用来检测上传的文件是否属于允许上传的类型范围内，及根据传入的路径名 
			*自动创建文件夹和文件名，返回的File文件我们可以用来做其它的使用，如得到保存后的文件名路径等 
			*这里我就先不做多的介绍。 
			*/  
            // File file1 = this.getFile(imgFile1, path2,path1,fileTypes);
        	 filename=getrundm();
        	 String fileName = imgFile1.getOriginalFilename();
        	 boolean type = false;
        	 for(String filet : fileTypes){
        		 if(fileName.contains(filet)){
        			 type=true;
        		 }
        	 }
        	 if(!type){
        		 return "not";
        	 }
        	 String ext = fileName.substring(fileName.lastIndexOf(".")+1,fileName.length());  
             //对扩展名进行小写转换  
             ext = ext.toLowerCase();  
        	 try {
				InputStream input = imgFile1.getInputStream();
				File file1=file_put_contents(path1+filename+"."+ext,input);
				if (file1!=null){
					return filename+"."+ext;
				}
				System.out.println(file1.getName());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
              
         }  
       return filename;    
          
    }  

   
    public static File file_put_contents(String file_name,InputStream is){
		File file=new File(file_name);
    	OutputStream os=null;
    	try{
    	os=new FileOutputStream(file);
    	byte buffer[]=new byte[4*1024];
    	while((is.read(buffer))!=-1){
    	os.write(buffer);
    	}
    	os.flush();
    	}
    	catch(Exception e){
    	e.printStackTrace();
    	}
    	finally{
    	try{
    	os.close();
    	}
    	catch(Exception e){
    	e.printStackTrace();
    	}
    	}
    	return file;
    	}
    //获取随机数
    private static String getrundm(){
    	String res=DateUtils.getDate("yyyyMMddHHmmss")+String.valueOf(Math.round(Math.random() * 10000));
    	return res;
    }
    
    //获取随机数
    public static String getrundm(String moduleName){
    	String res=moduleName+""+UUID.randomUUID().toString();
    	return res;
    }
}
