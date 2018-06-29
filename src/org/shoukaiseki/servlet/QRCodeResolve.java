package org.shoukaiseki.servlet;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.google.zxing.*;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 * org.shoukaiseki.servlet.QRCodeResolve <br>
 *
 * @author 蒋カイセキ    Japan-Tokyo  2018-06-29 14:47:43<br>
 * ブログ http://shoukaiseki.blog.163.com/<br>
 * E-メール jiang28555@Gmail.com<br>
 **/
public class QRCodeResolve  extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //显示数据
        resp.setContentType("text/html;charset=UTF-8");
        PrintWriter pw = resp.getWriter();

        //上传的临时目录路径
        ServletConfig config = this.getServletConfig();
        String filetemp=config.getInitParameter("temppath");
        File filetemppath=new File(filetemp);
        if(filetemppath.exists()){
            filetemppath.mkdirs();
        }

        req.setCharacterEncoding("UTF-8");

        FileItem file=null;

        //检查我们是否有文件上传请求
        boolean isMultipart = ServletFileUpload.isMultipartContent(req);

        if(!isMultipart){
            pw.println("isMultipart="+isMultipart);
            pw.println("<br>");
            pw.println("请选择要上传的二维码图片");
            return;
        }

        //1,声明DiskFileItemFactory工厂类，用于在指定磁盘上设置一个临时目录
        DiskFileItemFactory disk = new DiskFileItemFactory(1024*10,(filetemppath));
        //2,声明ServletFileUpload，接收上边的临时文件。也可以默认值
        ServletFileUpload up = new ServletFileUpload(disk);
        //3,解析request
        try {
            List<FileItem> list = up.parseRequest(req);
            //如果就一个文件，
            file = list.get(0);



            //获取文件名：
            String fileName = file.getName();
            //获取文件的类型：
            String fileType = file.getContentType();
            //获取文件的字节码：
            InputStream in = file.getInputStream();

            MultiFormatReader formatReader = new MultiFormatReader();
            BufferedImage image = ImageIO.read(in);
            LuminanceSource source = new BufferedImageLuminanceSource(image);
            Binarizer binarizer = new HybridBinarizer(source);
            BinaryBitmap binaryBitmap = new BinaryBitmap(binarizer);
            Map hints = new HashMap();
            hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
            Result result = formatReader.decode(binaryBitmap,hints);





            pw.println("result = "+ result.toString());
            pw.println("<br>");
            pw.println("resultFormat = "+ result.getBarcodeFormat());
            pw.println("<br>");
            pw.println("resultText = "+ result.getText());
        } catch (Exception e) {
            e.printStackTrace();
            pw.println("<br>");
            pw.println("二维码解析错误");
        } finally {

            try {
                if(file!=null){
                    file.delete();
                    System.out.println("<br>");
                    System.out.println("缓存删除");
                }
            }catch (Exception ee){

            }
        }

    }
}
