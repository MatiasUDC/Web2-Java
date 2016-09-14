<%-- 
    Document   : indexjsp
    Created on : 06-sep-2016, 1:05:41
    Author     : Matias
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="cabeceras.jsp" />
        <title>Clientes</title>
    </head>
    <body>
        <div class="container">
            <div class="row">
                <nav class="navbar navbar-default" role="navigation">
                      <!-- El logotipo y el icono que despliega el menú se agrupan
                           para mostrarlos mejor en los dispositivos móviles -->
                    <div class="navbar-header">
                        <button type="button" class="navbar-toggle" data-toggle="collapse"
                                data-target=".navbar-ex1-collapse">
                             <span class="sr-only">Desplegar navegación</span>
                             <span class="icon-bar"></span>
                            <span class="icon-bar"></span>
                            <span class="icon-bar"></span>
                        </button>
                        <a class="navbar-brand" href="#">Sistema de Clientes</a>
                    </div>
                      <!-- Agrupar los enlaces de navegación, los formularios y cualquier
                           otro elemento que se pueda ocultar al minimizar la barra -->
                    <div class="collapse navbar-collapse navbar-ex1-collapse">
                        <ul class="nav navbar-nav navbar-right">
                          <li class="dropdown">
                            <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                              Menú<b class="caret"></b>
                            </a>
                            <ul class="dropdown-menu">
                              <li>
                                  <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">
                                        <span class="glyphicon glyphicon-user"></span>
                                        <c:out value="${usuario.nombre}"/>
                                  </a>
                              </li>
                              <li class="divider"></li>
                              <li><a href="logout">Cerrar Session</a></li>
                            </ul>
                          </li>
                        </ul>
                      </div>
                </nav>
                <div class="col-md-8">
                    <h3>Clientes 
                        <c:if test="${permisos.tienePermiso('INSERT')}">
                            <a href="AltaFormulario"><small>Nuevo</small></a>
                        </c:if>
                    </h3>
                    <div class="table-responsive">
                        <table class="table">
                            <tr>
                                <td><strong>Nombre</strong></td>
                                <td><strong>Edad</strong></td>
                                <td><strong>Nacionalidad</strong></td>
                                <td><strong>Activo</strong></td>
                                <td></td>
                                <td></td>
                            </tr>
                            <c:forEach var="fila" items="${clientes}" >
                                <tr>
                                    <td><c:out value="${fila['nombre']}"/></td>
                                    <td><c:out value="${fila['edad']}"/></td>
                                    <td><c:out value="${fila['nacionalidad']}"/></td>
                                    <c:choose>
                                        <c:when test="${fila.activo eq 1}">
                                            <td><span class="glyphicon glyphicon-ok"></span></td>   
                                        </c:when>    
                                        <c:otherwise>
                                            <td><span class="glyphicon glyphicon-remove"></span></td>
                                           </c:otherwise>
                                    </c:choose>
                                        <td>
                                            <c:if test="${permisos.tienePermiso('UPDATE')}">
                                                <a href="Modificacion?id=<c:out value="${fila['id']}"/>" class="btn btn-info" >
                                                    <span class="glyphicon glyphicon-pencil"></span> Modificar
                                                </a>
                                            </c:if>
                                        </td>
                                        <td>
                                            <c:if test="${permisos.tienePermiso('DELETE')}">
                                                <a class="btn btn-danger" href="ProcesarBaja?id=<c:out value="${fila['id']}"/>" class="btn btn-default" >
                                                    <span class="glyphicon glyphicon-trash"></span> Eliminar
                                                </a>
                                            </c:if>
                                    </td>
                                </tr>
                            </c:forEach>
                        </table>
                    </div>
                </div>
                <div class="col-md-4">
                    <c:if test="${not empty eliminado}">
                        <div class="alert alert-danger">
                        <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
                            <strong>Eliminacion:</strong> El cliente se ha eliminado.
                         </div>
                    </c:if>
                    <c:if test="${not empty modificado}">
                        <div class="alert alert-info">
                        <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
                            <strong>Modificacion:</strong> El cliente se ha modificado.
                         </div>
                    </c:if>
                    <c:if test="${not empty insertado}">
                        <div class="alert alert-success">
                        <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
                            <strong>Guardado:</strong> El cliente se ha guardado.
                         </div>
                    </c:if>
                </div>
            </div>
        </div>
    </body>
</html>