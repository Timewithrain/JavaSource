import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

public class UploadPhotoServlet extends HttpServlet {
	
	public void doPost(HttpServletRequest request,HttpServletResponse response) {
		
		String filename = null;
        try {
            DiskFileItemFactory factory = new DiskFileItemFactory();
            ServletFileUpload upload = new ServletFileUpload(factory);
            // 设置上传文件的大小限制为1M
            factory.setSizeThreshold(1024 * 1024);
             
            List items = null;
            try {
                items = upload.parseRequest(request);
            } catch (FileUploadException e) {
                e.printStackTrace();
            }
  
            Iterator iter = items.iterator();
            while (iter.hasNext()) {
                FileItem item = (FileItem) iter.next();
                if (!item.isFormField()) {
  
                    // 根据时间戳创建头像文件
                    filename = System.currentTimeMillis() + ".jpg";
                     
                    //通过getRealPath获取上传文件夹，上传文件自动获取到/j2ee/web/uploaded路径的文件夹中
                    //getRealPath方法返回上传文件在服务器上应存放的位置的绝对路径
                    String photoFolder =request.getServletContext().getRealPath("uploaded");
                    //根据文件名和文件路径在服务器对应路径下创建文件
                    File f = new File(photoFolder, filename);
                    f.getParentFile().mkdirs();
  
                    // 通过item.getInputStream()获取浏览器上传的文件的输入流
                    InputStream is = item.getInputStream();
                    //将浏览器上传的文件内容写入服务器创建的对应文件中
                    FileOutputStream fos = new FileOutputStream(f);
                    byte b[] = new byte[1024 * 1024];
                    int length = 0;
                    //从传入的文件内容读到b中，再从b中写入服务器文件
                    while (-1 != (length = is.read(b))) {
                        fos.write(b, 0, length);
                    }
                    fos.close();
  
                } else {
                	//当上传的内容为常规字段时输出常规字段值
                    System.out.println(item.getFieldName());
                    String value = item.getString();
                    value = new String(value.getBytes("ISO-8859-1"), "UTF-8");
                    System.out.println(value);
                }
            }
            //设置图片在网页中的显示大小和文件路径
            String html = "<img width='200' height='200' src='uploaded/%s' />";
            response.setContentType("text/html");
            PrintWriter pw= response.getWriter();
             
            pw.format(html, filename);
             
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
		
	}
	
}
