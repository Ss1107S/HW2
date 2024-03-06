package by.orlova.controller;

import by.orlova.entity.Employee;
import by.orlova.parser.JsonParser;
import by.orlova.service.EmployeeService;
import by.orlova.service.impl.EmployeeServiceImpl;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/employees/*")
public class MainServlet extends HttpServlet {

    private final JsonParser parser = new JsonParser();
    private final EmployeeService employeeService = new EmployeeServiceImpl();

    /**
     * HTTP GET implementation shows all employees.
     *
     * @param req
     * @param resp
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        resp.setContentType("application/json");
        PrintWriter printWriter;
        try {
            printWriter = resp.getWriter();
            if (req.getPathInfo() != null && !req.getPathInfo().equals("/")) {
                int id = Integer.parseInt(req.getPathInfo().replace("/", ""));
                printWriter.write(parser.parseObjectToJson(employeeService.findEntityById(id)));
            } else {
                printWriter.write(parser.parseObjectToJson(employeeService.findAll()));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        printWriter.close();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String pathInfo = request.getPathInfo();
        if (pathInfo == null || pathInfo.equals("/")) {
            Employee employeeFromRequest = getEmployeeFromRequest(request);
            employeeService.create(employeeFromRequest);
            sendAsJson(response, employeeFromRequest);
        } else {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST);
        }
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        String pathInfo = request.getPathInfo();

        if (pathInfo == null || pathInfo.equals("/")) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST);
        }
        String[] splits = pathInfo.split("/");
        if (splits.length != 2) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }
        int employeeId = Integer.parseInt(splits[1]);
        if (employeeService.findEntityById(employeeId) == null) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }
        Employee employee = getEmployeeFromRequest(request);
        employee.setId(employeeId);
        employeeService.update(employee);
        sendAsJson(response, employee);
    }

    private Employee getEmployeeFromRequest(HttpServletRequest request) throws IOException {
        StringBuilder buffer = new StringBuilder();
        BufferedReader reader = request.getReader();
        String line;
        while ((line = reader.readLine()) != null) {
            buffer.append(line);
        }
        String payload = buffer.toString();
        return parser.parseJsonToObject(payload);
    }

    protected void doDelete(
            HttpServletRequest request, HttpServletResponse response) throws IOException {
        String pathInfo = request.getPathInfo();
        Employee employee;

        if (pathInfo == null || pathInfo.equals("/")) {

            response.sendError(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }
        String[] splits = pathInfo.split("/");
        if (splits.length != 2) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }
        int employeeId = Integer.parseInt(splits[1]);
        if (employeeService.findEntityById(employeeId) == null) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
        employee = employeeService.findEntityById(employeeId);
        employeeService.delete(employeeId);
        sendAsJson(response, employee);
    }

    private void sendAsJson(
            HttpServletResponse response, Object obj) throws IOException {
        response.setContentType("application/json");
        String res = parser.parseObjectToJson(obj);
        PrintWriter out = response.getWriter();
        out.print(res);
        out.flush();
    }


}