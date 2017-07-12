/****************************************Copyright (c)*************************************************
**                      Fujian Junpeng Communicaiton Technology Co.,Ltd.
**                               http://www.easivend.com.cn
**--------------File Info------------------------------------------------------------------------------
** File name:           ToolClass.java
** Last modified Date:  2015-01-10
** Last Version:         
** Descriptions:        �����࣬�������ŵ���Ҫ��static������static��Ա��ͳһ��Ϊȫ�ֱ�����ȫ�ֺ�����       
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
	public static String EV_DIR=null;//ev���ĵ�ַ
	private static int bentcom_id=-1,com_id=-1,columncom_id=-1,extracom_id=-1;//����id��
	private static String bentcom="",com="",columncom="",extracom="";//����������
	private static int bill_err=0,coin_err=0;//ֽ������Ӳ��������״̬
	public static String vmc_no="";//�������
	public static Bitmap mark=null;//����ͼƬ
	public static int goc=0;//�Ƿ�ʹ�ó���ȷ�ϰ�1��
	public static int extraComType=0;//1ʹ�ñ�ɽ���ͣ�2ʹ��չʾλ
	public static Map<Integer, Integer> huodaolist=null;//�����߼���������������Ķ�Ӧ��ϵ
	public static Map<Integer, Integer> elevatorlist=null;//�����������߼���������������Ķ�Ӧ��ϵ
	public static Map<String, String> selectlist=null;//����ѡ������id����Ʒid�Ķ�Ӧ��ϵ
	public static int orientation=0;//ʹ�ú�����������ģʽ
	public static SSLSocketFactory ssl=null;//ssl�������
	public static Context context=null;//��Ӧ��context
	private static int ServerVer=1;//0�ɵĺ�̨��1һ�ڵĺ�̨
	public static String version="";//�����汾��
	public static boolean CLIENT_STATUS_SERVICE=true;//true��������ʹ��,false������ͣ���� 
	
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
     * ���ø�Ŀ¼�ļ�
     */
    public static void SetDir() 
    {
    	final String SDCARD_DIR=File.separator+"sdcard"+File.separator+"ev";
    	final String NOSDCARD_DIR=File.separator+"ev";
    	File fileName=null;
    	String  sDir =null,str=null;
    	Map<String, String> list=null;
    	    	
        try {
        	  //�����ж�sdcard�Ƿ����
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
	
	//Log���������ڴ�ӡlog�������ı��ļ��д�ӡ������־
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
     * ׷���ļ���ʹ��FileWriter
     */
    public static void AppendLogFile(String content,String filename) 
    {
    	String  sDir =null;
    	File fileName=null;
    	SimpleDateFormat tempDate = new SimpleDateFormat("yyyy-MM-dd" + " "  
                + "HH:mm:ss:SSS"); //��ȷ������ 
        String datetime = tempDate.format(new java.util.Date()).toString();  
        String cont=datetime+content+"\r\n";
    	
        try {
        	 sDir = ToolClass.getEV_DIR()+File.separator+"logs";
        	
        	  File dirName = new File(sDir);
        	 //���Ŀ¼�����ڣ��򴴽�Ŀ¼
        	 if (!dirName.exists()) 
        	 {  
                //����ָ����·�������ļ���  
        		dirName.mkdirs(); 
             }
        	 
        	 fileName=new File(sDir+File.separator+filename);         	
        	//��������ڣ��򴴽��ļ�
        	if(!fileName.exists())
        	{  
    	      fileName.createNewFile(); 
    	    }  
            //��һ��д�ļ��������캯���еĵڶ�������true��ʾ��׷����ʽд�ļ�
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
    
    	
	//��ȡ�ļ�����ʱ��
    public static String getFileCreated(final File file)  
    {  
		 String res=null;
		 Scanner scan = null ;
		 try{
		 	scan = new Scanner(file) ;	// ���ļ���������
		 	if(scan.hasNext())
		 	{
		 		res=scan.next()+" "+scan.next();	//	ȡ����		 		
		 	}
		 	
		 }catch(Exception e){}
		 res=res.substring(0, res.indexOf("(INFO)"));// ��������Ϣ�н�ȡ������
		 System.out.println(" �ļ�����ʱ��1="+res);
         return res;
    }
	
	 
	 
	//�������ļ���fileNameԭ�ļ���,sDir��Ŀ¼
    public static void updatefile(File fileName,String  sDir)
	{
		SimpleDateFormat tempDate2 = new SimpleDateFormat("yyyy-MM-dd-HHmmss"); //��ȷ������ 
        String datetime2 = tempDate2.format(new java.util.Date()).toString();
        String oldname=fileName.getName();
		String newname=datetime2+oldname;
		System.out.println(fileName+" �޸��ļ�����="+newname);            		
		fileName.renameTo(new File(sDir+File.separator+newname));
	}
    
    //java�趨һ������ʱ�䣬�Ӽ����ӣ�Сʱ�����죩��õ��µ�����
    //���ص����ַ����͵�ʱ�䣬
    //�������String day��׼ʱ��, int x����
    public static String addDateMinut(String day, int x)
    {   
    	// 24Сʱ��  
    	//�����������ʽҲ������ HH:mm:ss����HH:mm�ȵȣ�������ģ�����������������ʱ��Ҫ������ı�
    	//��day��ʽһ��
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = null;   
        try {   
            date = format.parse(day);   
        } catch (Exception ex) {   
            ex.printStackTrace();   
        }   
        if (date == null)   
            return "";   
        System.out.println("front:" + format.format(date)); //��ʾ���������  
        Calendar cal = Calendar.getInstance();   
        cal.setTime(date);   //�õ���׼ʱ��
        
        cal.add(Calendar.DATE, x);// �� 
        date = cal.getTime();   
        System.out.println("after:" + format.format(date));  //��ʾ���º������ 
        cal = null;   
        return format.format(date);   
  
    } 
    /**
     * �ݹ�ɾ��productImage�ļ����ļ���
     * @param file    Ҫɾ���ĸ�Ŀ¼
     */
    public static void deleteproductImageFile()
    {
    	String  sDir =null;
    	 try {
    		  sDir = ToolClass.getEV_DIR()+File.separator+"productImage";
        	  File dirName = new File(sDir);
        	 //���Ŀ¼�����ڣ��򴴽�Ŀ¼
        	 if (!dirName.exists()) 
        	 {  
                //����ָ����·�������ļ���  
        		dirName.mkdirs(); 
             }
        	 
        	 deleteAllZIPFile(dirName);         	
        	
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
     * �ݹ�ɾ��ads,adshuo�ļ����ļ���
     * @param file    Ҫɾ���ĸ�Ŀ¼
     */
    public static void deleteadsImageFile()
    {
    	String  sDir =null;
    	 try {
    		 //ɾ�����Ŀ¼
    		  sDir = ToolClass.getEV_DIR()+File.separator+"ads";
        	  File dirName = new File(sDir);
        	 //���Ŀ¼�����ڣ��򴴽�Ŀ¼
        	 if (!dirName.exists()) 
        	 {  
                //����ָ����·�������ļ���  
        		dirName.mkdirs(); 
             }
        	 
        	 deleteAllZIPFile(dirName);
        	 
        	//ɾ���������Ŀ¼
	   		sDir = ToolClass.getEV_DIR()+File.separator+"adshuo";
	       	dirName = new File(sDir);
	       	//���Ŀ¼�����ڣ��򴴽�Ŀ¼
	       	if (!dirName.exists()) 
	       	{  
	            //����ָ����·�������ļ���  
	       		dirName.mkdirs(); 
	        }
	       	 
	       	deleteAllZIPFile(dirName);
        	
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
     * �ݹ�ɾ��ZIP�ļ����ļ���
     * @param file    Ҫɾ���ĸ�Ŀ¼
     */
    private static void deleteZIPFile()
    {
    	String  sDir =null;
    	 try {
        	  sDir = ToolClass.ReadLogFile()+"ZIPFile";
        	  File dirName = new File(sDir);
        	 //���Ŀ¼�����ڣ��򴴽�Ŀ¼
        	 if (!dirName.exists()) 
        	 {  
                //����ָ����·�������ļ���  
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
     * ���Ƶ����ļ�  
     * @param oldPath String ԭ�ļ�·�� �磺c:/fqf.txt  
     * @param newPath String ���ƺ�·�� �磺f:/fqf.txt  
     * @return boolean  
     */   
	   private static void copyFile(String oldPath, String newPath) 
	   {   
	       try {   
	           int bytesum = 0;   
	           int byteread = 0;   
	           File oldfile = new File(oldPath);   
	           if (oldfile.exists()) { //�ļ�����ʱ   
	               InputStream inStream = new FileInputStream(oldPath); //����ԭ�ļ�   
	               FileOutputStream fs = new FileOutputStream(newPath);   
	               byte[] buffer = new byte[1444];   
	               int length;   
	               while ( (byteread = inStream.read(buffer)) != -1) {   
	                   bytesum += byteread; //�ֽ��� �ļ���С   
	                   System.out.println(bytesum);   
	                   fs.write(buffer, 0, byteread);   
	               }   
	               inStream.close();   
	           }   
	       }   
	       catch (Exception e) {   
	           System.out.println("���Ƶ����ļ���������");   
	           e.printStackTrace();   
	  
	       }   
	  
	   }  
	    
    
	
	 /* ����Ŀ¼���ļ��б� file��Ŀ¼����datetime�ǵ�ǰʱ�䣬�����������£���ɾ��������ļ�
	  * */  
    public static void delFiles(File file) 
    {  
    	//1.������ʼʱ��ͽ���ʱ��
    	SimpleDateFormat tempDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
    	
    	//������ʼʱ��
        Calendar todayStart = Calendar.getInstance(); 
        todayStart.setFirstDayOfWeek(Calendar.MONDAY);  
        todayStart.set(Calendar.HOUR_OF_DAY, 0);  
        todayStart.set(Calendar.MINUTE, 0);  
        todayStart.set(Calendar.SECOND, 0);  
        todayStart.set(Calendar.MILLISECOND, 0); 
        todayStart.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY); 
        //����ǰ����
        todayStart.add(Calendar.WEEK_OF_YEAR, -2);
        Date date = todayStart.getTime(); 
        String starttime=tempDate.format(date);
        ParsePosition posstart = new ParsePosition(0);  
    	Date dstart = (Date) tempDate.parse(starttime, posstart);
    	
    	//��������ļ�����������ļ�
		File[] files = file.listFiles();
		if (files.length > 0) 
		{  
			for (int i = 0; i < files.length; i++) 
			{
			  if(!files[i].isDirectory())
			  {		
				    //3.��������ļ������ж�
		        	File fileName=new File(files[i].toString()); 
		        	if(fileName.exists())
		        	{  
		        		String logdatetime = getFileCreated(fileName);
		        		ParsePosition poslog = new ParsePosition(0);  
		        		Date dlog = (Date) tempDate.parse(logdatetime, poslog);
		        		ToolClass.Log(ToolClass.INFO,"EV_DOG","�ж���־Ŀ¼���ļ�="+files[i].toString()+"ʱ��="+logdatetime+",="+dlog.getTime(),"dogservice.txt");
		        		//�ж��Ƿ��ļ����ڱ���
		        		if(dlog.getTime()<=dstart.getTime())
		            	{
		        			ToolClass.Log(ToolClass.INFO,"EV_DOG","�ļ�="+files[i].toString()+"ɾ��","dogservice.txt");
		            		fileName.delete();		            		
		            	}
		        		else
		        		{
		        			ToolClass.Log(ToolClass.INFO,"EV_DOG","�ļ�="+files[i].toString()+"�ų�","dogservice.txt");
		        		}
		    	    } 
			  }
			}
		}    
    }
    
    /**
     * ʹ��isAPKFile,�ж�����������Ѿ�����Ŀ¼��,true����,false������
     */
    public static boolean isAPKFile(String filename) 
    {
    	String  sDir =null;
    	File fileName=null;
    	boolean fileext=false;
        try {
        	sDir = ToolClass.getEV_DIR()+File.separator+"APKFile";
        	  File dirName = new File(sDir);
        	 //���Ŀ¼�����ڣ��򴴽�Ŀ¼
        	 if (!dirName.exists()) 
        	 {  
                //����ָ����·�������ļ���  
        		dirName.mkdirs(); 
             }
        	 
        	 fileName=new File(sDir+File.separator+filename);         	
        	//��������ڣ��򴴽��ļ�
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
     * ʹ��isAPKFile,�����������Ŀ¼��
     */
    public static File setAPKFile(String filename) 
    {
    	String  sDir =null;
    	File fileName=null;
    	boolean fileext=false;
        try {
        	sDir = ToolClass.getEV_DIR()+File.separator+"APKFile";
        	  File dirName = new File(sDir);
        	 //���Ŀ¼�����ڣ��򴴽�Ŀ¼
        	 if (!dirName.exists()) 
        	 {  
                //����ָ����·�������ļ���  
        		dirName.mkdirs(); 
             }
        	 
        	 fileName=new File(sDir+File.separator+filename);         	
        	
        } catch (Exception e) {
            e.printStackTrace();
        }
        return fileName; 
    }
    /**
     * �ݹ�ɾ��APK�ļ����ļ���
     * @param file    Ҫɾ���ĸ�Ŀ¼
     */
    public static void deleteAPKFile()
    {
    	String  sDir =null;
    	 try {
        	sDir = ToolClass.getEV_DIR()+File.separator+"APKFile";
        	  File dirName = new File(sDir);
        	 //���Ŀ¼�����ڣ��򴴽�Ŀ¼
        	 if (!dirName.exists()) 
        	 {  
                //����ָ����·�������ļ���  
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
     * ʹ��isImgFile,�ж������ƷͼƬ���Ѿ�����Ŀ¼��,true����,false������
     */
    public static boolean isImgFile(String filename) 
    {
    	String  sDir =null;
    	File fileName=null;
    	boolean fileext=false;
        try {
        	  sDir = ToolClass.getEV_DIR()+File.separator+"productImage";
        	  File dirName = new File(sDir);
        	 //���Ŀ¼�����ڣ��򴴽�Ŀ¼
        	 if (!dirName.exists()) 
        	 {  
                //����ָ����·�������ļ���  
        		dirName.mkdirs(); 
             }
        	 
        	 fileName=new File(sDir+File.separator+filename+".jpg");         	
        	//��������ڣ��򴴽��ļ�
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
            
    //��BitmapͼƬ�����ڱ���
    public static boolean  saveBitmaptofile(Bitmap bmp,String filename)
    {      	
    	String  sDir =null;
    	File fileName=null;
    	boolean fileext=false;
        try {
        	  sDir = ToolClass.getEV_DIR()+File.separator+"productImage";
        	  File dirName = new File(sDir);
        	 //���Ŀ¼�����ڣ��򴴽�Ŀ¼
        	 if (!dirName.exists()) 
        	 {  
                //����ָ����·�������ļ���  
        		dirName.mkdirs(); 
             }
        	 
        	 fileName=new File(sDir+File.separator+filename+".jpg");         	
        	//��������ڣ���ʼ����ͼƬ
        	if(!fileName.exists())
        	{  
        		//1.����ԭ��ͼƬ
        		CompressFormat format= Bitmap.CompressFormat.JPEG;  
    	        int quality = 100;  
    	        OutputStream stream = null;  
    	        stream = new FileOutputStream(fileName);     	        
    	        fileext=bmp.compress(format, quality, stream); 
    	        //2.ѹ���ü�ͼƬ
 	    	   //������ǰ�����Ϊtrue����ôBitmapFactory.decodeFile(String path, Options opt)��������ķ���һ��Bitmap���㣬
 	    	   //������������Ŀ���ȡ�������㣬�����Ͳ���ռ��̫����ڴ�
                BitmapFactory.Options options = new BitmapFactory.Options();
                options.inJustDecodeBounds = true;
                //��δ���֮��options.outWidth �� options.outHeight����������Ҫ�Ŀ�͸���
                Bitmap bmptmp = BitmapFactory.decodeFile(fileName.toString(), options);
                //������������ѹ������ֵ���������Լ����ڴ�ʹ��
                // ���ŵı����������Ǻ��Ѱ�׼���ı����������ŵģ���ֵ�������ŵı�����
                //SDK�н�����ֵ��2��ָ��ֵ,ֵԽ��ᵼ��ͼƬ������ 
                int inSampleSize = options.outWidth / 350;
                options.inSampleSize = inSampleSize; 
                //��ͼƬ�����ε�����»�ȡ��ͼƬָ����С������ͼ��
                //��ô������Ҫ�ȼ���һ������֮��ͼƬ�ĸ߶��Ƕ���,������ʾ��ô��ĳ��Ϳ��ͼƬ
                int height = options.outHeight * 350 / options.outWidth;
                options.outWidth = 350;
                options.outHeight = height;              
                //Ϊ�˽�Լ�ڴ����ǻ�����ʹ������ļ����ֶ�
                options.inPreferredConfig = Bitmap.Config.ARGB_4444;// Ĭ����Bitmap.Config.ARGB_8888
                /* ���������ֶ���Ҫ���ʹ�� */
                options.inPurgeable = true;
                options.inInputShareable = true;
                /* �������������ķ���һ��Bitmap���� */
                options.inJustDecodeBounds = false;
                Bitmap bmp2 = BitmapFactory.decodeFile(fileName.toString(), options);
                //3.����ü���ͼƬ
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
     * ʹ��getImgFile,�õ������ƷͼƬ������Ŀ¼
     */
    public static String getImgFile(String filename) 
    {
    	String  sDir =null;
    	String fileName=null;
    	try {
    		  sDir = ToolClass.getEV_DIR()+File.separator+"productImage";
        	  File dirName = new File(sDir);
        	 //���Ŀ¼�����ڣ��򴴽�Ŀ¼
        	 if (!dirName.exists()) 
        	 {  
                //����ָ����·�������ļ���  
        		dirName.mkdirs(); 
             }
        	 
        	fileName=sDir+File.separator+filename+".jpg";  
        } catch (Exception e) {
            e.printStackTrace();
        }
        return fileName; 
    }
    
    
    
    //��BitmapͼƬ�����ڱ���
    public static boolean  saveBitmaptoads(Bitmap bmp,String filename,String ads)
    {      	
    	String  sDir =null;
    	File fileName=null;
    	boolean fileext=false;
        try {
        	  sDir = ToolClass.getEV_DIR()+File.separator+ads;
        	  File dirName = new File(sDir);
        	 //���Ŀ¼�����ڣ��򴴽�Ŀ¼
        	 if (!dirName.exists()) 
        	 {  
                //����ָ����·�������ļ���  
        		dirName.mkdirs(); 
             }
        	 
        	 fileName=new File(sDir+File.separator+filename+".jpg");         	
        	//��������ڣ���ʼ����ͼƬ
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
     * ʹ��saveAvitoads,���������Ƶ��浽Ŀ¼��
     */
    public static File saveAvitoads(String filename,String TypeStr,String ads) 
    {
    	String  sDir =null;
    	File fileName=null;
    	boolean fileext=false;
        try {
        	sDir = ToolClass.getEV_DIR()+File.separator+ads;
        	  File dirName = new File(sDir);
        	 //���Ŀ¼�����ڣ��򴴽�Ŀ¼
        	 if (!dirName.exists()) 
        	 {  
                //����ָ����·�������ļ���  
        		dirName.mkdirs(); 
             }
        	 
        	 fileName=new File(sDir+File.separator+filename+"."+TypeStr);         	
        	
        } catch (Exception e) {
            e.printStackTrace();
        }
        return fileName; 
    }
    
    //������ļ�ɾ��
    public static boolean  delAds(String filename,String TypeStr,String ads)
    {      	
    	String  sDir =null;
    	File fileName=null;
    	boolean fileext=false;
        try {
        	  sDir = ToolClass.getEV_DIR()+File.separator+ads;
        	  File dirName = new File(sDir);
        	 //���Ŀ¼�����ڣ��򴴽�Ŀ¼
        	 if (!dirName.exists()) 
        	 {  
                //����ָ����·�������ļ���  
        		dirName.mkdirs(); 
             }
        	 
        	 fileName=new File(sDir+File.separator+filename+"."+TypeStr);   
        	 //������ڣ�ɾ��
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
     * ��ȡ��־�ļ�
     */
    public static String ReadLogFile() 
    {
    	String  sDir =null;
    	sDir = ToolClass.getEV_DIR()+File.separator+"logs"+File.separator;
    	return sDir;
    }
    
    
    
    
    
    
	
	/**
	 * �������Uri��������ļ�ϵͳ�е�·��
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
     * ���ر���ͼƬ
     * @param url
     * @return
     */
	public static Bitmap getLoacalBitmap(String url) {
        try {
             FileInputStream fis = new FileInputStream(url);
             return BitmapFactory.decodeStream(fis);  ///����ת��ΪBitmapͼƬ        

          } catch (FileNotFoundException e) {
             e.printStackTrace();
             return null;
        }
   }
     
    
	
	/**
	 * ��ʽ��ʱ��
	 * @Title:getLastDayOfMonth
	 * @Description:
	 * @param:@param year
	 * @param:@param month
	 * @param:@param type=0�³�,1����Ѯ,2����Ѯ
	 * @param:@return
	 * @return:String
	 * @throws
	 */
	public static String getDayOfMonth(int year,int month,int day)
	{
		Calendar cal = Calendar.getInstance();
		//�������
		cal.set(Calendar.YEAR,year);
		//�����·�
		cal.set(Calendar.MONTH, month-1);
		//������
		cal.set(Calendar.DATE, day);
		
		//��ʽ������
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String lastDayOfMonth = sdf.format(cal.getTime());
		
		return lastDayOfMonth;
	}
	/**
	 * ��ȡĳ�µ����һ�������Ѯ
	 * @Title:getLastDayOfMonth
	 * @Description:
	 * @param:@param year
	 * @param:@param month
	 * @param:@param type=0�³�,1����Ѯ,2����Ѯ
	 * @param:@return
	 * @return:String
	 * @throws
	 */
	public static String getLastDayOfMonth(int year,int month,int type)
	{
		Calendar cal = Calendar.getInstance();
		//�������
		cal.set(Calendar.YEAR,year);
		//�����·�
		cal.set(Calendar.MONTH, month-1);
		if(type==0)
		{
			//�����������·ݵ���Ѯ
			cal.set(Calendar.DAY_OF_MONTH, 1);
		}
		else if(type==1)
		{
			//�����������·ݵ���Ѯ
			cal.set(Calendar.DAY_OF_MONTH, 15);
		}
		else if(type==2)
		{
			//��ȡĳ���������
			int lastDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
			//�����������·ݵ��������
			cal.set(Calendar.DAY_OF_MONTH, lastDay);
		}
		//��ʽ������
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String lastDayOfMonth = sdf.format(cal.getTime());
		
		return lastDayOfMonth;
	}
	//�Ƿ���ʱ��������,s����Ҫ�Ƚϵ�ʱ��,begin,end��ʱ������
	//�Ƿ���true
	public static boolean isdatein(String begin,String end,String s)
	{
		//ToolClass.Log(ToolClass.INFO,"EV_JNI","APP<<"+s+"��"+begin+"="+dateCompare(s,begin));
		//ToolClass.Log(ToolClass.INFO,"EV_JNI","APP<<"+s+"��"+end+"="+dateCompare(end,s));
		if((dateCompare(s,begin)>=0)&&(dateCompare(end,s)>=0))
		{
			return true;
		}
		return false;
	}
	//ʱ��Ƚ�,����ֵresult==0s1���s2,result<0s1С��s2,result:>0s1����s2,
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
			System.err.println("��ʽ����ȷ");
		}
		return c1.compareTo(c2);
	}
	
	//�õ�hopper�豸�ĵ�ǰ״̬
	public static String gethopperstats(int hopper)
	{
		String res=null;
		//"hopper":8��hopper��״̬,0����,1ȱ��,2����,3ͨѶ����
		if(hopper==0)
		{
			res="����";
		}
		else if(hopper==1)
		{
			res="ȱ��";
		}
		else if(hopper==2)
		{
			res="����";
		}
		else if(hopper==3)
		{
			res="ͨѶ����";
		}
		return res;
	}
	
	//Ϊ�ϴ���serverʹ�ã�����ǰʱ��ת��Ϊ��server�ϱ���ʱ��
	public static String getLasttime()
	{
		SimpleDateFormat tempDate = new SimpleDateFormat("yyyy-MM-dd"); //��ȷ������ 
		SimpleDateFormat tempTime = new SimpleDateFormat("HH:mm:ss"); //��ȷ������ 
        String datetime = tempDate.format(new java.util.Date()).toString()+"T"
        		+tempTime.format(new java.util.Date()).toString(); 
		return datetime;
	}
	
	//Ϊ�ϴ���serverʹ�ã�����һ��ʱ�䣬ת��Ϊ��server�ϱ���ʱ��
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
		SimpleDateFormat tempDate = new SimpleDateFormat("yyyy-MM-dd"); //��ȷ������ 
		SimpleDateFormat tempTime = new SimpleDateFormat("HH:mm:ss"); //��ȷ������ 
        String datetime = tempDate.format(date).toString()+"T"
        		+tempTime.format(date).toString(); 
		return datetime;
	}
	
	//��ȡ�豸״̬���ϴ���������
	//dev�豸��1ֽ����,2Ӳ����,3hopper1,4hopper2,5hopper3,6hopper4,7hopper5,8hopper6,9hopper7,10hopper8
	//���أ�2���ϣ�0����
	public static int getvmcStatus(Map<String, Object> Set,int dev)
	{
		//ToolClass.Log(ToolClass.INFO,"EV_JNI","APP<<�ֽ��豸״̬="+rst,"log.txt");	
		int rst=0;
		switch (dev) 
		{
			//ֽ����
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
			//Ӳ����	
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
	
	
	
	//�ж��ַ����Ƿ�Ϊ��:true�գ�false�ǿ�
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
	
	//���Service�Ƿ�������
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
