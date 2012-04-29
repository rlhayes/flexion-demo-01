<%@ include file="/WEB-INF/views/includes/taglibs.jsp"%>
                <p>Files processed:</p>
                <ul >
                    <c:choose>
                        <c:when test="${not empty processed}">
                            <c:forEach items="${processed}" var="f">
                                <li>
                                <c:out value="${f}"/>
                                </li>
                            </c:forEach>
                        </c:when>
                        <c:otherwise>No files processed yet.</c:otherwise>
                    </c:choose>
                </ul>
