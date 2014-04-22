<%-- 
    Document   : renderdrugcode
    Created on : Apr 22, 2014, 2:01:05 PM
    Author     : Rain
--%>

<%@page import="javax.faces.bean.RequestScoped"%>
<%@page import="java.util.ArrayList"%>
<%@page import="th.co.geniustree.training.searching.drugcode.Drug"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
    <center>
        <a href="index.html">back to main</a><br/>
    </center>


    <%
        
        HttpSession mySession = request.getSession();
        List<Drug> drugList = (List<Drug>) request.getAttribute("drug");
        for (Drug drug : drugList) {
            out.println("DrugCode : " + drug.getDrugCode() + "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" + "Drug ID : " + drug.getId());%><br/><br/><%
        }
        
        
        
    %>


</body>
</html>
