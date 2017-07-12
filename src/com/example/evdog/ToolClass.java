/****************************************Copyright (c)*************************************************
**                      Fujian Junpeng Communicaiton Technology Co.,Ltd.
**                               http://www.easivend.com.cn
**--------------File Info------------------------------------------------------------------------------
** File name:           ToolClass.java
** Last modified Date:  2015-01-10
** Last Version:         
** Descriptions:        工具类，这里面存放的主要是static函数，static成员，统一作为全局变量和全局函数用       
**------------------------------------------------------------------------------------------------------
** Created by:          guozhenzhen 
** Created date:        2015-01-10
** Version:             V1.0 
** Descriptions:        The original version       
********************************************************************************************************/


package com.example.evdog;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.charset.Charset;
import java.security.KeyStore;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;
import java.util.Map.Entry;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;

import org.json.JSONException;
import org.json.JSONObject;


import android.app.ActivityManager;
import android.app.ActivityManager.RunningServiceInfo;
import android.content.ContentResolver;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore.Images.ImageColumns;
import android.util.Log;
import android.view.Gravity;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

public class ToolClass 
{
	public final static int VERBOSE=0;
	public final static int DEBUG=1;
	public final static int INFO=2;
	public final static int WARN=3;
	public final static int ERROR=4;
	public static String EV_DIR=null;//ev包的地址
	private static int bentcom_id=-1,com_id=-1,columncom_id=-1,extracom_id=-1;//串口id号
	private static String bentcom="",com="",columncom="",extracom="";//串口描述符
	private static int bill_err=0,coin_err=0;//纸币器，硬币器故障状态
	public static String vmc_no="";//本机编号
	public static Bitmap mark=null;//售完图片
	public static int goc=0;//是否使用出货确认板1是
	public static int extraComType=0;//1使用冰山机型，2使用展示位
	public static Map<Integer, Integer> huodaolist=null;//保存逻辑货道与物理货道的对应关系
	public static Map<Integer, Integer> elevatorlist=null;//保存升降机逻辑货道与物理货道的对应关系
	public static Map<String, String> selectlist=null;//保存选货按键id与商品id的对应关系
	public static int orientation=0;//使用横屏还是竖屏模式
	public static SSLSocketFactory ssl=null;//ssl网络加密
	public static Context context=null;//本应用context
	private static int ServerVer=1;//0旧的后台，1一期的后台
	public static String version="";//本机版本号
	public static boolean CLIENT_STATUS_SERVICE=true;//true本机可以使用,false本机暂停销售 
	
	public static boolean isCLIENT_STATUS_SERVICE() {
		return CLIENT_STATUS_SERVICE;
	}

	public static void setCLIENT_STATUS_SERVICE(boolean cLIENT_STATUS_SERVICE) {
		CLIENT_STATUS_SERVICE = cLIENT_STATUS_SERVICE;
	}
	
	

	public static String getVersion() {
		String curVersion=null;
		int curVersionCode=0;
		 try {
	            PackageInfo pInfo = context.getPackageManager().getPackageInfo(
	            		context.getPackageName(), 0);
	            curVersion = pInfo.versionName;
	            curVersionCode = pInfo.versionCode;
	        } catch (NameNotFoundException e) {
	            Log.e("update", e.getMessage());
	            curVersion = "1.1.1000";
	            curVersionCode = 111000;
	        }
		 version=(curVersion+curVersionCode).toString();
		return version;
	}

	public static int getServerVer() {
		return ServerVer;
	}

	public static void setServerVer(int serverVer) {
		ServerVer = serverVer;
	}

	public static String getBentcom() {
		return bentcom;
	}

	public static void setBentcom(String bentcom) {
		ToolClass.bentcom = bentcom;
	}

	public static String getCom() {
		return com;
	}

	public static void setCom(String com) {
		ToolClass.com = com;
	}

	public static String getColumncom() {
		return columncom;
	}

	public static void setColumncom(String columncom) {
		ToolClass.columncom = columncom;
	}
	
	public static String getExtracom() {
		return extracom;
	}

	public static void setExtracom(String extracom) {
		ToolClass.extracom = extracom;
	}

	public static Context getContext() {
		return context;
	}

	public static void setContext(Context context) {
		ToolClass.context = context;
	}

	public static String getEV_DIR() {
		return EV_DIR;
	}

	public static void setEV_DIR(String eV_DIR) {
		EV_DIR = eV_DIR;
	}

	public static SSLSocketFactory getSsl() {
		return ssl;
	}

	public static void setSsl(SSLSocketFactory ssl) {
		ToolClass.ssl = ssl;
	}

	public static int getOrientation() {
		return orientation;
	}

	public static void setOrientation(int orientation) {
		ToolClass.orientation = orientation;
	}

	public static Bitmap getMark() {
		return mark;
	}

	public static void setMark(Bitmap mark) {
		ToolClass.mark = mark;
	}
	
	

	

	public static String getVmc_no() {
		return vmc_no;
	}

	public static void setVmc_no(String vmc_no) {
		ToolClass.vmc_no = vmc_no;
	}

	public static int getBentcom_id() {
		return bentcom_id;
	}

	public static void setBentcom_id(int bentcom_id) {
		ToolClass.bentcom_id = bentcom_id;
	}
	
	public static int getColumncom_id() {
		return columncom_id;
	}

	public static void setColumncom_id(int columncom_id) {
		ToolClass.columncom_id = columncom_id;
	}

	public static int getCom_id() {
		return com_id;
	}

	public static void setCom_id(int com_id) {
		ToolClass.com_id = com_id;
	}
	
	public static int getExtracom_id() {
		return extracom_id;
	}

	public static void setExtracom_id(int extracom_id) {
		ToolClass.extracom_id = extracom_id;
	}

	
	
	/**
     * 设置根目录文件
     */
    public static void SetDir() 
    {
    	final String SDCARD_DIR=File.separator+"sdcard"+File.separator+"ev";
    	final String NOSDCARD_DIR=File.separator+"ev";
    	File fileName=null;
    	String  sDir =null,str=null;
    	Map<String, String> list=null;
    	    	
        try {
        	  //首先判断sdcard是否插入
        	  String status = Environment.getExternalStorageState();
        	  if (status.equals(Environment.MEDIA_MOUNTED)) 
        	  {
        		 sDir = SDCARD_DIR;;
        	  } 
        	  else
        	  {
        		  sDir = NOSDCARD_DIR;
        	  }
        	  ToolClass.setEV_DIR(sDir); 
        	             
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
	
	//Log方法，用于打印log，和在文本文件中打印操作日志
	public static void Log(int info,String tag,String str,String filename)
	{
		String infotype="";
		switch(info)
		{
			case VERBOSE:
				infotype="VERBOSE";
				Log.v(tag,str);
				break;
			case DEBUG:
				infotype="DEBUG";
				Log.d(tag,str);
				break;
			case INFO:
				infotype="INFO";
				Log.i(tag,str);
				break;
			case WARN:
				infotype="WARN";
				Log.w(tag,str);
				break;
			case ERROR:
				infotype="ERROR";
				Log.e(tag,str);
				break;	
		}	
		infotype="("+infotype+"),["+tag+"] "+str;
		AppendLogFile(infotype,filename);
	}
	
	/**
     * 追加文件：使用FileWriter
     */
    public static void AppendLogFile(String content,String filename) 
    {
    	String  sDir =null;
    	File fileName=null;
    	SimpleDateFormat tempDate = new SimpleDateFormat("yyyy-MM-dd" + " "  
                + "HH:mm:ss:SSS"); //精确到毫秒 
        String datetime = tempDate.format(new java.util.Date()).toString();  
        String cont=datetime+content+"\r\n";
    	
        try {
        	 sDir = ToolClass.getEV_DIR()+File.separator+"logs";
        	
        	  File dirName = new File(sDir);
        	 //如果目录不存在，则创建目录
        	 if (!dirName.exists()) 
        	 {  
                //按照指定的路径创建文件夹  
        		dirName.mkdirs(); 
             }
        	 
        	 fileName=new File(sDir+File.separator+filename);         	
        	//如果不存在，则创建文件
        	if(!fileName.exists())
        	{  
    	      fileName.createNewFile(); 
    	    }  
            //打开一个写文件器，构造函数中的第二个参数true表示以追加形式写文件
//            FileWriter writer = new FileWriter(fileName, true);
//            writer.write(cont);
//            writer.close();
        	Writer writer = new BufferedWriter( new OutputStreamWriter(new FileOutputStream(fileName, true),Charset.forName("gbk")));
            writer.write(cont);
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    	
	//获取文件创建时间
    public static String getFileCreated(final File file)  
    {  
		 String res=null;
		 Scanner scan = null ;
		 try{
		 	scan = new Scanner(file) ;	// 从文件接收数据
		 	if(scan.hasNext())
		 	{
		 		res=scan.next()+" "+scan.next();	//	取数据		 		
		 	}
		 	
		 }catch(Exception e){}
		 res=res.substring(0, res.indexOf("(INFO)"));// 从收入信息中截取收入编号
		 System.out.println(" 文件创建时间1="+res);
         return res;
    }
	
	 
	 
	//重命名文件名fileName原文件名,sDir是目录
    public static void updatefile(File fileName,String  sDir)
	{
		SimpleDateFormat tempDate2 = new SimpleDateFormat("yyyy-MM-dd-HHmmss"); //精确到毫秒 
        String datetime2 = tempDate2.format(new java.util.Date()).toString();
        String oldname=fileName.getName();
		String newname=datetime2+oldname;
		System.out.println(fileName+" 修改文件操作="+newname);            		
		fileName.renameTo(new File(sDir+File.separator+newname));
	}
    
    //java设定一个日期时间，加几分钟（小时或者天）后得到新的日期
    //返回的是字符串型的时间，
    //输入的是String day基准时间, int x天数
    public static String addDateMinut(String day, int x)
    {   
    	// 24小时制  
    	//引号里面个格式也可以是 HH:mm:ss或者HH:mm等等，很随意的，不过在主函数调用时，要和输入的变
    	//量day格式一致
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = null;   
        try {   
            date = format.parse(day);   
        } catch (Exception ex) {   
            ex.printStackTrace();   
        }   
        if (date == null)   
            return "";   
        System.out.println("front:" + format.format(date)); //显示输入的日期  
        Calendar cal = Calendar.getInstance();   
        cal.setTime(date);   //得到基准时间
        
        cal.add(Calendar.DATE, x);// 天 
        date = cal.getTime();   
        System.out.println("after:" + format.format(date));  //显示更新后的日期 
        cal = null;   
        return format.format(date);   
  
    } 
    /**
     * 递归删除productImage文件和文件夹
     * @param file    要删除的根目录
     */
    public static void deleteproductImageFile()
    {
    	String  sDir =null;
    	 try {
    		  sDir = ToolClass.getEV_DIR()+File.separator+"productImage";
        	  File dirName = new File(sDir);
        	 //如果目录不存在，则创建目录
        	 if (!dirName.exists()) 
        	 {  
                //按照指定的路径创建文件夹  
        		dirName.mkdirs(); 
             }
        	 
        	 deleteAllZIPFile(dirName);         	
        	
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
     * 递归删除ads,adshuo文件和文件夹
     * @param file    要删除的根目录
     */
    public static void deleteadsImageFile()
    {
    	String  sDir =null;
    	 try {
    		 //删除广告目录
    		  sDir = ToolClass.getEV_DIR()+File.separator+"ads";
        	  File dirName = new File(sDir);
        	 //如果目录不存在，则创建目录
        	 if (!dirName.exists()) 
        	 {  
                //按照指定的路径创建文件夹  
        		dirName.mkdirs(); 
             }
        	 
        	 deleteAllZIPFile(dirName);
        	 
        	//删除弹窗广告目录
	   		sDir = ToolClass.getEV_DIR()+File.separator+"adshuo";
	       	dirName = new File(sDir);
	       	//如果目录不存在，则创建目录
	       	if (!dirName.exists()) 
	       	{  
	            //按照指定的路径创建文件夹  
	       		dirName.mkdirs(); 
	        }
	       	 
	       	deleteAllZIPFile(dirName);
        	
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
     * 递归删除ZIP文件和文件夹
     * @param file    要删除的根目录
     */
    private static void deleteZIPFile()
    {
    	String  sDir =null;
    	 try {
        	  sDir = ToolClass.ReadLogFile()+"ZIPFile";
        	  File dirName = new File(sDir);
        	 //如果目录不存在，则创建目录
        	 if (!dirName.exists()) 
        	 {  
                //按照指定的路径创建文件夹  
        		dirName.mkdirs(); 
             }
        	 
        	 deleteAllZIPFile(dirName);         	
        	
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private static void deleteAllZIPFile(File file)
    {        
        if(file.isDirectory())
        {
            File[] childFile = file.listFiles();
            if(childFile == null || childFile.length == 0)
            {
                return;
            }
            for(File f : childFile)
            {
            	f.delete();
            }
        }
    }
     /**  
     * 复制单个文件  
     * @param oldPath String 原文件路径 如：c:/fqf.txt  
     * @param newPath String 复制后路径 如：f:/fqf.txt  
     * @return boolean  
     */   
	   private static void copyFile(String oldPath, String newPath) 
	   {   
	       try {   
	           int bytesum = 0;   
	           int byteread = 0;   
	           File oldfile = new File(oldPath);   
	           if (oldfile.exists()) { //文件存在时   
	               InputStream inStream = new FileInputStream(oldPath); //读入原文件   
	               FileOutputStream fs = new FileOutputStream(newPath);   
	               byte[] buffer = new byte[1444];   
	               int length;   
	               while ( (byteread = inStream.read(buffer)) != -1) {   
	                   bytesum += byteread; //字节数 文件大小   
	                   System.out.println(bytesum);   
	                   fs.write(buffer, 0, byteread);   
	               }   
	               inStream.close();   
	           }   
	       }   
	       catch (Exception e) {   
	           System.out.println("复制单个文件操作出错");   
	           e.printStackTrace();   
	  
	       }   
	  
	   }  
	    
    
	
	 /* 遍历目录内文件列表， file是目录名，datetime是当前时间，如果超过半个月，就删除掉这个文件
	  * */  
    public static void delFiles(File file) 
    {  
    	//1.设置起始时间和结束时间
    	SimpleDateFormat tempDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
    	
    	//本周起始时间
        Calendar todayStart = Calendar.getInstance(); 
        todayStart.setFirstDayOfWeek(Calendar.MONDAY);  
        todayStart.set(Calendar.HOUR_OF_DAY, 0);  
        todayStart.set(Calendar.MINUTE, 0);  
        todayStart.set(Calendar.SECOND, 0);  
        todayStart.set(Calendar.MILLISECOND, 0); 
        todayStart.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY); 
        //再推前两周
        todayStart.add(Calendar.WEEK_OF_YEAR, -2);
        Date date = todayStart.getTime(); 
        String starttime=tempDate.format(date);
        ParsePosition posstart = new ParsePosition(0);  
    	Date dstart = (Date) tempDate.parse(starttime, posstart);
    	
    	//遍历这个文件夹里的所有文件
		File[] files = file.listFiles();
		if (files.length > 0) 
		{  
			for (int i = 0; i < files.length; i++) 
			{
			  if(!files[i].isDirectory())
			  {		
				    //3.如果存在文件，则判断
		        	File fileName=new File(files[i].toString()); 
		        	if(fileName.exists())
		        	{  
		        		String logdatetime = getFileCreated(fileName);
		        		ParsePosition poslog = new ParsePosition(0);  
		        		Date dlog = (Date) tempDate.parse(logdatetime, poslog);
		        		ToolClass.Log(ToolClass.INFO,"EV_DOG","判断日志目录内文件="+files[i].toString()+"时间="+logdatetime+",="+dlog.getTime(),"dogservice.txt");
		        		//判断是否文件早于本周
		        		if(dlog.getTime()<=dstart.getTime())
		            	{
		        			ToolClass.Log(ToolClass.INFO,"EV_DOG","文件="+files[i].toString()+"删除","dogservice.txt");
		            		fileName.delete();		            		
		            	}
		        		else
		        		{
		        			ToolClass.Log(ToolClass.INFO,"EV_DOG","文件="+files[i].toString()+"排除","dogservice.txt");
		        		}
		    	    } 
			  }
			}
		}    
    }
    
    /**
     * 使用isAPKFile,判断这个程序是已经存在目录中,true存在,false不存在
     */
    public static boolean isAPKFile(String filename) 
    {
    	String  sDir =null;
    	File fileName=null;
    	boolean fileext=false;
        try {
        	sDir = ToolClass.getEV_DIR()+File.separator+"APKFile";
        	  File dirName = new File(sDir);
        	 //如果目录不存在，则创建目录
        	 if (!dirName.exists()) 
        	 {  
                //按照指定的路径创建文件夹  
        		dirName.mkdirs(); 
             }
        	 
        	 fileName=new File(sDir+File.separator+filename);         	
        	//如果不存在，则创建文件
        	if(!fileName.exists())
        	{  
        		fileext=false; 
    	    }  
        	else
        		fileext=true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return fileext; 
    }
    /**
     * 使用isAPKFile,保存这个程序到目录中
     */
    public static File setAPKFile(String filename) 
    {
    	String  sDir =null;
    	File fileName=null;
    	boolean fileext=false;
        try {
        	sDir = ToolClass.getEV_DIR()+File.separator+"APKFile";
        	  File dirName = new File(sDir);
        	 //如果目录不存在，则创建目录
        	 if (!dirName.exists()) 
        	 {  
                //按照指定的路径创建文件夹  
        		dirName.mkdirs(); 
             }
        	 
        	 fileName=new File(sDir+File.separator+filename);         	
        	
        } catch (Exception e) {
            e.printStackTrace();
        }
        return fileName; 
    }
    /**
     * 递归删除APK文件和文件夹
     * @param file    要删除的根目录
     */
    public static void deleteAPKFile()
    {
    	String  sDir =null;
    	 try {
        	sDir = ToolClass.getEV_DIR()+File.separator+"APKFile";
        	  File dirName = new File(sDir);
        	 //如果目录不存在，则创建目录
        	 if (!dirName.exists()) 
        	 {  
                //按照指定的路径创建文件夹  
        		dirName.mkdirs(); 
             }
        	 
        	 deleteAllFile(dirName);         	
        	
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private static void deleteAllFile(File file)
    {        
        if(file.isDirectory())
        {
            File[] childFile = file.listFiles();
            if(childFile == null || childFile.length == 0)
            {
                return;
            }
            for(File f : childFile)
            {
            	f.delete();
            }
        }
    }
    
    
    
     
    /**
     * 使用isImgFile,判断这个商品图片是已经存在目录中,true存在,false不存在
     */
    public static boolean isImgFile(String filename) 
    {
    	String  sDir =null;
    	File fileName=null;
    	boolean fileext=false;
        try {
        	  sDir = ToolClass.getEV_DIR()+File.separator+"productImage";
        	  File dirName = new File(sDir);
        	 //如果目录不存在，则创建目录
        	 if (!dirName.exists()) 
        	 {  
                //按照指定的路径创建文件夹  
        		dirName.mkdirs(); 
             }
        	 
        	 fileName=new File(sDir+File.separator+filename+".jpg");         	
        	//如果不存在，则创建文件
        	if(!fileName.exists())
        	{  
        		fileext=false; 
    	    }  
        	else
        		fileext=true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return fileext; 
    }
            
    //将Bitmap图片保存在本地
    public static boolean  saveBitmaptofile(Bitmap bmp,String filename)
    {      	
    	String  sDir =null;
    	File fileName=null;
    	boolean fileext=false;
        try {
        	  sDir = ToolClass.getEV_DIR()+File.separator+"productImage";
        	  File dirName = new File(sDir);
        	 //如果目录不存在，则创建目录
        	 if (!dirName.exists()) 
        	 {  
                //按照指定的路径创建文件夹  
        		dirName.mkdirs(); 
             }
        	 
        	 fileName=new File(sDir+File.separator+filename+".jpg");         	
        	//如果不存在，则开始保存图片
        	if(!fileName.exists())
        	{  
        		//1.保存原版图片
        		CompressFormat format= Bitmap.CompressFormat.JPEG;  
    	        int quality = 100;  
    	        OutputStream stream = null;  
    	        stream = new FileOutputStream(fileName);     	        
    	        fileext=bmp.compress(format, quality, stream); 
    	        //2.压缩裁剪图片
 	    	   //如果我们把它设为true，那么BitmapFactory.decodeFile(String path, Options opt)并不会真的返回一个Bitmap给你，
 	    	   //它仅仅会把它的宽，高取回来给你，这样就不会占用太多的内存
                BitmapFactory.Options options = new BitmapFactory.Options();
                options.inJustDecodeBounds = true;
                //这段代码之后，options.outWidth 和 options.outHeight就是我们想要的宽和高了
                Bitmap bmptmp = BitmapFactory.decodeFile(fileName.toString(), options);
                //按比例收缩和压缩他的值，这样可以减低内存使用
                // 缩放的比例，缩放是很难按准备的比例进行缩放的，其值表明缩放的倍数，
                //SDK中建议其值是2的指数值,值越大会导致图片不清晰 
                int inSampleSize = options.outWidth / 350;
                options.inSampleSize = inSampleSize; 
                //在图片不变形的情况下获取到图片指定大小的缩略图呢
                //那么我们需要先计算一下缩放之后，图片的高度是多少,就能显示这么大的长和宽的图片
                int height = options.outHeight * 350 / options.outWidth;
                options.outWidth = 350;
                options.outHeight = height;              
                //为了节约内存我们还可以使用下面的几个字段
                options.inPreferredConfig = Bitmap.Config.ARGB_4444;// 默认是Bitmap.Config.ARGB_8888
                /* 下面两个字段需要组合使用 */
                options.inPurgeable = true;
                options.inInputShareable = true;
                /* 这样才能真正的返回一个Bitmap给你 */
                options.inJustDecodeBounds = false;
                Bitmap bmp2 = BitmapFactory.decodeFile(fileName.toString(), options);
                //3.保存裁剪版图片
        		stream = new FileOutputStream(fileName);     	        
    	        fileext=bmp2.compress(format, quality, stream); 
    	        stream.close();
    	    }  
        	else
        		fileext=false;
        } catch (Exception e) {
            e.printStackTrace();
        }   
       return fileext; 
     } 
    
    /**
     * 使用getImgFile,得到这个商品图片的完整目录
     */
    public static String getImgFile(String filename) 
    {
    	String  sDir =null;
    	String fileName=null;
    	try {
    		  sDir = ToolClass.getEV_DIR()+File.separator+"productImage";
        	  File dirName = new File(sDir);
        	 //如果目录不存在，则创建目录
        	 if (!dirName.exists()) 
        	 {  
                //按照指定的路径创建文件夹  
        		dirName.mkdirs(); 
             }
        	 
        	fileName=sDir+File.separator+filename+".jpg";  
        } catch (Exception e) {
            e.printStackTrace();
        }
        return fileName; 
    }
    
    
    
    //将Bitmap图片保存在本地
    public static boolean  saveBitmaptoads(Bitmap bmp,String filename,String ads)
    {      	
    	String  sDir =null;
    	File fileName=null;
    	boolean fileext=false;
        try {
        	  sDir = ToolClass.getEV_DIR()+File.separator+ads;
        	  File dirName = new File(sDir);
        	 //如果目录不存在，则创建目录
        	 if (!dirName.exists()) 
        	 {  
                //按照指定的路径创建文件夹  
        		dirName.mkdirs(); 
             }
        	 
        	 fileName=new File(sDir+File.separator+filename+".jpg");         	
        	//如果不存在，则开始保存图片
        	if(!fileName.exists())
        	{  
        		CompressFormat format= Bitmap.CompressFormat.JPEG;  
    	        int quality = 100;  
    	        OutputStream stream = null;  
    	        stream = new FileOutputStream(fileName);      	         
    	        fileext=bmp.compress(format, quality, stream); 
    	    }  
        	else
        		fileext=false;
        } catch (Exception e) {
            e.printStackTrace();
        }   
       return fileext; 
     } 
    
    /**
     * 使用saveAvitoads,保存这个视频广告到目录中
     */
    public static File saveAvitoads(String filename,String TypeStr,String ads) 
    {
    	String  sDir =null;
    	File fileName=null;
    	boolean fileext=false;
        try {
        	sDir = ToolClass.getEV_DIR()+File.separator+ads;
        	  File dirName = new File(sDir);
        	 //如果目录不存在，则创建目录
        	 if (!dirName.exists()) 
        	 {  
                //按照指定的路径创建文件夹  
        		dirName.mkdirs(); 
             }
        	 
        	 fileName=new File(sDir+File.separator+filename+"."+TypeStr);         	
        	
        } catch (Exception e) {
            e.printStackTrace();
        }
        return fileName; 
    }
    
    //将广告文件删除
    public static boolean  delAds(String filename,String TypeStr,String ads)
    {      	
    	String  sDir =null;
    	File fileName=null;
    	boolean fileext=false;
        try {
        	  sDir = ToolClass.getEV_DIR()+File.separator+ads;
        	  File dirName = new File(sDir);
        	 //如果目录不存在，则创建目录
        	 if (!dirName.exists()) 
        	 {  
                //按照指定的路径创建文件夹  
        		dirName.mkdirs(); 
             }
        	 
        	 fileName=new File(sDir+File.separator+filename+"."+TypeStr);   
        	 //如果存在，删除
        	 if(fileName.exists())
        	{  
        		 fileName.delete();	
    	    } 
        } catch (Exception e) {
            e.printStackTrace();
        }   
       return fileext; 
     }
    
    /**
     * 读取日志文件
     */
    public static String ReadLogFile() 
    {
    	String  sDir =null;
    	sDir = ToolClass.getEV_DIR()+File.separator+"logs"+File.separator;
    	return sDir;
    }
    
    
    
    
    
    
	
	/**
	 * 根据这个Uri获得其在文件系统中的路径
	 *
	 * @param context
	 * @param uri
	 * @return the file path or null
	 */
	public static String getRealFilePath( final Context context, final Uri uri )
	{
	    if ( null == uri ) 
	    	return "";
	    final String scheme = uri.getScheme();
	    String data = null;
	    if ( scheme == null )
	        data = uri.getPath();
	    else if ( ContentResolver.SCHEME_FILE.equals( scheme ) ) {
	        data = uri.getPath();
	    } else if ( ContentResolver.SCHEME_CONTENT.equals( scheme ) ) {
	        Cursor cursor = context.getContentResolver().query( uri, new String[] { ImageColumns.DATA }, null, null, null );
	        if ( null != cursor ) {
	            if ( cursor.moveToFirst() ) {
	                int index = cursor.getColumnIndex( ImageColumns.DATA );
	                if ( index > -1 ) {
	                    data = cursor.getString( index );
	                }
	            }
	            cursor.close();
	        }
	    }
	    return data;
	}
	
	/**
     * 加载本地图片
     * @param url
     * @return
     */
	public static Bitmap getLoacalBitmap(String url) {
        try {
             FileInputStream fis = new FileInputStream(url);
             return BitmapFactory.decodeStream(fis);  ///把流转化为Bitmap图片        

          } catch (FileNotFoundException e) {
             e.printStackTrace();
             return null;
        }
   }
     
    
	
	/**
	 * 格式化时间
	 * @Title:getLastDayOfMonth
	 * @Description:
	 * @param:@param year
	 * @param:@param month
	 * @param:@param type=0月初,1月中旬,2月下旬
	 * @param:@return
	 * @return:String
	 * @throws
	 */
	public static String getDayOfMonth(int year,int month,int day)
	{
		Calendar cal = Calendar.getInstance();
		//设置年份
		cal.set(Calendar.YEAR,year);
		//设置月份
		cal.set(Calendar.MONTH, month-1);
		//设置日
		cal.set(Calendar.DATE, day);
		
		//格式化日期
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String lastDayOfMonth = sdf.format(cal.getTime());
		
		return lastDayOfMonth;
	}
	/**
	 * 获取某月的最后一天或者中旬
	 * @Title:getLastDayOfMonth
	 * @Description:
	 * @param:@param year
	 * @param:@param month
	 * @param:@param type=0月初,1月中旬,2月下旬
	 * @param:@return
	 * @return:String
	 * @throws
	 */
	public static String getLastDayOfMonth(int year,int month,int type)
	{
		Calendar cal = Calendar.getInstance();
		//设置年份
		cal.set(Calendar.YEAR,year);
		//设置月份
		cal.set(Calendar.MONTH, month-1);
		if(type==0)
		{
			//设置日历中月份的中旬
			cal.set(Calendar.DAY_OF_MONTH, 1);
		}
		else if(type==1)
		{
			//设置日历中月份的中旬
			cal.set(Calendar.DAY_OF_MONTH, 15);
		}
		else if(type==2)
		{
			//获取某月最大天数
			int lastDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
			//设置日历中月份的最大天数
			cal.set(Calendar.DAY_OF_MONTH, lastDay);
		}
		//格式化日期
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String lastDayOfMonth = sdf.format(cal.getTime());
		
		return lastDayOfMonth;
	}
	//是否在时间区间中,s是需要比较的时间,begin,end是时间区间
	//是返回true
	public static boolean isdatein(String begin,String end,String s)
	{
		//ToolClass.Log(ToolClass.INFO,"EV_JNI","APP<<"+s+"在"+begin+"="+dateCompare(s,begin));
		//ToolClass.Log(ToolClass.INFO,"EV_JNI","APP<<"+s+"在"+end+"="+dateCompare(end,s));
		if((dateCompare(s,begin)>=0)&&(dateCompare(end,s)>=0))
		{
			return true;
		}
		return false;
	}
	//时间比较,返回值result==0s1相等s2,result<0s1小于s2,result:>0s1大于s2,
	public static int dateCompare(String s1,String s2)
	{
		//ToolClass.Log(ToolClass.INFO,"EV_JNI","APP<<"+s1);
		//ToolClass.Log(ToolClass.INFO,"EV_JNI","APP<<"+s2);
		SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		java.util.Calendar c1=java.util.Calendar.getInstance();
		java.util.Calendar c2=java.util.Calendar.getInstance();
		try
		{
			c1.setTime(df.parse(s1));
			c2.setTime(df.parse(s2));
		}catch(java.text.ParseException e){
			System.err.println("格式不正确");
		}
		return c1.compareTo(c2);
	}
	
	//得到hopper设备的当前状态
	public static String gethopperstats(int hopper)
	{
		String res=null;
		//"hopper":8个hopper的状态,0正常,1缺币,2故障,3通讯故障
		if(hopper==0)
		{
			res="正常";
		}
		else if(hopper==1)
		{
			res="缺币";
		}
		else if(hopper==2)
		{
			res="故障";
		}
		else if(hopper==3)
		{
			res="通讯故障";
		}
		return res;
	}
	
	//为上传给server使用，将当前时间转换为给server上报的时间
	public static String getLasttime()
	{
		SimpleDateFormat tempDate = new SimpleDateFormat("yyyy-MM-dd"); //精确到毫秒 
		SimpleDateFormat tempTime = new SimpleDateFormat("HH:mm:ss"); //精确到毫秒 
        String datetime = tempDate.format(new java.util.Date()).toString()+"T"
        		+tempTime.format(new java.util.Date()).toString(); 
		return datetime;
	}
	
	//为上传给server使用，传入一个时间，转换为给server上报的时间
	public static String getStrtime(String orderTime)
	{
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date =null;
		try {
			date = df.parse(orderTime);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		SimpleDateFormat tempDate = new SimpleDateFormat("yyyy-MM-dd"); //精确到毫秒 
		SimpleDateFormat tempTime = new SimpleDateFormat("HH:mm:ss"); //精确到毫秒 
        String datetime = tempDate.format(date).toString()+"T"
        		+tempTime.format(date).toString(); 
		return datetime;
	}
	
	//获取设备状态并上传给服务器
	//dev设备：1纸币器,2硬币器,3hopper1,4hopper2,5hopper3,6hopper4,7hopper5,8hopper6,9hopper7,10hopper8
	//返回：2故障，0正常
	public static int getvmcStatus(Map<String, Object> Set,int dev)
	{
		//ToolClass.Log(ToolClass.INFO,"EV_JNI","APP<<现金设备状态="+rst,"log.txt");	
		int rst=0;
		switch (dev) 
		{
			//纸币器
			case 1:
				int bill_err=(Integer)Set.get("bill_err");
				int bill_enable=(Integer)Set.get("bill_enable");
				if(bill_err>0)
				{
					rst=2;
				}
				else
				{
					rst=0;
				}
				break;
			//硬币器	
			case 2:
				int coin_err=(Integer)Set.get("coin_err");
				int coin_enable=(Integer)Set.get("coin_enable");
				if(coin_err>0)
				{
					rst=2;
				}
				else
				{
					rst=0;
				}
				break;
			//hopper1	
			case 3:
				int hopper1=(Integer)Set.get("hopper1");
				if((hopper1==3)||(hopper1==2))
				{
					rst=2;
				}
				else if(hopper1==1)
				{
					rst=1;
				}
				else
				{
					rst=0;
				}
				break;
			//hopper2	
			case 4:
				int hopper2=(Integer)Set.get("hopper2");
				if((hopper2==3)||(hopper2==2))
				{
					rst=2;
				}
				else if(hopper2==1)
				{
					rst=1;
				}
				else
				{
					rst=0;
				}
				break;
			//hopper3	
			case 5:
				int hopper3=(Integer)Set.get("hopper3");
				if((hopper3==3)||(hopper3==2))
				{
					rst=2;
				}
				else if(hopper3==1)
				{
					rst=1;
				}
				else
				{
					rst=0;
				}
				break;
			//hopper4		
			case 6:
				int hopper4=(Integer)Set.get("hopper4");
				if((hopper4==3)||(hopper4==2))
				{
					rst=2;
				}
				else if(hopper4==1)
				{
					rst=1;
				}
				else
				{
					rst=0;
				}
				break;
			//hopper5	
			case 7:
				int hopper5=(Integer)Set.get("hopper5");
				if((hopper5==3)||(hopper5==2))
				{
					rst=2;
				}
				else if(hopper5==1)
				{
					rst=1;
				}
				else
				{
					rst=0;
				}
				break;
			//hopper6
			case 8:
				int hopper6=(Integer)Set.get("hopper6");
				if((hopper6==3)||(hopper6==2))
				{
					rst=2;
				}
				else if(hopper6==1)
				{
					rst=1;
				}
				else
				{
					rst=0;
				}
				break;
			//hopper7
			case 9:
				int hopper7=(Integer)Set.get("hopper7");
				if((hopper7==3)||(hopper7==2))
				{
					rst=2;
				}
				else if(hopper7==1)
				{
					rst=1;
				}
				else
				{
					rst=0;
				}
				break;
			//hopper8
			case 10:
				int hopper8=(Integer)Set.get("hopper8");
				if((hopper8==3)||(hopper8==2))
				{
					rst=2;
				}
				else if(hopper8==1)
				{
					rst=1;
				}
				else
				{
					rst=0;
				}
				break;	
			default:
				break;
		}		
		return rst;
	}
	
	public static int getBill_err() {
		return bill_err;
	}

	public static void setBill_err(int bill_err) {
		ToolClass.bill_err = bill_err;
	}

	public static int getCoin_err() {
		return coin_err;
	}

	public static void setCoin_err(int coin_err) {
		ToolClass.coin_err = coin_err;
	}
	
	
	
	//判断字符串是否为空:true空，false非空
	public static boolean isEmptynull(String str)
	{
		boolean result=true;
		if(str!=null)
		{
			if((str.isEmpty()==false)&&(str.equals("")==false))
			{
				result=false;
			}
		}
		return result;
	}
	
	//检测Service是否已启动
	 public static boolean isServiceRunning(String serviceClassName)
	 { 
        final ActivityManager activityManager = (ActivityManager)context.getSystemService(Context.ACTIVITY_SERVICE); 
        final List<RunningServiceInfo> services = activityManager.getRunningServices(Integer.MAX_VALUE); 

        for (RunningServiceInfo runningServiceInfo : services) 
        { 
        	if (runningServiceInfo.service.getClassName().equals(serviceClassName))
            { 
                return true; 
            } 
        } 
        return false; 
	 }
	
}
