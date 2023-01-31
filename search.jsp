<%@page import="java.util.ArrayList"%>
<%@page import="com.Accio.SearchResult"%>
<html>
<body>
<form action="Search">
     <input type="text" name="keyword">
     <button type="submit">Search</button>
</form>
<table border=2>
<tr>
<td>Title</td>
<td>Link</td>
</tr>
<%
ArrayList<SearchResult>results=(ArrayList<SearchResult>)request.getAttribute("results");
for(SearchResult result:results){
%>
<tr>
<td><%out.println(result.getPageTitle());%></td>
<td><a href="<%out.println(result.getPageLink());%>"><%out.println(result.getPageLink());%></td>
</tr>
<%
}
%>
<table>
</body>
</html>