<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>${param.inputDept} 학과 검색 결과</title>
    
    <style>
       #result-table{
           border-collapse: collapse; /* td/th 테두리 고유 테두리 삭제 */
       }
    
       thead th{
          background-color : black;
          color : white;
          padding: 5px 30px;
       }
       
       tbody td:not(:nth-child(5)){
          text-align: center;
       }
       
       tbody > tr:nth-child(2n) td{
          background-color : #ddd;
       }
       
       tbody > tr:hover> td{
          background-color: #B1AFFF;
          cursor: pointer;
       }
       
    </style>
</head>
<body>

   <h1>[${param.inputDept}] 학과 검색 결과</h1>

   <table id="result-table">
      <thead>
         <tr>
            <th>순서</th>
            <th>학번</th>
            <th>이름</th>
            <th>학과</th>
            <th>주소</th>
         </tr>   
      </thead>
      
      <tbody>
         <c:choose>
            <c:when test="${empty stdList}">
               <tr>
                  <th colspan="5">일치하는 학과가 존재하지 않습니다.</th>
               </tr>
            </c:when>
            
            <c:otherwise>
               <c:forEach var="std" items="${stdList}" varStatus="vs">
                  <tr>
                     <td>${vs.count}</td>
                     <td>${std.studentNo}</td>
                     <td>${std.studentName}</td>
                     <td>${std.departmentName}</td>
                     <td>${std.studentAddress}</td>
                  </tr>      
               </c:forEach>
            </c:otherwise>
         </c:choose>
      </tbody>
   </table>   
   
</body>
</html>