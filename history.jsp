<%@page import="java.util.ArrayList"%>
<%@page import="com.Accio.HistoryResult"%>
<html>
<body>
<table border=2>
<tr>
<td>Keyword</td>
<td>Link</td>
</tr>
<%
 ArrayList<HistoryResult>results=(ArrayList<HistoryResult>)request.getAttribute("results");
 for(HistoryResult result:results){
%>
<tr>
<td><%out.println(result.getKeyword());%></td>
<td><a href="<%out.println(result.getLink());%>"><%out.println(result.getLink());%></td>
</tr>
<%
}
%>
</body>
</html>