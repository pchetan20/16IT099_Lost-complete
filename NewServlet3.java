

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import static java.lang.System.in;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class NewServlet3 extends HttpServlet {

  
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter(); 
          
        
    }

    
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
        
         PrintWriter out = response.getWriter();
         String userfname = request.getParameter("userfname");
        try {
         // Register JDBC driver
         Class.forName("com.mysql.jdbc.Driver");

         // Open a connection
         Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/simple", "root", "");
          //out.println("connected..");
         // Execute SQL query
         Statement stmt = conn.createStatement();
         String sql;
         sql = "SELECT * FROM findlost";
         ResultSet rs = stmt.executeQuery(sql);
         
         int a=0;
        /*  String str1 = "button1";
            String str2 = "button2";
            String str3 = "button3";
            String str4 = "button4";
            String str5 = "button5";
         
      
      out.println("111");
      String b = request.getParameter("b");
         out.println(b);
        

            out.println("222");
            
           
            
        
            
            
            out.println("333");
         */
         // Extract data from result set
         while(rs.next()){
             
            //Retrieve by column name
            String uname = rs.getString("uname");
            String iname = rs.getString("iname");
            String wtoi = rs.getString("wtoi");
            String othdis = rs.getString("othdis");
            String email = rs.getString("email");
            String cnumber = rs.getString("cnumber");
            
            
            String itemfname = request.getParameter("itemfname");
            String ftypeofitem = request.getParameter("ftypeofitem");
            String fothdis = request.getParameter("fothdis");
            String femail = request.getParameter("femail");
            String fcontactnumber = request.getParameter("fcontactnumber");
            String view="";
            
           
                
                if(userfname.equals(uname) ){
                    a=1;
           
                    out.println("Matched...");
                    out.println("<br>");
                    out.println("Name:"+uname);
                    out.println("<br>");
                    out.println("Item Name:"+iname);
                    out.println("<br>");          
                    out.println("What Type Of Item:"+wtoi);
                    out.println("<br>");
                    out.println("Other Discription:"+othdis);
                    out.println("<br>");
                    out.println("Email:"+email);
                    out.println("<br>");
                    out.println("Contact Number:"+cnumber);
                    out.println("<br>");
                    out.println("Image:");
                    out.println("<form>");
                    out.println("");
                    out.println("</form>");
                   //out.println("<a href=\"newjsp3.jsp\">view</a>");
                   /* Blob blob = rs.getBlob("image");
                    byte byteArray[] = blob.getBytes(1, (int)blob.length());
                    response.setContentType("image/png");
                    request.setAttribute("image", blob);
                   try (OutputStream os = response.getOutputStream()) {
                        os.write(byteArray);
                        os.flush();
                        os.close();
                    }catch(Exception e){
                        out.println(e);
                    }*/
                   //out.println("img:"+request.getAttribute("image"));
                    out.println("<br>");
                    request.setAttribute("uname", userfname);
                    RequestDispatcher dispatcher =   getServletContext().getRequestDispatcher("/newjsp3.jsp");
                    dispatcher.include(request, response);
           
                }
                
           

         }
          if(a==0){
            
            String msg="Sorry!..Data not found related to ";
           out.println("<br><br>"+"<font size='6' color=blue>" + msg +"</font>"+"<font color=red size='6'>"+userfname+"</font>");
            RequestDispatcher dispatcher =   getServletContext().getRequestDispatcher("/newjsp3.jsp");
            dispatcher.include(request, response);
           
        }
           
         // Clean-up environment
         rs.close();
         stmt.close();
         conn.close();
      } catch(SQLException se) {
         //Handle errors for JDBC
         se.printStackTrace();
      } catch(Exception e) {
         //Handle errors for Class.forName
         e.printStackTrace();
      }
       
    }

}