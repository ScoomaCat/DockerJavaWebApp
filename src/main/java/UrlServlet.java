import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

import jakarta.servlet.*;
import jakarta.servlet.http.*;

public class UrlServlet extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");

        String url1 = getServletContext().getInitParameter("URL1");
        String url2 = getServletContext().getInitParameter("URL2");
        String requestUrl1 = request.getParameter("url1");
        String requestUrl2 = request.getParameter("url2");

        if (validateParams(requestUrl1, requestUrl2)) {
            String httpResponse = makeHttpRequest("GET", requestUrl1 != null && !requestUrl1.isEmpty() ? url1 : url2);

            try (PrintWriter writer = response.getWriter()) {
                writer.println("<!DOCTYPE html><html>");
                writer.println("<head>");
                writer.println("<meta charset=\"UTF-8\" />");
                writer.println("<title></title>");
                writer.println("</head>");
                writer.println("<body>");
                writer.println("Url1 is" + requestUrl1);
                writer.println("Url2 is" + requestUrl2);
                writer.println("<h2>GET REQUEST RESULT</h2>");

                writer.println("<code>" + httpResponse.replace("<", "&lt;").replace(">", "&gt;")
                + "</code>");

                writer.println("</body>");
                writer.println("</html>");
            }

        }
        else {
            try (PrintWriter writer = response.getWriter()) {
                writer.println("<!DOCTYPE html><html>");
                writer.println("<head>");
                writer.println("<meta charset=\"UTF-8\" />");
                writer.println("<title></title>");
                writer.println("</head>");
                writer.println("<body>");

                writer.println("<h1>Sorry pal, you gotta send url1 or ulr2 parameter here</h1>");

                writer.println("</body>");
                writer.println("</html>");
            }
        }
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    private boolean validateParams(String requestUrl1, String requestUrl2)
    {
        return (requestUrl1 != null && !requestUrl1.isEmpty()) || (requestUrl2 != null && !requestUrl2.isEmpty());
    }

    private String makeHttpRequest(String method, String targetURL) {
        try {
            URL obj = new URL(targetURL);
            HttpURLConnection con = null;

            con = (HttpURLConnection) obj.openConnection();
            con.setRequestMethod(method);
            int responseCode = con.getResponseCode();
            System.out.println("GET Response Code :: " + responseCode);
            if (responseCode == HttpURLConnection.HTTP_OK) { // success
                BufferedReader in = new BufferedReader(new InputStreamReader(
                        con.getInputStream()));
                String inputLine;
                StringBuffer response = new StringBuffer();

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();

                // print result
                return response.toString();
            } else {
                return "GET request was executed but code was not success";
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "GET request not worked";
    }
}
