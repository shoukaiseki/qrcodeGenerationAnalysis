package org.shoukaiseki.servlet;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.*;
import javax.servlet.http.*;

/**
 * org.shoukaiseki.servlet.QRCodeServlet <br>
 *
 * @author 蒋カイセキ    Japan-Tokyo  2018-06-29 13:24:23<br>
 * ブログ http://shoukaiseki.blog.163.com/<br>
 * E-メール jiang28555@Gmail.com<br>
 **/
public class QRCodeServlet extends HttpServlet{

        public void init() throws ServletException
        {
        }

        public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
        {

            String codemsg=request.getParameter("codemsg");

            if(codemsg==null){
                System.out.println("codemsg is null");
                return;
            }


            if (response.containsHeader("Pragma")) {
                response.setHeader("Pragma", "cache");
            }

            if (response.containsHeader("Cache-Control")) {
                response.setHeader("Cache-Control", "public");
            }

            String requestUri = request.getRequestURI();
            String[] uriValues = requestUri.substring(request.getContextPath().length()).split("/");
            String imageId = uriValues[3];
//        System.out.println("imageId="+imageId);
            HttpSession session = request.getSession();
            session.setAttribute(imageId, imageId);
            String imageType = "image/jpeg";
            try {

                MultiFormatWriter multiFormatWriter = new MultiFormatWriter();//Zxing是Google提供的关于条码

                Map hints = new HashMap();
                hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
                BitMatrix bitMatrix = multiFormatWriter.encode(codemsg, BarcodeFormat.QR_CODE, 400, 400,hints);

                response.setContentType(imageType);
                ServletOutputStream sOutStream = response.getOutputStream();
                sOutStream.flush();

                MatrixToImageWriter.writeToStream(bitMatrix,"jpg",sOutStream);
                sOutStream.close();
            } catch (Exception var15) {
                var15.printStackTrace();
            }
        }

        public void destroy()
        {
            // do nothing.
        }
}
