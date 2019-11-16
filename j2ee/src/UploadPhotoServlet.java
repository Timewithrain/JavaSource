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
            // �����ϴ��ļ��Ĵ�С����Ϊ1M
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
  
                    // ����ʱ�������ͷ���ļ�
                    filename = System.currentTimeMillis() + ".jpg";
                     
                    //ͨ��getRealPath��ȡ�ϴ��ļ��У��ϴ��ļ��Զ���ȡ��/j2ee/web/uploaded·�����ļ�����
                    //getRealPath���������ϴ��ļ��ڷ�������Ӧ��ŵ�λ�õľ���·��
                    String photoFolder =request.getServletContext().getRealPath("uploaded");
                    //�����ļ������ļ�·���ڷ�������Ӧ·���´����ļ�
                    File f = new File(photoFolder, filename);
                    f.getParentFile().mkdirs();
  
                    // ͨ��item.getInputStream()��ȡ������ϴ����ļ���������
                    InputStream is = item.getInputStream();
                    //��������ϴ����ļ�����д������������Ķ�Ӧ�ļ���
                    FileOutputStream fos = new FileOutputStream(f);
                    byte b[] = new byte[1024 * 1024];
                    int length = 0;
                    //�Ӵ�����ļ����ݶ���b�У��ٴ�b��д��������ļ�
                    while (-1 != (length = is.read(b))) {
                        fos.write(b, 0, length);
                    }
                    fos.close();
  
                } else {
                	//���ϴ�������Ϊ�����ֶ�ʱ��������ֶ�ֵ
                    System.out.println(item.getFieldName());
                    String value = item.getString();
                    value = new String(value.getBytes("ISO-8859-1"), "UTF-8");
                    System.out.println(value);
                }
            }
            //����ͼƬ����ҳ�е���ʾ��С���ļ�·��
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
