import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.Objects;

@WebServlet(name = "CccServlet", value = "/CccServlet")
public class CccServlet extends HttpServlet {
    private static final String OLD_STATE = "old";
    private static final String NEW_STATE = "new";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String value1 = request.getParameter("value1");
        String value2 = request.getParameter("value2");
        String value3 = request.getParameter("value3");
        String cBeanState = request.getParameter("CBean");
        CBean cBean = null;

        if (!validateCBean(cBeanState)) {
            response.sendRedirect(request.getContextPath() + "/index.jsp?cBean=" + cBeanState);

            return;
        }

        if (Objects.equals(cBeanState, OLD_STATE)) {
            cBean = (CBean) getServletContext().getAttribute("CBean");

            if (cBean == null) {
                cBean = new CBean();
            }
        } else {
            cBean = new CBean();
        }

        cBean.setValue1(value1 != null ? value1 : cBean.getValue1());
        cBean.setValue2(value2 != null ? value2 : cBean.getValue2());
        cBean.setValue3(value3 != null ? value3 : cBean.getValue3());

        getServletContext().setAttribute("CBean", cBean);

        response.sendRedirect(request.getContextPath() + "/Ccc.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    private boolean validateCBean(String cBeanState)
    {
        return Objects.equals(cBeanState, OLD_STATE) || Objects.equals(cBeanState, NEW_STATE);
    }
}
